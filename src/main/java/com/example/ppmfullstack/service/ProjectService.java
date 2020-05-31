package com.example.ppmfullstack.service;

import com.example.ppmfullstack.domain.Backlog;
import com.example.ppmfullstack.domain.Project;
import com.example.ppmfullstack.domain.ProjectTask;
import com.example.ppmfullstack.domain.User;
import com.example.ppmfullstack.exceptions.ProjectIdException;
import com.example.ppmfullstack.exceptions.ProjectNotFoundException;
import com.example.ppmfullstack.repository.BacklogRepository;
import com.example.ppmfullstack.repository.ProjectRepository;
import com.example.ppmfullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private BacklogRepository backlogRepository;
@Autowired
    private ProjectRepository projectRepository;
@Autowired
private UserRepository userRepository;
public void saveOrUpdateProject( Project project,String username)
{

    /*Some logic to handle the update case*/
    if(project.getId()!=null)
    {
        Project existingProject=projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
        if(existingProject!=null&&!(existingProject.getProjectLeader().equals(username)))
            throw new ProjectNotFoundException("Project Not Found in your account");
        // to handle case when we put an invalid id and still update takes place
        else if( existingProject==null&&project.getId()!=null)
            throw new ProjectNotFoundException("This Project Id doesnt exist and cant be updated");

    }


    try
    {  User user=userRepository.findUserByUsername(username);
    project.setUser(user);
    project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
    project.setProjectLeader(username.toLowerCase());
if(project.getId()==null)
{//backlog sets only on creation
    Backlog b= new Backlog();
    b.setProject(project);
    project.setBacklog(b);
    b.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
}
else//updating
{
    project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier()));
}
projectRepository.save(project);
}
catch (Exception e)
{
    throw new ProjectIdException("Project Id"+ project.getProjectIdentifier().toUpperCase()+"already Exists.");
}

}
public List<Project> getAllProjects(String name)
{
    List<Project> projects= new ArrayList<>();
    for( Project p:projectRepository.findAllByProjectLeader(name))
        projects.add(p);
    return projects;
}
public Project getProjectByIdentifier( String projectId,String username)
{

    Project project= projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if( project==null)
        throw new ProjectIdException( projectId+" does not exists");
    if( !project.getProjectLeader().equals(username))
        throw  new ProjectNotFoundException(" Project not found in your account . PLease Try Again");
    return project;
}
public void deleteProjectByIdentifer( String projectId,String username)
{

    Project project= projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if( project==null)
        throw new ProjectIdException( " Cant delete project"+projectId+" as it doesnt exisits");
    if( !project.getProjectLeader().equals(username))
        throw  new ProjectNotFoundException("You are not the owner of this project . Cant delete it ");
    projectRepository.delete(project);
}

}
