package com.hujiang.project.zhgd.hjWorkerRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjWorkerRecord.mapper.HjWorkerRecordMapper;
import com.hujiang.project.zhgd.hjWorkerRecord.domain.HjWorkerRecord;
import com.hujiang.project.zhgd.hjWorkerRecord.service.IHjWorkerRecordService;
import com.hujiang.common.support.Convert;

/**
 * 工人从业记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-17
 */
@Service
@Transactional
public class HjWorkerRecordServiceImpl implements IHjWorkerRecordService 
{
	@Autowired
	private HjWorkerRecordMapper hjWorkerRecordMapper;

	/**
     * 查询工人从业记录信息
     * 
     * @param id 工人从业记录ID
     * @return 工人从业记录信息
     */
    @Override
	public HjWorkerRecord selectHjWorkerRecordById(Integer id)
	{
	    return hjWorkerRecordMapper.selectHjWorkerRecordById(id);
	}
	
	/**
     * 查询工人从业记录列表
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 工人从业记录集合
     */
	@Override
	public List<HjWorkerRecord> selectHjWorkerRecordList(HjWorkerRecord hjWorkerRecord)
	{
	    return hjWorkerRecordMapper.selectHjWorkerRecordList(hjWorkerRecord);
	}
	
    /**
     * 新增工人从业记录
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 结果
     */
	@Override
	public int insertHjWorkerRecord(HjWorkerRecord hjWorkerRecord)
	{
	    return hjWorkerRecordMapper.insertHjWorkerRecord(hjWorkerRecord);
	}
	
	/**
     * 修改工人从业记录
     * 
     * @param hjWorkerRecord 工人从业记录信息
     * @return 结果
     */
	@Override
	public int updateHjWorkerRecord(HjWorkerRecord hjWorkerRecord)
	{
	    return hjWorkerRecordMapper.updateHjWorkerRecord(hjWorkerRecord);
	}

	/**
     * 删除工人从业记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjWorkerRecordByIds(String ids)
	{
		return hjWorkerRecordMapper.deleteHjWorkerRecordByIds(Convert.toStrArray(ids));
	}
	
}
