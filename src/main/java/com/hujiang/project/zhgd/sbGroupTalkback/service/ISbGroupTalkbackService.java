package com.hujiang.project.zhgd.sbGroupTalkback.service;

import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import java.util.List;

/**
 * 集团对讲账号 服务层
 * 
 * @author hujiang
 * @date 2019-12-05
 */
public interface ISbGroupTalkbackService 
{
	/**
     * 查询集团对讲账号信息
     * 
     * @param id 集团对讲账号ID
     * @return 集团对讲账号信息
     */
	public SbGroupTalkback selectSbGroupTalkbackById(Integer id);
	
	/**
     * 查询集团对讲账号列表
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 集团对讲账号集合
     */
	public List<SbGroupTalkback> selectSbGroupTalkbackList(SbGroupTalkback sbGroupTalkback);
	
	/**
     * 新增集团对讲账号
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 结果
     */
	public int insertSbGroupTalkback(SbGroupTalkback sbGroupTalkback);
	
	/**
     * 修改集团对讲账号
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 结果
     */
	public int updateSbGroupTalkback(SbGroupTalkback sbGroupTalkback);
		
	/**
     * 删除集团对讲账号信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbGroupTalkbackByIds(String ids);

	/**
	 * 集团对讲机列表
	 */
	public List<SbGroupTalkback> getAccountList(SbGroupTalkback sbGroupTalkback);
}
