package com.hujiang.project.zhgd.hjProjectImage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjProjectImage.mapper.HjProjectImageMapper;
import com.hujiang.project.zhgd.hjProjectImage.domain.HjProjectImage;
import com.hujiang.project.zhgd.hjProjectImage.service.IHjProjectImageService;
import com.hujiang.common.support.Convert;

/**
 * 项目效果图 服务层实现
 * 
 * @author hujiang
 * @date 2019-11-15
 */
@Service
public class HjProjectImageServiceImpl implements IHjProjectImageService 
{
	@Autowired
	private HjProjectImageMapper hjProjectImageMapper;

	/**
     * 查询项目效果图信息
     * 
     * @param id 项目效果图ID
     * @return 项目效果图信息
     */
    @Override
	public HjProjectImage selectHjProjectImageById(Integer id)
	{
	    return hjProjectImageMapper.selectHjProjectImageById(id);
	}
	
	/**
     * 查询项目效果图列表
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 项目效果图集合
     */
	@Override
	public List<HjProjectImage> selectHjProjectImageList(HjProjectImage hjProjectImage)
	{
	    return hjProjectImageMapper.selectHjProjectImageList(hjProjectImage);
	}
	
    /**
     * 新增项目效果图
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 结果
     */
	@Override
	public int insertHjProjectImage(HjProjectImage hjProjectImage)
	{
	    return hjProjectImageMapper.insertHjProjectImage(hjProjectImage);
	}
	
	/**
     * 修改项目效果图
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 结果
     */
	@Override
	public int updateHjProjectImage(HjProjectImage hjProjectImage)
	{
	    return hjProjectImageMapper.updateHjProjectImage(hjProjectImage);
	}

	/**
     * 删除项目效果图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjProjectImageByIds(String ids)
	{
		return hjProjectImageMapper.deleteHjProjectImageByIds(Convert.toStrArray(ids));
	}
	
}
