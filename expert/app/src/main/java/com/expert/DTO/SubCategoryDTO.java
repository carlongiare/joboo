package com.expert.DTO;

import java.io.Serializable;

/**
 * Created by VARUN on 01/01/19.
 */
public class SubCategoryDTO implements Serializable {

    String id = "";
    String catId = "";
    String subcategory = "";
    boolean isSelected;

    public SubCategoryDTO(String id, String catId, String subcategory, boolean isSelected) {
        this.id = id;
        this.catId = catId;
        this.subcategory = subcategory;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
