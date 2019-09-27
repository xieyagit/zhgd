package com.hujiang.project.zhgd.hjDeeppit.service;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import java.util.List;

/**
 * 深基坑测点分组 服务层
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public interface ISbDeeppitGroupService 
{
	/**
     * 查询深基坑测点分组信息
     * 
     * @param id 深基坑测点分组ID
     * @return 深基坑测点分组信息
     */
	public SbDeeppitGroup selectSbDeeppitGroupById(Integer id);
	
	/**
     * 查询深基坑测点分组列表
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 深基坑测点分组集合
     */
	public List<SbDeeppitGroup> selectSbDeeppitGroupList(SbDeeppitGroup sbDeeppitGroup);
	
	/**
     * 新增深基坑测点分组
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 结果
     */
	public int insertSbDeeppitGroup(SbDeeppitGroup sbDeeppitGroup);
	
	/**
     * 修改深基坑测点分组
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 结果
     */
	public int updateSbDeeppitGroup(SbDeeppitGroup sbDeeppitGroup);
		
	/**
     * 删除深基坑测点分组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitGroupByIds(String ids);
	
}
