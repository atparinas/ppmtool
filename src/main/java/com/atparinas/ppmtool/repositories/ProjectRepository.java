package com.atparinas.ppmtool.repositories;

import com.atparinas.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

//    Project findProjectByprojectIdentifier(String projectId);

    Project findByProjectIdentifier(String projectId);
}
