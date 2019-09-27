package com.hujiang.project.zhgd.sbProjectDustEmission.mapper;

import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目对应的扬尘设备SN 数据层
 * 
 * @author hujiang
 * @date 2019-06-18
 */
public interface SbProjectDustEmissionMapper 
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
     * 删除项目对应的扬尘设备SN
     * 
     * @param id 项目对应的扬尘设备SNID
     * @return 结果
     */
	public int deleteSbProjectDustEmissionById(Long id);
	
	/**
     * 批量删除项目对应的扬尘设备SN
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectDustEmissionByIds(String[] ids);

	/** 查询某项目下的设备sn*/
    List<SbProjectDustEmission> selectSn(@Param("projectId") Long projectId);
}