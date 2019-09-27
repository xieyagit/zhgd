package com.hujiang.project.zhgd.dustEmissionThresholdValue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.mapper.DustEmissionThresholdValueMapper;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.service.IDustEmissionThresholdValueService;
import com.hujiang.common.support.Convert;

/**
 * 项目扬尘设备阈值 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-08
 */
@Service
public class DustEmissionThresholdValueServiceImpl implements IDustEmissionThresholdValueService 
{
	@Autowired
	private DustEmissionThresholdValueMapper dustEmissionThresholdValueMapper;

	@Override
	public DustEmissionThresholdValue selectDustEmissionThresholdById(int projectId) {
		return dustEmissionThresholdValueMapper.selectDustEmissionThresholdById(projectId);
	}

	/**
     * 查询项目扬尘设备阈值信息
     * 
     * @param id 项目扬尘设备阈值ID
     * @return 项目扬尘设备阈值信息
     */
    @Override
	public DustEmissionThresholdValue selectDustEmissionThresholdValueById(Integer id)
	{
	    return dustEmissionThresholdValueMapper.selectDustEmissionThresholdValueById(id);
	}
	
	/**
     * 查询项目扬尘设备阈值列表
     * 
     * @param dustEmissionThresholdValue 项目扬尘设备阈值信息
     * @return 项目扬尘设备阈值集合
     */
	@Override
	public List<DustEmissionThresholdValue> selectDustEmissionThresholdValueList(DustEmissionThresholdValue dustEmissionThresholdValue)
	{
	    return dustEmissionThresholdValueMapper.selectDustEmissionThresholdValueList(dustEmissionThresholdValue);
	}
	
    /**
     * 新增项目扬尘设备阈值
     * 
     * @param dustEmissionThresholdValue 项目扬尘设备阈值信息
     * @return 结果
     */
	@Override
	public int insertDustEmissionThresholdValue(DustEmissionThresholdValue dustEmissionThresholdValue)
	{
	    return dustEmissionThresholdValueMapper.insertDustEmissionThresholdValue(dustEmissionThresholdValue);
	}
	
	/**
     * 修改项目扬尘设备阈值
     * 
     * @param dustEmissionThresholdValue 项目扬尘设备阈值信息
     * @return 结果
     */
	@Override
	public int updateDustEmissionThresholdValue(DustEmissionThresholdValue dustEmissionThresholdValue)
	{
	    return dustEmissionThresholdValueMapper.updateDustEmissionThresholdValue(dustEmissionThresholdValue);
	}

	/**
     * 删除项目扬尘设备阈值对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDustEmissionThresholdValueByIds(String ids)
	{
		return dustEmissionThresholdValueMapper.deleteDustEmissionThresholdValueByIds(Convert.toStrArray(ids));
	}
	
}
