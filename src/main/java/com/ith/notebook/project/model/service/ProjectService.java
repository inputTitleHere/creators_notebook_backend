package com.ith.notebook.project.model.service;


import com.ith.notebook.project.model.dto.Project;

import java.util.List;

public interface ProjectService {

  Project save(Project proj);

  List<Project> findByProjOwner(String userId);
}
