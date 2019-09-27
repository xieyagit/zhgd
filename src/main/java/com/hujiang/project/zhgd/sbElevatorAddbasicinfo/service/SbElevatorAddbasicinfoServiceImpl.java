package com.hujiang.project.zhgd.sbElevatorAddbasicinfo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.mapper.SbElevatorAddbasicinfoMapper;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.domain.SbElevatorAddbasicinfo;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.service.ISbElevatorAddbasicinfoService;
import com.hujiang.common.support.Convert;

/**
 * 升降机基本 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Service
public class SbElevatorAddbasicinfoServiceImpl implements ISbElevatorAddbasicinfoService 
{
	@Autowired
	private SbElevatorAddbasicinfoMapper sbElevatorAddbasicinfoMapper;

	/**
     * 查询升降机基本信息
     * 
     * @param id 升降机基本ID
     * @return 升降机基本信息
     */
    @Override
	public SbElevatorAddbasicinfo selectSbElevatorAddbasicinfoById(Integer id)
	{
	    return sbElevatorAddbasicinfoMapper.selectSbElevatorAddbasicinfoById(id);
	}
	
	/**
     * 查询升降机基本列表
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 升降机基本集合
     */
	@Override
	public List<SbElevatorAddbasicinfo> selectSbElevatorAddbasicinfoList(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{
	    return sbElevatorAddbasicinfoMapper.selectSbElevatorAddbasicinfoList(sbElevatorAddbasicinfo);
	}
	
    /**
     * 新增升降机基本
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorAddbasicinfo(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{
	    return sbElevatorAddbasicinfoMapper.insertSbElevatorAddbasicinfo(sbElevatorAddbasicinfo);
	}
	
	/**
     * 修改升降机基本
     * 
     * @param sbElevatorAddbasicinfo 升降机基本信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorAddbasicinfo(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{
	    return sbElevatorAddbasicinfoMapper.updateSbElevatorAddbasicinfo(sbElevatorAddbasicinfo);
	}

	/**
     * 删除升降机基本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorAddbasicinfoByIds(String ids)
	{
		return sbElevatorAddbasicinfoMapper.deleteSbElevatorAddbasicinfoByIds(Convert.toStrArray(ids));
	}
	
}
