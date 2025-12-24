package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Category;
import com.ruoyi.system.domain.po.WxUserPo;
import com.ruoyi.system.domain.vo.WxUserVo;
import com.ruoyi.system.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.system.domain.Wxuser;
import com.ruoyi.system.service.IWxuserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户（微信用户和企业信息）Controller
 * 
 * @author ruoyi
 * @date 2025-12-22
 */
@RestController
@RequestMapping("/system/wxuser")
public class WxuserController extends BaseController
{
    @Autowired
    private IWxuserService wxuserService;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询用户（微信用户和企业信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(Wxuser wxuser)
    {
        startPage();
        List<Wxuser> list = wxuserService.selectWxuserList(wxuser);
        ArrayList<WxUserPo> wxUserVos = new ArrayList<>();
        for (Object o :list) {
            WxUserPo wxUserPo = new WxUserPo();
             com.ruoyi.system.domain.Wxuser wxuser1=(Wxuser)o;
            BeanUtils.copyProperties(wxuser1,wxUserPo);
            if(wxuser1.getCategoryId()!=null){
                Category category = categoryMapper.selectById(wxuser1.getCategoryId());
                wxUserPo.setCategoryName(category.getCategoryName());
            }

            wxUserVos.add(wxUserPo);
        }

        return getDataTable(list);
    }

    /**
     * 导出用户（微信用户和企业信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:export')")
    @Log(title = "用户（微信用户和企业信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Wxuser wxuser)
    {
        List<Wxuser> list = wxuserService.selectWxuserList(wxuser);
        ExcelUtil<Wxuser> util = new ExcelUtil<Wxuser>(Wxuser.class);
        util.exportExcel(response, list, "用户（微信用户和企业信息）数据");
    }

    /**
     * 获取用户（微信用户和企业信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxuserService.selectWxuserById(id));
    }

    /**
     * 新增用户（微信用户和企业信息）
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:add')")
    @Log(title = "用户（微信用户和企业信息）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Wxuser wxuser)
    {
        return toAjax(wxuserService.insertWxuser(wxuser));
    }

    /**
     * 修改用户（微信用户和企业信息）
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:edit')")
    @Log(title = "用户（微信用户和企业信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Wxuser wxuser)
    {
        return toAjax(wxuserService.updateWxuser(wxuser));
    }

    /**
     * 删除用户（微信用户和企业信息）
     */
    @PreAuthorize("@ss.hasPermi('system:wxuser:remove')")
    @Log(title = "用户（微信用户和企业信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxuserService.deleteWxuserByIds(ids));
    }
}
