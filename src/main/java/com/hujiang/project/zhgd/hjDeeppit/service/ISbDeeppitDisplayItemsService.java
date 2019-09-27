package com.hujiang.project.zhgd.hjDeeppit.service;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplayItems;
import java.util.List;

/**
 * 深基坑结构物监测因素 服务层
 * 
 * @author hujiang
 * @date 2019-09-04
 */
public interface ISbDeeppitDisplayItemsService 
{
	/**
     * 查询深基坑结构物监测因素信息
     * 
     * @param id 深基坑结构物监测因素ID
     * @return 深基坑结构物监测因素信息
     */
	public SbDeeppitDisplayItems selectSbDeeppitDisplayItemsById(Integer id);
	
	/**
     * 查询深基坑结构物监测因素列表
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 深基坑结构物监测因素集合
     */
	public List<SbDeeppitDisplayItems> selectSbDeeppitDisplayItemsList(SbDeeppitDisplayItems sbDeeppitDisplayItems);
	
	/**
     * 新增深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 结果
     */
	public int insertSbDeeppitDisplayItems(SbDeeppitDisplayItems sbDeeppitDisplayItems);
	
	/**
     * 修改深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 结果
     */
	public int updateSbDeeppitDisplayItems(SbDeeppitDisplayItems sbDeeppitDisplayItems);
		
	/**
     * 删除深基坑结构物监测因素信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitDisplayItemsByIds(String ids);
	
}
