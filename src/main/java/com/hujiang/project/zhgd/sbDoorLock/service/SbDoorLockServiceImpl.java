package com.hujiang.project.zhgd.sbDoorLock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbDoorLock.mapper.SbDoorLockMapper;
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.common.support.Convert;

/**
 * 门锁记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service
public class SbDoorLockServiceImpl implements ISbDoorLockService 
{
	@Autowired
	private SbDoorLockMapper sbDoorLockMapper;

	/**
	 * 获取最新的一条门锁记录
	 * @param electricityBoxId
	 * @return
	 */
	public SbDoorLock selectSbDoorLockToOne(String electricityBoxId){
		return sbDoorLockMapper.selectSbDoorLockToOne(electricityBoxId);
	}
	/**
     * 查询门锁记录信息
     * 
     * @param id 门锁记录ID
     * @return 门锁记录信息
     */
    @Override
	public SbDoorLock selectSbDoorLockById(Integer id)
	{
	    return sbDoorLockMapper.selectSbDoorLockById(id);
	}
	
	/**
     * 查询门锁记录列表
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 门锁记录集合
     */
	@Override
	public List<SbDoorLock> selectSbDoorLockList(SbDoorLock sbDoorLock)
	{
	    return sbDoorLockMapper.selectSbDoorLockList(sbDoorLock);
	}
	
    /**
     * 新增门锁记录
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 结果
     */
	@Override
	public int insertSbDoorLock(SbDoorLock sbDoorLock)
	{
	    return sbDoorLockMapper.insertSbDoorLock(sbDoorLock);
	}
	
	/**
     * 修改门锁记录
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 结果
     */
	@Override
	public int updateSbDoorLock(SbDoorLock sbDoorLock)
	{
	    return sbDoorLockMapper.updateSbDoorLock(sbDoorLock);
	}

	/**
     * 删除门锁记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDoorLockByIds(String ids)
	{
		return sbDoorLockMapper.deleteSbDoorLockByIds(Convert.toStrArray(ids));
	}
	
}
