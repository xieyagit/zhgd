package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitStructuresMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitStructuresService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑结构物 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Service
public class SbDeeppitStructuresServiceImpl implements ISbDeeppitStructuresService 
{
	@Autowired
	private SbDeeppitStructuresMapper sbDeeppitStructuresMapper;

	/**
     * 查询深基坑结构物信息
     * 
     * @param masterKey 深基坑结构物ID
     * @return 深基坑结构物信息
     */
    @Override
	public SbDeeppitStructures selectSbDeeppitStructuresByMasterKey(String masterKey)
	{
	    return sbDeeppitStructuresMapper.selectSbDeeppitStructuresByMasterKey(masterKey);
	}
	
	/**
     * 查询深基坑结构物列表
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 深基坑结构物集合
     */
	@Override
	public List<SbDeeppitStructures> selectSbDeeppitStructuresList(SbDeeppitStructures sbDeeppitStructures)
	{
	    return sbDeeppitStructuresMapper.selectSbDeeppitStructuresList(sbDeeppitStructures);
	}
	
    /**
     * 新增深基坑结构物
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitStructures(SbDeeppitStructures sbDeeppitStructures)
	{
	    return sbDeeppitStructuresMapper.insertSbDeeppitStructures(sbDeeppitStructures);
	}
	
	/**
     * 修改深基坑结构物
     * 
     * @param sbDeeppitStructures 深基坑结构物信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitStructures(SbDeeppitStructures sbDeeppitStructures)
	{
	    return sbDeeppitStructuresMapper.updateSbDeeppitStructures(sbDeeppitStructures);
	}

	@Override
	public int updateSbDeeppitStructuresO(SbDeeppitStructures sbDeeppitStructures) {
		return sbDeeppitStructuresMapper.updateSbDeeppitStructuresO(sbDeeppitStructures);
	}

	/**
     * 删除深基坑结构物对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitStructuresByIds(String ids)
	{
		return sbDeeppitStructuresMapper.deleteSbDeeppitStructuresByIds(Convert.toStrArray(ids));
	}
	
}
