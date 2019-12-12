package com.hujiang.project.zhgd.sbGroupTalkback.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbGroupTalkback.mapper.SbGroupTalkbackMapper;
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTalkback.service.ISbGroupTalkbackService;
import com.hujiang.common.support.Convert;

/**
 * 集团对讲账号 服务层实现
 * 
 * @author hujiang
 * @date 2019-12-05
 */
@Service
public class SbGroupTalkbackServiceImpl implements ISbGroupTalkbackService 
{
	@Autowired
	private SbGroupTalkbackMapper sbGroupTalkbackMapper;

	/**
     * 查询集团对讲账号信息
     * 
     * @param id 集团对讲账号ID
     * @return 集团对讲账号信息
     */
    @Override
	public SbGroupTalkback selectSbGroupTalkbackById(Integer id)
	{
	    return sbGroupTalkbackMapper.selectSbGroupTalkbackById(id);
	}
	
	/**
     * 查询集团对讲账号列表
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 集团对讲账号集合
     */
	@Override
	public List<SbGroupTalkback> selectSbGroupTalkbackList(SbGroupTalkback sbGroupTalkback)
	{
	    return sbGroupTalkbackMapper.selectSbGroupTalkbackList(sbGroupTalkback);
	}
	
    /**
     * 新增集团对讲账号
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 结果
     */
	@Override
	public int insertSbGroupTalkback(SbGroupTalkback sbGroupTalkback)
	{
	    return sbGroupTalkbackMapper.insertSbGroupTalkback(sbGroupTalkback);
	}
	
	/**
     * 修改集团对讲账号
     * 
     * @param sbGroupTalkback 集团对讲账号信息
     * @return 结果
     */
	@Override
	public int updateSbGroupTalkback(SbGroupTalkback sbGroupTalkback)
	{
	    return sbGroupTalkbackMapper.updateSbGroupTalkback(sbGroupTalkback);
	}

	/**
     * 删除集团对讲账号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbGroupTalkbackByIds(String ids)
	{
		return sbGroupTalkbackMapper.deleteSbGroupTalkbackByIds(Convert.toStrArray(ids));
	}
	/**
	 * 集团对讲机列表
	 */
	public List<SbGroupTalkback> getAccountList(SbGroupTalkback sbGroupTalkback){
		return sbGroupTalkbackMapper.getAccountList(sbGroupTalkback);
	}
}
