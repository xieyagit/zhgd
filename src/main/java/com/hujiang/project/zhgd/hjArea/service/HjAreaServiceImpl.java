package com.hujiang.project.zhgd.hjArea.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjArea.mapper.HjAreaMapper;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.common.support.Convert;

/**
 * 城市 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjAreaServiceImpl implements IHjAreaService 
{
	@Autowired
	private HjAreaMapper hjAreaMapper;

	/**
     * 查询城市信息
     * 
     * @param id 城市ID
     * @return 城市信息
     */
    @Override
	public HjArea selectHjAreaById(Long id)
	{
	    return hjAreaMapper.selectHjAreaById(id);
	}
	
	/**
     * 查询城市列表
     * 
     * @param hjArea 城市信息
     * @return 城市集合
     */
	@Override
	public List<HjArea> selectHjAreaList(HjArea hjArea)
	{
	    return hjAreaMapper.selectHjAreaList(hjArea);
	}
	
    /**
     * 新增城市
     * 
     * @param hjArea 城市信息
     * @return 结果
     */
	@Override
	public int insertHjArea(HjArea hjArea)
	{
	    return hjAreaMapper.insertHjArea(hjArea);
	}
	
	/**
     * 修改城市
     * 
     * @param hjArea 城市信息
     * @return 结果
     */
	@Override
	public int updateHjArea(HjArea hjArea)
	{
	    return hjAreaMapper.updateHjArea(hjArea);
	}

	/**
     * 删除城市对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjAreaByIds(String ids)
	{
		return hjAreaMapper.deleteHjAreaByIds(Convert.toStrArray(ids));
	}
	
}
