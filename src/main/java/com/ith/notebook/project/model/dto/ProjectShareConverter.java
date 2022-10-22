package com.ith.notebook.project.model.dto;

import javax.persistence.AttributeConverter;

public class ProjectShareConverter implements AttributeConverter<ProjectShare, String> {


  @Override
  public String convertToDatabaseColumn(ProjectShare attribute) {
    return attribute.toString();
  }

  @Override
  public ProjectShare convertToEntityAttribute(String dbData) {
    return ProjectShare.valueOf(dbData);
  }
}
