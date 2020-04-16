package com.hujiang.project.zhgd.lyPersonYunmou.service;

import com.hujiang.project.zhgd.lyPersonYunmou.domain.LyPersonYunmou;
import java.util.List;

/**
 * 云眸人员 服务层
 * 
 * @author hujiang
 * @date 2020-04-14
 */
public interface ILyPersonYunmouService 
{
	/**
     * 查询云眸人员信息
     * 
     * @param id 云眸人员ID
     * @return 云眸人员信息
     */
	public LyPersonYunmou selectLyPersonYunmouById(Integer id);
	
	/**
     * 查询云眸人员列表
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 云眸人员集合
     */
	public List<LyPersonYunmou> selectLyPersonYunmouList(LyPersonYunmou lyPersonYunmou);
	
	/**
     * 新增云眸人员
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 结果
     */
	public int insertLyPersonYunmou(LyPersonYunmou lyPersonYunmou);
	
	/**
     * 修改云眸人员
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 结果
     */
	public int updateLyPersonYunmou(LyPersonYunmou lyPersonYunmou);
		
	/**
     * 删除云眸人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyPersonYunmouByIds(String ids);
	
}
