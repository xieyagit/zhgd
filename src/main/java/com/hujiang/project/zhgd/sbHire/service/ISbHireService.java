package com.hujiang.project.zhgd.sbHire.service;

import com.hujiang.project.zhgd.sbHire.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备人员 服务层
 * 
 * @author hujiang
 * @date 2019-07-04
 */
public interface ISbHireService 
{
	public List<SbAreaProject> selectAreaProjectList();

	/**
	 * pc和移动
	 * 查询全部项目名称
	 * @return
	 */
	public List<HirePeople> selectProject(Integer projectId);
	/**
	 * pc和移动
	 *查询全部工业区
	 * @return
	 */
	public List<HirePeople> selectArea(Integer pId);
	public List<SbAreaLocaltion> selectAreaLocaltion();
	public List<SbAreaCertificate> selectAreaCertificate();
	/**
	 * pc和移动
	 * 查询人员
	 * @param areaId
	 * @return
	 */
	public List<HirePeople> selectPeople(Integer areaId);
	/**
	 * pc和移动
	 * 查询人员时间
	 * @param imei
	 * @return
	 */
	HirePeople selectTime(String imei);

	/**
	 * 查询人员时间
	 * @param imei
	 * @return
	 */
	List<HirePeople> selectTimeList(@Param(value = "imei")String imei);

	/**
	 * 查询人员时间
	 * @param imei
	 * @return
	 */
	HirePeople selectTimeTwo(String imei,String watchDate);
	/**
	 * 根据姓名或设备编号或电话查询
	 * @return
	 */
	public List<Hire> selectHireSearch(String filed,Integer projectId);

	/**
	 * 查询多个人员在线信息
	 * @param userName
	 * @return
	 */
	List<HirePeople> selectPeopleList(String userName,Integer projectId);

	/**
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistory(String filed,Integer projectId);
	/**
	 * pc
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistoryTime(String imei,String startTime);

	/**
	 * pc
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistoryTimeTwo(String imei);

	/**
	 * 根据姓名查询
	 * 移动端
	 * @param userName
	 * @return
	 */
	public List<Hire> selectHireByName(String userName,Integer projectId);


	/**
	 * 根据姓名查询做开始进的信息
	 * 移动端
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public Hire selectHireByTimeOne(String imei,String watchDate);

	/**
	 * 根据姓名查询做开始进的信息
	 * 移动端
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public Hire selectHireByTimeTwo(String imei,String watchDate);

	/**
	 * app
	 * 查询当天历史轨迹
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public List<Hire> selectHireByTimeHistory(String imei,String watchDate);

	/**
     * 查询设备人员信息
     * 
     * @param id 设备人员ID
     * @return 设备人员信息
     */
	public SbHire selectSbHireById(Integer id);
	
	/**
     * 查询设备人员列表
     * 
     * @param sbHire 设备人员信息
     * @return 设备人员集合
     */
	public List<SbHire> selectSbHireList(SbHire sbHire);
	
	/**
     * 新增设备人员
     * 
     * @param sbHire 设备人员信息
     * @return 结果
     */
	public int insertSbHire(SbHire sbHire);
	
	/**
     * 修改设备人员
     * 
     * @param sbHire 设备人员信息
     * @return 结果
     */
	public int updateSbHire(SbHire sbHire);
		
	/**
     * 删除设备人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbHireByIds(String ids);
	
}
