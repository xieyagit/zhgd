package com.hujiang.project.zhgd.sbExcessiveDust.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbExcessiveDust.mapper.SbExcessiveDustMapper;
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import com.hujiang.project.zhgd.sbExcessiveDust.service.ISbExcessiveDustService;
import com.hujiang.common.support.Convert;

/**
 * 扬尘超标记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-25
 */
@Service
public class SbExcessiveDustServiceImpl implements ISbExcessiveDustService 
{
	@Autowired
	private SbExcessiveDustMapper sbExcessiveDustMapper;

	@Override
	public int getExcessiveCount(Integer projectId,Integer userId) {
		return sbExcessiveDustMapper.getExcessiveCount(projectId, userId);
	}

	/**
     * 查询扬尘超标记录信息
     * 
     * @param id 扬尘超标记录ID
     * @return 扬尘超标记录信息
     */
    @Override
	public SbExcessiveDust selectSbExcessiveDustById(Integer id)
	{
	    return sbExcessiveDustMapper.selectSbExcessiveDustById(id);
	}
	
	/**
     * 查询扬尘超标记录列表
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 扬尘超标记录集合
     */
	@Override
	public List<SbExcessiveDust> selectSbExcessiveDustList(SbExcessiveDust sbExcessiveDust)
	{
	    return sbExcessiveDustMapper.selectSbExcessiveDustList(sbExcessiveDust);
	}
	
    /**
     * 新增扬尘超标记录
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 结果
     */
	@Override
	public int insertSbExcessiveDust(SbExcessiveDust sbExcessiveDust)
	{
	    return sbExcessiveDustMapper.insertSbExcessiveDust(sbExcessiveDust);
	}
	
	/**
     * 修改扬尘超标记录
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 结果
     */
	@Override
	public int updateSbExcessiveDust(SbExcessiveDust sbExcessiveDust)
	{
	    return sbExcessiveDustMapper.updateSbExcessiveDust(sbExcessiveDust);
	}

	/**
     * 删除扬尘超标记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbExcessiveDustByIds(String ids)
	{
		return sbExcessiveDustMapper.deleteSbExcessiveDustByIds(Convert.toStrArray(ids));
	}
	
}
