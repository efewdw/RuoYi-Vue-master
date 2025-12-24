package com.ruoyi.system.domain.vo;

import java.util.List;

public class CategoryTreeVO {
    private Long id;
    private String categoryName;
    private Long parentId;
    private Integer level;
    private List<CategoryTreeVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CategoryTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTreeVO> children) {
        this.children = children;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
