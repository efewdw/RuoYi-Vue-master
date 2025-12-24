package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Requirement;

/**
 * 用户需求Service接口
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
public interface IRequirementService 
{
    /**
     * 查询用户需求
     * 
     * @param id 用户需求主键
     * @return 用户需求
     */
    public Requirement selectRequirementById(Long id);

    /**
     * 查询用户需求列表
     * 
     * @param requirement 用户需求
     * @return 用户需求集合
     */
    public List<Requirement> selectRequirementList(Requirement requirement);

    /**
     * 新增用户需求
     * 
     * @param requirement 用户需求
     * @return 结果
     */
    public int insertRequirement(Requirement requirement);

    /**
     * 修改用户需求
     * 
     * @param requirement 用户需求
     * @return 结果
     */
    public int updateRequirement(Requirement requirement);

    /**
     * 批量删除用户需求
     * 
     * @param ids 需要删除的用户需求主键集合
     * @return 结果
     */
    public int deleteRequirementByIds(Long[] ids);

    /**
     * 删除用户需求信息
     * 
     * @param id 用户需求主键
     * @return 结果
     */
    public int deleteRequirementById(Long id);
}
