package com.hujiang.project.zhgd.hjProject.mapper;

import com.hujiang.project.zhgd.hjProject.domain.HjCompanyProjectTemp;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 项目 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjProjectMapper 
{

	public List<HjProject> projectList(Map<String, Object> map);
	/**
     * 查询项目信息
     * 
     * @param id 项目ID
     * @return 项目信息
     */
	public HjProject selectHjProjectById(Integer id);
	
	/**
     * 查询项目列表
     * 
     * @param hjProject 项目信息
     * @return 项目集合
     */
	public List<HjProject> selectHjProjectList(HjProject hjProject);
	/**
	 * 查询指定公司下的项目列表
	 *
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public List<HjProject> selectHjProjectListTwo(Integer companyId);

	/**
	 * 查询公司下指定范围内的项目列表分页
	 *
	 * @param companyId 公司Id
	 * @param region 范围（省、市）
	 * @return 项目集合
	 */
	public List<HjProject> selectHjProjectListR(@Param("companyId")Integer companyId, @Param("region")String region);


	/**
	 * 统计系统所有的项目name的总和
	 * @param name 字段名
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public double infoHjProjectR(@Param("name")String name, @Param("companyId")Integer companyId, @Param("region")String region);

	/**
	 * 统计系统所有的项目name的总和
	 * @param name 字段名
	 * @param companyId 公司Id
	 * @param region 地区编号
	 * @return 项目集合
	 */
	public double infoHjProjectRS(@Param("name")String name, @Param("companyId")Integer companyId);

	/**
	 * 统计系统所有的项目参建单位的总和
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public int infoConstructionR(@Param("companyId")Integer companyId);
	/**
	 * 统计系统所有的项目参建单位的总和
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public int infoConstructionRS(@Param("companyId")Integer companyId);

	/**
	 * 统计公司所有的项目在场总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public int infoPWorkertR(@Param("companyId")Integer companyId);

	/**
	 * 统计公司所有的项目在场总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public int infoPWorkertRS(@Param("companyId")Integer companyId);

	/**
	 * 统计公司所有的项目今日上工总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public List<HjProject> infoPWorkingR(@Param("companyId")Integer companyId);

	/**
	 * 统计公司所有的项目今日上工总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	public List<HjProject> infoPWorkingRS(@Param("companyId")Integer companyId);



	/**
	 * 查询公司下指定范围内的项目列表
	 *
	 * @param companyId 公司Id
	 * @param region 范围（省、市）
	 * @return 项目集合
	 */
	public List<HjProject> selectHjProjectList(@Param("companyId")Integer companyId, @Param("region")String region);



	/**
     * 新增项目
     * 
     * @param hjProject 项目信息
     * @return 结果
     */
	public int insertHjProject(HjProject hjProject);

	/**
	 * 项目分页
	 * @param map
	 * @return
	 */
	List<HjProject> selectProjectPage(Map<String,Object> map);
	/**
     * 修改项目
     * 
     * @param hjProject 项目信息
     * @return 结果
     */
	public int updateHjProject(HjProject hjProject);
	
	/**
     * 删除项目
     * 
     * @param id 项目ID
     * @return 结果
     */
	public int deleteHjProjectById(Integer id);
	
	/**
     * 批量删除项目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectByIds(String[] ids);

	/**
	 * 批量删除项目
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int hiddenHjProjectByIds(String[] ids);

	/** 智慧工地1.0工程概括*/
	/*工程概括*/
	List<HjProject> kanban(@Param("id") Integer id);
	HjProject kanbans(@Param("id") Integer id);
	/*分包单位*/
	List<HjProject> item(@Param("id") Integer id);
	/* 安全文明施工天数*/
	HjProject day(@Param("id") Integer id);
	/** 集团搜索项目 */
	List<HjProject> selectProjects(HjProject hjProject);
	List<HjProject> selectProjectRegion(HjProject hjProject);

	HjProject projectSelect(HjProject hjProject);
}