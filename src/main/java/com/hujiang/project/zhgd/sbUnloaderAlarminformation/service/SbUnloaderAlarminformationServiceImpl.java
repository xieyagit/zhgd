package com.hujiang.project.zhgd.sbUnloaderAlarminformation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.mapper.SbUnloaderAlarminformationMapper;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.domain.SbUnloaderAlarminformation;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.service.ISbUnloaderAlarminformationService;
import com.hujiang.common.support.Convert;

/**
 * 卸料报警时段数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbUnloaderAlarminformationServiceImpl implements ISbUnloaderAlarminformationService 
{
	@Autowired
	private SbUnloaderAlarminformationMapper sbUnloaderAlarminformationMapper;

	/**
     * 查询卸料报警时段数据信息
     * 
     * @param id 卸料报警时段数据ID
     * @return 卸料报警时段数据信息
     */
    @Override
	public SbUnloaderAlarminformation selectSbUnloaderAlarminformationById(Integer id)
	{
	    return sbUnloaderAlarminformationMapper.selectSbUnloaderAlarminformationById(id);
	}
	
	/**
     * 查询卸料报警时段数据列表
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 卸料报警时段数据集合
     */
	@Override
	public List<SbUnloaderAlarminformation> selectSbUnloaderAlarminformationList(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{
	    return sbUnloaderAlarminformationMapper.selectSbUnloaderAlarminformationList(sbUnloaderAlarminformation);
	}
	
    /**
     * 新增卸料报警时段数据
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderAlarminformation(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{
	    return sbUnloaderAlarminformationMapper.insertSbUnloaderAlarminformation(sbUnloaderAlarminformation);
	}
	
	/**
     * 修改卸料报警时段数据
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderAlarminformation(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{
	    return sbUnloaderAlarminformationMapper.updateSbUnloaderAlarminformation(sbUnloaderAlarminformation);
	}

	/**
     * 删除卸料报警时段数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderAlarminformationByIds(String ids)
	{
		return sbUnloaderAlarminformationMapper.deleteSbUnloaderAlarminformationByIds(Convert.toStrArray(ids));
	}
	
}
