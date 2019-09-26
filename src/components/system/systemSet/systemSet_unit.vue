<template>
  <div>
    <div class="content-box">
      <div class="title center">单位设置</div>
      <div class="Unit">
        <div class="button center hand" @click="showDialog">
          <i class="el-icon-circle-plus-outline"></i>
          新增
        </div>
        <div class="button center hand" @click="edit">
          <i class="el-icon-edit-outline"></i>
          编辑
        </div>
        <div class="button center hand" @click="deleteUnit">
          <i class="el-icon-delete"></i>
          删除
        </div>
      </div>
      <div class="table-wrap">
        <el-table
          :data="option"
          :header-cell-style="headerStyle"
          :cell-style="tdStyle"
          stripe
          @selection-change="selectChange"
        >
          <el-table-column type="selection" width='45'></el-table-column>
          <el-table-column type="index" label="序号" width="60"></el-table-column>
          <el-table-column prop="suid" label="社会统一信用代码"></el-table-column>
          <el-table-column prop="constructionName" label="参建单位名称"></el-table-column>
          <el-table-column prop="shortName" label="简称"></el-table-column>
          <el-table-column prop="dictionaries.title" label="单位类型"></el-table-column>
          <el-table-column prop="contacts" label="负责人"></el-table-column>
          <el-table-column prop="mobilePhone" label="联系电话"></el-table-column>
        </el-table>
      </div>
      <div v-show="Dialog" class="backGround block"></div>
      <div class="dialog-box" v-show="Dialog" style="width:12rem;top:1rem">
        <div class="title">
          {{isAdd?'添加单位':'编辑单位'}}
          <i class="el-icon-close hand" v-if="isAdd" @click="claerForm(ruleForm);showDialog()"></i>
          <i class="el-icon-close hand" v-else @click="showDialog()"></i>
        </div>
        <el-form
          v-show="isAdd"
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="1.6rem"
          class="rule-form"
        >
          <div class="row flex between">
            <el-form-item label="单位名称" prop="constructionName">
              <el-input v-model="ruleForm.constructionName"></el-input>
            </el-form-item>
            <el-form-item label="简称" prop="shortName">
              <el-input v-model="ruleForm.shortName"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="社会统一信用代码" prop="suid">
              <el-input v-model="ruleForm.suid"></el-input>
            </el-form-item>
            <el-form-item label="法人代表" prop="legalPerson">
              <el-input v-model="ruleForm.legalPerson"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="单位类型" prop="companyType">
              <!-- <el-input v-model="ruleForm.companyType"></el-input> -->
              <el-select v-model="ruleForm.companyType" placeholder="选择单位类型">
                <el-option
                  v-for="item in companyTypeList"
                  :key="item.id"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="责任人" prop="contacts">
              <el-input v-model="ruleForm.contacts"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="联系电话" prop="mobilePhone">
              <el-input v-model="ruleForm.mobilePhone" type="number"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="开户银行" prop="bankOpen">
              <el-input v-model="ruleForm.bankOpen"></el-input>
            </el-form-item>
            <el-form-item label="注册资金" prop="capital">
              <el-input v-model="ruleForm.capital"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="开户地址" prop="bankAddress">
              <el-input v-model="ruleForm.bankAddress"></el-input>
            </el-form-item>
            <el-form-item label="开户账号" prop="bankNum">
              <el-input v-model="ruleForm.bankNum"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="单位详细地址" prop="address">
              <el-input v-model="ruleForm.address"></el-input>
            </el-form-item>
            <el-form-item label="备注说明" prop="remark">
              <el-input v-model="ruleForm.remark"></el-input>
            </el-form-item>
          </div>
        </el-form>
        <el-form
          v-show="!isAdd"
          :model="ruleForm2"
          :rules="rules"
          ref="ruleForm2"
          label-width="1.6rem"
          class="rule-form"
        >
          <div class="row flex between">
            <el-form-item label="单位名称" prop="constructionName">
              <el-input v-model="ruleForm2.constructionName"></el-input>
            </el-form-item>
            <el-form-item label="简称" prop="shortName">
              <el-input v-model="ruleForm2.shortName"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="社会统一信用代码" prop="suid">
              <el-input v-model="ruleForm2.suid"></el-input>
            </el-form-item>
            <el-form-item label="法人代表" prop="legalPerson">
              <el-input v-model="ruleForm2.legalPerson"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="单位类型" prop="companyType">
              <!-- <el-input v-model="ruleForm2.companyType"></el-input> -->
              <el-select v-model="ruleForm2.companyType" placeholder="选择单位类型">
                <el-option
                  v-for="item in companyTypeList"
                  :key="item.id"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="责任人" prop="contacts">
              <el-input v-model="ruleForm2.contacts"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="联系电话" prop="mobilePhone">
              <el-input v-model="ruleForm2.mobilePhone" type="number"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="ruleForm2.email"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="开户银行" prop="bankOpen">
              <el-input v-model="ruleForm2.bankOpen"></el-input>
            </el-form-item>
            <el-form-item label="注册资金" prop="capital">
              <el-input v-model="ruleForm2.capital"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="开户地址" prop="bankAddress">
              <el-input v-model="ruleForm2.bankAddress"></el-input>
            </el-form-item>
            <el-form-item label="开户账号" prop="bankNum">
              <el-input v-model="ruleForm2.bankNum"></el-input>
            </el-form-item>
          </div>
          <div class="row flex between">
            <el-form-item label="单位详细地址" prop="address">
              <el-input v-model="ruleForm2.address"></el-input>
            </el-form-item>
            <el-form-item label="备注说明" prop="remark">
              <el-input v-model="ruleForm2.remark"></el-input>
            </el-form-item>
          </div>
        </el-form>
        <div class="footer">
          <div class="button hand" v-show="isAdd" @click="submitForm('ruleForm')">确认</div>
          <div class="button hand" v-show="!isAdd" @click="submitForm('ruleForm2')">修改</div>
        </div>
      </div>
      <el-pagination
        @size-change="sizeChange"
        @current-change="currentChange"
        :current-page="currentPage"
        :page-sizes="pageSizes"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
        class="page"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import mixin from '@/utils/mixin'
export default {
  mixins: [mixin],
  data() {
    return {
      option: [], // table的数据
      blocked: false, // 弹窗背景的显示
      Dialog: false, // 弹窗
      deleteList: [], // 需要删除的数组
      isAdd: true, // 是否是新增
      ruleForm: {  // 添加的表单提交
        constructionName: '', // 参建单位
        shortName: '', // 简称
        capital: '', // 注册资金
        companyType: '', // 单位类型
        legalPerson: '', // 法人
        suid: '', // 社会统一信用代码
        organizationCode: '', // 组织机构代码
        bankOpen: '', // 开户银行
        bankNum: '', // 开户账号
        bankAddress: '', // 开户地址
        address: '', // 单位详细地址
        contacts: '', // 负责人
        mobilePhone: '', // 电话
        email: '', // 电子邮箱
        remark: '', // 备注
      },
      ruleForm2: {}, // 修改的表单
      rules: {
        constructionName: [
          { required: true, message: '请输入单位名称', trigger: 'blur' }
        ],
        shortName: [
          { required: true, message: '请输入单位简称', trigger: 'blur' }
        ],
        companyType: [
          { required: true, message: '请输入选择单位类型', trigger: 'blur' }
        ],
        contacts: [
          { required: true, message: '请填写负责人', trigger: 'blur' }
        ],
        mobilePhone: [
          { required: true, message: '请填写联系电话', trigger: 'blur' },
          { max: 11, min: 11, message: '请填写11位联系电话', trigger: 'blur' }
        ],
        suid: [
          { required: true, message: '请填写社会统一信用代码', trigger: 'blur' }
        ]
      },
      companyTypeList: [], // 公司类型的下拉列表
    }
  },
  mounted() {
    this.getProjectId()
    this.getConstructionList()
    this.getCompanyType()
  },
  methods: {
    // 获取参建单位列表
    getConstructionList() {
      this.$axios
        .post(`/api/OptionsConstructionApi/getConstructionList?projectId=${this.projectId}&pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
        .then(res => {
          this.option = res.data.data
          this.total = res.data.total
        })
    },

    // 获取单位类型
    getCompanyType() {
      this.$axios
        .post(`/api/dictionariesApi/selectDictionaries?category=UNIT_TYPE`)
        .then(res => {
          for (let i = 0; i < res.data.data.length; i++) {
            this.companyTypeList.push({
              value: res.data.data[i].id,
              label: res.data.data[i].title
            })
          }
        })
    },

    // 显示弹窗
    showDialog(){
      this.Dialog=!this.Dialog
      this.blocked=!this.blocked
      this.isAdd = true
    },

    selectChange(e) {
      this.deleteList = e
      // console.log(this.deleteList)
    },

    // 提交按钮，点击判断
    submitForm(name) {
        if (name === 'ruleForm') {
          this.$refs[name].validate(valid => {
          if (valid) {
            this.$axios
              .post(`/api/constructionCompanyApi/insertConstructionCompany?constructionName=${this.ruleForm.constructionName}&shortName=${this.ruleForm.shortName}&capital=${this.ruleForm.capital}&companyType=${this.ruleForm.companyType}&legalPerson=${this.ruleForm.legalPerson}&suid=${this.ruleForm.suid}&organizationCode=${this.ruleForm.organizationCode}&bankOpen=${this.ruleForm.bankOpen}&bankNum=${this.ruleForm.bankNum}&bankAddress=${this.ruleForm.bankAddress}&address=${this.ruleForm.address}&contacts=${this.ruleForm.contacts}&mobilePhone=${this.ruleForm.mobilePhone}&email=${this.ruleForm.email}&remark=${this.ruleForm.remark}&projectId=${this.projectId}`)
              .then(res => {
                if (res.data.code == 0) {
                  this.showDialog()
                  this.claerForm(this.ruleForm)
                  this.getConstructionList()
                  this.$message({
                    type: 'success',
                    message: '添加成功'
                  })
                } else {
                  this.$message({
                    type: 'warning',
                    message: '添加失败'
                  })
                }
              })
            this.claerForm(this.ruleForm)
          } else {
            return false
          }
        })
        } else {
          this.ruleForm2 = this.notNull(this.ruleForm2)
          this.$refs[name].validate(valid => {
          if (valid) {
            this.$axios
              .post(`/api/constructionCompanyApi/updateConstructionCompany?constructionName=${this.ruleForm2.constructionName}&shortName=${this.ruleForm2.shortName}&capital=${this.ruleForm2.capital}&companyType=${this.ruleForm2.companyType}&legalPerson=${this.ruleForm2.legalPerson}&suid=${this.ruleForm2.suid}&organizationCode=${this.ruleForm2.organizationCode}&bankOpen=${this.ruleForm2.bankOpen}&bankNum=${this.ruleForm2.bankNum}&bankAddress=${this.ruleForm2.bankAddress}&address=${this.ruleForm2.address}&contacts=${this.ruleForm2.contacts}&mobilePhone=${this.ruleForm2.mobilePhone}&email=${this.ruleForm2.email}&remark=${this.ruleForm2.remark}&id=${this.ruleForm2.id}`)
              .then(res => {
                // console.log(res.data)
                if (res.data.code == '0') {
                  this.showDialog()
                  this.getConstructionList()
                  this.$message({
                    type: 'success',
                    message: '添加成功'
                  })
                } else {
                  this.$message({
                    type: 'warning',
                    message: '添加失败'
                  })
                }
              })
          } else {
            // console.log('请填写全部参数')
            return false
          }
        })
        }
    },

    // 编辑按钮的点击
    edit() {
      if (this.deleteList.length == 0) {
        this.$message({
          type: 'warning',
          message: '请选择需要编辑的单位！'
        })
      } else if (this.deleteList.length > 1) {
        this.$message({
          type: 'warning',
          message: '编辑单位数不可大于1'
        })
      } else {
        // console.log(this.deleteList[0])
        this.ruleForm2 = Object.assign({}, this.deleteList[0])
        this.showDialog()
        this.isAdd = false
      }
    },

    // 切换显示条数
    sizeChange(val) {
      this.pageSize = val
      this.getConstructionList()
    },

    // 切换页码
    currentChange(val) {
      this.currentPage = val
      this.getConstructionList()
    },

    // 删除单位
    deleteUnit() {
      // console.log(this.deleteList.length)
      if (this.deleteList.length == 0) {
        this.$message({
          type: 'warning',
          message: '请选择需要删除的参建单位'
        })
      } else {
        var temp = []
        this.deleteList.forEach(item => {
          temp.push(item.id)
        });
        temp = temp.join()
        this.$axios
          .post(`/api/constructionCompanyApi/deleteConstructionCompanyIds?ids=${temp}`)
          .then(res => {
            if (res.data.code === '0') {
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              this.getConstructionList()
            } else {
              this.$message({
                message: '删除失败',
                type: 'warning'
              })
            }
          })
      }
    }
  }
}
</script>

<style lang="less">
@import "~@/assets/current.less";
.content-box {
  overflow-y: auto;
  position: relative;
  >.title {
    height: 1.05rem;
    width: 98%;
    border-bottom: 0.35rem solid #c5e8ff;
    font-size: 0.24rem;
    line-height: 0.7rem;
  }
  .Unit {
    color: #0090FFFF;
    width: 98%;
    height: 0.7rem;
    display: flex;
    flex-direction: row;
    padding: 0.2rem 0;
    .button {
      display: inline-block;
      border: 1px solid #0090FFFF;
      border-radius: 5px;
      line-height: 0.3rem;
      width: 0.7rem;
      cursor: pointer;
      margin-right: 0.2rem;
      transition: 0.5s all;
    }
    .button:hover {
      color: #fff;
      background-color: #0090ff;
    }
  }
  .table-wrap {
    width: 98%;
    border-right: 1px solid #EBEEF5;
  }
  .rule-form {
    width: 100%;
    line-height: 0.41rem;
    padding: 0.1rem 1rem;
    .row {
      margin-top: 0.1rem;
      input {
        width: 3rem;
        border: 1px solid #b1b1b1;
      }
      .el-form-item__label {
        font-size: 0.16rem;
        cursor: default;
      }
    }
  }
  .page {
    position: absolute;
    right: 0.5rem;
    bottom: 0.5rem;
  }
}
</style>