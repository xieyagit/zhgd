package com.hujiang.project.zhgd.sbDustEmission.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.sbDustEmission.domain.Pm25_Pm10;
import com.hujiang.project.zhgd.sbDustEmission.domain.SdDustEmission;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbDustEmission.mapper.SbDustEmissionMapper;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.common.support.Convert;

/**
 * 扬尘数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-18
 */
@Service
public class SbDustEmissionServiceImpl implements ISbDustEmissionService 
{
	@Autowired
	private SbDustEmissionMapper sbDustEmissionMapper;

	@Override
	public List<SbDustEmission> selectSbDustEmissionListKB(Map<String, Object> map) {
		return sbDustEmissionMapper.selectSbDustEmissionListKB(map);
	}

	/**
	 * 根据设备id和记录时间查询
	 * @param id
	 * @param time
	 * @return
	 */
	@Override
	public SbDustEmission getDustEmissionByDateAndID(Long id, String time){
		return sbDustEmissionMapper.getDustEmissionByDateAndID(id, time);
	}
	/**
	 * 根据项目id和时间获取PM数据平均值
	 * @param pid
	 * @param time
	 * @return
	 */
	@Override
	public Map<String,Float> getPMAVG(Long pid, String time){
		return sbDustEmissionMapper.getPMAVG(pid, time);
	}
	/**
	 * 查询环境监测数据列表
	 * @param sn
	 * @return
	 */
	@Override
	public List<SbDustEmission> selectDustEmissionListBySn(String sn){
		return sbDustEmissionMapper.selectDustEmissionListBySn(sn);
	}
	/**
	 * 根据sn和时间查询pm2.5和pm10
	 * @param sn
	 * @param time
	 * @return
	 */
	@Override
	public Pm25_Pm10 getPm25_Pm10BySnAndTime( String sn,String time){
		return sbDustEmissionMapper.getPm25_Pm10BySnAndTime(sn, time);
	}

	/**
	 * 根据设备sn查询最新的一条记录
	 * @param sn
	 * @return
	 */
	@Override
	public SbDustEmission selectSbDustEmissionToOne(String sn ){
		return sbDustEmissionMapper.selectSbDustEmissionToOne(sn);
	}
	/**
	 * 根据时间和sn查询环境监测数据信息
	 * @param map
	 * @return
	 */
	@Override
	public List<SbDustEmission> selectSbDustEmissionByTime(Map<String,Object> map){
		return sbDustEmissionMapper.selectSbDustEmissionByTime(map);
	}

	@Override
	public List<SbDustEmission> selectSbDustEmissionByTimes(Map<String, Object> map) {
		return sbDustEmissionMapper.selectSbDustEmissionByTimes(map);
	}

	/**
     * 查询环境监测数据
     * 
     * @param id 环境监测数据ID
     * @return 环境监测数据信息
     */
    @Override
	public SbDustEmission selectSbDustEmissionById(Long id)
	{
	    return sbDustEmissionMapper.selectSbDustEmissionById(id);
	}
	
	/**
     * 查询环境监测数据列表
     * 
     * @param sbDustEmission 环境监测数据信息
     * @return 环境监测数据集合
     */
	@Override
	public List<SbDustEmission> selectSbDustEmissionList(SbDustEmission sbDustEmission)
	{
	    return sbDustEmissionMapper.selectSbDustEmissionList(sbDustEmission);
	}
	
    /**
     * 新增环境监测数据
     * 
     * @param sbDustEmission 环境监测数据信息
     * @return 结果
     */
	@Override
	public int insertSbDustEmission(SbDustEmission sbDustEmission)
	{
	    return sbDustEmissionMapper.insertSbDustEmission(sbDustEmission);
	}



	/**
     * 修改环境监测数据
     * 
     * @param sbDustEmission 扬尘数据信息
     * @return 结果
     */
	@Override
	public int updateSbDustEmission(SbDustEmission sbDustEmission)
	{
	    return sbDustEmissionMapper.updateSbDustEmission(sbDustEmission);
	}

	/**
     * 删除环境监测数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDustEmissionByIds(String ids)
	{
		return sbDustEmissionMapper.deleteSbDustEmissionByIds(Convert.toStrArray(ids));
	}
	/**
	 * 根据sn和时间查询湿度、风速、气温
	 * @param sn
	 * @param time
	 * @return
	 * */
	@Override
	public SbDustEmission selectToday(String sn, String time) {
		return sbDustEmissionMapper.selectToday(sn,time);
	}

	/** 看板1.0扬尘tsp数据*/
	public List<SbDustEmission> selectTsp(String sn){
		return sbDustEmissionMapper.selectTsp(sn);
	}
	public List<SbDustEmission> selectTsp1(String sn){
		return sbDustEmissionMapper.selectTsp1(sn);
	}

}
