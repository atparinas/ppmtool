package com.atparinas.ppmtool.services;

import com.atparinas.ppmtool.domain.Backlog;
import com.atparinas.ppmtool.domain.ProjectTask;
import com.atparinas.ppmtool.repositories.BacklogRepository;
import com.atparinas.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    private BacklogRepository backlogRepository;
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository){
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        System.out.println("ProjectTaskSetvice: BacklogIdentifier: " + projectIdentifier);
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if(backlog == null){
            System.out.println("Backlog is null");
        }
        projectTask.setBacklog(backlog);

        Integer backLogSequence = backlog.getPTSequence();
        backLogSequence++;
        backlog.setPTSequence(backLogSequence);

        //Add Sequence to ProjectTask
        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backLogSequence);
        projectTask.setProjectIdentifier(backlog.getProjectIdentifier().toUpperCase());

        //Set default priority when ProjectTask Priority is not set;
        if(projectTask.getPriority() == null ){
            projectTask.setPriority(3);
        }

        //Set ProjectTask Status
        if(projectTask.getStatus() == ""|| projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);


    }

}
