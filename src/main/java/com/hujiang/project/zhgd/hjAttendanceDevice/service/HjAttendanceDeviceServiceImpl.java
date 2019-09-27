package com.hujiang.project.zhgd.hjAttendanceDevice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjAttendanceDevice.mapper.HjAttendanceDeviceMapper;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.common.support.Convert;

/**
 * 考勤机设备 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-06
 */
@Service
public class HjAttendanceDeviceServiceImpl implements IHjAttendanceDeviceService 
{
	@Autowired
	private HjAttendanceDeviceMapper hjAttendanceDeviceMapper;

	/**
     * 查询考勤机设备信息
     * 
     * @param id 考勤机设备ID
     * @return 考勤机设备信息
     */
    @Override
	public HjAttendanceDevice selectHjAttendanceDeviceById(Integer id)
	{
	    return hjAttendanceDeviceMapper.selectHjAttendanceDeviceById(id);
	}
	
	/**
     * 查询考勤机设备列表
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 考勤机设备集合
     */
	@Override
	public List<HjAttendanceDevice> selectHjAttendanceDeviceList(HjAttendanceDevice hjAttendanceDevice)
	{
	    return hjAttendanceDeviceMapper.selectHjAttendanceDeviceList(hjAttendanceDevice);
	}
	
    /**
     * 新增考勤机设备
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 结果
     */
	@Override
	public int insertHjAttendanceDevice(HjAttendanceDevice hjAttendanceDevice)
	{
	    return hjAttendanceDeviceMapper.insertHjAttendanceDevice(hjAttendanceDevice);
	}
	
	/**
     * 修改考勤机设备
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 结果
     */
	@Override
	public int updateHjAttendanceDevice(HjAttendanceDevice hjAttendanceDevice)
	{
	    return hjAttendanceDeviceMapper.updateHjAttendanceDevice(hjAttendanceDevice);
	}
	/**
	 * 修改考勤机最后连接时间
	 *
	 * @param hjAttendanceDevice 考勤机设备信息
	 * @return 结果
	 */
	public int updateHjAttendanceDeviceTwo(HjAttendanceDevice hjAttendanceDevice){
		return hjAttendanceDeviceMapper.updateHjAttendanceDeviceTwo(hjAttendanceDevice);
	}

	/**
     * 删除考勤机设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjAttendanceDeviceByIds(String ids)
	{
		return hjAttendanceDeviceMapper.deleteHjAttendanceDeviceByIds(Convert.toStrArray(ids));
	}
	
}
