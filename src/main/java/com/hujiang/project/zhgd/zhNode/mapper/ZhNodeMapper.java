package com.hujiang.project.zhgd.zhNode.mapper;

import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import java.util.List;

/**
 * 节点计划详情 数据层
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public interface ZhNodeMapper 
{
	/**
     * 查询节点计划详情信息
     * 
     * @param id 节点计划ID
     * @return 节点计划详情信息
     */
	public ZhNode selectZhNodeById(Integer id);

	/**
	 * 查询节点计划详情信息
	 *
	 * @param progressId 计划ID
	 * @return 节点计划详情信息
	 */
	public ZhNode selectZhNodeByProgressId(Integer progressId);


	/**
     * 查询节点计划详情列表
     * 
     * @param zhNode 节点计划详情信息
     * @return 节点计划详情集合
     */
	public List<ZhNode> selectZhNodeList(ZhNode zhNode);
	
	/**
     * 新增节点计划详情
     * 
     * @param zhNode 节点计划详情信息
     * @return 结果
     */
	public int insertZhNode(ZhNode zhNode);
	
	/**
     * 修改节点计划详情
     * 
     * @param zhNode 节点计划详情信息
     * @return 结果
     */
	public int updateZhNode(ZhNode zhNode);
	
	/**
     * 删除节点计划详情
     * 
     * @param id 节点计划详情ID
     * @return 结果
     */
	public int deleteZhNodeById(Integer id);

	
}