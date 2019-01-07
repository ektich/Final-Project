package com.ssm.service;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;

import java.util.List;

/**
 * @author 13979
 */

public interface ProjectService {

    public List<Project> findAllProjects();

    public List<Project> queryProjectsByCategory(int code);

    public List<Project> queryProjectsBySupervisor(String supervisor);

    public int addProject(Project project);

    public Project queryProject(Project project);

    public Project queryProjectById(int id);

    public Project findProjectByLanguage(String language);

    public Project findProjectBySupervisor(String supervisor);

    public List<CCS> queryCCS(int code);

    /*
    public Project deleteProjectById(int id);

    public Project delegateProjectById(int id);

    */

}
