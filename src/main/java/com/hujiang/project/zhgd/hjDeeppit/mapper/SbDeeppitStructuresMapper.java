package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructures;
import java.util.List;	

/**
 * 深基坑结构物 数据层
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public interface SbDeeppitStructuresMapper 
{
	/**
     * 查询深基坑结构物信息
     * 
     * @param masterKey 深基坑结构物ID
     * @return 深基坑结构物信息
     */
	public SbDeeppitStructures selectSbDeeppitStructuresByMasterKey(String masterKey);
	
	/**
     * 查询深基坑结构物列表
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 深基坑结构物集合
     */
	public List<SbDeeppitStructures> selectSbDeeppitStructuresList(SbDeeppitStructures sbDeeppitStructures);
	
	/**
     * 新增深基坑结构物
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 结果
     */
	public int insertSbDeeppitStructures(SbDeeppitStructures sbDeeppitStructures);
	
	/**
     * 修改深基坑结构物
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 结果
     */
	public int updateSbDeeppitStructures(SbDeeppitStructures sbDeeppitStructures);

	/**
	 * 修改深基坑结构物
	 *
	 * @param sbDeeppitStructures 深基坑结构物信息
	 * @return 结果
	 */
	public int updateSbDeeppitStructuresO(SbDeeppitStructures sbDeeppitStructures);
	
	/**
     * 删除深基坑结构物
     * 
     * @param id 深基坑结构物ID
     * @return 结果
     */
	public int deleteSbDeeppitStructuresById(Integer id);
	
	/**
     * 批量删除深基坑结构物
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitStructuresByIds(String[] ids);
	
}