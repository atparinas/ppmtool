package com.atparinas.ppmtool.web;

import com.atparinas.ppmtool.domain.ProjectTask;
import com.atparinas.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            @PathVariable String backlog_id, BindingResult bindingResult){


        System.out.println(backlog_id);
        ProjectTask createdProjectTask = projectTaskService.addProjectTask(backlog_id, projectTask);

        return new ResponseEntity<>(createdProjectTask, HttpStatus.CREATED);

    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable String backlog_id){

        List<ProjectTask> projectTasks = projectTaskService.findBacklogById(backlog_id);

        return new ResponseEntity<>(projectTasks, HttpStatus.OK);

    }



}
