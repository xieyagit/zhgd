package com.hujiang.project.zhgd.sbVersion.service;

import com.hujiang.project.zhgd.sbVersion.domain.SbVersion;
import java.util.List;

/**
 * 版本 服务层
 * 
 * @author hujiang
 * @date 2019-08-26
 */
public interface ISbVersionService 
{
	/**
     * 查询版本信息
     * 
     * @param id 版本ID
     * @return 版本信息
     */
	public SbVersion selectSbVersionById(Integer id);

	public SbVersion selectSbVersion();
	
	/**
     * 查询版本列表
     * 
     * @param sbVersion 版本信息
     * @return 版本集合
     */
	public List<SbVersion> selectSbVersionList(SbVersion sbVersion);
	
	/**
     * 新增版本
     * 
     * @param sbVersion 版本信息
     * @return 结果
     */
	public int insertSbVersion(SbVersion sbVersion);
	
	/**
     * 修改版本
     * 
     * @param sbVersion 版本信息
     * @return 结果
     */
	public int updateSbVersion(SbVersion sbVersion);
		
	/**
     * 删除版本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbVersionByIds(String ids);
	
}
