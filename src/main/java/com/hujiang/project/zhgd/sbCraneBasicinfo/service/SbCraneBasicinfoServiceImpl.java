package com.hujiang.project.zhgd.sbCraneBasicinfo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneBasicinfo.mapper.SbCraneBasicinfoMapper;
import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import com.hujiang.project.zhgd.sbCraneBasicinfo.service.ISbCraneBasicinfoService;
import com.hujiang.common.support.Convert;

/**
 * 塔式起重机设备基本 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-20
 */
@Service@Transactional
public class SbCraneBasicinfoServiceImpl implements ISbCraneBasicinfoService 
{
	@Autowired
	private SbCraneBasicinfoMapper sbCraneBasicinfoMapper;

	/**
     * 查询塔式起重机设备基本信息
     * 
     * @param id 塔式起重机设备基本ID
     * @return 塔式起重机设备基本信息
     */
    @Override
	public SbCraneBasicinfo selectSbCraneBasicinfoById(Long id)
	{
	    return sbCraneBasicinfoMapper.selectSbCraneBasicinfoById(id);
	}
	
	/**
     * 查询塔式起重机设备基本列表
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 塔式起重机设备基本集合
     */
	@Override
	public List<SbCraneBasicinfo> selectSbCraneBasicinfoList(SbCraneBasicinfo sbCraneBasicinfo)
	{
	    return sbCraneBasicinfoMapper.selectSbCraneBasicinfoList(sbCraneBasicinfo);
	}
	
    /**
     * 新增塔式起重机设备基本
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 结果
     */
	@Override
	public int insertSbCraneBasicinfo(SbCraneBasicinfo sbCraneBasicinfo)
	{
	    return sbCraneBasicinfoMapper.insertSbCraneBasicinfo(sbCraneBasicinfo);
	}
	
	/**
     * 修改塔式起重机设备基本
     * 
     * @param sbCraneBasicinfo 塔式起重机设备基本信息
     * @return 结果
     */
	@Override
	public int updateSbCraneBasicinfo(SbCraneBasicinfo sbCraneBasicinfo)
	{
	    return sbCraneBasicinfoMapper.updateSbCraneBasicinfo(sbCraneBasicinfo);
	}

	/**
     * 删除塔式起重机设备基本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneBasicinfoByIds(String ids)
	{
		return sbCraneBasicinfoMapper.deleteSbCraneBasicinfoByIds(Convert.toStrArray(ids));
	}
	
}
