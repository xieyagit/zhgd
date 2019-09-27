package com.hujiang.project.zhgd.sbElevatorAddbasicinfo.mapper;

import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.domain.SbElevatorAddbasicinfo;
import java.util.List;	

/**
 * 升降机基本 数据层
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public interface SbElevatorAddbasicinfoMapper 
{
	/**
     * 查询升降机基本信息
     * 
     * @param id 升降机基本ID
     * @return 升降机基本信息
     */
	public SbElevatorAddbasicinfo selectSbElevatorAddbasicinfoById(Integer id);
	
	/**
     * 查询升降机基本列表
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 升降机基本集合
     */
	public List<SbElevatorAddbasicinfo> selectSbElevatorAddbasicinfoList(SbElevatorAddbasicinfo sbElevatorAddbasicinfo);
	
	/**
     * 新增升降机基本
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 结果
     */
	public int insertSbElevatorAddbasicinfo(SbElevatorAddbasicinfo sbElevatorAddbasicinfo);
	
	/**
     * 修改升降机基本
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 结果
     */
	public int updateSbElevatorAddbasicinfo(SbElevatorAddbasicinfo sbElevatorAddbasicinfo);
	
	/**
     * 删除升降机基本
     * 
     * @param id 升降机基本ID
     * @return 结果
     */
	public int deleteSbElevatorAddbasicinfoById(Integer id);
	
	/**
     * 批量删除升降机基本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorAddbasicinfoByIds(String[] ids);
	
}