package com.hujiang.project.zhgd.sbCameraBinding.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCameraBinding.mapper.SbCameraBindingMapper;
import com.hujiang.project.zhgd.sbCameraBinding.domain.SbCameraBinding;
import com.hujiang.project.zhgd.sbCameraBinding.service.ISbCameraBindingService;
import com.hujiang.common.support.Convert;

/**
 * 摄像头设备 服务层实现
 * 
 * @author hujiang
 * @date 2019-10-15
 */
@Service
public class SbCameraBindingServiceImpl implements ISbCameraBindingService 
{
	@Autowired
	private SbCameraBindingMapper sbCameraBindingMapper;

	/**
     * 查询摄像头设备信息
     * 
     * @param id 摄像头设备ID
     * @return 摄像头设备信息
     */
    @Override
	public SbCameraBinding selectSbCameraBindingById(Integer id)
	{
	    return sbCameraBindingMapper.selectSbCameraBindingById(id);
	}
	
	/**
     * 查询摄像头设备列表
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 摄像头设备集合
     */
	@Override
	public List<SbCameraBinding> selectSbCameraBindingList(SbCameraBinding sbCameraBinding)
	{
	    return sbCameraBindingMapper.selectSbCameraBindingList(sbCameraBinding);
	}
	
    /**
     * 新增摄像头设备
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 结果
     */
	@Override
	public int insertSbCameraBinding(SbCameraBinding sbCameraBinding)
	{
	    return sbCameraBindingMapper.insertSbCameraBinding(sbCameraBinding);
	}
	
	/**
     * 修改摄像头设备
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 结果
     */
	@Override
	public int updateSbCameraBinding(SbCameraBinding sbCameraBinding)
	{
	    return sbCameraBindingMapper.updateSbCameraBinding(sbCameraBinding);
	}

	/**
     * 删除摄像头设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCameraBindingByIds(String ids)
	{
		return sbCameraBindingMapper.deleteSbCameraBindingByIds(Convert.toStrArray(ids));
	}
	
}
