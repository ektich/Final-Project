package com.ssm.controller;

import com.ssm.entity.Project;
import com.ssm.entity.User;
import com.ssm.service.ProjectService;
import com.ssm.service.UserService;
import com.ssm.util.Tools;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@Scope(value = "prototype")
public class TestController {
    @Resource
    private UserService userService;
    private ProjectService projectService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public User findUser(@RequestParam("id") int id) {
        User user = this.userService.findUserById(id);
        return user;
    }




    /*
    //还不支持
    @RequestMapping(value = "/queryProject", method = RequestMethod.POST)
    @ResponseBody
    public Project queryProject(@RequestBody Project project) {
        Project resultProject = this.projectService.queryProject(project);
        return resultProject;
    }

    @RequestMapping(value = "/toplist")
    @ResponseBody
    public List<Map> toplist() {
        List<Project> list = projectService.findAllProjects(0);
        List<Map> newList = new ArrayList<>();
        int amount = 5;
        //PriorityQueue<Project> queue = new PriorityQueue<>();
        HashMap<String, Integer> map = new HashMap<>(amount);

        for(int j = 0; j < amount; j++) {
            int max = 0;
            String title = "";
            Project p = new Project();
            for(Project project: list) {
                int number = Tools.culculateCandidates(project.getCandidates());
                if(number > max) {
                    max = number;
                    title = project.getTitle();
                    p = project;
                }
            }

            if(max != 0) {
                map.put(title, max);
                newList.add(map);
                list.remove(p);
            }
        }

        return newList;
    }
     */



    /*
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Project queryProject1(@PathVariable("id") int id) {

        System.out.println(id);
        Project result = this.projectService.queryProjectById(id);

        return result;
        /*
        Project result = this.projectService.queryProjectById(id);

        if(result != null) {
            return result;
        } else {
            return null;
        }


    }



    //jsp登陆模式
    @RequestMapping("/login")
    public String jumpToLogin() {
        return "login";
    }
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(HttpServletRequest request, HttpSession session) {
        User user = this.userService.loginCheck(request.getParameter("username"), request.getParameter("password"));
        if(user != null) {
            if(user.getIdentity().equals("student")) {
                session.setAttribute("user", user);
                return "success";
            } else if(user.getIdentity().equals("lecture")) {
                session.setAttribute("user", user);
                return "success";
            }
        }
        return "error";
    }
    */

    /*
    //Logout
    @RequestMapping(value = "/logout")
    public String loginOut(HttpSession session) {
        session.invalidate();

        return "index";
    }

    /*
    //ajax登陆模式
    @RequestMapping("/login")
    @ResponseBody
    public User loginCheck(@RequestBody User user) {

    }

    //jsp登陆模式
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(HttpServletRequest request, HttpSession session) {
        User user = this.userService.loginCheck(request.getParameter("username"), request.getParameter("password"));
        if(user != null) {
            if(user.getIdentity().equals("student")) {
                request.setAttribute("user", user);
                session.setAttribute("user", user);
                return "success";
            } else if(user.getIdentity().equals("lecture")) {
                request.setAttribute("user", user);
                session.setAttribute("user", user);
                return "success";
            }
        }
        return "error";
    }
    */


}
/*
package com.Controller;

import com.Pojo.User;
import com.Service.ShiroService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class loginController {
    @Autowired
    private ShiroService shiroService;
    private Logger logger = Logger.getLogger(loginController.class);

     * 验证登录
     * @param username
     * @param password
     * @param session
     * @return url
     */
/*
    @RequestMapping(value = "/login")
    public String Login(String username, String password, HttpSession session, Model model){
        if(username==null){
            model.addAttribute("message", "账号不为空");
            return "login";
        }


        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        User user;
        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            //利用异常操作
            //需要开始调用到Realm中
            System.out.println("========================================");
            System.out.println("1、进入认证方法");
            subject.login(token);
            user = (User)subject.getPrincipal();
            session.setAttribute("user",subject);
            model.addAttribute("message", "登录完成");
            System.out.println("登录完成");
        } catch (UnknownAccountException e) {
            model.addAttribute("message", "账号密码不正确");
            return "index";
        }


        return "test";
    }

    @RequestMapping("/check")
    public String check(HttpSession session){

        Subject subject=(Subject)session.getAttribute("user");

        User user=(User)subject.getPrincipal();
        System.out.println(user.toString());
        return "permission";
    }

    @RequestMapping("/readName")
    public String readName(HttpSession session){

        return "name";
    }

    @RequestMapping("/readData")
    public String readData(){

        return "data";
    }


    @RequestMapping("/nopermission")
    public String noPermission(){
        return "error";
    }
}

 */