package com.hujiang.project.zhgd.lyPersonnel.service;

import com.hujiang.common.support.Convert;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import com.hujiang.project.zhgd.lyDevicePersonnel.mapper.LyDevicePersonnelMapper;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnelRecord;
import com.hujiang.project.zhgd.lyPersonnel.mapper.LyPersonnelMapper;
import com.hujiang.project.zhgd.lyRegistrationRecord.domain.LyRegistrationRecord;
import com.hujiang.project.zhgd.lyRegistrationRecord.mapper.LyRegistrationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
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
	public void personnelInOUt(LyPersonnel lyPersonnel,String ispresent){

		if("0".equals(ispresent)){
			insertLyPersonnel(lyPersonnel);
		}else{
			deleteAttendance(lyPersonnel);
		}
	}

//进场
	private void insertAttendance(LyPersonnel lyPersonnel) {

		HjAttendanceDevice had=new HjAttendanceDevice();
		had.setProjectId(lyPersonnel.getPid());
		List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
		LyDevicePersonnel hdpw=new LyDevicePersonnel();
		hdpw.setPersonnelId(lyPersonnel.getId());
		List<LyDevicePersonnel> list;
		for(HjAttendanceDevice h: hadList){
				//看是否是待删除人脸
				hdpw.setStatus("2");
				hdpw.setDeviceNo(h.getDeviceNo());
				list=lyDevicePersonnelMapper.selectLyDevicePersonnelList(hdpw);
				//是的话就不用删除了，直接改状态
				if(list.size()>0){
					hdpw.setStatus("1");
					lyDevicePersonnelMapper.updateLyDevicePersonnelTwo(hdpw);
				}else{
					//否的话就待添加
					hdpw.setStatus("0");
					lyDevicePersonnelMapper.insertLyDevicePersonnel(hdpw);
				}


		}
	}
	//离场操作
	private void deleteAttendance(LyPersonnel lyPersonnel) {

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
}
