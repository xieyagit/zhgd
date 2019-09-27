package com.hujiang.project.zhgd.sbProjectElectricityBox.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbPowerBoxAdd;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 项目电箱 服务层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface ISbProjectElectricityBoxService 
{

	/**
	 * 查询项目电箱信息
	 * @param projectId 项目ID
	 * @param hxzid 设备号
	 * @return 项目电箱信息
	 */
	public List<SbProjectElectricityBox> selectByProjectIdAndHxzId(Integer projectId,String hxzid);

	/**
     * 查询项目电箱信息
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
	 * 上报电箱参数
	 * @param
	 * @return
	 * @author yant
	 */
	public AjaxResult reportedEBox(SbPowerBoxAdd sbPowerBoxAdd) throws IOException, URISyntaxException;

	/**
	 * 上报电箱实时数据
	 * @param
	 * @return
	 * @author yant
	 */
	public AjaxResult reportedEBoxData(SbPowerBoxAdd sbPowerBoxAdd) throws IOException, URISyntaxException;
	
	/**
     * 修改项目电箱
     * 
     * @param sbProjectElectricityBox 项目电箱信息
     * @return 结果
     */
	public int updateSbProjectElectricityBox(SbProjectElectricityBox sbProjectElectricityBox);
		
	/**
     * 删除项目电箱信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbProjectElectricityBoxByIds(String ids);
	
}
