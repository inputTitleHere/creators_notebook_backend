package com.ith.notebook.project.model.repository;

import com.ith.notebook.project.model.dto.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
  List<Project> findByProjOwner(String userId);
}
