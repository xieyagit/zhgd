package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitGroupMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitGroupService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑测点分组 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Service
public class SbDeeppitGroupServiceImpl implements ISbDeeppitGroupService 
{
	@Autowired
	private SbDeeppitGroupMapper sbDeeppitGroupMapper;

	/**
     * 查询深基坑测点分组信息
     * 
     * @param id 深基坑测点分组ID
     * @return 深基坑测点分组信息
     */
    @Override
	public SbDeeppitGroup selectSbDeeppitGroupById(Integer id)
	{
	    return sbDeeppitGroupMapper.selectSbDeeppitGroupById(id);
	}
	
	/**
     * 查询深基坑测点分组列表
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 深基坑测点分组集合
     */
	@Override
	public List<SbDeeppitGroup> selectSbDeeppitGroupList(SbDeeppitGroup sbDeeppitGroup)
	{
	    return sbDeeppitGroupMapper.selectSbDeeppitGroupList(sbDeeppitGroup);
	}
	
    /**
     * 新增深基坑测点分组
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitGroup(SbDeeppitGroup sbDeeppitGroup)
	{
	    return sbDeeppitGroupMapper.insertSbDeeppitGroup(sbDeeppitGroup);
	}
	
	/**
     * 修改深基坑测点分组
     * 
     * @param sbDeeppitGroup 深基坑测点分组信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitGroup(SbDeeppitGroup sbDeeppitGroup)
	{
	    return sbDeeppitGroupMapper.updateSbDeeppitGroup(sbDeeppitGroup);
	}

	/**
     * 删除深基坑测点分组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitGroupByIds(String ids)
	{
		return sbDeeppitGroupMapper.deleteSbDeeppitGroupByIds(Convert.toStrArray(ids));
	}
	
}
