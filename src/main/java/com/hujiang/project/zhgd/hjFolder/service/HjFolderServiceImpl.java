package com.hujiang.project.zhgd.hjFolder.service;

import java.util.List;

import com.hujiang.project.zhgd.utils.FolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjFolder.mapper.HjFolderMapper;
import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;
import com.hujiang.project.zhgd.hjFolder.service.IHjFolderService;
import com.hujiang.common.support.Convert;

/**
 * 文件夹 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjFolderServiceImpl implements IHjFolderService 
{
	@Autowired
	private HjFolderMapper hjFolderMapper;

	/**
     * 查询文件夹信息
     * 
     * @param id 文件夹ID
     * @return 文件夹信息
     */
    @Override
	public HjFolder selectHjFolderById(Integer id)
	{
	    return hjFolderMapper.selectHjFolderById(id);
	}
	
	/**
     * 查询文件夹列表
     * 
     * @param hjFolder 文件夹信息
     * @return 文件夹集合
     */
	@Override
	public List<HjFolder> selectHjFolderList(HjFolder hjFolder)
	{
		List<HjFolder> hjFolders = hjFolderMapper.selectHjFolderList(hjFolder);
		if(hjFolders==null){
			return null;
		}
	    return FolderUtils.recursion(hjFolders,hjFolders.get(0).getParentLevel());
	}

	public List<HjFolder> selectHjFolderByParentLevel(Integer parentLevel){
		return hjFolderMapper.selectHjFolderByParentLevel(parentLevel);
	}
	
    /**
     * 新增文件夹
     * 
     * @param hjFolder 文件夹信息
     * @return 结果
     */
	@Override
	public int insertHjFolder(HjFolder hjFolder)
	{
	    return hjFolderMapper.insertHjFolder(hjFolder);
	}
	
	/**
     * 修改文件夹
     * 
     * @param hjFolder 文件夹信息
     * @return 结果
     */
	@Override
	public int updateHjFolder(HjFolder hjFolder)
	{
	    return hjFolderMapper.updateHjFolder(hjFolder);
	}

	/**
     * 删除文件夹对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjFolderByIds(String ids)
	{
		return hjFolderMapper.deleteHjFolderByIds(Convert.toStrArray(ids));
	}
	
}
