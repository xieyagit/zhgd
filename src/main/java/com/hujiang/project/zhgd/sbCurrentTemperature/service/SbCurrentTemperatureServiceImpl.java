package com.hujiang.project.zhgd.sbCurrentTemperature.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.sbCurrentTemperature.mapper.SbCurrentTemperatureMapper;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.common.support.Convert;

/**
 * 温度及漏电流记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Service
public class SbCurrentTemperatureServiceImpl implements ISbCurrentTemperatureService 
{
	@Autowired
	private SbCurrentTemperatureMapper sbCurrentTemperatureMapper;

	@Override
	public SbCurrentTemperature getSbCurrentTemperatureToOne(String electricityBoxId) {
		return sbCurrentTemperatureMapper.getSbCurrentTemperatureToOne(electricityBoxId);
	}

	/**
	 * 查询温度及漏电流记录列表
	 *
	 * @param currentTemperature 温度及漏电流记录信息
	 * @return 温度及漏电流记录集合
	 */
	public SbCurrentTemperature selectCurrentTemperature(SbCurrentTemperature currentTemperature){
		return sbCurrentTemperatureMapper.selectCurrentTemperature(currentTemperature);
	}
	/**
	 * 查询最新的一条数据
	 * @param electricityBoxId
	 * @return
	 */
	public SbCurrentTemperature selectSbCurrentTemperatureToOne(String electricityBoxId){

		return sbCurrentTemperatureMapper.selectSbCurrentTemperatureToOne(electricityBoxId);
	}


	/**
	 * 统计超标数
	 * @param electricityBoxId
	 * @return
	 */
	public Map<String,Object> selectSbCurrentTemperatureCount(String electricityBoxId){
		return sbCurrentTemperatureMapper.selectSbCurrentTemperatureCount(electricityBoxId);
	}

	/**
	 * 根据时间和电箱编号查询
	 * @param map
	 * @return
	 */
	public List<SbCurrentTemperature> selectSbCurrentTemperatureByTime(Map<String,Object> map){
		return sbCurrentTemperatureMapper.selectSbCurrentTemperatureByTime(map);
	}


	@Override
	public List<SbCurrentTemperature> selectSbCurrentTemperatureByTimes(Map<String, Object> map) {
		return sbCurrentTemperatureMapper.selectSbCurrentTemperatureByTimes(map);
	}

	/**
     * 查询温度及漏电流记录信息
     * 
     * @param id 温度及漏电流记录ID
     * @return 温度及漏电流记录信息
     */
    @Override
	public SbCurrentTemperature selectSbCurrentTemperatureById(Integer id)
	{
	    return sbCurrentTemperatureMapper.selectSbCurrentTemperatureById(id);
	}
	
	/**
     * 查询温度及漏电流记录列表
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 温度及漏电流记录集合
     */
	@Override
	public List<SbCurrentTemperature> selectSbCurrentTemperatureList(SbCurrentTemperature sbCurrentTemperature)
	{
	    return sbCurrentTemperatureMapper.selectSbCurrentTemperatureList(sbCurrentTemperature);
	}
	@Override
	public List<SbCurrentTemperature> selectSbCurrentTemperatureListTwo(SbCurrentTemperature sbCurrentTemperature)
	{
		return sbCurrentTemperatureMapper.selectSbCurrentTemperatureListTwo(sbCurrentTemperature);
	}
    /**
     * 新增温度及漏电流记录
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 结果
     */
	@Override
	public int insertSbCurrentTemperature(SbCurrentTemperature sbCurrentTemperature)
	{
	    return sbCurrentTemperatureMapper.insertSbCurrentTemperature(sbCurrentTemperature);
	}
	
	/**
     * 修改温度及漏电流记录
     * 
     * @param sbCurrentTemperature 温度及漏电流记录信息
     * @return 结果
     */
	@Override
	public int updateSbCurrentTemperature(SbCurrentTemperature sbCurrentTemperature)
	{
	    return sbCurrentTemperatureMapper.updateSbCurrentTemperature(sbCurrentTemperature);
	}

	/**
     * 删除温度及漏电流记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbCurrentTemperatureByIds(String ids)
	{
		return sbCurrentTemperatureMapper.deleteSbCurrentTemperatureByIds(Convert.toStrArray(ids));
	}

	@Override
	public SbCurrentTemperature kanban(Integer projectId){
		return sbCurrentTemperatureMapper.kanban(projectId);
	}
	public List<SbCurrentTemperature> selectkanban(Integer projectId){return sbCurrentTemperatureMapper.selectkanban(projectId);}
	
}
