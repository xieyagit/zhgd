package com.hujiang.project.zhgd.hjCompanyProject.service;

import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import java.util.List;

/**
 * 公司项目 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjCompanyProjectService 
{
	/**
     * 查询公司项目信息
     * 
     * @param id 公司项目ID
     * @return 公司项目信息
     */
	public HjCompanyProject selectHjCompanyProjectById(Integer id);
	
	/**
     * 查询公司项目列表
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 公司项目集合
     */
	public List<HjCompanyProject> selectHjCompanyProjectList(HjCompanyProject hjCompanyProject);
	
	/**
     * 新增公司项目
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 结果
     */
	public int insertHjCompanyProject(HjCompanyProject hjCompanyProject);
	
	/**
     * 修改公司项目
     * 
     * @param hjCompanyProject 公司项目信息
     * @return 结果
     */
	public int updateHjCompanyProject(HjCompanyProject hjCompanyProject);
		
	/**
     * 删除公司项目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjCompanyProjectByIds(String ids);
	
}
