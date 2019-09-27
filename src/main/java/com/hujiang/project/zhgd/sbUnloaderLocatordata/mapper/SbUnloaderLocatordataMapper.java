package com.hujiang.project.zhgd.sbUnloaderLocatordata.mapper;

import com.hujiang.project.zhgd.sbUnloaderLocatordata.domain.SbUnloaderLocatordata;
import java.util.List;	

/**
 * 卸料GPS定位数据
 数据层
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public interface SbUnloaderLocatordataMapper 
{
	/**
     * 查询卸料GPS定位数据
信息
     * 
     * @param id 卸料GPS定位数据
ID
     * @return 卸料GPS定位数据
信息
     */
	public SbUnloaderLocatordata selectSbUnloaderLocatordataById(Integer id);
	
	/**
     * 查询卸料GPS定位数据
列表
     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 卸料GPS定位数据
集合
     */
	public List<SbUnloaderLocatordata> selectSbUnloaderLocatordataList(SbUnloaderLocatordata sbUnloaderLocatordata);
	
	/**
     * 新增卸料GPS定位数据

     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 结果
     */
	public int insertSbUnloaderLocatordata(SbUnloaderLocatordata sbUnloaderLocatordata);
	
	/**
     * 修改卸料GPS定位数据

     * 
     * @param sbUnloaderLocatordata 卸料GPS定位数据
信息
     * @return 结果
     */
	public int updateSbUnloaderLocatordata(SbUnloaderLocatordata sbUnloaderLocatordata);
	
	/**
     * 删除卸料GPS定位数据

     * 
     * @param id 卸料GPS定位数据
ID
     * @return 结果
     */
	public int deleteSbUnloaderLocatordataById(Integer id);
	
	/**
     * 批量删除卸料GPS定位数据

     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderLocatordataByIds(String[] ids);
	
}