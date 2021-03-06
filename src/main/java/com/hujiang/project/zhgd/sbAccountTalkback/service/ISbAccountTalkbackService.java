package com.hujiang.project.zhgd.sbAccountTalkback.service;

import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import java.util.List;
import java.util.Map;

/**
 * 集团对讲机列 服务层
 * 
 * @author hujiang
 * @date 2019-12-05
 */
public interface ISbAccountTalkbackService 
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
     * 删除集团对讲机列信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbAccountTalkbackByIds(String ids);

	public List<SbAccountTalkback> getAccountListPage(Map<String,String> map);
}
