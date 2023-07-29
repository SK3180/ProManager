package com.sksingh.promanager.model;

public class TaskModel {
    String taskid,taskname,taskstatus,taskdisp,taskdeadline,userid;

    public TaskModel(){

    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }
    public String getTaskdisp() {
        return taskdisp;
    }

    public void setTaskdisp(String taskdisp) {
        this.taskdisp = taskdisp;
    }
    public String getTaskdeadline() {
        return taskdeadline;
    }

    public void setTaskdeadline(String taskdeadline) {
        this.taskdeadline = taskdeadline;
    }

    public String getUserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }


    public TaskModel(String taskid, String taskname, String taskstatus, String taskdisp, String taskdeadline, String userid) {
        this.taskid = taskid;
        this.taskname = taskname;
        this.taskstatus = taskstatus;
        this.taskdisp = taskdisp;
        this.taskdeadline = taskdeadline;
        this.userid = userid;
    }
}
