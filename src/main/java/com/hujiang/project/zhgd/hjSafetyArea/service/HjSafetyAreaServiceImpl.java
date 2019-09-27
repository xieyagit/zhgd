package com.hujiang.project.zhgd.hjSafetyArea.service;

import java.util.List;

import com.hujiang.project.zhgd.hjSafetyArea.domain.SafetyArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSafetyArea.mapper.HjSafetyAreaMapper;
import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.service.IHjSafetyAreaService;
import com.hujiang.common.support.Convert;

/**
 * 巡检工区 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-23
 */
@Service
public class HjSafetyAreaServiceImpl implements IHjSafetyAreaService 
{
	@Autowired
	private HjSafetyAreaMapper hjSafetyAreaMapper;

	@Override
	public List<SafetyArea> getOptionsList(Integer projectId) {
		return hjSafetyAreaMapper.getOptionsList(projectId);
	}

	@Override
	public List<SafetyArea> getUserByArea(Integer areaId) {
		return hjSafetyAreaMapper.getUserByArea(areaId);
	}

	@Override
	public int insertConstructionArea(SafetyArea safetyArea) {
		return hjSafetyAreaMapper.insertConstructionArea(safetyArea);
	}

	/**
     * 查询巡检工区信息
     * 
     * @param id 巡检工区ID
     * @return 巡检工区信息
     */
    @Override
	public HjSafetyArea selectHjSafetyAreaById(Integer id)
	{
	    return hjSafetyAreaMapper.selectHjSafetyAreaById(id);
	}

	@Override
	public int deleteAreaAndUser(Integer areaId) {
		return hjSafetyAreaMapper.deleteAreaAndUser(areaId);
	}

	@Override
	public int deleteAreaAndConstruction(Integer areaId) {
		return hjSafetyAreaMapper.deleteAreaAndConstruction(areaId);
	}

	@Override
	public int deleteArea(Integer areaId) {
		return hjSafetyAreaMapper.deleteArea(areaId);
	}

	/**
     * 查询巡检工区列表
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 巡检工区集合
     */
	@Override
	public List<HjSafetyArea> selectHjSafetyAreaList(HjSafetyArea hjSafetyArea)
	{
	    return hjSafetyAreaMapper.selectHjSafetyAreaList(hjSafetyArea);
	}
	
    /**
     * 新增巡检工区
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 结果
     */
	@Override
	public int insertHjSafetyArea(HjSafetyArea hjSafetyArea)
	{
	    return hjSafetyAreaMapper.insertHjSafetyArea(hjSafetyArea);
	}
	
	/**
     * 修改巡检工区
     * 
     * @param hjSafetyArea 巡检工区信息
     * @return 结果
     */
	@Override
	public int updateHjSafetyArea(HjSafetyArea hjSafetyArea)
	{
	    return hjSafetyAreaMapper.updateHjSafetyArea(hjSafetyArea);
	}

	@Override
	public int updateConstructionArea(SafetyArea safetyArea) {
		return hjSafetyAreaMapper.updateConstructionArea(safetyArea);
	}

	@Override
	public int insertAreaUser(SafetyArea safetyArea) {
		return hjSafetyAreaMapper.insertAreaUser(safetyArea);
	}

	@Override
	public int deleteAreaUser(Integer userId, Integer areaId) {
		return hjSafetyAreaMapper.deleteAreaUser(userId, areaId);
	}

	@Override
	public SafetyArea getAreaUser(SafetyArea safetyArea) {
		return hjSafetyAreaMapper.getAreaUser(safetyArea);
	}

	/**
     * 删除巡检工区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSafetyAreaByIds(String ids)
	{
		return hjSafetyAreaMapper.deleteHjSafetyAreaByIds(Convert.toStrArray(ids));
	}
	
}
