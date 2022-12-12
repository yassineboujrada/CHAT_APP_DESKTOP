package com.example.MODELS;

public class login {
    private String username,email,password,avatar;

    public login(String username,String email, String password,String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //to string method
    @Override
    public String toString() {
        return "login{" +
                "username='" + username + '\'' +
                ", email='" + email;
    }


}
