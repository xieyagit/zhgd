package com.hujiang.project.zhgd.sbUnloaderBinding.mapper;

import com.hujiang.project.zhgd.sbUnloaderBinding.domain.SbUnloaderBinding;
import java.util.List;	

/**
 * 卸料设备绑定 数据层
 * 
 * @author hujiang
 * @date 2019-09-15
 */
public interface SbUnloaderBindingMapper 
{
	/**
     * 查询卸料设备绑定信息
     * 
     * @param id 卸料设备绑定ID
     * @return 卸料设备绑定信息
     */
	public SbUnloaderBinding selectSbUnloaderBindingById(Integer id);
	
	/**
     * 查询卸料设备绑定列表
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 卸料设备绑定集合
     */
	public List<SbUnloaderBinding> selectSbUnloaderBindingList(SbUnloaderBinding sbUnloaderBinding);
	
	/**
     * 新增卸料设备绑定
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 结果
     */
	public int insertSbUnloaderBinding(SbUnloaderBinding sbUnloaderBinding);
	
	/**
     * 修改卸料设备绑定
     * 
     * @param sbUnloaderBinding 卸料设备绑定信息
     * @return 结果
     */
	public int updateSbUnloaderBinding(SbUnloaderBinding sbUnloaderBinding);
	
	/**
     * 删除卸料设备绑定
     * 
     * @param id 卸料设备绑定ID
     * @return 结果
     */
	public int deleteSbUnloaderBindingById(Integer id);
	
	/**
     * 批量删除卸料设备绑定
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderBindingByIds(String[] ids);
	
}