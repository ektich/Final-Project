package com.ssm.controller;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;
import com.ssm.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import com.ssm.util.Tools;
import java.util.*;

/**
 * @author 13979
 */

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private UserService userService;

    //所有项目分页显示 带分类

    /**
     * query all projects
     * @param pageNum page number
     * @param code category code
     * @return projects
     */
    @RequestMapping(value = "/all/{id}")
    @ResponseBody
    public PageInfo<Project> projectPageInfo(@PathVariable(value = "id") String pageNum, @RequestParam(defaultValue = "") String code) {
        int page = pageNum == null ? 1 : Integer.parseInt(pageNum);
        int c = "".equals(code) ? 0 : Integer.parseInt(code);
        try {
            List<Project> listProject;
            PageHelper.startPage(page, 5);
            listProject = projectService.findAllProjects(c);
            return new PageInfo<>(listProject);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    //top list
    /**
     * query all projects and sort by the number of interested students
     * @return result of top list
     */
    @RequestMapping("/topList")
    @ResponseBody
    public List<Map> topList() {
        List<Map> l = new ArrayList<>();
        List<Project> list = projectService.topProjects();
        int amount = 5;
        for(int i = 0; i < amount; i++) {
            Map<String, String> map = new HashMap<>(3);
            int max = 0;
            Project p = new Project();
            for(Project project: list) {
                int number = 0;
                if(project.getCandidates() != null && !"".equals(project.getCandidates())) {
                    number = Tools.calculateCandidates(project.getCandidates());
                }
                if(number > max) {
                    max = number;
                    p = project;
                }
            }
            if(max != 0) {
                map.put("id", p.getId() + "");
                map.put("title", p.getTitle());
                map.put("amount", max + "");
                l.add(map);
                list.remove(p);
            }
        }
        return l.size() > 0 ? l : null;
    }


    /**
     * lecture query result of his posted project
     * @param username lecture username
     * @return List<List<Map>>
     */
    @RequestMapping("/dispose")
    @ResponseBody
    public List<List<Map>> disposeProject(@RequestParam("username") String username) {
        List<List<Map>> list = new ArrayList<>();
        //传lecture名字作为参数
        //String supervisor = "Lecture1";
        List<Project> projects = projectService.queryProjectsBySupervisor(username);
        for(Project project : projects) {
            List<Map> nl = new ArrayList<>();
            if(!project.getDraft()) {
                if(!"".equals(project.getCandidates()) && project.getCandidates() != null) {
                    List<List<Integer>> states = Tools.queryStates(project.getCandidates());
                    for(List<Integer> l : states) {
                        Map<String, String> map = new HashMap<>(5);
                        map.put("pid", project.getId() + "");
                        map.put("pt", project.getTitle());
                        map.put("sid", l.get(0) + "");
                        map.put("tendency", l.get(1) + "");
                        map.put("draft", "false");
                        if(project.getDesignated() != 0) {
                            map.put("designated", project.getDesignated() + "");
                        } else {
                            map.put("designated", "");
                        }
                        nl.add(map);
                    }
                } else {
                    Map<String, String> map = new HashMap<>(5);
                    map.put("pid", project.getId() + "");
                    map.put("pt", project.getTitle());
                    map.put("draft", "false");
                    nl.add(map);
                }
                list.add(nl);
            } else {
                Map<String, String> map = new HashMap<>(3);
                map.put("pid", project.getId() + "");
                map.put("pt", project.getTitle());
                map.put("draft", "true");
                nl.add(map);
                list.add(nl);
            }

        }
        return list;
    }

    /**
     * query ccs
     * @param code id and code
     * @return ccs categories
     */
    @RequestMapping("/level")
    @ResponseBody
    public List<CCS> queryLevel(@RequestParam("code") String code) {
        int c = 0;
        if(code != null && !"".equals(code)) {
            c = Integer.parseInt(code);
        }
        return projectService.queryCCSByCode(c);
    }

    /**
     * concat student's id and his tendency
     * @param pid project id
     * @param sid student id
     * @param tendency tendency
     * @return 1 success 0 fail
     */
    @RequestMapping("/interested")
    @ResponseBody
    public int interestedProject(@RequestParam("pid") int pid, @RequestParam("sid") String sid, @RequestParam("tendency") int tendency) {
        String s1 = '[' + sid + '-' + tendency + ']';
        String s2 = '[' + (pid+"") + '-' + tendency + ']';
        int i = userService.updateStates(sid, s2);
        int j = projectService.updateSingleProject(pid, s1);
        if(i == j && i == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * designate project to a certain student
     * @param pid project id
     * @param sid student id
     * @return  1 success 0 fail
     */
    @RequestMapping("/designate")
    @ResponseBody
    public int designateStudent(@RequestParam int pid, @RequestParam int sid, @RequestParam String isCancel) {
        Boolean cancel = "true".equals(isCancel);
        int i = userService.mapProject(pid+"", sid+"", cancel);
        int j = projectService.updateSingleProject(pid, sid, cancel);
        if(i == j && i == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * insert project into database
     * @param project project object
     * @return 1 success 0 fail
     */
    @RequestMapping("/insert")
    @ResponseBody
    public int insertProject(@RequestBody Project project) {
        if(project.getId() == 0) {
            return projectService.addProject(project);
        } else {
            return projectService.updateSingleProject(project);
        }
    }


    //先查再改   数据回显功能
    /**
     * query single project
     * @param id project id
     * @return project object
     */
    @RequestMapping("/single")
    @ResponseBody
    public Map<String, Project> querySingleProjectById(@RequestParam("id") int id, @RequestParam("name") String name) {
        Map<String, Project> res = new HashMap<>(1);
        Project project;
        String s = "true";
        if(s.equals(name)) {
            project = projectService.queryProject(id, true);
        } else {
            project = projectService.queryProject(id, false);
        }
        res.put("project", project);
        return res;
    }

    /**
     *
     * @param code project id
     * @return 1 success 0 fail
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteProject(@RequestParam int code) {
        return code == 0 ? 0 : projectService.deleteProjectById(code);
    }

}