package com.hujiang.project.zhgd.hjghformwork.mapper;


import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkGroup;

import java.util.List;

/**
 * 高支模测点 数据层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface HighformworkGroupMapper 
{
	/**
     * 查询高支模测点信息
     * 
     * @param id 高支模测点ID
     * @return 高支模测点信息
     */
	public HighformworkGroup selectHighformworkGroupById(Integer id);
	
	/**
     * 查询高支模测点列表
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 高支模测点集合
     */
	public List<HighformworkGroup> selectHighformworkGroupList(HighformworkGroup highformworkGroup);
	
	/**
     * 新增高支模测点
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 结果
     */
	public int insertHighformworkGroup(HighformworkGroup highformworkGroup);
	
	/**
     * 修改高支模测点
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 结果
     */
	public int updateHighformworkGroup(HighformworkGroup highformworkGroup);
	
	/**
     * 删除高支模测点
     * 
     * @param id 高支模测点ID
     * @return 结果
     */
	public int deleteHighformworkGroupById(Integer id);
	
	/**
     * 批量删除高支模测点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighformworkGroupByIds(String[] ids);
	
}