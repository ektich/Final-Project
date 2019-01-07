package com.ssm.controller;

import com.ssm.entity.Message;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.util.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @author 13979
 */

@RestController
//@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/queryUser")
    @ResponseBody
    public User queryUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = this.userService.findUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public int insertUser(@RequestBody User user) {
        System.out.println(user);
        int validation;
        //1有效 0无效
        if("".equals(user.getUsername())) {
            validation = 0;
            return validation;
        } else {
            validation = this.userService.addUser(user);
            return validation;
        }
    }
    //插入成功返回1  否则返回0

    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public int deleteUser(@RequestParam("id") int id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/findUser")
    public String findUser(HttpServletRequest request, Model model) {
        User user = this.userService.findUserById(Integer.parseInt(request.getParameter("id")));
        model.addAttribute("user", user);
        return "find";
    }

    //lecture提交项目状态检查  ajax


    //个人提交项目状态检查  ajax部分

    @RequestMapping(value = "/states/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Map> queryStates(@PathVariable("id") String id) {
        //id为传入studentId
        int id1 = Integer.parseInt(id);
        List<Map> newList = new ArrayList<>();
        User user = userService.findUserById(id1);
        if(user != null && !"".equals(user.getSelected()) && user.getSelected() != null) {
            String selected = user.getSelected();
            String mapping = user.getMapping();
            if(!"".equals(selected)) {
                List<List<Integer>> list = Tools.queryStates(selected);
                for(List<Integer> l : list) {
                    Map<String, String> map = new HashMap<>(3);
                    map.put("id", l.get(0) + "");
                    map.put("tendency", l.get(1) + "");
                    if(mapping != null && !"".equals(mapping)) {
                        if(Integer.parseInt(mapping) == l.get(0)) {
                            map.put("state", "Accepted");
                        } else {
                            map.put("state", "Rejected");
                        }
                    } else {
                        map.put("state", "Pending");
                    }
                    newList.add(map);
                }
                return newList;
            }
        }
        return null;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        Map<String, String> map = new HashMap<>();
        if(username == null || "".equals(username)){
            map.put("response", "username can not be empty");
            return map;
        }
        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        User user;
        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            subject.login(token);
            user = (User)subject.getPrincipal();
            System.out.println("SessionId: " + subject.getSession().getId() + "/ Username: " + user.getUsername());
            System.out.println(new Date());
            map.put("username", user.getUsername());
            map.put("identity", user.getIdentity());
        } catch (UnknownAccountException e) {
            map.put("response", "username does not existed");
            return map;
        } catch (IncorrectCredentialsException e) {
            map.put("response", "incorrect password");
            return map;
        }
        return map;
    }

    @RequestMapping(value = "/logout.do")
    public Message logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
            return Message.success("Success");
        }
        return Message.error();
    }

    @RequestMapping(value = "/check")
    public String check(HttpSession session){
        Subject subject = (Subject)session.getAttribute("user");
        User user = (User)subject.getPrincipal();
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


    @RequestMapping("/testTimeOut")
    @ResponseBody
    public Map<String, String> testTimeOut(HttpServletRequest request) {
        String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
        System.out.println("url: " + url);
        //System.out.println(obj);
        //是否验证
        Boolean isAuthenticated = (Boolean)request.getSession().getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
        Boolean vaild = request.isRequestedSessionIdValid();
        //定时自动清除session中的principal

        //get user
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        request.getSession().getCreationTime();
        System.out.println("isAuthenticated: " + isAuthenticated);

        Map<String, String> map = new HashMap<>();
        if(user != null) {
            map.put("username", user.getUsername());
        } else {
            map.put("message", "fail");
        }
        return map;
    }
}
