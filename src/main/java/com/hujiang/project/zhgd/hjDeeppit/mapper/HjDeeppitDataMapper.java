package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbAvg;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 深基坑数据记录 数据层
 * 
 * @author hujiang
 * @date 2019-09-04
 */
public interface HjDeeppitDataMapper 
{
	/**
     * 查询深基坑数据记录信息
     * 
     * @param id 深基坑数据记录ID
     * @return 深基坑数据记录信息
     */
	public HjDeeppitData selectHjDeeppitDataById(Integer id);
	
	/**
     * 查询深基坑数据记录列表
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 深基坑数据记录集合
     */
	public List<HjDeeppitData> selectHjDeeppitDataList(HjDeeppitData hjDeeppitData);

	public List<HjDeeppitData> selectHjDeeppitDataByTime();

	public List<HjDeeppitData> selectHjDeeppitDataByTimeByType(String types);

	/**
	 * 取最大值
	 *
	 * @param
	 * @return
	 */
	public String selectParmeterMax(SbAvg parmeter);
	/**
	 * 取最小值
	 *
	 * @param
	 * @return
	 */
	public String selectParmeterMin(SbAvg parmeter);
	/**
	 * 取平均值
	 *
	 * @param
	 * @return
	 */
	public String selectParmeterAvg(SbAvg parmeter);

	/**
	 * 按时间段查询历史数据
	 * @param factorId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<HjDeeppitData> selectHjDeeppitDataListT(@Param(value = "factorId") Integer factorId,@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

	public String selectHjDeeppitDataListV(@Param(value = "factorId") Integer factorId,@Param(value = "param") String param,@Param(value = "startTime") String startTime, @Param(value = "endTime")String endTime);

	/**
     * 新增深基坑数据记录
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 结果
     */
	public int insertHjDeeppitData(HjDeeppitData hjDeeppitData);
	
	/**
     * 修改深基坑数据记录
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 结果
     */
	public int updateHjDeeppitData(HjDeeppitData hjDeeppitData);
	
	/**
     * 删除深基坑数据记录
     * 
     * @param id 深基坑数据记录ID
     * @return 结果
     */
	public int deleteHjDeeppitDataById(Integer id);
	
	/**
     * 批量删除深基坑数据记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjDeeppitDataByIds(String[] ids);


	List<HjDeeppitData> selectToDay(Integer factorId, String date, String types);

}