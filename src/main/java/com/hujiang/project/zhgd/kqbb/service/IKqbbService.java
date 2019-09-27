package com.hujiang.project.zhgd.kqbb.service;

import com.hujiang.project.zhgd.kqbb.domain.BG;
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;
import java.util.List;

/**
 * 考勤报 服务层
 * 
 * @author hujiang
 * @date 2019-07-05
 */
public interface IKqbbService 
{
	/**
     * 查询考勤报信息
     * 
     * @param projectId 考勤报ID
     * @return 考勤报信息
     */
	public Kqbb selectKqbbById(Integer projectId);

	public List<BG> selectKqbbListBb(Integer projectId);
	/**
     * 查询考勤报列表
     * 
     * @param kqbb 考勤报信息
     * @return 考勤报集合
     */
	public List<Kqbb> selectKqbbList(Kqbb kqbb);
	
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
     * 删除考勤报信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteKqbbByIds(String ids);
	
}
