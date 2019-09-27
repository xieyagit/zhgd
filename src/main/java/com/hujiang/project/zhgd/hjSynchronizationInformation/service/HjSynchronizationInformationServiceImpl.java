package com.hujiang.project.zhgd.hjSynchronizationInformation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSynchronizationInformation.mapper.HjSynchronizationInformationMapper;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.common.support.Convert;

/**
 * 项目两制同步 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjSynchronizationInformationServiceImpl implements IHjSynchronizationInformationService 
{
	@Autowired
	private HjSynchronizationInformationMapper hjSynchronizationInformationMapper;

	/**
     * 查询项目两制同步信息
     * 
     * @param id 项目两制同步ID
     * @return 项目两制同步信息
     */
    @Override
	public HjSynchronizationInformation selectHjSynchronizationInformationById(Integer id)
	{
	    return hjSynchronizationInformationMapper.selectHjSynchronizationInformationById(id);
	}
	
	/**
     * 查询项目两制同步列表
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 项目两制同步集合
     */
	@Override
	public List<HjSynchronizationInformation> selectHjSynchronizationInformationList(HjSynchronizationInformation hjSynchronizationInformation)
	{
	    return hjSynchronizationInformationMapper.selectHjSynchronizationInformationList(hjSynchronizationInformation);
	}
	
    /**
     * 新增项目两制同步
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 结果
     */
	@Override
	public int insertHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation)
	{
	    return hjSynchronizationInformationMapper.insertHjSynchronizationInformation(hjSynchronizationInformation);
	}
	
	/**
     * 修改项目两制同步
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 结果
     */
	@Override
	public int updateHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation)
	{
	    return hjSynchronizationInformationMapper.updateHjSynchronizationInformation(hjSynchronizationInformation);
	}

	/**
     * 删除项目两制同步对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSynchronizationInformationByIds(String ids)
	{
		return hjSynchronizationInformationMapper.deleteHjSynchronizationInformationByIds(Convert.toStrArray(ids));
	}

	/***
	 * 获取项目同步信息
	 * @param projectId & 平台名称
	 * @return HjSynchronizationInformation
	 */
	@Override
	public HjSynchronizationInformation getProjectSynchronizationInfoByPlatformname(Integer projectId, String platformName, String apiType) {

		HjSynchronizationInformation info = new HjSynchronizationInformation();
		info.setProjectId(projectId);
		info.setPlatformName(platformName);
		info.setApiType(apiType);

		return hjSynchronizationInformationMapper.getProjectSynchronizationInfoByPlatformname(info);
	}

}
