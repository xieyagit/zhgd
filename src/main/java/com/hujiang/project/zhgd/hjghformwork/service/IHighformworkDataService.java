package com.hujiang.project.zhgd.hjghformwork.service;


import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbAvg;
import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import java.util.List;

/**
 * 高支模数据记录 服务层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface IHighformworkDataService 
{
	/**
     * 查询高支模数据记录信息
     * 
     * @param id 高支模数据记录ID
     * @return 高支模数据记录信息
     */
	public HighformworkData selectHighformworkDataById(Integer id);
	
	/**
     * 查询高支模数据记录列表
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 高支模数据记录集合
     */
	public List<HighformworkData> selectHighformworkDataList(HighformworkData highformworkData);
	public List<HighformworkData> selectHighformworkDataListTask(HighformworkData highformworkData);

	/**
     * 新增高支模数据记录
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 结果
     */
	public int insertHighformworkData(HighformworkData highformworkData);
	
	/**
     * 修改高支模数据记录
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 结果
     */
	public int updateHighformworkData(HighformworkData highformworkData);
		
	/**
     * 删除高支模数据记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighformworkDataByIds(String ids);


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
	public List<HighformworkData> selectHighformworkDataListT(Integer factorId, String startTime, String endTime);
	/**
	 * 图表数据点
	 * @param factorId
	 * @param param
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String selectHighformworkDataListV(Integer factorId,String param ,String startTime,String endTime);


}
