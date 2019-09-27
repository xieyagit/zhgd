package com.hujiang.project.zhgd.sbElevatorOperator.mapper;

import com.hujiang.project.zhgd.sbElevatorOperator.domain.SbElevatorOperator;
import java.util.List;	

/**
 * 升降机操作记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public interface SbElevatorOperatorMapper 
{
	/**
     * 查询升降机操作记录信息
     * 
     * @param id 升降机操作记录ID
     * @return 升降机操作记录信息
     */
	public SbElevatorOperator selectSbElevatorOperatorById(Integer id);
	
	/**
     * 查询升降机操作记录列表
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 升降机操作记录集合
     */
	public List<SbElevatorOperator> selectSbElevatorOperatorList(SbElevatorOperator sbElevatorOperator);
	
	/**
     * 新增升降机操作记录
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 结果
     */
	public int insertSbElevatorOperator(SbElevatorOperator sbElevatorOperator);
	
	/**
     * 修改升降机操作记录
     * 
     * @param sbElevatorOperator 升降机操作记录信息
     * @return 结果
     */
	public int updateSbElevatorOperator(SbElevatorOperator sbElevatorOperator);
	
	/**
     * 删除升降机操作记录
     * 
     * @param id 升降机操作记录ID
     * @return 结果
     */
	public int deleteSbElevatorOperatorById(Integer id);
	
	/**
     * 批量删除升降机操作记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorOperatorByIds(String[] ids);
	
}