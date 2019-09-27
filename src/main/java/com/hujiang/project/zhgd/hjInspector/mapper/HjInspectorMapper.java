package com.hujiang.project.zhgd.hjInspector.mapper;

import com.hujiang.project.zhgd.hjInspector.domain.HjInspector;
import java.util.List;	

/**
 * 检查记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface HjInspectorMapper 
{
	/**
     * 查询检查记录信息
     * 
     * @param id 检查记录ID
     * @return 检查记录信息
     */
	public HjInspector selectHjInspectorById(Integer id);
	
	/**
     * 查询检查记录列表
     * 
     * @param hjInspector 检查记录信息
     * @return 检查记录集合
     */
	public List<HjInspector> selectHjInspectorList(HjInspector hjInspector);
	
	/**
     * 新增检查记录
     * 
     * @param hjInspector 检查记录信息
     * @return 结果
     */
	public int insertHjInspector(HjInspector hjInspector);
	
	/**
     * 修改检查记录
     * 
     * @param hjInspector 检查记录信息
     * @return 结果
     */
	public int updateHjInspector(HjInspector hjInspector);
	
	/**
     * 删除检查记录
     * 
     * @param id 检查记录ID
     * @return 结果
     */
	public int deleteHjInspectorById(Integer id);
	
	/**
     * 批量删除检查记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjInspectorByIds(String[] ids);
	
}