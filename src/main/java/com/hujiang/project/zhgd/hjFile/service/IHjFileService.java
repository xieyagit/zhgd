package com.hujiang.project.zhgd.hjFile.service;

import com.hujiang.project.zhgd.hjFile.domain.HjFile;
import java.util.List;
import java.util.Map;

/**
 * 文件路径 服务层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface IHjFileService 
{
	/**
     * 查询文件路径信息
     * 
     * @param id 文件路径ID
     * @return 文件路径信息
     */
	public HjFile selectHjFileById(Integer id);
	/**
	 * 获取项目已使用文件存储量和总存储量
	 * @param projectId
	 * @return
	 */
	public Map selectHjFileSize(Integer projectId);
	/**
	 * 删除文件路径
	 *
	 * @param folderId 文件夹id
	 * @return 结果
	 */
	public int deleteHjFileByFolderId(Integer folderId);
	/**
     * 查询文件路径列表
     * 
     * @param hjFile 文件路径信息
     * @return 文件路径集合
     */
	public List<HjFile> selectHjFileList(HjFile hjFile);
	
	/**
     * 新增文件路径
     * 
     * @param hjFile 文件路径信息
     * @return 结果
     */
	public int insertHjFile(HjFile hjFile);
	
	/**
     * 修改文件路径
     * 
     * @param hjFile 文件路径信息
     * @return 结果
     */
	public int updateHjFile(HjFile hjFile);
		
	/**
     * 删除文件路径信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjFileByIds(String ids);
	
}
