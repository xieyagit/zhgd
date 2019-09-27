package com.hujiang.project.zhgd.hjghformwork.mapper;


import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkDisplay;

import java.util.List;

/**
 * 高支模监测因素 数据层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface HighformworkDisplayMapper 
{
	/**
     * 查询高支模监测因素信息
     * 
     * @param id 高支模监测因素ID
     * @return 高支模监测因素信息
     */
	public HighformworkDisplay selectHighformworkDisplayById(Integer id);
	
	/**
     * 查询高支模监测因素列表
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 高支模监测因素集合
     */
	public List<HighformworkDisplay> selectHighformworkDisplayList(HighformworkDisplay highformworkDisplay);
	
	/**
     * 新增高支模监测因素
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 结果
     */
	public int insertHighformworkDisplay(HighformworkDisplay highformworkDisplay);
	
	/**
     * 修改高支模监测因素
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 结果
     */
	public int updateHighformworkDisplay(HighformworkDisplay highformworkDisplay);
	
	/**
     * 删除高支模监测因素
     * 
     * @param id 高支模监测因素ID
     * @return 结果
     */
	public int deleteHighformworkDisplayById(Integer id);
	
	/**
     * 批量删除高支模监测因素
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighformworkDisplayByIds(String[] ids);
	
}