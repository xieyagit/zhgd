package com.hujiang.project.zhgd.sbEquipmentWarning.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbEquipmentWarning.mapper.SbEquipmentWarningMapper;
import com.hujiang.project.zhgd.sbEquipmentWarning.domain.SbEquipmentWarning;
import com.hujiang.project.zhgd.sbEquipmentWarning.service.ISbEquipmentWarningService;
import com.hujiang.common.support.Convert;

/**
 * 定位报警 服务层实现
 * 
 * @author hujiang
 * @date 2019-10-19
 */
@Service
public class SbEquipmentWarningServiceImpl implements ISbEquipmentWarningService 
{
	@Autowired
	private SbEquipmentWarningMapper sbEquipmentWarningMapper;

	@Override
	public int warningCount(SbEquipmentWarning sbEquipmentWarning) {
		return sbEquipmentWarningMapper.warningCount(sbEquipmentWarning);
	}

	@Override
	public List<SbEquipmentWarning> getWarningList(SbEquipmentWarning sbEquipmentWarning) {
		return sbEquipmentWarningMapper.getWarningList(sbEquipmentWarning);
	}

	/**
     * 查询定位报警信息
     * 
     * @param id 定位报警ID
     * @return 定位报警信息
     */
    @Override
	public SbEquipmentWarning selectSbEquipmentWarningById(Integer id)
	{
	    return sbEquipmentWarningMapper.selectSbEquipmentWarningById(id);
	}
	
	/**
     * 查询定位报警列表
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 定位报警集合
     */
	@Override
	public List<SbEquipmentWarning> selectSbEquipmentWarningList(SbEquipmentWarning sbEquipmentWarning)
	{
	    return sbEquipmentWarningMapper.selectSbEquipmentWarningList(sbEquipmentWarning);
	}

	@Override
	public SbEquipmentWarning selectSbEquipmentWarning() {
		return sbEquipmentWarningMapper.selectSbEquipmentWarning();
	}

	/**
     * 新增定位报警
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 结果
     */
	@Override
	public int insertSbEquipmentWarning(List<SbEquipmentWarning> sbEquipmentWarning)
	{
	    return sbEquipmentWarningMapper.insertSbEquipmentWarning(sbEquipmentWarning);
	}
	
	/**
     * 修改定位报警
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 结果
     */
	@Override
	public int updateSbEquipmentWarning(SbEquipmentWarning sbEquipmentWarning)
	{
	    return sbEquipmentWarningMapper.updateSbEquipmentWarning(sbEquipmentWarning);
	}

	/**
     * 删除定位报警对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbEquipmentWarningByIds(String ids)
	{
		return sbEquipmentWarningMapper.deleteSbEquipmentWarningByIds(Convert.toStrArray(ids));
	}
	
}
