package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import java.util.List;	

/**
 * 深基坑结构物监测因素 数据层
 * 
 * @author hujiang
 * @date 2019-09-04
 */
public interface SbDeeppitDisplayMapper 
{
	/**
     * 查询深基坑结构物监测因素信息
     * 
     * @param id 深基坑结构物监测因素ID
     * @return 深基坑结构物监测因素信息
     */
	public SbDeeppitDisplay selectSbDeeppitDisplayById(Integer id);
	
	/**
     * 查询深基坑结构物监测因素列表
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 深基坑结构物监测因素集合
     */
	public List<SbDeeppitDisplay> selectSbDeeppitDisplayList(SbDeeppitDisplay sbDeeppitDisplay);
	
	/**
     * 新增深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 结果
     */
	public int insertSbDeeppitDisplay(SbDeeppitDisplay sbDeeppitDisplay);
	
	/**
     * 修改深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 结果
     */
	public int updateSbDeeppitDisplay(SbDeeppitDisplay sbDeeppitDisplay);
	
	/**
     * 删除深基坑结构物监测因素
     * 
     * @param id 深基坑结构物监测因素ID
     * @return 结果
     */
	public int deleteSbDeeppitDisplayById(Integer id);
	
	/**
     * 批量删除深基坑结构物监测因素
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitDisplayByIds(String[] ids);
	
}