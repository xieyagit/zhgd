package com.hujiang.project.zhgd.moredian.moredianPerson.mapper;

import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;

import java.util.List;

/**
 * 人员 数据层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface MoredianPersonMapper 
{
	/**
     * 查询人员信息
     * 
     * @param id 人员ID
     * @return 人员信息
     */
	public MoredianPerson selectMoredianPersonById(Integer id);
	
	/**
     * 查询人员列表
     * 
     * @param moredianPerson 人员信息
     * @return 人员集合
     */
	public List<MoredianPerson> selectMoredianPersonList(MoredianPerson moredianPerson);
	
	/**
     * 新增人员
     * 
     * @param moredianPerson 人员信息
     * @return 结果
     */
	public int insertMoredianPerson(MoredianPerson moredianPerson);
	
	/**
     * 修改人员
     * 
     * @param moredianPerson 人员信息
     * @return 结果
     */
	public int updateMoredianPerson(MoredianPerson moredianPerson);
	
	/**
     * 删除人员
     * 
     * @param id 人员ID
     * @return 结果
     */
	public int deleteMoredianPersonById(Integer id);
	
	/**
     * 批量删除人员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianPersonByIds(String[] ids);
	
}