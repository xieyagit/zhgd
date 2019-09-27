package com.hujiang.project.zhgd.sbCraneElectrify.mapper;

import com.hujiang.project.zhgd.sbCraneElectrify.domain.SbCraneElectrify;
import java.util.List;	

/**
 * 塔机通电时间 数据层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface SbCraneElectrifyMapper 
{
	/**
     * 查询塔机通电时间信息
     * 
     * @param id 塔机通电时间ID
     * @return 塔机通电时间信息
     */
	public SbCraneElectrify selectSbCraneElectrifyById(Long id);
	
	/**
     * 查询塔机通电时间列表
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 塔机通电时间集合
     */
	public List<SbCraneElectrify> selectSbCraneElectrifyList(SbCraneElectrify sbCraneElectrify);
	
	/**
     * 新增塔机通电时间
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 结果
     */
	public int insertSbCraneElectrify(SbCraneElectrify sbCraneElectrify);
	
	/**
     * 修改塔机通电时间
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 结果
     */
	public int updateSbCraneElectrify(SbCraneElectrify sbCraneElectrify);
	
	/**
     * 删除塔机通电时间
     * 
     * @param id 塔机通电时间ID
     * @return 结果
     */
	public int deleteSbCraneElectrifyById(Long id);
	
	/**
     * 批量删除塔机通电时间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneElectrifyByIds(String[] ids);
	
}