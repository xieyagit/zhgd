package com.hujiang.project.zhgd.hjDeeppit.service;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbStationsListData;

import java.util.List;

/**
 * 深基坑结构物-项目 服务层
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public interface ISbProjectDeeppitStructuresService 
{
	public List<SbStationsListData> selectSbStationsList();
	/**
     * 查询深基坑结构物-项目信息
     * 
     * @param id 深基坑结构物-项目ID
     * @return 深基坑结构物-项目信息
     */
	public SbProjectDeeppitStructures selectSbProjectDeeppitStructuresById(Integer id);
	
	/**
     * 查询深基坑结构物-项目列表
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 深基坑结构物-项目集合
     */
	public List<SbProjectDeeppitStructures> selectSbProjectDeeppitStructuresList(SbProjectDeeppitStructures sbProjectDeeppitStructures);

	/**
	 * 查重
	 * @param sbProjectDeeppitStructures
	 * @return
	 */
	public List<SbProjectDeeppitStructures> selectSbProjectDeeppitStructuresListS(SbProjectDeeppitStructures sbProjectDeeppitStructures);
	
	/**
     * 新增深基坑结构物-项目
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 结果
     */
	public int insertSbProjectDeeppitStructures(SbProjectDeeppitStructures sbProjectDeeppitStructures);
	
	/**
     * 修改深基坑结构物-项目
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 结果
     */
	public int updateSbProjectDeeppitStructures(SbProjectDeeppitStructures sbProjectDeeppitStructures);
		
	/**
     * 删除深基坑结构物-项目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectDeeppitStructuresByIds(String ids);
	
}
