package com.hujiang.project.zhgd.sbElevatorLocatordata.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbElevatorLocatordata.mapper.SbElevatorLocatordataMapper;
import com.hujiang.project.zhgd.sbElevatorLocatordata.domain.SbElevatorLocatordata;
import com.hujiang.project.zhgd.sbElevatorLocatordata.service.ISbElevatorLocatordataService;
import com.hujiang.common.support.Convert;

/**
 * 升降机GPS定位数据
 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbElevatorLocatordataServiceImpl implements ISbElevatorLocatordataService 
{
	@Autowired
	private SbElevatorLocatordataMapper sbElevatorLocatordataMapper;

	/**
     * 查询升降机GPS定位数据
信息
     * 
     * @param id 升降机GPS定位数据
ID
     * @return 升降机GPS定位数据
信息
     */
    @Override
	public SbElevatorLocatordata selectSbElevatorLocatordataById(Integer id)
	{
	    return sbElevatorLocatordataMapper.selectSbElevatorLocatordataById(id);
	}
	
	/**
     * 查询升降机GPS定位数据
列表
     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 升降机GPS定位数据
集合
     */
	@Override
	public List<SbElevatorLocatordata> selectSbElevatorLocatordataList(SbElevatorLocatordata sbElevatorLocatordata)
	{
	    return sbElevatorLocatordataMapper.selectSbElevatorLocatordataList(sbElevatorLocatordata);
	}
	
    /**
     * 新增升降机GPS定位数据

     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorLocatordata(SbElevatorLocatordata sbElevatorLocatordata)
	{
	    return sbElevatorLocatordataMapper.insertSbElevatorLocatordata(sbElevatorLocatordata);
	}
	
	/**
     * 修改升降机GPS定位数据

     * 
     * @param sbElevatorLocatordata 升降机GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorLocatordata(SbElevatorLocatordata sbElevatorLocatordata)
	{
	    return sbElevatorLocatordataMapper.updateSbElevatorLocatordata(sbElevatorLocatordata);
	}

	/**
     * 删除升降机GPS定位数据
对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorLocatordataByIds(String ids)
	{
		return sbElevatorLocatordataMapper.deleteSbElevatorLocatordataByIds(Convert.toStrArray(ids));
	}
	
}
