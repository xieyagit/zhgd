package com.hujiang.project.zhgd.sbProjectDustEmission.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbProjectDustEmission.mapper.SbProjectDustEmissionMapper;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.common.support.Convert;

/**
 * 项目对应的扬尘设备SN 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-18
 */
@Service
@Transactional
public class SbProjectDustEmissionServiceImpl implements ISbProjectDustEmissionService 
{
	@Autowired
	private SbProjectDustEmissionMapper sbProjectDustEmissionMapper;

	/**
	 * 查询项目对应的扬尘设备SN列表包含设备记录
	 *
	 * @param projectDustEmission 项目对应的扬尘设备SN信息
	 * @return 项目对应的扬尘设备SN集合
	 */
	public List<SbProjectDustEmission> selectProjectDustEmissionListData(SbProjectDustEmission projectDustEmission){
		return sbProjectDustEmissionMapper.selectProjectDustEmissionListData(projectDustEmission);
	}
	/**
     * 查询项目对应的扬尘设备SN信息
     * 
     * @param id 项目对应的扬尘设备SNID
     * @return 项目对应的扬尘设备SN信息
     */
    @Override
	public SbProjectDustEmission selectSbProjectDustEmissionById(Long id)
	{
	    return sbProjectDustEmissionMapper.selectSbProjectDustEmissionById(id);
	}

	@Override
	public AjaxResult reportedDustEData(SbDustEmission sbDustEmission) throws IOException, URISyntaxException {
		return null;
	}

	/**
     * 查询项目对应的扬尘设备SN列表
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 项目对应的扬尘设备SN集合
     */
	@Override
	public List<SbProjectDustEmission> selectSbProjectDustEmissionList(SbProjectDustEmission sbProjectDustEmission)
	{
	    return sbProjectDustEmissionMapper.selectSbProjectDustEmissionList(sbProjectDustEmission);
	}
	
    /**
     * 新增项目对应的扬尘设备SN
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 结果
     */
	@Override
	public int insertSbProjectDustEmission(SbProjectDustEmission sbProjectDustEmission)
	{
	    return sbProjectDustEmissionMapper.insertSbProjectDustEmission(sbProjectDustEmission);
	}
	
	/**
     * 修改项目对应的扬尘设备SN
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 结果
     */
	@Override
	public int updateSbProjectDustEmission(SbProjectDustEmission sbProjectDustEmission)
	{
	    return sbProjectDustEmissionMapper.updateSbProjectDustEmission(sbProjectDustEmission);
	}

	/**
     * 删除项目对应的扬尘设备SN对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectDustEmissionByIds(String ids)
	{
		return sbProjectDustEmissionMapper.deleteSbProjectDustEmissionByIds(Convert.toStrArray(ids));
	}

	/** 查询某项目下的设备sn*/
//	@Override
	public List<SbProjectDustEmission> selectSn(Long projectId){
		return sbProjectDustEmissionMapper.selectSn(projectId);
	}
}
