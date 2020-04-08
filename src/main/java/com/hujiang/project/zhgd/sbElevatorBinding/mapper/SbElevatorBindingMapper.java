package com.hujiang.project.zhgd.sbElevatorBinding.mapper;

import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 升降机绑定 数据层
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public interface SbElevatorBindingMapper 
{
	public List<SbElevatorBinding> selectHxzId(@Param(value = "pid")int pid);
	/**
     * 查询升降机绑定信息
     * 
     * @param id 升降机绑定ID
     * @return 升降机绑定信息
     */
	public SbElevatorBinding selectSbElevatorBindingById(Integer id);
	
	/**
     * 查询升降机绑定列表
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 升降机绑定集合
     */
	public List<SbElevatorBinding> selectSbElevatorBindingList(SbElevatorBinding sbElevatorBinding);
	
	/**
     * 新增升降机绑定
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 结果
     */
	public int insertSbElevatorBinding(SbElevatorBinding sbElevatorBinding);
	
	/**
     * 修改升降机绑定
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 结果
     */
	public int updateSbElevatorBinding(SbElevatorBinding sbElevatorBinding);
	
	/**
     * 删除升降机绑定
     * 
     * @param id 升降机绑定ID
     * @return 结果
     */
	public int deleteSbElevatorBindingById(Integer id);
	
	/**
     * 批量删除升降机绑定
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorBindingByIds(String[] ids);

	/**
	 * 查询某项目下升降机数量
	 * @return 结果
	 * */
	public List<SbElevatorBinding> list(SbElevatorBinding info);

	List<SbElevatorBinding> selectSbElevatorBindingByPid(Integer pid);

	SbElevatorBinding selectOneSbElevatorBinding(Integer pid, String hxzid);
}