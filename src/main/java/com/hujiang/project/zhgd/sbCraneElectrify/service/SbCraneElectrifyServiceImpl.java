package com.hujiang.project.zhgd.sbCraneElectrify.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneElectrify.mapper.SbCraneElectrifyMapper;
import com.hujiang.project.zhgd.sbCraneElectrify.domain.SbCraneElectrify;
import com.hujiang.project.zhgd.sbCraneElectrify.service.ISbCraneElectrifyService;
import com.hujiang.common.support.Convert;

/**
 * 塔机通电时间 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service@Transactional
public class SbCraneElectrifyServiceImpl implements ISbCraneElectrifyService 
{
	@Autowired
	private SbCraneElectrifyMapper sbCraneElectrifyMapper;

	/**
     * 查询塔机通电时间信息
     * 
     * @param id 塔机通电时间ID
     * @return 塔机通电时间信息
     */
    @Override
	public SbCraneElectrify selectSbCraneElectrifyById(Long id)
	{
	    return sbCraneElectrifyMapper.selectSbCraneElectrifyById(id);
	}
	
	/**
     * 查询塔机通电时间列表
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 塔机通电时间集合
     */
	@Override
	public List<SbCraneElectrify> selectSbCraneElectrifyList(SbCraneElectrify sbCraneElectrify)
	{
	    return sbCraneElectrifyMapper.selectSbCraneElectrifyList(sbCraneElectrify);
	}
	
    /**
     * 新增塔机通电时间
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 结果
     */
	@Override
	public int insertSbCraneElectrify(SbCraneElectrify sbCraneElectrify)
	{
	    return sbCraneElectrifyMapper.insertSbCraneElectrify(sbCraneElectrify);
	}
	
	/**
     * 修改塔机通电时间
     * 
     * @param sbCraneElectrify 塔机通电时间信息
     * @return 结果
     */
	@Override
	public int updateSbCraneElectrify(SbCraneElectrify sbCraneElectrify)
	{
	    return sbCraneElectrifyMapper.updateSbCraneElectrify(sbCraneElectrify);
	}

	/**
     * 删除塔机通电时间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneElectrifyByIds(String ids)
	{
		return sbCraneElectrifyMapper.deleteSbCraneElectrifyByIds(Convert.toStrArray(ids));
	}
	
}
