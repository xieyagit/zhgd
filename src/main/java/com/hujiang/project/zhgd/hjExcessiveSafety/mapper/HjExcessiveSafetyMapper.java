package com.hujiang.project.zhgd.hjExcessiveSafety.mapper;

import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import java.util.List;	

/**
 * 巡检短息记录 数据层
 * 
 * @author hujiang
 * @date 2019-07-29
 */
public interface HjExcessiveSafetyMapper 
{
	/**
     * 查询巡检短息记录信息
     * 
     * @param id 巡检短息记录ID
     * @return 巡检短息记录信息
     */
	public HjExcessiveSafety selectHjExcessiveSafetyById(Integer id);
	
	/**
     * 查询巡检短息记录列表
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 巡检短息记录集合
     */
	public List<HjExcessiveSafety> selectHjExcessiveSafetyList(HjExcessiveSafety hjExcessiveSafety);
	
	/**
     * 新增巡检短息记录
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 结果
     */
	public int insertHjExcessiveSafety(HjExcessiveSafety hjExcessiveSafety);
	
	/**
     * 修改巡检短息记录
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 结果
     */
	public int updateHjExcessiveSafety(HjExcessiveSafety hjExcessiveSafety);
	
	/**
     * 删除巡检短息记录
     * 
     * @param id 巡检短息记录ID
     * @return 结果
     */
	public int deleteHjExcessiveSafetyById(Integer id);
	
	/**
     * 批量删除巡检短息记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjExcessiveSafetyByIds(String[] ids);
	
}