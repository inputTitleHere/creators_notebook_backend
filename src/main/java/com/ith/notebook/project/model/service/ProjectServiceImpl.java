package com.ith.notebook.project.model.service;

import com.ith.notebook.project.model.dto.Project;
import com.ith.notebook.project.model.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjectServiceImpl implements ProjectService{
  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public Project save(Project proj) {
    return projectRepository.save(proj);
  }

  @Override
  public List<Project> findByProjOwner(String userId) {
    return projectRepository.findByProjOwner(userId);
  }
}
