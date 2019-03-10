package com.ssm.dao;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * @author 13979
 */

public interface ProjectDao {

    /**
     * query all projects without category
     * @return Result<List<Project>>
     */
    List<Project> findAllProjects();

    /**
     * query all projects with category
     * @param code ccs code
     * @return Result<List<Project>>
     */
    List<Project> queryProjectsByCategory(int code);

    /**
     * query level2's name of project and bind its' project id
     * @return Result<Map<Integer, Project>>
     */
    @MapKey("id")
    Map<Integer, Project> queryLevel2();

    /**
     * query level3's name of project and bind its' project id
     * @return Result<Map<Integer, Project>>
     */
    @MapKey("id")
    Map<Integer, Project> queryLevel3();

    /**
     * query projects by supervisor's name
     * @param supervisor supervisor's name
     * @return Result<List<Project>>
     */
    List<Project> queryProjectsBySupervisor(String supervisor);

    /**
     * query project by its' id
     * @param id project id
     * @param name project required level name
     * @return Result<Project>
     */
    Project queryProjectById(@Param(value = "id") int id, @Param(value = "name") Boolean name);

    /**
     * query ccs category level1 with its' id
     * @param id1 ccs id
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByCode1(@Param(value = "id1") int id1);

    /**
     * query ccs category level2 with iss' id and code
     * @param id1 id1 is refer to level1
     * @param id2 id2 is refer to level2
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByCode2(@Param(value = "id1") int id1, @Param(value = "id2") int id2);

    /**
     * query ccs by codes for read single project from ccs code transfer to ccs name
     * @param id2 id2 level2
     * @param id3 id3 level3
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByCode3(@Param(value = "id2") int id2, @Param(value = "id3") int id3);

    /**
     * query ccs by its' name
     * @param level1 level1
     * @param level2 level2
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByName(@Param("level1") String level1, @Param("level3") String level2);

    /**
     * query ccs by its' name
     * @param level1 level1
     * @param level2 level2
     * @param level3 level3
     * @return Result<List<CCS>>
     */
    List<CCS> queryCCSByName(@Param("level1") String level1, @Param("level2") String level2, @Param("level3") String level3);

    /**
     * query all projects for top list
     * @return Result<List<Project>>
     */
    List<Project> topProjects();

    /**
     * insert project into database
     * @param project project object
     * @return Result<int>
     */
    int addProject(Project project);

    /**
     * delete project by project id
     * @param code project id
     * @return Result<int>
     */
    int deleteProjectById(int code);

    /**
     * edit project
     * @param project project object
     * @return Result<int>
     */
    int updateSingleProject(Project project);

    /**
     * concat student id and tendency to certain project
     * @param pid project id
     * @param content string containing student's id and tendency
     * @return Result<int>
     */
    int updateSingleProject1(@Param(value = "pid") int pid, @Param(value = "content") String content);

    /**
     * delegate project to certain student
     * @param pid project id
     * @param sid student id
     * @return Result<int>
     */
    int updateSingleProject2(@Param(value = "pid") int pid, @Param(value = "sid") int sid);

    /**
     * delete specified student
     * @param pid project id
     * @param candidates candidates string
     * @return 1 success 0 fail
     */
    int updateSingleProject3(@Param(value = "pid") int pid, @Param(value = "candidates") String candidates);

    /**
     * query candidates
     * @return candidates string
     */
    String queryCandidates(int pid);
}
