<template>
  <div id="systemSet_equipmentSet_quality">
    <div class="content-box">
      <!-- 头部 -->
      <div class="title">
        <div class="text">安全巡检设置</div>
      </div>
      <!-- 导航栏 -->
      <div class="nav">
        <a @click="navState=true" :class="navState?'active':''">工区设置</a>
        <!-- <a @click="navState=false" :class="navState?'':'active'">报警设置</a> -->
      </div>

      <!-- 设备管理 -->
      <!-- 功能 -->
      <div class="button" v-show="navState">
        <a @click="dialogClick();areaName='';address='';information=''">
          <i class="icon"></i>
          添加工区
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState">
        <ul>
          <li v-for="(item, index) in dataTable" :key="index">
            <div class="left-box">
              <div class="name">{{ item.areaName }}</div>
              <div class="location">
                <span>地址：</span>
                {{ item.address }}
              </div>
              <div class="subcontract">
                <span>所属参建单位：</span>
                {{ item.constructionName }}
              </div>
              <div class="subcontract">
                <span class="spa">负责人：</span>
                <div v-for="(item2, index2) in item.userList" :key="index2">
                  <p>{{ item2.userName }}</p>
                  <i class="el-icon-close" @click="deleteUser(item2, item)"></i>
                </div>
              </div>
            </div>
            <div class="right-box">
              <a @click="personnelShow=true;areaId = item.areaId">绑定负责人</a>
              <a @click="compileShow=true;editIndustrial(item)">编辑</a>
              <a @click="deleteIndustrial(item)">删除</a>
            </div>
          </li>
          <!-- <li>
            <div class="left-box">
              <div class="name">创新科技园</div>
              <div class="location">
                <span>地址：</span>
                深圳市宝安区创业路创新科技园
              </div>
              <div class="subcontract">
                <span>所属参建单位：</span>
                某某某参建单位
              </div>
              <div class="subcontract">
                <span>负责人：</span>
                韩子昂
              </div>
            </div>
            <div class="right-box">
              <a @click="personnelShow=true">绑定负责人</a>
              <a @click="compileShow=true">编辑</a>
              <a>删除</a>
            </div>
          </li>
          <li>
            <div class="left-box">
              <div class="name">创新科技园</div>
              <div class="location">
                <span>地址：</span>
                深圳市宝安区创业路创新科技园
              </div>
              <div class="subcontract">
                <span>所属参建单位：</span>
                某某某参建单位
              </div>
              <div class="subcontract">
                <span>负责人：</span>
              </div>
            </div>
            <div class="right-box">
              <a>绑定负责人</a>
              <a @click="compileShow=true">编辑</a>
              <a>删除</a>
            </div>
          </li> -->
        </ul>
      </div>

      <!-- 报警设置 -->
      <!-- 功能 -->
      <div class="button" v-show="!navState">
        <div class="name">通知成员</div>
        <a @click="personnelShow=true">
          <i class="icon"></i>
          添加人员
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="!navState">
        <div class="table-box">
          <el-table :data="tableData" stripe border>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="number" label="序号" width="60"></el-table-column>
            <el-table-column prop="account" label="账号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="post" label="岗位"></el-table-column>
            <el-table-column prop="company" label="单位"></el-table-column>
            <el-table-column prop="id" label="操作" width="100">
              <template>
                <a class="table-button">删除</a>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="paging-box">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[15, 30, 45]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageTotal"
          ></el-pagination>
        </div>
      </div>

      <!-- 添加工区 -->
      <div class="dialog-box" v-show="dialogShow">
        <div class="title">
          添加工区
          <a class="close" @click="dialogClick">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                工区名称
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-input v-model="areaName" placeholder="请填写名称"></el-input>
            </li>
            <li>
              <span>
                地址
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-input v-model="address" placeholder="请填写地址"></el-input>
            </li>
            <li>
              <span>
                所属参建单位
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-select
                v-model="information"
                placeholder="请选择参建单位"
                clearable
              >
                <el-option
                  v-for="item in informationList"
                  :key="item.constructionId"
                  :label="item.constructionName"
                  :value="item.constructionId"
                ></el-option>
              </el-select>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="addArea">保存</a>
        </div>
      </div>
      <!-- 编辑工区 -->
      <div class="dialog-box" v-show="compileShow">
        <div class="title">
          编辑工区
          <a class="close" @click="compileShow=false;areaName='';address='';information=''">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                工区名称
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-input v-model="areaName" placeholder="请填写名称"></el-input>
            </li>
            <li>
              <span>
                地址
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-input v-model="address" placeholder="请填写地址"></el-input>
            </li>
            <li>
              <span>
                所属参建单位
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-select
                v-model="information"
                placeholder="请选参建单位"
                clearable
              >
                <el-option
                  v-for="item in informationList"
                  :key="item.constructionId"
                  :label="item.constructionName"
                  :value="item.constructionId"
                ></el-option>
              </el-select>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="editButton">保存</a>
        </div>
      </div>
      <!-- 绑定负责人 -->
      <div class="dialog-box" v-show="personnelShow">
        <div class="title">
          绑定负责人
          <a class="close" @click="personnelShow=false;userPeople='';areaId=''">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                选择人员
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-select
                v-model="userPeople"
                placeholder="请选择负责人"
                clearable
              >
                <el-option
                  v-for="item in people"
                  :key="item.userId"
                  :label="item.userName"
                  :value="item.userId"
                ></el-option>
              </el-select>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="savePrincipal">保存</a>
        </div>
      </div>
      <!-- 遮罩层 -->
      <div class="shade-box" v-show="dialogShow || compileShow || personnelShow"></div>
    </div>
  </div>
</template>

<style lang="less">
#systemSet_equipmentSet_quality {
  width: 100%;
  .content-box {
    height: 9rem;
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    padding-left: 0.2rem;
    padding-bottom: 0.3rem;
    overflow-y: scroll;
    > .title {
      height: 0.71rem;
      width: 98%;
      border-bottom: 0.01rem solid #c5e8ff;
      .text {
        font-size: 0.24rem;
        text-align: center;
        line-height: 0.7rem;
      }
    }
    .nav {
      width: 98%;
      height: 0.62rem;
      padding-top: 0.26rem;
      border-bottom: 0.02rem solid #c5e8ff;
      a {
        color: #4a4a4a;
        font-size: 0.14rem;
        line-height: 0.34rem;
        display: inline-block;
        margin-right: 0.35rem;
        transition: all 0.5s;
        &:hover {
          color: #0090ff;
          // border-bottom: .02rem solid #0090ff;
        }
      }
      .active {
        color: #0090ff;
        border-bottom: 0.02rem solid #0090ff;
      }
    }
    .button {
      width: 98%;
      height: 0.7rem;
      position: relative;
      .name {
        color: #4a4a4a;
        font-size: 0.22rem;
        position: absolute;
        left: 0;
        top: 50%;
        font-weight: bolder;
        transform: translateY(-50%);
      }
      a {
        float: left;
        height: 0.3rem;
        color: #0090ff;
        font-size: 0.14rem;
        transition: all 0.5s;
        line-height: 0.28rem;
        border-radius: 0.02rem;
        padding-right: 0.09rem;
        border: 0.01rem solid #0090ff;
        position: absolute;
        right: 0;
        top: 50%;
        transform: translateY(-50%);
        &:hover {
          color: #fff;
          background-color: #0090ff;
          .icon {
            background-image: url("../../../../../static/images/system-newHover.png");
          }
        }
        .icon {
          width: 0.37rem;
          height: 0.28rem;
          transition: all 0.5s;
          display: inline-block;
          vertical-align: middle;
          background-repeat: no-repeat;
          background-position: center center;
          background-image: url("../../../../../static/images/system-new.png");
        }
      }
    }
    .main {
      width: 98%;
      > ul {
        width: 100%;
        li {
          width: 100%;
          height: 1.64rem;
          padding-top: 0.05rem;
          padding-left: 0.2rem;
          margin-bottom: 0.2rem;
          border: 0.01rem solid #c5e8ff;
          .left-box {
            float: left;
            .name {
              color: #4a4a4a;
              font-size: 0.18rem;
              font-weight: bolder;
              line-height: 0.38rem;
            }
            .location,
            .subcontract {
              color: #4a4a4a;
              font-size: 0.18rem;
              line-height: 0.38rem;
              span {
                color: #7b7b7b;
              }
              .spa {
                line-height: 0.24rem;
              }
              div {
                display: inline-block;
                padding:0.05rem 0.1rem;
                border-radius: 0.15rem;
                background-color: rgb(85, 158, 253);
                margin-right: 0.1rem;
                box-shadow: 1px 2px 3px black;
                line-height: 0.24rem;
                p {
                  display: inline-block;
                  color:#fff;
                }
                i {
                  color: #fff;
                  line-height: 0.24rem;
                  margin-left: 0.05rem;
                  cursor: pointer;
                }
              }
            }
          }
          .right-box {
            float: right;
            a {
              color: #0090ff;
              font-size: 0.18rem;
              margin-right: 0.3rem;
              line-height: 1.57rem;
            }
          }
        }
      }
      .table-box {
        width: 100%;
        min-height: 5.6rem;
        // padding-left: .2rem;
        .el-table {
          width: 16.28rem;
          // width: 100%;
          th {
            padding: 0;
            div {
              color: #4a4a4a;
              height: 0.35rem;
              line-height: 0.35rem;
              background-color: #c5e8ff;
            }
          }
          td {
            padding: 0;
            div {
              height: 0.35rem;
              color: #646464;
              line-height: 0.35rem;
            }
          }
          .table-button {
            color: #0090ff;
            padding: 0 0.08rem;
            text-decoration: underline;
          }
        }
        .green-color {
          color: #58de87 !important;
        }
        .red-color {
          color: #fe898f !important;
        }
      }
      .paging-box {
        width: 100%;
        height: 0.52rem;
        margin-top: 0.2rem;
        padding-right: 0.2rem;
        padding-bottom: 0.2rem;
        .el-pagination {
          float: right;
        }
      }
    }
    .dialog-box {
      left: 50%;
      top: 2.14rem;
      z-index: 200;
      width: 6.84rem;
      overflow: hidden;
      position: absolute;
      border-radius: 0.1rem;
      transform: translate(-50%);
      background-color: #fefefe;
      .title {
        color: #fff;
        height: 0.6rem;
        font-size: 0.24rem;
        line-height: 0.6rem;
        text-align: center;
        position: relative;
        font-weight: bolder;
        background: linear-gradient(to right, #6cc4ff, #489cff);
        a {
          top: 50%;
          right: 0.2rem;
          color: #fff;
          position: absolute;
          transform: translateY(-50%);
        }
      }
      .form {
        > ul {
          padding-bottom: 0.3rem;
          > li {
            display: flex;
            height: 0.71rem;
            padding-top: 0.3rem;
            > span {
              width: 1.85rem;
              height: 0.41rem;
              font-size: 0.16rem;
              text-align: right;
              position: relative;
              line-height: 0.41rem;
              padding-right: 0.32rem;
              display: inline-block;
              .required {
                top: -0.01rem;
                right: 0.22rem;
                color: #f00;
                position: absolute;
              }
            }
            input {
              width: 3.66rem;
              height: 0.41rem;
              padding-left: 0.1rem;
              border-radius: 0.02rem;
              border: 0.01rem solid #b1b1b1;
            }
            .el-input {
              width: 3.66rem;
            }
            .el-date-editor {
              width: 3.66rem;
              height: 0.41rem;
              input {
                padding-left: 0.3rem;
              }
            }
          }
        }
        > .search-box {
          height: 0.82rem;
          padding-left: 0.3rem;
          padding-top: 0.2rem;
          .input-box {
            width: 4rem;
            height: 0.4rem;
            overflow: hidden;
            padding-left: 0.16rem;
            border-radius: 0.04rem;
            display: inline-block;
            border: 0.01rem solid #b6b6b6;
            input {
              width: 3.4rem;
              height: 0.38rem;
              font-size: 0.17rem;
              &::placeholder {
                color: #dadada;
              }
            }
            a {
              color: #ccc;
              width: 0.38rem;
              height: 0.38rem;
              font-size: 0.18rem;
              text-align: center;
              line-height: 0.38rem;
              transition: all 0.5s;
              display: inline-block;
              &:hover {
                color: #fff;
                background-color: #ccc;
              }
            }
          }
        }
        > .table-box {
          width: 100%;
          min-height: 3.85rem;
          padding-left: 0.3rem;
          padding-right: 0.3rem;
          .el-table {
            width: 16.28rem;
            // width: 100%;
            th {
              padding: 0;
              div {
                color: #4a4a4a;
                height: 0.35rem;
                line-height: 0.35rem;
                background-color: #c5e8ff;
              }
            }
            td {
              padding: 0;
              div {
                height: 0.35rem;
                color: #646464;
                line-height: 0.35rem;
              }
            }
            .table-button {
              color: #0090ff;
              padding: 0 0.08rem;
              text-decoration: underline;
            }
          }
          .green-color {
            color: #58de87 !important;
          }
          .red-color {
            color: #fe898f !important;
          }
        }
        > .paging-box {
          width: 100%;
          height: 0.52rem;
          margin-top: 0.2rem;
          padding-right: 0.2rem;
          padding-bottom: 0.2rem;
          .el-pagination {
            float: right;
          }
        }
      }
      .confirm {
        height: 0.8rem;
        background-color: #f8f8f8;
        border-top: 0.01rem solid #dedede;
        .button {
          color: #fff;
          display: block;
          width: 1.63rem;
          height: 0.49rem;
          margin: 0 auto;
          font-size: 0.2rem;
          margin-top: 0.15rem;
          text-align: center;
          line-height: 0.47rem;
          transition: all 0.5s;
          border-radius: 0.02rem;
          background-color: #ffd14f;
          border: 0.01rem solid #d9b759;
          &:hover {
            background-color: #d9b759;
          }
        }
      }
    }
    .shade-box {
      top: 0;
      left: 0;
      width: 100%;
      z-index: 100;
      height: 100%;
      position: fixed;
      background-color: rgba(0, 0, 0, 0.5);
    }
  }
}
</style>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          number: 1, // 序号
          account: "aqwer111", // 账号
          name: "某某某", // 姓名
          phone: "12345678978", // 手机号
          post: "安全员", // 岗位
          company: "深圳市市政有限公司", // 公司
          id: 1 // 人员id
        },
        {
          number: 2, // 序号
          account: "aqwer111", // 账号
          name: "某某某", // 姓名
          phone: "12345678978", // 手机号
          post: "安全员", // 岗位
          company: "深圳市市政有限公司", // 公司
          id: 1 // 人员id
        }
      ], // 列表数据
      tableData2: [
        {
          number: 1, // 序号
          account: "aqwer111", // 账号
          name: "某某某", // 姓名
          phone: "12345678978", // 手机号
          post: "安全员", // 岗位
          id: 1 // 人员id
        },
        {
          number: 2, // 序号
          account: "aqwer111", // 账号
          name: "某某某", // 姓名
          phone: "12345678978", // 手机号
          post: "安全员", // 岗位
          id: 1 // 人员id
        }
      ], // 添加人员列表数据
      navState: true, // 当前模块
      dialogShow: false, // 新增设备对话框
      compileShow: false, // 编辑对话框
      personnelShow: false, // 新增人员对话框
      pageSize: 15, // 每页条数
      pageNum: 1, // 当前页
      pageTotal: 2, // 总条数
      pageNum2: 1, // 每页条数
      pageTotal2: 2, // 总条数
      projectId: '', // 账号
      dataTable: [], // 工区设置的列表
      people: '', // 绑定负责人列表
      userPeople: '', // v-modesl绑定负责人
      areaId:'', // 工业区ID
      informationList: '', // 参建单位列表
      information: '', // 参建单位
      areaName: '', // 工区名称
      address: '', // 工区地址
    };
  },
  created() {
    this.getPid();
    this.getOptionsList();
    this.getUserList();
    this.getInformationList()
  },
  methods: {
    // 每页条数切换
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.pageSize = val;
    },

    // 当前页
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      this.pageNum = val;
    },

    // 当前页
    handleCurrentChange2(val) {
      // console.log(`当前页: ${val}`)
      this.pageNum = val;
    },

    // 新增对话框状态切换
    dialogClick() {
      this.dialogShow = !this.dialogShow;
    },

    //获取pid
    getPid() {
      this.projectId = sessionStorage.getItem("pid");
    },

    // 获取工区列表
    getOptionsList() {
      this.$axios
        .post(`/api/optionsSafetyApi/getOptionsList?projectId=${this.projectId}`)
        .then(res => {
          if (res.data.code == 0) {
            this.dataTable = res.data.data
          }
        })
    },

    // 删除负责人
    deleteUser(item,itemAreaid) {
      // console.log(item)
      this.$confirm('删除负责人' + item.userName + '吗？', '请确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // console.log('确认')
          this.$axios
            .post(`/api/optionsSafetyApi/deleteAreaUser?userId=${item.userId}&areaId=${itemAreaid.areaId}`)
            .then(res => {
              // console.log(res)
              if (res.data.code == 0) {
                this.$message({
                  message: '删除成功！',
                  type: 'success'
                })
                this.getOptionsList()
              } else {
                this.$message({
                  message: '删除失败！',
                  type: 'warning'
                })
              }
            })
        }).catch(() => {
          this.$message('已取消删除！')
        })
    },

    // 获得负责人列表
    getUserList() {
      this.$axios
        .post(`/api/optionsSafetyApi/getUserList?projectId=${this.projectId}`)
        .then(res => {
          // console.log(res.data)
          if (res.data.code == 0) {
            this.people = res.data.data
          }
        })
    },

    // 保存负责人
    savePrincipal() {
      // console.log(this.areaId)
      if (this.userPeople == '') {
        this.$message({
          message: '请选择需要添加的负责人',
          type: 'wraning'
        })
      } else {
        this.$axios
          .post(`/api/optionsSafetyApi/addAreaUser?userId=${this.userPeople}&areaId=${this.areaId}`)
          .then(res => {
            // console.log(res.data)
            if (res.data.code == 0) {
              this.$message({
                message: '负责人绑定成功！',
                type: 'success'
              })
              this.personnelShow=false;
              this.userPeople='';
              this.areaId='';
              this.getOptionsList()
            } else if (res.data.code == -1) {
              this.$message({
                message: '负责人已存在，不可重复添加',
                type: 'waring'
              })
            } else {
              this.$message({
                message: '添加失败',
                type: 'waring'
              })
            }
          })
      }
    },

    // 删除工业区
    deleteIndustrial(item) {
      this.$confirm('删除' + item.areaName + '吗？', '请确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        .then(() => {
          this.$axios
            .post(`/api/optionsSafetyApi/deleteArea?areaId=${item.areaId}`)
            .then(res => {
              // console.log(res.data)
              if (res.data.code == 0) {
                this.$message({
                  message: '删除成功！',
                  type: 'success'
                })
                this.getOptionsList()
              } else {
                this.$message({
                  message: '删除失败！',
                  type: 'warning'
                })
              }
            })
        })
        .catch(() => {
          this.$message('已取消删除！')
        })
    },

    // 获参建单位
    getInformationList() {
      this.$axios
        .post(`/api/safetyPcApi/getInformationList?projectId=${this.projectId}`)
        .then(res => {
          this.informationList = res.data.data
        })
    },

    // 编辑工业区
    editIndustrial(item) {
      console.log(item)
      this.areaName = item.areaName
      this.address = item.address
      this.information = item.constructionId
      this.areaId = item.areaId
    },

    // 编辑提交
    editButton() {
      if (this.areaName == '') {
        this.$message({
          message: '请填写工区名称！',
          type: 'warning'
        })
      } else if (this.address == '') {
        this.$message({
          message: '请填写工区地址！',
          type: 'warning'
        })
      } else if (this.information == '') {
        this.$message({
          message: '请填选参建单位！',
          type: 'warning'
        })
      } else {
        this.$axios
          .post(`/api/optionsSafetyApi/updateArea?areaId=${this.areaId}&areaName=${this.areaName}&address=${this.address}&constructionId=${this.information}`)
          .then(res => {
            // console.log(res.data)
            if (res.data.code == 0) {
              this.$message({
                message: '修改成功！',
                type: 'success'
              })
              this.compileShow=false;
              this.areaName='';
              this.address='';
              this.information='';
              this.getOptionsList()
            } else {
              this.$message({
                message: '修改失败！',
                type: 'warning'
              })
            }
          })
      }
    },

    // 添加工业区
    addArea() {
      if (this.areaName == '') {
        this.$message({
          message: '工业区名称不可为空！',
          type: 'warning'
        })
      } else if (this.address == '') {
        this.$message({
          message: '工业区地址不可为空！',
          type: 'warning'
        })
      } if (this.information == '') {
        this.$message({
          message: '请选参建单位！',
          type: 'warning'
        })
      } else {
        this.$axios
          .post(`/api/optionsSafetyApi/addArea?areaName=${this.areaName}&address=${this.address}&constructionId=${this.information}`)
          .then(res => {
            if (res.data.code == 0) {
              this.$message({
                message: '添加成功！',
                type: 'success'
              })
              this.dialogClick()
              this.getOptionsList()
            } else {
              this.$message({
                message: '添加失败！',
                type: 'warning'
              })
            }
          })
      }
    }
  }
};
</script>