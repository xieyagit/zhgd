package com.hujiang.project.zhgd.sbApiFaceInformation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbApiFaceInformation.mapper.SbApiFaceInformationMapper;
import com.hujiang.project.zhgd.sbApiFaceInformation.domain.SbApiFaceInformation;
import com.hujiang.project.zhgd.sbApiFaceInformation.service.ISbApiFaceInformationService;
import com.hujiang.common.support.Convert;

/**
 * 考勤人脸 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Service
public class SbApiFaceInformationServiceImpl implements ISbApiFaceInformationService 
{
	@Autowired
	private SbApiFaceInformationMapper sbApiFaceInformationMapper;

	/**
     * 查询考勤人脸信息
     * 
     * @param id 考勤人脸ID
     * @return 考勤人脸信息
     */
    @Override
	public SbApiFaceInformation selectSbApiFaceInformationById(Integer id)
	{
	    return sbApiFaceInformationMapper.selectSbApiFaceInformationById(id);
	}
	
	/**
     * 查询考勤人脸列表
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 考勤人脸集合
     */
	@Override
	public List<SbApiFaceInformation> selectSbApiFaceInformationList(SbApiFaceInformation sbApiFaceInformation)
	{
	    return sbApiFaceInformationMapper.selectSbApiFaceInformationList(sbApiFaceInformation);
	}
	
    /**
     * 新增考勤人脸
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 结果
     */
	@Override
	public int insertSbApiFaceInformation(SbApiFaceInformation sbApiFaceInformation)
	{
	    return sbApiFaceInformationMapper.insertSbApiFaceInformation(sbApiFaceInformation);
	}
	
	/**
     * 修改考勤人脸
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 结果
     */
	@Override
	public int updateSbApiFaceInformation(SbApiFaceInformation sbApiFaceInformation)
	{
	    return sbApiFaceInformationMapper.updateSbApiFaceInformation(sbApiFaceInformation);
	}

	/**
     * 删除考勤人脸对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbApiFaceInformationByIds(String ids)
	{
		return sbApiFaceInformationMapper.deleteSbApiFaceInformationByIds(Convert.toStrArray(ids));
	}
	
}
