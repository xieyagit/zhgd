package com.hujiang.project.zhgd.sbUnloaderRegistration.mapper;

import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.SbUnloaderRegistration;
import java.util.List;	

/**
 * 卸料注册 数据层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface SbUnloaderRegistrationMapper 
{
	/**
     * 查询卸料注册信息
     * 
     * @param id 卸料注册ID
     * @return 卸料注册信息
     */
	public SbUnloaderRegistration selectSbUnloaderRegistrationById(Integer id);
	
	/**
     * 查询卸料注册列表
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 卸料注册集合
     */
	public List<SbUnloaderRegistration> selectSbUnloaderRegistrationList(SbUnloaderRegistration sbUnloaderRegistration);
	
	/**
     * 新增卸料注册
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 结果
     */
	public int insertSbUnloaderRegistration(SbUnloaderRegistration sbUnloaderRegistration);
	
	/**
     * 修改卸料注册
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 结果
     */
	public int updateSbUnloaderRegistration(SbUnloaderRegistration sbUnloaderRegistration);
	
	/**
     * 删除卸料注册
     * 
     * @param id 卸料注册ID
     * @return 结果
     */
	public int deleteSbUnloaderRegistrationById(Integer id);
	
	/**
     * 批量删除卸料注册
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderRegistrationByIds(String[] ids);
	
}