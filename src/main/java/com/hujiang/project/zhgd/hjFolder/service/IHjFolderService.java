package com.hujiang.project.zhgd.hjFolder.service;

import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;
import java.util.List;

/**
 * 文件夹 服务层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface IHjFolderService 
{
	/**
     * 查询文件夹信息
     * 
     * @param id 文件夹ID
     * @return 文件夹信息
     */
	public HjFolder selectHjFolderById(Integer id);
	
	/**
     * 查询文件夹列表
     * 
     * @param hjFolder 文件夹信息
     * @return 文件夹集合
     */
	public List<HjFolder> selectHjFolderList(HjFolder hjFolder);

	public List<HjFolder> selectHjFolderByParentLevel(Integer parentLevel);
	
	/**
     * 新增文件夹
     * 
     * @param hjFolder 文件夹信息
     * @return 结果
     */
	public int insertHjFolder(HjFolder hjFolder);
	
	/**
     * 修改文件夹
     * 
     * @param hjFolder 文件夹信息
     * @return 结果
     */
	public int updateHjFolder(HjFolder hjFolder);
		
	/**
     * 删除文件夹信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjFolderByIds(String ids);
	
}
