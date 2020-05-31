package com.example.ppmfullstack.exceptions;

public class ProjectIdExceptionResponse {
    /* just for storing the message of the project id exceptions*/
    private String ProjectIdentifier;

    public ProjectIdExceptionResponse(String projectIdentifier) {
        ProjectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return ProjectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        ProjectIdentifier = projectIdentifier;
    }
}
