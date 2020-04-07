package com.hujiang.project.zhgd.hjAttendanceLocation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjAttendanceLocation.mapper.HjAttendanceLocationMapper;
import com.hujiang.project.zhgd.hjAttendanceLocation.domain.HjAttendanceLocation;
import com.hujiang.project.zhgd.hjAttendanceLocation.service.IHjAttendanceLocationService;
import com.hujiang.common.support.Convert;

/**
 * 打卡定位 服务层实现
 * 
 * @author hujiang
 * @date 2020-02-26
 */
@Service
public class HjAttendanceLocationServiceImpl implements IHjAttendanceLocationService 
{
	@Autowired
	private HjAttendanceLocationMapper hjAttendanceLocationMapper;

	/**
     * 查询打卡定位信息
     * 
     * @param id 打卡定位ID
     * @return 打卡定位信息
     */
    @Override
	public HjAttendanceLocation selectHjAttendanceLocationById(Integer id)
	{
	    return hjAttendanceLocationMapper.selectHjAttendanceLocationById(id);
	}
	
	/**
     * 查询打卡定位列表
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 打卡定位集合
     */
	@Override
	public List<HjAttendanceLocation> selectHjAttendanceLocationList(HjAttendanceLocation hjAttendanceLocation)
	{
	    return hjAttendanceLocationMapper.selectHjAttendanceLocationList(hjAttendanceLocation);
	}
	
    /**
     * 新增打卡定位
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 结果
     */
	@Override
	public int insertHjAttendanceLocation(HjAttendanceLocation hjAttendanceLocation)
	{
	    return hjAttendanceLocationMapper.insertHjAttendanceLocation(hjAttendanceLocation);
	}
	
	/**
     * 修改打卡定位
     * 
     * @param hjAttendanceLocation 打卡定位信息
     * @return 结果
     */
	@Override
	public int updateHjAttendanceLocation(HjAttendanceLocation hjAttendanceLocation)
	{
	    return hjAttendanceLocationMapper.updateHjAttendanceLocation(hjAttendanceLocation);
	}

	/**
     * 删除打卡定位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjAttendanceLocationByIds(String ids)
	{
		return hjAttendanceLocationMapper.deleteHjAttendanceLocationByIds(Convert.toStrArray(ids));
	}
	
}
