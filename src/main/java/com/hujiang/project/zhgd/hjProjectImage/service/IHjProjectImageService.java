package com.hujiang.project.zhgd.hjProjectImage.service;

import com.hujiang.project.zhgd.hjProjectImage.domain.HjProjectImage;
import java.util.List;

/**
 * 项目效果图 服务层
 * 
 * @author hujiang
 * @date 2019-11-15
 */
public interface IHjProjectImageService 
{
	/**
     * 查询项目效果图信息
     * 
     * @param id 项目效果图ID
     * @return 项目效果图信息
     */
	public HjProjectImage selectHjProjectImageById(Integer id);
	
	/**
     * 查询项目效果图列表
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 项目效果图集合
     */
	public List<HjProjectImage> selectHjProjectImageList(HjProjectImage hjProjectImage);
	
	/**
     * 新增项目效果图
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 结果
     */
	public int insertHjProjectImage(HjProjectImage hjProjectImage);
	
	/**
     * 修改项目效果图
     * 
     * @param hjProjectImage 项目效果图信息
     * @return 结果
     */
	public int updateHjProjectImage(HjProjectImage hjProjectImage);
		
	/**
     * 删除项目效果图信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectImageByIds(String ids);
	
}
