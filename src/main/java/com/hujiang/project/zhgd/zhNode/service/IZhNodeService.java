package com.hujiang.project.zhgd.zhNode.service;

import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import java.util.List;

/**
 * 节点计划详情 服务层
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public interface IZhNodeService 
{
	/**
     * 查询节点计划详情信息
     * 
     * @param id 节点计划详情ID
     * @return 节点计划详情信息
     */
	public ZhNode selectZhNodeById(Integer id);


	/**
	 * 查询节点计划详情信息
	 *
	 * @param progressId 计划id
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
	 * 查询关键节点计划列表
	 *
	 * @return 节点计划详情信息
	 */
	public List<ZhNode> selectCruxZhNode(ZhNode zhNode);

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
	 * 删除节点计划详情信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteZhNodeById(int id);

	/**
	 * 查询即将开始节点列表
	 * @param zhNode
	 * @return
	 */
	public List<ZhNode> selectBeginZhNode(ZhNode zhNode);

	/**
	 * 查询即将完成节点列表
	 * @param zhNode
	 * @return
	 */
	public List<ZhNode> selectEndZhNode(ZhNode zhNode);



	/**
	 * 查询预警节点列表
	 * @param zhNode
	 * @return
	 */
	public List<ZhNode> selectWarningZhNode(ZhNode zhNode);


	/**
	 * 条件搜索节点列表
	 * @param node
	 * @return
	 */
	public List<ZhNode> selectNormalStartZhNode(ZhNode node);

	public List<ZhNode> selectNoStartZhNode(ZhNode node);

	public List<ZhNode> selectDelayStartZhNode(ZhNode node);

	public List<ZhNode> selectDelayEndZhNode(ZhNode node);

	public List<ZhNode> selectNormalEndZhNode(ZhNode node);

	public List<ZhNode> selectNoEndZhNode(ZhNode node);

	public List<ZhNode> selectAdvanceStartZhNode(ZhNode node);
}
