package com.ith.notebook.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectImage {

  @Id
  @Column(name="proj_img_id")
  private String projImgId;


  @Column(name = "proj_id")
  private UUID projId;

}
