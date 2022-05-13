package com.example.kaueparking;

public abstract class User {
    private String name;
    private String id;
    private String password;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean verifyPass(String inputPass){
        if (inputPass.equalsIgnoreCase(this.password)){
            return true;
        }
        return false;
    }
    public boolean verifyInfo(){
        if (this.name == null || this.name.equalsIgnoreCase("")){
            return false;
        }if (this.password == null || this.password.equalsIgnoreCase("")){
            return false;
        }if (this.phone == null || this.phone.equalsIgnoreCase("")){
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
