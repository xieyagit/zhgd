package com.hujiang.project.zhgd.zhNode.mapper;

import com.hujiang.project.zhgd.zhNode.domain.ZhProgressPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 进度计划 数据层
 * 
 * @author hujiang
 * @date 2019-08-02
 */
public interface ZhProgressPlanMapper 
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
     * 删除进度计划
     * 
     * @param id 进度计划ID
     * @return 结果
     */
	public int deleteZhProgressPlanById(Integer id);
	
	/**
     * 批量删除进度计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteZhProgressPlanByIds(String[] ids);
	
}