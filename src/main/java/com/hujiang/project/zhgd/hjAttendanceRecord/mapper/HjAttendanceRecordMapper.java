package com.hujiang.project.zhgd.hjAttendanceRecord.mapper;

import com.hujiang.project.zhgd.hjAttendanceRecord.domain.DongTai;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.TCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤记录 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjAttendanceRecordMapper 
{
	/**
	 * 执行保存工人上班工时
	 * @param empId
	 * @param empName
	 * @param projectId
	 */
	public void addTime(@Param("empId") Integer empId,@Param("empName")String empName,@Param("projectId")Integer projectId);
	/**
	 * 获取最早一条上班考勤记录
	 * @param hjAttendanceRecord
	 * @return
	 */
	public HjAttendanceRecord selectHjAttendanceRecordListIn(HjAttendanceRecord hjAttendanceRecord);

	/**
	 * 获取最新一条下班考勤记录
	 * @param hjAttendanceRecord
	 * @return
	 */
	public HjAttendanceRecord selectHjAttendanceRecordListOut(HjAttendanceRecord hjAttendanceRecord);
	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
	public HjAttendanceRecord selectHjAttendanceRecordById(Integer id);
	
	/**
     * 查询考勤记录列表
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	public List<HjAttendanceRecord> selectHjAttendanceRecordList(HjAttendanceRecord hjAttendanceRecord);

	/**
	 * 根据人员id查询该日期是否有进出考勤
	 * @param id
	 * @param time
	 * @return
	 */
	public List<HjAttendanceRecord> selectHjAttendanceRecordInAndOut(@Param(value = "id") Integer id,@Param(value = "time")String time);

	/**
     * 新增考勤记录
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int insertHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord);
	
	/**
     * 修改考勤记录
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int updateHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord);
	
	/**
     * 删除考勤记录
     * 
     * @param id 考勤记录ID
     * @return 结果
     */
	public int deleteHjAttendanceRecordById(Integer id);
	
	/**
     * 批量删除考勤记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjAttendanceRecordByIds(String[] ids);

	/**
	 * 劳务工人考勤情况
	 * @param projectId 项目id
	 * @return
	 */
    Integer selectWorkerAttendance(@Param(value = "projectId") Integer projectId);

	/**
	 * 管理人员考勤情况
	 * @param projectId 项目id
	 * @return
	 */
	Integer selectAdministrationAttendance(@Param(value = "projectId") Integer projectId);

	/**
	 * pc端查看考勤记录
	 */
	List<com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param> selectAttendanceRecordListTwo(com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param param);

	/**
	 * 本月出勤情况
	 * @param userId
	 * @return
	 */
	List<HjAttendanceRecord> selectAttendanceRecordListThree(@Param(value = "userId") Integer userId);

	/**
	 * 今日出勤情况
	 * @param pid
	 * @return
	 */
	Integer selectJinRiChuQin(Integer pid);

	/**
	 * 指定项目关键岗位出勤
	 * @param pid
	 * @return
	 */
	Integer selectGJGWChuQin(Integer pid);

	/**
 * 工人考勤动态
 * @param pid
 * @return
 */
List<DongTai> selectGRKQDongTai(Integer pid);
	/**
	 * 管理人员考勤动态
	 * @param pid
	 * @return
	 */
	List<DongTai> selectGLKQDongTai(Integer pid);
	/**
	 * 根据项目id获取参建单位今日考勤人员统计
	 * @param pid
	 * @return
	 */
	List<TCount> getKQCount(Integer pid);

	/**
	 * 根据项目id获取参建单位在场人员统计
	 * @param pid
	 * @return
	 */
	List<TCount> getZCCount( Integer pid);

	/**
	 * 在场实时人数
	 * @param pid
	 * @return
	 */
	List<HjAttendanceRecord> selectZCSSRS( Integer pid);

	/**
	 * 智慧工地1.0看板
	 * */
	/**现场工种*/
	public List<HjAttendanceRecord> list(@Param("projectId") Integer projectId,@Param("passedTime") String passedTime);
	public List<HjAttendanceRecord> lists(@Param("projectId") Integer projectId,@Param("jobName") String jobName);

	/**人员动态*/
	public List<HjAttendanceRecord> turnover(@Param("projectId") Integer projectId,@Param("passedTime") String passedTime);

	/** 班组动态*/
	public List<HjAttendanceRecord> turnovers(@Param("projectId") Integer projectId);

	/** 项目出勤统计*/
	public List<HjAttendanceRecord> item(@Param("projectId") Integer projectId);
	public HjAttendanceRecord its(@Param("id") Integer id);
	public List<HjAttendanceRecord> itemin(@Param("id") Integer id,
									 @Param("passedTime") String passedTime);
	public List<HjAttendanceRecord> itemout(@Param("id") Integer id,
									 @Param("passedTime") String passedTime);

	public List<HjAttendanceRecord> labour(@Param("projectId") Integer projectId,@Param("passedTimes") String passedTimes);

	public List<HjAttendanceRecord> attendance(@Param("projectId") Integer projectId);
	public List<HjAttendanceRecord> attendances(@Param("projectId") Integer projectId);


	/** 工人上工情况*/
	public List<HjAttendanceRecord> items(@Param("projectId") Integer projectId,
										 @Param("passedTime") String passedTime);

	public List<HjAttendanceRecord> ite(@Param("projectId") Integer projectId,
										  @Param("passedTime") String passedTime);

	public HjAttendanceRecord selectNewHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord);

    /**
     * 电视看板工人考勤动态
     * @param projectId
     * @return
     */
    List<DongTai> selectWorkerList(Integer projectId);

    /**
     * 电视看板管理人员考勤动态
     * @param projectId
     * @return
     */
    List<DongTai> selectManagerList(Integer projectId);

}