package com.example.model;

/**
 * Created by 42278 on 2017/4/22.
 */
public class Enrollment {

    private String evenetId;
    private String name;
    private String phone;

    public String getEvenetId() {
        return evenetId;
    }

    public void setEvenetId(String evenetId) {
        this.evenetId = evenetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "evenetId='" + evenetId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
