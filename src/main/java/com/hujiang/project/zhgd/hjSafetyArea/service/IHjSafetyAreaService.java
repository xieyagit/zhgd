package com.hujiang.project.zhgd.hjSafetyArea.service;

import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.domain.SafetyArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 巡检工区 服务层
 * 
 * @author hujiang
 * @date 2019-07-23
 */
public interface IHjSafetyAreaService 
{
	public List<SafetyArea> getOptionsList(Integer projectId);
	public List<SafetyArea> getUserByArea(Integer areaId);
	public int insertConstructionArea(SafetyArea safetyArea);
	/**
     * 查询巡检工区信息
     * 
     * @param id 巡检工区ID
     * @return 巡检工区信息
     */
	public HjSafetyArea selectHjSafetyAreaById(Integer id);

	/**
	 * 删除工业区
	 */
	public int deleteAreaAndUser(Integer areaId);
	public int deleteAreaAndConstruction(Integer areaId);
	public int deleteArea(Integer areaId);
	
	/**
     * 查询巡检工区列表
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 巡检工区集合
     */
	public List<HjSafetyArea> selectHjSafetyAreaList(HjSafetyArea hjSafetyArea);
	
	/**
     * 新增巡检工区
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 结果
     */
	public int insertHjSafetyArea(HjSafetyArea hjSafetyArea);
	
	/**
     * 修改巡检工区
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 结果
     */
	public int updateHjSafetyArea(HjSafetyArea hjSafetyArea);
	public int updateConstructionArea(SafetyArea safetyArea);

	/**
	 * 增加负责人
	 * @param safetyArea
	 * @return
	 */
	public int insertAreaUser(SafetyArea safetyArea);
	public int deleteAreaUser(Integer userId,Integer areaId);
	public SafetyArea getAreaUser(SafetyArea safetyArea);
	/**
     * 删除巡检工区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyAreaByIds(String ids);
	
}
