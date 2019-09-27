package com.hujiang.project.zhgd.hjWorkerRecord.mapper;

import com.hujiang.project.zhgd.hjWorkerRecord.domain.HjWorkerRecord;
import java.util.List;	

/**
 * 工人从业记录 数据层
 * 
 * @author hujiang
 * @date 2019-05-17
 */
public interface HjWorkerRecordMapper 
{
	/**
     * 查询工人从业记录信息
     * 
     * @param id 工人从业记录ID
     * @return 工人从业记录信息
     */
	public HjWorkerRecord selectHjWorkerRecordById(Integer id);
	
	/**
     * 查询工人从业记录列表
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 工人从业记录集合
     */
	public List<HjWorkerRecord> selectHjWorkerRecordList(HjWorkerRecord hjWorkerRecord);
	
	/**
     * 新增工人从业记录
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 结果
     */
	public int insertHjWorkerRecord(HjWorkerRecord hjWorkerRecord);
	
	/**
     * 修改工人从业记录
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 结果
     */
	public int updateHjWorkerRecord(HjWorkerRecord hjWorkerRecord);
	
	/**
     * 删除工人从业记录
     * 
     * @param id 工人从业记录ID
     * @return 结果
     */
	public int deleteHjWorkerRecordById(Integer id);
	
	/**
     * 批量删除工人从业记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjWorkerRecordByIds(String[] ids);
	
}