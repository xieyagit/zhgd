package com.hujiang.project.zhgd.lyPersonYunmou.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyPersonYunmou.mapper.LyPersonYunmouMapper;
import com.hujiang.project.zhgd.lyPersonYunmou.domain.LyPersonYunmou;
import com.hujiang.project.zhgd.lyPersonYunmou.service.ILyPersonYunmouService;
import com.hujiang.common.support.Convert;

/**
 * 云眸人员 服务层实现
 * 
 * @author hujiang
 * @date 2020-04-14
 */
@Service
public class LyPersonYunmouServiceImpl implements ILyPersonYunmouService 
{
	@Autowired
	private LyPersonYunmouMapper lyPersonYunmouMapper;

	/**
     * 查询云眸人员信息
     * 
     * @param id 云眸人员ID
     * @return 云眸人员信息
     */
    @Override
	public LyPersonYunmou selectLyPersonYunmouById(Integer id)
	{
	    return lyPersonYunmouMapper.selectLyPersonYunmouById(id);
	}
	
	/**
     * 查询云眸人员列表
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 云眸人员集合
     */
	@Override
	public List<LyPersonYunmou> selectLyPersonYunmouList(LyPersonYunmou lyPersonYunmou)
	{
	    return lyPersonYunmouMapper.selectLyPersonYunmouList(lyPersonYunmou);
	}
	
    /**
     * 新增云眸人员
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 结果
     */
	@Override
	public int insertLyPersonYunmou(LyPersonYunmou lyPersonYunmou)
	{
	    return lyPersonYunmouMapper.insertLyPersonYunmou(lyPersonYunmou);
	}
	
	/**
     * 修改云眸人员
     * 
     * @param lyPersonYunmou 云眸人员信息
     * @return 结果
     */
	@Override
	public int updateLyPersonYunmou(LyPersonYunmou lyPersonYunmou)
	{
	    return lyPersonYunmouMapper.updateLyPersonYunmou(lyPersonYunmou);
	}

	/**
     * 删除云眸人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyPersonYunmouByIds(String ids)
	{
		return lyPersonYunmouMapper.deleteLyPersonYunmouByIds(Convert.toStrArray(ids));
	}
	
}
