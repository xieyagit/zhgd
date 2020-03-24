package com.hujiang.project.zhgd.lyPersonnel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.support.Convert;
import com.hujiang.project.client.SystemClient;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import com.hujiang.project.zhgd.lyDevicePersonnel.mapper.LyDevicePersonnelMapper;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnelRecord;
import com.hujiang.project.zhgd.lyPersonnel.mapper.LyPersonnelMapper;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 楼宇人员 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Service
public class LyPersonnelServiceImpl implements ILyPersonnelService 
{
	@Autowired
	private LyPersonnelMapper lyPersonnelMapper;

	@Autowired
	private IHjAttendanceDeviceService hjAttendanceDeviceService;
	@Autowired
	private LyDevicePersonnelMapper lyDevicePersonnelMapper;
	@Autowired
	private SystemClient client;
	@Autowired
	private ILyDevicePersonnelService lyDevicePersonnelService;
	/**
     * 查询楼宇人员信息
     * 
     * @param id 楼宇人员ID
     * @return 楼宇人员信息
     */
    @Override
	public LyPersonnel selectLyPersonnelById(Integer id)
	{
	    return lyPersonnelMapper.selectLyPersonnelById(id);
	}
	
	/**
     * 查询楼宇人员列表
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 楼宇人员集合
     */
	@Override
	public List<LyPersonnel> selectLyPersonnelList(LyPersonnel lyPersonnel)
	{
	    return lyPersonnelMapper.selectLyPersonnelList(lyPersonnel);
	}
	
    /**
     * 新增楼宇人员
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 结果
     */
	@Override
	public int insertLyPersonnel(LyPersonnel lyPersonnel)
	{
	    return lyPersonnelMapper.insertLyPersonnel(lyPersonnel);
	}
	
	/**
     * 修改楼宇人员
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 结果
     */
	@Override
	public int updateLyPersonnel(LyPersonnel lyPersonnel)
	{
	    return lyPersonnelMapper.updateLyPersonnel(lyPersonnel);
	}

	/**
     * 删除楼宇人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyPersonnelByIds(String ids)
	{
		return lyPersonnelMapper.deleteLyPersonnelByIds(Convert.toStrArray(ids));
	}

	/**
	 * 进退场
	 * @param lyPersonnel
	 * @param ispresent
	 */
	@Override
	public void personnelInOUt(LyPersonnel lyPersonnel,String ispresent)throws Exception{

		if("0".equals(ispresent)){
			insertAttendance(lyPersonnel);
		}else{
			deleteAttendance(lyPersonnel);
		}
	}

//进场
	private void insertAttendance(LyPersonnel lyPersonnel) throws Exception{

		HjAttendanceDevice had=new HjAttendanceDevice();
		had.setProjectId(lyPersonnel.getPid());
		List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
		LyDevicePersonnel hdpw=new LyDevicePersonnel();
		hdpw.setPersonnelId(lyPersonnel.getId());

		List<LyDevicePersonnel> list;
		for(HjAttendanceDevice h: hadList){
			if("yushi".equals(h.getDeviceFactory())){
				if(comparisonDate(h.getConnectTime())){
					String photo= BASE64DecodedMultipartFile.ImageToBase64ByOnline(lyPersonnel.getFaceUrl()).replaceAll("\r|\n", "");
					JSONObject json=new JSONObject();
					json.put("Num",1);
					JSONObject personInfoList=new JSONObject();
					personInfoList.put("PersonID",lyPersonnel.getId());
					personInfoList.put("LastChange",System.currentTimeMillis()/1000);
					personInfoList.put("PersonName",lyPersonnel.getEmpName());
					JSONObject timeTemplate=new JSONObject();
					timeTemplate.put("BeginTime",0);
					timeTemplate.put("EndTime",4294967295L);
					timeTemplate.put("Index",0);
					personInfoList.put("TimeTemplate",timeTemplate);
					personInfoList.put("IdentificationNum",1);
					JSONArray codeArray=new JSONArray();
					JSONObject codeObject=new JSONObject();
					codeObject.put("Type",0);
					codeObject.put("Number",lyPersonnel.getIdCode());
					codeArray.add(codeObject);
					personInfoList.put("IdentificationList",codeArray);
					personInfoList.put("ImageNum",1);
					JSONArray imageArray=new JSONArray();
					JSONObject imageObject=new JSONObject();
					imageObject.put("FaceID",lyPersonnel.getId());
					imageObject.put("Size",photo.length());
					imageObject.put("Data",photo);
					imageArray.add(imageObject);
					personInfoList.put("ImageList",imageArray);
					JSONArray perList=new JSONArray();
					perList.add(personInfoList);
					json.put("PersonInfoList",perList);
					client.insertPerson(json.toJSONString(),h.getDeviceNo());
					hdpw.setStatus("1");
					hdpw.setDeviceNo(h.getDeviceNo());
					hdpw.setType(lyPersonnel.getType());
					lyDevicePersonnelService.insertLyDevicePersonnel(hdpw);
				}else{
					hdpw.setStatus("0");
					hdpw.setDeviceNo(h.getDeviceNo());
					hdpw.setType(lyPersonnel.getType());
					lyDevicePersonnelService.insertLyDevicePersonnel(hdpw);
				}

			}
				//看是否是待删除人脸
				hdpw.setStatus("2");
				hdpw.setDeviceNo(h.getDeviceNo());
				list=lyDevicePersonnelMapper.selectLyDevicePersonnelList(hdpw);
				//是的话就不用删除了，直接改状态
				 if(list.size()>0){
					hdpw.setStatus("1");
					hdpw.setType(lyPersonnel.getType());
					lyDevicePersonnelMapper.updateLyDevicePersonnelTwo(hdpw);
				}else{
					//否的话就待添加
					hdpw.setStatus("0");
					list=lyDevicePersonnelMapper.selectLyDevicePersonnelList(hdpw);
					if(list.size()<=0){
						hdpw.setStatus("1");
						list=lyDevicePersonnelMapper.selectLyDevicePersonnelList(hdpw);
						if(list.size()<=0){
							hdpw.setStatus("0");
							hdpw.setType(lyPersonnel.getType());
							lyDevicePersonnelMapper.insertLyDevicePersonnel(hdpw);
							//如果是宇视设备，就立刻添加

						}

					}

				}


		}
	}
	//离场操作
	private void deleteAttendance(LyPersonnel lyPersonnel) throws Exception{

		HjAttendanceDevice had=new HjAttendanceDevice();
		had.setProjectId(lyPersonnel.getPid());
		List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
		LyDevicePersonnel hdpw=new LyDevicePersonnel();
		hdpw.setPersonnelId(lyPersonnel.getId());

		List<LyDevicePersonnel> list;
		for(HjAttendanceDevice h: hadList){

				//看是否是待添加人脸
				hdpw.setStatus("0");
			hdpw.setDeviceNo(h.getDeviceNo());
				list=lyDevicePersonnelMapper.selectLyDevicePersonnelTwo(hdpw);
				//是的话就直接删除
				if(list.size()>0){
					lyDevicePersonnelMapper.deleteLyDevicePersonnelById(list.get(0).getId());
				}else if("yushi".equals(h.getDeviceFactory())){
					if(comparisonDate(h.getConnectTime())){
						client.deletePerson(lyPersonnel.getId(),h.getDeviceNo());
						lyDevicePersonnelService.deleteLyDevicePersonnelTwo(hdpw);
					}else{
						//否的话就待删除
						hdpw.setStatus("2");
						hdpw.setPersonnelId(lyPersonnel.getId());
						lyDevicePersonnelMapper.updateLyDevicePersonnelTwo(hdpw);}
				}else{
					//否的话就待删除
					hdpw.setStatus("2");
					hdpw.setPersonnelId(lyPersonnel.getId());
					lyDevicePersonnelMapper.updateLyDevicePersonnelTwo(hdpw);
				}


		}
	}

	/**
	 * 获取公司人员
	 * @param lyPersonnel
	 * @return
	 */
	@Override
	public List<LyCompanyPersonnel> getLyCompanyPersonnel(LyPersonnel lyPersonnel){
		return lyPersonnelMapper.getLyCompanyPersonnel(lyPersonnel);
	}
	@Override
	public Integer zzryzs(Integer pid){
		return lyPersonnelMapper.zzryzs(pid);
	}
	@Override
	public Integer zzryin(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.zzryin(lyAttendanceRecord);
	}
	@Override
	public Integer zzryout(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.zzryout(lyAttendanceRecord);
	}
	@Override
	public Integer fkryzs(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.fkryzs(lyAttendanceRecord);
	}
	@Override
	public Integer fkryin(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.fkryin(lyAttendanceRecord);
	}
	@Override
	public Integer fkryout(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.fkryout(lyAttendanceRecord);
	}
	@Override
	public List<LyPersonnelRecord> getLyPersonnelRecordZZ(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.getLyPersonnelRecordZZ(lyAttendanceRecord);
	}
	@Override
	public List<LyPersonnelRecord> getLyPersonnelRecordFK(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.getLyPersonnelRecordFK(lyAttendanceRecord);
	}
	@Override
	public int insertBlacklist(String ids){
		return lyPersonnelMapper.insertBlacklist(ids);
	}
	@Override
	public int deleteBlacklist(String ids){
		return lyPersonnelMapper.deleteBlacklist(ids);
	}
	@Override
	public Integer zzryinout(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.zzryinout(lyAttendanceRecord);
	}
	@Override
	public Integer fkryinout(LyAttendanceRecord lyAttendanceRecord){
		return lyPersonnelMapper.fkryinout(lyAttendanceRecord);
	}
	public boolean comparisonDate(String time) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date kqTime=sdf.parse(time);//考勤时间
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -10);//
		Date dqTime=sdf.parse(sdf.format(beforeTime.getTime()));//当前时间
		if(dqTime.before(kqTime)){
			return true;
		} else{
			return false;
		}

	}
}
