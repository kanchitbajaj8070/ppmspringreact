package com.example.ppmfullstack.repository;

import com.example.ppmfullstack.domain.Backlog;
import com.example.ppmfullstack.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    public Backlog findByProjectIdentifier( String id);
}
