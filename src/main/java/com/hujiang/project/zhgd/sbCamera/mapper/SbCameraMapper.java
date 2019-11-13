package com.hujiang.project.zhgd.sbCamera.mapper;

import com.hujiang.project.zhgd.sbCamera.domain.SbCamera;
import java.util.List;	

/**
 * AI摄像头客户 数据层
 * 
 * @author hujiang
 * @date 2019-10-15
 */
public interface SbCameraMapper 
{
	/**
     * 查询AI摄像头客户信息
     * 
     * @param id AI摄像头客户ID
     * @return AI摄像头客户信息
     */
	public SbCamera selectSbCameraById(Integer id);
	
	/**
     * 查询AI摄像头客户列表
     * 
     * @param sbCamera AI摄像头客户信息
     * @return AI摄像头客户集合
     */
	public List<SbCamera> selectSbCameraList(SbCamera sbCamera);
	
	/**
     * 新增AI摄像头客户
     * 
     * @param sbCamera AI摄像头客户信息
     * @return 结果
     */
	public int insertSbCamera(SbCamera sbCamera);
	
	/**
     * 修改AI摄像头客户
     * 
     * @param sbCamera AI摄像头客户信息
     * @return 结果
     */
	public int updateSbCamera(SbCamera sbCamera);
	
	/**
     * 删除AI摄像头客户
     * 
     * @param id AI摄像头客户ID
     * @return 结果
     */
	public int deleteSbCameraById(Integer id);
	
	/**
     * 批量删除AI摄像头客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCameraByIds(String[] ids);
	
}