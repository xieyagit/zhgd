<template>
  <div id="systemZhiHui_shenJiKeng">
    <div class="content">
      <!-- 主体左部 -->
      <div class="left-main">
        <!-- 按钮区域 -->
        <div class="button-box">
          <div class="top-button">
            <a class="history" @click="alarmShow=false;historyShow=true,historyClick()" v-show="!historyShow">
              <i class="icon"></i>
              历史记录
            </a>
            <a class="history" @click="alarmShow=false;historyShow=false" v-show="historyShow">
              <i class="icon"></i>
              实时数据
            </a>
            <a class="cut" @click="dialog=!dialog,showBackGround=!showBackGround">
              <i class="icon"></i>
              切换基坑
            </a>
            <a class="derive">
              <i class="icon"></i>
              导出Excel
            </a>
          </div>
          <div class="botton-button">
            <a class="alarm" @click="alarmShow=true,selectUserAlarms('today'),getStationList()">
              <i class="icon"></i>
              设备报警
            </a>
          </div>
        </div>
        <!-- 警报状态 -->
        <div class="alarm-box">
          <div class="title">
            <span style="font-weight: bolder;">警报状态</span>
            <span>报警数：{{allOfAlarm}}</span>
          </div>
          <div class="alarm-chart" id="alarmChart"></div>
          <div class="list-box">
            <ul v-if="allAlarmList.length>0">
              <li v-for="item in allAlarmList" :key="item.id">
                <span>{{item.sourceName}}于{{getAlarmTime(item.endTime)}}产生{{item.level}}级{{item.content}}告警</span>
                <!-- <a>确认</a> -->
                <!-- <span>1号深基坑于2018-02-08产生1级超阀值告警</span>
                <a>确认</a> -->
              </li>
            </ul>
          </div>
        </div>
        <!-- 设备列表 -->
      </div>
      <!-- 主体右部 -->
      <div class="right-main">
        <!-- 实时数据 -->
        <div class="home-page" v-show="!alarmShow">
          <!-- 头部 -->
          <div class="title">
            <div class="name">
              <i class="icon"></i>
              {{ deviceName }}
            </div>
            <div class="rank">
              <i class="dot"></i>
              暂无数据
            </div>
            <div class="rank">
              <i class="dot normal"></i>
              数据正常
            </div>
            <div class="rank">
              <i class="dot three"></i>
              三级预警
            </div>
            <div class="rank">
              <i class="dot two"></i>
              二级预警
            </div>
            <div class="rank">
              <i class="dot one"></i>
              一级预警
            </div>
          </div>
          <!-- 导航栏 -->
          <div class="nav">
            <i class="el-icon-arrow-left" @click="scrollRight" v-if="factorList.length>4"></i>
            <div class="scroll-wrap" id="scrollWrap">
              <div class="scroll-inner">
                <a @click="navClick(1)" :class="selectShow==1?'active':''" v-if="stage!=''">地下水位</a>
                <a @click="navClick(2)" :class="selectShow==2?'active':''" v-if="offset!=''">水平位移</a>
                <a @click="navClick(3)" :class="selectShow==3?'active':''" v-if="subside!=''">周边沉降</a>
                <!-- <a @click="navClick(4)" :class="selectShow==4?'active':''">支撑轴力</a> -->

                <a @click="navClick(5)" :class="selectShow==5?'active':''" v-if="product!=''">结构应变</a>
                <a @click="navClick(6)" :class="selectShow==6?'active':''" v-if="bias!=''">建筑倾斜</a>
              </div>
            </div>
            <i class="el-icon-arrow-right" @click="scrollLeft" v-if="factorList.length>4"></i>
          </div>
          <!-- 地下水位实时数据 -->
          <div class="main" v-show="selectShow==1&&!historyShow" v-if="stage!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="stageListChild" placeholder="请选择" @change="getStage(1, 4),getStageFourHoverList(),getStageMax()">
                  <el-option
                    v-for="item in stageList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图</span>
                <span style="width:2.4rem;">当日数据列表</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    {{filterStage(stageListChild)}}
                  </div>
                  <ul>
                    <li>基坑：{{deviceName}}</li>
                    <li>最大值：{{stageMaxList.max}}m</li>
                    <li>最小值：{{stageMaxList.min}}m</li>
                    <li>平均值：{{stageMaxList.avg}}m</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="oneChart"></div>
                <!-- 当日数据列表 -->
                <div class="right-box">
                  <ul>
                    <li>
                      <span style="width:1.4rem;">时间</span>
                      <span style="width:2rem;">地下水位(m)</span>
                      <!-- <span style="width:.4rem;">状态</span> -->
                    </li>
                    <li>
                      <span style="width:1.4rem;">00:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[0]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">04:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[1]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">08:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[2]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">12:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[3]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">16:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[4]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">20:00</span>
                      <span style="width:2rem;">{{stageFourHoverList[5]}}</span>
                    </li>
                    <!-- <li>
                      <span style="width:2rem;">0.63</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">0.5</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li> -->
                  </ul>
                </div>
              </div>
              <div class="table-height">
                <span style="width:33%;">检测点</span>
                <span style="width:33%;">地下水位(m)</span>
                <span style="width:33%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li v-for="(item, index) in stageTable" :key="item.id" :class="{'even':index%2!=0}">
                    <span style="width:33%;">{{filterStage(item.factorId)}}</span>
                    <span style="width:33%;">{{item.waterLevel}}</span>
                    <span style="width:33%;">{{item.creation}}</span>
                  </li>
                  <!-- <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">0.5</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">1.2</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">0.5</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>
          <!-- 水平位移实时数据 -->
          <div class="main" v-show="selectShow==2&&!historyShow" v-if="offset!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="offsetListChild" placeholder="请选择" @change="getOffset(1, 4),getOffsetFourHoverList()">
                  <el-option
                    v-for="item in offsetList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图</span>
                <span style="width:2.4rem;">当日数据列表</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    {{filterOffset(offsetListChild)}}
                  </div>
                  <ul v-if="offsetTable.length>0">
                    <li>基坑：{{deviceName}}</li>
                    <li>X方向累计位移：{{offsetTable[offsetTable.length-1].levelAccumulateX}}mm</li>
                    <li>Y方向累计位移：{{offsetTable[offsetTable.length-1].levelAccumulateY}}mm</li>
                    <li>X方向位移：{{offsetTable[offsetTable.length-1].levelX}}mm</li>
                    <li>Y方向位移：{{offsetTable[offsetTable.length-1].levelY}}mm</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="twoChart"></div>
                <!-- 当日数据列表 -->
                <div class="right-box">
                  <ul>
                    <li>
                      <span style="width:1.4rem;">时间</span>
                      <span style="width:2rem;">X方向(mm)</span>
                      <span style="width:2rem;">Y方向(mm)</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">00:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[0]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[0]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">04:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[1]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[1]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">08:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[2]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[2]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">12:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[3]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[3]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">16:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[4]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[4]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">20:00</span>
                      <span style="width:2rem;">{{offsetFourHoverListX[5]}}</span>
                      <span style="width:2rem;">{{offsetFourHoverListY[5]}}</span>
                    </li>
                    <!-- <li>
                      <span style="width:2rem;">30</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">20</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li> -->
                  </ul>
                </div>
              </div>
              <div class="table-height">
                <span style="width:25%;">检测点</span>
                <span style="width:25%;">X方向位移(mm)</span>
                <span style="width:25%;">Y方向位移(mm)</span>
                <span style="width:25%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li v-for="(item, index) in offsetTable" :key="item.id" :class="{'even':index%2!=0}">
                    <span style="width:25%;">{{filterOffset(item.factorId)}}</span>
                    <span style="width:25%;">{{item.levelX}}</span>
                    <span style="width:25%;">{{item.levelY}}</span>
                    <span style="width:25%;">{{item.creation}}</span>
                  </li>
                  <!-- <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">20</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">30</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">25</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>
          <!-- 周边沉降实时数据 -->
          <div class="main" v-show="selectShow==3&&!historyShow" v-if="subside!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="subsideListChild" placeholder="请选择" @change="getSubside(1, 4),getSubsideFourHoverList(),getSubsideMax()">
                  <el-option
                    v-for="item in subsideList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图</span>
                <span style="width:2.4rem;">当日数据列表</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    {{filterSubside(subsideListChild)}}
                  </div>
                  <ul>
                    <li>基坑：{{deviceName}}</li>
                    <li>最大值：{{subsideMaxList.max}}mm</li>
                    <li>最小值：{{subsideMaxList.min}}mm</li>
                    <li>平均值：{{subsideMaxList.avg}}mm</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="threeChart"></div>
                <!-- 当日数据列表 -->
                <div class="right-box">
                  <ul>
                    <li>
                      <span style="width:1.4rem;">时间</span>
                      <span style="width:2rem;">周边沉降(mm)</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">00:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[0]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">04:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[1]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">08:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[2]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">12:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[3]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">16:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[4]}}</span>
                    </li>
                    <li>
                      <span style="width:1.4rem;">20:00</span>
                      <span style="width:2rem;">{{subsideFourHoverList[5]}}</span>
                    </li>
                    <!-- <li>
                      <span style="width:2rem;">20</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">30</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li> -->
                  </ul>
                </div>
              </div>
              <div class="table-height">
                <span style="width:33%;">检测点</span>
                <span style="width:33%;">周边沉降(mm)</span>
                <span style="width:33%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li v-for="(item, index) in subsideTable" :key="item.id" :class="{'even':index%2!=0}">
                    <span style="width:33%;">{{filterSubside(item.factorId)}}</span>
                    <span style="width:33%;">{{item.subside}}</span>
                    <span style="width:33%;">{{item.creation}}</span>
                  </li>
                  <!-- <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">20</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">30</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">25</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>
          <!-- 支撑轴力实时数据 暂时未开放 -->
          <div class="main" v-show="selectShow==4&&!historyShow">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="value4" placeholder="请选择">
                  <el-option
                    v-for="item in options4"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图</span>
                <span style="width:2.4rem;">当日数据列表</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    <i class="dot"></i>
                    ZL-01
                  </div>
                  <ul>
                    <li>深基坑：1号深基坑</li>
                    <li>最大值：100%FN</li>
                    <li>最小值：20%FN</li>
                    <li>平均值：60%FN</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="fourChart"></div>
                <!-- 当日数据列表 -->
                <div class="right-box">
                  <ul>
                    <li>
                      <span style="width:2rem;">支撑轴力(FN)</span>
                      <span style="width:1rem;">时间</span>
                      <span style="width:.4rem;">状态</span>
                    </li>
                    <li>
                      <span style="width:2rem;">25%</span>
                      <span style="width:1rem;">00:00</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">20%</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">30%</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="table-height">
                <span style="width:33%;">检测点</span>
                <span style="width:33%;">支撑轴力(FN)</span>
                <span style="width:33%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li>
                    <span style="width:33%;">CJ-01</span>
                    <span style="width:33%;">10%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">20%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">100%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">25%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- 结构应变实时数据 -->
          <div class="main" v-show="selectShow==5&&!historyShow" v-if="product!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="productListChild" placeholder="请选择" @change="getProduct(1, 4),getProductFourHoverList()">
                  <el-option
                    v-for="item in productList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图(HZ)</span>
                <span style="width:2.4rem;">当日数据趋势图(℃)</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    {{filterProduct(productListChild)}}
                  </div>
                  <ul v-if="productTable.length>0">
                    <li>基坑：{{deviceName}}</li>
                    <li>实时频率：{{productTable[productTable.length-1].strainFrequency}}HZ</li>
                    <li>实时温度：{{productTable[productTable.length-1].strainTemperature}}℃</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="structureFrequency"></div>
                <!-- 当日数据列表 -->
                <!-- <div class="right-box"> -->
                <div class="line-chart" id="structureTemperature"></div>
                  <!-- <ul>
                    <li>
                      <span style="width:2rem;">支撑轴力(FN)</span>
                      <span style="width:1rem;">时间</span>
                      <span style="width:.4rem;">状态</span>
                    </li>
                    <li>
                      <span style="width:2rem;">25%</span>
                      <span style="width:1rem;">00:00</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">20%</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">30%</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                  </ul> -->
                <!-- </div> -->
              </div>
              <div class="table-height">
                <span style="width:25%;">检测点</span>
                <span style="width:25%;">频率（HZ）</span>
                <span style="width:25%;">温度（℃）</span>
                <span style="width:25%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li v-for="(item, index) in productTable" :key="item.id" :class="{'even':index%2!=0}">
                    <span style="width:25%;">{{filterProduct(item.factorId)}}</span>
                    <span style="width:25%;">{{item.strainFrequency}}</span>
                    <span style="width:25%;">{{item.strainTemperature}}</span>
                    <span style="width:25%;">{{item.creation}}</span>
                  </li>
                  <!-- <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">20%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">100%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">25%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>
          <!-- 建筑倾斜实时数据 -->
          <div class="main" v-show="selectShow==6&&!historyShow" v-if="bias!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="biasListChild" placeholder="请选择" @change="getBias(1, 4)">
                  <el-option
                    v-for="item in biasList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </div>
              <!-- <div class="right-search">
                <div class="input-box">
                  <input type="text" placeholder="请输入测点名称搜索" />
                  <a class="el-icon-search"></a>
                </div>
              </div> -->
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图（X轴方向）</span>
                <span style="paddingRight:1rem;">当日数据趋势图（Y轴方向）</span>
              </div>
              <div class="top-box">
                <!-- 检测点 -->
                <div class="left-box">
                  <div class="name">
                    {{filterBias(biasListChild)}}
                  </div>
                  <ul v-if="biasTable.length>0">
                    <li>基坑：{{deviceName}}</li>
                    <li>X轴方向倾斜：{{biasTable[biasTable.length-1].tiltX}}°</li>
                    <li>Y轴方向倾斜：{{biasTable[biasTable.length-1].tiltY}}°</li>
                  </ul>
                </div>
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="slantX"></div>
                <!-- 当日数据列表 -->
                <div class="line-chart" id="slantY"></div>
                <!-- <div class="right-box">
                  <ul>
                    <li>
                      <span style="width:2rem;">支撑轴力(FN)</span>
                      <span style="width:1rem;">时间</span>
                      <span style="width:.4rem;">状态</span>
                    </li>
                    <li>
                      <span style="width:2rem;">25%</span>
                      <span style="width:1rem;">00:00</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">20%</span>
                      <span style="width:1rem;">12:12</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                    <li>
                      <span style="width:2rem;">30%</span>
                      <span style="width:1rem;">13:13</span>
                      <span style="width:.4rem;">
                        <i class="dot"></i>
                      </span>
                    </li>
                  </ul>
                </div> -->
              </div>
              <div class="table-height">
                <span style="width:25%;">检测点</span>
                <span style="width:25%;">倾斜方向X（°）</span>
                <span style="width:25%;">倾斜方向Y（°）</span>
                <span style="width:25%;">采集时间</span>
              </div>
              <div class="bottom-box">
                <ul>
                  <li v-for="(item, index) in biasTable" :key="item.id" :class="{'even':index%2!=0}">
                    <span style="width:25%;">{{filterBias(item.factorId)}}</span>
                    <span style="width:25%;">{{item.tiltX}}</span>
                    <span style="width:25%;">{{item.tiltY}}</span>
                    <span style="width:25%;">{{item.creation}}</span>
                  </li>
                  <!-- <li class="even">
                    <span style="width:33%;">JH-01</span>
                    <span style="width:33%;">20%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li>
                    <span style="width:33%;">JW-01</span>
                    <span style="width:33%;">100%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li>
                  <li class="even">
                    <span style="width:33%;">WU-01</span>
                    <span style="width:33%;">25%</span>
                    <span style="width:33%;">2018-02-06 12:00:00</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>

          <!-- 地下水位历史数据 -->
          <div class="main" v-show="selectShow==1&&historyShow" v-if="stage!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="stageListChild" placeholder="请选择" @change="getStageHistory('today')">
                  <el-option
                    v-for="item in stageList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span> </span>
                <span>当日数据趋势图</span>
                <span> </span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historyOne"></div>
              </div>
              <div class="table-box" :style="historyTableChange">
                <div class="table-wrap">
                  <el-table :data="stageHistoryTable" stripe border>
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                    <el-table-column label="检测点">
                      <template slot-scope="scope">
                        {{filterStage(scope.row.factorId)}}
                      </template>
                    </el-table-column>
                    <el-table-column prop="waterLevel" label="地下水位（m）"></el-table-column>
                    <!-- <el-table-column>
                      <template slot-scope="scope">
                        <div
                          :style="`color:${scope.row.status==0?'#fe8990':'#58de87'}`"
                        >{{scope.row.status==0?'不合格':'合格'}}</div>
                      </template>
                    </el-table-column> -->
                    <el-table-column prop="creation" label="时间"></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handlePageNum"
                    :current-page="pageNum"
                    :page-size="historyPageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="historyStageTolal"
                  ></el-pagination>
                  <div class="notSeeMore" @click="historyTableChangeHeight()">收起</div>
                </div>
                <div class="seeMore" v-show="seeMore"></div>
              </div>
              <div class="seeMoreClick" @click="historyTableChangeHeight('add')" v-show="seeMore">查看更多数据</div>
            </div>
          </div>
          <!-- 水平位移历史数据 -->
          <div class="main" v-show="selectShow==2&&historyShow" v-if="offset!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="offsetListChild" placeholder="请选择" @change="getOffsetHistory('today')">
                  <el-option
                    v-for="item in offsetList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span> </span>
                <span>当日数据趋势图</span>
                <span> </span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historyTwo"></div>
              </div>
              <div class="table-box" :style="historyTableChange">
                <div class="table-wrap">
                  <el-table :data="offsetHistoryTable" stripe border>
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                    <el-table-column label="检测点" width="150">
                      <template slot-scope="scope">
                        {{filterOffset(scope.row.factorId)}}
                      </template>
                    </el-table-column>
                    <el-table-column prop="levelX" label="X轴位移（mm）" width="150"></el-table-column>
                    <el-table-column prop="levelY" label="Y轴位移（mm）" width="150"></el-table-column>
                    <el-table-column prop="levelAccumulateX" label="X轴累积（mm）" width="150"></el-table-column>
                    <el-table-column prop="levelAccumulateY" label="Y轴累积（mm）" width="150"></el-table-column>
                    <el-table-column prop="creation" label="时间"></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handlePageNum"
                    :current-page="pageNum"
                    :page-size="historyPageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="historyOffsetTolal"
                  ></el-pagination>
                  <div class="notSeeMore" @click="historyTableChangeHeight()">收起</div>
                </div>
                <div class="seeMore" v-show="seeMore"></div>
              </div>
              <div class="seeMoreClick" @click="historyTableChangeHeight('add')" v-show="seeMore">查看更多数据</div>
            </div>
          </div>
          <!-- 周边沉降历史数据 -->
          <div class="main" v-show="selectShow==3&&historyShow" v-if="subside!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="subsideListChild" placeholder="请选择" @change="getSubsideHistory('today')">
                  <el-option
                    v-for="item in subsideList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span> </span>
                <span>当日数据趋势图</span>
                <span> </span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historyThree"></div>
              </div>
              <div class="table-box" :style="historyTableChange">
                <div class="table-wrap">
                  <el-table :data="subsideHistoryTable" stripe border>
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                    <el-table-column label="检测点" width="150">
                      <template slot-scope="scope">
                        {{filterSubside(scope.row.factorId)}}
                      </template>
                    </el-table-column>
                    <el-table-column prop="subside" label="周边沉降(mm)" width="150"></el-table-column>
                    <el-table-column prop="creation" label="时间"></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handlePageNum"
                    :current-page="pageNum"
                    :page-size="historyPageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="historySubsideTolal"
                  ></el-pagination>
                  <div class="notSeeMore" @click="historyTableChangeHeight()">收起</div>
                </div>
                <div class="seeMore" v-show="seeMore"></div>
              </div>
              <div class="seeMoreClick" @click="historyTableChangeHeight('add')" v-show="seeMore">查看更多数据</div>
            </div>
          </div>
          <!-- 支撑轴力历史数据 暂时未开发 -->
          <div class="main" v-show="selectShow==4&&historyShow">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="value" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span>检测点</span>
                <span>当日数据趋势图</span>
                <span style="width:2.4rem;">当日数据列表</span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historyFour"></div>
              </div>
              <div class="table-box">
                <el-table :data="historyTableData4" stripe border>
                  <el-table-column type="selection" width="40"></el-table-column>
                  <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                  <el-table-column prop="name" label="检测点" width="150"></el-table-column>
                  <el-table-column label="支撑轴力（FN）" width="150">
                    <template slot-scope="scope">
                      <div :style="`color:${scope.row.status==0?'#fe8990':''}`">{{scope.row.level}}</div>
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template slot-scope="scope">
                      <div
                        :style="`color:${scope.row.status==0?'#fe8990':'#58de87'}`"
                      >{{scope.row.status==0?'不合格':'合格'}}</div>
                    </template>
                  </el-table-column>
                  <el-table-column prop="time" label="时间"></el-table-column>
                </el-table>
              </div>
            </div>
          </div>
          <!-- 结构应变历史数据 -->
          <div class="main" v-show="selectShow==5&&historyShow" v-if="product!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="productListChild" placeholder="请选择" @change="getProductHistory('today')">
                  <el-option
                    v-for="item in productList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span> </span>
                <span>当日数据趋势图</span>
                <span> </span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historyFive"></div>
              </div>
              <div class="table-box" :style="historyTableChange">
                <div class="table-wrap">
                  <el-table :data="productHistoryTable" stripe border>
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                    <el-table-column label="检测点" width="150">
                      <template slot-scope="scope">
                        {{filterProduct(scope.row.factorId)}}
                      </template>
                    </el-table-column>
                    <el-table-column prop="strainFrequency" label="应变频率（HZ）" width="150"></el-table-column>
                    <el-table-column prop="strainTemperature" label="应变温度（℃）" width="150"></el-table-column>
                    <el-table-column prop="creation" label="时间"></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handlePageNum"
                    :current-page="pageNum"
                    :page-size="historyPageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="historyProductTolal"
                  ></el-pagination>
                  <div class="notSeeMore" @click="historyTableChangeHeight()">收起</div>
                </div>
                <div class="seeMore" v-show="seeMore"></div>
              </div>
              <div class="seeMoreClick" @click="historyTableChangeHeight('add')" v-show="seeMore">查看更多数据</div>
            </div>
          </div>
          <!-- 建筑倾斜历史数据 -->
          <div class="main" v-show="selectShow==6&&historyShow" v-if="bias!=''">
            <!-- 搜索栏 -->
            <div class="search-box">
              <div class="left-search">
                测点：
                <el-select v-model="biasListChild" placeholder="请选择" @change="getBiasHistory('today')">
                  <el-option
                    v-for="item in biasList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>起止时间：
                <el-date-picker
                  v-model="searchTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                ></el-date-picker>
              </div>
              <a class="search-btn" @click="searchClick">搜索</a>
            </div>
            <!-- 数据内容 -->
            <div class="data-box">
              <div class="table-height">
                <span> </span>
                <span>当日数据趋势图</span>
                <span> </span>
              </div>
              <div class="top-box">
                <!-- 当日数据趋势图 -->
                <div class="line-chart" id="historySix"></div>
              </div>
              <div class="table-box" :style="historyTableChange">
                <div class="table-wrap">
                  <el-table :data="biasHistoryTable" stripe border>
                    <el-table-column type="selection" width="40"></el-table-column>
                    <el-table-column type="index" label="序号" width="100" :index="indexMethod"></el-table-column>
                    <el-table-column label="检测点" width="150">
                      <template slot-scope="scope">
                        {{filterBias(scope.row.factorId)}}
                      </template>
                    </el-table-column>
                    <el-table-column prop="tiltX" label="X方向倾斜(°)" width="150"></el-table-column>
                    <el-table-column prop="tiltY" label="Y方向倾斜(°)" width="150"></el-table-column>
                    <el-table-column prop="creation" label="时间"></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handlePageNum"
                    :current-page="pageNum"
                    :page-size="historyPageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="historyBiasTolal"
                  ></el-pagination>
                  <div class="notSeeMore" @click="historyTableChangeHeight()">收起</div>
                </div>
                <div class="seeMore" v-show="seeMore"></div>
              </div>
              <div class="seeMoreClick" @click="historyTableChangeHeight('add')" v-show="seeMore">查看更多数据</div>
            </div>
          </div>
        </div>
        <!-- 设备警报 -->
        <div class="alarm-box" v-show="alarmShow">
          <div class="search-box">
            <div class="left-box">
              <!-- <el-select v-model="searchValue" placeholder="请选择">
                <el-option
                  v-for="item in searchOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select> -->
              <el-radio v-model="radio" label="1">实时</el-radio>
              <el-radio v-model="radio" label="2">历史</el-radio>
              <!-- <input type="text" placeholder="请输入测点名称搜索" /> -->
              <el-select v-model="searchValue" placeholder="请选择警告源" clearable>
                <el-option
                  v-for="item in stationList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </div>
            <a class="search-btn" @click="searchStation">搜索</a>
          </div>
          <div class="table-box">
            <el-table :data="alarmList" stripe>
              <el-table-column label="结构物">
                <template>{{deviceName}}</template>
              </el-table-column>
              <el-table-column prop="sourceName" label="警告源" width="200"></el-table-column>
              <el-table-column prop="level" label="警告等级" width="150"></el-table-column>
              <el-table-column prop="content" label="警告信息" width="200"></el-table-column>
              <!-- <el-table-column prop="number" label="警告次数" width="150"></el-table-column> -->
              <el-table-column prop="endTime" label="告警时间"></el-table-column>
              <!-- <el-table-column prop="type" label="操作" width="110">
                <template>
                  <a>确认</a>
                </template>
              </el-table-column> -->
            </el-table>
          </div>
          <div class="paging-box">
            <!-- @size-change="handleSizeChange"
            @current-change="handleCurrentChange"-->
            <el-pagination
              @current-change="searchStation"
              :current-page="alarmNum"
              :page-size="alarmSize"
              layout="total, prev, pager, next, jumper"
              :total="alarmListTotal"
            ></el-pagination>
          </div>
        </div>
      </div>
      <div class="dialog-box" v-show="dialog">
        <div class="title">
          切换基坑
          <i class="el-icon-close hand" @click="dialog=!dialog,showBackGround=!showBackGround"></i>
        </div>
        <div class="body">
          <span>请选择基坑:</span>
          <el-select v-if="handOverList.length>0" v-model="structureIdChange">
            <el-option
              v-for="item in handOverList"
              :label = item.label
              :value = item.value
              :key = item.value
            ></el-option>
          </el-select>
        </div>
        <div class="footer">
          <div class="button" @click="structureIdChangeClick">确定</div>
        </div>
      </div>
      <div class="backGround" :class="{'block':showBackGround}"></div>
    </div>
  </div>
</template>

<style lang="less" scoped>
@import '~@/assets/current';
#systemZhiHui_shenJiKeng {
  width: 100%;
  .content {
    border-radius: 0.04rem;
    background-color: #fff;
    box-shadow: 0 0 0.5rem -0.3rem #666;
    display: flex;
    .left-main {
      width: 4.1rem;
      height: 9.5rem;
      border-right: 0.1rem solid #f7f7f7;
      .button-box {
        height: 1.45rem;
        padding-top: 0.25rem;
        border-bottom: 0.1rem solid #f7f7f7;
        .botton-button {
          a {
            margin-top: 0.25rem;
          }
        }
        a {
          float: left;
          height: 0.3rem;
          color: #0090ff;
          font-size: 0.12rem;
          transition: all 0.5s;
          margin-left: 0.24rem;
          line-height: 0.28rem;
          border-radius: 0.02rem;
          padding-right: 0.09rem;
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
            vertical-align: top;
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
        .history {
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
        .alarm {
          .icon {
            background-image: url("../../../../static/images/system-alarm.png");
          }
          &:hover {
            .icon {
              background-image: url("../../../../static/images/system-alarmHover.png");
            }
          }
        }
      }
      .alarm-box {
        height: 8rem;
        overflow-y: auto;
        .title {
          color: #4a4a4a;
          font-size: 0.18rem;
          height: 0.52rem;
          line-height: 0.52rem;
          display: flex;
          padding: 0 0.21rem;
          justify-content: space-between;
        }
        .alarm-chart {
          height: 3.5rem;
        }
        .list-box {
          padding: 0 0.21rem;
          ul {
            li {
              display: flex;
              justify-content: space-between;
              font-size: 0.14rem;
              color: #4a4a4a;
              height: 0.41rem;
              line-height: 0.41rem;
              a {
                font-size: 0.14rem;
                color: #0090ff;
              }
            }
          }
        }
      }
    }
    .right-main {
      flex: 1;
      height: 9.5rem;
      vertical-align: top;
      .home-page {
        .title {
          // display: flex;
          height: 0.85rem;
          padding: 0 0.5rem;
          // justify-content: space-between;
          border-bottom: 0.1rem solid #f7f7f7;
          .name {
            color: #4a4a4a;
            height: 0.75rem;
            font-size: 0.24rem;
            line-height: 0.75rem;
            display: inline-block;
            .icon {
              display: inline-block;
              width: 0.24rem;
              height: 0.75rem;
              margin-right: 0.12rem;
              vertical-align: top;
              background-image: url("../../../../static/images/systemZhiHui-gaoZhiMoGreen.png");
              background-position: center center;
              background-repeat: no-repeat;
              background-size: contain;
            }
          }
          .rank {
            float:right;
            height: 0.75rem;
            color: #4a4a4a;
            font-size: 0.18rem;
            line-height: 0.75rem;
            margin-left: 0.15rem;
            .dot {
              width: 0.08rem;
              height: 0.08rem;
              border-radius: 0.08rem;
              background-color: #929292;
              margin-right: 0.1rem;
              display: inline-block;
              vertical-align: middle;
            }
            .one {
              background-color: #ff7a81;
            }
            .two {
              background-color: #feb27e;
            }
            .three {
              background-color: #ffde2a;
            }
            .normal {
              background-color: #3ada76;
            }
          }
        }
        .nav {
          display: flex;
          height: 0.85rem;
          padding: 0 0.7rem;
          // justify-content: space-between;
          border-bottom: 0.1rem solid #f7f7f7;
          position: relative;
          .scroll-wrap {
            width: 11.18rem;
            overflow-x: scroll;
            overflow-y: hidden;
            .scroll-inner {
              height: 100%;
              white-space: nowrap;
              a {
                height: 0.75rem;
                width: 1.04rem;
                color: #4a4a4a;
                font-size: 0.26rem;
                font-weight: bolder;
                line-height: 0.75rem;
                margin-right: 2.25rem;
              }
              a:last-child {
                margin-right: 0;
              }
              .active {
                color: #858585;
                height: 0.8rem;
                border-bottom: 0.05rem solid #3eaafd;
              }
            }
          }
          .scroll-wrap::-webkit-scrollbar { width: 0 !important }
          .el-icon-arrow-left, .el-icon-arrow-right {
            font-size: 0.3rem;
            position: absolute;
            line-height: 0.75rem;
          }
          .el-icon-arrow-left {
            left: 0.3rem;
          }
          .el-icon-arrow-right {
            right: 0.3rem;
          }
          .el-icon-arrow-left:hover, .el-icon-arrow-right:hover {
            text-shadow: 2px 2px 4px #000;
            cursor: pointer;
          }
        }
        .main {
          .search-box {
            height: 0.9rem;
            padding: 0 0.38rem;
            display: flex;
            justify-content: space-between;
            .left-search {
              height: 0.9rem;
              color: #4a4a4a;
              font-size: 0.18rem;
              line-height: 0.9rem;
              .el-select {
                margin-right: 0.8rem;
                input {
                  width: 2.6rem;
                  height: 0.4rem;
                  color: #4a4a4a;
                  border-radius: 0.04rem;
                  border: 0.01rem solid #b6b6b6;
                  padding-left: 0.1rem;
                }
              }
              .el-date-editor {
                > span {
                  width: auto;
                }
              }
            }
            .right-search {
              height: 0.9rem;
              // line-height: .9rem;
              .input-box {
                width: 3.4rem;
                height: 0.4rem;
                border-radius: 0.04rem;
                border: 0.01rem solid #b6b6b6;
                transform: translateY(0.26rem);
                input {
                  height: 0.38rem;
                  font-size: 0.18rem;
                  padding-left: 0.1rem;
                  line-height: 0.38rem;
                  width: 3rem;
                }
                a {
                  display: inline-block;
                  width: 0.38rem;
                  height: 0.38rem;
                  font-size: 0.2rem;
                  text-align: center;
                  line-height: 0.38rem;
                  &:hover {
                    color: #fff;
                    background-color: #ccc;
                  }
                }
              }
            }
            .search-btn {
              color: #fff;
              height: 0.4rem;
              width: 1.63rem;
              font-size: 0.2rem;
              text-align: center;
              transition: all 0.5s;
              line-height: 0.38rem;
              border-radius: 0.02rem;
              background-color: #ffd14f;
              transform: translateY(0.25rem);
              border: 0.01rem solid #d4ad40;
              &:hover {
                background-color: #d4ad40;
              }
            }
          }
          .data-box {
            padding: 0 0.38rem;
            position: relative;
            height: 6.9rem;
            overflow-y: hidden;
            .table-height {
              height: 0.6rem;
              display: flex;
              color: #5f676c;
              padding: 0 0.22rem;
              background-color: #c5e8ff;
              justify-content: space-between;
              span {
                font-size: 0.18rem;
                line-height: 0.6rem;
              }
            }
            .top-box {
              height: 3.2rem;
              display: flex;
              .left-box {
                height: 100%;
                width: 2.8rem;
                padding-top: 0.3rem;
                .name {
                  font-size: 0.18rem;
                  margin-bottom: 0.06rem;
                  padding-left: 0.1rem;
                  .dot {
                    width: 0.08rem;
                    height: 0.08rem;
                    margin-right: 0.14rem;
                    display: inline-block;
                    border-radius: 0.08rem;
                    vertical-align: middle;
                    background-color: #3ada76;
                  }
                }
                ul {
                  padding-left: 0.3rem;
                  li {
                    height: 0.4rem;
                    font-size: 0.18rem;
                    line-height: 0.4rem;
                  }
                }
              }
              .line-chart {
                flex: 1;
              }
              .right-box {
                width: 4rem;
                height: 100%;
                padding-top: 0.3rem;
                ul {
                  li {
                    text-align: center;
                    display: flex;
                    color: #4a4a4a;
                    font-size: 0.18rem;
                    height: 0.35rem;
                    line-height: 0.35rem;
                    span {
                      .dot {
                        width: 0.08rem;
                        height: 0.08rem;
                        margin-left: 0.16rem;
                        border-radius: 0.08rem;
                        display: inline-block;
                        background-color: #3ada76;
                      }
                    }
                  }
                }
              }
            }
            .bottom-box {
              ul {
                li {
                  height: 0.6rem;
                  display: flex;
                  padding: 0 0.22rem;
                  justify-content: space-between;
                  span {
                    color: #4a4a4a;
                    font-size: 0.18rem;
                    line-height: 0.6rem;
                  }
                }
                .even {
                  background-color: #f8f9fb;
                }
              }
            }
            .table-box {
              height: 2.5rem;
              overflow: hidden;
              position: relative;
              .table-wrap {
                /deep/.el-table {
                  height: 5.5rem;
                  margin-bottom: 0.1rem;
                  content:'';
                  clear: both;
                  display: block;
                  width: 100%;
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
                    color: #fd5101;
                  }
                  .yellow-color {
                    color: #ffd14f;
                  }
                }
                .el-pagination {
                  float: right;
                  margin-top: 0.05rem;
                }
                .notSeeMore {
                  float: left;
                  margin-top: 0.05rem;
                  font-size: 0.16rem;
                  cursor: pointer;
                }
                .notSeeMore:hover {
                  color: red;
                }
              }
              .seeMore {
                  position: absolute;
                  bottom: 0rem;
                  height: 0.3rem;
                  width: 100%;
                  z-index: 10;
                  background: #464646;
                  filter: blur(40px);
                }
            }
            .seeMoreClick {
              line-height: 0.3rem;
              width: 100%;
              text-align: center;
              cursor: pointer;
            }
            .seeMoreClick:hover {
              color: red;
            }
          }
        }
      }
      .alarm-box {
        .search-box {
          height: 0.9rem;
          padding: 0 0.38rem;
          display: flex;
          justify-content: space-between;
          .left-box {
            padding-top: 0.25rem;
            .el-select {
              margin-right: 0.3rem;
              .el-input__inner {
                width: 2.6rem;
                height: 0.4rem;
                border-radius: 0.04rem;
                border: 0.01rem solid #b6b6b6;
              }
            }
            > input {
              width: 3rem;
              height: 0.4rem;
              border-radius: 0.04rem;
              border: 0.01rem solid #b6b6b6;
              padding-left: 0.1rem;
            }
            // input:-ms-input-placeholder,
            input::-webkit-input-placeholder {
              color: #bebebe;
            }
          }
          .search-btn {
            color: #fff;
            height: 0.4rem;
            width: 1.63rem;
            font-size: 0.2rem;
            text-align: center;
            transition: all 0.5s;
            line-height: 0.38rem;
            border-radius: 0.02rem;
            background-color: #ffd14f;
            transform: translateY(0.25rem);
            border: 0.01rem solid #d4ad40;
            &:hover {
              background-color: #d4ad40;
            }
          }
        }
        .table-box {
          width: 100%;
          padding: 0 0.38rem;
          height: 7.8rem;
          .el-table {
            width: 11.82rem;
            // width: 100%;
            th {
              padding: 0;
              div {
                color: #4a4a4a;
                height: 0.6rem;
                line-height: 0.6rem;
                background-color: #c5e8ff;
                font-size: 0.18rem;
              }
            }
            td {
              padding: 0;
              div {
                height: 0.6rem;
                color: #4a4a4a;
                line-height: 0.6rem;
                font-size: 0.18rem;
              }
            }
          }
        }
        .paging-box {
          width: 100%;
          height: 0.32rem;
          margin-top: 0.2rem;
          .el-pagination {
            float: right;
            margin-right: 0.5rem;
          }
        }
      }
    }
    .dialog-box {
      width: 6rem;
      .body {
        padding: 0.5rem 0;
        font-size: 0.18rem;
        display: flex;
        justify-content: center;
        span {
          display: inline-block;
          margin-right: 0.3rem;
          line-height: 0.4rem;
        }
      }
    }
    /deep/.el-input--suffix .el-input__inner {
      font-size: 0.16rem;
      border: 1px solid #b1b1b1;
      border-radius: 5px;
    }
  }
}
</style>

<script>
import mixin from '@/utils/mixin'
export default {
  mixins:[mixin],
  data() {
    var scrollWrap =  document.getElementById('scrollWrap')
    return {
      selectShow: 1, // 当前模块
      alarmShow: false, // 警报模块状态
      historyShow: false, // 历史数据状态
      options: [], // 地下水位测点选项
      value: "", // 地下水位测点值
      options2: [], // 水平位移测点选项
      value2: "", // 水平位移测点值
      options3: [], // 周边沉降测点选项
      value3: "", // 周边沉降测点值
      options4: [], // 支撑轴力测点选项
      value4: "", // 支撑轴力测点值
      searchOptions: [], // 搜索栏选项
      searchValue: "", // 搜索栏值
      radio: "1", // 搜索栏单选框
      tableData: [
        {
          works: "地下水位", // 结构物
          source: "ZJ-01", // 警告源
          rank: "1", // 警告等级
          message: "超1级阀值告警", // 警告信息
          number: "2", // 警告次数
          time: "2018-02-06 12：00：00" // 告警时间
        },
        {
          works: "土体位移",
          source: "LG-01",
          rank: "1",
          message: "超1级阀值告警",
          number: "3",
          time: "2018-02-06 12：00：00"
        },
        {
          works: "周边沉降",
          source: "QJ-01",
          rank: "1",
          message: "超1级阀值告警",
          number: "1",
          time: "2018-02-06 12：00：00"
        },
        {
          works: "支撑轴力",
          source: "SP-01",
          rank: "1",
          message: "超1级阀值告警",
          number: "4",
          time: "2018-02-06 12：00：00"
        }
      ], // 警报列表数据
      date1: "", // 地下水位历史数据起止时间
      historyTableData1: [
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 地下水位
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 地下水位
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        }
      ], // 地下水位历史数据列表
      date2: "", // 水平位移历史数据起止时间
      historyTableData2: [
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 水平位移
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 水平位移
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 水平位移
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
      ], // 水平位移历史数据列表
      date3: "", // 周边沉降历史数据起止时间
      historyTableData3: [
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 周边沉降
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        }
      ], // 周边沉降历史数据列表
      date4: "", // 支撑轴力历史数据起止时间
      historyTableData4: [
        {
          name: "CJ-01", // 检测点
          level: 0.5, // 支撑轴力
          status: 1, // 状态
          time: "2019-10-10 19:19:19" // 时间
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 1.2,
          status: 0,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        },
        {
          name: "CJ-01",
          level: 0.5,
          status: 1,
          time: "2019-10-10 19:19:19"
        }
      ], // 支撑轴力历史数据列表
      deviceName: '', // 基坑名称
      structureId: '', // 基坑ID
      handOverList: [], // 切换设备列表
      showBackGround: false, // 显示弹窗背景
      dialog: false, // 弹窗的显示
      structureIdChange: '', // 切换的列表发生变化
      nowTime: '', // 当前的时间
      factorList: [], // 因素列表
      stage: '', // 水位ID
      offset: '', // 位移ID
      subside: '', // 沉降ID
      product: '', // 结构应变ID
      bias: '', // 倾斜ID
      stageList: [], // 水位监测点列表
      offsetList: [], // 位移监测点列表
      subsideList: [], // 沉降监测点列表
      productList: [], // 结构监测点列表
      biasList: [], // 倾斜监测点列表
      stageListChild: '', // 水位监测点列表的选项
      offsetListChild: '', // 位移监测点列表的选项
      subsideListChild: '', // 沉降监测点列表的选项
      productListChild: '', // 结构监测点列表的选项
      biasListChild: '', // 倾斜监测点列表的选项
      pageSize: 14, // 历史记录表格显示条数
      pageNum: 1, // 历史记录的页码
      stageTable: [], // 水位监测点表格
      offsetTable: [], // 位移监测点表格
      subsideTable: [], // 沉降监测点表格
      productTable: [], // 结构监测点表格
      biasTable: [], // 倾斜监测点表格
      stageHistoryTable: [], // 水位监测点表格(历史)
      offsetHistoryTable: [], // 位移监测点表格(历史)
      subsideHistoryTable: [], // 沉降监测点表格(历史)
      productHistoryTable: [], // 结构监测点表格(历史)
      biasHistoryTable: [], // 倾斜监测点表格(历史)
      stageHistoryEcharts: [], // 水位监测点echarts数据(历史)
      offsetHistoryEcharts: [], // 位移监测点echarts数据(历史)
      subsideHistoryEcharts: [], // 沉降监测点echarts数据(历史)
      productHistoryEcharts: [], // 结构监测点echarts数据(历史)
      biasHistoryEcharts: [], // 倾斜监测点echarts数据(历史)
      historyPageSize: 15, // 历史页面的表格
      historyStageTolal: 0, // 水位数据总数
      historyOffsetTolal: 0, // 位移数据总数
      historySubsideTolal: 0, // 沉降数据总数
      historyProductTolal: 0, // 结构应变数据总数
      historyBiasTolal: 0, // 倾斜数据总数
      historyTableChange: {}, // 历史记录表格弹出
      seeMore: true, // 是否显示点击查看更多按钮
      stageFourHoverList: [], // 水位监测点四小时数据
      offsetFourHoverListX: [], // 位移监测点X四小时数据
      offsetFourHoverListY: [], // 位移监测点Y四小时数据
      offsetHistoryFourHoverListX: [], // 位移监测点X累积四小时数据
      offsetHistoryFourHoverListY: [], // 位移监测点Y累积四小时数据
      subsideFourHoverList: [], // 沉降监测点四小时数据
      productHzFourHoverList: [], // 结构监测点四小时数据(HZ)
      productTempFourHoverList: [], // 结构监测点四小时数据（温度）
      biasFourHoverListX: [], // 倾斜监测点X四小时数据
      biasFourHoverListY: [], // 倾斜监测点Y四小时数据
      stageMaxList: {}, // 获取地下水位最大值列表
      subsideMaxList: {}, // 获取周边沉降最大值列表
      biasMaxList: {}, // 获取倾斜最大值列表
      searchTime: '', // 搜索的时间
      alarmList: [], // 当天报警状态列表
      allAlarmList: [], // 所有报警列表
      alarmSize: 15, // 报警状态条数
      alarmNum: 1, // 报警状态页码
      allOfAlarm: 0, // 总共的报警次数
      alarmListTotal: 0, // 报警表格数据总数
      alarmEchats: [], // 报警图表数据
      stationList: [], // 报警搜索的测点显示
    };
  },
  mounted() {
    this.getTime()
    this.getSelectStructure()
    // this.selectUserAlarms()
    // this.alarmChart();
    this.ifCart();
  },
  updated() {
    this.ifCart();
  },
  methods: {
    // 序号
    indexMethod(index) {
      return index + 1;
    },

    // 获取当前时间
    getTime() {
      var now = new Date()
      var month = (parseInt(now.getMonth()) + parseInt(1)) < 10 ? '0' + (parseInt(now.getMonth()) + parseInt(1)) : (parseInt(now.getMonth()) + parseInt(1))
      this.nowTime = now.getFullYear() + '-' + month + '-' + now.getDate()
      // this.nowTime = '2019-09-08'
      // this.nowTime = '2019-08-28'
    },

    // 获取基坑列表
    getSelectStructure() {
      var num = new Date().getTime();
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectStructure?projectId=4&k=${num}`)
        .then(res => {
          this.deviceName = res.data.data[0].deviceName
          this.structureId = res.data.data[0].deviceId
          this.structureIdChange = this.structureId
          for (let i = 0; i < res.data.data.length; i++) {
            this.handOverList.push({label: '', value: ''})
            this.$set(this.handOverList[i], 'label', res.data.data[i].deviceName)
            this.$set(this.handOverList[i], 'value', res.data.data[i].deviceId)
          }
          this.selectUserAlarms()
          this.getSelectDisplay()
          this.getAlarmEchats()
        })
    },

    // 切换基坑
    structureIdChangeClick() {
      this.structureId = this.structureIdChange
      this.selectUserAlarms()
      this.getSelectDisplay()
      this.getAlarmEchats()
      this.dialog = !this.dialog
      this.showBackGround = !this.showBackGround
    },

    // 查询因素列表
    getSelectDisplay() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectDisplay?structureId=${this.structureId}`)
        .then(res => {
          this.factorList = res.data.data
          if (this.factorList[0].name == '深层水平位移') {
            this.selectShow = 2
          } else if (this.factorList[0].name == '应变原始数据') {
            this.selectShow = 5
          } else if (this.factorList[0].name == '地下水位') {
            this.selectShow = 1
          } else if (this.factorList[0].name == '沉降') {
            this.selectShow = 3
          } else if (this.factorList[0].name == '建筑物倾斜') {
            this.selectShow = 6
          }
          for (let i = 0; i < this.factorList.length; i++) {
            if (this.factorList[i].name == '深层水平位移') {
              this.offset = this.factorList[i].id
            } else if (this.factorList[i].name == '应变原始数据') {
              this.product = this.factorList[i].id
            } else if (this.factorList[i].name == '地下水位') {
              this.stage = this.factorList[i].id
            } else if (this.factorList[i].name == '沉降') {
              this.subside = this.factorList[i].id
            } else if (this.factorList[i].name == '建筑物倾斜') {
              this.bias = this.factorList[i].id
            }
          }
          this.getFactorList()
        })
    },

    // 获取监测点列表
    getFactorList() {
      if (this.stage > 0) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorList?structureId=${this.structureId}&displayId=${this.stage}`)
          .then(res => {
            this.stageList = res.data.data
            this.stageListChild = res.data.data[0].id
            // this.stageListChild = 4
            this.getStage(1, 4)
            this.getStageFourHoverList()
            this.getStageMax()
          })
      }
      if (this.offset > 0) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorList?structureId=${this.structureId}&displayId=${this.offset}`)
          .then(res => {
            this.offsetList = res.data.data
            this.offsetListChild = res.data.data[0].id
            // this.offsetListChild = 1
            this.getOffset(1, 4)
            this.getOffsetFourHoverList()
          })
      }
      if (this.subside > 0) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorList?structureId=${this.structureId}&displayId=${this.subside}`)
          .then(res => {
            this.subsideList = res.data.data
            this.subsideListChild = res.data.data[0].id
            // this.subsideListChild = 17092
            this.getSubside(1, 4)
            this.getSubsideFourHoverList()
            this.getSubsideMax()
          })
      }
      if (this.product > 0) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorList?structureId=${this.structureId}&displayId=${this.product}`)
          .then(res => {
            this.productList = res.data.data
            this.productListChild = res.data.data[0].id
            // this.productListChild = 3
            this.getProduct(1, 4)
            this.getProductFourHoverList()
          })
      }
      if (this.bias > 0) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorList?structureId=${this.structureId}&displayId=${this.bias}`)
          .then(res => {
            this.biasList = res.data.data
            this.biasListChild = res.data.data[0].id
            // this.biasListChild = 2
            this.getBias(1, 4)
            this.getBiasFourHoverList()
            this.getBiasMax()
          })
      }
    },

    // 获取测点水位数据
    getStage(num, size) {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.stageListChild}&date=${this.nowTime}&pageNum=${num || this.pageNum}&pageSize=${size || this.pageSize}`)
        .then(res => {
          if (!size) {
            // this.stageHistoryTable = res.data.data
          } else {
            this.stageTable = res.data.data
          }
        })
    },

    // 获取测点位移数据
    getOffset(num, size) {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.offsetListChild}&date=${this.nowTime}&pageNum=${num || this.pageNum}&pageSize=${size || this.pageSize}`)
        .then(res => {
          if (!size) {
            // this.offsetHistoryTable = res.data.data
          } else {
            this.offsetTable = res.data.data
          }
        })
    },

    // 获取测点沉降数据
    getSubside(num, size) {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.subsideListChild}&date=${this.nowTime}&pageNum=${num || this.pageNum}&pageSize=${size || this.pageSize}`)
        .then(res => {
          if (!size) {
            // this.subsideHistoryTable = res.data.data
          } else {
            this.subsideTable = res.data.data
          }
        })
    },

    // 获取测点结构数据
    getProduct(num, size) {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.productListChild}&date=${this.nowTime}&pageNum=${num || this.pageNum}&pageSize=${size || this.pageSize}`)
        .then(res => {
          if (!size) {
            // this.productHistoryTable = res.data.data
          } else {
            this.productTable = res.data.data
          }
        })
    },

    // 获取测点倾斜数据
    getBias(num, size) {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.biasListChild}&date=${this.nowTime}&pageNum=${num || this.pageNum}&pageSize=${size || this.pageSize}`)
        .then(res => {
          if (!size) {
            // this.biasHistoryTable = res.data.data
          } else {
            this.biasTable = res.data.data
          }
        })
    },

    // 显示水位监测点名称
    filterStage(id){
      for (let i = 0; i < this.stageList.length; i++) {
        if (this.stageList[i].id == id) {
          return this.stageList[i].name
        }
      }
    },

    // 显示位移监测点名称
    filterOffset(id){
      for (let i = 0; i < this.offsetList.length; i++) {
        if (this.offsetList[i].id == id) {
          return this.offsetList[i].name
        }
      }
    },

    // 显示沉降监测点名称
    filterSubside(id){
      for (let i = 0; i < this.subsideList.length; i++) {
        if (this.subsideList[i].id == id) {
          return this.subsideList[i].name
        }
      }
    },

    // 显示结构监测点名称
    filterProduct(id){
      for (let i = 0; i < this.productList.length; i++) {
        if (this.productList[i].id == id) {
          return this.productList[i].name
        }
      }
    },

    // 显示倾斜监测点名称
    filterBias(id){
      for (let i = 0; i < this.biasList.length; i++) {
        if (this.biasList[i].id == id) {
          return this.biasList[i].name
        }
      }
    },

    // 获取水位四小时记录
    getStageFourHoverList() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.stageListChild}&date=${this.nowTime}&param=water_level`)
        .then(res => {
          this.stageFourHoverList = res.data
        })
    },

    // 获取位移四小时记录
    getOffsetFourHoverList() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.offsetListChild}&date=${this.nowTime}&param=level_x`)
        .then(res => {
          this.offsetFourHoverListX = res.data
        })
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.offsetListChild}&date=${this.nowTime}&param=level_y`)
        .then(res => {
          this.offsetFourHoverListY = res.data
        })
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.offsetListChild}&date=${this.nowTime}&param=level_accumulate_x`)
        .then(res => {
          this.offsetHistoryFourHoverListX = res.data
        })
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.offsetListChild}&date=${this.nowTime}&param=level_accumulate_y`)
        .then(res => {
          this.offsetHistoryFourHoverListY = res.data
        })
    },

    // 获取沉降四小时记录
    getSubsideFourHoverList() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.subsideListChild}&date=${this.nowTime}&param=subside`)
        .then(res => {
          this.subsideFourHoverList = res.data
        })
    },

    // 获取结构四小时记录
    getProductFourHoverList() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.productListChild}&date=${this.nowTime}&param=strain_frequency`)
        .then(res => {
          this.productHzFourHoverList = res.data
        })
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.productListChild}&date=${this.nowTime}&param=strain_temperature`)
        .then(res => {
          this.productTempFourHoverList = res.data
        })
    },

    // 获取倾斜四小时记录
    getBiasFourHoverList() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.biasListChild}&date=${this.nowTime}&param=tilt_x`)
        .then(res => {
          this.biasFourHoverListX = res.data
        })
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecial?factorId=${this.biasListChild}&date=${this.nowTime}&param=tilt_y`)
        .then(res => {
          this.biasFourHoverListY = res.data
        })
    },

    // 历史记录翻页
    handlePageNum(val) {
      this.pageNum = val
      this.searchClick()
    },

    // 历史记录表格点击显示
    historyTableChangeHeight(add) {
      if (add) {
        this.seeMore = false
        this.historyTableChange = {
          height: '10rem',
          top: '-3.2rem',
          zIndex: '16'
        }
      } else {
        this.seeMore = true
        this.historyTableChange = {}
      }
    },

    // 导航栏点击事件
    navClick(num) {
      this.selectShow = num
      if (this.historyShow) {
        this.searchTime = ''
        this.pageNum = 1
        this.historyTableChangeHeight()
        if (this.selectShow == 1) {
          this.getStageHistory('today')
        } else if (this.selectShow == 2) {
          this.getOffsetHistory('today')
        } else if (this.selectShow == 3) {
          this.getSubsideHistory('today')
        } else if (this.selectShow == 5) {
          this.getProductHistory('today')
        } else if (this.selectShow == 6) {
          this.getBiasHistory('today')
        }
      }
    },

    // 获取地下水位最大值
    getStageMax() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getParmeterAvg?displayId=${this.stage}&factorId=${this.stageListChild}`)
        .then(res => {
          this.stageMaxList = res.data.data
        })
    },

    // 获取周边沉降最大值
    getSubsideMax() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/getParmeterAvg?displayId=${this.subside}&factorId=${this.subsideListChild}`)
        .then(res => {
          this.subsideMaxList = res.data.data
        })
    },

    // 获取倾斜最大值 倾斜不知道怎么搞，等隆鑫回来 调用已经写了
    getBiasMax() {
      // this.$axios
      //   .post(`http://192.168.1.117:4524/provider/hjDeeppit/getParmeterAvg?displayId=${this.bias}&factorId=${this.biasListChild}`)
      //   .then(res => {
      //     this.biasMaxList = res.data.data
      //   })
    },

    // 历史记录按钮点击事件
    historyClick() {
      if (this.selectShow == 1) {
        this.getStageHistory('today')
      } else if (this.selectShow == 2) {
        this.getOffsetHistory('today')
      } else if (this.selectShow == 3) {
        this.getSubsideHistory('today')
      } else if (this.selectShow == 5) {
        this.getProductHistory('today')
      } else if (this.selectShow == 6) {
        this.getBiasHistory('today')
      }
    },

    // 获取地下水位历史数据
    getStageHistory(today) {
      if (today) {
        var startTime = this.nowTime + ' 00:00:00'
        var endTime = this.nowTime + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.stageListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.stage}`)
          .then(res => {
            this.stageHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorDataT?factorId=${this.stageListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.stageHistoryTable = res.data.data
            this.historyStageTolal = res.data.sum
          })
      } else {
        var startTime = this.searchTime[0].toLocaleDateString().split('/').join('-') + ' 00:00:00'
        var endTime = this.searchTime[1].toLocaleDateString().split('/').join('-') + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.biasListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.stage}`)
          .then(res => {
            this.stageHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.stageListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.stageHistoryTable = res.data.data
            this.historyStageTolal = res.data.sum
          })
      }
    },

    // 获取位移历史数据
    getOffsetHistory(today) {
      if (today) {
        var startTime = this.nowTime + ' 00:00:00'
        var endTime = this.nowTime + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.offsetListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.offset}`)
          .then(res => {
            this.offsetHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorDataT?factorId=${this.offsetListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.offsetHistoryTable = res.data.data
            this.historyOffsetTolal = res.data.sum
          })
      } else {
        var startTime = this.searchTime[0].toLocaleDateString().split('/').join('-') + ' 00:00:00'
        var endTime = this.searchTime[1].toLocaleDateString().split('/').join('-') + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.offsetListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.offset}`)
          .then(res => {
            this.offsetHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.offsetListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.offsetHistoryTable = res.data.data
            this.historyOffsetTolal = res.data.sum
          })
      }
    },

    // 获取沉降历史数据
    getSubsideHistory(today) {
      if (today) {
        var startTime = this.nowTime + ' 00:00:00'
        var endTime = this.nowTime + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.subsideListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.subside}`)
          .then(res => {
            this.subsideHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorDataT?factorId=${this.subsideListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.subsideHistoryTable = res.data.data
            this.historySubsideTolal = res.data.sum
          })
      } else {
        var startTime = this.searchTime[0].toLocaleDateString().split('/').join('-') + ' 00:00:00'
        var endTime = this.searchTime[1].toLocaleDateString().split('/').join('-') + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.subsideListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.subside}`)
          .then(res => {
            this.subsideHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.subsideListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.subsideHistoryTable = res.data.data
            this.historySubsideTolal = res.data.sum
          })
      }
    },

    // 获取结构应变历史数据
    getProductHistory(today) {
      if (today) {
        var startTime = this.nowTime + ' 00:00:00'
        var endTime = this.nowTime + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.productListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.product}`)
          .then(res => {
            this.productHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorDataT?factorId=${this.productListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.productHistoryTable = res.data.data
            this.historyProductTolal = res.data.sum
          })
      } else {
        var startTime = this.searchTime[0].toLocaleDateString().split('/').join('-') + ' 00:00:00'
        var endTime = this.searchTime[1].toLocaleDateString().split('/').join('-') + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.productListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.product}`)
          .then(res => {
            this.productHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.productListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.productHistoryTable = res.data.data
            this.historyProductTolal = res.data.sum
          })
      }
    },

    // 获取倾斜历史数据
    getBiasHistory(today) {
      if (today) {
        var startTime = this.nowTime + ' 00:00:00'
        var endTime = this.nowTime + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.biasListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.bias}`)
          .then(res => {
            this.biasHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorDataT?factorId=${this.biasListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.biasHistoryTable = res.data.data
            this.historyBiasTolal = res.data.sum
          })
      } else {
        var startTime = this.searchTime[0].toLocaleDateString().split('/').join('-') + ' 00:00:00'
        var endTime = this.searchTime[1].toLocaleDateString().split('/').join('-') + ' 23:59:59'
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectSpecialS?factorId=${this.biasListChild}&startTime=${startTime}&endTime=${endTime}&displayId=${this.bias}`)
          .then(res => {
            this.biasHistoryEcharts = res.data
          })
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/getFactorData?factorId=${this.biasListChild}&startTime=${startTime}&endTime=${endTime}&pageSize=${this.pageSize}&pageNum=${this.pageNum}`)
          .then(res => {
            this.biasHistoryTable = res.data.data
            this.historyBiasTolal = res.data.sum
          })
      }
    },

    // 搜索点击
    searchClick() {
      if (this.selectShow == 1) {
        if (this.searchTime) {
          this.getStageHistory()
        } else {
          this.getStageHistory('today')
        }
      } else if (this.selectShow == 2) {
        if (this.searchTime) {
          this.getOffsetHistory()
        } else {
          this.getOffsetHistory('today')
        }
      } else if (this.selectShow == 3) {
        if (this.searchTime) {
          this.getSubsideHistory()
        } else {
          this.getSubsideHistory('today')
        }
      } else if (this.selectShow == 5) {
        if (this.searchTime) {
          this.getProductHistory()
        } else {
          this.getProductHistory('today')
        }
      } else if (this.selectShow == 6) {
        if (this.searchTime) {
          this.getBiasHistory()
        } else {
          this.getBiasHistory('today')
        }
      }
    },

    // 查询报警数据
    selectUserAlarms(today) {
      if (today) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectUserAlarms?structureId=${this.structureId}&date=${this.nowTime}&pageSize=${this.alarmSize}&pageNum=${this.alarmNum}`)
          .then(res => {
            this.alarmList = res.data.data
            this.alarmListTotal = res.data.sum
          })
      } else {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectUserAlarms?structureId=${this.structureId}&pageSize=100&pageNum=1&date=`)
          .then(res => {
            this.allAlarmList = res.data.data
            this.allOfAlarm = res.data.sum
          })
      }
    },

    // 搜索报警数据
    searchUserAlarms(today) {
      if (today) {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectUserAlarmsByFactor?factorName=${this.searchValue}&date=${this.nowTime}&pageSize=${this.alarmSize}&pageNum=${this.alarmNum}`)
          .then(res => {
            this.alarmList = res.data.data.rows
            this.alarmListTotal = res.data.total
          })
      } else {
        this.$axios
          .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectUserAlarmsByFactor?factorName=${this.searchValue}&pageSize=${this.alarmSize}&pageNum=${this.alarmNum}&date=`)
          .then(res => {
            this.alarmList = res.data.data.rows
            this.alarmListTotal = res.data.total
          })
      }
    },

    // 获取报警搜索栏的测点数据
    getStationList() {
      this.stageList.forEach(item => {
        this.stationList.push(item)
      });
      this.offsetList.forEach(item => {
        this.stationList.push(item)
      });
      this.subsideList.forEach(item => {
        this.stationList.push(item)
      });
      this.productList.forEach(item => {
        this.stationList.push(item)
      });
      this.stageList.forEach(item => {
        this.biasList.push(item)
      });
    },

    // 测点搜索点击
    searchStation() {
      // console.log(this.searchValue)
      // console.log(this.radio)
      if (this.radio == 1) {
        if (this.searchValue) {
          this.searchUserAlarms('today')
        } else {
          this.selectUserAlarms('today')
        }
      } else {
        if (this.searchValue) {
          this.searchUserAlarms()
        } else {
          this.$axios
            .post(`http://192.168.1.117:4524/provider/hjDeeppit/selectUserAlarms?structureId=${this.structureId}&pageSize=${this.alarmSize}&pageNum=${this.alarmNum}&date=`)
            .then(res => {
              // debugger
              this.alarmList = res.data.data
              this.alarmListTotal = res.data.sum
            })
        }
      }
    },

    // 报警翻页
    handleAlarm(val) {
      this.alarmNum = val
      this.selectUserAlarms('today')
    },

    // 报警图表
    getAlarmEchats() {
      this.$axios
        .post(`http://192.168.1.117:4524/provider/hjDeeppit/statisticsAlertor?structureId=${this.structureId}`)
        .then(res => {
          // this.alarmEchats = res.data.data
          for (let i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].level == 1) {
              this.alarmEchats.push({
                value: res.data.data[i].cnt,
                name: '一级预警'
              })
            } else if (res.data.data[i].level == 2) {
              this.alarmEchats.push({
                value: res.data.data[i].cnt,
                name: '二级预警'
              })
            } else if (res.data.data[i].level == 3) {
              this.alarmEchats.push({
                value: res.data.data[i].cnt,
                name: '三级预警'
              })
            }
          }
          this.alarmChart(this.alarmEchats)
        })
    },

    // 格式化报警时间
    getAlarmTime(time) {
      var a = time.split('-')
      a.shift()
      return a.join('-')
    },

    // 渲染深基坑预警占比图
    alarmChart(data) {
      let myChart = this.$echarts.init(document.getElementById("alarmChart"));
      myChart.setOption({
        color: ["#ff7a81", "#feb27e", "#ffde2a"],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "445px",
          y: "75px",
          itemWidth: 16, // 设置图例图形的宽
          itemHeight: 16, // 设置图例图形的高
          textStyle: {
            fontSize: "16",
            color: "#000"
          },
          data: ["一级预警", "二级预警", "三级预警"]
        },
        series: [
          {
            name: "预警占比",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "24"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            // data: [
            //   { value: 0, name: "一级预警" },
            //   { value: 0, name: "二级预警" },
            //   { value: 0, name: "三级预警" }
            // ]
            data: data
          }
        ]
      });
    },

    // 地下水位当日数据趋势图
    oneChart(stage) {
      let oneChart = this.$echarts.init(document.getElementById("oneChart"));
      oneChart.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            min: 0,
            // interval: 0.1,
            axisLabel: {
              textStyle: {
                color: "#000"
              },
              // formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "地下水位",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: stage
          }
          // {
          //     name: "考勤总人数",
          //     type: "line",
          //     symbolSize: 10,
          //     smooth: 0.2,
          //     color: ["#0090ff"],
          //     // data: [300, 200, 100, 500, 300, 300, 400, 300, 100, 300],
          //     data: kqrs
          // },
          // {
          //     name: "管理人员出勤人数",
          //     type: "line",
          //     symbolSize: 4,
          //     smooth: 0.2,
          //     color: ["#33577c"],
          //     data: [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5],
          //     // data: aMZcGly
          // }
        ]
      });
    },

    // 水平位移当日数据趋势图
    twoChart(offsetX, offsetY) {
      let twoChart = this.$echarts.init(document.getElementById("twoChart"));
      twoChart.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            min: 0,
            // interval: 1.5,
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "X方向位移",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [3.6, 4.5, 2.5, 3.5, 4.4, 3.5]
            data: offsetX
          },{
            name: "Y方向位移",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#009000"],
            // data: [3.6, 4.5, 2.5, 3.5, 4.4, 3.5]
            data: offsetY
          }
        ]
      });
    },

    // 周边沉降当日数据趋势图
    threeChart(subside) {
      let threeChart = this.$echarts.init(
        document.getElementById("threeChart")
      );
      threeChart.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            position: 'top',
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            // min: 0,
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "周边沉降",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: subside
          }
        ]
      });
    },

    // 倾斜当日数据趋势图(X)
    slantX(bias) {
      let slantX = this.$echarts.init(document.getElementById("slantX"));
      slantX.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "建筑倾斜（X轴）",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.26, 0.25, 0.35, 0.25, 0.14, 0.15]
            data: bias
          }
        ]
      });
    },

    // 倾斜当日数据趋势图(Y)
    slantY(bias) {
      let slantX = this.$echarts.init(document.getElementById("slantY"));
      slantX.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "建筑倾斜（Y轴）",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#009000"],
            // data: [0.26, 0.25, 0.35, 0.25, 0.14, 0.15]
            data: bias
          }
        ]
      });
    },

    // 结构应变当日数据趋势图（频率）
    structureFrequency(productHz) {
      let fourChart = this.$echarts.init(document.getElementById("structureFrequency"));
      fourChart.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            min: 0,
            // interval: 0.15,
            axisLabel: {
              textStyle: {
                color: "#000"
              },
              formatter: "{value}HZ"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "结构应变（频率）",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.26, 0.25, 0.35, 0.25, 0.14, 0.15]
            data: productHz
          }
        ]
      });
    },

    // 结构应变当日数据趋势图（温度）
    structureTemperature(productTemp) {
      let fourChart = this.$echarts.init(document.getElementById("structureTemperature"));
      fourChart.setOption({
        // backgroundColor: "#FBFBFB",
        grid: {
          x: 60,
          y: 30,
          x2: 50,
          y2: 50,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            min: 0,
            // interval: 0.15,
            axisLabel: {
              textStyle: {
                color: "#000"
              },
              formatter: "{value}℃"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        series: [
          {
            name: "结构应变（温度）",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#009000"],
            // data: [0.26, 0.25, 0.35, 0.25, 0.14, 0.15]
            data: productTemp
          }
        ]
      });
    },

    // 地下水位历史数据图
    historyOne(data) {
      let historyOne = this.$echarts.init(
        document.getElementById("historyOne")
      );
      historyOne.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            // data: [
            //   "2019-08-01 00：00",
            //   "2019-08-01 04：00",
            //   "2019-08-01 08：00",
            //   "2019-08-01 12：00",
            //   "2019-08-01 16：00",
            //   "2019-08-01 20：00"
            // ]
            data: data.map(item => {
              return item[0]
            })
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: [{
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65,//结束位置（共100等份）
          // filterMode: 'filter'
        }, {
          type: 'inside'
        }],
        series: [
          {
            name: "地下水位",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[1]
            })
          }
        ]
      });
    },

    // 水平位移历史数据
    historyTwo(data) {
      let historyTwo = this.$echarts.init(
        document.getElementById("historyTwo")
      );
      historyTwo.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            // data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
            data: data.map(item => {
              return item[0]
            })
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: [{
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65,//结束位置（共100等份）
          // filterMode: 'filter'
        }, {
          type: 'inside'
        }],
        series: [
          {
            name: "X方向位移",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [3.6, 4.5, 2.5, 3.5, 4.4, 3.5]
            data: data.map(item => {
              return item[1]
            })
          },{
            name: "Y方向位移",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [3.6, 4.5, 2.5, 3.5, 4.4, 3.5]
            data: data.map(item => {
              return item[2]
            })
          }
        ]
      });
    },

    // 周边沉降历史数据
    historyThree(data) {
      let historyThree = this.$echarts.init(
        document.getElementById("historyThree")
      );
      historyThree.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            position: 'top',
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            // data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
            data: data.map(item => {
              return item[0]
            })
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: [{
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65,//结束位置（共100等份）
          // filterMode: 'filter'
        }, {
          type: 'inside'
        }],
        series: [
          {
            name: "周边沉降",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[1]
            })
          }
        ]
      });
    },

    // 支撑轴力历史数据
    historyFour() {
      let historyFour = this.$echarts.init(
        document.getElementById("historyFour")
      );
      historyFour.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
          }
        ],
        yAxis: [
          {
            type: "value",
            min: 0,
            interval: 0.15,
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: {
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65//结束位置（共100等份）
        },
        series: [
          {
            name: "支撑轴力历史数据图",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            data: [0.26, 0.25, 0.35, 0.25, 0.14, 0.15]
            // data: zcrs
          }
        ]
      });
    },

    // 应变历史数据
    historyFive(data) {
      let historyThree = this.$echarts.init(
        document.getElementById("historyFive")
      );
      historyThree.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            position: 'top',
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            // data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
            data: data.map(item => {
              return item[0]
            })
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: [{
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65,//结束位置（共100等份）
          // filterMode: 'filter'
        }, {
          type: 'inside'
        }],
        series: [
          {
            name: "结构应变(HZ)",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[1]
            })
          }, {
            name: "结构应变(℃)",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#009000"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[2]
            })
          }
        ]
      });
    },

    // 倾斜历史数据
    historySix(data) {
      let historyThree = this.$echarts.init(
        document.getElementById("historySix")
      );
      historyThree.setOption({
        grid: {
          x: 50,
          y: 30,
          x2: 70,
          y2: 40,
          containLabel: true
        },
        tooltip: {
          trigger: "axis"
        },
        calculable: true,
        xAxis: [
          {
            position: 'top',
            axisLabel: {
              rotate: 0,
              interval: 0,
              color: "#000"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            type: "category",
            boundaryGap: false,
            // data: ["00：00", "04：00", "08：00", "12：00", "16：00", "20：00"]
            data: data.map(item => {
              return item[0]
            })
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              textStyle: {
                color: "#000"
              }
              //   formatter: "{value} 度"
            },
            axisLine: {
              lineStyle: {
                color: "#132e6d"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#ccc"],
                width: 1,
                type: "dashed"
              }
            }
          }
        ],
        dataZoom: [{
          realtime:true, //拖动滚动条时是否动态的更新图表数据
          height:25,//滚动条高度
          start:40,//滚动条开始位置（共100等份）
          end:65,//结束位置（共100等份）
          // filterMode: 'filter'
        }, {
          type: 'inside'
        }],
        series: [
          {
            name: "建筑倾斜(X轴)",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#0090ff"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[1]
            })
          }, {
            name: "建筑倾斜(Y轴)",
            type: "line",
            symbolSize: 10,
            smooth: 0.2,
            color: ["#009000"],
            // data: [0.6, 0.5, 0.5, 0.5, 0.4, 0.5]
            data: data.map(item => {
              return item[2]
            })
          }
        ]
      });
    },

    // 渲染当前选中模块的ECharts图
    ifCart() {
      if (this.selectShow == 1 && !this.historyShow) {
        if (document.getElementById("oneChart")) {
          this.oneChart(this.stageFourHoverList);
        }
      }
      if (this.selectShow == 2 && !this.historyShow) {
        // this.twoChart();
        if (document.getElementById("twoChart")) {
          this.twoChart(this.offsetFourHoverListX, this.offsetFourHoverListY);
        }
      }
      if (this.selectShow == 3 && !this.historyShow) {
        // this.threeChart();
        if (document.getElementById("threeChart")) {
          this.threeChart(this.subsideFourHoverList);
        }
      }
      if (this.selectShow == 4 && !this.historyShow) {
        // this.fourChart();
        // if (document.getElementById("fourChart")) {
        //   this.fourChart();
        // }
      }
      if (this.selectShow == 5 && !this.historyShow) {
        if (document.getElementById('structureFrequency')) {
          this.structureFrequency(this.productHzFourHoverList);
          this.structureTemperature(this.productTempFourHoverList);
        }
        // this.structureFrequency();
        // this.structureTemperature();
      }
      if (this.selectShow == 6 && !this.historyShow) {
        if (document.getElementById("slantX")) {
          this.slantX(this.biasFourHoverListX);
          this.slantY(this.biasFourHoverListY)
        }
      }
      // 历史记录
      if (this.selectShow == 1 && this.historyShow) {
        // this.historyOne();
        if (document.getElementById("historyOne")) {
          this.historyOne(this.stageHistoryEcharts);
        }
      }
      if (this.selectShow == 2 && this.historyShow) {
        // this.historyTwo();
        if (document.getElementById("historyTwo")) {
          this.historyTwo(this.offsetHistoryEcharts);
        }
      }
      if (this.selectShow == 3 && this.historyShow) {
        // this.historyThree();
        if (document.getElementById("historyThree")) {
          this.historyThree(this.subsideHistoryEcharts);
        }
      }
      if (this.selectShow == 4 && this.historyShow) {
        // this.historyFour();
        if (document.getElementById("historyFour")) {
          this.historyFour();
        }
      }
      if (this.selectShow == 5 && this.historyShow) {
        this.historyFive(this.productHistoryEcharts);
      }
      if (this.selectShow == 6 && this.historyShow) {
        this.historySix(this.biasHistoryEcharts);
      }
    },

    // 点击向左滚动效果
    scrollLeft() {
      var temp = Math.floor(scrollWrap.scrollLeft) + 329
      clearInterval(interval)
      var interval = setInterval(() => {
        if (scrollWrap.scrollLeft < temp) {
          var distance = scrollWrap.scrollLeft += 20
          if (scrollWrap.scrollLeft !== distance) {
            clearInterval(interval)
          }
        } else {
          scrollWrap.scrollLeft = temp
          clearInterval(interval)
        }
      }, 5);
    },

    // 点击向右滚动效果
    scrollRight() {
      var temp = Math.floor(scrollWrap.scrollLeft) - 329
      clearInterval(interval)
      var interval = setInterval(() => {
        if (scrollWrap.scrollLeft > temp) {
          var distance = scrollWrap.scrollLeft -= 20
          if (scrollWrap.scrollLeft !== distance) {
            clearInterval(interval)
          }
        } else {
          scrollWrap.scrollLeft = temp
          clearInterval(interval)
        }
      }, 5);
    }
  }
};
</script>