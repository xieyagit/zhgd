package com.hujiang.project.zhgd.sbVersion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbVersion.mapper.SbVersionMapper;
import com.hujiang.project.zhgd.sbVersion.domain.SbVersion;
import com.hujiang.project.zhgd.sbVersion.service.ISbVersionService;
import com.hujiang.common.support.Convert;

/**
 * 版本 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-26
 */
@Service
public class SbVersionServiceImpl implements ISbVersionService 
{
	@Autowired
	private SbVersionMapper sbVersionMapper;

	/**
     * 查询版本信息
     * 
     * @param id 版本ID
     * @return 版本信息
     */
    @Override
	public SbVersion selectSbVersionById(Integer id)
	{
	    return sbVersionMapper.selectSbVersionById(id);
	}

	@Override
	public SbVersion selectSbVersion() {
		return sbVersionMapper.selectSbVersion();
	}

	/**
     * 查询版本列表
     * 
     * @param sbVersion 版本信息
     * @return 版本集合
     */
	@Override
	public List<SbVersion> selectSbVersionList(SbVersion sbVersion)
	{
	    return sbVersionMapper.selectSbVersionList(sbVersion);
	}
	
    /**
     * 新增版本
     * 
     * @param sbVersion 版本信息
     * @return 结果
     */
	@Override
	public int insertSbVersion(SbVersion sbVersion)
	{
	    return sbVersionMapper.insertSbVersion(sbVersion);
	}
	
	/**
     * 修改版本
     * 
     * @param sbVersion 版本信息
     * @return 结果
     */
	@Override
	public int updateSbVersion(SbVersion sbVersion)
	{
	    return sbVersionMapper.updateSbVersion(sbVersion);
	}

	/**
     * 删除版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbVersionByIds(String ids)
	{
		return sbVersionMapper.deleteSbVersionByIds(Convert.toStrArray(ids));
	}
	
}
