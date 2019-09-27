package com.hujiang.project.zhgd.zhNode.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.zhNode.mapper.ZhPreposeMapper;
import com.hujiang.project.zhgd.zhNode.domain.ZhPrepose;
import com.hujiang.project.zhgd.zhNode.service.IZhPreposeService;
import com.hujiang.common.support.Convert;

/**
 * 节点关联 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-05
 */
@Service
public class ZhPreposeServiceImpl implements IZhPreposeService 
{
	@Autowired
	private ZhPreposeMapper zhPreposeMapper;

	/**
     * 查询节点关联信息
     * 
     * @param id 节点关联ID
     * @return 节点关联信息
     */
    @Override
	public ZhPrepose selectZhPreposeById(Integer id)
	{
	    return zhPreposeMapper.selectZhPreposeById(id);
	}
	
	/**
     * 查询节点关联列表
     * 
     * @param zhPrepose 节点关联信息
     * @return 节点关联集合
     */
	@Override
	public List<ZhPrepose> selectZhPreposeList(ZhPrepose zhPrepose)
	{
	    return zhPreposeMapper.selectZhPreposeList(zhPrepose);
	}
	
    /**
     * 新增节点关联
     * 
     * @param zhPrepose 节点关联信息
     * @return 结果
     */
	@Override
	public int insertZhPrepose(ZhPrepose zhPrepose)
	{
	    return zhPreposeMapper.insertZhPrepose(zhPrepose);
	}
	
	/**
     * 修改节点关联
     * 
     * @param zhPrepose 节点关联信息
     * @return 结果
     */
	@Override
	public int updateZhPrepose(ZhPrepose zhPrepose)
	{
	    return zhPreposeMapper.updateZhPrepose(zhPrepose);
	}


	/**
	 * 删除节点关联对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteZhPreposeById(int id)
	{
		return zhPreposeMapper.deleteZhPreposeById(id);
	}
	
}
