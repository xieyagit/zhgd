package com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.mapper.HjProjectPersonnelSynchronizationMapper;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service.IHjProjectPersonnelSynchronizationService;
import com.hujiang.common.support.Convert;

/**
 * 项目人员同步 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-16
 */
@Service
@Transactional
public class HjProjectPersonnelSynchronizationServiceImpl implements IHjProjectPersonnelSynchronizationService 
{
	@Autowired
	private HjProjectPersonnelSynchronizationMapper hjProjectPersonnelSynchronizationMapper;

	/**
     * 查询项目人员同步信息
     * 
     * @param id 项目人员同步ID
     * @return 项目人员同步信息
     */
    @Override
	public HjProjectPersonnelSynchronization selectHjProjectPersonnelSynchronizationById(Integer id)
	{
	    return hjProjectPersonnelSynchronizationMapper.selectHjProjectPersonnelSynchronizationById(id);
	}
	
	/**
     * 查询项目人员同步列表
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 项目人员同步集合
     */
	@Override
	public List<HjProjectPersonnelSynchronization> selectHjProjectPersonnelSynchronizationList(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{
	    return hjProjectPersonnelSynchronizationMapper.selectHjProjectPersonnelSynchronizationList(hjProjectPersonnelSynchronization);
	}
	
    /**
     * 新增项目人员同步
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 结果
     */
	@Override
	public int insertHjProjectPersonnelSynchronization(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{
	    return hjProjectPersonnelSynchronizationMapper.insertHjProjectPersonnelSynchronization(hjProjectPersonnelSynchronization);
	}
	
	/**
     * 修改项目人员同步
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 结果
     */
	@Override
	public int updateHjProjectPersonnelSynchronization(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{
	    return hjProjectPersonnelSynchronizationMapper.updateHjProjectPersonnelSynchronization(hjProjectPersonnelSynchronization);
	}

	/**
     * 删除项目人员同步对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjProjectPersonnelSynchronizationByIds(String ids)
	{
		return hjProjectPersonnelSynchronizationMapper.deleteHjProjectPersonnelSynchronizationByIds(Convert.toStrArray(ids));
	}
	
}
