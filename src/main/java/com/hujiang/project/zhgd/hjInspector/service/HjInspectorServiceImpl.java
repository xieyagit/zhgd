package com.hujiang.project.zhgd.hjInspector.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjInspector.mapper.HjInspectorMapper;
import com.hujiang.project.zhgd.hjInspector.domain.HjInspector;
import com.hujiang.project.zhgd.hjInspector.service.IHjInspectorService;
import com.hujiang.common.support.Convert;

/**
 * 检查记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjInspectorServiceImpl implements IHjInspectorService 
{
	@Autowired
	private HjInspectorMapper hjInspectorMapper;

	/**
     * 查询检查记录信息
     * 
     * @param id 检查记录ID
     * @return 检查记录信息
     */
    @Override
	public HjInspector selectHjInspectorById(Integer id)
	{
	    return hjInspectorMapper.selectHjInspectorById(id);
	}
	
	/**
     * 查询检查记录列表
     * 
     * @param hjInspector 检查记录信息
     * @return 检查记录集合
     */
	@Override
	public List<HjInspector> selectHjInspectorList(HjInspector hjInspector)
	{
	    return hjInspectorMapper.selectHjInspectorList(hjInspector);
	}
	
    /**
     * 新增检查记录
     * 
     * @param hjInspector 检查记录信息
     * @return 结果
     */
	@Override
	public int insertHjInspector(HjInspector hjInspector)
	{
	    return hjInspectorMapper.insertHjInspector(hjInspector);
	}
	
	/**
     * 修改检查记录
     * 
     * @param hjInspector 检查记录信息
     * @return 结果
     */
	@Override
	public int updateHjInspector(HjInspector hjInspector)
	{
	    return hjInspectorMapper.updateHjInspector(hjInspector);
	}

	/**
     * 删除检查记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjInspectorByIds(String ids)
	{
		return hjInspectorMapper.deleteHjInspectorByIds(Convert.toStrArray(ids));
	}
	
}
