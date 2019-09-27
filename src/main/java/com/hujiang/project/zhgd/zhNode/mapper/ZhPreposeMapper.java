package com.hujiang.project.zhgd.zhNode.mapper;

import com.hujiang.project.zhgd.zhNode.domain.ZhPrepose;
import java.util.List;	

/**
 * 节点关联 数据层
 * 
 * @author hujiang
 * @date 2019-08-05
 */
public interface ZhPreposeMapper 
{
	/**
     * 查询节点关联信息
     * 
     * @param id 节点关联ID
     * @return 节点关联信息
     */
	public ZhPrepose selectZhPreposeById(Integer id);
	
	/**
     * 查询节点关联列表
     * 
     * @param zhPrepose 节点关联信息
     * @return 节点关联集合
     */
	public List<ZhPrepose> selectZhPreposeList(ZhPrepose zhPrepose);
	
	/**
     * 新增节点关联
     * 
     * @param zhPrepose 节点关联信息
     * @return 结果
     */
	public int insertZhPrepose(ZhPrepose zhPrepose);
	
	/**
     * 修改节点关联
     * 
     * @param zhPrepose 节点关联信息
     * @return 结果
     */
	public int updateZhPrepose(ZhPrepose zhPrepose);
	
	/**
     * 删除节点关联
     * 
     * @param id 节点关联ID
     * @return 结果
     */
	public int deleteZhPreposeById(Integer id);
	
}