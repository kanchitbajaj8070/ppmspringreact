package com.example.ppmfullstack.service;

import com.example.ppmfullstack.domain.Backlog;
import com.example.ppmfullstack.domain.Project;
import com.example.ppmfullstack.domain.ProjectTask;
import com.example.ppmfullstack.exceptions.ProjectNotFoundException;
import com.example.ppmfullstack.repository.BacklogRepository;
import com.example.ppmfullstack.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTaskService {

@Autowired
   private  BacklogRepository backlogRepository;
@Autowired
    private ProjectTaskRepository projectTaskRepository;
@Autowired
private     ProjectService projectService;
public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username) {// Id=abcd
    //we want taks like->
    //abcd-1, abcd-2 ,abcd-3
    // update the backlog sequence on addintion
    //Intial priority if intial priority is null
    //status has to be set indtially when
    projectIdentifier=projectIdentifier.toUpperCase();
    Backlog backlog = null;
    backlog = projectService.getProjectByIdentifier(projectIdentifier,username).getBacklog() ;
    if (backlog == null)
        throw new ProjectNotFoundException("Project "+projectIdentifier+" Not found");
    else {
        projectTask.setBacklog(backlog);
        Integer backlogSequence = backlog.getPTSequence() == null ? 0 : backlog.getPTSequence();
        projectTask.setProjectSequence(projectIdentifier + "-" + (backlogSequence + 1));
        backlog.setPTSequence(backlogSequence + 1);
        projectTask.setProjectIdentifier(projectIdentifier);
        if (projectTask.getPriority() == null || (projectTask.getPriority() != null && projectTask.getPriority() == 0))
            projectTask.setPriority(3);
        if (projectTask.getStatus() == null || (projectTask.getStatus() != null && projectTask.getStatus().equals("")))
            projectTask.setStatus("TO_DO");
        return projectTaskRepository.save(projectTask);
    }
}
public List<ProjectTask> getAllTasksById( String projectId,String username)
{List<ProjectTask> projectTasks= new ArrayList<>();
Backlog backlog= projectService.getProjectByIdentifier(projectId,username).getBacklog();
    if (backlog == null)
        throw new ProjectNotFoundException("Project "+projectId+" Not found");
for( ProjectTask task:projectTaskRepository.getAllByBacklogOrderByPriority(backlog))
    projectTasks.add(task);
return projectTasks;
}
    public ProjectTask findProjectTaskBySequence( String backlogId,String projectSequence,String username)
    {
         Backlog backlog=projectService.getProjectByIdentifier(backlogId,username).getBacklog();
        if( backlog==null)
            throw new ProjectNotFoundException("Project "+backlogId+" Not found");
        ProjectTask projectTask=projectTaskRepository.findFirstByProjectSequence(projectSequence);
        if( projectTask==null)
            throw new ProjectNotFoundException("Project Task With "+projectSequence+" Not found");
       if( !projectTask.getBacklog().equals(backlog))
           throw new ProjectNotFoundException("Project Task  With "+projectSequence+" does not exist in project with id "+
                   backlogId+" . Combination Not found ");
        return projectTask;
    }
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId,String username)
    {
        //this checks for any possible errors.
        Backlog backlog = projectService.getProjectByIdentifier(backlogId,username).getBacklog() ;
        if (backlog == null)
            throw new ProjectNotFoundException("Project "+backlogId+" Not found");
        updatedTask.setBacklog(updatedTask.getBacklog());
        return projectTaskRepository.save(updatedTask);

    }
    public void deleteByProjectSequence( String backlogId,String projectSequence,String username)
    {
        //this checks for any possible errors.
        ProjectTask pt=findProjectTaskBySequence(backlogId,projectSequence,username);
         projectTaskRepository.delete(pt);
    }


}
