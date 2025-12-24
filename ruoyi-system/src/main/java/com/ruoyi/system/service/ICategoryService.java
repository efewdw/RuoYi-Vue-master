package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Category;

/**
 * 企业类别（行业分类）Service接口
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
public interface ICategoryService 
{
    /**
     * 查询企业类别（行业分类）
     * 
     * @param id 企业类别（行业分类）主键
     * @return 企业类别（行业分类）
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询企业类别（行业分类）列表
     * 
     * @param category 企业类别（行业分类）
     * @return 企业类别（行业分类）集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增企业类别（行业分类）
     * 
     * @param category 企业类别（行业分类）
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改企业类别（行业分类）
     * 
     * @param category 企业类别（行业分类）
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除企业类别（行业分类）
     * 
     * @param ids 需要删除的企业类别（行业分类）主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    /**
     * 删除企业类别（行业分类）信息
     * 
     * @param id 企业类别（行业分类）主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);
}
