package com.hujiang.project.zhgd.sbHire.service;

import java.util.List;

import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbHire.domain.Hire;
import com.hujiang.project.zhgd.sbHire.domain.HirePeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.sbHire.mapper.SbHireMapper;
import com.hujiang.project.zhgd.sbHire.domain.SbHire;
import com.hujiang.project.zhgd.sbHire.service.ISbHireService;
import com.hujiang.common.support.Convert;

/**
 * 设备人员 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-04
 */
@Service
public class SbHireServiceImpl implements ISbHireService 
{
	@Autowired
	private SbHireMapper sbHireMapper;



	@Override
	public List<HirePeople> selectProject(Integer projectId) {
		return sbHireMapper.selectProject(projectId);
	}

	@Override
	public List<HirePeople> selectArea(Integer pId) {
		return sbHireMapper.selectArea(pId);
	}

	@Override
	public List<HirePeople> selectPeople(Integer areaId) {
		return sbHireMapper.selectPeople(areaId);
	}

	@Override
	public HirePeople selectTime(String imei) {
		return sbHireMapper.selectTime(imei);
	}

	@Override
	public List<HirePeople> selectTimeList(String imei) {
		return sbHireMapper.selectTimeList(imei);
	}

	@Override
	public HirePeople selectTimeTwo(String imei,String watchDate) {
		return sbHireMapper.selectTimeTwo(imei,watchDate);
	}

	@Override
	public List<Hire> selectHireSearch(String filed,Integer projectId) {
		return sbHireMapper.selectHireSearch(filed,projectId);
	}

	@Override
	public List<HirePeople> selectPeopleList(String userName,Integer projectId) {
		return sbHireMapper.selectPeopleList(userName,projectId);
	}

	@Override
	public List<Hire> selectHireHistory(String filed,Integer projectId) {
		return sbHireMapper.selectHireHistory(filed,projectId);
	}

	@Override
	public List<Hire> selectHireHistoryTime(String imei,String startTime) {
		return sbHireMapper.selectHireHistoryTime(imei,startTime);
	}

	@Override
	public List<Hire> selectHireHistoryTimeTwo(String imei) {
		return sbHireMapper.selectHireHistoryTimeTwo(imei);
	}

	@Override
	public List<Hire> selectHireByName(String userName,Integer projectId) {
		return sbHireMapper.selectHireByName(userName,projectId);
	}


	@Override
	public Hire selectHireByTimeOne(String imei,String watchDate) {
		return sbHireMapper.selectHireByTimeOne(imei,watchDate);
	}

	@Override
	public Hire selectHireByTimeTwo(String imei, String watchDate) {
		return sbHireMapper.selectHireByTimeTwo(imei, watchDate);
	}

	@Override
	public List<Hire> selectHireByTimeHistory(String imei, String watchDate) {
		return sbHireMapper.selectHireByTimeHistory(imei,watchDate);
	}

	/**
     * 查询设备人员信息
     * 
     * @param id 设备人员ID
     * @return 设备人员信息
     */
    @Override
	public SbHire selectSbHireById(Integer id)
	{
	    return sbHireMapper.selectSbHireById(id);
	}
	
	/**
     * 查询设备人员列表
     * 
     * @param sbHire 设备人员信息
     * @return 设备人员集合
     */
	@Override
	public List<SbHire> selectSbHireList(SbHire sbHire)
	{
	    return sbHireMapper.selectSbHireList(sbHire);
	}
	
    /**
     * 新增设备人员
     * 
     * @param sbHire 设备人员信息
     * @return 结果
     */
	@Override
	public int insertSbHire(SbHire sbHire)
	{
	    return sbHireMapper.insertSbHire(sbHire);
	}
	
	/**
     * 修改设备人员
     * 
     * @param sbHire 设备人员信息
     * @return 结果
     */
	@Override
	public int updateSbHire(SbHire sbHire)
	{
	    return sbHireMapper.updateSbHire(sbHire);
	}

	/**
     * 删除设备人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbHireByIds(String ids)
	{
		return sbHireMapper.deleteSbHireByIds(Convert.toStrArray(ids));
	}
	
}
