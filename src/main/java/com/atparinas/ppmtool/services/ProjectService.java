package com.atparinas.ppmtool.services;

import com.atparinas.ppmtool.domain.Backlog;
import com.atparinas.ppmtool.domain.Project;
import com.atparinas.ppmtool.exceptions.ProjectIdException;
import com.atparinas.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            return projectRepository.save(project);

        }catch (Exception e){

            throw new ProjectIdException("Project Identifier must be unique");
        }

    }

    public Project findProjectByIndentifier(String projectId){

        Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project Id does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){

        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String identifier){

        Project project = projectRepository.findByProjectIdentifier(identifier.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Cannot delete project, Project does not exist");
        }

        projectRepository.delete(project);
    }


}
