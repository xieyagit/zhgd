<template>
  <div id="systemSet_equipmentSet_elevator">
    <div class="content-box">
      <!-- 头部 -->
      <div class="title">
        <div class="text">升降机设置</div>
      </div>
      <!-- 导航栏 -->
      <div class="nav">
        <a @click="navState=true" :class="navState?'active':''">设备管理</a>
        <a @click="navState=false" :class="navState?'':'active'">报警设置</a>
      </div>

      <!-- 设备管理 -->
      <!-- 功能 -->
      <div class="button" v-show="navState">
        <a @click="dialogClick">
          <i class="icon"></i>
          添加设备
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState">
        <ul>
          <li v-for="item in craneList" :key="item.id">
            <div class="left-box">
              <div class="name">{{ item.elevatorName }}</div>
              <div class="location">
                <span>设备SN：</span>
                {{ item.hxzId }}
              </div>
              <div class="subcontract">
                <span>设备SN（32位）：</span>
                {{ item.deviceNo }}
              </div>
            </div>
            <div class="right-box">
              <!-- <a>人员设置</a> -->
              <a @click="compileShow=true,clickEdit1(item)">编辑</a>
              <a @click="deleteTower(item.id)">删除</a>
            </div>
          </li>
        </ul>
      </div>

      <!-- 报警设置 -->
      <!-- 功能 -->
      <div class="button" v-show="!navState">
        <div class="name">通知成员</div>
        <!-- <a @click="personnelShow=true">
          <i class="icon"></i>
          添加人员
        </a> -->
        <i class="el-icon-search" @click="searchClick"></i>
        <el-input class="search" v-model="search" placeholder="输入姓名、账号搜索" @keydown.enter.native="searchClick"></el-input>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="!navState">
        <div class="table-box">
          <el-table :data="userList" stripe border>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column type="index" label="序号" width="60"></el-table-column>
            <el-table-column prop="userAccount" label="账号"></el-table-column>
            <el-table-column prop="userName" label="姓名"></el-table-column>
            <el-table-column prop="userPhone" label="手机号"></el-table-column>
            <!-- <el-table-column prop="post" label="岗位"></el-table-column>
            <el-table-column prop="company" label="单位"></el-table-column> -->
            <el-table-column prop="onOff" label="通知开关" width="100">
              <template slot-scope="scope">
                <!-- <a class="table-button">删除</a> -->
                <el-switch v-model="scope.row.onOff" @change="switchClick(scope.row)"></el-switch>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- <div class="paging-box">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[15, 30, 45]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageTotal"
          ></el-pagination>
        </div> -->
      </div>

      <!-- 新增设备 -->
      <div class="dialog-box" v-show="dialogShow">
        <div class="title">
          新增设备
          <a class="close" @click="dialogClick(),craneName='',hxzId='',deviceNo=''">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                设备名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="craneName">
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="number" v-model="hxzId">
            </li>
            <!-- <li>
              <span>
                设备SN(32位)
                <div class="required">*</div>
              </span>
              <input type="text" v-model="deviceNo">
            </li> -->
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="insertCrane">保存</a>
        </div>
      </div>
      <!-- 编辑设备 -->
      <div class="dialog-box" v-show="compileShow">
        <div class="title">
          编辑设备
          <a class="close" @click="compileShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                设备名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editTower.elevatorName">
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="number" v-model="editTower.hxzId">
            </li>
            <!-- <li>
              <span>
                设备SN(32位)
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editTower.deviceNo">
            </li> -->
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="saveEdit">保存</a>
        </div>
      </div>
      <!-- 新增人员 -->
      <div class="dialog-box" v-show="personnelShow" style="width:11.8rem">
        <div class="title">
          新增人员
          <a class="close" @click="personnelShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <div class="search-box">
            <div class="input-box">
              <input type="text" placeholder="请输入姓名或账号搜索人员" />
              <a class="el-icon-search"></a>
            </div>
          </div>
          <div class="table-box">
            <el-table :data="tableData2" stripe border>
              <el-table-column type="selection" width="40"></el-table-column>
              <el-table-column prop="number" label="序号" width="60"></el-table-column>
              <el-table-column prop="account" label="账号"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="phone" label="手机号"></el-table-column>
              <el-table-column prop="post" label="岗位"></el-table-column>
            </el-table>
          </div>
          <div class="paging-box">
            <el-pagination
              @current-change="handleCurrentChange2"
              :current-page="pageNum2"
              layout="total, prev, pager, next, jumper"
              :total="pageTotal2"
            ></el-pagination>
          </div>
        </div>
        <div class="confirm">
          <a class="button">保存</a>
        </div>
      </div>
      <!-- 遮罩层 -->
      <div class="shade-box" v-show="dialogShow || compileShow || personnelShow"></div>
    </div>
  </div>
</template>

<style lang="less">
#systemSet_equipmentSet_elevator {
  width: 100%;
  .content-box {
    min-height: 7rem;
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    padding-left: 0.2rem;
    padding-bottom: 0.3rem;
    max-height: 9.5rem;
    overflow: auto;
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
      .search {
        position: absolute;
        right:0rem;
        top:0.35rem;
        transform: translateY(-50%);
        width: 3rem;
        input {
          border:1px solid #B6B6B6FF;
          border-radius: 3px;
          // background-image: url('../../../../../static/images/search.png');
          // background-position: 95%
        }
      }
      .el-icon-search {
        position: absolute;
        color: #B6B6B6FF;
        right: 0.1rem;
        top:50%;
        transform: translateY(-50%);
        z-index: 10;
        font-size: 0.3rem;
        cursor: pointer;
      }
    }
    .main {
      width: 98%;
      > ul {
        width: 100%;
        li {
          width: 100%;
          height: 1.29rem;
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
            }
          }
          .right-box {
            float: right;
            a {
              color: #0090ff;
              font-size: 0.18rem;
              margin-right: 0.3rem;
              line-height: 1.21rem;
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
            .el-date-editor {
              width: 3.66rem;
              height: 0.41rem;
              input {
                padding-left: 0.3rem;
              }
            }
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
              -webkit-appearance: none;
            }
            input[type="number"]{
              -moz-appearance: textfield;
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
import mixin from '@/utils/mixin'
export default {
  mixins: [mixin],
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
      craneList: '', // 塔吊列表
      craneName: '', // 设备名称
      deviceNo: '', //设备编号32位
      hxzId: '', // 设备编号
      craneName: '', // 设备名称
      deviceNo: '', // 设备编号32位
      hxzId: '', // 设备编号
      editTower: {}, // 编辑的塔吊
      userList: [], // 报警设置列表
      search: '', // 报警设置搜索
    };
  },
  mounted() {
    this.getCraneList()
    this.getCraneUserList()
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

    // 获取塔吊列表
    getCraneList() {
      this.$axios
        .post(`/api/OptionsElevatorApi/getElevatorList?projectId=${this.projectId}`)
        .then(res => {
          this.craneList = res.data.data
        })
    },

    // 删除塔吊
    deleteTower(id) {
      this.$confirm('此操作将永久删除该塔吊, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
            .post(`/api/OptionsElevatorApi/deleteElevator?id=${id}`)
            .then(res => {
              if (res.data.code == 0) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                });
                this.getCraneList()
              } else {
                this.$message({
                  message: '删除失败',
                  type: 'warning'
                });
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },

    // 添加设备提交
    insertCrane() {
      if (this.craneName == '') {
        this.$message({
          message: '请填写设备名称',
          type: 'warning'
        });
      } else if (this.hxzId == '') {
        this.$message({
          message: '设备SN为必填项',
          type: 'warning'
        });
      }
      //  else if (this.deviceNo == '') {
      //   this.$message({
      //     message: '设备SN（32位）为必填项',
      //     type: 'warning'
      //   });
      // }
      else {
        this.$axios
          .post(`/api/OptionsElevatorApi/insertElevator?elevatorName=${this.craneName}&hxzId=${this.hxzId}&projectId=${this.projectId}`)
          .then(res => {
            if (res.data.code == 0) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.getCraneList()
              this.craneName = ''
              this.hxzId = ''
              this.deviceNo = ''
              this.dialogShow = false
            } else {
              this.$message({
                message: '添加失败',
                type: 'warning'
              });
            }
          })
      }
    },

    // 点击编辑塔吊
    clickEdit1(item) {
      this.editTower = Object.assign({}, item)
    },

    // 编辑塔吊提交
    saveEdit() {
      if (this.editTower.elevatorName == '') {
        this.messageBox('设备名称为必填项', 0)
      } else if (this.editTower.hxzId == '') {
        this.messageBox('设备SN为必填项', 0)
      }
      //  else if (this.editTower.deviceNo == '') {
      //   this.messageBox('设备SN（32位）为必填项', 0)
      // } 
      else {
        this.$axios
          .post(`/api/OptionsElevatorApi/updateElevator?id=${this.editTower.id}&elevatorName=${this.editTower.elevatorName}&hxzId=${this.editTower.hxzId}`)
          .then(res => {
            if (res.data.code == 0) {
              this.messageBox('修改成功', 1)
              this.compileShow = false
              this.getCraneList()
            } else {
              this.messageBox('修改失败', 0)
            }
          })
      }
    },

    // 获取报警设置列表
    getCraneUserList() {
      this.$axios
        .post(`/api/OptionsElevatorApi/getElevatorUserList?projectId=${this.projectId}`)
        .then(res => {
          for (let i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].onOff == 0) {
              // res.data.data[i] = false
              this.$set(res.data.data[i], 'onOff', false)
            } else {
              // res.data.data[i] = true
              this.$set(res.data.data[i], 'onOff', true)
            }
          }
          this.userList = res.data.data
        })
    },

    // 开关点击
    switchClick(obj) {
      // console.log(obj.onOff)
      this.$axios
        .post(`/api/OptionsPushApi/optionsPush?privilegesId=8&userId=${obj.id}&onOff=${obj.onOff?1:0}`)
        .then(res => {
          if (res.data.code === 0) {
            this.messageBox(res.data.msg, 1)
          } else {
            this.messageBox('修改失败', 0)
          }
        })
    },

    // 获取当前数量
    // getIndex(num) {
    //   return num + 1 + 10 * (this.pageNum - 1)
    // },

    // 搜索
    searchClick() {
      this.$axios
        .post(`/api/OptionsElevatorApi/getElevatorUserList?projectId=${this.projectId}&filed=${this.search}`)
        .then(res => {
          if (res.data.code == 0) {
            for (let i = 0; i < res.data.data.length; i++) {
              if (res.data.data[i].onOff == 0) {
                // res.data.data[i] = false
                this.$set(res.data.data[i], 'onOff', false)
              } else {
                // res.data.data[i] = true
                this.$set(res.data.data[i], 'onOff', true)
              }
            }
            this.userList = res.data.data
          }
        })
    }
  }
};
</script>