package com.hujiang.project.zhgd.sbCraneWorkloop.service;

import com.hujiang.project.zhgd.sbCraneWorkloop.domain.SbCraneWorkloop;
import java.util.List;

/**
 * 塔机工作循环数据 服务层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface ISbCraneWorkloopService 
{
	/**
     * 查询塔机工作循环数据信息
     * 
     * @param id 塔机工作循环数据ID
     * @return 塔机工作循环数据信息
     */
	public SbCraneWorkloop selectSbCraneWorkloopById(Integer id);
	
	/**
     * 查询塔机工作循环数据列表
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 塔机工作循环数据集合
     */
	public List<SbCraneWorkloop> selectSbCraneWorkloopList(SbCraneWorkloop sbCraneWorkloop);
	
	/**
     * 新增塔机工作循环数据
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 结果
     */
	public int insertSbCraneWorkloop(SbCraneWorkloop sbCraneWorkloop);
	
	/**
     * 修改塔机工作循环数据
     * 
     * @param sbCraneWorkloop 塔机工作循环数据信息
     * @return 结果
     */
	public int updateSbCraneWorkloop(SbCraneWorkloop sbCraneWorkloop);
		
	/**
     * 删除塔机工作循环数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneWorkloopByIds(String ids);

	/**
	 * 查询塔吊预警数据
	 *
	 * @param hxzid
	 * @return 结果
	 * */
	public SbCraneWorkloop selectTD(String hxzid);
}
