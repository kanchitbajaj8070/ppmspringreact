package com.example.ppmfullstack.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please enter a project name")
    private  String projectName;
    @NotBlank(message="please enter a default value")
    //we have to use a dependancy of hibernate-validator
    @Size(min=4,max=5,message =  "Please enter a project Identifier of 4 or 5 words only")
    @Column(updatable = false,unique = true)  // we dont want accident updation
    private String projectIdentifier;//Never use project_id because when using foreign keys it creates problems
 @NotBlank(message = "Please enter a valid description")
 private String description;
 @JsonFormat(pattern = "yyyy-mm-dd")
 @NotNull(message="please enter a start date of the project")
 private Date start_date;
 @JsonFormat(pattern = "yyyy-mm-dd")
 @NotNull(message="please enter a end date of project")
 private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
 private Date created_at;
    @JsonFormat(pattern = "yyyy-mm-dd")
 private Date updated_at;


    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /*   The @PrePresist annotate model methods to indicate that the method should be called before the entity is
           inserted (persisted) into the database.
           The @PreUpdate is used to configure a pre-update callback for the entity model, i.e., it is used to annotate methods in models
            to indicate an operation that should be triggered before an entity has been updated in the database.
            */
    @PrePersist
    public void onCreate()
    {
        this.created_at=new Date();
    }
    @PreUpdate
    public void onUpdate()
    {
        this.updated_at=new Date();
    }

    @OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;
    public Backlog getBacklog() {
        return backlog;
    }
    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }
        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    private String projectLeader;

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }
}
