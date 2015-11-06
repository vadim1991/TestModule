package com.englishschool.entity;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 9/25/2015.
 */
@Document
public class TestProfile implements Serializable {

    @Id
    private String id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String email;
    private int age;
    private String password;
    private String groupId;
    private Set<Role> roles;
    private List<String> availableTests;
    private int averageMark;
    private List<String> passedTests;

    public TestProfile() {
        availableTests = new ArrayList<>();
        passedTests = new ArrayList<>();
        roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getAvailableTests() {
        return availableTests;
    }

    public void setAvailableTests(List<String> availableTests) {
        this.availableTests = availableTests;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }

    public List<String> getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(List<String> passedTests) {
        this.passedTests = passedTests;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestProfile profile = (TestProfile) o;
        return Objects.equal(id, profile.id) &&
                Objects.equal(name, profile.name) &&
                Objects.equal(surname, profile.surname) &&
                Objects.equal(email, profile.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, surname, email);
    }

    @Override
    public String toString() {
        return "TestProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", groupId='" + groupId + '\'' +
                ", roles=" + roles +
                ", availableTests=" + availableTests +
                ", averageMark=" + averageMark +
                ", passedTests=" + passedTests +
                '}';
    }

}
