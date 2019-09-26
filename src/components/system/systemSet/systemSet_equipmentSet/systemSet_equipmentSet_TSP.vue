<template>
  <div id="systemSet_equipmentSet_TSP">
    <div class="content-box">
      <!-- 头部 -->
      <div class="title">
        <div class="text">TSP设置</div>
      </div>
      <!-- 导航栏 -->
      <div class="nav">
        <a @click="navState=1" :class="navState==1?'active':''">设备管理</a>
        <a @click="navState=2" :class="navState==2?'active':''">阀值设置</a>
        <a @click="navState=3" :class="navState==3?'active':''">报警设置</a>
      </div>

      <!-- 设备管理 -->
      <!-- 功能 -->
      <div class="button" v-show="navState==1">
        <a @click="dialogClick()">
          <i class="icon"></i>
          添加设备
        </a>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState==1">
        <ul>
          <li v-for="(item,index) in equipmentListData" :key="index">
            <div class="left-box">
              <div class="name">{{item.comments}}</div>
              <div class="subcontract">
                <span>设备SN：</span>
                {{item.sn}}
              </div>
              <div class="location">
                <span>视频流：</span>
                {{item.videoAddress}}
              </div>
              <div class="location">
                <span>设备厂商：</span>
                {{getManufacturerName(item.manufacturerId)}}
              </div>
              <div class="location">
                <span>上传平台：</span>
                {{geScznlName(item.scznl)}}
              </div>
            </div>
            <div class="right-box">
              <!-- <a @click="examine(item.id)">查看</a> -->
              <a @click="getEdit(item.id)">编辑</a>
              <a @click="removeClick(item)">删除</a>
            </div>
          </li>
        </ul>
        <div class="paging-box">
          <el-pagination
            @current-change="handleCurrentChange3"
            :current-page="pageNum3"
            :page-size="pageSize3"
            layout="total, prev, pager, next, jumper"
            :total="pageTotal3"
          ></el-pagination>
        </div>
      </div>

      <!-- 阀值设置 -->
      <!-- 功能 -->
      <div class="button" v-show="navState==2">
        <div class="name">阀值设置</div>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState==2">
        <ul class="threshol">
          <li>
            <div class="text-box">PM2.5</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="pm25" />
            </div>
          </li>
          <li>
            <div class="text-box">PM10</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="pm10" />
            </div>
          </li>
          <li>
            <div class="text-box">TSP</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="tsp" />
            </div>
          </li>
          <li>
            <div class="text-box">噪音</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="noise" />
            </div>
          </li>
          <li>
            <div class="text-box">温度</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="temperature" />
            </div>
          </li>
          <li>
            <div class="text-box">湿度</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="humidity" />
            </div>
          </li>
          <li>
            <div class="text-box">风速</div>
            <div class="input-box">
              <span>最大阀值：</span>
              <input type="number" v-model="windSpeed" />
            </div>
          </li>
        </ul>
        <a class="confirm" @click="updateThresholdValue">确定</a>
      </div>

      <!-- 报警设置 -->
      <!-- 功能 -->
      <div class="button" v-show="navState==3">
        <div class="name">通知成员</div>
        <!-- <a @click="personnelShow=true">
          <i class="icon"></i>
          添加人员
        </a> -->
        <i class="el-icon-search" @click="searchClick"></i>
        <el-input class="search" v-model="search" placeholder="输入姓名、账号搜索" @keydown.enter.native="searchClick"></el-input>
      </div>
      <!-- 主体 -->
      <div class="main" v-show="navState==3">
        <div class="table-box">
          <el-table :data="tspList" stripe border>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column type="index" label="序号" width="60"></el-table-column>
            <el-table-column prop="userAccount" label="账号"></el-table-column>
            <el-table-column prop="userName" label="姓名"></el-table-column>
            <el-table-column prop="userPhone" label="手机号"></el-table-column>
            <!-- <el-table-column prop="post" label="岗位"></el-table-column>
            <el-table-column prop="company" label="单位"></el-table-column> -->
            <el-table-column label="通知开关" width="100">
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
          <a class="close" @click="dialogClick">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form">
          <ul>
            <li>
              <span>
                对接平台
                <div class="required">*</div>
              </span>
              <el-select v-model="changeUnit" @change="getCay">
                <el-option
                  v-for="item in uploadUnit"
                  :key="item.tag"
                  :label="item.name"
                  :value="item.tag"
                ></el-option>
              </el-select>
            </li>
            <li>
              <span>
                设备厂家
                <div class="required">*</div>
              </span>
              <el-select v-model="manufacturers">
                <el-option
                  v-for="item in manufacturersList"
                  :key="item.id"
                  :label="item.manufacturerName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </li>
            <li v-if="changeUnit=='CAY' || changeUnit=='RCAJ'">
              <span>
                项目监督编号
                <div class="required">*</div>
              </span>
              <input type="text" v-model="superviseNum" :disabled="changeUnit=='CAY'"/>
            </li>
            <li v-if="changeUnit=='CAY' || changeUnit=='RCAJ'">
              <span>
                项目编号
                <div class="required">*</div>
              </span>
              <input type="text" v-model="itemsId" :disabled="changeUnit=='CAY'"/>
            </li>
            <li>
              <span>
                设备名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="addComments" />
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="text" v-model="addSn" />
            </li>
            <li>
              <span>
                安装时间
                <div class="required"></div>
              </span>
              <el-date-picker type="date" placeholder="选择日期" v-model="deviceInstallationDate" value-format="yyyy-MM-dd hh:mm:ss"></el-date-picker>
            </li>
            <li>
              <span>
                视频流
                <div class="required"></div>
              </span>
              <input type="text" v-model="addVideoAddress" />
            </li>
            <li>
              <span>
                监测项
                <div class="required"></div>
              </span>
              <input type="text" v-model="meOption" />
            </li>
            <li>
              <span>
                安装位置
                <div class="required"></div>
              </span>
              <input type="text" v-model="installAddress" />
            </li>
            <li>
              <span>
                设备安装单位
                <div class="required"></div>
              </span>
              <input type="text" v-model="installCompany" />
            </li>
          </ul>
          <!-- <ul>
            <li>
              <span>
                上传单位
              </span>
              <el-select v-model="addComments" @change="initChange">
                <el-option
                  v-for="item in uploadUnit"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </li>
            <div v-if="addUnitList.length>0">
              <li v-for="(item, index) in addUnitList" :key="item.value">
                <span>
                  {{item.name}}
                  <div class="required" v-if="item.must">*</div>
                </span>
                <input type="number" v-model="uploadData[index]" v-if="item.typeName == 'facilitySN'">
                <el-date-picker type="date" placeholder="选择日期" v-model="uploadData[index]" value-format="yyyy-MM-dd hh:mm:ss" v-else-if="item.typeName == 'installTime'"></el-date-picker>
                <input type="text" v-model="uploadData[index]" v-else>
              </li>
            </div>
          </ul> -->
        </div>
        <div class="confirm">
          <a class="button" @click="addSave">保存</a>
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
                对接平台
                <div class="required">*</div>
              </span>
              <el-select v-model="editChangeUnit" @change="getCay">
                <el-option
                  v-for="item in uploadUnit"
                  :key="item.tag"
                  :label="item.name"
                  :value="item.tag"
                ></el-option>
              </el-select>
            </li>
            <li>
              <span>
                设备厂家
                <div class="required">*</div>
              </span>
              <el-select v-model="editManufacturers">
                <el-option
                  v-for="item in manufacturersList"
                  :key="item.id"
                  :label="item.manufacturerName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </li>
            <li v-if="editChangeUnit=='CAY' || editChangeUnit=='RCAJ'">
              <span>
                项目监督编号
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editSuperviseNum" :disabled="editChangeUnit=='CAY'"/>
            </li>
            <li v-if="editChangeUnit=='CAY' || editChangeUnit=='RCAJ'">
              <span>
                项目编号
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editItemsId" :disabled="editChangeUnit=='CAY'"/>
            </li>
            <li>
              <span>
                设备名称
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editComments" />
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editSn" />
            </li>
            <li>
              <span>
                安装时间
                <div class="required"></div>
              </span>
              <!-- <input type="text" v-model="deviceInstallationDate" /> -->
              <el-date-picker type="date" placeholder="选择日期" v-model="editDeviceInstallationDate" value-format="yyyy-MM-dd hh:mm:ss"></el-date-picker>
            </li>
            <li>
              <span>
                视频流
                <div class="required"></div>
              </span>
              <input type="text" v-model="editVideoAddress" />
            </li>
            <li>
              <span>
                监测项
                <div class="required"></div>
              </span>
              <input type="text" v-model="editMeOption" />
            </li>
            <li>
              <span>
                安装位置
                <div class="required"></div>
              </span>
              <input type="text" v-model="editInstallAddress" />
            </li>
            <li>
              <span>
                设备安装单位
                <div class="required"></div>
              </span>
              <input type="text" v-model="editInstallCompany" />
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="editClick">保存</a>
        </div>
      </div>
      <!-- 查看设备 -->
      <div class="dialog-box" v-show="compileShow2">
        <div class="title">
          编辑设备
          <a class="close" @click="compileShow2=false">
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
              <input type="text" v-model="editComments" disabled/>
            </li>
            <li>
              <span>
                设备SN
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editSn" disabled/>
            </li>
            <li>
              <span>
                项目监督编号
                <div class="required">*</div>
              </span>
              <input type="text" v-model="editSuperviseNum" disabled/>
            </li>
            <li>
              <span>
                安装时间
                <div class="required"></div>
              </span>
              <!-- <input type="text" v-model="deviceInstallationDate" /> -->
              <el-date-picker type="date" placeholder="选择日期" v-model="editDeviceInstallationDate" disabled></el-date-picker>
            </li>
            <li>
              <span>
                视频流
                <div class="required"></div>
              </span>
              <input type="text" v-model="editVideoAddress" disabled/>
            </li>
            <li>
              <span>
                监测项
                <div class="required"></div>
              </span>
              <input type="text" v-model="editMeOption" disabled/>
            </li>
            <li>
              <span>
                安装位置
                <div class="required"></div>
              </span>
              <input type="text" v-model="editInstallAddress" disabled/>
            </li>
            <li>
              <span>
                设备安装单位
                <div class="required"></div>
              </span>
              <input type="text" v-model="editInstallCompany" disabled/>
            </li>
          </ul>
        </div>
        <div class="confirm">
          <a class="button" @click="compileShow2 = false">关闭</a>
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
      <!-- 删除确定时间 -->
      <div class="dialog-box" v-show="personnelShow3" style="width:5rem">
        <div class="title">
          移除时间
          <a class="close" @click="personnelShow3=false">
            <i class="el-icon-close"></i>
          </a>
        </div>
        <div class="form3">
          <el-date-picker
            v-model="deleteTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </div>
        <div class="confirm">
          <a class="button" @click="deleteCAY">确定</a>
        </div>
      </div>
      <!-- 遮罩层 -->
      <div class="shade-box" v-show="dialogShow || compileShow || personnelShow || compileShow2 || personnelShow3"></div>
    </div>
  </div>
</template>

<style lang="less">
#systemSet_equipmentSet_TSP {
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
      position: relative;
      > ul {
        width: 100%;
        min-height: 5.6rem;
        li {
          width: 100%;
          height: 2rem;
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
              line-height: 2rem;
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
      .threshol {
        display: flex;
        min-height: 0;
        flex-wrap: wrap;
        justify-content: space-between;
        li {
          height: 0.97rem;
          width: 7.98rem;
          border: 0.01rem solid #c5e8ff;
          .text-box {
            height: 0.42rem;
            color: #4a4a4a;
            font-size: 0.17rem;
            line-height: 0.42rem;
            font-weight: bolder;
          }
          .input-box {
            height: 0.44rem;
            span {
              color: #7b7b7b;
              font-size: 0.16rem;
              line-height: 0.44rem;
            }
            input {
              width: 3rem;
              height: 0.4rem;
              border-radius: 0.02rem;
              border: 0.01rem solid #b6b6b6;
              padding-left: 0.1rem;
            }
          }
        }
      }
      .confirm {
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
    .dialog-box {
      left: 50%;
      top: 0.5rem;
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
            .el-input.is-disabled .el-input__inner {
              background: #ebebe4;
              cursor: auto;
            }
          }
          > div {
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
            .el-input.is-disabled .el-input__inner {
              background: #ebebe4;
              cursor: auto;
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
      .form3 {
        padding: 0.4rem;
        .el-date-editor.el-input, .el-date-editor.el-input__inner {
          width: 100%;
          input {
            border: 1px solid rgb(146, 146, 146);
            border-radius: 5px;
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
      projectId: 0, // 项目id
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
      navState: 1, // 当前模块
      dialogShow: false, // 新增设备对话框
      compileShow: false, // 编辑对话框
      personnelShow: false, // 新增人员对话框
      pageSize: 15, // 每页条数
      pageNum: 1, // 当前页
      pageTotal: 2, // 总条数
      pageNum2: 1, // 当前页
      pageTotal2: 2, // 总条数
      pageNum3: 1, // 设备列表当前页
      pageSize3: 4, // 设备列表每页条数
      pageTotal3: 0, // 设备列表总条数
      equipmentListData: "", // 设备列表
      addComments: "", // 新增设备设备名称
      addSn: "", // 新增设备设备SN编号
      addVideoAddress: "", // 新增设备视频地址
      editComments: "", // 编辑设备设备名称
      editSn: "", // 编辑设备设备SN编号
      editVideoAddress: "", // 编辑设备视频地址
      selectId: "", // 当前编辑设备的id
      pm25: "", // pm2.5阀值
      pm10: "", // pm10阀值
      tsp: "", // tsp阀值
      noise: "", // 噪音阀值
      temperature: "", // 温度阀值
      humidity: "", // 湿度阀值
      windSpeed: "", // 风速阀值
      id: "", // 阀值id
      tspList: [], // tep列表
      meOption: '', //监测项
      installAddress: '', // 安装位置
      installCompany: '', // 安装单位
      deviceInstallationDate: '', // 安装时间
      editDeviceInstallationDate: '', // 编辑安装时间
      editMeOption: '', // 检测项编辑
      editInstallAddress: '', // 安装位置编辑
      editInstallCompany: '', // 设备安装单位编辑
      compileShow2: '', //查看设备弹窗
      search: '', // 搜索内容
      superviseNum: '', // 监督编号
      editSuperviseNum: '', // 编辑监督编号
      uploadUnit: [ // 设备上传位置下拉选框
        // {
        //   name: '住建局',
        //   tag: 'HOUS'
        // }, {
        //   name: '市政总',
        //   tag: 'MUNICIPAL'
        // }, {
        //   name: '工务署',
        //   tag: 'WORKSBUREAU'
        // }, {
        //   name: '深圳市城安院',
        //   tag: 'CAY'
        // }, {
        //   name: '人才安居',
        //   tag: 'RCAJ'
        // }, {
        //   name: '其他位置',
        //   tag: 'QTWZ'
        // }
      ],
      changeUnit: '', // 上传位置选择
      itemsId: '', // 项目编号
      sub_id: '', // 工程ID
      manufacturers: '', // 设备厂家
      manufacturersList: [], // 设备厂家列表
      editChangeUnit: '', // 修改对接平台
      editManufacturers: '', //修改设备厂家
      editSuperviseNum: '', // 修改项目监督编号
      editItemsId: '', // 修改项目监督编号
      deleteTime: '', // 删除时间
      personnelShow3: false, // 删除时间弹窗
      deleteId: '', // 删除的id
    };
  },
  created() {
    this.getProjectId();
    this.getEquipmentList();
    this.getThresholdValue();
    this.getTSP()
    this.initChange()
    this.getManufacturer()
    this.getCay()
  },
  methods: {
    // 获取项目id
    getProjectId() {
      this.projectId = sessionStorage.getItem("pid");
    },

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

    // 设备列表当前页
    handleCurrentChange3(val) {
      // console.log(`当前页: ${val}`)
      this.pageNum3 = val;
      this.getEquipmentList();
    },

    // 新增对话框状态切换
    dialogClick() {
      this.dialogShow = !this.dialogShow;
      this.addComments = "";
      this.addSn = "";
      this.addVideoAddress = "";
      this.deviceInstallationDate = ''
      this.meOption = ''
      this.installAddress = ''
      this.installCompany = ''
      this.superviseNum = ''
      this.changeUnit = ''
      this.itemsId = ''
      this.sub_id = ''
      this.manufacturers = ''
    },

    // 获取设备列表
    getEquipmentList() {
      this.$axios
        .post(
          `http://192.168.1.22:8080/api/ProjectDustEmission/projectDustEmissionList?projectId=${this.projectId}&pageSize=${this.pageSize3}&pageNum=${this.pageNum3}`
        )
        .then(res => {
          // console.log(res.data)
          this.pageTotal3 = res.data.data.total;
          this.equipmentListData = res.data.data.rows;
        });
    },

    // 获取上传单位列表
    initChange() {
      this.$axios
        .post(`http://192.168.1.22:8083/provider/dictionariesApi/cxdjpt?category=PLATFORM`)
        .then(res => {
          this.uploadUnit = res.data.data
        })
    },

    // 获取厂家列表
    getManufacturer() {
      this.$axios
        .post(`http://192.168.1.22:8083/provider/manufacturer/list`)
        .then(res => {
          this.manufacturersList = res.data.data
        })
    },

    // 获取项目监督编号
    getCay() {
      if (this.editChangeUnit == 'CAY') {
        this.$axios
          .post(`http://192.168.1.22:8083/provider/cay?projectId=${this.projectId}`)
          .then(res => {
            this.superviseNum = res.data.jdbh
            this.itemsId = res.data.xmid
            this.sub_id = res.data.subId
            this.editSuperviseNum = res.data.jdbh
            this.editItemsId = res.data.xmid
          })
      }
    },

    // 添加设备
    addSave() {
      if (this.changeUnit && this.manufacturers && this.addComments && this.addSn) {
        if (this.changeUnit == 'CAY' || this.changeUnit == 'RCAJ') {
          if (this.superviseNum == '' || this.itemsId == '') {
            this.$message({
              message: "带 * 号的输入框不得为空",
              type: "warning"
            });
          } else {
            this.childAddSave()
          }
        } else {
          this.childAddSave()
        }
      } else {
        this.$message({
          message: "带 * 号的输入框不得为空",
          type: "warning"
        });
      }
    },

    // 添加设备调用
    childAddSave() {
      if (this.deviceInstallationDate == null) {
        this.deviceInstallationDate = ''
      }
      if (this.itemsId == undefined) {
        this.itemsId = ''
      }
      this.$axios
        .post(
          `http://192.168.1.22:8080/api/ProjectDustEmission/projectDustEmissionAddSave?projectId=${this.projectId}&comments=${this.addComments}&sn=${this.addSn}&videoAddress=${this.addVideoAddress}&meOption=${this.meOption}&installAddress=${this.installAddress}&installCompany=${this.installCompany}&deviceInstallationDate=${this.deviceInstallationDate}&jdbh=${this.superviseNum}&xmid=${this.itemsId}&sub_id=${this.sub_id}&manufacturerId=${this.manufacturers}&scznl=${this.changeUnit}`
        )
        .then(res => {
          // console.log(res.data)
          if (res.data.code == 0) {
            this.$message({
              message: "添加成功",
              type: "success"
            });
            this.dialogShow = false;
            // this.pageNum3 = 1
            this.getEquipmentList();
          } else {
            this.$message({
              message: `${res.data.msg}`,
              type: "warning"
            });
          }
        });
    },

    // 删除设备
    removeClick(item) {
      if (item.scznl == 'CAY') {
        this.deleteId = item.id
        this.personnelShow3 = true
      } else {
        this.$confirm("是否要删除选中的参建单位？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            this.$axios
              .post(`http://192.168.1.22:8080/api/ProjectDustEmission/remove?id=${item.id}`)
              .then(res => {
                // console.log(res.data)
                if (res.data.code == 0) {
                  this.$message({
                    type: "success",
                    message: "删除成功"
                  });
                  this.pageNum3 = 1;
                  this.getEquipmentList();
                } else {
                  this.$message({
                    message: "删除失败，请重试",
                    type: "error"
                  });
                }
              });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      }
    },

    // 删除城安院数据
    deleteCAY() {
      console.log(this.deleteTime)
      this.$axios
        .post(`http://192.168.1.22:8080/api/ProjectDustEmission/remove?id=${this.deleteId}&devCcrq=${this.deleteTime}`)
        .then(res => {
          if (res.data.code == 0) {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.deleteTime = ''
            this.personnelShow3 = false
            this.getEquipmentList()
          } else {
            this.$message({
              message: '删除失败',
              type: 'warning'
            });
          }
        })
    },

    // 设备厂商名称
    getManufacturerName(id) {
      for (let i = 0; i < this.manufacturersList.length; i++) {
        if (this.manufacturersList[i].id == id) {
          return this.manufacturersList[i].manufacturerName
        }
      }
    },

    // 上传平台名称
    geScznlName(id) {
      for (let i = 0; i < this.uploadUnit.length; i++) {
        if (this.uploadUnit[i].tag == id) {
          return this.uploadUnit[i].name
        }
      }
    },

    // 获取编辑设备信息
    getEdit(id) {
      this.$axios
        .post(`http://192.168.1.22:8080/api/ProjectDustEmission/projectDustEmissionById?id=${id}`)
        .then(res => {
          // console.log(res.data);
          this.editChangeUnit = res.data.data.scznl
          this.editManufacturers = Number(res.data.data.manufacturerId)
          this.editSuperviseNum = res.data.data.jdbh
          this.editItemsId = res.data.data.xmid
          this.editComments = res.data.data.comments;
          this.editSn = res.data.data.sn;
          this.selectId = id;
          this.editVideoAddress = res.data.data.videoAddress;
          this.editDeviceInstallationDate = res.data.data.deviceInstallationDate;
          this.editMeOption = res.data.data.meOption;
          this.editInstallAddress = res.data.data.installAddress;
          this.editInstallCompany = res.data.data.installCompany;
          // res.data.data.videoAddress?this.editVideoAddress='':this.editVideoAddress=res.data.data.videoAddress
          this.compileShow = true;
          // console.log(this.editVideoAddress?'我是空':'我有值')
        });
    },

    // 查看
    examine(id) {
      this.$axios
        .post(`/api/ProjectDustEmission/projectDustEmissionById?id=${id}`)
        .then(res => {
          // console.log(res.data);
          this.editComments = res.data.data.comments;
          this.editSn = res.data.data.sn;
          this.selectId = id;
          this.editVideoAddress = res.data.data.videoAddress;
          this.editDeviceInstallationDate = res.data.data.deviceInstallationDate;
          this.editMeOption = res.data.data.meOption;
          this.editInstallAddress = res.data.data.installAddress;
          this.editInstallCompany = res.data.data.installCompany;
          this.editSuperviseNum = res.data.data.jdbh
          // res.data.data.videoAddress?this.editVideoAddress='':this.editVideoAddress=res.data.data.videoAddress
          this.compileShow2 = true;
          // console.log(this.editVideoAddress?'我是空':'我有值')
        });
    },

    // 编辑设备
    editClick() {
      if (this.editDeviceInstallationDate == null) {
        this.editDeviceInstallationDate = ''
      }
      if (this.editMeOption == null) {
        this.editMeOption = ''
      }
      if (this.editInstallAddress == null) {
        this.editInstallAddress = ''
      }
      if (this.editInstallCompany == null) {
        this.editInstallCompany = ''
      }
      if (this.editSuperviseNum == null) {
        this.editSuperviseNum = ''
      }
      if (this.editItemsId == null) {
        this.editItemsId = ''
      }
      if (this.sub_id == null) {
        this.sub_id = ''
      }
      if (this.editComments && this.editSn) {
        this.$axios
          .post(
            `/api/ProjectDustEmission/projectDustEmissionEditSave?projectId=${
              this.projectId
            }&comments=${this.editComments}&sn=${this.editSn}&videoAddress=${
              this.editVideoAddress ? this.editVideoAddress : ""
            }&id=${this.selectId}&meOption=${this.editMeOption}&installAddress=${this.editInstallAddress}&installCompany=${this.editInstallCompany}&deviceInstallationDate=${this.editDeviceInstallationDate}&jdbh=${this.editSuperviseNum}&xmid=${this.editItemsId}&subId=${this.sub_id}`
          )
          .then(res => {
            // console.log(res.data)
            if (res.data.code == 0) {
              this.$message({
                message: "添加成功",
                type: "success"
              });
              this.compileShow = false;
              // this.pageNum3 = 1
              this.getEquipmentList();
            } else {
              this.$message({
                message: `${res.data.msg}`,
                type: "warning"
              });
            }
          });
      } else {
        this.$message({
          message: "带 * 号的输入框不得为空",
          type: "warning"
        });
      }
    },

    // 获取阀值数据
    getThresholdValue() {
      this.$axios
        .post(
          `/api/DustEmissionThresholdValueAPI/getThresholdValue?projectId=${this.projectId}`
        )
        .then(res => {
          // console.log(res.data)
          this.id = res.data.data.id;
          this.pm25 = res.data.data.pm25;
          this.pm10 = res.data.data.pm10;
          this.tsp = res.data.data.tsp;
          this.noise = res.data.data.noise;
          this.temperature = res.data.data.temperature;
          this.humidity = res.data.data.humidity;
          this.windSpeed = res.data.data.windSpeed;
        });
    },

    // 修改阀值
    updateThresholdValue() {
      this.$axios
        .post(
          `/api/DustEmissionThresholdValueAPI/updateThresholdValue?id=${this.id}&pm25=${this.pm25}&pm10=${this.pm10}&tsp=${this.tsp}&noise=${this.noise}&temperature=${this.temperature}&humidity=${this.humidity}&windSpeed=${this.windSpeed}`
        )
        .then(res => {
          if (res.data.code == 0) {
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.getThresholdValue();
          } else {
            this.$message({
              message: `${res.data.msg}`,
              type: "warning"
            });
          }
        });
    },

    // 获取报警设置
    getTSP() {
      this.$axios
        .post(`/api/DustEmission/getTSP?projectId=${this.projectId}`)
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
          this.tspList = res.data.data
        })
    },

    // 开关点击
    switchClick(obj) {
      // console.log(obj.onOff)
      this.$axios
        .post(`/api/OptionsPushApi/optionsPush?privilegesId=26&userId=${obj.id}&onOff=${obj.onOff?1:0}`)
        .then(res => {
          if (res.data.code === 0) {
            this.$message({
              type: 'success',
              message: res.data.msg
            })
          } else {
            this.$message({
              type: 'warning',
              message: '修改失败'
            })
          }
        })
    },

     // 搜索
    searchClick() {
      this.$axios
        .post(`/api/DustEmission/getTSP?projectId=${this.projectId}&filed=${this.search}`)
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
            this.tspList = res.data.data
          }
        })
    }
  }
};
</script>