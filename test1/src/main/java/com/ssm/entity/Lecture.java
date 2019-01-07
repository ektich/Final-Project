package com.ssm.entity;

/**
 * @author 13979
 */
public class Lecture {

    private int lecid;
    private int phoneNum;
    private String lecemail;
    private String department;


    public int getLecid() { return lecid; }
    public void setLecid(int lecid) { this.lecid = lecid;}

    public int getPhoneNum() { return phoneNum; }
    public void setPhoneNum(int phoneNum) { this.phoneNum = phoneNum; }

    public String getLecemail() { return lecemail; }
    public void setLecemail(String Lecemail) { this.lecemail = lecemail; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "lecture [lecid = " + lecid + ", phoneNum = " + phoneNum + ", " +
                "lecemail = " + lecemail + ", department = " + department + "]";
    }

}
