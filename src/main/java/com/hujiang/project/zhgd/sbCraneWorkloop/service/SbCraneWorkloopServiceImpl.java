package com.hujiang.project.zhgd.sbCraneWorkloop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbCraneWorkloop.mapper.SbCraneWorkloopMapper;
import com.hujiang.project.zhgd.sbCraneWorkloop.domain.SbCraneWorkloop;
import com.hujiang.project.zhgd.sbCraneWorkloop.service.ISbCraneWorkloopService;
import com.hujiang.common.support.Convert;

/**
 * 塔机工作循环数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service@Transactional
public class SbCraneWorkloopServiceImpl implements ISbCraneWorkloopService 
{
	@Autowired
	private SbCraneWorkloopMapper sbCraneWorkloopMapper;

	/**
     * 查询塔机工作循环数据信息
     * 
     * @param id 塔机工作循环数据ID
     * @return 塔机工作循环数据信息
     */
    @Override
	public SbCraneWorkloop selectSbCraneWorkloopById(Integer id)
	{
	    return sbCraneWorkloopMapper.selectSbCraneWorkloopById(id);
	}
	
	/**
     * 查询塔机工作循环数据列表
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 塔机工作循环数据集合
     */
	@Override
	public List<SbCraneWorkloop> selectSbCraneWorkloopList(SbCraneWorkloop sbCraneWorkloop)
	{
	    return sbCraneWorkloopMapper.selectSbCraneWorkloopList(sbCraneWorkloop);
	}
	
    /**
     * 新增塔机工作循环数据
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 结果
     */
	@Override
	public int insertSbCraneWorkloop(SbCraneWorkloop sbCraneWorkloop)
	{
	    return sbCraneWorkloopMapper.insertSbCraneWorkloop(sbCraneWorkloop);
	}
	
	/**
     * 修改塔机工作循环数据
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 结果
     */
	@Override
	public int updateSbCraneWorkloop(SbCraneWorkloop sbCraneWorkloop)
	{
	    return sbCraneWorkloopMapper.updateSbCraneWorkloop(sbCraneWorkloop);
	}

	/**
     * 删除塔机工作循环数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCraneWorkloopByIds(String ids)
	{
		return sbCraneWorkloopMapper.deleteSbCraneWorkloopByIds(Convert.toStrArray(ids));
	}
	
}
