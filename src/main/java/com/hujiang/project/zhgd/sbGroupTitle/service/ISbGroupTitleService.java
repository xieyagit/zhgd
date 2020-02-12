package com.hujiang.project.zhgd.sbGroupTitle.service;

import com.hujiang.project.zhgd.sbGroupTitle.domain.SbGroupTitle;
import java.util.List;

/**
 * 项目标题 服务层
 * 
 * @author hujiang
 * @date 2020-01-03
 */
public interface ISbGroupTitleService 
{
	/**
     * 查询项目标题信息
     * 
     * @param id 项目标题ID
     * @return 项目标题信息
     */
	public SbGroupTitle selectSbGroupTitleById(Integer id);
	
	/**
     * 查询项目标题列表
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 项目标题集合
     */
	public List<SbGroupTitle> selectSbGroupTitleList(SbGroupTitle sbGroupTitle);
	
	/**
     * 新增项目标题
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 结果
     */
	public int insertSbGroupTitle(SbGroupTitle sbGroupTitle);
	
	/**
     * 修改项目标题
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 结果
     */
	public int updateSbGroupTitle(SbGroupTitle sbGroupTitle);
		
	/**
     * 删除项目标题信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbGroupTitleByIds(String ids);
	
}
