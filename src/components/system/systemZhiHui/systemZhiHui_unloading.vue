<template>
  <div id="systemZhiHui_unloading">
    <div class="content">
      <!-- 顶部状态栏 -->
      <div class="top-status">
        <ul>
          <li>
            <div class="icon-box online"></div>
            <div class="text-box">
              <div class="top-text">在线卸料平台</div>
              <div class="bottom-text">{{onLine}}</div>
            </div>
          </li>
          <li>
            <div class="icon-box offline"></div>
            <div class="text-box">
              <div class="top-text">离线卸料平台</div>
              <div class="bottom-text">{{offLine}}</div>
            </div>
          </li>
          <li>
            <div class="icon-box alarm"></div>
            <div class="text-box">
              <div class="top-text">报警统计</div>
              <div class="bottom-text">{{call}}</div>
            </div>
          </li>
        </ul>
      </div>
      <!-- 主体 -->
      <div class="main-box">
        <!-- 设备列表 -->
        <div class="equipment-list">
          <div class="title">{{projectName}}</div>
          <div class="list-head">
            <span style="width:2.54rem">设备编号</span>
            <span>状态</span>
          </div>
          <div class="list-body">
            <ul>
              <li v-for="item in unloaderList" :key="item.deviceId" :class="{active:item.deviceId==deviceId}" @click="getSbUnloaderRealtimeList(item.deviceId)">
                <span style="width:2.54rem">{{item.deviceName}}</span>
                <span class="green" v-if="item.status == 1">
                    <i class="dot"></i>
                    运行中
                </span>
                <span class="red" v-else>
                  <i class="dot"></i>
                  未运行
                </span>
              </li>
            </ul>
          </div>
        </div>
        <!-- 实时数据 -->
        <div class="real-time">
          <!-- 实时数据 -->
          <div class="top-box">
            <div class="title">实时数据</div>
            <div class="border-box">
              <div class="text-box">
                <div class="top-text">{{weight}}T</div>
                <div class="bottom-text">平台载重</div>
              </div>
            </div>
            <div class="status">
              <ul>
                <li>
                  <div class="top-text">载重比例</div>
                  <div class="bottom-text">{{weightPercent}}%</div>
                  <div class="green-box" v-if="weightPercent<80"></div>
                  <div class="orange-box" v-else-if="weightPercent<90"></div>
                  <div class="red-box" v-else></div>
                </li>
                <li>
                  <div class="top-text">倾角X</div>
                  <div class="bottom-text">{{ObliguityXStatus==0?'正常':ObliguityXStatus==1?'预警':ObliguityXStatus==2?'报警':'故障'}}</div>
                  <div class="green-box" v-if="ObliguityXStatus==0"></div>
                  <div class="orange-box" v-else-if="ObliguityXStatus==1"></div>
                  <div class="red-box" v-else-if="ObliguityXStatus==2"></div>
                  <div class="gray-box" v-else></div>
                </li>
                <li>
                  <div class="top-text">倾角Y</div>
                  <div class="bottom-text">{{ObliguityYStatus==0?'正常':ObliguityXStatus==1?'预警':ObliguityXStatus==2?'报警':'故障'}}</div>
                  <div class="green-box" v-if="ObliguityYStatus==0"></div>
                  <div class="orange-box" v-else-if="ObliguityYStatus==1"></div>
                  <div class="red-box" v-else-if="ObliguityYStatus==2"></div>
                  <div class="gray-box" v-else></div>
                </li>
              </ul>
            </div>
          </div>
          <!-- 预警统计 -->
          <div class="bottom-box">
            <div class="title">卸料预警汇总统计</div>
            <div class="top-box">
              <div style="background-color:#0090ff;" @click="getRealtimeHistory(2),times=''">
                <div class="name">倾角X报警</div>
                <div class="num">
                  <span>{{dipX}}</span>
                  次
                </div>
              </div>
              <div style="background-color:#3ada76;" @click="getRealtimeHistory(3),times=''">
                <div class="name">倾角Y报警</div>
                <div class="num">
                  <span>{{dipY}}</span>
                  次
                </div>
              </div>
            </div>
            <div class="bottom-box">
              <div style="background-color:#ffb079;" @click="getRealtimeHistory(1),times=''">
                <div class="name">载重报警</div>
                <div class="num">
                  <span>{{load}}</span>
                  次
                </div>
              </div>
              <div style="background-color:#0090ff;" @click="getRealtimeHistory(4),times=''">
                <div class="name">电池电量报警</div>
                <div class="num">
                  <span>{{bat}}</span>
                  次
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 历史记录 -->
        <div class="history-box">
          <div class="btn-box">
            <a class="date" @click="selectTime=true">
              <i class="icon"></i>
              选择时间
            </a>
            <!-- <a class="cut">
              <i class="icon"></i>
              选择状态
            </a> -->
            <a class="derive" @click="download">
              <i class="icon"></i>
              导出Excel
            </a>
            <a class="cut" v-show="!onHistoryList" @click="onHistoryList=!onHistoryList,ListId=5,times=''">
              <i class="icon"></i>
              关闭报警
            </a>
          </div>
          <div class="history" v-show="onHistoryList">
            <div class="table-box">
              <el-table :data="historyList" stripe border @selection-change="selectList">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column type="index" label="序号" width="50" :index="indexMethod"></el-table-column>
                <el-table-column label="载重" width="80">
                  <template slot-scope="scope">
                    {{scope.row.weight}}T
                  </template>
                </el-table-column>
                <el-table-column label="载重比例" width="80">
                  <template slot-scope="scope">
                    {{scope.row.weightPercent}}%
                  </template>
                </el-table-column>
                <el-table-column label="倾角X" width="80">
                  <template slot-scope="scope">
                    {{scope.row.obliguityX}}°
                  </template>
                </el-table-column>
                <el-table-column label="倾角Y" width="80">
                  <template slot-scope="scope">
                    {{scope.row.obliguityY}}°
                  </template>
                </el-table-column>
                <el-table-column prop="time" label="时间"></el-table-column>
              </el-table>
            </div>
            <div class="paging-box">
              <el-pagination
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-size="pageSize"
                layout="total, prev, pager, next, jumper"
                :total="historyTotal"
              ></el-pagination>
            </div>
          </div>
          <div class="alarm" v-show="!onHistoryList">
            <div class="table-box">
              <el-table :data="alarmList" stripe border @selection-change="selectList">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column type="index" label="序号" width="50" :index="indexMethod2"></el-table-column>
                <el-table-column label="报警因素">
                  <template>
                    {{ListId==1?'载重报警':ListId==2?'倾角X报警':ListId==3?'倾角Y报警':'电池电量报警'}}
                  </template>
                </el-table-column>
                <el-table-column prop="alarmValue" label="报警数值"></el-table-column>
                <el-table-column prop="startTime" label="时间"></el-table-column>
              </el-table>
            </div>
            <div class="paging-box">
              <el-pagination
                @current-change="handalarmChange"
                :current-page="pageNumAlarm"
                :page-size="pageSize"
                layout="total, prev, pager, next, jumper"
                :total="alarmTotal"
              ></el-pagination>
            </div>
          </div>
        </div>
      </div>
      <div class="dialog-box" v-show="selectTime">
        <div class="title">请选择时间
          <i class="el-icon-close" @click="selectTime=!selectTime,times=''"></i>
        </div>
        <div class="center">
          <el-date-picker
            v-model="times"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </div>
        <div class="footer">
          <div class="button" @click="timeClick">确定</div>
        </div>
      </div>
      <div class="back" v-show="selectTime"></div>
    </div>
  </div>
</template>

<style lang="less">
#systemZhiHui_unloading {
  width: 100%;
  .content {
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    .top-status {
      height: 1.1rem;
      border-bottom: 0.1rem solid #f7f7f7;
      ul {
        display: flex;
        justify-content: space-between;
        padding: 0 2.12rem;
        li {
          .icon-box {
            width: 0.58rem;
            height: 1rem;
            margin-right: 0.2rem;
            vertical-align: top;
            display: inline-block;
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            &.online {
              background-image: url("../../../../static/images/online-unloading.png");
            }
            &.offline {
              background-image: url("../../../../static/images/offline-unloading.png");
            }
            &.alarm {
              background-image: url("../../../../static/images/systemZhiHui-alertor.png");
            }
          }
          .text-box {
            color: #303030;
            vertical-align: top;
            padding-top: 0.18rem;
            display: inline-block;
            .top-text {
              font-size: 0.18rem;
            }
            .bottom-text {
              margin-top: 0.1rem;
              font-size: 0.26rem;
              font-weight: bolder;
            }
          }
        }
      }
    }
    .main-box {
      display: flex;
      height: 8.4rem;
      .equipment-list {
        width: 4.1rem;
        border-right: 0.1rem solid #f7f7f7;
        overflow-y: auto;
        .title {
          height: 0.51rem;
          font-size: 0.18rem;
          line-height: 0.5rem;
          color: #0090ff;
          border-left: 0.04rem solid #0090ff;
          border-bottom: 0.01rem solid #f7f7f7;
          padding-left: 0.26rem;
        }
        .list-head {
          height: 0.51rem;
          color: #4a4a4a;
          font-size: 0.18rem;
          line-height: 0.5rem;
          padding-left: 0.26rem;
          border-bottom: 0.01rem solid #f7f7f7;
          display: flex;
          justify-content: flex-start;
        }
        .list-body {
          ul {
            li {
              height: 0.51rem;
              color: #4a4a4a;
              font-size: 0.18rem;
              line-height: 0.5rem;
              padding-left: 0.26rem;
              border-bottom: 0.01rem solid #f7f7f7;
              display: flex;
              justify-content: flex-start;
              cursor: pointer;
              .dot {
                width: 0.08rem;
                height: 0.08rem;
                border-radius: 0.08rem;
                display: inline-block;
                vertical-align: middle;
              }
              .green {
                color: #3ada76;
                .dot {
                  background-color: #3ada76;
                }
              }
              .red {
                color: #ff7a81;
                .dot {
                  background-color: #ff7a81;
                }
              }
            }
            .active {
              background:#c5e8ff;
            }
          }
        }
      }
      .real-time {
        width: 5.67rem;
        border-right: 0.1rem solid #f7f7f7;
        > .top-box {
          height: 4.98rem;
          padding-top: 0.25rem;
          border-bottom: 0.1rem solid #f7f7f7;
          .title {
            color: #4a4a4a;
            font-size: 0.18rem;
            padding-left: 0.25rem;
            font-weight: bolder;
          }
          .border-box {
            width: 2.48rem;
            height: 2.48rem;
            // border-radius: 2.48rem;
            // border: .01rem solid #3ada76;
            margin: 0 auto;
            margin-top: 0.25rem;
            position: relative;
            background-image: url("../../../../static/images/systemGreen-circle.png");
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            .text-box {
              width: 1.92rem;
              height: 1.92rem;
              border-radius: 1.92rem;
              // background-color: #3ada76;
              position: absolute;
              top: 50%;
              left: 50%;
              transform: translate(-50%, -50%);
              color: #fff;
              text-align: center;
              padding-top: 0.58rem;
              .top-text {
                font-size: 0.34rem;
                font-weight: bolder;
              }
              .bottom-text {
                font-size: 0.28rem;
                margin-top: 0.2rem;
                font-weight: bolder;
              }
            }
          }
          .status {
            margin-top: 0.3rem;
            ul {
              display: flex;
              padding: 0 0.28rem;
              justify-content: space-between;
              li {
                height: 1rem;
                width: 1.4rem;
                border-radius: 0.04rem;
                background-color: #f7f7f7;
                padding: 0 0.2rem;
                color: #4a4a4a;
                padding-top: 0.07rem;
                .top-text {
                  font-size: 0.22rem;
                  text-align: center;
                }
                .bottom-text {
                  font-size: 0.16rem;
                  text-align: center;
                  margin-top: 0.08rem;
                }
                .green-box {
                  height: 0.2rem;
                  margin-top: 0.08rem;
                  background-color: #88eeafd3;
                }
                .orange-box {
                  height: 0.2rem;
                  margin-top: 0.08rem;
                  background-color: #f7d086bd;
                }
                .red-box {
                  height: 0.2rem;
                  margin-top: 0.08rem;
                  background-color: #fa6363d0;
                }
                .gray-box {
                  height: 0.2rem;
                  margin-top: 0.08rem;
                  background-color: #727272d0;
                }
              }
            }
          }
        }
        > .bottom-box {
          padding-top: 0.25rem;
          .title {
            color: #4a4a4a;
            font-size: 0.18rem;
            padding-left: 0.25rem;
            font-weight: bolder;
          }
          .top-box {
            width: 3.06rem;
            margin: 0 auto;
            margin-top: 0.3rem;
            display: flex;
            justify-content: space-between;
            > div {
              color: #fff;
              height: 0.81rem;
              width: 1.43rem;
              border-radius: 0.04rem;
              padding-top: 0.1rem;
              cursor: pointer;
              .name {
                font-size: 0.16rem;
                text-align: center;
                font-weight: bolder;
              }
              .num {
                font-size: 0.14rem;
                text-align: center;
                font-weight: bolder;
                > span {
                  font-size: 0.2rem;
                }
              }
            }
          }
          .bottom-box {
            width: 3.06rem;
            margin-top: 0.22rem;
            margin: 0 auto;
            margin-top: 0.3rem;
            display: flex;
            justify-content: space-between;
            > div {
              color: #fff;
              height: 0.81rem;
              width: 1.43rem;
              border-radius: 0.04rem;
              padding-top: 0.1rem;
              cursor: pointer;
              .name {
                font-size: 0.16rem;
                text-align: center;
                font-weight: bolder;
              }
              .num {
                font-size: 0.14rem;
                text-align: center;
                font-weight: bolder;
                > span {
                  font-size: 0.2rem;
                }
              }
            }
          }
        }
      }
      .history-box {
        flex: 1;
        .btn-box {
          height: 0.8rem;
          padding: 0 1.4rem;
          padding-top: 0.2rem;
          border-bottom: 0.1rem solid #f7f7f7;
          a {
            float: left;
            height: 0.3rem;
            color: #0090ff;
            font-size: 0.14rem;
            transition: all 0.5s;
            line-height: 0.28rem;
            border-radius: 0.02rem;
            padding-right: 0.09rem;
            margin-left: 0.2rem;
            border: 0.01rem solid #0090ff;
            &:hover {
              color: #fff;
              background-color: #0090ff;
            }
            .icon {
              width: 0.37rem;
              height: 0.28rem;
              transition: all 0.5s;
              display: inline-block;
              vertical-align: middle;
              background-repeat: no-repeat;
              background-position: center center;
            }
          }
          .derive {
            .icon {
              background-image: url("../../../../static/images/system-derive.png");
            }
            &:hover {
              .icon {
                background-image: url("../../../../static/images/system-deriveHover.png");
              }
            }
          }
          .date {
            .icon {
              background-image: url("../../../../static/images/system-date.png");
            }
            &:hover {
              .icon {
                background-image: url("../../../../static/images/system-dateHover.png");
              }
            }
          }
          .cut {
            .icon {
              background-image: url("../../../../static/images/systemGreen-cut.png");
            }
            &:hover {
              .icon {
                background-image: url("../../../../static/images/systemGreen-cutHover.png");
              }
            }
          }
        }
        .table-box {
          width: 100%;
          padding: 0 0.2rem;
          margin-top: 0.2rem;
          min-height: 6.4rem;
          .el-table {
            width: 6.5rem;
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
            .red-color {
              color: #fe8990;
            }
            .green-color {
              color: #58de87;
            }
          }
        }
        .paging-box {
          width: 100%;
          height: 0.32rem;
          margin-top: 0.2rem;
          padding-right: 0.2rem;
          .el-pagination {
            float: right;
            // margin: 0 auto;
          }
        }
      }
    }
    .dialog-box {
      left: 50%;
      top: 2.14rem;
      z-index: 200;
      width: 5.24rem;
      overflow: hidden;
      position: fixed;
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
        i {
          top: 50%;
          right: 0.2rem;
          color: #fff;
          position: absolute;
          transform: translateY(-50%);
          cursor: pointer;
        }
      }
      .center {
        padding: 0.6rem 1rem;
        .el-date-editor.el-input, .el-date-editor.el-input__inner {
          width: 100% !important;
          input {
            border: 1px solid #a1a1a1;
            border-radius: 5px;
          }
        }
      }
      .footer {
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
    .back {
      top: 0;
      left: 0;
      width: 100%;
      z-index: 100;
      height: 100%;
      position: fixed;
      background-color: rgba(0, 0, 0, .5);
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
      pageSize: 10, // 每页条数
      pageNum: 1, // 当前页
      pageTotal: 2, // 总条数
      tableData: [
        {
          load: "20t", // 载重
          range: "2M", // 幅度
          mainRope: "正常", // 主绳
          deputyRope: "正常", // 副绳
          status: 0, // 状态
          time: "9-11 09:11" // 时间
        },
        {
          load: "100t", // 载重
          range: "2M", // 幅度
          mainRope: "正常", // 主绳
          deputyRope: "正常", // 副绳
          status: 1, // 状态
          time: "9-11 11:11" // 时间
        }
      ], // 历史记录列表
      unloaderList: {}, // 设备列表
      onLine: 0, // 在线平台数量
      offLine: 0, //离线平台数量
      projectName: '', //项目名称
      call: 0, // 报警统计
      deviceId: '', // 设备编号ID
      dipY: 0, // 倾角Y报警次数
      dipX: 0, // 倾角X报警次数
      weightPercent: 0, // 载重百分比
      load: 0, // 载重报警次数
      bat: 0, // 电池电量报警次数
      ObliguityYStatus: 0, // 倾角Y状态0：正常1：预警2：报警3：故障
      weight: 0, // 平台载重
      ObliguityXStatus: 0, // 倾角X状态0：正常1：预警2：报警3：故障
      pageNum: 1, // 实时记录页码
      pageNumAlarm: 1, // 报警页码
      pageSize: 15, // 页面显示条数
      alarmList: [], // 报警列表
      times: '', // 查询的时间
      ListId: 5, // 1：载重 2：倾角X 3：倾角Y 4：电池电量报警 5: 正常
      historyList: [], // 实时数据列表
      onHistoryList: true, // 实时数据列表显示
      historyTotal: 0, // 历史记录总条数
      alarmTotal: 0, // 报警记录总条数
      selectTime: false, // 选择时间
      excelList: [], // 导出列表
    };
  },
  mounted() {
    this.getUnloaderList()
  },
  methods: {
    // 当前页
    handleCurrentChange(val) {
      // console.log(`当前页：${val}`);
      this.pageNum = val
      this.getSbUnloaderHistory()
    },

    handalarmChange(val) {
      this.pageNumAlarm = val
      this.getRealtimeHistory()
    },

    // 序号
    indexMethod(index) {
      return (this.pageNum - 1) * this.pageSize + index + 1;
    },

    // 序号
    indexMethod2(index) {
      return (this.pageNumAlarm - 1) * this.pageSize + index + 1;
    },

    // 获取设备列表
    getUnloaderList() {
      this.$axios
        .post(`/api/unloaderPcApi/getUnloaderList?projectId=${this.projectId}`)
        .then(res => {
          this.projectName = res.data.data.projectName
          this.onLine = res.data.data.onLine
          this.offLine = res.data.data.offLine
          this.call = res.data.data.call
          this.unloaderList = res.data.data.deviceList
          this.deviceId = this.unloaderList[0].deviceId
          this.getSbUnloaderRealtimeList()
          this.getSbUnloaderHistory()
        })
    },

    // 选择设备，请求实时数据
    getSbUnloaderRealtimeList(id) {
      this.historyList = []
      this.alarmList = []
      this.onHistoryList = true
      if (id) {
        this.deviceId = id
        this.getSbUnloaderRealtimeList()
        this.getSbUnloaderHistory()
      }
      this.$axios
        .post(`/api/unloaderPcApi/getSbUnloaderRealtimeList?projectId=${this.projectId}&deviceId=${this.deviceId}`)
        .then(res => {
          this.ObliguityXStatus = res.data.data.ObliguityXStatus
          this.ObliguityYStatus = res.data.data.ObliguityYStatus
          this.bat = res.data.data.bat
          this.dipX = res.data.data.dipX
          this.dipY = res.data.data.dipY
          this.load = res.data.data.load
          this.weight = res.data.data.weight
          this.weightPercent = res.data.data.weightPercent
        })
    },

    // 报警次数列表
    getRealtimeHistory(alarmType) {
      if (alarmType) {
        this.pageNumAlarm = 1
        this.ListId = alarmType
        this.onHistoryList = false
      }
      if (this.times == null) {
        this.times = ''
      }
      this.$axios
        .post(`/api/unloaderPcApi/getRealtimeHistory?projectId=${this.projectId}&deviceId=${this.deviceId}&alarmType=${this.ListId}&time=${this.times}&pageNum=${this.pageNumAlarm}&pageSize=${this.pageSize}`)
        .then(res => {
          this.alarmList = res.data.data
          this.alarmTotal = res.data.total
        })
    },

    // 历史数据列表
    getSbUnloaderHistory() {
      if (this.times == null) {
        this.times = ''
      }
      this.$axios
        .post(`/api/unloaderPcApi/getSbUnloaderHistory?projectId=${this.projectId}&deviceId=${this.deviceId}&pageNum=${this.pageNum}&pageSize=${this.pageSize}&time=${this.times}`)
        .then(res => {
          this.historyList = res.data.data
          this.historyTotal = res.data.total
        })
    },

    // 时间选择
    timeClick() {
      if (this.ListId != 5) {
        this.getRealtimeHistory()
        this.selectTime = false
      } else {
        this.getSbUnloaderHistory()
        this.selectTime = false
      }
    },

    // 选择下载的列表
    selectList(row) {
      this.excelList = []
      for (let i = 0; i < row.length; i++) {
        this.excelList.push(row[i].id)
      }
      console.log(this.excelList)
    },

    // 下载
    download() {
      location.href=`http://47.106.71.3:8080/api/unloaderPcApi/exportUnloader?judge=${this.ListId}&ids=${this.excelList}&deviceId=${this.deviceId}`
    }
  }
};
</script>