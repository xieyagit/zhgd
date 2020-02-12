package com.hujiang.project.zhgd.sbCraneAddrecord.service;

import com.hujiang.project.zhgd.sbCraneAddrecord.domain.CraneAddRecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbgroup.domain.CraneKB;

import java.util.List;
import java.util.Map;

/**
 * 塔式起重机实时数据 服务层
 *
 * @author hujiang
 * @date 2019-06-21
 */
public interface ISbCraneAddrecordService
{
	public List<SbCraneAddrecord> selectCraneAddrecordList(Map<String, Object> map);
	public CraneKB selectCount(Map<String, Object> map);



	public SbCraneAddrecord selectSbCraneAddRecord(String hxzId, String runtime);
	public CraneAddRecord selectCraneAddRecordName(String hxzId, String runtime, Integer projectId);
	/**
	 * 查询塔吊历史记录
	 * 移动端
	 * @return
	 */
	public List<SbCraneAddrecord> selectSbCraneAddRecordHistory(String hxzId, String dateTime, String endTime);


	/**
	 * 查询塔式起重机实时数据信息
	 *
	 * @param id 塔式起重机实时数据ID
	 * @return 塔式起重机实时数据信息
	 */
	public SbCraneAddrecord selectSbCraneAddrecordById(Long id);

	/**
	 * 查询塔式起重机实时数据列表
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 塔式起重机实时数据集合
	 */
	public List<SbCraneAddrecord> selectSbCraneAddrecordList(SbCraneAddrecord sbCraneAddrecord);
	public List<SbCraneAddrecord> selectSbCraneAddrecordListTwo(SbCraneAddrecord sbCraneAddrecord);
	public List<SbCraneAddrecord> selectSbCraneAddrecordListThree(Map<String, Object> map);


	/**
	 * 新增塔式起重机实时数据
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 结果
	 */
	public int insertSbCraneAddrecord(SbCraneAddrecord sbCraneAddrecord);

	/**
	 * 修改塔式起重机实时数据
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 结果
	 */
	public int updateSbCraneAddrecord(SbCraneAddrecord sbCraneAddrecord);

	/**
	 * 删除塔式起重机实时数据信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteSbCraneAddrecordByIds(String ids);


	public List<SbCraneAddrecord> selectCraneCount(Map<String, Object> map);
	public Integer selectCraneCountTwo(Map<String, Object> map);
	public Integer selectCraneCountThree(Map<String, Object> map);

	/** 智慧工地1.0看板塔吊*/
	public SbCraneAddrecord kanban(String hxzid);
	public SbCraneAddrecord lists(String hxzid);
	public List<SbCraneAddrecord> list(Integer Pid);
	public List<SbCraneAddrecord> kanbans(Integer Pid);
}
