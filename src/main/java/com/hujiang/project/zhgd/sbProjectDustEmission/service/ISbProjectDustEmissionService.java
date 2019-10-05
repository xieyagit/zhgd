package com.hujiang.project.zhgd.sbProjectDustEmission.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 项目对应的扬尘设备SN 服务层
 * 
 * @author hujiang
 * @date 2019-06-18
 */
public interface ISbProjectDustEmissionService 
{
	/**
	 * 查询项目对应的扬尘设备SN列表包含设备记录
	 *
	 * @param projectDustEmission 项目对应的扬尘设备SN信息
	 * @return 项目对应的扬尘设备SN集合
	 */
	public List<SbProjectDustEmission> selectProjectDustEmissionListData(SbProjectDustEmission projectDustEmission);
	/**
     * 查询项目对应的扬尘设备SN信息
     * 
     * @param id 项目对应的扬尘设备SNID
     * @return 项目对应的扬尘设备SN信息
     */
	public SbProjectDustEmission selectSbProjectDustEmissionById(Long id);

	/**
	 * 上报扬尘实时数据
	 * @param
	 * @return
	 * @author yant
	 */
	public AjaxResult reportedDustEData(SbDustEmission sbDustEmission) throws IOException, URISyntaxException;
	
	/**
     * 查询项目对应的扬尘设备SN列表
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 项目对应的扬尘设备SN集合
     */
	public List<SbProjectDustEmission> selectSbProjectDustEmissionList(SbProjectDustEmission sbProjectDustEmission);
	
	/**
     * 新增项目对应的扬尘设备SN
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 结果
     */
	public int insertSbProjectDustEmission(SbProjectDustEmission sbProjectDustEmission);
	
	/**
     * 修改项目对应的扬尘设备SN
     * 
     * @param sbProjectDustEmission 项目对应的扬尘设备SN信息
     * @return 结果
     */
	public int updateSbProjectDustEmission(SbProjectDustEmission sbProjectDustEmission);
		
	/**
     * 删除项目对应的扬尘设备SN信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectDustEmissionByIds(Integer id);

	/** 查询某项目下的设备sn*/
	List<SbProjectDustEmission> selectSn(Long projectId);

}
