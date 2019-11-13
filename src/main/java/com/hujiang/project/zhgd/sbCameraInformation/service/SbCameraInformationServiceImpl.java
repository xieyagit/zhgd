package com.hujiang.project.zhgd.sbCameraInformation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.sbCameraInformation.mapper.SbCameraInformationMapper;
import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import com.hujiang.common.support.Convert;

/**
 * 海康摄像头报警记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-10-16
 */
@Service
public class SbCameraInformationServiceImpl implements ISbCameraInformationService 
{
	@Autowired
	private SbCameraInformationMapper sbCameraInformationMapper;

	/**
     * 查询海康摄像头报警记录信息
     * 
     * @param msgId 海康摄像头报警记录msgId
     * @return 海康摄像头报警记录信息
     */
    @Override
	public SbCameraInformation selectSbCameraInformationById(String msgId)
	{
	    return sbCameraInformationMapper.selectSbCameraInformationById(msgId);
	}

	/**
     * 查询海康摄像头报警记录列表
     *
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 海康摄像头报警记录集合
     */
	@Override
	public List<SbCameraInformation> selectSbCameraInformationList(SbCameraInformation sbCameraInformation)
	{
	    return sbCameraInformationMapper.selectSbCameraInformationList(sbCameraInformation);
	}

    /**
     * 新增海康摄像头报警记录
     *
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 结果
     */
	@Override
	public int insertSbCameraInformation(SbCameraInformation sbCameraInformation)
	{
	    return sbCameraInformationMapper.insertSbCameraInformation(sbCameraInformation);
	}

	/**
     * 修改海康摄像头报警记录
     *
     * @param sbCameraInformation 海康摄像头报警记录信息
     * @return 结果
     */
	@Override
	public int updateSbCameraInformation(SbCameraInformation sbCameraInformation)
	{
	    return sbCameraInformationMapper.updateSbCameraInformation(sbCameraInformation);
	}

	/**
     * 删除海康摄像头报警记录对象
     *
     * @param msgIds 需要删除的数据msgId
     * @return 结果
     */
	@Override
	public int deleteSbCameraInformationByIds(String msgIds)
	{
		return sbCameraInformationMapper.deleteSbCameraInformationByIds(Convert.toStrArray(msgIds));
	}

	/**
	 * 批量插入海康摄像头报警记录
	 *
	 * @param messages 需要插入的报警数据
	 * @return 结果
	 */
	public int insertInformation(List<SbCameraInformation> messages){
		return sbCameraInformationMapper.insertInformation(messages);
	}

	public List<SbCameraInformation> selectSbCameraInformationLists(SbCameraInformation sbCameraInformation){
		return sbCameraInformationMapper.selectSbCameraInformationLists(sbCameraInformation);
	}
}
