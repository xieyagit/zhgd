package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import java.util.List;	

/**
 * 深基坑测点 数据层
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public interface SbDeeppitFactorMapper 
{
	/**
     * 查询深基坑测点信息
     * 
     * @param id 深基坑测点ID
     * @return 深基坑测点信息
     */
	public SbDeeppitFactor selectSbDeeppitFactorById(Integer id);
	
	/**
     * 查询深基坑测点列表
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 深基坑测点集合
     */
	public List<SbDeeppitFactor> selectSbDeeppitFactorList(SbDeeppitFactor sbDeeppitFactor);
	
	/**
     * 新增深基坑测点
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 结果
     */
	public int insertSbDeeppitFactor(SbDeeppitFactor sbDeeppitFactor);
	
	/**
     * 修改深基坑测点
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 结果
     */
	public int updateSbDeeppitFactor(SbDeeppitFactor sbDeeppitFactor);
	
	/**
     * 删除深基坑测点
     * 
     * @param id 深基坑测点ID
     * @return 结果
     */
	public int deleteSbDeeppitFactorById(Integer id);
	
	/**
     * 批量删除深基坑测点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitFactorByIds(String[] ids);
	
}