package com.hujiang.project.zhgd.sbHire.mapper;

import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbHire.domain.Hire;
import com.hujiang.project.zhgd.sbHire.domain.HirePeople;
import com.hujiang.project.zhgd.sbHire.domain.SbHire;
import org.apache.ibatis.annotations.Param;
import org.bytedeco.javacpp.presets.opencv_core;

import java.util.List;

/**
 * 设备人员 数据层
 * 
 * @author hujiang
 * @date 2019-07-04
 */
public interface SbHireMapper 
{


	/**
	 * pc
	 * 根据姓名或设备编号或电话查询
	 * @return
	 */
	public List<Hire> selectHireSearch(@Param("filed")String filed,
										@Param("projectId")Integer projectId);

	/**
	 * pc
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistory(@Param("filed")String filed,
										 @Param("projectId")Integer projectId);
	/**
	 * pc
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistoryTime(@Param(value = "imei")String imei,
											@Param("startTime")String startTime);
	/**
	 * pc
	 * 查询历史轨迹
	 * @return
	 */
	public List<Hire> selectHireHistoryTimeTwo(@Param(value = "imei")String imei);


	/**
	 * pcAndAPP
	 * 查询全部项目名称
	 * @return
	 */
	public List<HirePeople> selectProject(@Param("projectId")Integer projectId);

	/**
	 * pcAndApp
	 *查询全部工业区
	 * @return
	 */
	public List<HirePeople> selectArea(@Param(value = "pId")Integer pId);

	/**
	 * pcAndApp
	 * 查询人员
	 * @param areaId
	 * @return
	 */
	public List<HirePeople> selectPeople(@Param(value = "areaId")Integer areaId);

	/**
	 * 查询人员时间
	 * @param imei
	 * @return
	 */
	HirePeople selectTime(@Param(value = "imei")String imei);

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
	HirePeople selectTimeTwo(@Param(value = "imei")String imei,@Param("watchDate")String watchDate);


	/**
	 * 查询多个人员在线信息
	 * @param userName
	 * @return
	 */
	List<HirePeople> selectPeopleList(@Param("userName")String userName,@Param("projectId")Integer projectId);

	/**
	 * 根据姓名查询
	 * 移动端
	 * @param userName
	 * @return
	 */
	public List<Hire> selectHireByName(@Param("userName")String userName,@Param("projectId")Integer projectId);


	/**
	 * 根据姓名查询做开始进的信息
	 * 移动端
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public Hire selectHireByTimeOne(@Param("imei")String imei,@Param("watchDate")String watchDate);

	/**
	 * 根据姓名查询做开始进的信息
	 * 移动端
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public Hire selectHireByTimeTwo(@Param("imei")String imei,@Param("watchDate")String watchDate);

	/**
	 * app
	 * 查询当天历史轨迹
	 * @param imei
	 * @param watchDate
	 * @return
	 */
	public List<Hire> selectHireByTimeHistory(@Param("imei")String imei,@Param("watchDate")String watchDate);
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
     * 删除设备人员
     * 
     * @param id 设备人员ID
     * @return 结果
     */
	public int deleteSbHireById(Integer id);
	
	/**
     * 批量删除设备人员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbHireByIds(String[] ids);
	
}