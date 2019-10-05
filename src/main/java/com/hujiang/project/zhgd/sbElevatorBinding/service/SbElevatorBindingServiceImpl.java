package com.hujiang.project.zhgd.sbElevatorBinding.service;

import java.util.List;

import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.sbElevatorBinding.mapper.SbElevatorBindingMapper;
import com.hujiang.common.support.Convert;

/**
 * 升降机绑定 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Service
public class SbElevatorBindingServiceImpl implements ISbElevatorBindingService 
{
	@Autowired
	private SbElevatorBindingMapper sbElevatorBindingMapper;

	@Override
	public List<SbElevatorBinding> selectHxzId(int pid) {
		return sbElevatorBindingMapper.selectHxzId(pid);
	}

	/**
     * 查询升降机绑定信息
     * 
     * @param id 升降机绑定ID
     * @return 升降机绑定信息
     */
    @Override
	public SbElevatorBinding selectSbElevatorBindingById(Integer id)
	{
	    return sbElevatorBindingMapper.selectSbElevatorBindingById(id);
	}
	
	/**
     * 查询升降机绑定列表
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 升降机绑定集合
     */
	@Override
	public List<SbElevatorBinding> selectSbElevatorBindingList(SbElevatorBinding sbElevatorBinding)
	{
	    return sbElevatorBindingMapper.selectSbElevatorBindingList(sbElevatorBinding);
	}
	
    /**
     * 新增升降机绑定
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorBinding(SbElevatorBinding sbElevatorBinding)
	{
	    return sbElevatorBindingMapper.insertSbElevatorBinding(sbElevatorBinding);
	}
	
	/**
     * 修改升降机绑定
     * 
     * @param sbElevatorBinding 升降机绑定信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorBinding(SbElevatorBinding sbElevatorBinding)
	{
	    return sbElevatorBindingMapper.updateSbElevatorBinding(sbElevatorBinding);
	}

	/**
     * 删除升降机绑定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorBindingByIds(String ids)
	{
		return sbElevatorBindingMapper.deleteSbElevatorBindingByIds(Convert.toStrArray(ids));
	}


	/**
	 * 查询某项目下升降机数量
	 * @return 结果
	 * */
	public List<SbElevatorBinding> list(SbElevatorBinding info){
		return sbElevatorBindingMapper.list(info);
	}
	
}
