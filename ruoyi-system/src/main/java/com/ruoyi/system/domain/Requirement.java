package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.joda.time.DateTime;

/**
 * 用户需求对象 requirement
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
public class Requirement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需求ID，主键，自增 */
    private Long id;

    /** 用户ID，关联user表的id */
    @Excel(name = "用户ID，关联user表的id")
    private Long userId;

    /** 需求详细内容描述 */
    @Excel(name = "需求详细内容描述")
    private String content;

    /** 需求状态：0-未提交，1-已提交 */
    @Excel(name = "需求状态：0-未提交，1-已提交")
    private Integer status;

    /** 需求发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "需求发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("status", getStatus())
            .append("publishTime", getPublishTime())
            .toString();
    }
}
