package com.englishschool.entity.datatable;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
public class ProfileDataTableBean implements Serializable {

    private String id;
    private String name;
    private String surname;
    private String email;
    private int averageMark;
    private String updateLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }

    public String getUpdateLink() {
        return updateLink;
    }

    public void setUpdateLink(String updateLink) {
        this.updateLink = updateLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDataTableBean that = (ProfileDataTableBean) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(name, that.name) &&
                Objects.equal(surname, that.surname) &&
                Objects.equal(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, surname, email);
    }

    @Override
    public String toString() {
        return "ProfileDataTableBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", averageMark=" + averageMark +
                '}';
    }
}
