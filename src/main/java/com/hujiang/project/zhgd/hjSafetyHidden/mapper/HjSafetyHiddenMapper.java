package com.hujiang.project.zhgd.hjSafetyHidden.mapper;

import com.hujiang.project.zhgd.hjSafetyHidden.domain.HjSafetyHidden;
import java.util.List;	

/**
 * 隐患类型 数据层
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public interface HjSafetyHiddenMapper 
{
	/**
     * 查询隐患类型信息
     * 
     * @param id 隐患类型ID
     * @return 隐患类型信息
     */
	public HjSafetyHidden selectHjSafetyHiddenById(Integer id);
	
	/**
     * 查询隐患类型列表
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 隐患类型集合
     */
	public List<HjSafetyHidden> selectHjSafetyHiddenList(HjSafetyHidden hjSafetyHidden);
	
	/**
     * 新增隐患类型
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 结果
     */
	public int insertHjSafetyHidden(HjSafetyHidden hjSafetyHidden);
	
	/**
     * 修改隐患类型
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 结果
     */
	public int updateHjSafetyHidden(HjSafetyHidden hjSafetyHidden);
	
	/**
     * 删除隐患类型
     * 
     * @param id 隐患类型ID
     * @return 结果
     */
	public int deleteHjSafetyHiddenById(Integer id);
	
	/**
     * 批量删除隐患类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyHiddenByIds(String[] ids);
	
}