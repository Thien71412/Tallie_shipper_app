package com.example.tallieshipper.models;

import java.io.Serializable;

// TODO: phai implements Serializable
public class Shipper implements Serializable {

    String _id;
    String name;
    String nationalId;
    String phone;
    String password;

    public Shipper(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Shipper(String _id, String phone, String password, String name, String nationalId) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.nationalId = nationalId;
    }

    public Shipper(String phone, String password, String name, String nationalId) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.nationalId = nationalId;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
