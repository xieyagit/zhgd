package com.hujiang.project.zhgd.hjghformwork.service;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkGroup;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.common.support.Convert;

/**
 * 高支模测点 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class HighformworkGroupServiceImpl implements IHighformworkGroupService 
{
	@Autowired
	private HighformworkGroupMapper highformworkGroupMapper;

	/**
     * 查询高支模测点信息
     * 
     * @param id 高支模测点ID
     * @return 高支模测点信息
     */
    @Override
	public HighformworkGroup selectHighformworkGroupById(Integer id)
	{
	    return highformworkGroupMapper.selectHighformworkGroupById(id);
	}
	
	/**
     * 查询高支模测点列表
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 高支模测点集合
     */
	@Override
	public List<HighformworkGroup> selectHighformworkGroupList(HighformworkGroup highformworkGroup)
	{
	    return highformworkGroupMapper.selectHighformworkGroupList(highformworkGroup);
	}
	
    /**
     * 新增高支模测点
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 结果
     */
	@Override
	public int insertHighformworkGroup(HighformworkGroup highformworkGroup)
	{
	    return highformworkGroupMapper.insertHighformworkGroup(highformworkGroup);
	}
	
	/**
     * 修改高支模测点
     * 
     * @param highformworkGroup 高支模测点信息
     * @return 结果
     */
	@Override
	public int updateHighformworkGroup(HighformworkGroup highformworkGroup)
	{
	    return highformworkGroupMapper.updateHighformworkGroup(highformworkGroup);
	}

	/**
     * 删除高支模测点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHighformworkGroupByIds(String ids)
	{
		return highformworkGroupMapper.deleteHighformworkGroupByIds(Convert.toStrArray(ids));
	}
	
}
