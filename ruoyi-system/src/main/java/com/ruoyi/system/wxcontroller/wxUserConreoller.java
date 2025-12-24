package com.ruoyi.system.wxcontroller;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.oss.OssTransferUploadUtil;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.domain.Requirement;
import com.ruoyi.system.domain.Wxuser;
import com.ruoyi.system.domain.po.WxUserPo;
import com.ruoyi.system.domain.vo.CategoryTreeVO;
import com.ruoyi.system.domain.vo.OkHttpVo;
import com.ruoyi.system.domain.vo.RequirementVo;
import com.ruoyi.system.domain.vo.WxUserVo;
import com.ruoyi.system.mapper.CategoryMapper;
import com.ruoyi.system.mapper.RequirementMapper;
import com.ruoyi.system.mapper.WxuserMapper;
import com.ruoyi.system.utils.JwtUtils;
import com.ruoyi.system.utils.OkHttpUtils;
import io.jsonwebtoken.Claims;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 前台微信用户类
 */
@RestController
@RequestMapping("/wxusers")
public class wxUserConreoller {
    private static final Logger log = LoggerFactory.getLogger(wxUserConreoller.class);
    @Autowired
    private WxuserMapper wxuserMapper;
    @Autowired
    private OssTransferUploadUtil ossTransferUploadUtil;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RequirementMapper requirementMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 小程序的appid
     */
    private final String APP_ID = "wxe6a693986fff6a76";
    /**
     * 小程序的SECRET
     */
    private final String SECRET = "29c32eea3338cc6891e1c6b518f3fa3a";

    /**
     * /**
     * 保存用户信息接口
     */
    @PostMapping()
    public AjaxResult save(@RequestHeader String token, @RequestBody WxUserVo wxuservo) {
        boolean b = jwtUtils.validateToken(token);
        if (b == true) {
            //如果wxuser或者userId不为空开始更新
            if (wxuservo != null) {
                Claims claims = jwtUtils.parseToken(token);
                Integer user = (Integer) claims.get("userId");
                long userId = user.longValue();
                log.info("从token中获取到用户表的id" + userId);
                System.out.println(wxuservo);
                Wxuser wxuser = new Wxuser();
                BeanUtils.copyProperties(wxuservo,wxuser);

                wxuser.setId(userId);
                wxuser.setNickname(wxuservo.getNickName());
                //企业三要素校验
                String creditCode = wxuservo.getCreditCode();
                String companyName = wxuservo.getCompanyName();
                String legalPerson = wxuservo.getLegalPerson();
                OkHttpVo okHttpVo = new OkHttpVo();
                okHttpVo.setCompanyName(companyName);
                okHttpVo.setCreditCode(creditCode);
                okHttpVo.setLegalPerson(legalPerson);
                JSONObject jsonObject = new JSONObject();
                JSONObject from = JSONObject.from(okHttpVo);
                String string = from.toString();
                String url="https://kzcpthree.market.alicloudapi.com/company_three/check?spm=5176.product-detail.debug.1.27553de6992bIk";
                HashMap<String, String> map = new HashMap<>();
//                map.put("Authorization","APPCODE ")
//                OkHttpUtils.doPostJson(url,string,)
 //

                ///
               int i = wxuserMapper.updateWxuser(wxuser);
                if (i != 0) {
                  return AjaxResult.success("保存信息成功");
               }

                return AjaxResult.error("请创建新用户");
            }
        }else{
            return AjaxResult.error("token过期，请重新登录");
        }


        //为空直接报错
        return AjaxResult.error("请创建用户信息");
    }

    /**
     * 上传图片接口
     */
    @PostMapping("/load")
    public AjaxResult load(@RequestHeader String token, @RequestParam("file") MultipartFile file, String url) {

        boolean b = jwtUtils.validateToken(token);
        if (b == true) {
            Claims claims = jwtUtils.parseToken(token);
            Integer user = (Integer) claims.get("userId");
            long userId = user.longValue();
            log.info("从token中获取到用户表的id" + userId);
            if (file != null) {
                String ossFileUrl = null;
                String files = "https://zty2206267352.oss-cn-beijing.aliyuncs.com/";
                try {


                    ossFileUrl = ossTransferUploadUtil.uploadMultipartFileToOss(file);


                } catch (IOException e) {
                    return AjaxResult.error("文件上传失败，请重试");
                }

                String files1 = files + ossFileUrl;

                return AjaxResult.success("文件上传成功").put("url", files1);


            }

        } else {
            return AjaxResult.error("token过期，请重新登录");
        }

        return AjaxResult.error("文件上传失败，请重试");

    }

    /**
     * 微信登录
     *
     * @param code
     * @return
     */
    @GetMapping("/wechatLogin")
    public AjaxResult wechatLogin(String code) {
        //1.根据code 获取openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", APP_ID).replace("SECRET", SECRET).replace("CODE", code);
        String responseBodyStr = HttpUtils.sendGet(url);
        String openid = JSONObject.parseObject(responseBodyStr).getString("openid");
        //2.根据openID 查询数据库中的wxUser的信息 如果没有登录过，则创建一个wxUser的信息
        Wxuser wxUser = wxuserMapper.selectWxuserByopenid(openid);
        if (wxUser == null) {
            //2.1用户第一次登录，微信用户的信息保存到数据库中
            wxUser = new Wxuser();
            wxUser.setOpenid(openid);
            wxuserMapper.insertWxuser(wxUser);
        }
        //3.后端登录，基于若依认证，生成一个token
        String token = jwtUtils.generateToken(wxUser);
        log.info(token);

        return AjaxResult.success().put("token", token);
    }

    /**
     * 跟据userId查询所有信息
     */
    @PostMapping("/select")
    public AjaxResult select(@RequestHeader String token) {
        boolean b = jwtUtils.validateToken(token);
        if (b == true) {
            Claims claims = jwtUtils.parseToken(token);
            Integer user = (Integer) claims.get("userId");
            long userId = user.longValue();
            log.info("从token中获取到用户表的id" + userId);
            Wxuser wxuser = wxuserMapper.selectWxuserById(userId);
            Long categoryId = wxuser.getCategoryId();
            Category category = categoryMapper.selectById(categoryId);
            WxUserPo wxUserPo = new WxUserPo();
            BeanUtils.copyProperties(wxuser,wxUserPo);
            wxUserPo.setNickName(wxuser.getNickname());
            wxUserPo.setCategoryName(category.getCategoryName());

            if (wxuser == null) {
                return AjaxResult.error("非法用户id");
            }
            return AjaxResult.success(wxUserPo);
        } else {
            return AjaxResult.error("token过期，请重新登录");
        }

    }

    /**
     * 上传需求
     * 根据用户id
     */
    @PostMapping("/loadRequirement")
    public AjaxResult loadRequirement(@RequestHeader String token, @RequestBody  List<RequirementVo> requirementVos) {

        boolean b = jwtUtils.validateToken(token);
        if (b == true) {
            Claims claims = jwtUtils.parseToken(token);
            Integer user = (Integer) claims.get("userId");
            long userId = user.longValue();
            log.info("从token中获取到用户表的id" + userId);
            Requirement requirement = new Requirement();
            //将前端传过来的vo的成员变量拷贝
            for (Object os :requirementVos) {
                com.ruoyi.system.domain.vo.RequirementVo requirementVo =(RequirementVo)os;
                BeanUtils.copyProperties(requirementVo,requirement);
                requirement.setUserId(userId);
                requirement.setContent(requirementVo.getDemand());
                // 直接 new Date() 即可获取当前系统时间（包含年月日+时分秒+毫秒）
                Date currentDate = new Date();

                // 打印默认格式（可读性较差，需格式化）
                System.out.println("默认格式当前时间：" + currentDate);
                // 输出示例：Tue Dec 23 17:25:30 CST 2025

                // 格式化为数据库 DATETIME 兼容格式（yyyy-MM-dd HH:mm:ss）
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTimeStr = sdf.format(currentDate);
                try {
                    Date parse = sdf.parse(currentDateTimeStr);
                    requirement.setPublishTime(parse);
                    requirementMapper.insertRequirement(requirement);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            }



            return AjaxResult.success();
        }else {
            return AjaxResult.error("token过期，请重新登录");
        }


    }

    /**
     *
     * 查询所有需求
     * 根据userId
     *
     *
     */
    @GetMapping("/selectRequirement")
    public AjaxResult selectRequirement(@RequestHeader String token) {
        boolean b = jwtUtils.validateToken(token);
        if (b == true) {
            Claims claims = jwtUtils.parseToken(token);
            Integer user = (Integer) claims.get("userId");
            long userId = user.longValue();
            log.info("从token中获取到用户表的id" + userId);
            List<Requirement> requirements = requirementMapper.selectRequirementByuserId(userId);


            return AjaxResult.success(requirements);

        }else {
            return AjaxResult.error("token过期，请重新登录");
        }


    }

    /**
     * 查询所有类别
     */
    @GetMapping("/selectType")
    public AjaxResult selectType() {

        List<CategoryTreeVO> categoryTree = getCategoryTree();

        return AjaxResult.success(categoryTree);
    }

    /**
     * 递归方式组装树形结构（更简洁）
     */
    public List<CategoryTreeVO> getCategoryTree() {
        List<Category> allCategories = categoryMapper.selectAllCategories();

        // 转换为Map，key为parentId，value为子类别列表
        Map<Long, List<CategoryTreeVO>> categoryMap = allCategories.stream()
                .map(cat -> {
                    CategoryTreeVO vo = new CategoryTreeVO();
                    vo.setId(cat.getId());
                    vo.setCategoryName(cat.getCategoryName());
                    vo.setParentId(cat.getParentId());
                    vo.setLevel(cat.getLevel());
                    vo.setChildren(new ArrayList<>());
                    return vo;
                })
                .collect(Collectors.groupingBy(CategoryTreeVO::getParentId));

        // 组装树形结构
        List<CategoryTreeVO> result = categoryMap.getOrDefault(0L, new ArrayList<>());

        // 递归设置子类别
        result.forEach(item -> buildTree(item, categoryMap));

        return result;
    }

    private void buildTree(CategoryTreeVO parent, Map<Long, List<CategoryTreeVO>> categoryMap) {
        List<CategoryTreeVO> children = categoryMap.get(parent.getId());
        if (children != null && !children.isEmpty()) {
            parent.setChildren(children);
            children.forEach(child -> buildTree(child, categoryMap));
        }
    }
}
