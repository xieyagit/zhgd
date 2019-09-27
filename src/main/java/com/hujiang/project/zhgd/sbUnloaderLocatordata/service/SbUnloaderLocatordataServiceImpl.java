package com.hujiang.project.zhgd.sbUnloaderLocatordata.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.mapper.SbUnloaderLocatordataMapper;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.domain.SbUnloaderLocatordata;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.service.ISbUnloaderLocatordataService;
import com.hujiang.common.support.Convert;

/**
 * 卸料GPS定位数据
 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbUnloaderLocatordataServiceImpl implements ISbUnloaderLocatordataService 
{
	@Autowired
	private SbUnloaderLocatordataMapper sbUnloaderLocatordataMapper;

	/**
     * 查询卸料GPS定位数据
信息
     * 
     * @param id 卸料GPS定位数据
ID
     * @return 卸料GPS定位数据
信息
     */
    @Override
	public SbUnloaderLocatordata selectSbUnloaderLocatordataById(Integer id)
	{
	    return sbUnloaderLocatordataMapper.selectSbUnloaderLocatordataById(id);
	}
	
	/**
     * 查询卸料GPS定位数据
列表
     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 卸料GPS定位数据
集合
     */
	@Override
	public List<SbUnloaderLocatordata> selectSbUnloaderLocatordataList(SbUnloaderLocatordata sbUnloaderLocatordata)
	{
	    return sbUnloaderLocatordataMapper.selectSbUnloaderLocatordataList(sbUnloaderLocatordata);
	}
	
    /**
     * 新增卸料GPS定位数据

     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderLocatordata(SbUnloaderLocatordata sbUnloaderLocatordata)
	{
	    return sbUnloaderLocatordataMapper.insertSbUnloaderLocatordata(sbUnloaderLocatordata);
	}
	
	/**
     * 修改卸料GPS定位数据

     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderLocatordata(SbUnloaderLocatordata sbUnloaderLocatordata)
	{
	    return sbUnloaderLocatordataMapper.updateSbUnloaderLocatordata(sbUnloaderLocatordata);
	}

	/**
     * 删除卸料GPS定位数据
对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderLocatordataByIds(String ids)
	{
		return sbUnloaderLocatordataMapper.deleteSbUnloaderLocatordataByIds(Convert.toStrArray(ids));
	}
	
}
