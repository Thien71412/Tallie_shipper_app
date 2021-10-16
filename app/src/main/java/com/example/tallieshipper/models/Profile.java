package com.example.tallieshipper.models;

import java.io.Serializable;

public class Profile implements Serializable {
    String _id;
    String name;
    String nationalId;
    String phone;

    public Profile(String _id, String name, String nationalId, String phone) {
        this._id = _id;
        this.name = name;
        this.nationalId = nationalId;
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
