package com.hujiang.project.zhgd.sbProjectElectricityBox.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbPowerBoxAdd;
import com.hujiang.project.zhgd.utils.CommonChars;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbProjectElectricityBox.mapper.SbProjectElectricityBoxMapper;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import com.hujiang.common.support.Convert;

/**
 * 项目电箱 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service
public class SbProjectElectricityBoxServiceImpl implements ISbProjectElectricityBoxService 
{
	@Autowired
	private SbProjectElectricityBoxMapper sbProjectElectricityBoxMapper;

	@Override
	public List<SbProjectElectricityBox> selectByProjectIdAndHxzId(Integer projectId, String hxzid) {
		Map<String,Object> conditionMap = new HashMap<>();
		conditionMap.put("projectId",projectId);
		conditionMap.put("hxzid",hxzid);
		return sbProjectElectricityBoxMapper.selectByProjectIdAndHxzId(conditionMap);
	}

	/**
     * 查询项目电箱信息
     * 
     * @param id 项目电箱ID
     * @return 项目电箱信息
     */
    @Override
	public SbProjectElectricityBox selectSbProjectElectricityBoxById(Integer id)
	{
	    return sbProjectElectricityBoxMapper.selectSbProjectElectricityBoxById(id);
	}
	
	/**
     * 查询项目电箱列表
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 项目电箱集合
     */
	@Override
	public List<SbProjectElectricityBox> selectSbProjectElectricityBoxList(SbProjectElectricityBox sbProjectElectricityBox)
	{
	    return sbProjectElectricityBoxMapper.selectSbProjectElectricityBoxList(sbProjectElectricityBox);
	}


	/**
	 * 上报电箱参数
	 * @param
	 * @return
	 * @author yant
	 */
	@Override
	public AjaxResult reportedEBox(SbPowerBoxAdd sbPowerBoxAdd)throws IOException, URISyntaxException {
		AjaxResult a = new AjaxResult();
		if(sbPowerBoxAdd != null) {
			Integer pid = sbPowerBoxAdd.getProjectId();
			HjSynchronizationInformation hsi = new HjSynchronizationInformation();
			hsi.setProjectId(pid);
			hsi.setPlatformName("WORKSBUREAU");
			hsi.setState(1);
			hsi.setApiType("keytype5");
			//上传电箱基本信息
			JSONObject json1 = new JSONObject();
			//project.getApiKey()
			json1.put("api_key", CommonChars.EBoxApiKey);
			json1.put("api_version", CommonChars.ApiVersion);
			//时间戳
			//json1.put("timestamp", "");
			//项目编码
			json1.put("project_code",CommonChars.ProjectCode);
			//工程编码
			json1.put("eng_code", CommonChars.EngCode);
			//对API输入参数进行MD5加密获得
			//json1.put("signature", "");
			JSONObject body = new JSONObject();
			//设备编号
			body.put("device_no", sbPowerBoxAdd.getElectricityBoxId());
			//设备类型
			body.put("type", sbPowerBoxAdd.getType());
			//设备名称
			body.put("name", sbPowerBoxAdd.getName());
			//安装商
			body.put("install_company", sbPowerBoxAdd.getInstallCompany());
			//区域类型参考区域类型
			body.put("installadd_type", sbPowerBoxAdd.getInstalladdType());
			//电缆温度限值(℃)
			body.put("temp_limit", sbPowerBoxAdd.getTempLimit());
			//漏电流限值(mA)
			body.put("elec_limit", sbPowerBoxAdd.getElecLimit());
			//周围环境温度限值(℃)
			body.put("around_temp", sbPowerBoxAdd.getAroundTemp());

			json1.put("body", body);
			String str = ZCAPIClient.reportedData2019("powerBox/addParams", json1);//ZCAPIClient.reportedData("/crane/addBasicInfo", json1);
			JSONObject status1 = JSONObject.parseObject(str);
				a.put("result",status1.getString("result"));
				a.put("code",status1.getString("code"));
				a.put("message",status1.getString("message"));
				return a;
		}
		return AjaxResult.error();
	}


	/**
	 * 上报电箱实时数据
	 * @param
	 * @return
	 * @author yant
	 */
	@Override
	public AjaxResult reportedEBoxData(SbPowerBoxAdd sbPowerBoxAdd) throws IOException, URISyntaxException {
		AjaxResult a = new AjaxResult();
		if(sbPowerBoxAdd != null) {
			Integer pid = sbPowerBoxAdd.getProjectId();
			HjSynchronizationInformation hsi = new HjSynchronizationInformation();
			hsi.setProjectId(pid);
			hsi.setPlatformName("WORKSBUREAU");
			hsi.setState(1);
			hsi.setApiType("keytype5");
			//上传电箱基本信息
			JSONObject json1 = new JSONObject();
			json1.put("api_key", CommonChars.EBoxApiKey);//project.getApiKey()
			json1.put("api_version", CommonChars.ApiVersion);
			//json1.put("timestamp", "");//时间戳
			json1.put("project_code",CommonChars.ProjectCode);//项目编码
			json1.put("eng_code", CommonChars.EngCode);//工程编码
			json1.put("signature", "");//对API输入参数进行MD5加密获得
			JSONObject body = new JSONObject();
			body.put("device_no", sbPowerBoxAdd.getElectricityBoxId());//设备编号
			//设备类型
			body.put("type", sbPowerBoxAdd.getElectricityBoxId());
			//设备名称
			body.put("name", sbPowerBoxAdd.getElectricityBoxId());
			body.put("install_company", sbPowerBoxAdd.getElectricityBoxId());//安装商
			body.put("installadd_type", sbPowerBoxAdd.getElectricityBoxId());//区域类型参考区域类型
			body.put("temp_limit", sbPowerBoxAdd.getElectricityBoxId());//电缆温度限值(℃)
			body.put("elec_limit", sbPowerBoxAdd.getElectricityBoxId());//漏电流限值(mA)
			body.put("around_temp", sbPowerBoxAdd.getElectricityBoxId());//周围环境温度限值(℃)

			json1.put("body", body);
			String str = ZCAPIClient.reportedData2019("powerBox/addRecord", json1);//ZCAPIClient.reportedData("/crane/addBasicInfo", json1);
			JSONObject status1 = JSONObject.parseObject(str);
			if ("0".equals(status1.getString("code"))) {
				a.put("code",0);
			}else {
				a.put("code",status1.getString("code"));
				a.put("message",status1.getString("message"));
				return a;
			}
		}
		return AjaxResult.error();
	}

    /**
     * 新增项目电箱
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 结果
     */
	@Override
	public int insertSbProjectElectricityBox(SbProjectElectricityBox sbProjectElectricityBox)
	{
	    return sbProjectElectricityBoxMapper.insertSbProjectElectricityBox(sbProjectElectricityBox);
	}
	
	/**
     * 修改项目电箱
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 结果
     */
	@Override
	public int updateSbProjectElectricityBox(SbProjectElectricityBox sbProjectElectricityBox)
	{
	    return sbProjectElectricityBoxMapper.updateSbProjectElectricityBox(sbProjectElectricityBox);
	}

	/**
     * 删除项目电箱对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectElectricityBoxByIds(String ids)
	{
		return sbProjectElectricityBoxMapper.deleteSbProjectElectricityBoxByIds(Convert.toStrArray(ids));
	}
	
}
