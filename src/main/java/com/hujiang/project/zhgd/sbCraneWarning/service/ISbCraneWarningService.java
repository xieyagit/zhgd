package com.hujiang.project.zhgd.sbCraneWarning.service;

import com.hujiang.project.zhgd.sbCraneWarning.domain.SbCraneWarning;
import java.util.List;
import java.util.Map;

/**
 * 塔机预警数据 服务层
 * 
 * @author hujiang
 * @date 2019-06-21
 */
public interface ISbCraneWarningService 
{
	/**
     * 查询塔机预警数据信息
     * 
     * @param id 塔机预警数据ID
     * @return 塔机预警数据信息
     */
	public SbCraneWarning selectSbCraneWarningById(Long id);
	
	/**
     * 查询塔机预警数据列表
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 塔机预警数据集合
     */
	public List<SbCraneWarning> selectSbCraneWarningList(SbCraneWarning sbCraneWarning);
	
	/**
     * 新增塔机预警数据
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 结果
     */
	public int insertSbCraneWarning(SbCraneWarning sbCraneWarning);
	
	/**
     * 修改塔机预警数据
     * 
     * @param sbCraneWarning 塔机预警数据信息
     * @return 结果
     */
	public int updateSbCraneWarning(SbCraneWarning sbCraneWarning);
		
	/**
     * 删除塔机预警数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbCraneWarningByIds(String ids);

	public Integer selectCraneCountOne(Map<String,Object> map);
	public Integer selectCraneCountTwo(Map<String,Object> map);
	public Integer selectCraneCountThree(Map<String,Object> map);
}
