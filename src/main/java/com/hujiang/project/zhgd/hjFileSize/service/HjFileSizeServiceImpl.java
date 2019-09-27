package com.hujiang.project.zhgd.hjFileSize.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjFileSize.mapper.HjFileSizeMapper;
import com.hujiang.project.zhgd.hjFileSize.domain.HjFileSize;
import com.hujiang.project.zhgd.hjFileSize.service.IHjFileSizeService;
import com.hujiang.common.support.Convert;

/**
 * 项目存储空间大小 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjFileSizeServiceImpl implements IHjFileSizeService 
{
	@Autowired
	private HjFileSizeMapper hjFileSizeMapper;

	/**
     * 查询项目存储空间大小信息
     * 
     * @param projectId 项目存储空间大小ID
     * @return 项目存储空间大小信息
     */
    @Override
	public HjFileSize selectHjFileSizeById(Integer projectId)
	{
	    return hjFileSizeMapper.selectHjFileSizeById(projectId);
	}
	
	/**
     * 查询项目存储空间大小列表
     * 
     * @param hjFileSize 项目存储空间大小信息
     * @return 项目存储空间大小集合
     */
	@Override
	public List<HjFileSize> selectHjFileSizeList(HjFileSize hjFileSize)
	{
	    return hjFileSizeMapper.selectHjFileSizeList(hjFileSize);
	}
	
    /**
     * 新增项目存储空间大小
     * 
     * @param hjFileSize 项目存储空间大小信息
     * @return 结果
     */
	@Override
	public int insertHjFileSize(HjFileSize hjFileSize)
	{
	    return hjFileSizeMapper.insertHjFileSize(hjFileSize);
	}
	
	/**
     * 修改项目存储空间大小
     * 
     * @param hjFileSize 项目存储空间大小信息
     * @return 结果
     */
	@Override
	public int updateHjFileSize(HjFileSize hjFileSize)
	{
	    return hjFileSizeMapper.updateHjFileSize(hjFileSize);
	}

	/**
     * 删除项目存储空间大小对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjFileSizeByIds(String ids)
	{
		return hjFileSizeMapper.deleteHjFileSizeByIds(Convert.toStrArray(ids));
	}
	
}
