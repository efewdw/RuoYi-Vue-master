package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CategoryMapper;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.ICategoryService;

/**
 * 企业类别（行业分类）Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询企业类别（行业分类）
     * 
     * @param id 企业类别（行业分类）主键
     * @return 企业类别（行业分类）
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询企业类别（行业分类）列表
     * 
     * @param category 企业类别（行业分类）
     * @return 企业类别（行业分类）
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增企业类别（行业分类）
     * 
     * @param category 企业类别（行业分类）
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改企业类别（行业分类）
     * 
     * @param category 企业类别（行业分类）
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除企业类别（行业分类）
     * 
     * @param ids 需要删除的企业类别（行业分类）主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除企业类别（行业分类）信息
     * 
     * @param id 企业类别（行业分类）主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }
}
