package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbProjectDeeppitStructuresMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbProjectDeeppitStructuresService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑结构物-项目 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Service
public class SbProjectDeeppitStructuresServiceImpl implements ISbProjectDeeppitStructuresService 
{
	@Autowired
	private SbProjectDeeppitStructuresMapper sbProjectDeeppitStructuresMapper;

	/**
     * 查询深基坑结构物-项目信息
     * 
     * @param id 深基坑结构物-项目ID
     * @return 深基坑结构物-项目信息
     */
    @Override
	public SbProjectDeeppitStructures selectSbProjectDeeppitStructuresById(Integer id)
	{
	    return sbProjectDeeppitStructuresMapper.selectSbProjectDeeppitStructuresById(id);
	}
	
	/**
     * 查询深基坑结构物-项目列表
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 深基坑结构物-项目集合
     */
	@Override
	public List<SbProjectDeeppitStructures> selectSbProjectDeeppitStructuresList(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{
	    return sbProjectDeeppitStructuresMapper.selectSbProjectDeeppitStructuresList(sbProjectDeeppitStructures);
	}

	@Override
	public List<SbProjectDeeppitStructures> selectSbProjectDeeppitStructuresListS(SbProjectDeeppitStructures sbProjectDeeppitStructures) {
		return sbProjectDeeppitStructuresMapper.selectSbProjectDeeppitStructuresListS(sbProjectDeeppitStructures);
	}

	/**
     * 新增深基坑结构物-项目
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 结果
     */
	@Override
	public int insertSbProjectDeeppitStructures(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{
	    return sbProjectDeeppitStructuresMapper.insertSbProjectDeeppitStructures(sbProjectDeeppitStructures);
	}
	
	/**
     * 修改深基坑结构物-项目
     * 
     * @param sbProjectDeeppitStructures 深基坑结构物-项目信息
     * @return 结果
     */
	@Override
	public int updateSbProjectDeeppitStructures(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{
	    return sbProjectDeeppitStructuresMapper.updateSbProjectDeeppitStructures(sbProjectDeeppitStructures);
	}

	/**
     * 删除深基坑结构物-项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbProjectDeeppitStructuresByIds(String ids)
	{
		return sbProjectDeeppitStructuresMapper.deleteSbProjectDeeppitStructuresByIds(Convert.toStrArray(ids));
	}
	
}
