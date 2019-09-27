package com.hujiang.project.zhgd.sbArea.service;

import java.util.List;

import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.sbArea.domain.Area;
import com.hujiang.project.zhgd.sbArea.domain.OptionsLocation;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbArea.mapper.SbAreaMapper;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import com.hujiang.common.support.Convert;

/**
 * 工区 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Service
public class SbAreaServiceImpl implements ISbAreaService
{
	@Autowired
	private SbAreaMapper sbAreaMapper;


	@Override
	public List<OptionsUser> getUserList(Integer areaId, String filed, Integer projectId) {
		return sbAreaMapper.getUserList(areaId, filed, projectId);
	}

	@Override
	public OptionsUser getAreaUserById(Integer userId) {
		return sbAreaMapper.getAreaUserById(userId);
	}


	@Override
	public int updateAreaUser(OptionsUser optionsUser) {
		return sbAreaMapper.updateAreaUser(optionsUser);
	}

	@Override
	public int updateUserPhone(OptionsUser optionsUser) {
		return sbAreaMapper.updateUserPhone(optionsUser);
	}

	@Override
	public int deleteAreaUser(Integer userId,Integer areaId) {
		return sbAreaMapper.deleteAreaUser(userId,areaId);
	}

	@Override
	public int deleteUser(Integer userId) {
		return sbAreaMapper.deleteUser(userId);
	}

	@Override
	public int insertAreaUser(OptionsUser optionsUser) {
		return sbAreaMapper.insertAreaUser(optionsUser);
	}

	@Override
	public int insertUserPhone(OptionsUser optionsUser) {
		return sbAreaMapper.insertUserPhone(optionsUser);
	}

	@Override
	public OptionsLocation getAreaById(Integer areaId) {
		return sbAreaMapper.getAreaById(areaId);
	}

	@Override
	public List<OptionsLocation> getAreaList(Integer projectId) {
		return sbAreaMapper.getAreaList(projectId);
	}

	@Override
	public int updateArea(OptionsLocation optionsLocation) {
		return sbAreaMapper.updateArea(optionsLocation);
	}

	@Override
	public int addArea(OptionsLocation optionsLocation) {
		return sbAreaMapper.addArea(optionsLocation);
	}

	@Override
	public int deleteArea(Integer areaId) {
		return sbAreaMapper.deleteArea(areaId);
	}

	/**
     * 查询工区信息
     * 
     * @param id 工区ID
     * @return 工区信息
     */
    @Override
	public SbArea selectSbAreaById(Integer id)
	{
	    return sbAreaMapper.selectSbAreaById(id);
	}
	
	/**
     * 查询工区列表
     * 
     * @param sbArea 工区信息
     * @return 工区集合
     */
	@Override
	public List<SbArea> selectSbAreaList(SbArea sbArea)
	{
	    return sbAreaMapper.selectSbAreaList(sbArea);
	}
	
    /**
     * 新增工区
     * 
     * @param sbArea 工区信息
     * @return 结果
     */
	@Override
	public int insertSbArea(SbArea sbArea)
	{
	    return sbAreaMapper.insertSbArea(sbArea);
	}
	
	/**
     * 修改工区
     * 
     * @param sbArea 工区信息
     * @return 结果
     */
	@Override
	public int updateSbArea(SbArea sbArea)
	{
	    return sbAreaMapper.updateSbArea(sbArea);
	}

	/**
     * 删除工区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbAreaByIds(String ids)
	{
		return sbAreaMapper.deleteSbAreaByIds(Convert.toStrArray(ids));
	}

	/**
	 * 设置电子围栏半径
	 *
	 * @return 结果
	 */
	@Override
	public int updateRadius(SbArea sbArea){
		return sbAreaMapper.updateRadius(sbArea);
	}
	
}
