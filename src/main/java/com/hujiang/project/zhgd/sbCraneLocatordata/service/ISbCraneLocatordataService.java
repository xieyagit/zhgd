package com.hujiang.project.zhgd.sbCraneLocatordata.service;

import com.hujiang.project.zhgd.sbCraneLocatordata.domain.SbCraneLocatordata;
import java.util.List;

/**
 * 塔吊GPS定位数据
 服务层
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public interface ISbCraneLocatordataService 
{
	/**
     * 查询塔吊GPS定位数据
信息
     * 
     * @param id 塔吊GPS定位数据
ID
     * @return 塔吊GPS定位数据
信息
     */
	public SbCraneLocatordata selectSbCraneLocatordataById(Integer id);
	
	/**
     * 查询塔吊GPS定位数据
列表
     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 塔吊GPS定位数据
集合
     */
	public List<SbCraneLocatordata> selectSbCraneLocatordataList(SbCraneLocatordata sbCraneLocatordata);
	
	/**
     * 新增塔吊GPS定位数据

     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 结果
     */
	public int insertSbCraneLocatordata(SbCraneLocatordata sbCraneLocatordata);
	
	/**
     * 修改塔吊GPS定位数据

     * 
     * @param sbCraneLocatordata 塔吊GPS定位数据
信息
     * @return 结果
     */
	public int updateSbCraneLocatordata(SbCraneLocatordata sbCraneLocatordata);
		
	/**
     * 删除塔吊GPS定位数据
信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneLocatordataByIds(String ids);
	
}
