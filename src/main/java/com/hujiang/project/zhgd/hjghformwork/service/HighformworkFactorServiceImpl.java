package com.hujiang.project.zhgd.hjghformwork.service;

import java.util.List;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkFactor;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkFactorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.common.support.Convert;

/**
 * 高支模传感器 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class HighformworkFactorServiceImpl implements IHighformworkFactorService 
{
	@Autowired
	private HighformworkFactorMapper highformworkFactorMapper;

	/**
     * 查询高支模传感器信息
     * 
     * @param id 高支模传感器ID
     * @return 高支模传感器信息
     */
    @Override
	public HighformworkFactor selectHighformworkFactorById(Integer id)
	{
	    return highformworkFactorMapper.selectHighformworkFactorById(id);
	}
	
	/**
     * 查询高支模传感器列表
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 高支模传感器集合
     */
	@Override
	public List<HighformworkFactor> selectHighformworkFactorList(HighformworkFactor highformworkFactor)
	{
	    return highformworkFactorMapper.selectHighformworkFactorList(highformworkFactor);
	}
	
    /**
     * 新增高支模传感器
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 结果
     */
	@Override
	public int insertHighformworkFactor(HighformworkFactor highformworkFactor)
	{
	    return highformworkFactorMapper.insertHighformworkFactor(highformworkFactor);
	}
	
	/**
     * 修改高支模传感器
     * 
     * @param highformworkFactor 高支模传感器信息
     * @return 结果
     */
	@Override
	public int updateHighformworkFactor(HighformworkFactor highformworkFactor)
	{
	    return highformworkFactorMapper.updateHighformworkFactor(highformworkFactor);
	}

	/**
     * 删除高支模传感器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHighformworkFactorByIds(String ids)
	{
		return highformworkFactorMapper.deleteHighformworkFactorByIds(Convert.toStrArray(ids));
	}
	
}
