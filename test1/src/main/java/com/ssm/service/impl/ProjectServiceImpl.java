package com.ssm.service.impl;

import com.ssm.dao.ProjectDao;
import com.ssm.entity.CCS;
import com.ssm.entity.Project;
import com.ssm.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 13979
 */

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {


    @Resource
    private ProjectDao projectDao;

    @Override
    public List<Project> findAllProjects() { return projectDao.findAllProjects(); }

    @Override
    public List<Project> queryProjectsByCategory(int code) { return projectDao.queryProjectsByCategory(code); }

    @Override
    public List<Project> queryProjectsBySupervisor(String supervisor) { return projectDao.queryProjectsBySupervisor(supervisor); }

    @Override
    public int addProject(Project project) { return projectDao.addProject(project); }

    @Override
    public Project queryProjectById(int id) { return projectDao.queryProjectById(id); }

    @Override
    public Project queryProject(Project project) { return projectDao.queryProject(project); }

    @Override
    public Project findProjectByLanguage(String language) { return projectDao.findProjectByLanguage(language); }

    @Override
    public Project findProjectBySupervisor(String supervisor) { return projectDao.findProjectBySupervisor(supervisor); }

    @Override
    public List<CCS> queryCCS(int code) { return projectDao.queryCCS(code); }

}
