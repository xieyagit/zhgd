package com.hujiang.project.zhgd.sbDeviceimei.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbDeviceimei.mapper.SbDeviceimeiMapper;
import com.hujiang.project.zhgd.sbDeviceimei.domain.SbDeviceimei;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import com.hujiang.common.support.Convert;

/**
 * 设备编号
 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Service
public class SbDeviceimeiServiceImpl implements ISbDeviceimeiService 
{
	@Autowired
	private SbDeviceimeiMapper sbDeviceimeiMapper;

	@Override
	public List<String> selectDeviceimeiListAll() {
		return sbDeviceimeiMapper.selectDeviceimeiListAll();
	}

	/**
     * 查询设备编号
信息
     * 
     * @param id 设备编号
ID
     * @return 设备编号
信息
     */
    @Override
	public SbDeviceimei selectSbDeviceimeiById(Integer id)
	{
	    return sbDeviceimeiMapper.selectSbDeviceimeiById(id);
	}
	
	/**
     * 查询设备编号
列表
     * 
     * @param sbDeviceimei 设备编号
信息
     * @return 设备编号
集合
     */
	@Override
	public List<SbDeviceimei> selectSbDeviceimeiList(SbDeviceimei sbDeviceimei)
	{
	    return sbDeviceimeiMapper.selectSbDeviceimeiList(sbDeviceimei);
	}
	
    /**
     * 新增设备编号

     * 
     * @param sbDeviceimei 设备编号
信息
     * @return 结果
     */
	@Override
	public int insertSbDeviceimei(SbDeviceimei sbDeviceimei)
	{
	    return sbDeviceimeiMapper.insertSbDeviceimei(sbDeviceimei);
	}
	
	/**
     * 修改设备编号

     * 
     * @param sbDeviceimei 设备编号
信息
     * @return 结果
     */
	@Override
	public int updateSbDeviceimei(SbDeviceimei sbDeviceimei)
	{
	    return sbDeviceimeiMapper.updateSbDeviceimei(sbDeviceimei);
	}

	/**
     * 删除设备编号
对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeviceimeiByIds(String ids)
	{
		return sbDeviceimeiMapper.deleteSbDeviceimeiByIds(Convert.toStrArray(ids));
	}
	
}
