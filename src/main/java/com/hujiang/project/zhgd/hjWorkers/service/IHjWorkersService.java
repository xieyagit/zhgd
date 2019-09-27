package com.hujiang.project.zhgd.hjWorkers.service;

import com.hujiang.project.zhgd.hjWorkers.domain.HjWorkers;
import java.util.List;

/**
 * 工人库 服务层
 * 
 * @author hujiang
 * @date 2019-05-16
 */
public interface IHjWorkersService 
{
	/**
     * 查询工人库信息
     * 
     * @param id 工人库ID
     * @return 工人库信息
     */
	public HjWorkers selectHjWorkersById(Integer id);
	
	/**
     * 查询工人库列表
     * 
     * @param hjWorkers 工人库信息
     * @return 工人库集合
     */
	public List<HjWorkers> selectHjWorkersList(HjWorkers hjWorkers);
	
	/**
     * 新增工人库
     * 
     * @param hjWorkers 工人库信息
     * @return 结果
     */
	public int insertHjWorkers(HjWorkers hjWorkers);
	
	/**
     * 修改工人库
     * 
     * @param hjWorkers 工人库信息
     * @return 结果
     */
	public int updateHjWorkers(HjWorkers hjWorkers);
		
	/**
     * 删除工人库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjWorkersByIds(String ids);
	
}
