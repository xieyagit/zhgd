<template>
  <div id="systemSet_equipmentSet_location">
    <div class="content-box">
      <!-- 头部 -->
      <div class="title">
        <div class="text">定位设置</div>
      </div>
      <!-- 导航栏 -->
      <div class="nav">
        <a @click="navState=true" :class="navState?'active':''">工区设置</a>
        <a @click="navState=false" :class="navState?'':'active'">人员设置</a>
      </div>

      <!-- 设备管理 -->
      <!-- 功能 -->
      <div class="button" v-show="navState">
        <a @click="dialogClick">
          <i class="icon"></i>
          添加工区
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState">
        <ul>
          <li v-for="item in gradenList" :key="item.constructionId">
            <div class="left-box">
              <div class="name">{{ item.areaName }}</div>
              <div class="location">
                <span>地址：</span>
                {{ item.areaAddress }}
              </div>
              <div class="subcontract">
                <span>所属参建单位：</span>
                {{ item.constructionName }}
              </div>
            </div>
            <div class="right-box">
              <a @click="cameraShow=true;getAreaUserList(item)">查看人员</a>
              <a @click="compileShow=true;openEditArea(item)">编辑</a>
              <a @click="deleteArea(item)">删除</a>
            </div>
          </li>
        </ul>
      </div>

      <!-- 人员设置 -->
      <!-- 功能 -->
      <div class="button" v-show="!navState">
        <!-- <div class="name">通知成员</div> -->
        <div class="input-box">
          <input type="text" placeholder="请输入姓名、工区搜索" v-model="search" @keydown.enter="getAreaUserList2" @blur="getAreaUserList2">
          <a class="el-icon-search" @click="getAreaUserList2"></a>
        </div>
        <a @click="personnelShow=true">
          <i class="icon"></i>
          添加人员
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="!navState">
        <div class="table-box">
          <el-table :data="userList" stripe border>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="序号" width="60">
              <template slot-scope="scope">
                {{ indexNum(scope.$index) }}
              </template>
            </el-table-column>
            <el-table-column prop="userName" label="姓名"></el-table-column>
            <el-table-column prop="areaName" label="所属工区"></el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <a class="table-button" @click="modificationShow=true,editUser(scope.row)">编辑</a>
                <a class="table-button" @click="deleteUser(scope.row)">删除</a>
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
          <a class="close" @click="dialogClick(),longitude='',latitude='',range='',areaName='',constructionName='',areaAddress=''">
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
              <input type="text" v-model="areaName">
            </li>
            <li>
              <span>
                所属位置
                <div class="required">*</div>
              </span>
              <input type="text" v-model="areaAddress">
            </li>
            <li>
              <span>
                所属参建单位
                <div class="required">*</div>
              </span>
              <!-- <input type="text" v-model="constructionName"> -->
              <el-select v-model="constructionName" placeholder="请选择所属参见单位" clearable>
                <el-option
                  v-for="item in informationList"
                  :key="item.constructionId"
                  :label="item.constructionName"
                  :value="item.constructionId"
                ></el-option>
              </el-select>
            </li>
            <li class="rail">
              <span>
                选择位置
                <div class="required">*</div>
              </span>
              <div class="text-box">
                经度：{{longitude}}
                &nbsp;
                纬度：{{latitude}}
              </div>
              <div class="scope">
                范围
                <input type="number" placeholder="默认100米" v-model="range">
              </div>
            </li>
            <li class="map-box">
              <el-amap
                ref="map"
                vid="amapDemo"
                :amap-manager="amapManager"
                :center="center"
                :zoom="zoom"
                :events="events"
                class="amap-demo"
              ></el-amap>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="saveArea">保存</a>
        </div>
      </div>
      <!-- 编辑工区 -->
      <div class="dialog-box" v-show="compileShow">
        <div class="title">
          编辑工区
          <a class="close" @click="compileShow=false">
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
              <input type="text" v-model="editList.areaName">
            </li>
            <li>
              <span>
                所属位置
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editList.areaAddress">
            </li>
            <li>
              <span>
                所属参建单位
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editList.constructionName">
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="editArea">保存</a>
        </div>
      </div>
      <!-- 查看人员 -->
      <div class="dialog-box" v-show="cameraShow">
        <div class="title">
          人员设置
          <a class="close" @click="cameraShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <div class="search-box">
            <div class="text-box" style="font-size:.18rem;line-height:.42rem;">{{ userSet }}</div>
          </div>
          <div class="table-box">
            <el-table :data="userSetList" stripe border>
              <el-table-column type="selection" width="40"></el-table-column>
              <el-table-column label="序号" width="60">
                <template slot-scope="scope">
                  {{ indexNum(scope.$index) }}
                </template>
              </el-table-column>
              <el-table-column prop="userName" label="名称"></el-table-column>
              <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                  <a class="table-button" @click="deleteAreaUser(scope.row)">删除</a>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="paging-box">
            <el-pagination
              @current-change="currentChange"
              :current-page="currentPage"
              layout="total, prev, pager, next, jumper"
              :total="total"
            ></el-pagination>
          </div>
        </div>
        <div class="confirm">
          <a class="button">确定</a>
        </div>
      </div>
      <!-- 添加人员 -->
      <div class="dialog-box" v-show="personnelShow">
        <div class="title">
          添加人员
          <a class="close" @click="personnelShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                人员名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="user_Name">
            </li>
            <li>
              <span>
                所属工区
                <div class="required">*</div>
              </span>
              <!-- <input type="text" /> -->
              <el-select v-model="user_areaId" placeholder="请选择所属参见单位" clearable>
                <el-option
                  v-for="item in gradenList"
                  :key="item.areaId"
                  :label="item.areaName"
                  :value="item.areaId"
                ></el-option>
              </el-select>
            </li>
            <li>
              <span>
                绑定设备
                <div class="required">*</div>
              </span>
              <input type="text" v-model="user_imei">
            </li>
            <li>
              <span>电话</span>
              <input type="text" v-model="user_userPhone">
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="addAreaUser">保存</a>
        </div>
      </div>
      <!-- 编辑人员 -->
      <div class="dialog-box" v-show="modificationShow">
        <div class="title">
          编辑人员
          <a class="close" @click="modificationShow=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                人员名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editList2.userName">
            </li>
            <li>
              <span>
                所属工区
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editList2.areaName">
            </li>
            <li>
              <span>
                绑定设备
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editList2.imei">
            </li>
            <li>
              <span>电话</span>
              <input type="text" v-model="editList2.userPhone">
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="clickEdit">保存</a>
        </div>
      </div>
      <!-- 遮罩层 -->
      <div
        class="shade-box"
        v-show="dialogShow || compileShow || personnelShow || cameraShow || modificationShow"
      ></div>
    </div>
  </div>
</template>

<style lang="less">
#systemSet_equipmentSet_location {
  width: 100%;
  .content-box {
    min-height: 7rem;
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    padding-left: 0.2rem;
    padding-bottom: 0.3rem;
    max-height: 9rem;
    overflow-y: auto;
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
      > a {
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
      > .input-box {
        width: 4rem;
        height: 0.4rem;
        overflow: hidden;
        padding-left: 0.16rem;
        border-radius: 0.04rem;
        display: inline-block;
        border: 0.01rem solid #b6b6b6;
        transform: translateY(0.15rem);
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
      top: 1.1rem;
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
              width: 1.44rem;
              height: 0.41rem;
              font-size: 0.16rem;
              text-align: right;
              position: relative;
              line-height: 0.41rem;
              padding-right: 0.18rem;
              display: inline-block;
              .required {
                top: -0.01rem;
                right: 0.1rem;
                color: #f00;
                position: absolute;
              }
            }
            input {
              width: 4.8rem;
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
          }
          .uploading {
            height: 2.4rem;
            > a {
              width: 4.8rem;
              height: 2.1rem;
              display: inline-block;
              border-radius: 0.02rem;
              border: 0.01rem solid #b1b1b1;
            }
          }
          .rail {
            position: relative;
            .text-box {
              padding-left: 0.05rem;
              color: #333;
              font-size: 0.18rem;
              line-height: 0.41rem;
            }
            .scope {
              position: absolute;
              right: 0.6rem;
              input {
                width: 1.1rem;
              }
            }
          }
          .map-box {
            width: 5.7rem;
            height: 2.26rem;
            margin: 0 auto;
            > div {
              width: 100%;
              height: 100%;
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
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
      -webkit-appearance: none;
    }
    input[type="number"]{
      -moz-appearance: textfield;
    }
  }
}
</style>

<script>
import mixin from '@/utils/mixin'
let amapManager = new VueAMap.AMapManager();
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
          name: "某某某工区摄像头", // 姓名
          workArea: "创新工业园" // 所属工区
        }
      ], // 添加人员列表数据
      tableData3: [
        {
          number: 1, // 序号
          name: "某某某", // 名称
          id: 1 // id
        },
        {
          number: 2, // 序号
          name: "某某某", // 名称
          id: 2 // id
        }
      ], // 人员设置设置列表
      navState: true, // 当前模块
      dialogShow: false, // 添加工区对话框
      compileShow: false, // 编辑工区对话框
      personnelShow: false, // 添加人员对话框
      cameraShow: false, // 人员设置设置对话框
      modificationShow: false, // 编辑人员对话框
      pageSize: 15, // 每页条数
      pageNum: 1, // 当前页
      pageTotal: 2, // 总条数
      pageNum2: 1, // 每页条数
      pageTotal2: 2, // 总条数
      pageNum3: 1, // 每页条数
      pageTotal3: 2, // 总条数
      amapManager,
      // amapManager2,
      zoom: 12,
      center: [114.014129, 22.571492],
      events: {
        init: o => {},
        moveend: () => {},
        zoomchange: () => {},
        click: e => {
          // console.log(e.lnglat)
          this.longitude = e.lnglat.lng;
          this.latitude = e.lnglat.lat;
          // console.log(this.longitude+':'+this.latitude)
        }
      },
      longitude: "", // 经度
      latitude: "", // 纬度
      gradenList: '', // 园区列表
      userSet: '', // 人员设置拿到的工区信息
      userSetList: [], // 人员设置列表
      areaId: '', // 点击修改的工业区Id
      editList: {}, // 编辑的工区内容
      areaAddress: '', // 工区地址
      areaName: '', // 工区名称
      constructionName: '', // 参建单位名称
      informationList: '', // 参建单位列表
      range: '', // 范围
      search: '', // 搜索栏内容
      userList: [], // 人员设置列表
      editList2: [], // 人员设置编辑
      user_Name: '', // 添加人员
      user_areaId: '', // 添加工区
      user_imei: '', // 添加人员设备编号
      user_userPhone: '' // 添加人员电话
    };
  },
  mounted() {
    this.getProjectId()
    this.getGradenList()
    this.getInformationList()
    this.getAreaUserList()
    this.getAreaUserList2()
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
      this.pageNum2 = val;
    },

    // 当前页
    handleCurrentChange3(val) {
      // console.log(`当前页: ${val}`)
      this.pageNum3 = val;
    },

    // 新增对话框状态切换
    dialogClick() {
      this.dialogShow = !this.dialogShow;
    },

    // 获取园区列表
    getGradenList() {
      this.$axios
        .post(`/api/OptionsLocationApi/getAreaList?projectId=${this.projectId}`)
        .then(res => {
          this.gradenList = res.data.data
        })
    },

    // 获取园区人员
    getAreaUserList(item) {
      if (item) {
        this.userSet = item.areaName
        this.areaId = item.areaId
        this.$axios
          .post(`/api/OptionsLocationApi/getAreaUserList?areaId=${item.areaId}&projectId=${this.projectId}&pageNum=${this.currentPage}&pageSize=10`)
          .then(res => {
            this.userSetList = res.data.data
            this.total = res.data.total
          })
      } else {
        this.$axios
          .post(`/api/OptionsLocationApi/getAreaUserList?areaId=${this.areaId}&projectId=${this.projectId}&pageNum=${this.currentPage}&pageSize=10`)
          .then(res => {
            this.userSetList = res.data.data
            this.total = res.data.total
          })
      }
    },

    // 查看人员翻页
    currentChange(val) {
      this.currentPage = val
      this.getAreaUserList()
    },

    // 删除人员
    deleteAreaUser(item) {
      this.$axios
        .post(`/api/OptionsLocationApi/deleteAreaUser?userId=${item.userId}&areaId=${item.areaId}`)
        .then(res => {
          if (res.data.code === 0) {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.getAreaUserList()
          } else {
            this.$message({
              type: 'warning',
              message: '删除失败'
            })
          }
        })
    },

    // 人员设置的数量
    indexNum(num) {
      return num + 1 + (this.currentPage-1) * 10
    },

    // 编辑工区点击
    openEditArea(item) {
      this.editList = Object.assign({}, item)
    },

    // 编辑工区提交
    editArea() {
      if (this.editList.areaName == '') {
        this.$message({
          message: '工区名称为必填项',
          type: 'warning'
        })
      } else if (this.editList.areaAddress == '') {
        this.$message({
          message: '所属位置为必填项',
          type: 'warning'
        })
      } else if (this.editList.constructionName == '') {
        this.$message({
          message: '所属参建单位为必填项',
          type: 'warning'
        })
      } else {
        this.$axios
          .post(`/api/OptionsLocationApi/updateArea?areaName=${this.editList.areaName}&areaId=${this.editList.areaId}&areaAddress=${this.editList.areaAddress}&constructionId=${this.editList.constructionId}&constructionName=${this.editList.constructionName}`)
          .then( res => {
            debugger
            if (res.data.code == 0) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.editList = {}
              this.compileShow = false
            } else {
              this.$message({
                message: '修改失败',
                type: 'warning'
              })
            }
          })
      }
    },

    // 删除工业区
    deleteArea(item) {
      console.log(item)
      this.$confirm('此操作将永久删除该工业区, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
            .post(`/api/OptionsLocationApi/deleteArea?areaId=${item.areaId}`)
            .then(res => {
              if (res.data.code == 0) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.getGradenList()
              } else {
                this.$message({
                  type: 'warning',
                  message: '删除失败!'
                });
              }
            })
        }).catch(() => {
          this.$message({
            type: 'warning',
            message: '已取消删除'
          });
        });
    },

    // 查询参建单位列表
    getInformationList() {
      this.$axios
        .post(`/api/safetyPcApi/getInformationList?projectId=${this.projectId}`)
        .then(res => {
          this.informationList = res.data.data
        })
    },

    // 添加工业区
    saveArea() {
      if (this.areaName == '') {
        this.$message({
          message: '请填写工业区名称',
          type: 'warning'
        })
      } else if (this.areaAddress == '') {
        this.$message({
          message: '请填写所属位置',
          type: 'warning'
        })
      } else if (this.constructionName == '') {
        this.$message({
          message: '请选择所属参建单位',
          type: 'warning'
        })
      } else if (this.longitude == '' || this.latitude == '') {
        this.$message({
          message: '请选择位置',
          type: 'warning'
        })
      } else {
        if (this.range == '') {
          this.range = 100
        }
        this.$axios
          .post(`/api/OptionsLocationApi/addArea?projectId=${this.projectId}&areaAddress=${this.areaAddress}&areaName=${this.areaName}&constructionId=${this.constructionName}&areaXloc=${this.longitude}&areaYloc=${this.latitude}&radius=${this.range}`)
          .then(res => {
            if (res.data.code === 0) {
              this.$message({
                message: '添加成功',
                type: 'success'
              })
              this.dialogShow = false
              this.areaAddress = ''
              this.areaName = ''
              this.constructionName = ''
              this.longitude = ''
              this.latitude = ''
              this.range = ''
              this.getGradenList()
            } else {
              this.$message({
                message: '添加失败',
                type: 'warning'
              })
            }
          })
      }
    },

    // 获取人员设置列表
    getAreaUserList2() {
      this.$axios
        .post(`/api/OptionsLocationApi/getAreaUserList?projectId=${this.projectId}&pageNum=${this.pageNum2}&pageSize=${this.pageSize}&filed=${this.search}`)
        .then(res => {
          this.userList = res.data.data
        })
    },

    // 删除人员(人员设置)
    deleteUser(item) {
      this.$confirm('此操作将永久删除该人员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
            .post(`/api/OptionsLocationApi/deleteAreaUser?areaId=${item.areaId}&userId=${item.userId}`)
            .then(res => {
              if (res.data.code == 0) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.getAreaUserList2()
              } else {
                this.$message({
                  type: 'warning',
                  message: '删除失败!'
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

    // 编辑人员（人员设置）
    editUser(item) {
      this.editList2 = Object.assign({}, item)
    },

    // 编辑人员提交
    clickEdit() {
      if (this.editList2.userName == '') {
        this.$message({
          type: 'warning',
          message: '人员名称为必填项'
        });
      } else if (this.editList2.areaName == '') {
        this.$message({
          type: 'warning',
          message: '所属工区为必填项'
        });
      } else if (this.editList2.imei == '') {
        this.$message({
          type: 'warning',
          message: '绑定设置为必填项'
        });
      } else {
        this.notNull(this.editList2)
        this.$axios
          .post(`/api/OptionsLocationApi/updateAreaUser?areaId=${this.editList2.areaId}&userName=${this.editList2.userName}&userPhone=${this.editList2.userPhone}&imei=${this.editList2.imei}&userId=${this.editList2.userId}`)
          .then(res => {
            if (res.data.code == 0) {
              this.$message({
                type: 'success',
                message: '编辑成功'
              });
              this.modificationShow = false
              this.getAreaUserList2()
            } else {
              this.$message({
                type: 'warning',
                message: '编辑失败'
              });
            }
          })
      }
    },

    // 新增人员
    addAreaUser() {
      if (this.user_Name == '') {
        this.$message({
          message: '人员名称为必填项',
          type: 'warning'
        });
      } else if (this.user_areaId == '') {
        this.$message({
          message: '所属工区为必填项',
          type: 'warning'
        });
      } else if (this.user_imei == '') {
        this.$message({
          message: '绑定设备为必填项',
          type: 'warning'
        });
      } else {
        this.$axios
          .post(`/api/OptionsLocationApi/addAreaUser?areaId=${this.user_areaId}&userName=${this.user_Name}&imei=${this.user_imei}&userPhone=${this.user_userPhone}`)
          .then(res => {
            if (res.data.code == 0) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.personnelShow = false
              this.getAreaUserList2()
            } else {
              this.$message({
                message: '添加失败',
                type: 'warning'
              });
            }
          })
      }
    }
  }
};
</script>