package com.ssm.service.impl;

import com.ssm.dao.ProjectDao;
import com.ssm.entity.CCS;
import com.ssm.entity.Project;
import com.ssm.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 13979
 */

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {


    @Resource
    private ProjectDao projectDao;

    @Override
    public List<Project> findAllProjects(int code) {
        List<Project> res;
        if(code == 0) {
            res = projectDao.findAllProjects();
        } else {
            res = projectDao.queryProjectsByCategory(code);
        }
        Map<Integer, Project> level2 = projectDao.queryLevel2();
        Map<Integer, Project> level3 = projectDao.queryLevel3();
        for(Project project : res) {
            if(level2.containsKey(project.getId())) {
                project.setLevel2(level2.get(project.getId()).getLevel2());
            }
            if(level3.containsKey(project.getId())) {
                project.setLevel3(level3.get(project.getId()).getLevel3());
            }
        }
        return res;
    }

    @Override
    public List<Project> queryProjectsBySupervisor(String supervisor) { return projectDao.queryProjectsBySupervisor(supervisor); }

    @Override
    public int addProject(Project project) {
        List<CCS> names = projectDao.queryCCSByCode2(Integer.parseInt(project.getLevel1()), Integer.parseInt(project.getLevel2()));
        project.setLevel1(names.get(0).getName());
        project.setLevel2(names.get(1).getName());
        return projectDao.addProject(project);
    }

    @Override
    public Project queryProject(int id) { return projectDao.queryProject(id); }

    @Override
    public List<CCS> queryCCSByCode(int id1) { return projectDao.queryCCSByCode1(id1); }

    @Override
    public List<CCS> queryCCSByCode(int id1, int id2) { return projectDao.queryCCSByCode2(id1, id2); }

    @Override
    public List<CCS> queryCCSByName(String level1, String level2) { return projectDao.queryCCSByName(level1, level2); }

    @Override
    public int deleteProjectById(int code) { return projectDao.deleteProjectById(code); }

    @Override
    public int concatInterest(int id, String content) { return projectDao.concatInterest(id, content); }

    @Override
    public List<Project> topProjects() { return projectDao.topProjects(); }

}
