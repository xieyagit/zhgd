package com.hujiang.project.zhgd.zhNode.service;

import java.util.List;

import com.hujiang.project.zhgd.zhNode.domain.ZhProgressPlan;
import com.hujiang.project.zhgd.zhNode.mapper.ZhProgressPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.common.support.Convert;

/**
 * 进度计划 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-02
 */
@Service
public class ZhProgressPlanServiceImpl implements IZhProgressPlanService 
{
	@Autowired
	private ZhProgressPlanMapper zhProgressPlanMapper;

	/**
     * 查询进度计划信息
     * 
     * @param id 进度计划ID
     * @return 进度计划信息
     */
    @Override
	public ZhProgressPlan selectZhProgressPlanById(Integer id)
	{
	    return zhProgressPlanMapper.selectZhProgressPlanById(id);
	}
	
	/**
     * 查询进度计划列表
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 进度计划集合
     */
	@Override
	public List<ZhProgressPlan> selectZhProgressPlanList(ZhProgressPlan zhProgressPlan)
	{
	    return zhProgressPlanMapper.selectZhProgressPlanList(zhProgressPlan);
	}
	
    /**
     * 新增进度计划
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 结果
     */
	@Override
	public int insertZhProgressPlan(ZhProgressPlan zhProgressPlan)
	{
	    return zhProgressPlanMapper.insertZhProgressPlan(zhProgressPlan);
	}
	
	/**
     * 修改进度计划
     * 
     * @param zhProgressPlan 进度计划信息
     * @return 结果
     */
	@Override
	public int updateZhProgressPlan(ZhProgressPlan zhProgressPlan)
	{
	    return zhProgressPlanMapper.updateZhProgressPlan(zhProgressPlan);
	}


	/**
	 * 根据id删除进度计划对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteZhProgressPlanById(int id)
	{
		return zhProgressPlanMapper.deleteZhProgressPlanById(id);
	}
	/**
     * 多项删除进度计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteZhProgressPlanByIds(String ids)
	{
		return zhProgressPlanMapper.deleteZhProgressPlanByIds(Convert.toStrArray(ids));
	}
	
}
