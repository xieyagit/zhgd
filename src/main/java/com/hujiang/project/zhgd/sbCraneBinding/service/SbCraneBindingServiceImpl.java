package com.hujiang.project.zhgd.sbCraneBinding.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneBinding.mapper.SbCraneBindingMapper;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.common.support.Convert;

/**
 * 塔吊设备绑定 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service@Transactional
public class SbCraneBindingServiceImpl implements ISbCraneBindingService 
{
	@Autowired
	private SbCraneBindingMapper sbCraneBindingMapper;

	@Override
	public List<SbCraneBinding> selectByHxzId(int pid) {
		return sbCraneBindingMapper.selectByHxzId(pid);
	}

	/**
     * 查询塔吊设备绑定信息
     * 
     * @param id 塔吊设备绑定ID
     * @return 塔吊设备绑定信息
     */
    @Override
	public SbCraneBinding selectSbCraneBindingById(Integer id)
	{
	    return sbCraneBindingMapper.selectSbCraneBindingById(id);
	}
	
	/**
     * 查询塔吊设备绑定列表
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 塔吊设备绑定集合
     */
	@Override
	public List<SbCraneBinding> selectSbCraneBindingList(SbCraneBinding sbCraneBinding)
	{
	    return sbCraneBindingMapper.selectSbCraneBindingList(sbCraneBinding);
	}
	
    /**
     * 新增塔吊设备绑定
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 结果
     */
	@Override
	public int insertSbCraneBinding(SbCraneBinding sbCraneBinding)
	{
	    return sbCraneBindingMapper.insertSbCraneBinding(sbCraneBinding);
	}
	
	/**
     * 修改塔吊设备绑定
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 结果
     */
	@Override
	public int updateSbCraneBinding(SbCraneBinding sbCraneBinding)
	{
	    return sbCraneBindingMapper.updateSbCraneBinding(sbCraneBinding);
	}

	/**
     * 删除塔吊设备绑定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneBindingByIds(String ids)
	{
		return sbCraneBindingMapper.deleteSbCraneBindingByIds(Convert.toStrArray(ids));
	}




}
