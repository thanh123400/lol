package com.example.auctionapp.Model;

import java.util.List;

public class UserInfor {
    private String id;
    private String role;
    private String account;
    private String password;
    private String email;
    private String name;
    private String gender;
    private String location;
    private String birth;
    private String phone;
    private String uId;
    private String placeUID;
    private String dateCreateUID;
    private List<String> listFavourite;

    public List<String> getListFavourite() {
        return listFavourite;
    }

    public UserInfor() {

    }
    public UserInfor(String password, String email, String location, String birth, String gender,
                     String uId, String dateCreateUID, String placeUID, String phone) {
        this.password = password;
        this.email = email;
        this.location = location;
        this.birth = birth;
        this.gender = gender;
        this.uId = uId;
        this.dateCreateUID = dateCreateUID;
        this.placeUID = placeUID;
        this.phone = phone;

    }
    public UserInfor(List<String> listFavourite, List<String> listRegister) {
        this.listFavourite = listFavourite;
        this.listRegister = listRegister;
    }
    public void setListFavourite(List<String> listFavourite) {
        this.listFavourite = listFavourite;
    }

    public List<String> getListRegister() {
        return listRegister;
    }

    public void setListRegister(List<String> listRegister) {
        this.listRegister = listRegister;
    }

    private List<String> listRegister;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPlaceUID() {
        return placeUID;
    }

    public void setPlaceUID(String placeUID) {
        this.placeUID = placeUID;
    }

    public String getDateCreateUID() {
        return dateCreateUID;
    }

    public void setDateCreateUID(String dateCreateUID) {
        this.dateCreateUID = dateCreateUID;
    }
}
