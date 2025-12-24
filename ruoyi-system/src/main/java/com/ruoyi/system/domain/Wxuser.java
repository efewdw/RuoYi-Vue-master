package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户（微信用户和企业信息）对象 wxuser
 * 
 * @author ruoyi
 * @date 2025-12-22
 */
public class Wxuser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID，自增 */
    private Long id;

    /** 微信用户openid，唯一标识 */
    @Excel(name = "微信用户openid，唯一标识")
    private String openid;

    /** 微信昵称 */
    @Excel(name = "微信昵称")
    private String nickname;

    /** 微信头像URL */
    @Excel(name = "微信头像URL")
    private String avatarUrl;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String companyName;

    /** 统一社会信用代码，18位 */
    @Excel(name = "统一社会信用代码，18位")
    private String creditCode;

    /** 企业类型（如：有限责任公司、股份有限公司、个体工商户等） */
    @Excel(name = "企业类型", readConverterExp = "如=：有限责任公司、股份有限公司、个体工商户等")
    private String companyType;

    /** 法定代表人姓名 */
    @Excel(name = "法定代表人姓名")
    private String legalPerson;

    /** 注册资本，单位：万元 */
    @Excel(name = "注册资本，单位：万元")
    private String registeredCapital;

    /** 成立日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date establishDate;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String registeredAddress;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String businessScope;

    /** 营业执照图片URL或文件路径 */
    @Excel(name = "营业执照图片URL或文件路径")
    private String licenseImageUrl;

    /** 企业类别ID，关联category表的id */
    @Excel(name = "企业类别ID，关联category表的id")
    private Long categoryId;

    /** 企业历程（企业发展历史、重要事件等） */
    @Excel(name = "企业历程", readConverterExp = "企=业发展历史、重要事件等")
    private String companyHistory;

    /** 企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败 */
    @Excel(name = "企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败")
    private Integer companyCertified;

    /** 个人简历（用户个人工作经历、教育背景等） */
    @Excel(name = "个人简历", readConverterExp = "用=户个人工作经历、教育背景等")
    private String personalResume;

    /** 展示企业信息默认打开0,不打开1 */
    @Excel(name = "展示企业信息默认打开0,不打开1")
    private String status;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String address;

    /** 服务内容 */
    @Excel(name = "服务内容")
    private String serviceContent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }

    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }

    public void setCreditCode(String creditCode) 
    {
        this.creditCode = creditCode;
    }

    public String getCreditCode() 
    {
        return creditCode;
    }

    public void setCompanyType(String companyType) 
    {
        this.companyType = companyType;
    }

    public String getCompanyType() 
    {
        return companyType;
    }

    public void setLegalPerson(String legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() 
    {
        return legalPerson;
    }

    public void setRegisteredCapital(String registeredCapital)
    {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital()
    {
        return registeredCapital;
    }

    public void setEstablishDate(Date establishDate) 
    {
        this.establishDate = establishDate;
    }

    public Date getEstablishDate() 
    {
        return establishDate;
    }

    public void setRegisteredAddress(String registeredAddress) 
    {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredAddress() 
    {
        return registeredAddress;
    }

    public void setBusinessScope(String businessScope) 
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() 
    {
        return businessScope;
    }

    public void setLicenseImageUrl(String licenseImageUrl) 
    {
        this.licenseImageUrl = licenseImageUrl;
    }

    public String getLicenseImageUrl() 
    {
        return licenseImageUrl;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setCompanyHistory(String companyHistory) 
    {
        this.companyHistory = companyHistory;
    }

    public String getCompanyHistory() 
    {
        return companyHistory;
    }

    public void setCompanyCertified(Integer companyCertified) 
    {
        this.companyCertified = companyCertified;
    }

    public Integer getCompanyCertified() 
    {
        return companyCertified;
    }

    public void setPersonalResume(String personalResume) 
    {
        this.personalResume = personalResume;
    }

    public String getPersonalResume() 
    {
        return personalResume;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setServiceContent(String serviceContent) 
    {
        this.serviceContent = serviceContent;
    }

    public String getServiceContent() 
    {
        return serviceContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("avatarUrl", getAvatarUrl())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("companyName", getCompanyName())
            .append("creditCode", getCreditCode())
            .append("companyType", getCompanyType())
            .append("legalPerson", getLegalPerson())
            .append("registeredCapital", getRegisteredCapital())
            .append("establishDate", getEstablishDate())
            .append("registeredAddress", getRegisteredAddress())
            .append("businessScope", getBusinessScope())
            .append("licenseImageUrl", getLicenseImageUrl())
            .append("categoryId", getCategoryId())
            .append("companyHistory", getCompanyHistory())
            .append("companyCertified", getCompanyCertified())
            .append("personalResume", getPersonalResume())
            .append("status", getStatus())
            .append("address", getAddress())
            .append("serviceContent", getServiceContent())
            .toString();
    }
}
