package com.sksingh.promanager;

public class Users {
    String userId,name,profile;

    public Users(String userId, String name, String profile) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
    }

    public  Users(){
    }

    public String getUserId(String uid) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName(String displayName) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}

