package com.hujiang.project.zhgd.sbUnloaderParameter.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderParameter.mapper.SbUnloaderParameterMapper;
import com.hujiang.project.zhgd.sbUnloaderParameter.domain.SbUnloaderParameter;
import com.hujiang.project.zhgd.sbUnloaderParameter.service.ISbUnloaderParameterService;
import com.hujiang.common.support.Convert;

/**
 * 卸料基础参数 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-10
 */
@Service
public class SbUnloaderParameterServiceImpl implements ISbUnloaderParameterService 
{
	@Autowired
	private SbUnloaderParameterMapper sbUnloaderParameterMapper;

	/**
     * 查询卸料基础参数信息
     * 
     * @param id 卸料基础参数ID
     * @return 卸料基础参数信息
     */
    @Override
	public SbUnloaderParameter selectSbUnloaderParameterById(Integer id)
	{
	    return sbUnloaderParameterMapper.selectSbUnloaderParameterById(id);
	}
	
	/**
     * 查询卸料基础参数列表
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 卸料基础参数集合
     */
	@Override
	public List<SbUnloaderParameter> selectSbUnloaderParameterList(SbUnloaderParameter sbUnloaderParameter)
	{
	    return sbUnloaderParameterMapper.selectSbUnloaderParameterList(sbUnloaderParameter);
	}
	
    /**
     * 新增卸料基础参数
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderParameter(SbUnloaderParameter sbUnloaderParameter)
	{
	    return sbUnloaderParameterMapper.insertSbUnloaderParameter(sbUnloaderParameter);
	}
	
	/**
     * 修改卸料基础参数
     * 
     * @param sbUnloaderParameter 卸料基础参数信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderParameter(SbUnloaderParameter sbUnloaderParameter)
	{
	    return sbUnloaderParameterMapper.updateSbUnloaderParameter(sbUnloaderParameter);
	}

	/**
     * 删除卸料基础参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderParameterByIds(String ids)
	{
		return sbUnloaderParameterMapper.deleteSbUnloaderParameterByIds(Convert.toStrArray(ids));
	}
	
}
