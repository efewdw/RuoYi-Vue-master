<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="微信用户openid，唯一标识" prop="openid">
        <el-input
          v-model="queryParams.openid"
          placeholder="请输入微信用户openid，唯一标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入微信昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统一社会信用代码，18位" prop="creditCode">
        <el-input
          v-model="queryParams.creditCode"
          placeholder="请输入统一社会信用代码，18位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法定代表人姓名" prop="legalPerson">
        <el-input
          v-model="queryParams.legalPerson"
          placeholder="请输入法定代表人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册资本，单位：万元" prop="registeredCapital">
        <el-input
          v-model="queryParams.registeredCapital"
          placeholder="请输入注册资本，单位：万元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成立日期" prop="establishDate">
        <el-date-picker clearable
          v-model="queryParams.establishDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择成立日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="企业类别ID，关联category表的id" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入企业类别ID，关联category表的id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败" prop="companyCertified">
        <el-input
          v-model="queryParams.companyCertified"
          placeholder="请输入企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入企业地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:wxuser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:wxuser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:wxuser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:wxuser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wxuserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID，自增" align="center" prop="id" />
      <el-table-column label="微信用户openid，唯一标识" align="center" prop="openid" />
      <el-table-column label="微信昵称" align="center" prop="nickname" />
      <el-table-column label="微信头像URL" align="center" prop="avatarUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.avatarUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="sex" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="企业名称" align="center" prop="companyName" />
      <el-table-column label="统一社会信用代码，18位" align="center" prop="creditCode" />
      <el-table-column label="企业类型" align="center" prop="companyType" />
      <el-table-column label="法定代表人姓名" align="center" prop="legalPerson" />
      <el-table-column label="注册资本，单位：万元" align="center" prop="registeredCapital" />
      <el-table-column label="成立日期" align="center" prop="establishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.establishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册地址" align="center" prop="registeredAddress" />
      <el-table-column label="经营范围" align="center" prop="businessScope" />
      <el-table-column label="营业执照图片URL或文件路径" align="center" prop="licenseImageUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.licenseImageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="企业类别ID，关联category表的id" align="center" prop="categoryId" />
      <el-table-column label="企业历程" align="center" prop="companyHistory" />
      <el-table-column label="企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败" align="center" prop="companyCertified" />
      <el-table-column label="个人简历" align="center" prop="personalResume" />
      <el-table-column label="展示企业信息默认打开0,不打开1" align="center" prop="status" />
      <el-table-column label="企业地址" align="center" prop="address" />
      <el-table-column label="服务内容" align="center" prop="serviceContent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:wxuser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:wxuser:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户（微信用户和企业信息）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="微信用户openid，唯一标识" prop="openid">
          <el-input v-model="form.openid" placeholder="请输入微信用户openid，唯一标识" />
        </el-form-item>
        <el-form-item label="微信昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入微信昵称" />
        </el-form-item>
        <el-form-item label="微信头像URL" prop="avatarUrl">
          <image-upload v-model="form.avatarUrl"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="企业名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="统一社会信用代码，18位" prop="creditCode">
          <el-input v-model="form.creditCode" placeholder="请输入统一社会信用代码，18位" />
        </el-form-item>
        <el-form-item label="法定代表人姓名" prop="legalPerson">
          <el-input v-model="form.legalPerson" placeholder="请输入法定代表人姓名" />
        </el-form-item>
        <el-form-item label="注册资本，单位：万元" prop="registeredCapital">
          <el-input v-model="form.registeredCapital" placeholder="请输入注册资本，单位：万元" />
        </el-form-item>
        <el-form-item label="成立日期" prop="establishDate">
          <el-date-picker clearable
            v-model="form.establishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择成立日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册地址" prop="registeredAddress">
          <el-input v-model="form.registeredAddress" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="经营范围" prop="businessScope">
          <el-input v-model="form.businessScope" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="营业执照图片URL或文件路径" prop="licenseImageUrl">
          <image-upload v-model="form.licenseImageUrl"/>
        </el-form-item>
        <el-form-item label="企业类别ID，关联category表的id" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入企业类别ID，关联category表的id" />
        </el-form-item>
        <el-form-item label="企业历程" prop="companyHistory">
          <el-input v-model="form.companyHistory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败" prop="companyCertified">
          <el-input v-model="form.companyCertified" placeholder="请输入企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败" />
        </el-form-item>
        <el-form-item label="个人简历" prop="personalResume">
          <el-input v-model="form.personalResume" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="企业地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入企业地址" />
        </el-form-item>
        <el-form-item label="服务内容">
          <editor v-model="form.serviceContent" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWxuser, getWxuser, delWxuser, addWxuser, updateWxuser } from "@/api/system/wxuser"

export default {
  name: "Wxuser",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户（微信用户和企业信息）表格数据
      wxuserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        openid: null,
        nickname: null,
        avatarUrl: null,
        sex: null,
        phone: null,
        companyName: null,
        creditCode: null,
        companyType: null,
        legalPerson: null,
        registeredCapital: null,
        establishDate: null,
        registeredAddress: null,
        businessScope: null,
        licenseImageUrl: null,
        categoryId: null,
        companyHistory: null,
        companyCertified: null,
        personalResume: null,
        status: null,
        address: null,
        serviceContent: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        openid: [
          { required: true, message: "微信用户openid，唯一标识不能为空", trigger: "blur" }
        ],
        companyName: [
          { required: true, message: "企业名称不能为空", trigger: "blur" }
        ],
        creditCode: [
          { required: true, message: "统一社会信用代码，18位不能为空", trigger: "blur" }
        ],
        companyCertified: [
          { required: true, message: "企业认证状态：0-未认证，1-已认证，2-认证中，3-认证失败不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "展示企业信息默认打开0,不打开1不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询用户（微信用户和企业信息）列表 */
    getList() {
      this.loading = true
      listWxuser(this.queryParams).then(response => {
        this.wxuserList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        openid: null,
        nickname: null,
        avatarUrl: null,
        sex: null,
        phone: null,
        companyName: null,
        creditCode: null,
        companyType: null,
        legalPerson: null,
        registeredCapital: null,
        establishDate: null,
        registeredAddress: null,
        businessScope: null,
        licenseImageUrl: null,
        categoryId: null,
        companyHistory: null,
        companyCertified: null,
        personalResume: null,
        status: null,
        address: null,
        serviceContent: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加用户（微信用户和企业信息）"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getWxuser(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改用户（微信用户和企业信息）"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWxuser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addWxuser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除用户（微信用户和企业信息）编号为"' + ids + '"的数据项？').then(function() {
        return delWxuser(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/wxuser/export', {
        ...this.queryParams
      }, `wxuser_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
