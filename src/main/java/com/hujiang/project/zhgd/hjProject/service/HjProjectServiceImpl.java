package com.hujiang.project.zhgd.hjProject.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjProject.domain.HjCompanyProjectTemp;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjProject.mapper.HjProjectMapper;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.common.support.Convert;

/**
 * 项目 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjProjectServiceImpl implements IHjProjectService 
{
	@Autowired
	private HjProjectMapper hjProjectMapper;

	/**
     * 查询项目信息
     * 
     * @param id 项目ID
     * @return 项目信息
     */
    @Override
	public HjProject selectHjProjectById(Integer id)
	{
	    return hjProjectMapper.selectHjProjectById(id);
	}
	
	/**
     * 查询项目列表
     * 
     * @param hjProject 项目信息
     * @return 项目集合
     */
	@Override
	public List<HjProject> selectHjProjectList(HjProject hjProject)
	{
	    return hjProjectMapper.selectHjProjectList(hjProject);
	}
	/**
	 * 查询指定公司下的项目列表
	 *
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	@Override
	public List<HjProject> selectHjProjectListTwo(Integer companyId){
		return hjProjectMapper.selectHjProjectListTwo(companyId);
	}

	/**
	 * 查询公司下指定范围内的项目列表
	 *
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	@Override
	/*public List<HjProject> selectHjProjectListR(Integer companyId, String region){
		return hjProjectMapper.selectHjProjectListR(companyId,region);
	}*/

	public List<HjProject> selectHjProjectListR(Integer companyId, String region){
		return hjProjectMapper.selectHjProjectListR(companyId,region);
	}

	/**
	 * 统计公司所有的项目name的总和
	 * @param name 字段名
	 * @param companyId 公司Id
	 * @param region 地区编号
	 * @return 项目集合
	 */
	@Override
	public double infoHjProjectR(String name, Integer companyId, String region) {
		return hjProjectMapper.infoHjProjectR(name,companyId,region);
	}

	/**
	 * 统计公司所有的项目参建单位的总和
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	@Override
	public int infoConstructionR(@Param("companyId")Integer companyId) {
		return hjProjectMapper.infoConstructionR(companyId);
	}

	/**
	 * 统计公司所有的项目在场总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	@Override
	public int infoPWorkertR(@Param("companyId")Integer companyId){
		return hjProjectMapper.infoPWorkertR(companyId);
	}

	/**
	 * 统计公司所有的项目今日上工总人数
	 * @param companyId 公司Id
	 * @return 项目集合
	 */
	@Override
	public int infoPWorkingR(@Param("companyId")Integer companyId){
		return hjProjectMapper.infoPWorkingR(companyId);
	}

	/**
	 * 项目分页
	 * @param hjProject
	 * @param companyId
	 * @return
	 */
	@Override
	public List<HjProject> selectProjectPage(HjProject hjProject, Integer companyId){
		Map<String,Object> map = new HashMap<>();
		map.put("hj",hjProject);
		map.put("cid",companyId);
		return hjProjectMapper.selectProjectPage(map);
	}
    /**
     * 新增项目
     * 
     * @param hjProject 项目信息
     * @return 结果
     */
	@Override
	public int insertHjProject(HjProject hjProject)
	{
	    return hjProjectMapper.insertHjProject(hjProject);
	}
	
	/**
     * 修改项目
     * 
     * @param hjProject 项目信息
     * @return 结果
     */
	@Override
	public int updateHjProject(HjProject hjProject)
	{
	    return hjProjectMapper.updateHjProject(hjProject);
	}

	/**
     * 删除项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjProjectByIds(String ids)
	{
		return hjProjectMapper.deleteHjProjectByIds(Convert.toStrArray(ids));
	}
	/**
	 * 批量删除项目
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int hiddenHjProjectByIds(String ids)
	{
		return hjProjectMapper.hiddenHjProjectByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询项目详情
	 * @param projectId
	 * @return
	 */
	@Override
	public Map<String, Object> getProject(Integer projectId) {
		HjProject hjProject = hjProjectMapper.selectHjProjectById(projectId);
		return AjaxResult.success(hjProject);
	}

	/** 智慧工地1.0工程概括*/
	@Override
	public List<HjProject> kanban(Integer id){
		return hjProjectMapper.kanban(id);
	}
	public HjProject kanbans(Integer id){
		return hjProjectMapper.kanbans(id);
	}

	@Override
	public List<HjProject> item(Integer id) {
		return hjProjectMapper.item(id);
	}

	/* 安全文明施工天数*/
	@Override
	public HjProject day(Integer id){
		return hjProjectMapper.day(id);
	}

	/** 集团搜索项目 */
	public List<HjProject> selectProjects(HjProject hjProject){
		return hjProjectMapper.selectProjects(hjProject);
	}
    public List<HjProject> selectProjectRegion(HjProject hjProject){
        return hjProjectMapper.selectProjectRegion(hjProject);
    }

    public HjProject projectSelect(HjProject hjProject){
	    return hjProjectMapper.projectSelect(hjProject);
    }

}
