package com.hujiang.project.zhgd.sbElevatorElectrify.mapper;

import com.hujiang.project.zhgd.sbElevatorElectrify.domain.SbElevatorElectrify;
import java.util.List;	

/**
 * 升降机通电时间接口 数据层
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public interface SbElevatorElectrifyMapper 
{
	/**
     * 查询升降机通电时间接口信息
     * 
     * @param id 升降机通电时间接口ID
     * @return 升降机通电时间接口信息
     */
	public SbElevatorElectrify selectSbElevatorElectrifyById(Integer id);
	
	/**
     * 查询升降机通电时间接口列表
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 升降机通电时间接口集合
     */
	public List<SbElevatorElectrify> selectSbElevatorElectrifyList(SbElevatorElectrify sbElevatorElectrify);
	
	/**
     * 新增升降机通电时间接口
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 结果
     */
	public int insertSbElevatorElectrify(SbElevatorElectrify sbElevatorElectrify);
	
	/**
     * 修改升降机通电时间接口
     * 
     * @param sbElevatorElectrify 升降机通电时间接口信息
     * @return 结果
     */
	public int updateSbElevatorElectrify(SbElevatorElectrify sbElevatorElectrify);
	
	/**
     * 删除升降机通电时间接口
     * 
     * @param id 升降机通电时间接口ID
     * @return 结果
     */
	public int deleteSbElevatorElectrifyById(Integer id);
	
	/**
     * 批量删除升降机通电时间接口
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorElectrifyByIds(String[] ids);
	
}