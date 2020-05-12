package com.hujiang.project.zhgd.hjNanhuId.mapper;

import com.hujiang.project.zhgd.hjNanhuId.domain.HjNanhuId;
import java.util.List;	

/**
 * 南湖项目id-我系统项目id 数据层
 * 
 * @author hujiang
 * @date 2020-04-27
 */
public interface HjNanhuIdMapper 
{
	/**
     * 查询南湖项目id-我系统项目id信息
     * 
     * @param id 南湖项目id-我系统项目idID
     * @return 南湖项目id-我系统项目id信息
     */
	public HjNanhuId selectHjNanhuIdById(Integer id);
	
	/**
     * 查询南湖项目id-我系统项目id列表
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 南湖项目id-我系统项目id集合
     */
	public List<HjNanhuId> selectHjNanhuIdList(HjNanhuId hjNanhuId);
	
	/**
     * 新增南湖项目id-我系统项目id
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 结果
     */
	public int insertHjNanhuId(HjNanhuId hjNanhuId);
	
	/**
     * 修改南湖项目id-我系统项目id
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 结果
     */
	public int updateHjNanhuId(HjNanhuId hjNanhuId);
	
	/**
     * 删除南湖项目id-我系统项目id
     * 
     * @param id 南湖项目id-我系统项目idID
     * @return 结果
     */
	public int deleteHjNanhuIdById(Integer id);
	
	/**
     * 批量删除南湖项目id-我系统项目id
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjNanhuIdByIds(String[] ids);
	
}