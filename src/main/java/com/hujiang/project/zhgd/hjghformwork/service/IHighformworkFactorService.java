package com.hujiang.project.zhgd.hjghformwork.service;


import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkFactor;

import java.util.List;

/**
 * 高支模传感器 服务层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface IHighformworkFactorService 
{
	/**
     * 查询高支模传感器信息
     * 
     * @param id 高支模传感器ID
     * @return 高支模传感器信息
     */
	public HighformworkFactor selectHighformworkFactorById(Integer id);
	
	/**
     * 查询高支模传感器列表
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 高支模传感器集合
     */
	public List<HighformworkFactor> selectHighformworkFactorList(HighformworkFactor highformworkFactor);
	
	/**
     * 新增高支模传感器
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 结果
     */
	public int insertHighformworkFactor(HighformworkFactor highformworkFactor);
	
	/**
     * 修改高支模传感器
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 结果
     */
	public int updateHighformworkFactor(HighformworkFactor highformworkFactor);
		
	/**
     * 删除高支模传感器信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighformworkFactorByIds(String ids);
	
}
