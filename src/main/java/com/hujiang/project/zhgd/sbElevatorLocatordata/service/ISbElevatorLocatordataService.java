package com.hujiang.project.zhgd.sbElevatorLocatordata.service;

import com.hujiang.project.zhgd.sbElevatorLocatordata.domain.SbElevatorLocatordata;
import java.util.List;

/**
 * 升降机GPS定位数据
 服务层
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public interface ISbElevatorLocatordataService 
{
	/**
     * 查询升降机GPS定位数据
信息
     * 
     * @param id 升降机GPS定位数据
ID
     * @return 升降机GPS定位数据
信息
     */
	public SbElevatorLocatordata selectSbElevatorLocatordataById(Integer id);
	
	/**
     * 查询升降机GPS定位数据
列表
     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 升降机GPS定位数据
集合
     */
	public List<SbElevatorLocatordata> selectSbElevatorLocatordataList(SbElevatorLocatordata sbElevatorLocatordata);
	
	/**
     * 新增升降机GPS定位数据

     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 结果
     */
	public int insertSbElevatorLocatordata(SbElevatorLocatordata sbElevatorLocatordata);
	
	/**
     * 修改升降机GPS定位数据

     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 结果
     */
	public int updateSbElevatorLocatordata(SbElevatorLocatordata sbElevatorLocatordata);
		
	/**
     * 删除升降机GPS定位数据
信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorLocatordataByIds(String ids);
	
}
