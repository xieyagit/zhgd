<template>
  <div class="content">
    <el-row class="wrap-main clearfix container">
      <el-col :span="14" class="wrap-left">
        <div class="top-show">
          <h3>工人上工情况</h3>
          <div class="jindu">
            <div class="subjindu" :style="`width:${attendanceData.bfb}`" v-if="attendanceData.bfb">
              <p>{{attendanceData.bfb}}</p>
            </div>
            <div class="subjindu" style="width:0" v-else>
            </div>
          </div>
          <ul class="work-list clearfix">
            <li>
              今日
              <!-- <span v-for="(item2,index2) in projectData.grsg" :key="index2" v-if="item<item2" class="danger">{{item.JR}} ↓</span>
              <span v-for="(item2,index2) in projectData.grsg" :key="index2+1" v-else-if="item>item2" class="noml">{{item.JR}} ↑</span> -->
              <span v-if="attendanceData.today">{{attendanceData.today}} 人</span>
              <span v-else>0 人</span>
            </li>
            <li>
              昨日
              <span v-if="attendanceData.yesterday">{{attendanceData.yesterday}} 人</span>
              <span v-else>0 人</span>
            </li>
            <li>
              本月
              <span class="down" v-if="attendanceData.thisMonth">{{attendanceData.thisMonth}} 次</span>
              <span class="down" v-else>0 次</span>
            </li>
            <li>
              上月
              <span v-if="attendanceData.ultimo">{{attendanceData.ultimo}} 次</span>
              <span v-else>0 次</span>
            </li>
          </ul>
        </div>
        <div class="summarize">
          <h3>工程概况</h3>
          <ul>
            <li v-if="generalizeData.projectName">
              <a href="javascript:;">
                <span class="summarizeName">项目名称:</span>
                <span class="summarizeContent">{{generalizeData.projectName}}</span>
              </a>
            </li>
            <li v-if="generalizeData.acreage">
              <a href="javascript:;">
                <span class="summarizeName">工程面积:</span>
                <span class="summarizeContent">{{generalizeData.acreage}}㎡</span>
              </a>
            </li>
            <li v-if="generalizeData.projectCost">
              <a href="javascript:;">
                <span class="summarizeName">工程造价:</span>
                <span class="summarizeContent">{{generalizeData.projectCost}}万</span>
              </a>
            </li>
            <li v-if="generalizeData.constructionName">
              <a href="javascript:;">
                <span class="summarizeName">总包单位:</span>
                <span class="summarizeContent">{{generalizeData.constructionName}}</span>
              </a>
            </li>
          </ul>
          <div id="colee" style="overflow:hidden;height:3.12rem;width:4.1rem;">
            <ul id="colee1">
              <!-- <li>
                <a href="javascript:;">
                  <span class="summarizeName">项目名称：</span>
                  <span class="summarizeContent">龙岗深圳河项目布吉片区</span>
                </a>
              </li> -->
              <!-- <li v-for="(item,index) in projectData.gcgk" :key="item.title">
                <a href="javascript:;">
                  <span class="summarizeName">{{item.type}}：</span>
                  <span class="summarizeContent">{{item.title}}</span>
                </a>
              </li> -->
              <li v-for="(item,index) in generalizeData.subpackage" :key="index">
                <a href="javascript:;">
                  <span class="summarizeName">分包单位:</span>
                  <span class="summarizeContent">{{item.name}}</span>
                </a>
              </li>
            </ul>
            <ul id="colee2"></ul>
            <div v-if="generalizeData" class="text-box" style="padding: 0 0.35rem;">
              项目简介:
            </div>
            <div class="roll-box" v-if="generalizeData.projectIntroduction">
              <div class="text-box" style="margin-top:0">{{generalizeData.projectIntroduction}} </div>
            </div>
          </div>
        </div>
        <div class="plate">
          <h3>车牌识别系统</h3>
          <div class="out_in">
            <div class="left">
              <span>今日进出：</span>
              <span v-if="carData">{{carData.todaycount.count}}辆</span>
            </div>
            <div class="right">
              <span>运行情况：</span>
              <!-- <span v-if="cardid.status=='0'" class="status noml">正常</span> -->
              <span class="status noml">正常</span>
              <!-- <span v-else class="status danger">异常</span> -->
            </div>
          </div>
          <div class="case">
            <div class="left">进出情况</div>
            <div class="right" id="cardid" style="overflow:hidden;height:1.04rem;width:4.1rem;">
              <ul id="cardid1" v-if="carData">
                <li v-for="(item, index) in carData.todaycount.vehicleList" :key="index" >
                  <span>{{item.vehicleNo}}</span>
                  <span>{{item.gateinname}}</span>
                  <span>{{item.liftTime}}</span>
                </li>
              </ul>
              <ul id="cardid2"></ul>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="6" class="wrap-center">
        <div class="mainInfo">
          <span class="title">
            安全文明施工天数:
            <span class="day">{{day}}</span>天
          </span>
          <router-link to="/dormitory" class="dormitory">智慧宿舍</router-link>
        </div>
        <div class="videoInfo">
          <el-carousel trigger="click" height="6.14rem">
            <el-carousel-item>
              <img src="../../../static/images/mmexport1565845097787.jpg">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../../../static/images/mmexport1565845100546.jpg">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../../../static/images/mmexport1565845104313.jpg">
              <!-- <img src="" alt=""> -->
            </el-carousel-item>
          </el-carousel>
        </div>
        <div class="bottomInfo">
          <div class="left">
            <h3>塔吊防碰撞系统</h3>
            <ul>
              <li>
                <span class="leftName">塔吊数量：</span>
                <span class="sub" v-if="towerCraneData">{{towerCraneData.length}}座</span>
              </li>
              <li>
                <span class="leftName">违规操作：</span>
                <span class="sub status noml">无</span>
                <!-- <span v-else class="sub status danger">有</span> -->
              </li>
              <li v-if="towerCraneData">
                <span class="leftName">运行情况：</span>
                <!-- <span v-if="towerCraneData[0].sb=='正常'" class="sub noml">正常</span> -->
                <!-- <span v-else class="sub danger">异常</span> -->
                <span v-if="!towerCraneData" class="sub danger">离线</span>
                <span v-else class="sub noml">正常</span>
              </li>
              <li v-else>
                <span class="leftName">运行情况：</span>
                <span class="sub danger">无数据</span>
              </li>
            </ul>
            <div class="outer">
              <div class="line"></div>
              <div class="dian"></div>
            </div>
          </div>
          <div class="right">
            <h3>升降机系统</h3>
            <ul>
              <li>
                <span class="leftName">升降机数量：</span>
                <!-- <span class="sub" v-if="elevatorData">{{elevatorData.length}}座</span> -->
                <span class="sub" v-if="elevatorData.length>0">3座</span>
                <span class="sub" v-else>0座</span>
              </li>
              <li>
                <span class="leftName">载重重量：</span>
                <span class="sub" v-if="elevatorData.length>0" :class="elevatorData[0].laod>30?'danger':'noml'">{{elevatorData[0].laod}}T</span>
                <span class="sub" v-else>0T</span>
              </li>
              <li v-if="elevatorData.length>0">
                <span class="leftName">运行情况：</span>
                <span v-if="elevatorData[0].sb=='正常'" class="sub noml">正常</span>
                <span v-else class="sub danger">异常</span>
              </li>
              <li v-else>
                <span class="leftName">运行情况：</span>
                <span class="sub danger">无数据</span>
              </li>
            </ul>
            <div class="runtime">
              <div>已正常运行</div>
              <img src="../../../static/images/shizhong.png" alt>
              <!-- 升降机的运行时间，先固定数值，只后会修改 -->
              <!-- <div class="noml runtimeBg" v-if="elevatorData">{{elevatorData[0].days}} H</div> -->
              <div class="noml runtimeBg" v-if="elevatorData.length>0">25 H</div>
              <div class="noml runtimeBg" v-else>0 H</div>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="6" class="wrap-right">
        <div class="code">
          <h3>产业工人库</h3>
          <div class="search-box">
            <input type="text" placeholder="搜索工种或公司">
            <router-link to="/workerHome" class="search-button">搜索</router-link>
          </div>
          <div class="hot-search">
            <ul>
              <li>
                热门搜索：
              </li>
              <li class="option">
                <router-link to="/workerHome">木工</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">绿化工</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">杂工</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">水泥工</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">电工</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">中建一局</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">中建二局</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">中建三局</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">中建四局</router-link>
              </li>
              <li class="option">
                <router-link to="/workerHome">中建五局</router-link>
              </li>
            </ul>
          </div>
        </div>
        <div class="ech">
          <h3>扬尘检测</h3>
          <div class="status">
            <span class="good">良好</span>
            <span class="notgood">超标</span>
          </div>
          <div class="ecCompany">
            <div class="company">ug/m<sub>3</sub></div>
            <div id="dust" :style="{width: '3.6rem', height: '2.05rem'}" class="dust"></div>
          </div>
          <div class="temperaturebox">
            <h3 style="margin-bottom:.1rem">温度检测</h3>
            <div
              id="temperature"
              style="width: 3.8rem;height:2.04rem;transform: translateY(-0.18rem)"
              class="temperature"
            ></div>
            <div class="temperaturebox-hour">H</div>
          </div>
        </div>
        <div class="electro">
          <h3>用电检测</h3>
          <ul>
            <li>
              <span class="leftName">电箱数量：</span>
              <span v-if="electricityBox.length>0" class="sub">{{electricityBox.count}}座</span>
              <span v-else>0座</span>
            </li>
            <li>
              <span class="leftName">电箱温度：</span>
              <span v-if="electricityBox.length>0" class="sub" :class="electricityBox.envirwarm>45?'danger':'noml'">{{electricityBox.envirwarm}}℃</span>
              <span v-else>无数据</span>
            </li>
            <li v-if="electricityBox.length>0">
              <span class="leftName">运行情况：</span>
              <span v-if="electricityBox.kgjl.sb=='正常'" class="sub noml">正常</span>
              <span v-else class="sub danger">异常</span>
            </li>
            <li v-else>
              <span class="leftName">运行情况：</span>
              <span class="sub danger">无数据</span>
            </li>
          </ul>
          <div class="runtime">
            <div>已正常运行</div>
            <img src="../../../static/images/shizhong.png" alt>
            <div class="noml runtimeBg" v-if="electricityBox.length>0">{{electricityBox.kgjl.days}} H</div>
            <div class="noml runtimeBg" v-else>0 H</div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { setTimeout, setInterval } from 'timers';
export default {
  data() {
    return {
      percentage: 70,
      baifenbi: 70,
      dh: 0,
      timeId: null,
      gundongId: null,
      // 工人上工
      manWork: {},
      // 工人概括
      summary: {},
      // 车牌识别
      cardid: {},
      // 塔吊防碰撞
      crash: {},
      // 升降机
      lift: {},
      // 轮播图
      centerInfo: {},
      // 用电
      electricity: {},
      // 项目id
      xmid: "281",
      projectData: '', //项目总况页面数据

      projectId: '', //项目id
      electricityBox: '', // 电箱数据
      carData: '', // 车辆数据
      generalizeData: '', // 工程概括数据
      attendanceData: '', // 工人上工情况数据
      elevatorData: '', // 升降机数据
      towerCraneData: '', // 塔吊数据
      day: 0, // 安全文明施工天数
      re: 0,

      temp1: '',
      temp2: '',
    };
  },
  created() {
    this.getPid()
    // this.getCenterInfo()
    this.getElectricityBox()
    this.getCarData()
    this.getGeneralize()
    this.getAttendance()
    this.getElevator()
    this.getTowerCrane()
    this.getTspData()
    this.getDay()
  },
  methods: {
    // 初始化扬尘检测数据图
    dustPic(dPH,dPV) {
      let mydust = this.$echarts.init(document.getElementById("dust"));
      mydust.setOption({
        title: { text: "" },
        grid: {
          x: 100,
          y: 0,
          x2: 14,
          y2: 20
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          },
          formatter: '{b}<br/>{c} ug/m<sub>3</sub>'
        },
        xAxis: {
          max: 200, //最大值
          min: 0, //最小值
          interval: 25, //间隔
          //字体样式
          axisLabel: {
            textStyle: {
              color: "#fff"
            }
          },
          //坐标轴样式
          axisLine: {
            lineStyle: {
              color: "#132e6d",
              width: 2
            }
          },
          //网格样式
          splitLine: {
            show: true,
            lineStyle: {
              color: "#132e6d",
              width: 1
            }
          }
        },
        orient: "horizontal",
        yAxis: {
          data: dPH,
          axisLabel: {
            textStyle: {
              color: "#fff"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#132e6d",
              width: 2
            }
          },
          //网格样式
          splitLine: {
            show: true,
            lineStyle: {
              color: "#132e6d",
              width: 0
            }
          }
        },
        series: [
          {
            type: "bar",
            barWidth: 10, //柱状图宽度
            data: dPV,
            //柱状图颜色
            itemStyle: {
              normal: {
                //判断
                color: function(params) {
                  if (params.value >= 75) {
                    return "#fb497c";
                  } else {
                    return "#009b41";
                  }
                }
                //渐变
                // color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                //       offset: 0,
                //       color: "#0162ff" // 0% 处的颜色
                //     }, {
                //       offset: 1,
                //       color: "#00a7fe" // 100% 处的颜色
                // }], false)
              }
            }
          }
        ]
      })
    },
    
    // 初始化温度检测数据图
    temperaturePic(tPH,tPV) {
      // hours.push("h");
      let mytemperature = this.$echarts.init(
        document.getElementById("temperature")
      );
      mytemperature.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 50,
          y: 20,
          x2: 10,
          y2: 40
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "line" // 默认为直线，可选为：'line' | 'shadow'
          },
          formatter: '{b} H<br/>{c} <sup>o</sup>C</SUP>'
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#fff"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: tPH
          }
        ],
        yAxis: [
          {
            type: "value",
            max: 45,
            min: -30,
            interval: 15,
            axisLabel: {
              textStyle: {
                color: "#fff"
              },
              formatter: "{value} ℃"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#132e6d"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "温度",
            type: "line",
            symbolSize: 3,
            smooth: 0.2,
            color: ["#f98d98"],
            data: tPV
          }
        ]
      });
    },

    // 工人上工区域
    renderBaifenbi() {
      this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=GetXMKq&xmid=${this.xmid}`)
        .then(res => {
          if(res.data.success == 1){
            this.$router.push('unopen')
          }else{
            this.manWork = res.data;
            this.timeId = setInterval(() => {
              if (this.dh >= this.manWork.ratio || this.dh > 100) {
                if (this.dh > 100) {
                  this.dh = 100
                }
                clearInterval(this.timeId);
              } else {
                this.dh++;
                $(".subjindu").css("width", this.dh + "%");
              }
            }, 30);
          }
        });
    },

    // 工程概括区域
    getSummary() {
      this.xmid = this.getQueryString("xmid")
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=GetXMDetail&xmid=${this.xmid}`)
        .then(res => {
          if(res.data.success == 1){
            this.$router.push('unopen')
          }else{
            this.summary = res.data;
            if (this.summary.data.length >= 10) {
              // 调用滚动方法
              this.scrollText();
            }
          }
        });
    },

    // 车牌识别
    getCardid() {
      this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=PkData&xmid=${this.xmid}`)
        .then(res => {
          if(res.data.success == 1){
            this.$router.push('unopen')
          }else{
            this.cardid = res.data;
            if (this.cardid.cars.length >= 5) {
              this.cardScroll();
            }
          }
        });
    },

    // 塔吊防碰撞
    getCrash() {
      this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=TowerData&xmid=${this.xmid}`)
        .then(res => {
          if(res.data.success == 1){
            this.$router.push('unopen')
          }else{
            this.crash = res.data;
          }
        });
    },

    // 升降机
    getLift() {
      this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=LifterData&xmid=${this.xmid}`)
        .then(res => {
          if(res.data.success == 1){
            this.$router.push('unopen')
          }else{
            this.lift = res.data;
          }
        });
    },

    // 中间主图
    getCenterInfo() {
      // this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=XMData&xmid=DV2mxBGL1Ao%3D`)
        .then(res => {
            this.centerInfo = res.data;
        })
    },

    scrollText() {
      setTimeout(() => {
        var speed = 40;
        var colee2 = document.getElementById("colee2");
        var colee1 = document.getElementById("colee1");
        var colee = document.getElementById("colee");
        colee2.innerHTML = colee1.innerHTML; //克隆colee1为colee2

        function Marquee1() {
          //当滚动至colee1与colee2交界时
          if (colee2.offsetTop - colee.scrollTop <= 0) {
            colee.scrollTop -= colee1.offsetHeight; //colee跳到最顶端
          } else {
            // console.log(111111);
            colee.scrollTop++;

            if (colee.scrollTop == 829) {
              colee.scrollTop = 170;
            }
          }
        }
        var MyMar1 = setInterval(Marquee1, speed); //设置定时器
        //鼠标移上时清除定时器达到滚动停止的目的
        // colee.onmouseover = function() {
        //   clearInterval(MyMar1);
        //   // console.log(123)
        // };
        // //鼠标移开时重设定时器
        // colee.onmouseout = function() {
        //   MyMar1 = setInterval(Marquee1, speed);
        // };
      }, 1000);
    },

    cardScroll() {
      setTimeout(() => {
        var speed = 45;
        var colee2 = document.getElementById("cardid2");
        var colee1 = document.getElementById("cardid1");
        var colee = document.getElementById("cardid");
        colee2.innerHTML = colee1.innerHTML; //克隆colee1为colee2
        function Marquee1() {
          //当滚动至colee1与colee2交界时
          if (colee2.offsetTop - colee.scrollTop <= 0) {
            colee.scrollTop -= colee1.offsetHeight; //colee跳到最顶端
          } else {
            colee.scrollTop++;
            if (colee.scrollTop == 104) {
              colee.scrollTop = 0;
            }
          }
        }
        var MyMar1 = setInterval(Marquee1, speed); //设置定时器
        // //鼠标移上时清除定时器达到滚动停止的目的
        // colee.onmouseover = function() {
        //   clearInterval(MyMar1);
        // };
        // //鼠标移开时重设定时器
        // colee.onmouseout = function() {
        //   MyMar1 = setInterval(Marquee1, speed);
        // };
      }, 1000);
    },

    // 用电检测
    getyongdian() {
      this.xmid = this.getQueryString("xmid");
      this.$axios
        .get(`http://gd.17hr.net:8018/APP/XMPage/XmData.ashx?method=ElectricityData&xmid=${this.xmid}`)
        .then(res => {
          this.electricity = res.data;
        });
    },

    getQueryString(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return null;
    },

    // 获取页面所需数据
    getProjectData() {
      this.$axios.get(`/home/get/projectData?pid=${this.pid}`).then(
        res => {
          // console.log(res.data.cjaw)
          this.projectData = res.data
          let dPH = []
          let dPV = []
          let tPH = []
          let tPV = []
          for (let i = 0; i < this.projectData.PM25AVG.length; i++) {
            for (const key in this.projectData.PM25AVG[i]) {
              dPH.push(key)
              dPV.push(this.projectData.PM25AVG[i][key])
            }
          }
          for (let i = 0; i < this.projectData.QW.length; i++) {
            tPH.push(this.projectData.QW[i].time)
            tPV.push(this.projectData.QW[i].temperature)
          }
          tPH.push('h')
          // 初始化ECharts图
          this.dustPic(dPH,dPV)
          this.temperaturePic(tPH,tPV)

          this.timeId = setInterval(() => {
            // console.log(this.attendanceData.EmpRenShuData[0].bfb)
            if (this.dh >= this.projectData.grsg[1].bfb || this.dh > 100) {
              if (this.dh > 100) {
                this.dh = 100
              }
              clearInterval(this.timeId);
            } else {
              this.dh++;
              $(".subjindu").css("width", this.dh + "%");
            }
          }, 30)
          if (res.data.cjaw.length >= 7) {
            // 调用滚动方法
            this.scrollText()
          }
          // console.log(tPH);
          // console.log(tPV);
        }
      )
    },

    // 获取项目id
    getPid() {
      this.projectId = sessionStorage.getItem('pid')
      console.log(this.projectId)
    },

    // 获取电箱数据
    getElectricityBox() {
      this.$axios.post(`/api/currentTemperatureApi/kanban?projectId=${this.projectId}`).then(
        res => {
          // console.log(res.data)
          if (res.data) {
            this.electricityBox = res.data
          }
        }
      )
    },

    // 获取车辆数据
    getCarData() {
      this.$axios.post(`/api/parkings/todaycount?projectId=${this.projectId}`).then(
        res => {
          // console.log(res.data.data[0])
          if (res.data.data) {
            this.carData = res.data.data[0]
            // if (this.carData.todaycount.vehicleList.length >= 4) {
            //   // 调用滚动方法
            //   setTimeout(()=>{
            //     this.cardScroll()
            //   },100)
            // }
          }
        }
      )
    },

    // 获取升降机数据
    getElevator() {
      this.$axios.post(`/api/appElevatorAddRecord/crane?pid=${this.projectId}`).then(
        res => {
          // console.log(res.data)
          if (res.data.data) {
            this.elevatorData = res.data.data
          }
        }
      )
    },

    // 获取塔吊数据
    getTowerCrane() {
        this.$axios.post(`/api/appCraneAddRecord/kanban?pid=${this.projectId}`).then(
            res => {
                console.log(res.data.sjjlist)
                if (res.data.sjjlist) {
                  this.towerCraneData = res.data.sjjlist
                }
            }
        )
    },

    // 获取工程概括
    getGeneralize() {
      this.$axios.post(`/api/project/getXmzk?id=${this.projectId}`).then (
        res => {
          // console.log(res.data)
          if (res.data) {
            this.generalizeData = res.data
            if (this.generalizeData.subpackage.length >= 5) {
              // 调用滚动方法
              setTimeout(()=>{
                this.scrollText()
              },100)
            }
          }
        }
      )
    },

    // 获取工人上工情况
    getAttendance() {
      this.$axios.post(`/api/attendanceRecordApi/TheWorkersWork?projectId=${this.projectId}`).then(
        res => {
          // console.log(res.data)
          if (res.data) {
            this.attendanceData = res.data
            console.log(this.attendanceData)
          }
        }
      )
    },

    // 获取扬尘与温度检测数据
    getTspData() {
      this.$axios.post(`/api/DustEmission/Tsp?projectId=${this.projectId}`).then(
        res => {
          // console.log(res.data)
          if (res.data.tsp) {
            let temp1 = []
            let temp2 = []
            let temp3 = []
            let temp4 = []
            for (let i = 0; i < res.data.tsp.length; i++) {
              for (const key in res.data.tsp[i]) {
                // console.log(key)
                // console.log(res.data.tsp[i][key])
                temp1.push(key)
                temp2.push(res.data.tsp[i][key])
              }
            }
            for (let i = 0; i < res.data.temperature.length; i++) {
              for (const key in res.data.temperature[i]) {
                // console.log(key)
                // console.log(res.data.temperature[i][key])
                temp3.push(key.split(' ')[1])
                var temperature
                if (res.data.temperature[i][key] == 0) {
                  temperature = ''
                } else {
                  temperature = res.data.temperature[i][key]
                }
                temp4.push(temperature)
              }
            }
            setTimeout(()=>{
              this.dustPic(temp1,temp2)
              this.temperaturePic(temp3,temp4)
            },100)
          }
        }
      )
    },

    // 获取施工天数
    getDay() {
      this.$axios.post(`/api/project/day?id=${this.projectId}`).then(
        res => {
          console.log(res.data)
          if (res.data.day) {
            this.day = res.data.day
          }
        }
      )
    },

  }
};
</script>

<style lang='less' scoped>
.content {
  padding-top: 0.5rem;
  height: 10.3rem;
  overflow: hidden;
  .wrap-main {
    display: flex;
  }
}
.wrap-left {
  width: 22.22%;
}
.wrap-center {
  flex: 1;
  // width: 52.36%;
  margin-left: 1.4%;
  .mainInfo {
    margin-top: 0.02rem;
    // width: 9.73rem;
    width: 100%;
    height: 0.83rem;
    background: url("../../../static/images/c_top.png") no-repeat center center;
    background-size: 100% 100%;
    color: #fff;
    text-align: center;
    line-height: 0.83rem;
    position: relative;
    .title {
      font-size: 0.26rem;
      font-weight: 600;
    }
    .day {
      font-size: 0.5rem;
      line-height: 0.83rem;
      font-family: "YJZT";
      margin: 0 0.1rem;
      font-weight: normal;
      display: inline-block;
      vertical-align: middle;
    }
    .dormitory {
      width: 1rem;
      height: .3rem;
      background-color: rgb(0, 168, 254);
      position: absolute;
      line-height: .3rem;
      color: #fff;
      right: .1rem;
      top: 50%;
      transform: translateY(-50%);
      border-radius: .05rem;
    }
  }
  .videoInfo {
    margin-top: 0.3rem;
    width: 100%;
    height: 6.14rem;
    background: url("../../../static/images/c_1.png") no-repeat center center;
    background-size: 100% 100%;
    img {
      height: 6.14rem;
      width: 100%;
    }
  }
  .bottomInfo {
    margin-top: 0.3rem;
    width: 100%;
    display: flex;
    .left {
      float: left;
      width: 49%;
      height: 1.94rem;
      background: url("../../../static/images/cd_1.png") no-repeat center center;
      background-size: 100% 100%;
      .outer {
        float: right;
        position: relative;
        transform: translateY(-1.52rem);
        margin-right: 0.45rem;
        width: 1.62rem;
        height: 1.62rem;
        border-radius: 50%;
        border: 0.01rem solid #8c8d9a;
        box-shadow: #8c8d9a 0px 0px 0.18rem 0px inset;
        .line {
          position: absolute;
          left: 50%;
          top: 50%;
          width: 0.01rem;
          height: 0.8rem;
          background-color: #b7b8c7;
          animation: xuanzhuan 10s linear infinite;
          transform-origin: 0 0;
        }
        .dian {
          position: absolute;
          left: 50%;
          top: 50%;
          width: 0.08rem;
          height: 0.08rem;
          background-color: #fff;
          border-radius: 50%;
          transform: translateX(-50%) translateY(-50%);
        }
      }
    }
    .right {
      float: right;
      width: 49%;
      height: 1.94rem;
      background: url("../../../static/images/cd_2.png") no-repeat center center;
      background-size: 100% 100%;
      margin-left: 0.15rem;
      .runtime {
        float: right;
        font-size: 0.22rem;
        color: #fff;
        transform: translateX(-1rem) translateY(-1.3rem);
        text-align: center;
        img {
          transform: translateY(0.09rem);
          width: 0.83rem;
          height: 0.83rem;
        }
        .runtimeBg {
          // position: absolute;
          // top: 60%;
          // margin-left: 0.14rem;
          transform: translateY(-.45rem)
        }
      }
    }
    ul {
      padding-left: 0.15rem;
      li {
        color: #fff;
        font-size: 0.18rem;
        line-height: 0.4rem;
        .sub {
          margin-left: 0.05rem;
        }
      }
    }
  }
}
@keyframes xuanzhuan {
  0% {
    transform: rotate(0deg);
  }
  25% {
    transform: rotate(90deg);
  }
  50% {
    transform: rotate(180deg);
  }
  75% {
    transform: rotate(270deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
.wrap-right {
  width: 22.22%;
  margin-left: 1.4%;
  .code {
    margin-top: 0.02rem;
    width: 4.14rem;
    height: 1.96rem;
    background: url("../../../static/images/r_2.png") no-repeat center center;
    background-size: 100% 100%;
    .search-box {
      margin-top: .12rem;
      padding-left: .18rem;
      input {
        height: .3rem;
        width: 2rem;
        border: .01rem solid #2c61d4;
        border-radius: .03rem;
        background-color: rgba(0, 0, 0, 0);
        padding-left: .1rem;
        color: #fff;
        vertical-align: top;
      }
      .search-button {
        display: inline-block;
        width: .7rem;
        height: .3rem;
        background-image: url('../../../static/images/home-searchButton.png');
        background-position: center center;
        background-repeat: no-repeat;
        background-size: contain;
        margin-left: .15rem;
        text-align: center;
        line-height: .3rem;
        font-size: .16rem;
        color: #fff;
      }
    }
    .hot-search {
      padding-left: .18rem;
      margin-top: .16rem;
      ul {
        padding: 0;
        display: flex;
        flex-wrap: wrap;
        li {
          color: #fff;
          font-size: .14rem;
          a {
            color: #fff;
            font-size: .14rem;
          }
        }
        .option {
          padding-right: .22rem;
        }
      }
    }
  }
  .ech {
    margin-top: 0.3rem;
    width: 4.14rem;
    height: 5.01rem;
    background: url("../../../static/images/r_1.png") no-repeat center center;
    background-size: 100% 100%;
    .status {
      font-size: .12rem;
      position: relative;
      top: -0.34rem;
      left: 1.5rem;
      .good {
        color: #009b41;
        margin-left: 0.5rem;
        &::before {
          content: "";
          position: absolute;
          left: 0.2rem;
          top: 0.05rem;
          width: 0.18rem;
          height: 0.12rem;
          background-color: #009b41;
          border-radius: 0.05rem;
        }
      }
      .notgood {
        color: #fb497c;
        margin-left: 0.5rem;
        &::before {
          content: "";
          position: absolute;
          left: 0.9rem;
          top: 0.06rem;
          width: 0.18rem;
          height: 0.12rem;
          background-color: #fb497c;
          border-radius: 0.05rem;
        }
      }
    }
    .ecCompany {
      width: 100%;
      position: relative;
      .company {
        color: #fff;
        font-size: 0.12rem;
        position: absolute;
        right: 0.1rem;
        bottom: 0.2rem;
      }
      .dust {
        transform: translateY(-0.24rem);
      }
    }
  }
  .electro {
    margin-top: 0.3rem;
    width: 4.14rem;
    height: 1.94rem;
    background: url("../../../static/images/down_2.png") no-repeat center center;
    background-size: 100% 100%;
    .runtime {
      float: right;
      font-size: 0.22rem;
      color: #fff;
      transform: translateX(-1rem) translateY(-1.3rem);
      text-align: center;
      img {
        transform: translateY(0.09rem);
        width: 0.83rem;
        height: 0.83rem;
      }
      .runtimeBg {
        // position: absolute;
        // top: 60%;
        // margin-left: 0.14rem;
        transform: translateY(-.45rem);
      }
    }
  }
  ul {
    padding-left: 0.15rem;
    li {
      color: #fff;
      font-size: 0.18rem;
      line-height: 0.4rem;
      .sub {
        margin-left: 0.05rem;
      }
    }
  }
  .temperaturebox {
    transform: translateY(-0.2rem);
    z-index: 1;
    position: relative;
    .temperaturebox-hour {
      color: #fff;
      position: absolute;
      right: 0.2rem;
      top: 2.05rem;
    }
  }
}
.danger {
  color: #fb497c !important;
}
.noml {
  color: #24e974 !important;
}
.wrap-left .top-show {
  height: 1.96rem;
  background: url("../../../static/images/slidTop.png") no-repeat center center;
  background-size: 100% 100%;
  .jindu {
    position: relative;
    // background-color: #fff;
    width: 2.2rem;
    height: 0.24rem;
    left: 43.5%;
    top: -26%;
    .subjindu {
      position: absolute;
      left: 0%;
      top: 15.6%;
      height: 0.24rem;
      width: 0%;
      background: linear-gradient(to right, #009b41, #00fe15);
      transform: skew(13deg);
    }
    p {
      margin-left: 105%;
      color: #ffffff;
      font-size: 0.12rem;
      transform: skew(-13deg);
    }
  }
}

.work-list {
  display: flex;
  flex-wrap: wrap;
  margin-left: 0.12rem;
  transform: translateY(-18%);
  li {
    width: 50%;
    height: 50%;
    font-size: 0.18rem;
    color: #6e8994;
    line-height: 0.6rem;
    span {
      font-size: 0.3rem;
      color: #347fea;
      margin-left: 0.12rem;
    }
  }
}
.summarize {
  margin-top: 0.3rem;
  height: 5.03rem;
  background: url("../../../static/images/r_1.png") no-repeat center center;
  background-size: 100% 100%;
  overflow: hidden;
  ul {
    margin-top: -0.1rem;
    padding: 0 0.35rem;
    li {
      border-bottom: 0.03rem solid #0f1f53;
      line-height: 346%;
      overflow: hidden;
      a {
        text-align: center;
        font-size: 0.16rem;
        color: #7f9ea8;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        .summarizeContent {
          margin-left: 0.2rem;
        }
      }
    }
  }
  .roll-box {
    padding: 0 0.35rem;
    height: 2.3rem;
    overflow: auto;
  }
  .text-box {
    // padding: 0 0.35rem;
    font-size: 0.16rem;
    color: #7f9ea8;
    margin-top: .15rem;
  }
}
.plate {
  margin-top: 0.3rem;
  height: 1.94rem;
  background: url("../../../static/images/r_2.png") no-repeat center center;
  background-size: 100% 100%;
  overflow: hidden;

  .out_in {
    display: flex;
    color: #fff;
    font-size: 0.18rem;
    padding: 0 0.15rem;
    div {
      margin-right: 0.4rem;
    }
  }
  .case {
    display: flex;
    padding: 0 0.15rem;
    margin-top: 0.17rem;
    .left {
      width: 0.18rem;
      height: 0.76rem;
      font-size: 0.18rem;
      line-height: 0.2rem;
      color: #0184ff;
    }
    .right {
      color: #7f9ea8;
      overflow: hidden;
      margin-left: 0.2rem;
      ul {
        transform: translateY(-0.05rem);
        li {
          line-height: 0.26rem;
          span {
            margin-right: 0.16rem;
          }
        }
      }
    }
  }
}
</style>
