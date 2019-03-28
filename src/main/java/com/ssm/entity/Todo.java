package com.ssm.entity;

public class Todo {
    private int pid;
    private int sid;
    private boolean isCancel;

    public int getPid() { return pid; }
    public void setPid(int pid) { this.pid = pid; }

    public int getSid() { return sid; }
    public void setSid(int sid) { this.sid = sid; }

    public boolean getIsCancel() { return isCancel; }
    public void setIsCancel(boolean isCancel) { this.isCancel = isCancel; }
}
