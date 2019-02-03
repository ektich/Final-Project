package com.ssm.service;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;

import java.util.*;

/**
 * @author 13979
 */

public interface ProjectService {

    public List<Project> findAllProjects(int code);

    public List<Project> topProjects();

    public List<Project> queryProjectsBySupervisor(String supervisor);

    public Project queryProject(int id);

    public List<CCS> queryCCSByCode(int id1);

    public List<CCS> queryCCSByCode(int id1, int id2);

    public List<CCS> queryCCSByName(String level1, String level2);

    public int addProject(Project project);

    public int deleteProjectById(int id);

    public int concatInterest(int id, String content);

    /*
    public Project delegateProjectById(int pid, int sid);

    */

}
