package com.hujiang.project.zhgd.sbUnloaderEquipment.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderEquipment.mapper.SbUnloaderEquipmentMapper;
import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import com.hujiang.project.zhgd.sbUnloaderEquipment.service.ISbUnloaderEquipmentService;
import com.hujiang.common.support.Convert;

/**
 * 卸料设备运行时长 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-12
 */
@Service
public class SbUnloaderEquipmentServiceImpl implements ISbUnloaderEquipmentService
{
	@Autowired
	private SbUnloaderEquipmentMapper sbUnloaderEquipmentMapper;

	@Override
	public SbUnloaderEquipment getSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment) {
		return sbUnloaderEquipmentMapper.getSbUnloaderEquipment(sbUnloaderEquipment);
	}

	@Override
	public int count(SbUnloaderEquipment sbUnloaderEquipment) {
		return sbUnloaderEquipmentMapper.count(sbUnloaderEquipment);
	}

	/**
     * 查询卸料设备运行时长信息
     * 
     * @param id 卸料设备运行时长ID
     * @return 卸料设备运行时长信息
     */
    @Override
	public SbUnloaderEquipment selectSbUnloaderEquipmentById(Integer id)
	{
	    return sbUnloaderEquipmentMapper.selectSbUnloaderEquipmentById(id);
	}
	
	/**
     * 查询卸料设备运行时长列表
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 卸料设备运行时长集合
     */
	@Override
	public List<SbUnloaderEquipment> selectSbUnloaderEquipmentList(SbUnloaderEquipment sbUnloaderEquipment)
	{
	    return sbUnloaderEquipmentMapper.selectSbUnloaderEquipmentList(sbUnloaderEquipment);
	}
	
    /**
     * 新增卸料设备运行时长
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment)
	{
	    return sbUnloaderEquipmentMapper.insertSbUnloaderEquipment(sbUnloaderEquipment);
	}
	
	/**
     * 修改卸料设备运行时长
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment)
	{
	    return sbUnloaderEquipmentMapper.updateSbUnloaderEquipment(sbUnloaderEquipment);
	}

	/**
     * 删除卸料设备运行时长对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderEquipmentByIds(String ids)
	{
		return sbUnloaderEquipmentMapper.deleteSbUnloaderEquipmentByIds(Convert.toStrArray(ids));
	}
	
}
