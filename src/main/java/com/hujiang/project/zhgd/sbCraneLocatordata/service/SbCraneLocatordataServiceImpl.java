package com.hujiang.project.zhgd.sbCraneLocatordata.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneLocatordata.mapper.SbCraneLocatordataMapper;
import com.hujiang.project.zhgd.sbCraneLocatordata.domain.SbCraneLocatordata;
import com.hujiang.project.zhgd.sbCraneLocatordata.service.ISbCraneLocatordataService;
import com.hujiang.common.support.Convert;

/**
 * 塔吊GPS定位数据
 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbCraneLocatordataServiceImpl implements ISbCraneLocatordataService 
{
	@Autowired
	private SbCraneLocatordataMapper sbCraneLocatordataMapper;

	/**
     * 查询塔吊GPS定位数据
信息
     * 
     * @param id 塔吊GPS定位数据
ID
     * @return 塔吊GPS定位数据
信息
     */
    @Override
	public SbCraneLocatordata selectSbCraneLocatordataById(Integer id)
	{
	    return sbCraneLocatordataMapper.selectSbCraneLocatordataById(id);
	}
	
	/**
     * 查询塔吊GPS定位数据
列表
     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 塔吊GPS定位数据
集合
     */
	@Override
	public List<SbCraneLocatordata> selectSbCraneLocatordataList(SbCraneLocatordata sbCraneLocatordata)
	{
	    return sbCraneLocatordataMapper.selectSbCraneLocatordataList(sbCraneLocatordata);
	}
	
    /**
     * 新增塔吊GPS定位数据

     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int insertSbCraneLocatordata(SbCraneLocatordata sbCraneLocatordata)
	{
	    return sbCraneLocatordataMapper.insertSbCraneLocatordata(sbCraneLocatordata);
	}
	
	/**
     * 修改塔吊GPS定位数据

     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int updateSbCraneLocatordata(SbCraneLocatordata sbCraneLocatordata)
	{
	    return sbCraneLocatordataMapper.updateSbCraneLocatordata(sbCraneLocatordata);
	}

	/**
     * 删除塔吊GPS定位数据
对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneLocatordataByIds(String ids)
	{
		return sbCraneLocatordataMapper.deleteSbCraneLocatordataByIds(Convert.toStrArray(ids));
	}
	
}
