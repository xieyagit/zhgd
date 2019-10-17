package com.hujiang.project.zhgd.sbCamera.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCamera.mapper.SbCameraMapper;
import com.hujiang.project.zhgd.sbCamera.domain.SbCamera;
import com.hujiang.project.zhgd.sbCamera.service.ISbCameraService;
import com.hujiang.common.support.Convert;

/**
 * AI摄像头客户 服务层实现
 * 
 * @author hujiang
 * @date 2019-10-15
 */
@Service
public class SbCameraServiceImpl implements ISbCameraService 
{
	@Autowired
	private SbCameraMapper sbCameraMapper;

	/**
     * 查询AI摄像头客户信息
     * 
     * @param id AI摄像头客户ID
     * @return AI摄像头客户信息
     */
    @Override
	public SbCamera selectSbCameraById(Integer id)
	{
	    return sbCameraMapper.selectSbCameraById(id);
	}
	
	/**
     * 查询AI摄像头客户列表
     * 
     * @param sbCamera AI摄像头客户信息
     * @return AI摄像头客户集合
     */
	@Override
	public List<SbCamera> selectSbCameraList(SbCamera sbCamera)
	{
	    return sbCameraMapper.selectSbCameraList(sbCamera);
	}
	
    /**
     * 新增AI摄像头客户
     * 
     * @param sbCamera AI摄像头客户信息
     * @return 结果
     */
	@Override
	public int insertSbCamera(SbCamera sbCamera)
	{
	    return sbCameraMapper.insertSbCamera(sbCamera);
	}
	
	/**
     * 修改AI摄像头客户
     * 
     * @param sbCamera AI摄像头客户信息
     * @return 结果
     */
	@Override
	public int updateSbCamera(SbCamera sbCamera)
	{
	    return sbCameraMapper.updateSbCamera(sbCamera);
	}

	/**
     * 删除AI摄像头客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCameraByIds(String ids)
	{
		return sbCameraMapper.deleteSbCameraByIds(Convert.toStrArray(ids));
	}
	
}
