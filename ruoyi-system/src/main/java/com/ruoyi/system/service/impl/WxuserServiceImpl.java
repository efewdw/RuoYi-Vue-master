package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxuserMapper;
import com.ruoyi.system.domain.Wxuser;
import com.ruoyi.system.service.IWxuserService;

/**
 * 用户（微信用户和企业信息）Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-22
 */
@Service
public class WxuserServiceImpl implements IWxuserService 
{
    @Autowired
    private WxuserMapper wxuserMapper;

    /**
     * 查询用户（微信用户和企业信息）
     * 
     * @param id 用户（微信用户和企业信息）主键
     * @return 用户（微信用户和企业信息）
     */
    @Override
    public Wxuser selectWxuserById(Long id)
    {
        return wxuserMapper.selectWxuserById(id);
    }

    /**
     * 查询用户（微信用户和企业信息）列表
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 用户（微信用户和企业信息）
     */
    @Override
    public List<Wxuser> selectWxuserList(Wxuser wxuser)
    {
        return wxuserMapper.selectWxuserList(wxuser);
    }

    /**
     * 新增用户（微信用户和企业信息）
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 结果
     */
    @Override
    public int insertWxuser(Wxuser wxuser)
    {
        return wxuserMapper.insertWxuser(wxuser);
    }

    /**
     * 修改用户（微信用户和企业信息）
     * 
     * @param wxuser 用户（微信用户和企业信息）
     * @return 结果
     */
    @Override
    public int updateWxuser(Wxuser wxuser)
    {
        return wxuserMapper.updateWxuser(wxuser);
    }

    /**
     * 批量删除用户（微信用户和企业信息）
     * 
     * @param ids 需要删除的用户（微信用户和企业信息）主键
     * @return 结果
     */
    @Override
    public int deleteWxuserByIds(Long[] ids)
    {
        return wxuserMapper.deleteWxuserByIds(ids);
    }

    /**
     * 删除用户（微信用户和企业信息）信息
     * 
     * @param id 用户（微信用户和企业信息）主键
     * @return 结果
     */
    @Override
    public int deleteWxuserById(Long id)
    {
        return wxuserMapper.deleteWxuserById(id);
    }
}
