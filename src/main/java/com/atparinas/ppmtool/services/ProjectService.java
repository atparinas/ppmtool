package com.atparinas.ppmtool.services;

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
            return projectRepository.save(project);

        }catch (Exception e){

            throw new ProjectIdException("Project Identifier must be unique");
        }



    }

}
