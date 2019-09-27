package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.SbDeeppitTokenMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitToken;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitTokenService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑token 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-03
 */
@Service
public class SbDeeppitTokenServiceImpl implements ISbDeeppitTokenService 
{
	@Autowired
	private SbDeeppitTokenMapper sbDeeppitTokenMapper;

	/**
     * 查询深基坑token信息
     *
     * @param id 深基坑tokenID
     * @return 深基坑token信息
     */
    @Override
	public SbDeeppitToken selectSbDeeppitTokenById(Integer id)
	{
	    return sbDeeppitTokenMapper.selectSbDeeppitTokenById(id);
	}

	/**
	 * 查询深基坑token信息
	 *
	 * @param appId appId
	 * @return 深基坑token信息
	 */
	@Override
	public SbDeeppitToken selectDeeppitTokenByAppId(Integer appId) {
		return null;
	}

	/**
	 * 查询深基坑token信息
	 *
	 * @param appId appId
	 * @return 深基坑token信息
	 */
	
	/**
     * 查询深基坑token列表
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 深基坑token集合
     */
	@Override
	public List<SbDeeppitToken> selectSbDeeppitTokenList(SbDeeppitToken sbDeeppitToken)
	{
	    return sbDeeppitTokenMapper.selectSbDeeppitTokenList(sbDeeppitToken);
	}
	
    /**
     * 新增深基坑token
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 结果
     */
	@Override
	public int insertSbDeeppitToken(SbDeeppitToken sbDeeppitToken)
	{
	    return sbDeeppitTokenMapper.insertSbDeeppitToken(sbDeeppitToken);
	}
	
	/**
     * 修改深基坑token
     * 
     * @param sbDeeppitToken 深基坑token信息
     * @return 结果
     */
	@Override
	public int updateSbDeeppitToken(SbDeeppitToken sbDeeppitToken)
	{
	    return sbDeeppitTokenMapper.updateSbDeeppitToken(sbDeeppitToken);
	}

	/**
     * 删除深基坑token对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbDeeppitTokenByIds(String ids)
	{
		return sbDeeppitTokenMapper.deleteSbDeeppitTokenByIds(Convert.toStrArray(ids));
	}
	
}
