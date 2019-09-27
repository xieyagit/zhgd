package com.hujiang.project.zhgd.hjCompanyProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjCompanyProject.mapper.HjCompanyProjectMapper;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.common.support.Convert;

/**
 * 公司项目 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjCompanyProjectServiceImpl implements IHjCompanyProjectService 
{
	@Autowired
	private HjCompanyProjectMapper hjCompanyProjectMapper;

	/**
     * 查询公司项目信息
     * 
     * @param id 公司项目ID
     * @return 公司项目信息
     */
    @Override
	public HjCompanyProject selectHjCompanyProjectById(Integer id)
	{
	    return hjCompanyProjectMapper.selectHjCompanyProjectById(id);
	}
	
	/**
     * 查询公司项目列表
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 公司项目集合
     */
	@Override
	public List<HjCompanyProject> selectHjCompanyProjectList(HjCompanyProject hjCompanyProject)
	{
	    return hjCompanyProjectMapper.selectHjCompanyProjectList(hjCompanyProject);
	}
	
    /**
     * 新增公司项目
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 结果
     */
	@Override
	public int insertHjCompanyProject(HjCompanyProject hjCompanyProject)
	{
	    return hjCompanyProjectMapper.insertHjCompanyProject(hjCompanyProject);
	}
	
	/**
     * 修改公司项目
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 结果
     */
	@Override
	public int updateHjCompanyProject(HjCompanyProject hjCompanyProject)
	{
	    return hjCompanyProjectMapper.updateHjCompanyProject(hjCompanyProject);
	}

	/**
     * 删除公司项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjCompanyProjectByIds(String ids)
	{
		return hjCompanyProjectMapper.deleteHjCompanyProjectByIds(Convert.toStrArray(ids));
	}
	
}
