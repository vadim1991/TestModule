package com.englishschool.entity.spring;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vadym_Vlasenko on 10/19/2015.
 */
public class AssignTestBean implements Serializable {

    private List<String> groupIDs;
    private List<String> profileIDs;
    private List<String> testIDs;

    public List<String> getGroupIDs() {
        return groupIDs;
    }

    public void setGroupIDs(List<String> groupIDs) {
        this.groupIDs = groupIDs;
    }

    public List<String> getProfileIDs() {
        return profileIDs;
    }

    public void setProfileIDs(List<String> profileIDs) {
        this.profileIDs = profileIDs;
    }

    public List<String> getTestIDs() {
        return testIDs;
    }

    public void setTestIDs(List<String> testIDs) {
        this.testIDs = testIDs;
    }

    @Override
    public String toString() {
        return "AssignTestBean{" +
                "groupIDs=" + groupIDs +
                ", profileIDs=" + profileIDs +
                ", testUDs=" + testIDs +
                '}';
    }

}
