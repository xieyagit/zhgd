package com.hujiang.project.zhgd.sbEquipmentRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbEquipmentRecord.mapper.SbEquipmentRecordMapper;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import com.hujiang.common.support.Convert;

/**
 * 定位记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Service
public class SbEquipmentRecordServiceImpl implements ISbEquipmentRecordService 
{
	@Autowired
	private SbEquipmentRecordMapper sbEquipmentRecordMapper;

	/**
     * 查询定位记录信息
     * 
     * @param id 定位记录ID
     * @return 定位记录信息
     */
    @Override
	public SbEquipmentRecord selectSbEquipmentRecordById(Integer id)
	{
	    return sbEquipmentRecordMapper.selectSbEquipmentRecordById(id);
	}
	
	/**
     * 查询定位记录列表
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 定位记录集合
     */
	@Override
	public List<SbEquipmentRecord> selectSbEquipmentRecordList(SbEquipmentRecord sbEquipmentRecord)
	{
	    return sbEquipmentRecordMapper.selectSbEquipmentRecordList(sbEquipmentRecord);
	}
	
    /**
     * 新增定位记录
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 结果
     */
	@Override
	public int insertSbEquipmentRecord(SbEquipmentRecord sbEquipmentRecord)
	{
	    return sbEquipmentRecordMapper.insertSbEquipmentRecord(sbEquipmentRecord);
	}
	
	/**
     * 修改定位记录
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 结果
     */
	@Override
	public int updateSbEquipmentRecord(SbEquipmentRecord sbEquipmentRecord)
	{
	    return sbEquipmentRecordMapper.updateSbEquipmentRecord(sbEquipmentRecord);
	}

	/**
     * 删除定位记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbEquipmentRecordByIds(String ids)
	{
		return sbEquipmentRecordMapper.deleteSbEquipmentRecordByIds(Convert.toStrArray(ids));
	}
	
}
