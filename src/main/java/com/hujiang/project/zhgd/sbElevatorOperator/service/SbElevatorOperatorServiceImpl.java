package com.hujiang.project.zhgd.sbElevatorOperator.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbElevatorOperator.mapper.SbElevatorOperatorMapper;
import com.hujiang.project.zhgd.sbElevatorOperator.domain.SbElevatorOperator;
import com.hujiang.project.zhgd.sbElevatorOperator.service.ISbElevatorOperatorService;
import com.hujiang.common.support.Convert;

/**
 * 升降机操作记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Service
public class SbElevatorOperatorServiceImpl implements ISbElevatorOperatorService 
{
	@Autowired
	private SbElevatorOperatorMapper sbElevatorOperatorMapper;

	/**
     * 查询升降机操作记录信息
     * 
     * @param id 升降机操作记录ID
     * @return 升降机操作记录信息
     */
    @Override
	public SbElevatorOperator selectSbElevatorOperatorById(Integer id)
	{
	    return sbElevatorOperatorMapper.selectSbElevatorOperatorById(id);
	}
	
	/**
     * 查询升降机操作记录列表
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 升降机操作记录集合
     */
	@Override
	public List<SbElevatorOperator> selectSbElevatorOperatorList(SbElevatorOperator sbElevatorOperator)
	{
	    return sbElevatorOperatorMapper.selectSbElevatorOperatorList(sbElevatorOperator);
	}
	
    /**
     * 新增升降机操作记录
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorOperator(SbElevatorOperator sbElevatorOperator)
	{
	    return sbElevatorOperatorMapper.insertSbElevatorOperator(sbElevatorOperator);
	}
	
	/**
     * 修改升降机操作记录
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorOperator(SbElevatorOperator sbElevatorOperator)
	{
	    return sbElevatorOperatorMapper.updateSbElevatorOperator(sbElevatorOperator);
	}

	/**
     * 删除升降机操作记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorOperatorByIds(String ids)
	{
		return sbElevatorOperatorMapper.deleteSbElevatorOperatorByIds(Convert.toStrArray(ids));
	}
	
}
