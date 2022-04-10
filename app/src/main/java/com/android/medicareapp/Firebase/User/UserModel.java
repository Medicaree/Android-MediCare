package com.android.medicareapp.Firebase.User;

public class UserModel {
    String uid; //Unique ID
    String name;
    String address;
    String contact;
    String pic;
    String email;
    String password;
    String type;
    boolean isUser;
    public UserModel(){}

    public UserModel(String name, String address, String contact, String pic, String email, String password, boolean isUser) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.pic = pic;
        this.email = email;
        this.password = password;
        this.isUser = isUser;
    }
    public UserModel(String name, String address, String contact, String pic, String email, String password, boolean isUser, String type) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.pic = pic;
        this.email = email;
        this.password = password;
        this.isUser = isUser;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getPic() {
        return pic;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
