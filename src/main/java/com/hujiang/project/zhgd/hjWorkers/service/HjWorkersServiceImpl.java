package com.hujiang.project.zhgd.hjWorkers.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjWorkers.mapper.HjWorkersMapper;
import com.hujiang.project.zhgd.hjWorkers.domain.HjWorkers;
import com.hujiang.project.zhgd.hjWorkers.service.IHjWorkersService;
import com.hujiang.common.support.Convert;

/**
 * 工人库 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-16
 */
@Service
@Transactional
public class HjWorkersServiceImpl implements IHjWorkersService 
{
	@Autowired
	private HjWorkersMapper hjWorkersMapper;

	/**
     * 查询工人库信息
     * 
     * @param id 工人库ID
     * @return 工人库信息
     */
    @Override
	public HjWorkers selectHjWorkersById(Integer id)
	{
	    return hjWorkersMapper.selectHjWorkersById(id);
	}
	
	/**
     * 查询工人库列表
     * 
     * @param hjWorkers 工人库信息
     * @return 工人库集合
     */
	@Override
	public List<HjWorkers> selectHjWorkersList(HjWorkers hjWorkers)
	{
	    return hjWorkersMapper.selectHjWorkersList(hjWorkers);
	}
	
    /**
     * 新增工人库
     * 
     * @param hjWorkers 工人库信息
     * @return 结果
     */
	@Override
	public int insertHjWorkers(HjWorkers hjWorkers)
	{
	    return hjWorkersMapper.insertHjWorkers(hjWorkers);
	}
	
	/**
     * 修改工人库
     * 
     * @param hjWorkers 工人库信息
     * @return 结果
     */
	@Override
	public int updateHjWorkers(HjWorkers hjWorkers)
	{
	    return hjWorkersMapper.updateHjWorkers(hjWorkers);
	}

	/**
     * 删除工人库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjWorkersByIds(String ids)
	{
		return hjWorkersMapper.deleteHjWorkersByIds(Convert.toStrArray(ids));
	}
	
}
