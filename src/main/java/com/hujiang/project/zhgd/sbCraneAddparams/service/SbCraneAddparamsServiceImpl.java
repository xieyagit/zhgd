package com.hujiang.project.zhgd.sbCraneAddparams.service;

import java.util.List;

import com.hujiang.project.zhgd.sbCraneAddparams.domain.OptionsCrane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneAddparams.mapper.SbCraneAddparamsMapper;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import com.hujiang.project.zhgd.sbCraneAddparams.service.ISbCraneAddparamsService;
import com.hujiang.common.support.Convert;

/**
 * 塔式起重机参数 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-20
 */
@Service@Transactional
public class SbCraneAddparamsServiceImpl implements ISbCraneAddparamsService 
{
	@Autowired
	private SbCraneAddparamsMapper sbCraneAddparamsMapper;

	@Override
	public List<OptionsCrane> getOptionsCraneList(Integer projectId) {
		return sbCraneAddparamsMapper.getOptionsCraneList(projectId);
	}

	@Override
	public int updateCrane(OptionsCrane optionsCrane) {
		return sbCraneAddparamsMapper.updateCrane(optionsCrane);
	}

	@Override
	public int deleteCrane(Integer id) {
		return sbCraneAddparamsMapper.deleteCrane(id);
	}

	@Override
	public int insertCrane(OptionsCrane optionsCrane) {
		return sbCraneAddparamsMapper.insertCrane(optionsCrane);
	}

	/**
     * 查询塔式起重机参数信息
     * 
     * @param id 塔式起重机参数ID
     * @return 塔式起重机参数信息
     */
    @Override
	public SbCraneAddparams selectSbCraneAddparamsById(Long id)
	{
	    return sbCraneAddparamsMapper.selectSbCraneAddparamsById(id);
	}
	
	/**
     * 查询塔式起重机参数列表
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 塔式起重机参数集合
     */
	@Override
	public List<SbCraneAddparams> selectSbCraneAddparamsList(SbCraneAddparams sbCraneAddparams)
	{
	    return sbCraneAddparamsMapper.selectSbCraneAddparamsList(sbCraneAddparams);
	}
	
    /**
     * 新增塔式起重机参数
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 结果
     */
	@Override
	public int insertSbCraneAddparams(SbCraneAddparams sbCraneAddparams)
	{
	    return sbCraneAddparamsMapper.insertSbCraneAddparams(sbCraneAddparams);
	}
	
	/**
     * 修改塔式起重机参数
     * 
     * @param sbCraneAddparams 塔式起重机参数信息
     * @return 结果
     */
	@Override
	public int updateSbCraneAddparams(SbCraneAddparams sbCraneAddparams)
	{
	    return sbCraneAddparamsMapper.updateSbCraneAddparams(sbCraneAddparams);
	}

	/**
     * 删除塔式起重机参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneAddparamsByIds(String ids)
	{
		return sbCraneAddparamsMapper.deleteSbCraneAddparamsByIds(Convert.toStrArray(ids));
	}
	
}
