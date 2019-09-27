package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitToken;
import java.util.List;	

/**
 * 深基坑token 数据层
 * 
 * @author hujiang
 * @date 2019-09-03
 */
public interface SbDeeppitTokenMapper 
{
	/**
     * 查询深基坑token信息
     * 
     * @param id 深基坑tokenID
     * @return 深基坑token信息
     */
	public SbDeeppitToken selectSbDeeppitTokenById(Integer id);
	
	/**
     * 查询深基坑token列表
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 深基坑token集合
     */
	public List<SbDeeppitToken> selectSbDeeppitTokenList(SbDeeppitToken sbDeeppitToken);
	
	/**
     * 新增深基坑token
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 结果
     */
	public int insertSbDeeppitToken(SbDeeppitToken sbDeeppitToken);
	
	/**
     * 修改深基坑token
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 结果
     */
	public int updateSbDeeppitToken(SbDeeppitToken sbDeeppitToken);
	
	/**
     * 删除深基坑token
     * 
     * @param id 深基坑tokenID
     * @return 结果
     */
	public int deleteSbDeeppitTokenById(Integer id);
	
	/**
     * 批量删除深基坑token
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbDeeppitTokenByIds(String[] ids);
	
}