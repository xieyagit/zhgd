package com.hujiang.project.zhgd.hjZhgdDriver.service;

import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 车牌绑定司机 服务层
 * 
 * @author hujiang
 * @date 2019-08-30
 */
public interface IHjZhgdDriverService 
{
	/**
     * 查询车牌绑定司机信息
     * 
     * @param id 车牌绑定司机ID
     * @return 车牌绑定司机信息
     */
	public HjZhgdDriver selectHjZhgdDriverById(Integer id);
	
	/**
     * 查询车牌绑定司机列表
     * 
     * @param hjZhgdDriver 车牌绑定司机信息
     * @return 车牌绑定司机集合
     */
	public List<HjZhgdDriver> selectHjZhgdDriverList(HjZhgdDriver hjZhgdDriver);
	
	/**
     * 新增车牌绑定司机
     * @return 结果
     */
	public int insertHjZhgdDriver(String driver, String vehicle,String projectId);
	
	/**
     * 修改车牌绑定司机
     * 
     * @param hjZhgdDriver 车牌绑定司机信息
     * @return 结果
     */
	public int updateHjZhgdDriver(HjZhgdDriver hjZhgdDriver);
		
	/**
     * 删除车牌绑定司机信息
     * @return 结果
     */
	public int deleteHjZhgdDriverById(Integer id);
	
}
