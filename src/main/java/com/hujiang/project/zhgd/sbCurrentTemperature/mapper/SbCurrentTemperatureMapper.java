package com.hujiang.project.zhgd.sbCurrentTemperature.mapper;

import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 温度及漏电流记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface SbCurrentTemperatureMapper 
{
	/**
	 * 查询温度及漏电流记录列表
	 *
	 * @param currentTemperature 温度及漏电流记录信息
	 * @return 温度及漏电流记录集合
	 */
	public SbCurrentTemperature selectCurrentTemperature(SbCurrentTemperature currentTemperature);
	/**
     * 查询温度及漏电流记录信息
     * 
     * @param id 温度及漏电流记录ID
     * @return 温度及漏电流记录信息
     */
	public SbCurrentTemperature selectSbCurrentTemperatureById(Integer id);

	/**
	 * 查询最新的一条数据
	 * @param electricityBoxId
	 * @return
	 */
	 SbCurrentTemperature selectSbCurrentTemperatureToOne(@Param("electricityBoxId") String electricityBoxId);
	/**
	 * 查询最新的一条数据
	 * @param electricityBoxId
	 * @return
	 */
	SbCurrentTemperature getSbCurrentTemperatureToOne(@Param("electricityBoxId") String electricityBoxId);
	/**
	 * 统计超标数
	 * @param electricityBoxId
	 * @return
	 */
	 Map<String,Object> selectSbCurrentTemperatureCount(@Param("electricityBoxId") String electricityBoxId);

	/**
     * 查询温度及漏电流记录列表
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 温度及漏电流记录集合
     */
	public List<SbCurrentTemperature> selectSbCurrentTemperatureList(SbCurrentTemperature sbCurrentTemperature);
	public List<SbCurrentTemperature> selectSbCurrentTemperatureListTwo(SbCurrentTemperature sbCurrentTemperature);

	/**
	 * 根据时间和电箱编号查询
	 * @param map
	 * @return
	 */
	public List<SbCurrentTemperature> selectSbCurrentTemperatureByTime(Map<String,Object> map);
	/**
	 * 根据时间和电箱编号查询
	 * @param map
	 * @return
	 */
	public List<SbCurrentTemperature> selectSbCurrentTemperatureByTimes(Map<String,Object> map);


	/**
     * 新增温度及漏电流记录
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 结果
     */
	public int insertSbCurrentTemperature(SbCurrentTemperature sbCurrentTemperature);
	
	/**
     * 修改温度及漏电流记录
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 结果
     */
	public int updateSbCurrentTemperature(SbCurrentTemperature sbCurrentTemperature);
	
	/**
     * 删除温度及漏电流记录
     * 
     * @param id 温度及漏电流记录ID
     * @return 结果
     */
	public int deleteSbCurrentTemperatureById(Integer id);
	
	/**
     * 批量删除温度及漏电流记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCurrentTemperatureByIds(String[] ids);


	/**
	 * 看板1.0电箱数据
	 * @return 结果
	 * */
	public SbCurrentTemperature kanban(Integer projectId);
	public List<SbCurrentTemperature> selectkanban(Integer projectId);
	
}