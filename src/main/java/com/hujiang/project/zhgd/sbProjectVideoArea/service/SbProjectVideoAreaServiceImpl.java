package com.hujiang.project.zhgd.sbProjectVideoArea.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbProjectVideoArea.mapper.SbProjectVideoAreaMapper;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import com.hujiang.common.support.Convert;

/**
 * 项目视频区 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-23
 */
@Service
public class SbProjectVideoAreaServiceImpl implements ISbProjectVideoAreaService 
{
	@Autowired
	private SbProjectVideoAreaMapper sbProjectVideoAreaMapper;

	/**
     * 查询项目视频区信息
     * 
     * @param id 项目视频区ID
     * @return 项目视频区信息
     */
    @Override
	public SbProjectVideoArea selectSbProjectVideoAreaById(Integer id)
	{
	    return sbProjectVideoAreaMapper.selectSbProjectVideoAreaById(id);
	}
	
	/**
     * 查询项目视频区列表
     * 
     * @param sbProjectVideoArea 项目视频区信息
     * @return 项目视频区集合
     */
	@Override
	public List<SbProjectVideoArea> selectSbProjectVideoAreaList(SbProjectVideoArea sbProjectVideoArea)
	{
	    return sbProjectVideoAreaMapper.selectSbProjectVideoAreaList(sbProjectVideoArea);
	}
	
    /**
     * 新增项目视频区
     * 
     * @param sbProjectVideoArea 项目视频区信息
     * @return 结果
     */
	@Override
	public int insertSbProjectVideoArea(SbProjectVideoArea sbProjectVideoArea)
	{
	    return sbProjectVideoAreaMapper.insertSbProjectVideoArea(sbProjectVideoArea);
	}
	
	/**
     * 修改项目视频区
     * 
     * @param sbProjectVideoArea 项目视频区信息
     * @return 结果
     */
	@Override
	public int updateSbProjectVideoArea(SbProjectVideoArea sbProjectVideoArea)
	{
	    return sbProjectVideoAreaMapper.updateSbProjectVideoArea(sbProjectVideoArea);
	}

	/**
     * 删除项目视频区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectVideoAreaByIds(String ids)
	{
		return sbProjectVideoAreaMapper.deleteSbProjectVideoAreaByIds(Convert.toStrArray(ids));
	}
	
}
