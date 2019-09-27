package com.hujiang.project.zhgd.zhNode.mapper;

import com.hujiang.project.zhgd.zhNode.domain.ZhProgressNode;
import java.util.List;	

/**
 * 进度节点 数据层
 * 
 * @author hujiang
 * @date 2019-08-06
 */
public interface ZhProgressNodeMapper 
{
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
     * 删除进度节点
     * 
     * @param id 进度节点ID
     * @return 结果
     */
	public int deleteZhProgressNodeById(Integer id);
	
	/**
     * 批量删除进度节点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteZhProgressNodeByIds(String[] ids);
	
}