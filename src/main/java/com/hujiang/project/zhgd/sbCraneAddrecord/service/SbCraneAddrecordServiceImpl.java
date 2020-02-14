package com.hujiang.project.zhgd.sbCraneAddrecord.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.sbCraneAddrecord.domain.CraneAddRecord;

import com.hujiang.project.zhgd.sbGroup.domain.CraneKB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneAddrecord.mapper.SbCraneAddrecordMapper;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.common.support.Convert;

/**
 * 塔式起重机实时数据 服务层实现
 *
 * @author hujiang
 * @date 2019-06-21
 */
@Service@Transactional
public class SbCraneAddrecordServiceImpl implements ISbCraneAddrecordService
{
	@Autowired
	private SbCraneAddrecordMapper sbCraneAddrecordMapper;

	@Override
	public List<SbCraneAddrecord> selectCraneAddrecordList(Map<String, Object> map) {
		return sbCraneAddrecordMapper.selectCraneAddrecordList(map);
	}

	@Override
	public CraneKB selectCount(Map<String, Object> map) {
		return sbCraneAddrecordMapper.selectCount(map);
	}

	@Override
	public SbCraneAddrecord selectSbCraneAddRecord(String hxzId, String runtime) {
		return sbCraneAddrecordMapper.selectSbCraneAddRecord(hxzId, runtime);
	}

	@Override
	public CraneAddRecord selectCraneAddRecordName(String hxzId, String runtime,Integer projectId) {
		return sbCraneAddrecordMapper.selectCraneAddRecordName(hxzId, runtime,projectId);
	}

	@Override
	public List<SbCraneAddrecord> selectSbCraneAddRecordHistory(String deviceId, String dateTime,String endTime) {
		return sbCraneAddrecordMapper.selectSbCraneAddRecordHistory(deviceId, dateTime,endTime);
	}

	/**
	 * 查询塔式起重机实时数据信息
	 *
	 * @param id 塔式起重机实时数据ID
	 * @return 塔式起重机实时数据信息
	 */
	@Override
	public SbCraneAddrecord selectSbCraneAddrecordById(Long id)
	{
		return sbCraneAddrecordMapper.selectSbCraneAddrecordById(id);
	}

	/**
	 * 查询塔式起重机实时数据列表
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 塔式起重机实时数据集合
	 */
	@Override
	public List<SbCraneAddrecord> selectSbCraneAddrecordList(SbCraneAddrecord sbCraneAddrecord)
	{
		return sbCraneAddrecordMapper.selectSbCraneAddrecordList(sbCraneAddrecord);
	}
	@Override
	public List<SbCraneAddrecord> selectSbCraneAddrecordListTwo(SbCraneAddrecord sbCraneAddrecord)
	{
		return sbCraneAddrecordMapper.selectSbCraneAddrecordListTwo(sbCraneAddrecord);
	}
	@Override
	public List<SbCraneAddrecord> selectSbCraneAddrecordListThree(Map<String,Object> map)
	{
		return sbCraneAddrecordMapper.selectSbCraneAddrecordListThree(map);
	}

	/**
	 * 新增塔式起重机实时数据
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 结果
	 */
	@Override
	public int insertSbCraneAddrecord(SbCraneAddrecord sbCraneAddrecord)
	{
		return sbCraneAddrecordMapper.insertSbCraneAddrecord(sbCraneAddrecord);
	}

	/**
	 * 修改塔式起重机实时数据
	 *
	 * @param sbCraneAddrecord 塔式起重机实时数据信息
	 * @return 结果
	 */
	@Override
	public int updateSbCraneAddrecord(SbCraneAddrecord sbCraneAddrecord)
	{
		return sbCraneAddrecordMapper.updateSbCraneAddrecord(sbCraneAddrecord);
	}

	/**
	 * 删除塔式起重机实时数据对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteSbCraneAddrecordByIds(String ids)
	{
		return sbCraneAddrecordMapper.deleteSbCraneAddrecordByIds(Convert.toStrArray(ids));
	}
	public List<SbCraneAddrecord> selectCraneCount(Map<String,Object> map){
		return sbCraneAddrecordMapper.selectCraneCount(map);
	}
	public Integer selectCraneCountTwo(Map<String,Object> map){
		return sbCraneAddrecordMapper.selectCraneCountTwo(map);
	}
	public Integer selectCraneCountThree(Map<String,Object> map){
		return sbCraneAddrecordMapper.selectCraneCountThree(map);
	}

	/** 智慧工地1.0看板塔吊*/
	public SbCraneAddrecord kanban(String hxzid){
		return  sbCraneAddrecordMapper.kanban(hxzid);
	}
	public SbCraneAddrecord lists(String hxzid){
		return  sbCraneAddrecordMapper.lists(hxzid);
	}
	public List<SbCraneAddrecord> list(Integer pid){
		return  sbCraneAddrecordMapper.list(pid);
	}
	public List<SbCraneAddrecord> kanbans(Integer Pid){
		return sbCraneAddrecordMapper.kanbans(Pid);
	}
}
