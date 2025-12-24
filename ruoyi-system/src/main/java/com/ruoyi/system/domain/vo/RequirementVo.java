package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

public class RequirementVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 需求ID，主键，自增 */
    private Long id;

    /** 用户ID，关联user表的id */
    @Excel(name = "用户ID，关联user表的id")
    private Long userId;

    /** 需求详细内容描述 */
    @Excel(name = "需求详细内容描述")
    private String demand;

    /** 需求状态：0-未提交，1-已提交 */
    @Excel(name = "需求状态：0-未提交，1-已提交")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
