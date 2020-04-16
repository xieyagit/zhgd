package com.hujiang.project.zhgd.sbArea.mapper;

import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.sbArea.domain.Area;
import com.hujiang.project.zhgd.sbArea.domain.OptionsLocation;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbHire.domain.SbAreaLocaltion;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 工区 数据层
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public interface SbAreaMapper 
{
	public List<OptionsUser> getUserList(@Param("areaId") Integer areaId,
										 @Param("filed")String filed,
										 @Param("projectId")Integer projectId);
	public OptionsUser getAreaUserById(@Param("userId") Integer userId);
	public int updateAreaUser(OptionsUser optionsUser);
	public int updateUserPhone(OptionsUser optionsUser);
	public int deleteAreaUser(@Param("userId") Integer userId,@Param("areaId")Integer areaId);
	public int deleteUser(Integer userId);
	public int insertAreaUser(OptionsUser optionsUser);
	public int insertUserPhone(OptionsUser optionsUser);
	/**
	 * 定位工业区列表
	 * @return
	 */
	public List<OptionsLocation> getAreaList(Integer projectId);

	public OptionsLocation getAreaById(Integer areaId);

	/**
	 * 修改
	 * @param optionsLocation
	 * @return
	 */
	public int updateArea(OptionsLocation optionsLocation);

	/**
	 * 增加
	 * @return
	 */
	public int addArea(OptionsLocation optionsLocation);
	public int addAreaLocaltion(List<SbAreaLocaltion> sbAreaLocaltionList);
	public int deleteArea(Integer areaId);

	public List<Area> selectProject();
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
     * 删除工区
     * 
     * @param id 工区ID
     * @return 结果
     */
	public int deleteSbAreaById(Integer id);
	
	/**
     * 批量删除工区
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbAreaByIds(String[] ids);

	/**
	 * 设置电子围栏半径
	 * @return 结果
	 */
	public int updateRadius(SbArea sbArea);

	
}