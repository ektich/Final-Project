package com.ssm.entity;

/**
 * @author 13979
 */
public class Student {
    private int stuid;
    private int phoneNum;
    private String stuemail;
    private String department;


    public int getStuid() { return stuid; }
    public void setStuid(int stuid) { this.stuid = stuid;}

    public int getPhoneNum() { return phoneNum; }
    public void setPhoneNum(int phoneNum) { this.phoneNum = phoneNum; }

    public String getStuemail() { return stuemail; }
    public void setStuemail(String stuemail) { this.stuemail = stuemail; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Project [stuid = "+ stuid +", phoneNum = " + phoneNum + ", stuemail = " + stuemail + ", department = " + department + "]";
    }
}


