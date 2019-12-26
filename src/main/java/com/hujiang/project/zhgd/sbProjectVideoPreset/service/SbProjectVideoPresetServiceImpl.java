package com.hujiang.project.zhgd.sbProjectVideoPreset.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.sbProjectVideoPreset.domain.SbProjectVideoPreset;
import com.hujiang.project.zhgd.sbProjectVideoPreset.mapper.SbProjectVideoPresetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预置点 服务层实现
 * 
 * @author hujiang
 * @date 2019-12-11
 */
@Service
public class SbProjectVideoPresetServiceImpl implements ISbProjectVideoPresetService 
{
	@Autowired
	private SbProjectVideoPresetMapper sbProjectVideoPresetMapper;

	/**
     * 查询预置点信息
     * 
     * @param id 预置点ID
     * @return 预置点信息
     */
    @Override
	public SbProjectVideoPreset selectSbProjectVideoPresetById(Integer id)
	{
	    return sbProjectVideoPresetMapper.selectSbProjectVideoPresetById(id);
	}
	
	/**
     * 查询预置点列表
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 预置点集合
     */
	@Override
	public List<SbProjectVideoPreset> selectSbProjectVideoPresetList(SbProjectVideoPreset sbProjectVideoPreset)
	{
	    return sbProjectVideoPresetMapper.selectSbProjectVideoPresetList(sbProjectVideoPreset);
	}
	
    /**
     * 新增预置点
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 结果
     */
	@Override
	public int insertSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset)
	{
	    return sbProjectVideoPresetMapper.insertSbProjectVideoPreset(sbProjectVideoPreset);
	}
	
	/**
     * 修改预置点
     * 
     * @param sbProjectVideoPreset 预置点信息
     * @return 结果
     */
	@Override
	public int updateSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset)
	{
	    return sbProjectVideoPresetMapper.updateSbProjectVideoPreset(sbProjectVideoPreset);
	}

	/**
     * 删除预置点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectVideoPresetByIds(String ids)
	{
		return sbProjectVideoPresetMapper.deleteSbProjectVideoPresetByIds(Convert.toStrArray(ids));
	}
	@Override
	public int deleteSbProjectVideoPreset(SbProjectVideoPreset sbProjectVideoPreset)
	{
		return sbProjectVideoPresetMapper.deleteSbProjectVideoPreset(sbProjectVideoPreset);
	}

}
