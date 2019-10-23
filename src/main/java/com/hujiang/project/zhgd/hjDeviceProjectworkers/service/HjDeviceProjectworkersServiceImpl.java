package com.hujiang.project.zhgd.hjDeviceProjectworkers.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.mapper.HjDeviceProjectworkersMapper;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.common.support.Convert;

/**
 * 考勤设备人员 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-08
 */
@Service
public class HjDeviceProjectworkersServiceImpl implements IHjDeviceProjectworkersService 
{
	@Autowired
	private HjDeviceProjectworkersMapper hjDeviceProjectworkersMapper;

	/**
     * 查询考勤设备人员信息
     * 
     * @param id 考勤设备人员ID
     * @return 考勤设备人员信息
     */
    @Override
	public HjDeviceProjectworkers selectHjDeviceProjectworkersById(Integer id)
	{
	    return hjDeviceProjectworkersMapper.selectHjDeviceProjectworkersById(id);
	}
	
	/**
     * 查询考勤设备人员列表
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 考勤设备人员集合
     */
	@Override
	public List<HjDeviceProjectworkers> selectHjDeviceProjectworkersList(HjDeviceProjectworkers hjDeviceProjectworkers)
	{
	    return hjDeviceProjectworkersMapper.selectHjDeviceProjectworkersList(hjDeviceProjectworkers);
	}
	
    /**
     * 新增考勤设备人员
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 结果
     */
	@Override
	public int insertHjDeviceProjectworkers(HjDeviceProjectworkers hjDeviceProjectworkers)
	{
	    return hjDeviceProjectworkersMapper.insertHjDeviceProjectworkers(hjDeviceProjectworkers);
	}
	
	/**
     * 修改考勤设备人员
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 结果
     */
	@Override
	public int updateHjDeviceProjectworkers(HjDeviceProjectworkers hjDeviceProjectworkers)
	{
	    return hjDeviceProjectworkersMapper.updateHjDeviceProjectworkers(hjDeviceProjectworkers);
	}
	@Override
	public int updateHjDeviceProjectworkersTwo(HjDeviceProjectworkers hjDeviceProjectworkers)
	{
		return hjDeviceProjectworkersMapper.updateHjDeviceProjectworkersTwo(hjDeviceProjectworkers);
	}
	/**
     * 删除考勤设备人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjDeviceProjectworkersByIds(String ids)
	{
		return hjDeviceProjectworkersMapper.deleteHjDeviceProjectworkersByIds(Convert.toStrArray(ids));
	}
	@Override
	public List<HjProjectWorkers> selectHjProjectworkersList(Map<String,String> param){
		return hjDeviceProjectworkersMapper.selectHjProjectworkersList(param);
	}
	@Override
	public List<HjDeviceProjectworkersParam> selectHjProjectworkersListTwo(Map<String,String> param){
		return hjDeviceProjectworkersMapper.selectHjProjectworkersListTwo(param);
	}
	@Override
	public List<HjDeviceProjectworkersParam> selectHjProjectworkersListThree(Map<String,String> param){
		return hjDeviceProjectworkersMapper.selectHjProjectworkersListThree(param);
	}
	@Override
	public int deleteIds(Map<String,String> param){
		return hjDeviceProjectworkersMapper.deleteIds(param);
	}
	@Override
	public int deleteHjDeviceProjectworkersTwo(HjDeviceProjectworkers hjDeviceProjectworkers){
		return hjDeviceProjectworkersMapper.deleteHjDeviceProjectworkersTwo(hjDeviceProjectworkers);
	}
}
