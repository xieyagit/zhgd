package com.hujiang.project.zhgd.hjZhgdPkcount.mapper;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车位剩余 数据层
 * 
 * @author hujiang
 * @date 2019-07-09
 */
public interface HjZhgdPkcountMapper 
{
	/**
     * 查询车位剩余信息
     * 
     * @param id 车位剩余ID
     * @return 车位剩余信息
     */
	public HjZhgdPkcount selectHjZhgdPkcountById(Integer id);
	
	/**
     * 查询车位剩余列表
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 车位剩余集合
     */
	public List<HjZhgdPkcount> selectHjZhgdPkcountList(HjZhgdPkcount hjZhgdPkcount);
	
	/**
     * 新增车位剩余
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 结果
     */
	public int insertHjZhgdPkcount(HjZhgdPkcount hjZhgdPkcount);
	
	/**
     * 修改车位剩余
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 结果
     */
	public int updateHjZhgdPkcount(HjZhgdPkcount hjZhgdPkcount);
	
	/**
     * 删除车位剩余
     * 
     * @param id 车位剩余ID
     * @return 结果
     */
	public int deleteHjZhgdPkcountById(@Param("id") Integer id);


	/**
	 * 查询某项所以车场ID
	 * */
	public List<HjZhgdPkcount> pkcount(HjZhgdPkcount hjZhgdPkcount);

	/**
	 * 查询剩余车位
	 * */
	public HjZhgdPkcount carpkcount(HjZhgdPkcount hjZhgdPkcount);

	/**
	 *场内车位设置
	 * */
	public int carUpd(@Param("deptId") String deptId,@Param("pkcount") Integer pkcount);
	/**
	 * 添加设备
	 * */
	public int add(HjZhgdPkcount hjZhgdPkcount);

	/**
	 * 查询sn
	 * */
	public HjZhgdPkcount selectAll(@Param("deptId") String deptId , @Param("snName") String snName);

	/**
	 * 查询项目Id
	 * */
	public HjZhgdPkcount selectProjectId(@Param("deptID")Integer deptID);

}