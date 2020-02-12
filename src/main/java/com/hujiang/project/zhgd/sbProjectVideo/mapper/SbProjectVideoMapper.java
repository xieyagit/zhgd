package com.hujiang.project.zhgd.sbProjectVideo.mapper;

import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import java.util.List;	

/**
 * 项目视频 数据层
 * 
 * @author hujiang
 * @date 2019-06-23
 */
public interface SbProjectVideoMapper 
{
	/**
     * 查询项目视频信息
     * 
     * @param id 项目视频ID
     * @return 项目视频信息
     */
	public SbProjectVideo selectSbProjectVideoById(Integer id);
	/**
	 * 根据项目id查询所有摄像头
	 * @param projectId
	 * @return
	 */
	public List<SbProjectVideo> selectSbProjectVideoByProjectId(Integer projectId);
	/**
     * 查询项目视频列表
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 项目视频集合
     */
	public List<SbProjectVideo> selectSbProjectVideoList(SbProjectVideo sbProjectVideo);
	
	/**
     * 新增项目视频
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 结果
     */
	public int insertSbProjectVideo(SbProjectVideo sbProjectVideo);
	
	/**
     * 修改项目视频
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 结果
     */
	public int updateSbProjectVideo(SbProjectVideo sbProjectVideo);

	public int updateSbProjectVideoPicUrl(SbProjectVideo sbProjectVideo);
	public int updateVideoCoordinate(SbProjectVideo sbProjectVideo);

	/**
     * 删除项目视频
     * 
     * @param id 项目视频ID
     * @return 结果
     */
	public int deleteSbProjectVideoById(Integer id);
	
	/**
     * 批量删除项目视频
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectVideoByIds(String[] ids);
	
}