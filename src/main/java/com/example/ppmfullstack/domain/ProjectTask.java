package com.example.ppmfullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ProjectTask {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(updatable = false,unique = true)
private String projectSequence;
@NotBlank(message ="please include a summary")
private String summary;
private String acceptanceCriteria;
private String status;
private Integer priority;
@JsonFormat(pattern = "yyyy-mm-dd")
@NotNull(message="please enter a due date of the project")
private Date dueDate;
@JsonFormat(pattern = "yyyy-mm-dd")
private Date create_at;
@Column(updatable = false)
private String projectIdentifier;
@JsonFormat(pattern = "yyyy-mm-dd")
private Date update_at;
@PrePersist
public void onCreate()
{
    this.create_at=new Date();
}
@PreUpdate
    public void onUpdate()
    {
        this.update_at=new Date();
    }
public ProjectTask()
{

}



    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("id -");sb.append(id);sb.append(" , ");
        sb.append("projectSequence =");sb.append( projectSequence) ;
        sb.append("summary ="+ summary);
        sb.append("acceptanceCriteria = "+acceptanceCriteria);
        sb.append("status = "+status);
        sb.append("priority ="+ priority);
        sb.append("this.dueDate = " +dueDate);
        sb.append(" projectIdentifier = "+projectIdentifier);
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore// always used fro bidirectional relationships
    private Backlog backlog;

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public ProjectTask(@NotBlank(message = "please include a summary") String summary) {
        this.summary = summary;
    }
}
