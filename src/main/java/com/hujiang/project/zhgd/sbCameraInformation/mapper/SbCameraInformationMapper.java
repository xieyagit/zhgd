package com.hujiang.project.zhgd.sbCameraInformation.mapper;

import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 海康摄像头报警记录 数据层
 * 
 * @author hujiang
 * @date 2019-10-16
 */
public interface SbCameraInformationMapper 
{
	/**
     * 查询海康摄像头报警记录信息
     * 
     * @param msgId 海康摄像头报警记录ID
     * @return 海康摄像头报警记录信息
     */
	public SbCameraInformation selectSbCameraInformationById(String msgId);
	
	/**
     * 查询海康摄像头报警记录列表
     * 
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 海康摄像头报警记录集合
     */
	public List<SbCameraInformation> selectSbCameraInformationList(SbCameraInformation sbCameraInformation);

	/**
     * 新增海康摄像头报警记录
     * 
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 结果
     */
	public int insertSbCameraInformation(SbCameraInformation sbCameraInformation);
	
	/**
     * 修改海康摄像头报警记录
     * 
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 结果
     */
	public int updateSbCameraInformation(SbCameraInformation sbCameraInformation);
	
	/**
     * 删除海康摄像头报警记录
     * 
     * @param msgId 海康摄像头报警记录ID
     * @return 结果
     */
	public int deleteSbCameraInformationById(String msgId);
	
	/**
     * 批量删除海康摄像头报警记录
     * 
     * @param msgId 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCameraInformationByIds(String[] msgId);

	/**
	 * 批量插入海康摄像头报警记录
	 */
	public int insertInformation(@Param("messages") List<SbCameraInformation> messages);

	public List<SbCameraInformation> selectSbCameraInformationLists(SbCameraInformation sbCameraInformation);
	
}