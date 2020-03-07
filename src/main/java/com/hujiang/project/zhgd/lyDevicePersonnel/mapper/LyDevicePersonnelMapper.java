package com.hujiang.project.zhgd.lyDevicePersonnel.mapper;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import java.util.List;	

/**
 * 楼宇考勤设备人员 数据层
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public interface LyDevicePersonnelMapper 
{
	/**
     * 查询楼宇考勤设备人员信息
     * 
     * @param id 楼宇考勤设备人员ID
     * @return 楼宇考勤设备人员信息
     */
	public LyDevicePersonnel selectLyDevicePersonnelById(Integer id);
	
	/**
     * 查询楼宇考勤设备人员列表
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 楼宇考勤设备人员集合
     */
	public List<LyDevicePersonnel> selectLyDevicePersonnelList(LyDevicePersonnel lyDevicePersonnel);
	
	/**
     * 新增楼宇考勤设备人员
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 结果
     */
	public int insertLyDevicePersonnel(LyDevicePersonnel lyDevicePersonnel);
	
	/**
     * 修改楼宇考勤设备人员
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 结果
     */
	public int updateLyDevicePersonnel(LyDevicePersonnel lyDevicePersonnel);
	
	/**
     * 删除楼宇考勤设备人员
     * 
     * @param id 楼宇考勤设备人员ID
     * @return 结果
     */
	public int deleteLyDevicePersonnelById(Integer id);
	
	/**
     * 批量删除楼宇考勤设备人员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyDevicePersonnelByIds(String[] ids);
	public int updateLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel);
	public List<LyDevicePersonnel> selectLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel);
}