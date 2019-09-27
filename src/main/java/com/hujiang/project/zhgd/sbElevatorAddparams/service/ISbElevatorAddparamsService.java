package com.hujiang.project.zhgd.sbElevatorAddparams.service;

import com.hujiang.project.zhgd.sbElevatorAddparams.domain.OptionsElevator;
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.SbElevatorAddparams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 升降机参数   服务层
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public interface ISbElevatorAddparamsService 
{
	//平台设置：升降机列表
	public List<OptionsElevator> getElevatorList(Integer projectId);

	public int insertElevator(OptionsElevator optionsElevator);

	public int updateElevator(OptionsElevator optionsElevator);

	public int deleteElevator(@Param("id")Integer id);
	/**
     * 查询升降机参数  信息
     * 
     * @param id 升降机参数  ID
     * @return 升降机参数  信息
     */
	public SbElevatorAddparams selectSbElevatorAddparamsById(Integer id);
	
	/**
     * 查询升降机参数  列表
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 升降机参数  集合
     */
	public List<SbElevatorAddparams> selectSbElevatorAddparamsList(SbElevatorAddparams sbElevatorAddparams);
	
	/**
     * 新增升降机参数  
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 结果
     */
	public int insertSbElevatorAddparams(SbElevatorAddparams sbElevatorAddparams);
	
	/**
     * 修改升降机参数  
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 结果
     */
	public int updateSbElevatorAddparams(SbElevatorAddparams sbElevatorAddparams);
		
	/**
     * 删除升降机参数  信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorAddparamsByIds(String ids);
	
}
