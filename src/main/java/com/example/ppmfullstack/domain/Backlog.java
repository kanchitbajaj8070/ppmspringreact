package com.example.ppmfullstack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* Each project has one backlog and each backlog has a number of project tasks*/
@Entity
public class Backlog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence=0;
    private String projectIdentifier;
    public Backlog(){
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore// to prevent infinite recursion
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Backlog(Integer PTSequence, String projectIdentifier) {
        this.PTSequence = PTSequence;
        this.projectIdentifier = projectIdentifier;
    }

    public Backlog(Project project) {
        this.project = project;
    }

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH,orphanRemoval = true ,mappedBy = "backlog")
    //very import REFRESH and orphan removal to remove unreference project taks( children) from backlog
    private List<ProjectTask> projectTasks= new ArrayList<>();
}
