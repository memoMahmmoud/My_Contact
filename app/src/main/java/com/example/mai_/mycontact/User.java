package com.example.mai_.mycontact;

/**
 * Created by Mai_ on 08-Oct-15.
 */
public class User {
    private String name;
    private String mobile;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String mobile,String email) {
        this.name = name;
        this.mobile = mobile;
        this.email=email;

    }

}
