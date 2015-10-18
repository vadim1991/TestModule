package com.englishschool.entity;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 10/18/2015.
 */
@Document
public class Category implements Serializable {

    @Id
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equal(id, category.id) &&
                Objects.equal(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title);
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
