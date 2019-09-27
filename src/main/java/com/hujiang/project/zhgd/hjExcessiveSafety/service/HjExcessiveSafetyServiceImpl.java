package com.hujiang.project.zhgd.hjExcessiveSafety.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjExcessiveSafety.mapper.HjExcessiveSafetyMapper;
import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import com.hujiang.project.zhgd.hjExcessiveSafety.service.IHjExcessiveSafetyService;
import com.hujiang.common.support.Convert;

/**
 * 巡检短息记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-29
 */
@Service
public class HjExcessiveSafetyServiceImpl implements IHjExcessiveSafetyService 
{
	@Autowired
	private HjExcessiveSafetyMapper hjExcessiveSafetyMapper;

	/**
     * 查询巡检短息记录信息
     * 
     * @param id 巡检短息记录ID
     * @return 巡检短息记录信息
     */
    @Override
	public HjExcessiveSafety selectHjExcessiveSafetyById(Integer id)
	{
	    return hjExcessiveSafetyMapper.selectHjExcessiveSafetyById(id);
	}
	
	/**
     * 查询巡检短息记录列表
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 巡检短息记录集合
     */
	@Override
	public List<HjExcessiveSafety> selectHjExcessiveSafetyList(HjExcessiveSafety hjExcessiveSafety)
	{
	    return hjExcessiveSafetyMapper.selectHjExcessiveSafetyList(hjExcessiveSafety);
	}
	
    /**
     * 新增巡检短息记录
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 结果
     */
	@Override
	public int insertHjExcessiveSafety(HjExcessiveSafety hjExcessiveSafety)
	{
	    return hjExcessiveSafetyMapper.insertHjExcessiveSafety(hjExcessiveSafety);
	}
	
	/**
     * 修改巡检短息记录
     * 
     * @param hjExcessiveSafety 巡检短息记录信息
     * @return 结果
     */
	@Override
	public int updateHjExcessiveSafety(HjExcessiveSafety hjExcessiveSafety)
	{
	    return hjExcessiveSafetyMapper.updateHjExcessiveSafety(hjExcessiveSafety);
	}

	/**
     * 删除巡检短息记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjExcessiveSafetyByIds(String ids)
	{
		return hjExcessiveSafetyMapper.deleteHjExcessiveSafetyByIds(Convert.toStrArray(ids));
	}
	
}
