package com.hujiang.project.zhgd.sbUnloaderRegistration.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderRegistration.mapper.SbUnloaderRegistrationMapper;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.SbUnloaderRegistration;
import com.hujiang.project.zhgd.sbUnloaderRegistration.service.ISbUnloaderRegistrationService;
import com.hujiang.common.support.Convert;

/**
 * 卸料注册 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class SbUnloaderRegistrationServiceImpl implements ISbUnloaderRegistrationService 
{
	@Autowired
	private SbUnloaderRegistrationMapper sbUnloaderRegistrationMapper;

	/**
     * 查询卸料注册信息
     * 
     * @param id 卸料注册ID
     * @return 卸料注册信息
     */
    @Override
	public SbUnloaderRegistration selectSbUnloaderRegistrationById(Integer id)
	{
	    return sbUnloaderRegistrationMapper.selectSbUnloaderRegistrationById(id);
	}
	
	/**
     * 查询卸料注册列表
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 卸料注册集合
     */
	@Override
	public List<SbUnloaderRegistration> selectSbUnloaderRegistrationList(SbUnloaderRegistration sbUnloaderRegistration)
	{
	    return sbUnloaderRegistrationMapper.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
	}
	
    /**
     * 新增卸料注册
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 结果
     */
	@Override
	public int insertSbUnloaderRegistration(SbUnloaderRegistration sbUnloaderRegistration)
	{
	    return sbUnloaderRegistrationMapper.insertSbUnloaderRegistration(sbUnloaderRegistration);
	}
	
	/**
     * 修改卸料注册
     * 
     * @param sbUnloaderRegistration 卸料注册信息
     * @return 结果
     */
	@Override
	public int updateSbUnloaderRegistration(SbUnloaderRegistration sbUnloaderRegistration)
	{
	    return sbUnloaderRegistrationMapper.updateSbUnloaderRegistration(sbUnloaderRegistration);
	}

	/**
     * 删除卸料注册对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbUnloaderRegistrationByIds(String ids)
	{
		return sbUnloaderRegistrationMapper.deleteSbUnloaderRegistrationByIds(Convert.toStrArray(ids));
	}
	
}
