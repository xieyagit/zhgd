package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitDisplayMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑结构物监测因素 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-04
 */
@Service
public class SbDeeppitDisplayServiceImpl implements ISbDeeppitDisplayService 
{
	@Autowired
	private SbDeeppitDisplayMapper sbDeeppitDisplayMapper;

	/**
     * 查询深基坑结构物监测因素信息
     * 
     * @param id 深基坑结构物监测因素ID
     * @return 深基坑结构物监测因素信息
     */
    @Override
	public SbDeeppitDisplay selectSbDeeppitDisplayById(Integer id)
	{
	    return sbDeeppitDisplayMapper.selectSbDeeppitDisplayById(id);
	}
	
	/**
     * 查询深基坑结构物监测因素列表
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 深基坑结构物监测因素集合
     */
	@Override
	public List<SbDeeppitDisplay> selectSbDeeppitDisplayList(SbDeeppitDisplay sbDeeppitDisplay)
	{
	    return sbDeeppitDisplayMapper.selectSbDeeppitDisplayList(sbDeeppitDisplay);
	}
	
    /**
     * 新增深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitDisplay(SbDeeppitDisplay sbDeeppitDisplay)
	{
	    return sbDeeppitDisplayMapper.insertSbDeeppitDisplay(sbDeeppitDisplay);
	}
	
	/**
     * 修改深基坑结构物监测因素
     * 
     * @param sbDeeppitDisplay 深基坑结构物监测因素信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitDisplay(SbDeeppitDisplay sbDeeppitDisplay)
	{
	    return sbDeeppitDisplayMapper.updateSbDeeppitDisplay(sbDeeppitDisplay);
	}

	/**
     * 删除深基坑结构物监测因素对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitDisplayByIds(String ids)
	{
		return sbDeeppitDisplayMapper.deleteSbDeeppitDisplayByIds(Convert.toStrArray(ids));
	}
	
}
