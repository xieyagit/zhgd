package com.hujiang.project.zhgd.sbManufacturer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbManufacturer.mapper.SbManufacturerMapper;
import com.hujiang.project.zhgd.sbManufacturer.domain.SbManufacturer;
import com.hujiang.project.zhgd.sbManufacturer.service.ISbManufacturerService;
import com.hujiang.common.support.Convert;

/**
 * 设备厂商名称 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-24
 */
@Service
public class SbManufacturerServiceImpl implements ISbManufacturerService 
{
	@Autowired
	private SbManufacturerMapper sbManufacturerMapper;

	/**
     * 查询设备厂商名称信息
     * 
     * @param id 设备厂商名称ID
     * @return 设备厂商名称信息
     */
    @Override
	public SbManufacturer selectSbManufacturerById(Integer id)
	{
	    return sbManufacturerMapper.selectSbManufacturerById(id);
	}
	
	/**
     * 查询设备厂商名称列表
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 设备厂商名称集合
     */
	@Override
	public List<SbManufacturer> selectSbManufacturerList(SbManufacturer sbManufacturer)
	{
	    return sbManufacturerMapper.selectSbManufacturerList(sbManufacturer);
	}
	
    /**
     * 新增设备厂商名称
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 结果
     */
	@Override
	public int insertSbManufacturer(SbManufacturer sbManufacturer)
	{
	    return sbManufacturerMapper.insertSbManufacturer(sbManufacturer);
	}
	
	/**
     * 修改设备厂商名称
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 结果
     */
	@Override
	public int updateSbManufacturer(SbManufacturer sbManufacturer)
	{
	    return sbManufacturerMapper.updateSbManufacturer(sbManufacturer);
	}

	/**
     * 删除设备厂商名称对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbManufacturerByIds(String ids)
	{
		return sbManufacturerMapper.deleteSbManufacturerByIds(Convert.toStrArray(ids));
	}
	
}
