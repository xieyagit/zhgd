package com.hujiang.project.zhgd.zhNode.service;

import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import com.hujiang.project.zhgd.zhNode.domain.ZhNodeWithProgress;
import com.hujiang.project.zhgd.zhNode.domain.ZhProgressNode;
import java.util.List;

/**
 * 进度节点 服务层
 * 
 * @author hujiang
 * @date 2019-08-06
 */
public interface IZhProgressNodeService 
{


	/**
	 * 查询计划关联节点列表
	 * @param zhProgressNode
	 * @return
	 */
	public List<ZhNodeWithProgress> selectZhNodeProgressList(ZhProgressNode zhProgressNode);
	/**
     * 查询进度节点信息
     * 
     * @param id 进度节点ID
     * @return 进度节点信息
     */
	public ZhProgressNode selectZhProgressNodeById(Integer id);
	
	/**
     * 查询进度节点列表
     * 
     * @param zhProgressNode 进度节点信息
     * @return 进度节点集合
     */
	public List<ZhProgressNode> selectZhProgressNodeList(ZhProgressNode zhProgressNode);
	
	/**
     * 新增进度节点
     * 
     * @param zhProgressNode 进度节点信息
     * @return 结果
     */
	public int insertZhProgressNode(ZhProgressNode zhProgressNode);
	
	/**
     * 修改进度节点
     * 
     * @param zhProgressNode 进度节点信息
     * @return 结果
     */
	public int updateZhProgressNode(ZhProgressNode zhProgressNode);
		
	/**
     * 删除进度节点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteZhProgressNodeByIds(String ids);

	/**
	 * 根据id删除进度节点对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteZhProgressNodeById(int id);

	/**
	 * 根据节点id删除进度节点对象
	 *
	 * @param nodeId 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteZhProgressNodeByNodeId(int nodeId);

	
}
