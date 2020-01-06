package com.hujiang.project.zhgd.sbProjectVideo.service;

import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import java.util.List;

/**
 * 项目视频 服务层
 * 
 * @author hujiang
 * @date 2019-06-23
 */
public interface ISbProjectVideoService 
{
	/**
     * 查询项目视频信息
     * 
     * @param id 项目视频ID
     * @return 项目视频信息
     */
	public SbProjectVideo selectSbProjectVideoById(Integer id);
	
	/**
     * 查询项目视频列表
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 项目视频集合
     */
	public List<SbProjectVideo> selectSbProjectVideoList(SbProjectVideo sbProjectVideo);

	/**
	 * 根据项目id查询所有摄像头
	 * @param projectId
	 * @return
	 */
	public List<SbProjectVideo> selectSbProjectVideoByProjectId(Integer projectId);

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
     * 删除项目视频信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectVideoByIds(String ids);
	
}
