package com.hujiang.project.zhgd.sbCameraBinding.mapper;

import com.hujiang.project.zhgd.sbCameraBinding.domain.SbCameraBinding;
import java.util.List;	

/**
 * 摄像头设备 数据层
 * 
 * @author hujiang
 * @date 2019-10-15
 */
public interface SbCameraBindingMapper 
{
	/**
     * 查询摄像头设备信息
     * 
     * @param id 摄像头设备ID
     * @return 摄像头设备信息
     */
	public SbCameraBinding selectSbCameraBindingById(Integer id);
	
	/**
     * 查询摄像头设备列表
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 摄像头设备集合
     */
	public List<SbCameraBinding> selectSbCameraBindingList(SbCameraBinding sbCameraBinding);
	
	/**
     * 新增摄像头设备
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 结果
     */
	public int insertSbCameraBinding(SbCameraBinding sbCameraBinding);
	
	/**
     * 修改摄像头设备
     * 
     * @param sbCameraBinding 摄像头设备信息
     * @return 结果
     */
	public int updateSbCameraBinding(SbCameraBinding sbCameraBinding);
	
	/**
     * 删除摄像头设备
     * 
     * @param id 摄像头设备ID
     * @return 结果
     */
	public int deleteSbCameraBindingById(Integer id);
	
	/**
     * 批量删除摄像头设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCameraBindingByIds(String[] ids);
	
}