package com.educative.ecommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="category_name")
    private @NotBlank String categoryName;

    private @NotBlank String categoryDescription;

    private @NotBlank String imgUrl;

    public Category(){}

    public Category(@ NotBlank String categoryName,@NotBlank String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Category(@NotBlank String categoryName,@NotBlank String categoryDescription, @NotBlank String imgUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    @NotBlank
    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setImgUrl(@NotBlank String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
