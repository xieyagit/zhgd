package com.hujiang.project.zhgd.sbCraneWarning.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneWarning.mapper.SbCraneWarningMapper;
import com.hujiang.project.zhgd.sbCraneWarning.domain.SbCraneWarning;
import com.hujiang.project.zhgd.sbCraneWarning.service.ISbCraneWarningService;
import com.hujiang.common.support.Convert;

/**
 * 塔机预警数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-21
 */
@Service@Transactional
public class SbCraneWarningServiceImpl implements ISbCraneWarningService 
{
	@Autowired
	private SbCraneWarningMapper sbCraneWarningMapper;

	/**
     * 查询塔机预警数据信息
     * 
     * @param id 塔机预警数据ID
     * @return 塔机预警数据信息
     */
    @Override
	public SbCraneWarning selectSbCraneWarningById(Long id)
	{
	    return sbCraneWarningMapper.selectSbCraneWarningById(id);
	}
	
	/**
     * 查询塔机预警数据列表
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 塔机预警数据集合
     */
	@Override
	public List<SbCraneWarning> selectSbCraneWarningList(SbCraneWarning sbCraneWarning)
	{
	    return sbCraneWarningMapper.selectSbCraneWarningList(sbCraneWarning);
	}
	
    /**
     * 新增塔机预警数据
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 结果
     */
	@Override
	public int insertSbCraneWarning(SbCraneWarning sbCraneWarning)
	{
	    return sbCraneWarningMapper.insertSbCraneWarning(sbCraneWarning);
	}
	
	/**
     * 修改塔机预警数据
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 结果
     */
	@Override
	public int updateSbCraneWarning(SbCraneWarning sbCraneWarning)
	{
	    return sbCraneWarningMapper.updateSbCraneWarning(sbCraneWarning);
	}

	/**
     * 删除塔机预警数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneWarningByIds(String ids)
	{
		return sbCraneWarningMapper.deleteSbCraneWarningByIds(Convert.toStrArray(ids));
	}
	public Integer selectCraneCountOne(Map<String,Object> map){
		return sbCraneWarningMapper.selectCraneCountOne(map);
	}
	public Integer selectCraneCountTwo(Map<String,Object> map){
		return sbCraneWarningMapper.selectCraneCountTwo(map);
	}
	public Integer selectCraneCountThree(Map<String,Object> map){
		return sbCraneWarningMapper.selectCraneCountThree(map);
	}
}
