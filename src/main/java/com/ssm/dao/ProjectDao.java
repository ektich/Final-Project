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

    public int addProject(Project project);

    public List<Project> findAllProjects();

    public List<Project> queryProjectsByCategory(int code);

    @MapKey("id")
    public Map<Integer, Project> queryLevel2();

    @MapKey("id")
    public Map<Integer, Project> queryLevel3();

    public List<Project> queryProjectsBySupervisor(String supervisor);

    public Project queryProject(Integer id);

    public List<CCS> queryCCSByCode1(@Param(value = "id1") int id1);

    public List<CCS> queryCCSByCode2(@Param(value = "id1") int id1, @Param(value = "id2") int id2);

    public List<CCS> queryCCSByName(String level1, String level2);

    public List<Project> topProjects();

    public int deleteProjectById(int code);

    public int concatInterest(int id, String content);

}
