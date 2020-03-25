package com.hujiang.project.zhgd.lyDevicePersonnel.service;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import java.util.List;
import java.util.Map;

/**
 * 楼宇考勤设备人员 服务层
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public interface ILyDevicePersonnelService 
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
     * 删除楼宇考勤设备人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyDevicePersonnelByIds(String ids);
	//清除访客人员
	public int deleteLyDevicePersonnelTypeTwo();
	public int updateLyDevicePersonnelTypeTwo();
	public List<HjDeviceProjectworkersParam> selectLyDevicePersonnelListTwo(Map<String,String> param);
	public int updateLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel);
	public int deleteLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel);
}
