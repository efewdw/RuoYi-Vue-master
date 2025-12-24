package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RequirementMapper;
import com.ruoyi.system.domain.Requirement;
import com.ruoyi.system.service.IRequirementService;

/**
 * 用户需求Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
@Service
public class RequirementServiceImpl implements IRequirementService 
{
    @Autowired
    private RequirementMapper requirementMapper;

    /**
     * 查询用户需求
     * 
     * @param id 用户需求主键
     * @return 用户需求
     */
    @Override
    public Requirement selectRequirementById(Long id)
    {
        return requirementMapper.selectRequirementById(id);
    }

    /**
     * 查询用户需求列表
     * 
     * @param requirement 用户需求
     * @return 用户需求
     */
    @Override
    public List<Requirement> selectRequirementList(Requirement requirement)
    {
        return requirementMapper.selectRequirementList(requirement);
    }

    /**
     * 新增用户需求
     * 
     * @param requirement 用户需求
     * @return 结果
     */
    @Override
    public int insertRequirement(Requirement requirement)
    {
        return requirementMapper.insertRequirement(requirement);
    }

    /**
     * 修改用户需求
     * 
     * @param requirement 用户需求
     * @return 结果
     */
    @Override
    public int updateRequirement(Requirement requirement)
    {
        return requirementMapper.updateRequirement(requirement);
    }

    /**
     * 批量删除用户需求
     * 
     * @param ids 需要删除的用户需求主键
     * @return 结果
     */
    @Override
    public int deleteRequirementByIds(Long[] ids)
    {
        return requirementMapper.deleteRequirementByIds(ids);
    }

    /**
     * 删除用户需求信息
     * 
     * @param id 用户需求主键
     * @return 结果
     */
    @Override
    public int deleteRequirementById(Long id)
    {
        return requirementMapper.deleteRequirementById(id);
    }
}
