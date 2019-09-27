package com.hujiang.project.zhgd.hjConstructionProject.mapper;

import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import java.util.List;	

/**
 * 参建单位项目 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjConstructionProjectMapper 
{
	/**
     * 查询参建单位项目信息
     * 
     * @param id 参建单位项目ID
     * @return 参建单位项目信息
     */
	public HjConstructionProject selectHjConstructionProjectById(Integer id);

	/**
	 * 查询项目ID
	 * @param constructionId
	 * @return
	 */
	public HjConstructionProject selectHjConstructionProjectByProjectId(Integer constructionId);

	
	/**
     * 查询参建单位项目列表
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 参建单位项目集合
     */
	public List<HjConstructionProject> selectHjConstructionProjectList(HjConstructionProject hjConstructionProject);

	/**
	 * 查询项目的参建单位列表
	 *
	 * @param projectId 项目id
	 * @return 参建单位集合
	 */
	public List<HjConstructionProject> selectHjConstructionList(int projectId);
	
	/**
     * 新增参建单位项目
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 结果
     */
	public int insertHjConstructionProject(HjConstructionProject hjConstructionProject);
	
	/**
     * 修改参建单位项目
     * 
     * @param hjConstructionProject 参建单位项目信息
     * @return 结果
     */
	public int updateHjConstructionProject(HjConstructionProject hjConstructionProject);
	
	/**
     * 删除参建单位项目
     * 
     * @param id 参建单位项目ID
     * @return 结果
     */
	public int deleteHjConstructionProjectById(Integer id);
	
	/**
     * 批量删除参建单位项目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjConstructionProjectByIds(String[] ids);
	
}