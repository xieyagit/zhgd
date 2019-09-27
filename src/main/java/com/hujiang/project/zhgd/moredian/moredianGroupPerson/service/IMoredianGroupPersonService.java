package com.hujiang.project.zhgd.moredian.moredianGroupPerson.service;

import com.hujiang.project.zhgd.moredian.moredianGroupPerson.domain.MoredianGroupPerson;

import java.util.List;

/**
 * 群组人员 服务层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface IMoredianGroupPersonService 
{
	/**
     * 查询群组人员信息
     * 
     * @param id 群组人员ID
     * @return 群组人员信息
     */
	public MoredianGroupPerson selectMoredianGroupPersonById(Integer id);
	
	/**
     * 查询群组人员列表
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 群组人员集合
     */
	public List<MoredianGroupPerson> selectMoredianGroupPersonList(MoredianGroupPerson moredianGroupPerson);
	
	/**
     * 新增群组人员
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 结果
     */
	public int insertMoredianGroupPerson(MoredianGroupPerson moredianGroupPerson);
	
	/**
     * 修改群组人员
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 结果
     */
	public int updateMoredianGroupPerson(MoredianGroupPerson moredianGroupPerson);
		
	/**
     * 删除群组人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianGroupPersonByIds(String ids);

	public int deleteMoredianGroupPersonBymemberId(String ids);

}
