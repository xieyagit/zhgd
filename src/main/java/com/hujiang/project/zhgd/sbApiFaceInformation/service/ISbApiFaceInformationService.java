package com.hujiang.project.zhgd.sbApiFaceInformation.service;

import com.hujiang.project.zhgd.sbApiFaceInformation.domain.SbApiFaceInformation;
import java.util.List;

/**
 * 考勤人脸 服务层
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public interface ISbApiFaceInformationService 
{
	/**
     * 查询考勤人脸信息
     * 
     * @param id 考勤人脸ID
     * @return 考勤人脸信息
     */
	public SbApiFaceInformation selectSbApiFaceInformationById(Integer id);
	
	/**
     * 查询考勤人脸列表
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 考勤人脸集合
     */
	public List<SbApiFaceInformation> selectSbApiFaceInformationList(SbApiFaceInformation sbApiFaceInformation);
	
	/**
     * 新增考勤人脸
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 结果
     */
	public int insertSbApiFaceInformation(SbApiFaceInformation sbApiFaceInformation);
	
	/**
     * 修改考勤人脸
     * 
     * @param sbApiFaceInformation 考勤人脸信息
     * @return 结果
     */
	public int updateSbApiFaceInformation(SbApiFaceInformation sbApiFaceInformation);
		
	/**
     * 删除考勤人脸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbApiFaceInformationByIds(String ids);
	
}
