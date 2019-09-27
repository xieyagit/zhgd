package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitFactorMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitFactorService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑测点 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Service
public class SbDeeppitFactorServiceImpl implements ISbDeeppitFactorService 
{
	@Autowired
	private SbDeeppitFactorMapper sbDeeppitFactorMapper;

	/**
     * 查询深基坑测点信息
     * 
     * @param id 深基坑测点ID
     * @return 深基坑测点信息
     */
    @Override
	public SbDeeppitFactor selectSbDeeppitFactorById(Integer id)
	{
	    return sbDeeppitFactorMapper.selectSbDeeppitFactorById(id);
	}
	
	/**
     * 查询深基坑测点列表
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 深基坑测点集合
     */
	@Override
	public List<SbDeeppitFactor> selectSbDeeppitFactorList(SbDeeppitFactor sbDeeppitFactor)
	{
	    return sbDeeppitFactorMapper.selectSbDeeppitFactorList(sbDeeppitFactor);
	}
	
    /**
     * 新增深基坑测点
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitFactor(SbDeeppitFactor sbDeeppitFactor)
	{
	    return sbDeeppitFactorMapper.insertSbDeeppitFactor(sbDeeppitFactor);
	}
	
	/**
     * 修改深基坑测点
     * 
     * @param sbDeeppitFactor 深基坑测点信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitFactor(SbDeeppitFactor sbDeeppitFactor)
	{
	    return sbDeeppitFactorMapper.updateSbDeeppitFactor(sbDeeppitFactor);
	}

	/**
     * 删除深基坑测点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitFactorByIds(String ids)
	{
		return sbDeeppitFactorMapper.deleteSbDeeppitFactorByIds(Convert.toStrArray(ids));
	}
	
}
