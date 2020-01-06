package com.hujiang.project.zhgd.sbGroupTitle.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbGroupTitle.mapper.SbGroupTitleMapper;
import com.hujiang.project.zhgd.sbGroupTitle.domain.SbGroupTitle;
import com.hujiang.project.zhgd.sbGroupTitle.service.ISbGroupTitleService;
import com.hujiang.common.support.Convert;

/**
 * 项目标题 服务层实现
 * 
 * @author hujiang
 * @date 2020-01-03
 */
@Service
public class SbGroupTitleServiceImpl implements ISbGroupTitleService 
{
	@Autowired
	private SbGroupTitleMapper sbGroupTitleMapper;

	/**
     * 查询项目标题信息
     * 
     * @param id 项目标题ID
     * @return 项目标题信息
     */
    @Override
	public SbGroupTitle selectSbGroupTitleById(Integer id)
	{
	    return sbGroupTitleMapper.selectSbGroupTitleById(id);
	}
	
	/**
     * 查询项目标题列表
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 项目标题集合
     */
	@Override
	public List<SbGroupTitle> selectSbGroupTitleList(SbGroupTitle sbGroupTitle)
	{
	    return sbGroupTitleMapper.selectSbGroupTitleList(sbGroupTitle);
	}
	
    /**
     * 新增项目标题
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 结果
     */
	@Override
	public int insertSbGroupTitle(SbGroupTitle sbGroupTitle)
	{
	    return sbGroupTitleMapper.insertSbGroupTitle(sbGroupTitle);
	}
	
	/**
     * 修改项目标题
     * 
     * @param sbGroupTitle 项目标题信息
     * @return 结果
     */
	@Override
	public int updateSbGroupTitle(SbGroupTitle sbGroupTitle)
	{
	    return sbGroupTitleMapper.updateSbGroupTitle(sbGroupTitle);
	}

	/**
     * 删除项目标题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbGroupTitleByIds(String ids)
	{
		return sbGroupTitleMapper.deleteSbGroupTitleByIds(Convert.toStrArray(ids));
	}
	
}
