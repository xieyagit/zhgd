package com.hujiang.project.zhgd.kqbb.mapper;

import com.hujiang.project.zhgd.kqbb.domain.BG;
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;
import java.util.List;	

/**
 * 考勤报 数据层
 * 
 * @author hujiang
 * @date 2019-07-05
 */
public interface KqbbMapper 
{
	/**
     * 查询考勤报信息
     * 
     * @param projectId 考勤报ID
     * @return 考勤报信息
     */
	public Kqbb selectKqbbById(Integer projectId);
	
	/**
     * 查询考勤报列表
     * 
     * @param kqbb 考勤报信息
     * @return 考勤报集合
     */
	public List<Kqbb> selectKqbbList(Kqbb kqbb);

	public List<BG> selectKqbbListBb(Integer projectId);

	/**
     * 新增考勤报
     * 
     * @param kqbb 考勤报信息
     * @return 结果
     */
	public int insertKqbb(Kqbb kqbb);
	
	/**
     * 修改考勤报
     * 
     * @param kqbb 考勤报信息
     * @return 结果
     */
	public int updateKqbb(Kqbb kqbb);
	
	/**
     * 删除考勤报
     * 
     * @param projectId 考勤报ID
     * @return 结果
     */
	public int deleteKqbbById(Integer projectId);
	
	/**
     * 批量删除考勤报
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteKqbbByIds(String[] projectIds);
	
}