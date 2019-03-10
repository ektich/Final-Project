package com.ssm.service;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;

import java.util.*;

/**
 * @author 13979
 */

public interface ProjectService {

    /**
     * find all projects
     * @param code code is refer to category
     * @return Result<List<Project>>
     */
    List<Project> findAllProjects(int code);

    /**
     * query projects for top list
     * @return Result<List<Project>>
     */
    List<Project> topProjects();

    /**
     * query all projects by supervisor name
     * @param supervisor supervisor's name
     * @return Result<List<Project>>
     */
    List<Project> queryProjectsBySupervisor(String supervisor);

    /**
     * query project by project id
     * @param id project's id
     * @return Result<Project>
     */
    Project queryProject(int id, boolean name);

    /**
     * query all projects belongs to level1
     * @param id1 all sub-ccs belongs to this category
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByCode(int id1);

    /**
     * query ccs by two codes
     * id1 is refer to the code of the record
     * id2 is refer to the id of the record
     * @param id1 id1 -> id
     * @param id2 id2 -> code
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByCode(int id1, int id2);

    /**
     * query ccs by two names, to query ccs belongs to level1 and level2
     * @param level1 level1's name
     * @param level2 level2's name
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByName(String level1, String level2);

    /**
     * query ccs by two names, to query ccs belongs to level1 and level2
     * @param level1 level1's name
     * @param level2 level2's name
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByName(String level1, String level2, String level3);

    /**
     * query project by project id
     * @param project project
     * @return Result<Project>
     */
    int addProject(Project project);

    /**
     * delete project by project id
     * @param id project's id
     * @return 1 success 0 fail
     */
    int deleteProjectById(int id);

    /**
     * edit project
     * @param project project object
     * @return 1 success 0 fail
     */
    int updateSingleProject(Project project);

    /**
     * concat student's student id and his tendency to certain project
     * @param pid project's id
     * @param candidates student's student id and tendency
     * @return Result<int>
     */
    int updateSingleProject(int pid, String candidates);

    /**
     * delegate project to student by student id and project id
     * @param pid project's id
     * @param sid student's id
     * @param cancel cancel candidate
     * @return 1 success 0 fail
     */
    int updateSingleProject(int pid, int sid, Boolean cancel);

    /**
     * delete student on this project
     * @param pid project id
     * @param sid student id
     * @return 1 success 0 fail
     */
    int deleteStudent(int pid, int sid);
}
