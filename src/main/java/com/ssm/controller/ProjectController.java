package com.ssm.controller;

import com.ssm.entity.CCS;
import com.ssm.entity.Project;
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

    //所有项目分页显示 带分类

    @RequestMapping(value = "/all/{id}")
    @ResponseBody
    public PageInfo<Project> projectPageInfo(@PathVariable(value = "id") String pageNum, @RequestParam(defaultValue = "") String code) {
        int page = pageNum == null ? 1 : Integer.parseInt(pageNum);
        int c = !"".equals(code) ? 0 : Integer.parseInt(code);
        /*
        if(pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        if(!"".equals(code) && code != null) {
            c = Integer.parseInt(code);
        }
         */
        try {
            List<Project> listProject;
            PageHelper.startPage(page, 10);
            listProject = projectService.findAllProjects(c);
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
        if(l.size() > 0) {
            return l;
        }
        return null;
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
                map.put("pc", project.getLevel2());
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
        return projectService.queryCCSByCode(c);
    }

    @RequestMapping("/interested")
    @ResponseBody
    public int interestedProject(@RequestParam("id") int id, @RequestParam("tendency") int tendency) {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(id).append('-').append(tendency).append(']');
        System.out.println(sb.toString());

        return projectService.concatInterest(id, sb.toString());
    }

    /*
        成功返回1 否则0
    */

    @RequestMapping("/insert")
    @ResponseBody
    public int insertProject(@RequestBody Project project) {
        if(project != null) {
            List<CCS> res = projectService.queryCCSByName(project.getLevel1(), project.getLevel2());
            System.out.println(project);
            project.setLevel1(res.get(0).getId()+"");
            project.setLevel2(res.get(0).getId()+"");
            return projectService.addProject(project);
        }
        return 0;
    }



    //specified project

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Project queryProject(@PathVariable("id") String id) {
        Project result = projectService.queryProject(Integer.parseInt(id));
        result.setCandidates("");
        result.setDesignated("");
        return result;
    }

    //先查再改   数据回显功能

    @RequestMapping("/modify")
    @ResponseBody
    public int modifyProject(@RequestParam String code) {
        int c = Integer.parseInt(code);
        Project project = projectService.queryProject(c);
        return 0;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int deleteProject(@RequestParam String code) {
        int c = Integer.parseInt(code);
        return projectService.deleteProjectById(c);
    }



    @RequestMapping("/designate")
    @ResponseBody
    public int designate(@RequestParam String pid, @RequestParam String sid) {
        return 0;
    }
}