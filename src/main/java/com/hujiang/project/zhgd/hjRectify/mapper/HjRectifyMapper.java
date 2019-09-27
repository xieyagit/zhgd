package com.hujiang.project.zhgd.hjRectify.mapper;

import com.hujiang.project.zhgd.hjRectify.domain.HjRectify;
import java.util.List;	

/**
 * 整改记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface HjRectifyMapper 
{
	/**
     * 查询整改记录信息
     * 
     * @param id 整改记录ID
     * @return 整改记录信息
     */
	public HjRectify selectHjRectifyById(Integer id);
	
	/**
     * 查询整改记录列表
     * 
     * @param hjRectify 整改记录信息
     * @return 整改记录集合
     */
	public List<HjRectify> selectHjRectifyList(HjRectify hjRectify);
	
	/**
     * 新增整改记录
     * 
     * @param hjRectify 整改记录信息
     * @return 结果
     */
	public int insertHjRectify(HjRectify hjRectify);
	
	/**
     * 修改整改记录
     * 
     * @param hjRectify 整改记录信息
     * @return 结果
     */
	public int updateHjRectify(HjRectify hjRectify);
	
	/**
     * 删除整改记录
     * 
     * @param id 整改记录ID
     * @return 结果
     */
	public int deleteHjRectifyById(Integer id);
	
	/**
     * 批量删除整改记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjRectifyByIds(String[] ids);
	
}