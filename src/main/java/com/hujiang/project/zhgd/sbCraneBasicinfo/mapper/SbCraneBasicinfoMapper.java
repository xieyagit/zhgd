package com.hujiang.project.zhgd.sbCraneBasicinfo.mapper;

import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import java.util.List;	

/**
 * 塔式起重机设备基本 数据层
 * 
 * @author hujiang
 * @date 2019-06-20
 */
public interface SbCraneBasicinfoMapper 
{
	/**
     * 查询塔式起重机设备基本信息
     * 
     * @param id 塔式起重机设备基本ID
     * @return 塔式起重机设备基本信息
     */
	public SbCraneBasicinfo selectSbCraneBasicinfoById(Long id);
	
	/**
     * 查询塔式起重机设备基本列表
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 塔式起重机设备基本集合
     */
	public List<SbCraneBasicinfo> selectSbCraneBasicinfoList(SbCraneBasicinfo sbCraneBasicinfo);
	
	/**
     * 新增塔式起重机设备基本
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 结果
     */
	public int insertSbCraneBasicinfo(SbCraneBasicinfo sbCraneBasicinfo);
	
	/**
     * 修改塔式起重机设备基本
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 结果
     */
	public int updateSbCraneBasicinfo(SbCraneBasicinfo sbCraneBasicinfo);
	
	/**
     * 删除塔式起重机设备基本
     * 
     * @param id 塔式起重机设备基本ID
     * @return 结果
     */
	public int deleteSbCraneBasicinfoById(Long id);
	
	/**
     * 批量删除塔式起重机设备基本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneBasicinfoByIds(String[] ids);
	
}