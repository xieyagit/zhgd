package com.hujiang.project.zhgd.hjAttendanceLocation.mapper;

import com.hujiang.project.zhgd.hjAttendanceLocation.domain.HjAttendanceLocation;
import java.util.List;	

/**
 * 打卡定位 数据层
 * 
 * @author hujiang
 * @date 2020-02-26
 */
public interface HjAttendanceLocationMapper 
{
	/**
     * 查询打卡定位信息
     * 
     * @param id 打卡定位ID
     * @return 打卡定位信息
     */
	public HjAttendanceLocation selectHjAttendanceLocationById(Integer id);
	
	/**
     * 查询打卡定位列表
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 打卡定位集合
     */
	public List<HjAttendanceLocation> selectHjAttendanceLocationList(HjAttendanceLocation hjAttendanceLocation);
	
	/**
     * 新增打卡定位
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 结果
     */
	public int insertHjAttendanceLocation(HjAttendanceLocation hjAttendanceLocation);
	
	/**
     * 修改打卡定位
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 结果
     */
	public int updateHjAttendanceLocation(HjAttendanceLocation hjAttendanceLocation);
	
	/**
     * 删除打卡定位
     * 
     * @param id 打卡定位ID
     * @return 结果
     */
	public int deleteHjAttendanceLocationById(Integer id);
	
	/**
     * 批量删除打卡定位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjAttendanceLocationByIds(String[] ids);
	
}