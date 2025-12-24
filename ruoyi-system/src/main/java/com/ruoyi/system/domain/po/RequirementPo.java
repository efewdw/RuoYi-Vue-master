package com.ruoyi.system.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class RequirementPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 需求ID，主键，自增
     */
    private Long id;

    /**
     *
     * 用户名称
     */
    @Excel(name = "用户名字，关联user表的id")
    private String nickName;
    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 需求详细内容描述
     */
    @Excel(name = "需求详细内容描述")
    private String content;

    /**
     * 需求状态：0-未提交，1-已提交
     */
    @Excel(name = "需求状态：0-已提交，1-对接中,2-已完成")
    private Integer status;
    /** 需求发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "需求发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}