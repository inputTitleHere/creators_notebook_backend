package com.ith.notebook.project.controller;

import com.ith.notebook.common.Utils;
import com.ith.notebook.project.model.dto.Project;
import com.ith.notebook.project.model.dto.ProjectShare;
import com.ith.notebook.project.model.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  private ProjectService projectService;
  private Utils utils = new Utils();

  @PostMapping("/createProject")
  public ResponseEntity<?> createProject(@RequestPart(required = false) MultipartFile proj_img, @RequestParam HashMap<String, Object> data, @AuthenticationPrincipal String userId) {

    log.debug("Data = {}", data);
    log.debug("UserId = {}",userId);

    Project proj = Project.builder()
            .projName((String)data.get("proj_title"))
            .projOwner(userId)
            .projData(data)
            .projShare(ProjectShare.N)
            .build();
    Project saved = projectService.save(proj);
    log.debug("proj_uuid = {}",saved.getProjId());


    if (!proj_img.isEmpty()) {
      log.debug("File name = {}", proj_img.getOriginalFilename());
      String newImgName = utils.generateImageName(proj_img.getOriginalFilename());
      log.debug("NewFileName = {}",newImgName);
    } else {
      log.debug("Multipart image file is Empty");
    }
//    log.debug("Create Project - param : {}",param);


    return ResponseEntity.ok("ok");
  }

  @GetMapping("/myProjects")
  public ResponseEntity<?> getMyProjects(@AuthenticationPrincipal String userId){
    List<Project> myProjects = projectService.findByProjOwner(userId);

    return ResponseEntity.ok(myProjects);
  }

}
