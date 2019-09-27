package com.hujiang.project.zhgd.hjZhgdDriver.mapper;

import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车牌绑定司机 数据层
 * 
 * @author hujiang
 * @date 2019-08-30
 */
public interface HjZhgdDriverMapper 
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
	public int insertHjZhgdDriver(@Param("driver") String driver,@Param("vehicle") String vehicle,@Param("projectId") String projectId);
	
	/**
     * 修改车牌绑定司机
     * 
     * @param hjZhgdDriver 车牌绑定司机信息
     * @return 结果
     */
	public int updateHjZhgdDriver(HjZhgdDriver hjZhgdDriver);
	
	/**
     * 删除车牌绑定司机
     * 
     * @param id 车牌绑定司机ID
     * @return 结果
     */
	public int deleteHjZhgdDriverById(Integer id);

	
}