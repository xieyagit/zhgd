<template>
  <div id="systemSet_equipmentSet_car">
    <div class="content-box">
      <!-- 头部 -->
      <div class="title">
        <div class="text">车辆设置</div>
      </div>
      <!-- 导航栏 -->
      <div class="nav">
        <a @click="navState=true" :class="navState?'active':''">设备管理</a>
        <a @click="navState=false" :class="navState?'':'active'">司机设置</a>
      </div>

      <!-- 设备管理 -->
      <!-- 功能 -->
      <div class="button" v-show="navState">
        <a @click="truckSpaceShow=true,pkcounts=pkcount" style="right:1.28rem">
          <i class="icon"></i>
          场内车位设置
        </a>
        <a @click="dialogClick">
          <i class="icon"></i>
          添加设备
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState">
        <ul>
          <li v-if="carList.length>0" v-for="item in carList" :key="item.deptID">
            <div class="left-box">
              <div class="name">{{item.deviceName}}</div>
              <div class="subcontract">
                <span>设备SN：</span>
                {{item.sn}}
              </div>
              <div class="location">
                <span>进出类型：</span>
                {{item.snName}}
              </div>
            </div>
            <div class="right-box">
              <!-- <a>人员设置</a> -->
              <a @click="editCars(item)">编辑</a>
              <a @click="deleteCar(item.id)">删除</a>
            </div>
          </li>
          <!-- <li>
            <div class="left-box">
              <div class="name">创新科技园东侧车辆管理系统</div>
              <div class="subcontract">
                <span>设备SN：</span>
                3892029349404043
              </div>
              <div class="location">
                <span>进出类型：</span>
                出
              </div>
            </div>
            <div class="right-box">
              <a>人员设置</a>
              <a @click="compileShow=true">编辑</a>
              <a>删除</a>
            </div>
          </li> -->
        </ul>
      </div>

      <!-- 报警设置 -->
      <!-- 功能 -->
      <div class="button" v-show="!navState">
        <!-- <div class="name">通知成员</div> -->
        <a @click="personnelShow=true">
          <i class="icon"></i>
          添加司机
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="!navState">
        <div class="table-box">
          <el-table :data="driverList" stripe border>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column type="index" label="序号" width="60"></el-table-column>
            <el-table-column prop="driver" label="驾驶员姓名"></el-table-column>
            <el-table-column prop="vehicle" label="车牌号"></el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <a class="table-button" @click="editDriver(scope.row)">编辑</a>
                <a class="table-button" @click="deleteDriver(scope.row.id)">删除</a>
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
            layout="total, prev, pager, next, jumper"
            :total="pageTotal"
          ></el-pagination>
        </div>
      </div>

      <!-- 新增设备 -->
      <div class="dialog-box" v-show="dialogShow">
        <div class="title">
          新增设备
          <a class="close" @click="dialogClick(),clearNewDialog()">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                停车场编号
                <div class="required">*</div>
              </span>
              <input type="text" :disabled="deptId!=''" v-model="deptId"/>
            </li>
            <li>
              <span>
                停车场名称
                <div class="required">*</div>
              </span>
              <input type="text" :disabled="carName!=''" v-model="carName"/>
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="text" v-model="sn"/>
            </li>
            <li>
              <span>
                进出类型
                <div class="required">*</div>
              </span>
              <input type="text" v-model="snName"/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="newCreate">保存</a>
        </div>
      </div>
      <!-- 编辑设备 -->
      <div class="dialog-box" v-show="compileShow">
        <div class="title">
          编辑设备
          <a class="close" @click="compileShow=false,editCar={}">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li v-if="editCar!={}">
              <span>
                设备名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editCar.deviceName"/>
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editCar.sn"/>
            </li>
            <li>
              <span>
                进出类型
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editCar.snName"/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="editCarClick">保存</a>
        </div>
      </div>
      <!-- 车位设置 -->
      <div class="dialog-box" v-show="truckSpaceShow">
        <div class="title">
          车位设置
          <a class="close" @click="truckSpaceShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                车位设置
                <div class="required">*</div>
              </span>
              <input type="number" v-model="pkcounts"/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="carUpd">保存</a>
        </div>
      </div>
      <!-- 添加司机 -->
      <div class="dialog-box" v-show="personnelShow">
        <div class="title">
          添加司机
          <a class="close" @click="personnelShow=false,createDriver='',createVehicle=''">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                司机名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="createDriver"/>
            </li>
            <li>
              <span>
                车牌号码
                <div class="required">*</div>
              </span>
              <input type="text" v-model="createVehicle"/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="insertDriver">保存</a>
        </div>
      </div>
      <!-- 编辑司机 -->
      <div class="dialog-box" v-show="driverEdit">
        <div class="title">
          编辑司机
          <a class="close" @click="driverEdit=false,editDriverObj={}">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                司机名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editDriverObj.driver"/>
            </li>
            <li>
              <span>
                车牌号码
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editDriverObj.vehicle"/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="updateDriver">保存</a>
        </div>
      </div>
      <!-- 遮罩层 -->
      <div class="shade-box" v-show="dialogShow || compileShow || personnelShow || truckSpaceShow || driverEdit"></div>
    </div>
  </div>
</template>

<style lang="less">
#systemSet_equipmentSet_car {
  width: 100%;
  .content-box {
    min-height: 7rem;
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    padding-left: 0.2rem;
    padding-bottom: 0.3rem;
    max-height: 9rem;
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
      navState: true, // 当前模块
      dialogShow: false, // 新增设备对话框
      compileShow: false, // 编辑对话框
      personnelShow: false, // 新增人员对话框
      truckSpaceShow: false, // 车位设置
      driverEdit: false, // 编辑驾驶员对话框
      pageSize: 15, // 每页条数
      pageNum: 1, // 当前页
      pageTotal: 0, // 总条数
      pageNum2: 1, // 每页条数
      pageTotal2: 2, // 总条数
      deptId: '', // 车场ID
      carList: [], // 车辆列表
      editCar: {}, // 编辑的车辆信息
      snName: '', // 进出类型
      carName: '', // 停车场名称
      sn: '', // 设备sn
      pkcount: '', // 车位数
      pkcounts: '', // 用于显示的车位数
      driverList: [], // 司机列表
      createDriver: '', // 新添加的司机
      createVehicle: '', // 新添加的车牌
      editDriverObj: {}, // 编辑司机的内容
    };
  },
  mounted() {
    this.getSelectpkcount()
    this.getDriverList()
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
      this.getDriverList()
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

    // 获取车辆列表
    getSelectpkcount() {
      this.$axios
        .post(`http://192.168.1.22:8080/api/parkings/selectpkcount?projectId=${this.projectId}`)
        .then(res => {
          this.carList = res.data.data
          this.deptId = res.data.data[0].deviceId
          this.carName = res.data.data[0].deviceName
          this.pkcount = res.data.data[0].pkcount
        })
    },

    // 编辑车辆
    editCars(item) {
      this.editCar = {}
      this.compileShow = true;
      this.editCar = JSON.parse(JSON.stringify(item))
    },

    // 删除车辆
    deleteCar(id) {
      this.$confirm('此操作将永久删除该系统, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
            .post(`http://192.168.1.22:8080//api/parkings/vehicleSnDel?id=${id}`)
            .then(res => {
              if (res.data.data == 0) {
                this.messageBox('删除成功', 1)
                this.getSelectpkcount()
              } else {
                this.messageBox('删除失败', 0)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },

    // 修改点击
    editCarClick() {
      this.$axios
        .post(`http://192.168.1.22:8080/api/parkings/vehicleSnUpd?id=${this.editCar.id}&sn=${this.editCar.sn}&snName=${this.editCar.snName}&carName=${this.editCar.deviceName}`)
        .then(res => {
          if (res.data.data == 0) {
            this.messageBox('修改成功', 1)
            this.compileShow = false
            this.editCar = {}
            this.getSelectpkcount()
          } else {
            this.messageBox('修改失败', 0)
          }
        })
    },

    // 添加设备
    newCreate() {
      if (this.carName == '') {
        this.messageBox('请填写停车场编号', 0)
      } else if (this.carName == '') {
        this.messageBox('请填写停车场名称', 0)
      } else if (this.sn == '') {
        this.messageBox('请填写设备SN', 0)
      } else if (this.snName == '') {
        this.messageBox('请填写进出类型', 0)
      } else {
        this.$axios
          .post(`http://192.168.1.22:8080/api/parkings/vehicleSnAdd?projectId=${this.projectId}&deptID=${this.deptId}&sn=${this.sn}&snName=${this.snName}&carName=${this.carName}`)
          .then(res => {
            if (res.data.data == 0) {
              this.messageBox('添加成功', 1)
              this.dialogShow = false;
              this.clearNewDialog();
              this.getSelectpkcount()
            } else {
              this.messageBox('添加失败', 0)
            }
          })
      }
    },

    // 清除设备管理弹窗内容
    clearNewDialog() {
      this.sn = ''
      this.snName = ''
      this.deptId = ''
      this.carName = ''
    },

    // 设置车位数
    carUpd() {
      if (this.pkcounts == '') {
        this.messageBox('请输入车位数', 0)
      } else {
        this.$axios
          .post(`http://192.168.1.22:8080/api/parkings/carUpd?deptId=${this.deptId}&pkcount=${this.pkcounts}`)
          .then(res => {
            if (res.data.data == 0) {
              this.messageBox('设置成功', 1)
              this.truckSpaceShow = false
              this.getSelectpkcount()
            } else {
              this.messageBox('设置失败', 0)
            }
          })
      }
    },

    // 获取司机列表
    getDriverList() {
      this.$axios
        .post(`http://192.168.1.22:8080/api/driver/selectAll?projectId=${this.projectId}&pageNum=${this.pageNum}&pageSize=${this.pageSize}`)
        .then(res => {
          this.driverList = res.data.data[0].rows
          this.pageTotal = res.data.data[0].total
        })
    },

    // 删除司机
    deleteDriver(id) {
      this.$confirm('此操作将永久删除该司机, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
            .post(`http://192.168.1.22:8080/api/driver/delDriver?id=${id}`)
            .then(res => {
              if (res.data.data == 0) {
                this.messageBox('删除成功', 1)
                this.getDriverList()
              } else {
                this.messageBox('删除失败', 0)
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },

    // 编辑司机
    editDriver(item) {
      this.editDriverObj = JSON.parse(JSON.stringify(item))
      this.driverEdit = true
    },

    // 添加司机
    insertDriver() {
      if (this.createDriver == '') {
        this.messageBox('请填写司机名称', 0)
      } else if (this.createVehicle == '') {
        this.messageBox('请填写车牌号码', 0)
      } else {
        this.$axios
          .post(`http://192.168.1.22:8080/api/driver/insertDriver?projectId=${this.projectId}&driver=${this.createDriver}&vehicle=${this.createVehicle}`)
          .then(res => {
            if (res.data.data == 0) {
              this.messageBox('添加成功', 1)
              this.personnelShow = false
              this.getDriverList()
              this.createDriver = ''
              this.createVehicle = ''
            } else {
              this.messageBox('添加失败', 0)
            }
          })
      }
    },

    // 编辑司机点击
    updateDriver() {
      if (this.editDriverObj.driver == '' || this.editDriverObj.vehicle == null) {
        this.messageBox('请填写司机名称', 0)
      } else if (this.editDriverObj.vehicle == '' || this.editDriverObj.vehicle == null) {
        this.messageBox('请填写车牌号码', 0)
      } else {
        this.$axios
          .post(`http://192.168.1.22:8080/api/driver/updateDriver?vehicle=${this.editDriverObj.vehicle}&driver=${this.editDriverObj.driver}&id=${this.editDriverObj.id}`)
          .then(res => {
            if (res.data.data == 0) {
              this.messageBox('编辑成功', 1)
              this.editDriverObj = {}
              this.driverEdit = false
              this.getDriverList()
            } else {
              this.messageBox('编辑失败', 0)
            }
          })
      }
    }
  }
};
</script>