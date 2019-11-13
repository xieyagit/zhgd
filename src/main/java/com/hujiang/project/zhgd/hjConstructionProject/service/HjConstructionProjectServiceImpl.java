package com.hujiang.project.zhgd.hjConstructionProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjConstructionProject.mapper.HjConstructionProjectMapper;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.common.support.Convert;

/**
 * 参建单位项目 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjConstructionProjectServiceImpl implements IHjConstructionProjectService 
{
	@Autowired
	private HjConstructionProjectMapper hjConstructionProjectMapper;

	@Override
	public HjConstructionProject selectHjConstructionProjectByProjectId(Integer constructionId) {
		return hjConstructionProjectMapper.selectHjConstructionProjectByProjectId(constructionId);
	}

	/**
     * 查询参建单位项目信息
     * 
     * @param id 参建单位项目ID
     * @return 参建单位项目信息
     */
    @Override
	public HjConstructionProject selectHjConstructionProjectById(Integer id)
	{
	    return hjConstructionProjectMapper.selectHjConstructionProjectById(id);
	}
	
	/**
     * 查询参建单位项目列表
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 参建单位项目集合
     */
	@Override
	public List<HjConstructionProject> selectHjConstructionProjectList(HjConstructionProject hjConstructionProject)
	{
	    return hjConstructionProjectMapper.selectHjConstructionProjectList(hjConstructionProject);
	}

	/**
	 * 查询项目的参建单位列表
	 *
	 * @param projectId 项目id
	 * @return 参建单位集合
	 */
	@Override
	public List<HjConstructionProject> selectHjConstructionList(int projectId)
	{
		return hjConstructionProjectMapper.selectHjConstructionList(projectId);
	}

    /**
     * 新增参建单位项目
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 结果
     */
	@Override
	public int insertHjConstructionProject(HjConstructionProject hjConstructionProject)
	{
	    return hjConstructionProjectMapper.insertHjConstructionProject(hjConstructionProject);
	}
	
	/**
     * 修改参建单位项目
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 结果
     */
	@Override
	public int updateHjConstructionProject(HjConstructionProject hjConstructionProject)
	{
	    return hjConstructionProjectMapper.updateHjConstructionProject(hjConstructionProject);
	}

	/**
     * 删除参建单位项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjConstructionProjectByIds(String ids)
	{
		return hjConstructionProjectMapper.deleteHjConstructionProjectByIds(Convert.toStrArray(ids));
	}
	public List<HjConstructionProject> hj(Integer id){
		return hjConstructionProjectMapper.hj(id);
	}
}
