package com.hujiang.project.zhgd.sbProjectVideo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbProjectVideo.mapper.SbProjectVideoMapper;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.common.support.Convert;

/**
 * 项目视频 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-23
 */
@Service
public class SbProjectVideoServiceImpl implements ISbProjectVideoService 
{
	@Autowired
	private SbProjectVideoMapper sbProjectVideoMapper;
	/**
	 * 根据项目id查询所有摄像头
	 * @param projectId
	 * @return
	 */
	public List<SbProjectVideo> selectSbProjectVideoByProjectId(Integer projectId){
		return sbProjectVideoMapper.selectSbProjectVideoByProjectId(projectId);
	}
	/**
     * 查询项目视频信息
     * 
     * @param id 项目视频ID
     * @return 项目视频信息
     */
    @Override
	public SbProjectVideo selectSbProjectVideoById(Integer id)
	{
	    return sbProjectVideoMapper.selectSbProjectVideoById(id);
	}
	
	/**
     * 查询项目视频列表
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 项目视频集合
     */
	@Override
	public List<SbProjectVideo> selectSbProjectVideoList(SbProjectVideo sbProjectVideo)
	{
	    return sbProjectVideoMapper.selectSbProjectVideoList(sbProjectVideo);
	}
	
    /**
     * 新增项目视频
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 结果
     */
	@Override
	public int insertSbProjectVideo(SbProjectVideo sbProjectVideo)
	{
	    return sbProjectVideoMapper.insertSbProjectVideo(sbProjectVideo);
	}
	
	/**
     * 修改项目视频
     * 
     * @param sbProjectVideo 项目视频信息
     * @return 结果
     */
	@Override
	public int updateSbProjectVideo(SbProjectVideo sbProjectVideo)
	{
	    return sbProjectVideoMapper.updateSbProjectVideo(sbProjectVideo);
	}

	/**
     * 删除项目视频对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectVideoByIds(String ids)
	{
		return sbProjectVideoMapper.deleteSbProjectVideoByIds(Convert.toStrArray(ids));
	}
	
}
