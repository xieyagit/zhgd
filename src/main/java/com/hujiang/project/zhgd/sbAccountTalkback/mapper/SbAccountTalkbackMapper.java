package com.hujiang.project.zhgd.sbAccountTalkback.mapper;

import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import java.util.List;	

/**
 * 集团对讲机列 数据层
 * 
 * @author hujiang
 * @date 2019-12-05
 */
public interface SbAccountTalkbackMapper 
{
	/**
     * 查询集团对讲机列信息
     * 
     * @param id 集团对讲机列ID
     * @return 集团对讲机列信息
     */
	public SbAccountTalkback selectSbAccountTalkbackById(Integer id);
	
	/**
     * 查询集团对讲机列列表
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 集团对讲机列集合
     */
	public List<SbAccountTalkback> selectSbAccountTalkbackList(SbAccountTalkback sbAccountTalkback);
	
	/**
     * 新增集团对讲机列
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 结果
     */
	public int insertSbAccountTalkback(SbAccountTalkback sbAccountTalkback);
	
	/**
     * 修改集团对讲机列
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 结果
     */
	public int updateSbAccountTalkback(SbAccountTalkback sbAccountTalkback);
	
	/**
     * 删除集团对讲机列
     * 
     * @param id 集团对讲机列ID
     * @return 结果
     */
	public int deleteSbAccountTalkbackById(Integer id);
	
	/**
     * 批量删除集团对讲机列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbAccountTalkbackByIds(String[] ids);
	
}