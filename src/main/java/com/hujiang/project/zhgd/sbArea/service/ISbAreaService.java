package com.hujiang.project.zhgd.sbArea.service;

import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.sbArea.domain.Area;
import com.hujiang.project.zhgd.sbArea.domain.OptionsLocation;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbHire.domain.SbAreaLocaltion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工区 服务层
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public interface ISbAreaService 
{
	public List<OptionsUser> getUserList(Integer areaId, String filed,Integer projectId);
	public OptionsUser getAreaUserById(Integer userId);
	public int updateAreaUser(OptionsUser optionsUser);
	public int updateUserPhone(OptionsUser optionsUser);
	public int deleteAreaUser(Integer userId,Integer areaId);
	public int deleteUser(Integer userId);
	public int insertAreaUser(OptionsUser optionsUser);
	public int insertUserPhone(OptionsUser optionsUser);
	public OptionsLocation getAreaById(Integer areaId);
	/**
	 * 定位工业区列表
	 * @return
	 */
	public List<OptionsLocation> getAreaList(Integer projectId);
	public int updateArea(OptionsLocation optionsLocation);
	/**
	 * 增加
	 * @return
	 */
	public int addArea(OptionsLocation optionsLocation);
	public int addAreaLocaltion(List<SbAreaLocaltion> sbAreaLocaltionList);
	public int deleteArea(Integer areaId);

	/**
     * 查询工区信息
     * 
     * @param id 工区ID
     * @return 工区信息
     */
	public SbArea selectSbAreaById(Integer id);
	
	/**
     * 查询工区列表
     * 
     * @param sbArea 工区信息
     * @return 工区集合
     */
	public List<SbArea> selectSbAreaList(SbArea sbArea);
	
	/**
     * 新增工区
     * 
     * @param sbArea 工区信息
     * @return 结果
     */
	public int insertSbArea(SbArea sbArea);
	
	/**
     * 修改工区
     * 
     * @param sbArea 工区信息
     * @return 结果
     */
	public int updateSbArea(SbArea sbArea);
		
	/**
     * 删除工区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbAreaByIds(String ids);

	/**
	 * 设置电子围栏半径
	 * @return 结果
	 */
	public int updateRadius(SbArea sbArea);
}
