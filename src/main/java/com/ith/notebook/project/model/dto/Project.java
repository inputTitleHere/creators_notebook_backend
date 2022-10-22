package com.ith.notebook.project.model.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name="jsonb", typeClass = JsonBinaryType.class)
public class Project {

  @Id
  @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name="proj_id", columnDefinition = "UUID")
  private UUID projId;

  private String projName;
  private String projOwner; // Member(id)

  @Type(type="jsonb")
  @Column(name="proj_data", columnDefinition = "jsonb")
//  @Convert(converter = JsonConverter.class)
  private HashMap<String,Object> projData = new HashMap<>();

  @Convert(converter = ProjectShareConverter.class)
  private ProjectShare projShare;

  @Transient // prevents mapping
  @OneToOne
  @JoinColumn(name="project_img_id")
  private ProjectImage projImage;
}
