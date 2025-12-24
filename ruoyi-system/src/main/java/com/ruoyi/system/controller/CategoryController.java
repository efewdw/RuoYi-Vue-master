package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 企业类别（行业分类）Controller
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
@RestController
@RequestMapping("/system/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询企业类别（行业分类）列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public AjaxResult list(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        return success(list);
    }

    /**
     * 导出企业类别（行业分类）列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "企业类别（行业分类）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "企业类别（行业分类）数据");
    }

    /**
     * 获取企业类别（行业分类）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryService.selectCategoryById(id));
    }

    /**
     * 新增企业类别（行业分类）
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "企业类别（行业分类）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改企业类别（行业分类）
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "企业类别（行业分类）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除企业类别（行业分类）
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "企业类别（行业分类）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
