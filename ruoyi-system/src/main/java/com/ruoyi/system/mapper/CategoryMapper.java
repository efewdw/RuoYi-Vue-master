package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Category;

/**
 * 企业类别（行业分类）Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
public interface CategoryMapper 
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
     * 删除企业类别（行业分类）
     * 
     * @param id 企业类别（行业分类）主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除企业类别（行业分类）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);
    /**
     * 查询所有类别（包含所有层级）
     * @return 类别列表
     */
    List<Category> selectAllCategories();

    /**
     * 根据父级ID查询子类别列表
     * @param parentId 父级ID
     * @return 子类别列表
     */
    List<Category> selectByParentId(Long parentId);

    /**
     * 根据层级查询类别列表
     * @param level 层级（1-一级，2-二级，3-三级）
     * @return 类别列表
     */
    List<Category> selectByLevel(Integer level);

    /**
     * 根据ID查询类别
     * @param id 类别ID
     * @return 类别对象
     */
    Category selectById(Long id);
}
