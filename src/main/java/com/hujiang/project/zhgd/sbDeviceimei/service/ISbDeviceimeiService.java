package com.hujiang.project.zhgd.sbDeviceimei.service;

import com.hujiang.project.zhgd.sbDeviceimei.domain.SbDeviceimei;
import java.util.List;

/**
 * 设备编号
 服务层
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public interface ISbDeviceimeiService 
{
	/**
	 * 查询所有定位设备
	 * @return
	 */
	public List<String> selectDeviceimeiListAll();
	/**
     * 查询设备编号信息
     * 
     * @param id 设备编号ID
     * @return 设备编号信息
     */
	public SbDeviceimei selectSbDeviceimeiById(Integer id);
	
	/**
     * 查询设备编号列表
     * 
     * @param sbDeviceimei 设备编号信息
     * @return 设备编号集合
     */
	public List<SbDeviceimei> selectSbDeviceimeiList(SbDeviceimei sbDeviceimei);
	
	/**
     * 新增设备编号

     * 
     * @param sbDeviceimei 设备编号信息
     * @return 结果
     */
	public int insertSbDeviceimei(SbDeviceimei sbDeviceimei);
	
	/**
     * 修改设备编号

     * 
     * @param sbDeviceimei 设备编号信息
     * @return 结果
     */
	public int updateSbDeviceimei(SbDeviceimei sbDeviceimei);
		
	/**
     * 删除设备编号信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeviceimeiByIds(String ids);
	
}
