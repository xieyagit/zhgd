package com.hujiang.project.zhgd.deye;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import com.hujiang.project.zhgd.sbCraneAddparams.service.ISbCraneAddparamsService;
import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import com.hujiang.project.zhgd.sbCraneBasicinfo.service.ISbCraneBasicinfoService;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.domain.SbElevatorAddbasicinfo;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.service.ISbElevatorAddbasicinfoService;
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.SbElevatorAddparams;
import com.hujiang.project.zhgd.sbElevatorAddparams.service.ISbElevatorAddparamsService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 塔吊升降机中车对接
 *
 * @author hujiang
 * @date 2019-04-13
 */
@Service
public class ZCDataService
{
	@Autowired
	private ISbCraneAddparamsService sbCraneAddparamsService;
	@Autowired
	private IHjSynchronizationInformationService hjSynchronizationInformationService;
	@Autowired
	private ISbCraneBasicinfoService sbCraneBasicinfoService;
	@Autowired
	private ISbElevatorAddbasicinfoService sbElevatorAddbasicinfoService;
	@Autowired
	private ISbElevatorAddparamsService sbElevatorAddparamsService;
	/**
	 * 上传 塔吊起重机基本信息和参数
	 * @return
	 */
	public int setBaseCrane(SbCraneBinding binding)throws IOException, URISyntaxException {
		int i=0;
		String hxzId=binding.getHxzid();	//设备编号
		SbCraneBasicinfo scb=new SbCraneBasicinfo();
		scb.setHxzid(hxzId);
		List<SbCraneBasicinfo> scbList=sbCraneBasicinfoService.selectSbCraneBasicinfoList(scb);
		SbCraneAddparams sca=new SbCraneAddparams();
		sca.setHxzid(hxzId);
		List<SbCraneAddparams> sList=sbCraneAddparamsService.selectSbCraneAddparamsList(sca);
		if(sList.size()>0&&scbList.size()>0) {
			SbCraneAddparams basedatacrane=sList.get(0);
			SbCraneBasicinfo scb2=scbList.get(0);
			Integer pid = binding.getPid();
			HjSynchronizationInformation  hsi=new HjSynchronizationInformation();
			hsi.setProjectId(pid);
			hsi.setPlatformName("WORKSBUREAU");
			hsi.setState(1);
			hsi.setApiType("keytype5");
			List<HjSynchronizationInformation> hsiList=	hjSynchronizationInformationService.selectHjSynchronizationInformationList(hsi);
			if (hsiList.size() > 0) {
				HjSynchronizationInformation s = hsiList.get(0);
//				Project project=projectService.selectProjectById(pid);
				//上传塔吊基本信息
				JSONObject json1 = new JSONObject();
				json1.put("api_key", s.getApiKey());//project.getApiKey()
				json1.put("api_version", "1.0");
				json1.put("timestamp", s.getCreateDate());//时间戳
				json1.put("project_code", s.getProjectNumber());//项目编码
				json1.put("eng_code", s.getEngineeringCode());//工程编码
				json1.put("signature", "");//对API输入参数进行MD5加密获得
				JSONObject body = new JSONObject();
				body.put("device_no",scb2.getDeviceNo());//设备编号
//				body.put("model", basedatacrane.getCraneType() == 0 ? "塔头平臂吊" : basedatacrane.getCraneType() == 1 ? "平头平臂吊" : basedatacrane.getCraneType() == 2 ? "动臂吊" : "");//设备型号
				body.put("name", "塔式起重机");//设备名称
//				body.put("uid", binding.getDname());//设备用户名称
//				body.put("mon_device_man", scb2.getMonDeviceMan());//监测设备厂商
//				body.put("device_installation_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//监测设备安装日期
				body.put("guangdong_install_num","粤BE-S03981");
				SbCraneBasicinfo scb3=JSONObject.parseObject(body.toJSONString(),SbCraneBasicinfo.class);
				scb3.setId(scb2.getId());
				sbCraneBasicinfoService.updateSbCraneBasicinfo(scb3);
				json1.put("body", body);
				String str = ZCAPIClient.reportedData2019("crane/addBasicInfo", json1);//ZCAPIClient.reportedData("/crane/addBasicInfo", json1);
				JSONObject status1 = JSONObject.parseObject(str);
				if ("0".equals(status1.getString("code"))) {
					i += 1;
				}
				//上传塔吊参数信息
				JSONObject body2 = new JSONObject();
				body2.put("device_no",scb2.getDeviceNo());//设备编号
//				body2.put("model", basedatacrane.getCraneType() == 0 ? "塔头平臂吊" : basedatacrane.getCraneType() == 1 ? "平头平臂吊" : basedatacrane.getCraneType() == 2 ? "动臂吊" : "");//设备型号
				body2.put("name", "塔式起重机");//设备名称
				body2.put("TC_MaxScope", basedatacrane.getTcMaxscope());//最大幅度（M）
				body2.put("TC_MaxHeight", basedatacrane.getTcMaxheight());//最大高度
				body2.put("TC_LoadCapacity", basedatacrane.getTcLoadcapacity() * 1000);//最大载重
				body2.put("load_moment",((basedatacrane.getTcLoadcapacity() * 1000)/basedatacrane.getTcMaxscope()));//额定起重力矩（N·m）
//				body2.put("Tower_type",basedatacrane.getTowerType());//塔机类型
				json1.put("body", body2);
				String s2 = ZCAPIClient.reportedData2019("crane/addParams", json1);
				JSONObject status2 = JSONObject.parseObject(s2);
				if ("0".equals(status2.getString("code"))) {
					i += 1;
				}
			}
		}
		return i;

	}

	/**
	 * 上传 升降机机基本信息和参数
	 * @return
	 */
	public int setBaseElevator(SbElevatorBinding binding)throws IOException, URISyntaxException {
		int i = 0;
		String hxzId = binding.getHxzid();
		SbElevatorAddbasicinfo sea = new SbElevatorAddbasicinfo();
		sea.setHxzid(hxzId);
		List<SbElevatorAddbasicinfo> seaList = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoList(sea);
		SbElevatorAddparams sep = new SbElevatorAddparams();
		sep.setHxzid(hxzId);
		List<SbElevatorAddparams> sepList = sbElevatorAddparamsService.selectSbElevatorAddparamsList(sep);
		if (seaList.size() > 0 && sepList.size() > 0) {
			SbElevatorAddbasicinfo sbElevatorAddbasicinfo=seaList.get(0);
			SbElevatorAddparams sbElevatorAddparams=sepList.get(0);
			Integer pid = binding.getPid();
			HjSynchronizationInformation  hsi=new HjSynchronizationInformation();
			hsi.setProjectId(pid);
			hsi.setPlatformName("WORKSBUREAU");
			hsi.setState(1);
			hsi.setApiType("keytype6");
			List<HjSynchronizationInformation> hsiList=	hjSynchronizationInformationService.selectHjSynchronizationInformationList(hsi);

			if (hsiList.size() > 0) {
				HjSynchronizationInformation s = hsiList.get(0);
				JSONObject json1 = new JSONObject();
				json1.put("api_key", s.getApiKey());//project.getApiKey()		..4cco8008fc7747e78891380ab9ff4602
				json1.put("api_version", "1.0");
				json1.put("timestamp", s.getCreateDate());//时间戳
				json1.put("project_code", s.getProjectNumber());//项目编码
				json1.put("eng_code", s.getEngineeringCode());//工程编码
				json1.put("signature", "");//对API输入参数进行MD5加密获得

				//升降机基本信息
				JSONObject body = new JSONObject();
				body.put("device_no", sbElevatorAddbasicinfo.getDeviceNo());//设备编号
				body.put("name", sbElevatorAddbasicinfo.getName());//设备名称
				body.put("mon_device_man", sbElevatorAddbasicinfo.getMonDeviceMan());//监测设备厂商
				body.put("guangdong_install_num",sbElevatorAddbasicinfo.getHxzid());//广东省统一安装告知编号（产权备案编号）
//				body.put("device_installation_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//监测设备安装日期
				SbElevatorAddbasicinfo sea2=JSONObject.parseObject(body.toJSONString(),SbElevatorAddbasicinfo.class);
				sea2.setId(sbElevatorAddbasicinfo.getId());
				sbElevatorAddbasicinfoService.updateSbElevatorAddbasicinfo(sea2);
				json1.put("body", body);
				String str = ZCAPIClient.reportedData2019("elevator/addBasicInfo", json1);
				JSONObject status1 = JSONObject.parseObject(str);
				if ("0".equals(status1.getString("code"))) {
					i += 1;
				}
				//上传升降机参数信息
				JSONObject body2 = new JSONObject();
				body2.put("device_no", sbElevatorAddbasicinfo.getDeviceNo());//设备编号
//				body2.put("name", "升降机");//设备名称
				body2.put("L_Load_Capacity", sbElevatorAddparams.getLLoadCapacity() * 1000);//最大载重
				body2.put("L_Height", sbElevatorAddparams.getLHeight());//最大高度

				json1.put("body", body2);
				String str2 = ZCAPIClient.reportedData2019("elevator/addParams", json1);
				JSONObject status2 = JSONObject.parseObject(str2);
				if ("0".equals(status2.getString("code"))) {
					i += 1;
				}
			}
		}
		return i;
	}

}
