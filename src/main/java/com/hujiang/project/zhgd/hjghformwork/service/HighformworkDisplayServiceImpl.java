package com.hujiang.project.zhgd.hjghformwork.service;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkDisplay;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkDisplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.common.support.Convert;

/**
 * 高支模监测因素 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class HighformworkDisplayServiceImpl implements IHighformworkDisplayService 
{
	@Autowired
	private HighformworkDisplayMapper highformworkDisplayMapper;

	/**
     * 查询高支模监测因素信息
     * 
     * @param id 高支模监测因素ID
     * @return 高支模监测因素信息
     */
    @Override
	public HighformworkDisplay selectHighformworkDisplayById(Integer id)
	{
	    return highformworkDisplayMapper.selectHighformworkDisplayById(id);
	}
	
	/**
     * 查询高支模监测因素列表
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 高支模监测因素集合
     */
	@Override
	public List<HighformworkDisplay> selectHighformworkDisplayList(HighformworkDisplay highformworkDisplay)
	{
	    return highformworkDisplayMapper.selectHighformworkDisplayList(highformworkDisplay);
	}
	
    /**
     * 新增高支模监测因素
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 结果
     */
	@Override
	public int insertHighformworkDisplay(HighformworkDisplay highformworkDisplay)
	{
	    return highformworkDisplayMapper.insertHighformworkDisplay(highformworkDisplay);
	}
	
	/**
     * 修改高支模监测因素
     * 
     * @param highformworkDisplay 高支模监测因素信息
     * @return 结果
     */
	@Override
	public int updateHighformworkDisplay(HighformworkDisplay highformworkDisplay)
	{
	    return highformworkDisplayMapper.updateHighformworkDisplay(highformworkDisplay);
	}

	/**
     * 删除高支模监测因素对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHighformworkDisplayByIds(String ids)
	{
		return highformworkDisplayMapper.deleteHighformworkDisplayByIds(Convert.toStrArray(ids));
	}
	
}
