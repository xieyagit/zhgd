package com.hujiang.project.zhgd.sbAccountTalkback.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbAccountTalkback.mapper.SbAccountTalkbackMapper;
import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import com.hujiang.project.zhgd.sbAccountTalkback.service.ISbAccountTalkbackService;
import com.hujiang.common.support.Convert;

/**
 * 集团对讲机列 服务层实现
 * 
 * @author hujiang
 * @date 2019-12-05
 */
@Service
public class SbAccountTalkbackServiceImpl implements ISbAccountTalkbackService 
{
	@Autowired
	private SbAccountTalkbackMapper sbAccountTalkbackMapper;

	/**
     * 查询集团对讲机列信息
     * 
     * @param id 集团对讲机列ID
     * @return 集团对讲机列信息
     */
    @Override
	public SbAccountTalkback selectSbAccountTalkbackById(Integer id)
	{
	    return sbAccountTalkbackMapper.selectSbAccountTalkbackById(id);
	}
	
	/**
     * 查询集团对讲机列列表
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 集团对讲机列集合
     */
	@Override
	public List<SbAccountTalkback> selectSbAccountTalkbackList(SbAccountTalkback sbAccountTalkback)
	{
	    return sbAccountTalkbackMapper.selectSbAccountTalkbackList(sbAccountTalkback);
	}
	
    /**
     * 新增集团对讲机列
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 结果
     */
	@Override
	public int insertSbAccountTalkback(SbAccountTalkback sbAccountTalkback)
	{
	    return sbAccountTalkbackMapper.insertSbAccountTalkback(sbAccountTalkback);
	}
	
	/**
     * 修改集团对讲机列
     * 
     * @param sbAccountTalkback 集团对讲机列信息
     * @return 结果
     */
	@Override
	public int updateSbAccountTalkback(SbAccountTalkback sbAccountTalkback)
	{
	    return sbAccountTalkbackMapper.updateSbAccountTalkback(sbAccountTalkback);
	}

	/**
     * 删除集团对讲机列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbAccountTalkbackByIds(String ids)
	{
		return sbAccountTalkbackMapper.deleteSbAccountTalkbackByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<SbAccountTalkback> getAccountListPage(Map<String,String> map){
		return sbAccountTalkbackMapper.getAccountListPage(map);
	}
}
