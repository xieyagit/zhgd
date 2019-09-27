package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitDisplayItemsMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplayItems;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayItemsService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑结构物监测因素 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-04
 */
@Service
public class SbDeeppitDisplayItemsServiceImpl implements ISbDeeppitDisplayItemsService 
{
	@Autowired
	private SbDeeppitDisplayItemsMapper sbDeeppitDisplayItemsMapper;

	/**
     * 查询深基坑结构物监测因素信息
     * 
     * @param id 深基坑结构物监测因素ID
     * @return 深基坑结构物监测因素信息
     */
    @Override
	public SbDeeppitDisplayItems selectSbDeeppitDisplayItemsById(Integer id)
	{
	    return sbDeeppitDisplayItemsMapper.selectSbDeeppitDisplayItemsById(id);
	}
	
	/**
     * 查询深基坑结构物监测因素列表
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 深基坑结构物监测因素集合
     */
	@Override
	public List<SbDeeppitDisplayItems> selectSbDeeppitDisplayItemsList(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{
	    return sbDeeppitDisplayItemsMapper.selectSbDeeppitDisplayItemsList(sbDeeppitDisplayItems);
	}
	
    /**
     * 新增深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitDisplayItems(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{
	    return sbDeeppitDisplayItemsMapper.insertSbDeeppitDisplayItems(sbDeeppitDisplayItems);
	}
	
	/**
     * 修改深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplayItems 深基坑结构物监测因素信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitDisplayItems(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{
	    return sbDeeppitDisplayItemsMapper.updateSbDeeppitDisplayItems(sbDeeppitDisplayItems);
	}

	/**
     * 删除深基坑结构物监测因素对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitDisplayItemsByIds(String ids)
	{
		return sbDeeppitDisplayItemsMapper.deleteSbDeeppitDisplayItemsByIds(Convert.toStrArray(ids));
	}
	
}
