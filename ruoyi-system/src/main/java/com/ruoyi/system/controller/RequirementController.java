package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.Wxuser;
import com.ruoyi.system.domain.po.RequirementPo;
import com.ruoyi.system.mapper.WxuserMapper;
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
import com.ruoyi.system.domain.Requirement;
import com.ruoyi.system.service.IRequirementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户需求Controller
 * 
 * @author ruoyi
 * @date 2025-12-23
 */
@RestController
@RequestMapping("/system/requirement")
public class RequirementController extends BaseController
{
    @Autowired
    private IRequirementService requirementService;
    @Autowired
    private WxuserMapper wxuserMapper;

    /**
     * 查询用户需求列表
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Requirement requirement)
    {
        startPage();
        List<Requirement> list = requirementService.selectRequirementList(requirement);
        ArrayList<RequirementPo> requirementPos = new ArrayList<>();
        for (Object o :list) {
            com.ruoyi.system.domain.Requirement requirement1 =(Requirement)o;
            RequirementPo requirementPo = new RequirementPo();
            BeanUtils.copyProperties(requirement1,requirementPo);
            Wxuser wxuser = wxuserMapper.selectWxuserById(requirement1.getUserId());
            requirementPo.setNickName(wxuser.getNickname());
            requirementPo.setPhone(wxuser.getPhone());
            requirementPos.add(requirementPo);
        }

        return getDataTable(requirementPos);
    }

    /**
     * 导出用户需求列表
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:export')")
    @Log(title = "用户需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Requirement requirement)
    {
        List<Requirement> list = requirementService.selectRequirementList(requirement);
        ExcelUtil<Requirement> util = new ExcelUtil<Requirement>(Requirement.class);
        util.exportExcel(response, list, "用户需求数据");
    }

    /**
     * 获取用户需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(requirementService.selectRequirementById(id));
    }

    /**
     * 新增用户需求
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:add')")
    @Log(title = "用户需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Requirement requirement)
    {
        return toAjax(requirementService.insertRequirement(requirement));
    }

    /**
     * 修改用户需求
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:edit')")
    @Log(title = "用户需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Requirement requirement)
    {
        return toAjax(requirementService.updateRequirement(requirement));
    }

    /**
     * 删除用户需求
     */
    @PreAuthorize("@ss.hasPermi('system:requirement:remove')")
    @Log(title = "用户需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(requirementService.deleteRequirementByIds(ids));
    }
}
