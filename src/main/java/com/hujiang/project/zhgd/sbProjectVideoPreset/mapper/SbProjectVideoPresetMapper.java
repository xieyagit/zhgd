package com.hujiang.project.zhgd.sbProjectVideoPreset.mapper;

import com.hujiang.project.zhgd.sbProjectVideoPreset.domain.SbProjectVideoPreset;

import java.util.List;

/**
 * 预置点 数据层
 * 
 * @author hujiang
 * @date 2019-12-11
 */
public interface SbProjectVideoPresetMapper 
{
	/**
     * 查询预置点信息
     * 
     * @param id 预置点ID
     * @return 预置点信息
     */
	public SbProjectVideoPreset selectSbProjectVideoPresetById(Integer id);
	
	/**
     * 查询预置点列表
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 预置点集合
     */
	public List<SbProjectVideoPreset> selectSbProjectVideoPresetList(SbProjectVideoPreset sbProjectVideoPreset);
	
	/**
     * 新增预置点
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 结果
     */
	public int insertSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset);
	
	/**
     * 修改预置点
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 结果
     */
	public int updateSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset);
	
	/**
     * 删除预置点
     * 
     * @param id 预置点ID
     * @return 结果
     */
	public int deleteSbProjectVideoPresetById(Integer id);
	
	/**
     * 批量删除预置点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectVideoPresetByIds(String[] ids);
	public int deleteSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset);

}