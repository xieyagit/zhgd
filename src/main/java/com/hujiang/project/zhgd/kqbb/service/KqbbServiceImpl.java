package com.hujiang.project.zhgd.kqbb.service;

import java.util.List;

import com.hujiang.project.zhgd.kqbb.domain.BG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.kqbb.mapper.KqbbMapper;
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;
import com.hujiang.project.zhgd.kqbb.service.IKqbbService;
import com.hujiang.common.support.Convert;

/**
 * 考勤报 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-05
 */
@Service
public class KqbbServiceImpl implements IKqbbService 
{
	@Autowired
	private KqbbMapper kqbbMapper;

	public List<BG> selectKqbbListBb(Integer projectId){
		return kqbbMapper.selectKqbbListBb(projectId);
	}
	/**
     * 查询考勤报信息
     * 
     * @param projectId 考勤报ID
     * @return 考勤报信息
     */
    @Override
	public Kqbb selectKqbbById(Integer projectId)
	{
	    return kqbbMapper.selectKqbbById(projectId);
	}
	
	/**
     * 查询考勤报列表
     * 
     * @param kqbb 考勤报信息
     * @return 考勤报集合
     */
	@Override
	public List<Kqbb> selectKqbbList(Kqbb kqbb)
	{
	    return kqbbMapper.selectKqbbList(kqbb);
	}
	
    /**
     * 新增考勤报
     * 
     * @param kqbb 考勤报信息
     * @return 结果
     */
	@Override
	public int insertKqbb(Kqbb kqbb)
	{
	    return kqbbMapper.insertKqbb(kqbb);
	}
	
	/**
     * 修改考勤报
     * 
     * @param kqbb 考勤报信息
     * @return 结果
     */
	@Override
	public int updateKqbb(Kqbb kqbb)
	{
	    return kqbbMapper.updateKqbb(kqbb);
	}

	/**
     * 删除考勤报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteKqbbByIds(String ids)
	{
		return kqbbMapper.deleteKqbbByIds(Convert.toStrArray(ids));
	}
	
}
