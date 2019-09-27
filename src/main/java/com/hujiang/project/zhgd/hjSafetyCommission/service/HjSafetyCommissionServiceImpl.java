package com.hujiang.project.zhgd.hjSafetyCommission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSafetyCommission.mapper.HjSafetyCommissionMapper;
import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.hjSafetyCommission.service.IHjSafetyCommissionService;
import com.hujiang.common.support.Convert;

/**
 * 巡检通知页面数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-01
 */
@Service
public class HjSafetyCommissionServiceImpl implements IHjSafetyCommissionService 
{
	@Autowired
	private HjSafetyCommissionMapper hjSafetyCommissionMapper;

	@Override
	public int deleteSafetyCommission(HjSafetyCommission hjSafetyCommission) {
		return hjSafetyCommissionMapper.deleteSafetyCommission(hjSafetyCommission);
	}

	@Override
	public int getCommissionCount(Integer projectId,Integer userId) {
		return hjSafetyCommissionMapper.getCommissionCount(projectId, userId);
	}

	/**
     * 查询巡检通知页面数据信息
     * 
     * @param id 巡检通知页面数据ID
     * @return 巡检通知页面数据信息
     */
    @Override
	public HjSafetyCommission selectHjSafetyCommissionById(Integer id)
	{
	    return hjSafetyCommissionMapper.selectHjSafetyCommissionById(id);
	}
	
	/**
     * 查询巡检通知页面数据列表
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 巡检通知页面数据集合
     */
	@Override
	public List<HjSafetyCommission> selectHjSafetyCommissionList(HjSafetyCommission hjSafetyCommission)
	{
	    return hjSafetyCommissionMapper.selectHjSafetyCommissionList(hjSafetyCommission);
	}
	
    /**
     * 新增巡检通知页面数据
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 结果
     */
	@Override
	public int insertHjSafetyCommission(HjSafetyCommission hjSafetyCommission)
	{
	    return hjSafetyCommissionMapper.insertHjSafetyCommission(hjSafetyCommission);
	}
	
	/**
     * 修改巡检通知页面数据
     * 
     * @param hjSafetyCommission 巡检通知页面数据信息
     * @return 结果
     */
	@Override
	public int updateHjSafetyCommission(HjSafetyCommission hjSafetyCommission)
	{
	    return hjSafetyCommissionMapper.updateHjSafetyCommission(hjSafetyCommission);
	}

	/**
     * 删除巡检通知页面数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSafetyCommissionByIds(String ids)
	{
		return hjSafetyCommissionMapper.deleteHjSafetyCommissionByIds(Convert.toStrArray(ids));
	}
	
}
