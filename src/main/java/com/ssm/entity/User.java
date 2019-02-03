package com.ssm.entity;

import com.sun.javafx.beans.IDProperty;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Generated;

/**
 * @author 13979
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String username;
    private String password;
    private String identity;
    private String selected;
    private String mapping;
    private String posted;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getIdentity() { return identity; }
    public void setIdentity(String identity) { this.identity = identity; }

    public String getSelected() { return selected; }
    public void setSelected(String selected) { this.selected = selected; }

    public String getMapping() { return mapping; }
    public void setMapping(String mapping) { this.mapping = mapping; }

    public String getPosted() { return posted; }
    public void setPosted(String posted) { this.posted = posted; }


    @Override
    public String toString() {
        return "User [id = " + id + ", name = " + name +", age = " + age + ", " +
                "username = " + username + ", " + "password = " + password + ", " +
                "identity = " + identity + ", " + "selected = " + selected + ", " +
                "mapping = " + mapping + ", " + "posted = " + posted + "]";
    }


}
