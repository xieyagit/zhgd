package com.hujiang.project.zhgd.sbDustEmission.mapper;

import com.hujiang.project.zhgd.sbDustEmission.domain.Pm25_Pm10;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.domain.SdDustEmission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 扬尘数据 数据层
 * 
 * @author hujiang
 * @date 2019-06-18
 */
public interface SbDustEmissionMapper 
{
	public  List<SbDustEmission> selectSbDustEmissionListKB(Map<String, Object> map);
	/**
	 * 根据设备id和记录时间查询
	 * @param id
	 * @param time
	 * @return
	 */
	public SbDustEmission getDustEmissionByDateAndID(@Param("id") Long id, @Param("time") String time);
	/**
	 * 根据项目id和时间获取PM数据平均值
	 * @param pid
	 * @param time
	 * @return
	 */
	public Map<String,Float> getPMAVG(@Param("pid") Long pid, @Param("time") String time);
	/**
	 * 查询扬尘数据列表
	 * @param sn
	 * @return
	 */
	public List<SbDustEmission> selectDustEmissionListBySn(String sn);
	/**
	 * 根据sn和时间查询pm2.5和pm10
	 * @param sn
	 * @param time
	 * @return
	 */
	Pm25_Pm10 getPm25_Pm10BySnAndTime(@Param("sn") String sn, @Param("time") String time);
	/**
	 * 根据时间和sn查询扬尘数据信息
	 * @param map
	 * @return
	 */
	public  List<SbDustEmission> selectSbDustEmissionByTime(Map<String, Object> map);
	/**
 	* 根据时间和sn查询扬尘数据信息
 	* @param map
 	* @return
 	*/
	public  List<SbDustEmission> selectSbDustEmissionByTimes(Map<String, Object> map);

	/**
     * 查询扬尘数据信息
     * 
     * @param id 扬尘数据ID
     * @return 扬尘数据信息
     */
	public SbDustEmission selectSbDustEmissionById(Long id);

	/**
	 * 根据设备sn查询最新的一条记录
	 * @param sn
	 * @return
	 */
	SbDustEmission selectSbDustEmissionToOne(@Param("sn") String sn);

	/**
     * 查询扬尘数据列表
     * 
     * @param sbDustEmission 扬尘数据信息
     * @return 扬尘数据集合
     */
	public List<SbDustEmission> selectSbDustEmissionList(SbDustEmission sbDustEmission);
	
	/**
     * 新增扬尘数据
     * 
     * @param sbDustEmission 扬尘数据信息
     * @return 结果
     */
	public int insertSbDustEmission(SbDustEmission sbDustEmission);
	
	/**
     * 修改扬尘数据
     * 
     * @param sbDustEmission 扬尘数据信息
     * @return 结果
     */
	public int updateSbDustEmission(SbDustEmission sbDustEmission);
	
	/**
     * 删除扬尘数据
     * 
     * @param id 扬尘数据ID
     * @return 结果
     */
	public int deleteSbDustEmissionById(Long id);
	
	/**
     * 批量删除扬尘数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDustEmissionByIds(String[] ids);

	/**
	 * 根据sn和时间查询湿度、风速、气温
	 * @param sn
	 * @param time
	 * @return
	 */
	public SbDustEmission selectToday(@Param("sn") String sn, @Param("time") String time);

	/** 看板1.0扬尘tsp数据*/
	public List<SbDustEmission> selectTsp(@Param("sn") String sn);
	public List<SbDustEmission> selectTsp1(@Param("sn") String sn);

}