package com.hujiang.project.zhgd.sbUnloaderParameter.mapper;

import com.hujiang.project.zhgd.sbUnloaderParameter.domain.SbUnloaderParameter;
import java.util.List;	

/**
 * 卸料基础参数 数据层
 * 
 * @author hujiang
 * @date 2019-09-10
 */
public interface SbUnloaderParameterMapper 
{
	/**
     * 查询卸料基础参数信息
     * 
     * @param id 卸料基础参数ID
     * @return 卸料基础参数信息
     */
	public SbUnloaderParameter selectSbUnloaderParameterById(Integer id);
	
	/**
     * 查询卸料基础参数列表
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 卸料基础参数集合
     */
	public List<SbUnloaderParameter> selectSbUnloaderParameterList(SbUnloaderParameter sbUnloaderParameter);
	
	/**
     * 新增卸料基础参数
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 结果
     */
	public int insertSbUnloaderParameter(SbUnloaderParameter sbUnloaderParameter);
	
	/**
     * 修改卸料基础参数
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 结果
     */
	public int updateSbUnloaderParameter(SbUnloaderParameter sbUnloaderParameter);
	
	/**
     * 删除卸料基础参数
     * 
     * @param id 卸料基础参数ID
     * @return 结果
     */
	public int deleteSbUnloaderParameterById(Integer id);
	
	/**
     * 批量删除卸料基础参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderParameterByIds(String[] ids);
	
}