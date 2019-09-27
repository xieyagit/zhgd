package com.hujiang.project.zhgd.sbUnloaderBinding.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderBinding.mapper.SbUnloaderBindingMapper;
import com.hujiang.project.zhgd.sbUnloaderBinding.domain.SbUnloaderBinding;
import com.hujiang.project.zhgd.sbUnloaderBinding.service.ISbUnloaderBindingService;
import com.hujiang.common.support.Convert;

/**
 * 卸料设备绑定 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-15
 */
@Service
public class SbUnloaderBindingServiceImpl implements ISbUnloaderBindingService 
{
	@Autowired
	private SbUnloaderBindingMapper sbUnloaderBindingMapper;

	/**
     * 查询卸料设备绑定信息
     * 
     * @param id 卸料设备绑定ID
     * @return 卸料设备绑定信息
     */
    @Override
	public SbUnloaderBinding selectSbUnloaderBindingById(Integer id)
	{
	    return sbUnloaderBindingMapper.selectSbUnloaderBindingById(id);
	}
	
	/**
     * 查询卸料设备绑定列表
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 卸料设备绑定集合
     */
	@Override
	public List<SbUnloaderBinding> selectSbUnloaderBindingList(SbUnloaderBinding sbUnloaderBinding)
	{
	    return sbUnloaderBindingMapper.selectSbUnloaderBindingList(sbUnloaderBinding);
	}
	
    /**
     * 新增卸料设备绑定
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderBinding(SbUnloaderBinding sbUnloaderBinding)
	{
	    return sbUnloaderBindingMapper.insertSbUnloaderBinding(sbUnloaderBinding);
	}
	
	/**
     * 修改卸料设备绑定
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderBinding(SbUnloaderBinding sbUnloaderBinding)
	{
	    return sbUnloaderBindingMapper.updateSbUnloaderBinding(sbUnloaderBinding);
	}

	/**
     * 删除卸料设备绑定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderBindingByIds(String ids)
	{
		return sbUnloaderBindingMapper.deleteSbUnloaderBindingByIds(Convert.toStrArray(ids));
	}
	
}
