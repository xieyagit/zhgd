package com.hujiang.project.zhgd.zhNode.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.zhNode.mapper.ZhProgressNodeMapper;
import com.hujiang.project.zhgd.zhNode.domain.ZhProgressNode;
import com.hujiang.project.zhgd.zhNode.service.IZhProgressNodeService;
import com.hujiang.common.support.Convert;

/**
 * 进度节点 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-06
 */
@Service
public class ZhProgressNodeServiceImpl implements IZhProgressNodeService 
{
	@Autowired
	private ZhProgressNodeMapper zhProgressNodeMapper;

	/**
     * 查询进度节点信息
     * 
     * @param id 进度节点ID
     * @return 进度节点信息
     */
    @Override
	public ZhProgressNode selectZhProgressNodeById(Integer id)
	{
	    return zhProgressNodeMapper.selectZhProgressNodeById(id);
	}
	
	/**
     * 查询进度节点列表
     * 
     * @param zhProgressNode 进度节点信息
     * @return 进度节点集合
     */
	@Override
	public List<ZhProgressNode> selectZhProgressNodeList(ZhProgressNode zhProgressNode)
	{
	    return zhProgressNodeMapper.selectZhProgressNodeList(zhProgressNode);
	}
	
    /**
     * 新增进度节点
     * 
     * @param zhProgressNode 进度节点信息
     * @return 结果
     */
	@Override
	public int insertZhProgressNode(ZhProgressNode zhProgressNode)
	{
	    return zhProgressNodeMapper.insertZhProgressNode(zhProgressNode);
	}
	
	/**
     * 修改进度节点
     * 
     * @param zhProgressNode 进度节点信息
     * @return 结果
     */
	@Override
	public int updateZhProgressNode(ZhProgressNode zhProgressNode)
	{
	    return zhProgressNodeMapper.updateZhProgressNode(zhProgressNode);
	}

	/**
     * 删除进度节点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteZhProgressNodeByIds(String ids)
	{
		return zhProgressNodeMapper.deleteZhProgressNodeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据id删除进度节点对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteZhProgressNodeById(int id)
	{
		return zhProgressNodeMapper.deleteZhProgressNodeById(id);
	}
}
