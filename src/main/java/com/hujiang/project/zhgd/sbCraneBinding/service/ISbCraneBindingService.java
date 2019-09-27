package com.hujiang.project.zhgd.sbCraneBinding.service;

import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import java.util.List;

/**
 * 塔吊设备绑定 服务层
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public interface ISbCraneBindingService 
{
	public List<SbCraneBinding> selectByHxzId(int pid);
	/**
     * 查询塔吊设备绑定信息
     * 
     * @param id 塔吊设备绑定ID
     * @return 塔吊设备绑定信息
     */
	public SbCraneBinding selectSbCraneBindingById(Integer id);
	
	/**
     * 查询塔吊设备绑定列表
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 塔吊设备绑定集合
     */
	public List<SbCraneBinding> selectSbCraneBindingList(SbCraneBinding sbCraneBinding);
	
	/**
     * 新增塔吊设备绑定
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 结果
     */
	public int insertSbCraneBinding(SbCraneBinding sbCraneBinding);
	
	/**
     * 修改塔吊设备绑定
     * 
     * @param sbCraneBinding 塔吊设备绑定信息
     * @return 结果
     */
	public int updateSbCraneBinding(SbCraneBinding sbCraneBinding);
		
	/**
     * 删除塔吊设备绑定信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneBindingByIds(String ids);



}
