package com.hujiang.project.zhgd.hjAttendanceRecord.service;

import com.hujiang.project.zhgd.hjAttendanceRecord.domain.DongTai;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.TCount;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考勤记录 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjAttendanceRecordService 
{
	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
	public HjAttendanceRecord selectHjAttendanceRecordById(Integer id);

	/**
	 * 获取最新一条下班考勤记录
	 * @param hjAttendanceRecord
	 * @return
	 */
	public HjAttendanceRecord selectNewHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord);
	/**
     * 查询考勤记录列表
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	public List<HjAttendanceRecord> selectHjAttendanceRecordList(HjAttendanceRecord hjAttendanceRecord);

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
	 * 执行保存工人上班工时
	 * @param empId
	 * @param empName
	 * @param projectId
	 */
	public void addTime(Integer empId,String empName,Integer projectId);
	/**
	 * 根据人员id查询该日期是否有进出考勤
	 * @param id
	 * @param time
	 * @return
	 */
	public List<HjAttendanceRecord> selectHjAttendanceRecordInAndOut(Integer id,String time);

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
     * 删除考勤记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjAttendanceRecordByIds(String ids);

	/**
	 * 劳务工人考勤情况
	 * @param projectId 项目id
	 * @return
	 */
    Map<String, Object> selectWorkerAttendance(Integer projectId);

	/**
	 * 管理人员考勤情况
	 * @param projectId 项目id
	 * @return
	 */
	Map<String, Object> selectAdministration(Integer projectId);

	/**
	 * 人脸考勤
	 * @param
	 * @return
	 */
    Map<String, Object> insertAdministration(HjAttendanceRecord hjAttendanceRecord,MultipartFile file);

	/**
	 * pc端查看考勤记录
	 */
	List<Param> selectAttendanceRecordListTwo(Param param);
	/**
	 * 本月出勤情况
	 * @param userId
	 * @return
	 */
	List<HjAttendanceRecord> selectAttendanceRecordListThree(Integer userId);
	/**
	 * 今日出勤情况
	 * @param pid
	 * @return
	 */
	List<HjAttendanceRecord> selectJinRiChuQin(Integer pid);
	/**
	 * 指定项目关键岗位出勤
	 * @param pid
	 * @return
	 */
	List<HjAttendanceRecord> selectGJGWChuQin(Integer pid);
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
	List<TCount> getZCCount(Integer pid);
	/**
	 * 在场实时人数
	 * @param pid
	 * @return
	 */
	List<HjAttendanceRecord> selectZCSSRS( Integer pid);


	public List<HjAttendanceRecord> list(Integer projectId,String passedTime);
	public List<HjAttendanceRecord> lists(Integer projectId,String jobName);

	public List<HjAttendanceRecord> turnover(Integer projectId,String passedTime);
	public List<HjAttendanceRecord> turnovers(Integer projectId);
	/** 项目出勤统计*/
	public List<HjAttendanceRecord> item(Integer projectId);
	public HjAttendanceRecord its(Integer id);
	public List<HjAttendanceRecord> itemin(Integer id,String passedTime);
	public List<HjAttendanceRecord> itemout(Integer id,String passedTime);

	public List<HjAttendanceRecord> labour(Integer projectId,String passedTimes);

	/** 智慧工地1.0 今日出勤总人数*/
	public List<HjAttendanceRecord> attendance(Integer projectId);
	/** 智慧工地1.0 今日工人出勤人数*/
	public List<HjAttendanceRecord> attendances(Integer projectId);

	/** 工人上工情况*/
	public List<HjAttendanceRecord> items(Integer projectId,String passedTime);
	public List<HjAttendanceRecord> ite(Integer projectId,String passedTime);
}
