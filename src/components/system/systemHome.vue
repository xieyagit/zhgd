<template>
  <div id="systemHome">
    <!-- 侧导航栏 -->
    <div class="side-nav">
      <ul>
        <li class="button">
          <a class="retract" @click="retractClick"></a>
        </li>
        <li>
          <a class="active">
            <div class="icon home"></div>
            <span>首&#12288;&#12288;页</span>
          </a>
        </li>
        <li>
          <router-link to="/systemLiangZhi">
            <div class="icon liangZhi"></div>
            <span>两制管理</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemPersonnel">
            <div class="icon personnel"></div>
            <span>人员管理</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemSchedule">
            <div class="icon schedule"></div>
            <span>进度管理</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemZhiHui">
            <div class="icon equipment"></div>
            <span>设备管理</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemMonitoring">
            <div class="icon video"></div>
            <span>视频监控</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemQuality">
            <div class="icon quality"></div>
            <span>质量监督</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemSafety">
            <div class="icon safety"></div>
            <span>安全巡检</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemGreen">
            <div class="icon green"></div>
            <span>绿色施工</span>
          </router-link>
        </li>
        <li>
          <router-link to="/systemInformation">
            <div class="icon datum"></div>
            <span>资料管理</span>
          </router-link>
        </li>
        <li>
          <a @click="unopenClick">
            <div class="icon dormitory"></div>
            <span>智慧宿舍</span>
          </a>
        </li>
        <li>
          <a @click="unopenClick">
            <div class="icon use"></div>
            <span>智能应用</span>
          </a>
        </li>
        <li>
          <router-link to="/systemSet">
            <div class="icon set"></div>
            <span>平台设置</span>
          </router-link>
        </li>
      </ul>
    </div>
    <!-- 内容 -->
    <div class="centent">
      <div class="content-box">
        <el-collapse v-model="activeNames">
          <el-collapse-item title="政府要求项" name="1">
            <!-- 两制 -->
            <div class="flex systemCard_bg" v-if="open">
              <router-link to="/systemLiangZhi">
                <img src="../../../static/images/systemCard.png" alt="">
              </router-link>
            </div>
            <div class="flex systemCard_black" v-else></div>
            <!-- 塔吊 -->
            <div class="flex towerCard_bg" v-if="open">
              <router-link to="/systemZhiHui_taDiao">
                <img src="../../../static/images/towerCard.png" alt="">
              </router-link>
            </div>
            <div class="flex towerCard_black" v-else></div>
            <!-- 升降机 -->
            <div class="flex lifterCard_bg" v-if="open">
              <router-link to="/systemZhiHui_shengJiangJi">
                <img src="../../../static/images/lifterCard.png" alt="">
              </router-link>
            </div>
            <div class="flex lifterCard_black" v-else></div>
            <!-- 车辆 -->
            <div class="flex carCard_bg" v-if="open">
              <router-link to="/systemZhiHui_car">
                <img src="../../../static/images/carCard.png" alt="">
              </router-link>
            </div>
            <div class="flex carCard_black" v-else></div>
            <!-- 视频 -->
            <div class="flex videoCard_bg" v-if="open">
              <router-link to="/systemMonitoring">
                <img src="../../../static/images/videoCard.png" alt="">
              </router-link>
            </div>
            <div class="flex videoCard_black" v-else></div>
            <!-- 电箱 -->
            <div class="flex electrivcityCard_bg" v-if="open">
              <router-link to="/systemZhiHui_dianXiang">
                <img src="../../../static/images/electricityCard.png" alt="">
              </router-link>
            </div>
            <div class="flex electricityCard_black" v-else></div>
            <!-- TSP -->
            <div class="flex TSPCard_pg" v-if="open">
              <router-link to="/systemGreen">
                <img src="../../../static/images/TSPCard.png" alt="">
              </router-link>
            </div>
            <div class="flex TSPCard_black" v-else></div>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
          </el-collapse-item>
          <el-collapse-item title="可选购项" name="2">
            <!-- 资料管理 -->
            <div class="flex dataCard_bg" v-if="open">
              <router-link to="/systemInformation">
                <img src="../../../static/images/dataCard.png" alt="">
              </router-link>
            </div>
            <div class="flex dataCard_black" v-else></div>
            <!-- 深基坑 -->
            <div class="flex deepExcavationCard_bg" v-if="open">
              <router-link to="/systemZhiHui_shenJiKeng">
                <img src="../../../static/images/deepExcavationCard.png" alt="">
              </router-link>
            </div>
            <div class="flex deepExcavationCard_black" v-else></div>
            <!-- 气体检测 -->
            <div class="flex gasCard_bg" v-if="open">
              <router-link to="/systemZhiHui_gas">
                <img src="../../../static/images/gasCard.png" alt="">
              </router-link>
            </div>
            <div class="flex gasCard_black" v-else></div>
            <!-- 安全帽视频 -->
            <div class="flex helmetCard_bg" v-if="open">
              <router-link to="/systemMonitoring">
                <img src="../../../static/images/helmetCard.png" alt="">
              </router-link>
            </div>
            <div class="flex helmetCard_black" v-else></div>
            <!-- 高支模 -->
            <div class="flex highFormworkCard_bg" v-if="open">
              <router-link to="/systemZhiHui_gaoZhiMo">
                <img src="../../../static/images/highFormworkCard.png" alt="">
              </router-link>
            </div>
            <div class="flex highFormworkCard_black" v-else></div>
            <!-- 人员定位 -->
            <div class="flex locationCard_bg" v-if="open">
              <router-link to="/systemLocation">
                <img src="../../../static/images/locationCard.png" alt="">
              </router-link>
            </div>
            <div class="flex locationCard_black" v-else></div>
            <!-- 进度管理 -->
            <div class="flex planCard_bg" v-if="open">
              <router-link to="/systemSchedule">
                <img src="../../../static/images/planCard.png" alt="">
              </router-link>
            </div>
            <div class="flex planCard_black" v-else></div>
            <!-- 质量监督 -->
            <div class="flex qualityCard_bg" v-if="open">
              <router-link to="/systemQuality">
                <img src="../../../static/images/qualityCard.png" alt="">
              </router-link>
            </div>
            <div class="flex qualityCard_black" v-else></div>
            <!-- 安全 -->
            <div class="flex safetyCard_bg" v-if="open">
              <router-link to="/systemSafety">
                <img src="../../../static/images/safetyCard.png" alt="">
              </router-link>
            </div>
            <div class="flex safetyCard_black" v-else></div>
            <!-- 卸料 -->
            <div class="flex unloadCard_bg" v-if="open">
              <router-link to="/systemZhiHui_unloading">
                <img src="../../../static/images/unloadCard.png" alt="">
              </router-link>
            </div>
            <div class="flex unloadCard_black" v-else></div>
            <!-- 智能水电 -->
            <div class="flex waterCard_bg" v-if="open">
              <router-link to="/systemZhiHui_shuidian">
                <img src="../../../static/images/waterCard.png" alt="">
              </router-link>
            </div>
            <div class="flex waterCard_black" v-else></div>
            <!-- 产业工人 -->
            <div class="flex workerCard_bg" v-if="!open">
              <!-- <router-link to=""> -->
                <img src="../../../static/images/workerCard.png" alt="">
              <!-- </router-link> -->
            </div>
            <div class="flex workerCard_black" v-else></div>
            <!-- 设置 -->
            <div class="flex setCard_bg" v-if="open">
              <router-link to="/systemSet_equipmentSet">
                <img src="../../../static/images/setCard.png" alt="">
              </router-link>
            </div>
            <div class="flex setCard_black" v-else></div>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
          </el-collapse-item>

          <el-collapse-item title="待开发项目" name="3">
            <div class="flex bgc">
              <img src="../../../static/images/3DCard.png" alt="">
              <p>3D打印应用</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/AIriskCard.png" alt="">
              <p>AI风险识别</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/BIMCard.png" alt="">
              <p>BIM技术应用</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/conferenceCard.png" alt="">
              <p>视频会议</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/detectionCard.png" alt="">
              <p>施工周边检测</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/dormitoryCard.png" alt="">
              <p>智能宿舍</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/educationCard.png" alt="">
              <p>智能安全教育</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/fireCard.png" alt="">
              <p>烟火监控</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/gantryCard.png" alt="">
              <p>龙门吊监管</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/noPeoplePlaneCard.png" alt="">
              <p>无人机应用</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/oneCardAll.png" alt="">
              <p>一卡通管理</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/rainwaterCard.png" alt="">
              <p>雨水回收检测</p>
            </div>
            <div class="flex bgc">
              <img src="../../../static/images/VRCard.png" alt="">
              <p>VR应用</p>
            </div>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
            <i></i>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </div>
</template>

<style lang="less">
#systemHome {
  width: 100%;
  display: flex;
  background-color: #f7f7f7;
  .side-nav {
    width: 1.75rem;
    height: 10rem;
    overflow: hidden;
    // display: inline-block;
    background-color: #54a4d7;
    ul {
      width: 1.75rem;
      li {
        height: 0.6rem;
        position: relative;
        a {
          width: 100%;
          height: 0.6rem;
          color: #fff;
          font-size: 0.2rem;
          line-height: 0.6rem;
          position: absolute;
          transition: all 0.5s;
          border-bottom: 0.01rem solid #98c8e7;
          .icon {
            opacity: 0.5;
            width: 0.6rem;
            height: 0.59rem;
            transition: all 0.5s;
            display: inline-block;
            background-repeat: no-repeat;
            background-position: center center;
          }
          span {
            // width: .8rem;
            vertical-align: top;
            // text-align: justify;
            // text-align-last:justify;
            // display: inline-block;
          }
          .home {
            background-image: url("../../../static/images/system-home.png");
          }
          .liangZhi {
            background-image: url("../../../static/images/systemHome-liangZhi.png");
          }
          .personnel {
            background-image: url("../../../static/images/systemHome-personnel.png");
          }
          .equipment {
            background-image: url("../../../static/images/systemHome-equipment.png");
          }
          .video {
            background-image: url("../../../static/images/systemHome-video.png");
          }
          .quality {
            background-image: url("../../../static/images/systemHome-quality.png");
          }
          .safety {
            background-image: url("../../../static/images/systemHome-safety.png");
          }
          .green {
            background-image: url("../../../static/images/systemHome-green.png");
          }
          .datum {
            background-image: url("../../../static/images/systemHome-datum.png");
          }
          .dormitory {
            background-image: url("../../../static/images/systemHome-dormitory.png");
          }
          .use {
            background-image: url("../../../static/images/systemHome-use.png");
          }
          .set {
            background-image: url("../../../static/images/systemHome-set.png");
          }
          .schedule {
            background-image: url("../../../static/images/system-schedule.png");
          }
        }
        a:hover {
          background-color: #2d83bb;
          .icon {
            opacity: 1;
          }
        }
        .active {
          z-index: 10;
          background-color: #378dc5;
          .icon {
            opacity: 1;
          }
        }
      }
      .button {
        position: relative;
        border-bottom: 0.01rem solid #98c8e7;
        a {
          top: 50%;
          left: 50%;
          border: 0;
          padding: 0;
          width: 0.22rem;
          height: 0.22rem;
          position: absolute;
          background-repeat: no-repeat;
          transform: translate(-50%, -50%);
          background-position: center center;
          background-image: url("../../../static/images/system-retract.png");
        }
        a:hover {
          background-color: #2d83bb;
        }
      }
    }
  }
  .centent {
    flex: 1;
    padding-top: 0.3rem;
    padding-left: 0.3rem;
    padding-right: 0.3rem;
    // display: inline-block;
    .el-collapse {
      width: 100%;
      .el-collapse-item__header {
        color: #0a94ff;
        font-size: 0.2rem;
      }
    }
    .content-box {
      height: 8.5rem;
      padding: 0.3rem;
      border-radius: 0.1rem;
      background-color: #fff;
      display: flex;
      justify-content: space-between;
      overflow-y: auto;
      .flex {
        width: 2.8rem;
        height: 1.72rem;
        display: inline-block;
        margin-bottom: 0.5rem;
        position: relative;
        img {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          cursor: pointer;
        }
        img:hover {
          top: 49%;
          left: 49%;
          box-shadow: 3px 3px 3px #909399
        }
      }
      .bgc {
        background-color: #f7f7f7;
        border: 1px solid #a6a6a6;
        img {
          cursor: default;
        }
        img:hover {
          top: 50% !important;
          left: 50% !important;
          box-shadow: none !important;
        }
        p {
          color: #a6a6a6;
          position: absolute;
          top: 80%;
          left: 50%;
          transform: translate(-50%, -50%);
        }
      }
      .systemCard_bg {
        background-image: url('../../../static/images/systemCard_bg.png');
      }
      .systemCard_black {
        background-image: url('../../../static/images/systemCard_black.png');
      }
      .towerCard_bg {
        background-image: url('../../../static/images/towerCard_bg.png');
      }
      .towerCard_black {
        background-image: url('../../../static/images/towerCard_black.png');
      }
      .lifterCard_bg {
        background-image: url('../../../static/images/lifterCard_bg.png');
      }
      .lifterCard_black {
        background-image: url('../../../static/images/lifterCard_black.png');
      }
      .carCard_bg {
        background-image: url('../../../static/images/carCard_bg.png');
      }
      .carCard_black {
        background-image: url('../../../static/images/carCard_black.png');
      }
      .videoCard_bg {
        background-image: url('../../../static/images/videoCard_bg.png');
      }
      .videoCard_black {
        background-image: url('../../../static/images/videoCard_black.png');
      }
      .electrivcityCard_bg {
        background-image: url('../../../static/images/electrivcityCard_bg.png');
      }
      .electricityCard_black {
        background-image: url('../../../static/images/electricityCard_black.png');
      }
      .TSPCard_pg {
        background-image: url('../../../static/images/TSPCard_pg.png');
      }
      .TSPCard_black {
        background-image: url('../../../static/images/TSPCard_black.png');
      }
      // 可选购各项
      .dataCard_bg {
        background-image: url('../../../static/images/dataCard_bg.png');
      }
      .dataCard_black {
        background-image: url('../../../static/images/dataCard_black.png');
      }
      .deepExcavationCard_bg {
        background-image: url('../../../static/images/deepExcavationCard_bg.png');
      }
      .deepExcavationCard_black {
        background-image: url('../../../static/images/deepExcavationCard_black.png');
      }
      .gasCard_bg {
        background-image: url('../../../static/images/gasCard_bg.png');
      }
      .gasCard_black {
        background-image: url('../../../static/images/gasCard_black.png');
      }
      .helmetCard_bg {
        background-image: url('../../../static/images/helmetCard_bg.png');
      }
      .helmetCard_black {
        background-image: url('../../../static/images/helmetCard_black.png');
      }
      .highFormworkCard_bg {
        background-image: url('../../../static/images/highFormworkCard_bg.png');
      }
      .highFormworkCard_black {
        background-image: url('../../../static/images/highFormworkCard_black.png');
      }
      .planCard_bg {
        background-image: url('../../../static/images/planCard_bg.png');
      }
      .planCard_black {
        background-image: url('../../../static/images/planCard_black.png');
      }
      .qualityCard_bg {
        background-image: url('../../../static/images/qualityCard_bg.png');
      }
      .qualityCard_black {
        background-image: url('../../../static/images/qualityCard_black.png');
      }
      .safetyCard_bg {
        background-image: url('../../../static/images/safetyCard_bg.png');
      }
      .safetyCard_black {
        background-image: url('../../../static/images/safetyCard_black.png');
      }
      .unloadCard_bg {
        background-image: url('../../../static/images/unloadCard_bg.png');
      }
      .unloadCard_black {
        background-image: url('../../../static/images/unloadCard_black.png');
      }
      .waterCard_bg {
        background-image: url('../../../static/images/waterCard_bg.png');
      }
      .waterCard_black {
        background-image: url('../../../static/images/waterCard_black.png');
      }
      .setCard_bg {
        background-image: url('../../../static/images/setCard_bg.png');
      }
      .setCard_black {
        background-image: url('../../../static/images/setCard_black.png');
      }
      .locationCard_bg {
        background-image: url('../../../static/images/locationCard_bg.png');
      }
      .locationCard_black {
        background-image: url('../../../static/images/locationCard_black.png');
      }
      .workerCard_bg {
        background-image: url('../../../static/images/workerCard_bg.png');
      }
      .workerCard_black {
        background-image: url('../../../static/images/workerCard_black.png');
      }
    }
    .el-collapse-item__content {
      width: 100%;
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      i {
        width: 2.8rem;
      }
    }
  }
  .content-box::-webkit-scrollbar {
    display: none;
  }
}
</style>

<script>
export default {
  data() {
    return {
      retractState: true, // 侧导航栏状态
      activeNames: ['1', '2', '3'], // 打开的下拉菜单
      open: true, // 是否禁止按钮
    };
  },
  mounted() {
  },
  methods: {
    // 侧导航栏缩进
    retractClick() {
      if (this.retractState) {
        $(".side-nav").animate(
          {
            width: ".6rem"
          },
          500
        );
        $(".retract").animate({
          left: ".3rem"
        });
        this.retractState = !this.retractState;
      } else {
        $(".side-nav").animate(
          {
            width: "1.75rem"
          },
          500
        );
        $(".retract").animate({
          left: "50%"
        });
        this.retractState = !this.retractState;
      }
    },

    // 该模块暂未开放
    unopenClick() {
      this.$message({
        message: "该功能暂未开放",
        type: "warning"
      });
    },
  },
  created() {}
};
</script>