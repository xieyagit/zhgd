package com.hujiang.project.zhgd.sbElevatorElectrify.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbElevatorElectrify.mapper.SbElevatorElectrifyMapper;
import com.hujiang.project.zhgd.sbElevatorElectrify.domain.SbElevatorElectrify;
import com.hujiang.project.zhgd.sbElevatorElectrify.service.ISbElevatorElectrifyService;
import com.hujiang.common.support.Convert;

/**
 * 升降机通电时间接口 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Service
public class SbElevatorElectrifyServiceImpl implements ISbElevatorElectrifyService 
{
	@Autowired
	private SbElevatorElectrifyMapper sbElevatorElectrifyMapper;

	/**
     * 查询升降机通电时间接口信息
     * 
     * @param id 升降机通电时间接口ID
     * @return 升降机通电时间接口信息
     */
    @Override
	public SbElevatorElectrify selectSbElevatorElectrifyById(Integer id)
	{
	    return sbElevatorElectrifyMapper.selectSbElevatorElectrifyById(id);
	}
	
	/**
     * 查询升降机通电时间接口列表
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 升降机通电时间接口集合
     */
	@Override
	public List<SbElevatorElectrify> selectSbElevatorElectrifyList(SbElevatorElectrify sbElevatorElectrify)
	{
	    return sbElevatorElectrifyMapper.selectSbElevatorElectrifyList(sbElevatorElectrify);
	}
	
    /**
     * 新增升降机通电时间接口
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorElectrify(SbElevatorElectrify sbElevatorElectrify)
	{
	    return sbElevatorElectrifyMapper.insertSbElevatorElectrify(sbElevatorElectrify);
	}
	
	/**
     * 修改升降机通电时间接口
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorElectrify(SbElevatorElectrify sbElevatorElectrify)
	{
	    return sbElevatorElectrifyMapper.updateSbElevatorElectrify(sbElevatorElectrify);
	}

	/**
     * 删除升降机通电时间接口对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorElectrifyByIds(String ids)
	{
		return sbElevatorElectrifyMapper.deleteSbElevatorElectrifyByIds(Convert.toStrArray(ids));
	}
	
}
