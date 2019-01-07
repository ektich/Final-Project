package com.ssm.dao;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;

import java.util.List;

/**
 * @author 13979
 */

public interface ProjectDao {

    public int addProject(Project project);

    public List<Project> findAllProjects();

    public List<Project> queryProjectsByCategory(int code);

    public List<Project> queryProjectsBySupervisor(String supervisor);

    public Project queryProject(Project project);

    public Project queryProjectById(Integer id);

    public Project findProjectByLanguage(String language);

    public Project findProjectBySupervisor(String supervisor);

    public List<CCS> queryCCS(int code);

}
