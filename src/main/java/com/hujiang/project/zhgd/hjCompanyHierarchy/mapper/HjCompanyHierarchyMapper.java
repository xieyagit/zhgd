package com.hujiang.project.zhgd.hjCompanyHierarchy.mapper;

import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import java.util.List;

/**
 * 公司层级 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjCompanyHierarchyMapper 
{
	/**
     * 查询公司层级信息
     * 
     * @param id 公司层级ID
     * @return 公司层级信息
     */
	public HjCompanyHierarchy selectHjCompanyHierarchyById(Integer id);
	
	/**
     * 查询公司层级列表
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 公司层级集合
     */
	public List<HjCompanyHierarchy> selectHjCompanyHierarchyList(HjCompanyHierarchy hjCompanyHierarchy);

	/**
     * 新增公司层级
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 结果
     */
	public int insertHjCompanyHierarchy(HjCompanyHierarchy hjCompanyHierarchy);
	
	/**
     * 修改公司层级
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 结果
     */
	public int updateHjCompanyHierarchy(HjCompanyHierarchy hjCompanyHierarchy);
	
	/**
     * 删除公司层级
     * 
     * @param id 公司层级ID
     * @return 结果
     */
	public int deleteHjCompanyHierarchyById(Integer id);
	
	/**
     * 批量删除公司层级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjCompanyHierarchyByIds(String[] ids);
	
}