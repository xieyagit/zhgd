package com.hujiang.project.zhgd.hjSafetyCommission.service;

import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检通知页面数据 服务层
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public interface IHjSafetyCommissionService 
{
	/**
	 * 删除巡检通知页面数据
	 *
	 * @param hjSafetyCommission 巡检通知页面数据信息
	 * @return 结果
	 */
	public int deleteSafetyCommission(HjSafetyCommission hjSafetyCommission);
	/**
	 * 根据ID查询全部信息
	 * @return
	 */
	public int getCommissionCount(Integer projectId,Integer userId);
	/**
     * 查询巡检通知页面数据信息
     * 
     * @param id 巡检通知页面数据ID
     * @return 巡检通知页面数据信息
     */
	public HjSafetyCommission selectHjSafetyCommissionById(Integer id);
	
	/**
     * 查询巡检通知页面数据列表
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 巡检通知页面数据集合
     */
	public List<HjSafetyCommission> selectHjSafetyCommissionList(HjSafetyCommission hjSafetyCommission);
	
	/**
     * 新增巡检通知页面数据
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 结果
     */
	public int insertHjSafetyCommission(HjSafetyCommission hjSafetyCommission);
	
	/**
     * 修改巡检通知页面数据
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 结果
     */
	public int updateHjSafetyCommission(HjSafetyCommission hjSafetyCommission);
		
	/**
     * 删除巡检通知页面数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyCommissionByIds(String ids);
	
}
