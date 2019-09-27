package com.hujiang.project.zhgd.sbCraneAddparams.mapper;

import com.hujiang.project.zhgd.sbCraneAddparams.domain.OptionsCrane;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 塔式起重机参数 数据层
 * 
 * @author hujiang
 * @date 2019-06-20
 */
public interface SbCraneAddparamsMapper 
{
	public List<OptionsCrane> getOptionsCraneList(@Param("projectId")Integer projectId);

	public int updateCrane(OptionsCrane optionsCrane);

	public int deleteCrane(@Param("id")Integer id);

	public int insertCrane(OptionsCrane optionsCrane);
	/**
     * 查询塔式起重机参数信息
     * 
     * @param id 塔式起重机参数ID
     * @return 塔式起重机参数信息
     */
	public SbCraneAddparams selectSbCraneAddparamsById(Long id);
	
	/**
     * 查询塔式起重机参数列表
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 塔式起重机参数集合
     */
	public List<SbCraneAddparams> selectSbCraneAddparamsList(SbCraneAddparams sbCraneAddparams);
	
	/**
     * 新增塔式起重机参数
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 结果
     */
	public int insertSbCraneAddparams(SbCraneAddparams sbCraneAddparams);
	
	/**
     * 修改塔式起重机参数
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 结果
     */
	public int updateSbCraneAddparams(SbCraneAddparams sbCraneAddparams);
	
	/**
     * 删除塔式起重机参数
     *
     * @param id 塔式起重机参数ID
     * @return 结果
     */
	public int deleteSbCraneAddparamsById(Integer id);
	
	/**
     * 批量删除塔式起重机参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneAddparamsByIds(String[] ids);
	
}