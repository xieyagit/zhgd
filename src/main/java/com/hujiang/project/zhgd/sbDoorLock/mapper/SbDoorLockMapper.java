package com.hujiang.project.zhgd.sbDoorLock.mapper;

import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import java.util.List;	

/**
 * 门锁记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface SbDoorLockMapper 
{
	/**
     * 查询门锁记录信息
     * 
     * @param id 门锁记录ID
     * @return 门锁记录信息
     */
	public SbDoorLock selectSbDoorLockById(Integer id);

	/**
	 * 获取最新的一条门锁记录
	 * @param electricityBoxId
	 * @return
	 */
	public SbDoorLock selectSbDoorLockToOne(String electricityBoxId);

	/**
     * 查询门锁记录列表
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 门锁记录集合
     */
	public List<SbDoorLock> selectSbDoorLockList(SbDoorLock sbDoorLock);
	
	/**
     * 新增门锁记录
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 结果
     */
	public int insertSbDoorLock(SbDoorLock sbDoorLock);
	
	/**
     * 修改门锁记录
     * 
     * @param sbDoorLock 门锁记录信息
     * @return 结果
     */
	public int updateSbDoorLock(SbDoorLock sbDoorLock);
	
	/**
     * 删除门锁记录
     * 
     * @param id 门锁记录ID
     * @return 结果
     */
	public int deleteSbDoorLockById(Integer id);
	
	/**
     * 批量删除门锁记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDoorLockByIds(String[] ids);
	
}