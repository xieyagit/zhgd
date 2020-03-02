package com.hujiang.project.zhgd.hjZhgdPkcount.service;

import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车位剩余 服务层
 * 
 * @author hujiang
 * @date 2019-07-09
 */
public interface IHjZhgdPkcountService 
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
     * 删除车位剩余信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjZhgdPkcountById(Integer id);


	/**
	 * 查询某项目剩余车位
	 * */
	public List<HjZhgdPkcount> pkcount(HjZhgdPkcount hjZhgdPkcount);

	/**
	 * 查询剩余车位
	 * */
	public HjZhgdPkcount carpkcount(HjZhgdPkcount hjZhgdPkcount);

	/**
	 *场内车位设置
	 * */
	public int carUpd(String deptId, Integer pkcount);

	/**
	 * 添加设备
	 * */
	public int add(HjZhgdPkcount hjZhgdPkcount);

	/**
	 * 查询sn
	 * */
	public HjZhgdPkcount selectAll(String deptId ,String snName);

	/**
	 * 查询项目Id
	 * */
	public HjZhgdPkcount selectProjectId(Integer deptID);

	/**查询sn*/
	public HjZhgdPkcount selectSN(HjZhgdPkcount hjZhgdPkcount);
	/**
	 * 车辆总数
	 */
	public List<HjZhgdPkcount> getHjZhgdPkcountList(Integer cid);
}
