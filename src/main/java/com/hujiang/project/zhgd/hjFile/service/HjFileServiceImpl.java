package com.hujiang.project.zhgd.hjFile.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjFile.mapper.HjFileMapper;
import com.hujiang.project.zhgd.hjFile.domain.HjFile;
import com.hujiang.project.zhgd.hjFile.service.IHjFileService;
import com.hujiang.common.support.Convert;

/**
 * 文件路径 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjFileServiceImpl implements IHjFileService 
{
	@Autowired
	private HjFileMapper hjFileMapper;

	/**
	 * 获取项目已使用文件存储量和总存储量
	 * @param projectId
	 * @return
	 */
	public Map selectHjFileSize(Integer projectId){
		return hjFileMapper.selectHjFileSize(projectId);
	}
	/**
	 * 删除文件路径
	 *
	 * @param folderId 文件夹id
	 * @return 结果
	 */
	public int deleteHjFileByFolderId(Integer folderId){
		return hjFileMapper.deleteHjFileByFolderId(folderId);
	}
	/**
     * 查询文件路径信息
     * 
     * @param id 文件路径ID
     * @return 文件路径信息
     */
    @Override
	public HjFile selectHjFileById(Integer id)
	{
	    return hjFileMapper.selectHjFileById(id);
	}
	
	/**
     * 查询文件路径列表
     * 
     * @param hjFile 文件路径信息
     * @return 文件路径集合
     */
	@Override
	public List<HjFile> selectHjFileList(HjFile hjFile)
	{
	    return hjFileMapper.selectHjFileList(hjFile);
	}
	
    /**
     * 新增文件路径
     * 
     * @param hjFile 文件路径信息
     * @return 结果
     */
	@Override
	public int insertHjFile(HjFile hjFile)
	{
	    return hjFileMapper.insertHjFile(hjFile);
	}
	
	/**
     * 修改文件路径
     * 
     * @param hjFile 文件路径信息
     * @return 结果
     */
	@Override
	public int updateHjFile(HjFile hjFile)
	{
	    return hjFileMapper.updateHjFile(hjFile);
	}

	/**
     * 删除文件路径对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjFileByIds(String ids)
	{
		return hjFileMapper.deleteHjFileByIds(Convert.toStrArray(ids));
	}
	
}
