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
    public List<Project> queryProjectsBySupervisor(String supervisor) {
        return projectDao.queryProjectsBySupervisor(supervisor);
    }

    @Override
    public int addProject(Project project) {
        Project pro = transferCodeToName(project);
        return projectDao.addProject(pro);
    }

    @Override
    public Project queryProject(int id, boolean name) {
        Project project;
        if(name) {
            project = projectDao.queryProjectById(id, true);
            List<CCS> res = projectDao.queryCCSByCode3("".equals(project.getLevel2()) ? 0 : Integer.parseInt(project.getLevel2()),
                    "".equals(project.getLevel3()) ? 0 : Integer.parseInt(project.getLevel3()));
            if(res.size() >= 1) {
                project.setLevel2(res.get(0).getName());
            }
            if(res.size() >= 2) {
                project.setLevel3(res.get(1).getName());
            }
        } else {
            project = projectDao.queryProjectById(id, false);
        }
        return project;
    }

    @Override
    public List<CCS> queryCCSByCode(int id1) { return projectDao.queryCCSByCode1(id1); }

    @Override
    public List<CCS> queryCCSByCode(int id1, int id2) { return projectDao.queryCCSByCode2(id1, id2); }

    @Override
    public List<CCS> queryCCSByName(String level1, String level2) {
        return projectDao.queryCCSByName(level1, level2); }

    @Override
    public List<CCS> queryCCSByName(String level1, String level2, String level3) {
        return projectDao.queryCCSByName(level1, level2, level3); }

    @Override
    public int deleteProjectById(int code) { return projectDao.deleteProjectById(code); }

    @Override
    public int updateSingleProject(Project project) {
        return projectDao.updateSingleProject(transferCodeToName(project));
    }

    @Override
    public int updateSingleProject(int pid, String content) { return projectDao.updateSingleProject1(pid, content); }

    @Override
    public synchronized int updateSingleProject(int pid, int sid, Boolean cancel) {
        System.out.println(projectDao);
        if(cancel) {
            String before = projectDao.queryCandidates(pid);
            String after = deleteStudentUtil(before, sid);
            return projectDao.cancelCandidate(pid, after);
        } else {
            return projectDao.concatCandidate(pid, sid+"");
        }
    }

    @Override
    public List<Project> topProjects() { return projectDao.topProjects(); }

    @Override
    public int deleteStudent(int pid, int sid) {
        String content = projectDao.queryCandidates(pid);
        return projectDao.cancelCandidate(pid, deleteStudentUtil(content, sid));
    }

    /**
     * ccs code transfer to name
     * @param project project with ccs code
     * @return project with ccs name
     */
    private Project transferCodeToName(Project project) {
        List<CCS> names = projectDao.queryCCSByName(project.getLevel1(), project.getLevel2()
                , project.getLevel3());
        if(names.size() != 0) {
            project.setLevel1(names.get(0).getId() + "");
        }
        if(names.size() > 1) {
            project.setLevel2(names.get(1).getId() + "");
        }
        if(names.size() > 2) {
            project.setLevel3(names.get(2).getId() + "");
        }
        return project;
    }

    //删除指定学生
    /**
     * binary search
     * delete required student id
     * if deleted return new string else return null
     * @param s string
     * @param id student id
     * @return string or null
     */
    private String deleteStudentUtil(String s, int id) {
        if ("".equals(s)) {
            return "";
        }
        String[] array = s.split(",");
        if(array.length == 1) {
            if(array[0].equals(id+"")) {
                return "";
            } else {
                return s;
            }
        }
        List<String> list = new ArrayList<String>(Arrays.asList(array));
        int size = list.size();
        list.sort((s1, s2) -> s1.compareTo(s2));
        int start = 0, end = list.size()-1;
        int mid = 0;
        while(start+1 < end) {
            mid = start + (start + end) / 2;
            int i = Integer.parseInt(list.get(mid));
            if(i == id) {
                list.remove(mid);
                break;
            } else if(i < id) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(list.size() > 1) {
            if(Integer.parseInt(list.get(list.size()-1)) == id) {
                list.remove(list.size()-1);
            }
        }
        if(Integer.parseInt(list.get(0)) == id) {
            list.remove(0);
        }
        if(list.size() == size) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(String s2 : list) {
            sb.append(s2+",");
        }
        return sb.toString();
    }

}