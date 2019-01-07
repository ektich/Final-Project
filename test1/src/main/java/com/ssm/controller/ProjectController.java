package com.ssm.controller;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;
import com.ssm.entity.User;
import com.ssm.service.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    //所有项目分页显示

    @RequestMapping(value = "/all/{id}")
    @ResponseBody
    public PageInfo<Project> projectPageInfo(@PathVariable(value = "id") String pageNum) {
        int page = 1;
        if(pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        try {
            PageHelper.startPage(page, 2);
            List<Project> listProject = projectService.findAllProjects();
            for(Project project : listProject) {
                project.setCandidates("");
                project.setDesignated("");
            }
            return new PageInfo<>(listProject);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    //top list

    @RequestMapping("/topList")
    @ResponseBody
    public List<Map> topList() {
        List<Map> l = new ArrayList<>();
        List<Project> list = projectService.findAllProjects();
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
        if(l.size() > 0) {
            return l;
        }
        return null;
    }

    //所有项目分类查找

    @RequestMapping(value = "/category")
    @ResponseBody
    public PageInfo<Project> queryProjectByRequest(@RequestParam int code) {
        System.out.println(code);
        try {
            PageHelper.startPage(1, 10);
            List<Project> listProject = projectService.queryProjectsByCategory(code);
            return new PageInfo<>(listProject);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    //specified project

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Project queryProject(@PathVariable("id") String id) {
        Project result = projectService.queryProjectById(Integer.parseInt(id));
        result.setCandidates("");
        result.setDesignated("");
        return result;
    }


    @RequestMapping("/insert")
    @ResponseBody
    public int insertProject(@RequestBody Project project) {
        System.out.println(project);
        return projectService.addProject(project);
    }


    @RequestMapping("/dispose")
    @ResponseBody
    public List<List<Map>> disposeProject(@RequestParam("id") String id, HttpServletRequest request) {
        List<List<Map>> list = new ArrayList<>();
        //传lecture名字作为参数
        String supervisor = "Lecture1";
        List<Project> projects = projectService.queryProjectsBySupervisor(supervisor);
        for(Project project : projects) {
            List<Map> nl = new ArrayList<>();
            List<List<Integer>> states = Tools.queryStates(project.getCandidates());
            for(List<Integer> l : states) {
                Map<String, String> map = new HashMap<>(3);
                map.put("pid", project.getId() + "");
                map.put("pt", project.getTitle());
                map.put("id", l.get(0) + "");
                map.put("tendency", l.get(1) + "");
                if(!"".equals(project.getDesignated()) && project.getDesignated() != null) {
                    map.put("designed", project.getDesignated());
                } else {
                    map.put("designed", "");
                }
                nl.add(map);
            }
            list.add(nl);
        }
        return list;
    }

    @RequestMapping("/level")
    @ResponseBody
    public List<CCS> queryLevel1(@RequestParam("code") String code) {
        int c = 0;
        if(code != null && !"".equals(code)) {
            c = Integer.parseInt(code);
        }
        return projectService.queryCCS(c);
    }
}