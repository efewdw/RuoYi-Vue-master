package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 企业类别（行业分类）对象 category
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
public class Category extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别ID，主键，自增 */
    private Long id;

    /** 类别名称（如：制造业、金融业、服务业等） */
    @Excel(name = "类别名称", readConverterExp = "如=：制造业、金融业、服务业等")
    private String categoryName;

    /** 类别层级：1-一级分类，2-二级分类，3-三级分类 */
    @Excel(name = "类别层级：1-一级分类，2-二级分类，3-三级分类")
    private Integer level;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryName", getCategoryName())
            .append("parentId", getParentId())
            .append("level", getLevel())
            .toString();
    }
}
