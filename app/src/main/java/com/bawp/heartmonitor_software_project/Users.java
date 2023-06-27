package com.bawp.heartmonitor_software_project;

public class Users {
    String username,email,age;

    public Users(String username, String email, String age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
    public Users() {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
