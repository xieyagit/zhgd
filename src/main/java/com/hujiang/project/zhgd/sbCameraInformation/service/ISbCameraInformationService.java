package com.hujiang.project.zhgd.sbCameraInformation.service;

import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import java.util.List;

/**
 * 海康摄像头报警记录 服务层
 * 
 * @author hujiang
 * @date 2019-10-16
 */
public interface ISbCameraInformationService 
{
	/**
     * 查询海康摄像头报警记录信息
     * 
     * @param msgId 海康摄像头报警记录msgId
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
     * 删除海康摄像头报警记录信息
     *
     * @param msgIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCameraInformationByIds(String msgIds);

	/**
	 * 批量插入海康摄像头报警记录
	 *
	 * @param messages 需要插入的报警数据
	 * @return 结果
	 */
	public int insertInformation(List<SbCameraInformation> messages);
}
