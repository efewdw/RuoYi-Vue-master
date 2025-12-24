package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Wxuser;

/**
 * 用户（微信用户和企业信息）Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-22
 */
public interface WxuserMapper 
{
    /**
     * 根据openid查询是否登陆过
     */
public Wxuser selectWxuserByopenid(String openid);
    /**
     * 查询用户（微信用户和企业信息）
     * 
     * @param id 用户（微信用户和企业信息）主键
     * @return 用户（微信用户和企业信息）
     */
    public Wxuser selectWxuserById(Long id);

    /**
     * 查询用户（微信用户和企业信息）列表
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 用户（微信用户和企业信息）集合
     */
    public List<Wxuser> selectWxuserList(Wxuser wxuser);

    /**
     * 新增用户（微信用户和企业信息）
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 结果
     */
    public int insertWxuser(Wxuser wxuser);

    /**
     * 修改用户（微信用户和企业信息）
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 结果
     */
    public int updateWxuser(Wxuser wxuser);

    /**
     * 删除用户（微信用户和企业信息）
     * 
     * @param id 用户（微信用户和企业信息）主键
     * @return 结果
     */
    public int deleteWxuserById(Long id);

    /**
     * 批量删除用户（微信用户和企业信息）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxuserByIds(Long[] ids);
}
