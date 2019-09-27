package com.hujiang.project.zhgd.sbApiFaceEquipment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbApiFaceEquipment.mapper.SbApiFaceEquipmentMapper;
import com.hujiang.project.zhgd.sbApiFaceEquipment.domain.SbApiFaceEquipment;
import com.hujiang.project.zhgd.sbApiFaceEquipment.service.ISbApiFaceEquipmentService;
import com.hujiang.common.support.Convert;

/**
 * 考勤人脸 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Service
public class SbApiFaceEquipmentServiceImpl implements ISbApiFaceEquipmentService 
{
	@Autowired
	private SbApiFaceEquipmentMapper sbApiFaceEquipmentMapper;

	/**
     * 查询考勤人脸信息
     * 
     * @param id 考勤人脸ID
     * @return 考勤人脸信息
     */
    @Override
	public SbApiFaceEquipment selectSbApiFaceEquipmentById(Integer id)
	{
	    return sbApiFaceEquipmentMapper.selectSbApiFaceEquipmentById(id);
	}
	
	/**
     * 查询考勤人脸列表
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 考勤人脸集合
     */
	@Override
	public List<SbApiFaceEquipment> selectSbApiFaceEquipmentList(SbApiFaceEquipment sbApiFaceEquipment)
	{
	    return sbApiFaceEquipmentMapper.selectSbApiFaceEquipmentList(sbApiFaceEquipment);
	}
	
    /**
     * 新增考勤人脸
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 结果
     */
	@Override
	public int insertSbApiFaceEquipment(SbApiFaceEquipment sbApiFaceEquipment)
	{
	    return sbApiFaceEquipmentMapper.insertSbApiFaceEquipment(sbApiFaceEquipment);
	}
	
	/**
     * 修改考勤人脸
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 结果
     */
	@Override
	public int updateSbApiFaceEquipment(SbApiFaceEquipment sbApiFaceEquipment)
	{
	    return sbApiFaceEquipmentMapper.updateSbApiFaceEquipment(sbApiFaceEquipment);
	}

	/**
     * 删除考勤人脸对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbApiFaceEquipmentByIds(String ids)
	{
		return sbApiFaceEquipmentMapper.deleteSbApiFaceEquipmentByIds(Convert.toStrArray(ids));
	}
	
}
