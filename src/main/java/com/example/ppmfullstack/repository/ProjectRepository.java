package com.example.ppmfullstack.repository;

import com.example.ppmfullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    public Project findByProjectIdentifier(String projectIdentifier);
    Iterable<Project> findAllByProjectLeader(String username);
}
