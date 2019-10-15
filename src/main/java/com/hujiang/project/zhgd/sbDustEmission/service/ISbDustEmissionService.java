package com.hujiang.project.zhgd.sbDustEmission.service;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDustEmission.domain.Pm25_Pm10;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.domain.SdDustEmission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 扬尘数据 服务层
 * 
 * @author hujiang
 * @date 2019-06-18
 */
public interface ISbDustEmissionService 
{
	/**
	 * 根据设备id和记录时间查询
	 * @param id
	 * @param time
	 * @return
	 */
	public SbDustEmission getDustEmissionByDateAndID(Long id,String time);
	/**
	 * 根据项目id和时间获取PM数据平均值
	 * @param pid
	 * @param time
	 * @return
	 */
	public Map<String,Float> getPMAVG(Long pid, String time);
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
	Pm25_Pm10 getPm25_Pm10BySnAndTime(String sn, String time);
	/**
	 * 根据时间和sn查询扬尘数据信息
	 * @param map
	 * @return
	 */
	List<SbDustEmission> selectSbDustEmissionByTime(Map<String,Object> map);
	/**
 * 根据时间和sn查询扬尘数据信息
 * @param map
 * @return
 */
public  List<SbDustEmission> selectSbDustEmissionByTimes(Map<String,Object> map);

	/**
	 * 根据设备sn查询最新的一条记录
	 * @param sn
	 * @return
	 */
	SbDustEmission selectSbDustEmissionToOne(String sn );
	/**
     * 查询扬尘数据信息
     * 
     * @param id 扬尘数据ID
     * @return 扬尘数据信息
     */
	public SbDustEmission selectSbDustEmissionById(Long id);
	
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
     * 删除扬尘数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDustEmissionByIds(String ids);

	/**
	 * 根据sn和时间查询湿度、风速、气温
	 * @param sn
	 * @param time
	 * @return
	 */
	public SbDustEmission selectToday(@Param("sn") String sn, @Param("time")String time);

	/** 看板1.0扬尘tsp数据*/
	public List<SbDustEmission> selectTsp(String sn);
	public List<SbDustEmission> selectTsp1(String sn);

}
