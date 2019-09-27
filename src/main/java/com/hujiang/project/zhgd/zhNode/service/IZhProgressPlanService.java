package com.hujiang.project.zhgd.zhNode.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.zhNode.domain.ZhProgressPlan;
import java.util.List;

/**
 * 进度计划 服务层
 * 
 * @author hujiang
 * @date 2019-08-02
 */
public interface IZhProgressPlanService 
{
	/**
     * 查询进度计划信息
     * 
     * @param id 进度计划ID
     * @return 进度计划信息
     */
	public ZhProgressPlan selectZhProgressPlanById(Integer id);
	
	/**
     * 查询进度计划列表
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 进度计划集合
     */
	public List<ZhProgressPlan> selectZhProgressPlanList(ZhProgressPlan zhProgressPlan);
	
	/**
     * 新增进度计划
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 结果
     */
	public int insertZhProgressPlan(ZhProgressPlan zhProgressPlan);
	
	/**
     * 修改进度计划
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 结果
     */
	public int updateZhProgressPlan(ZhProgressPlan zhProgressPlan);


	/**
	 * 根据id删除进度计划对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteZhProgressPlanById(int id);

	/**
	 * 多项删除进度计划对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteZhProgressPlanByIds(String ids);
	
}
