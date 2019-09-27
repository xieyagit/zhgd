package com.hujiang.project.zhgd.sbProjectElectricityBox.mapper;

import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import java.util.List;
import java.util.Map;

/**
 * 项目电箱 数据层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface SbProjectElectricityBoxMapper 
{
	/**
	 * 根据项目ID，设备号查询电箱信息
	 *
	 * @param map 参数
	 * @return 项目电箱信息
	 */
	public List<SbProjectElectricityBox> selectByProjectIdAndHxzId(Map<String,Object> map);

	/**
     * 查询项目电箱信息gu
     * 
     * @param id 项目电箱ID
     * @return 项目电箱信息
     */
	public SbProjectElectricityBox selectSbProjectElectricityBoxById(Integer id);
	
	/**
     * 查询项目电箱列表
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 项目电箱集合
     */
	public List<SbProjectElectricityBox> selectSbProjectElectricityBoxList(SbProjectElectricityBox sbProjectElectricityBox);
	
	/**
     * 新增项目电箱
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 结果
     */
	public int insertSbProjectElectricityBox(SbProjectElectricityBox sbProjectElectricityBox);
	
	/**
     * 修改项目电箱
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 结果
     */
	public int updateSbProjectElectricityBox(SbProjectElectricityBox sbProjectElectricityBox);
	
	/**
     * 删除项目电箱
     * 
     * @param id 项目电箱ID
     * @return 结果
     */
	public int deleteSbProjectElectricityBoxById(Integer id);
	
	/**
     * 批量删除项目电箱
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectElectricityBoxByIds(String[] ids);
	
}