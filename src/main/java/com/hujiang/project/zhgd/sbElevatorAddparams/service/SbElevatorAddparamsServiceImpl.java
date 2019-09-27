package com.hujiang.project.zhgd.sbElevatorAddparams.service;

import java.util.List;

import com.hujiang.project.zhgd.sbElevatorAddparams.domain.OptionsElevator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbElevatorAddparams.mapper.SbElevatorAddparamsMapper;
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.SbElevatorAddparams;
import com.hujiang.project.zhgd.sbElevatorAddparams.service.ISbElevatorAddparamsService;
import com.hujiang.common.support.Convert;

/**
 * 升降机参数   服务层实现
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Service
public class SbElevatorAddparamsServiceImpl implements ISbElevatorAddparamsService
{
	@Autowired
	private SbElevatorAddparamsMapper sbElevatorAddparamsMapper;

	@Override
	public List<OptionsElevator> getElevatorList(Integer projectId) {
		return sbElevatorAddparamsMapper.getElevatorList(projectId);
	}

	@Override
	public int insertElevator(OptionsElevator optionsElevator) {
		return sbElevatorAddparamsMapper.insertElevator(optionsElevator);
	}

	@Override
	public int updateElevator(OptionsElevator optionsElevator) {
		return sbElevatorAddparamsMapper.updateElevator(optionsElevator);
	}

	@Override
	public int deleteElevator(Integer id) {
		return sbElevatorAddparamsMapper.deleteElevator(id);
	}

	/**
     * 查询升降机参数  信息
     * 
     * @param id 升降机参数  ID
     * @return 升降机参数  信息
     */
    @Override
	public SbElevatorAddparams selectSbElevatorAddparamsById(Integer id)
	{
	    return sbElevatorAddparamsMapper.selectSbElevatorAddparamsById(id);
	}
	
	/**
     * 查询升降机参数  列表
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 升降机参数  集合
     */
	@Override
	public List<SbElevatorAddparams> selectSbElevatorAddparamsList(SbElevatorAddparams sbElevatorAddparams)
	{
	    return sbElevatorAddparamsMapper.selectSbElevatorAddparamsList(sbElevatorAddparams);
	}
	
    /**
     * 新增升降机参数  
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorAddparams(SbElevatorAddparams sbElevatorAddparams)
	{
	    return sbElevatorAddparamsMapper.insertSbElevatorAddparams(sbElevatorAddparams);
	}
	
	/**
     * 修改升降机参数  
     * 
     * @param sbElevatorAddparams 升降机参数  信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorAddparams(SbElevatorAddparams sbElevatorAddparams)
	{
	    return sbElevatorAddparamsMapper.updateSbElevatorAddparams(sbElevatorAddparams);
	}

	/**
     * 删除升降机参数  对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorAddparamsByIds(String ids)
	{
		return sbElevatorAddparamsMapper.deleteSbElevatorAddparamsByIds(Convert.toStrArray(ids));
	}
	
}
