package com.hujiang.project.zhgd.sbExcessiveDust.mapper;

import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 扬尘超标记录 数据层
 * 
 * @author hujiang
 * @date 2019-07-25
 */
public interface SbExcessiveDustMapper 
{
	/**
	 * 根据ID查询全部信息
	 * @return
	 */
	public int getExcessiveCount(@Param("projectId")Integer projectId,@Param("userId")Integer userId);
	/**
     * 查询扬尘超标记录信息
     * 
     * @param id 扬尘超标记录ID
     * @return 扬尘超标记录信息
     */
	public SbExcessiveDust selectSbExcessiveDustById(Integer id);
	
	/**
     * 查询扬尘超标记录列表
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 扬尘超标记录集合
     */
	public List<SbExcessiveDust> selectSbExcessiveDustList(SbExcessiveDust sbExcessiveDust);
	
	/**
     * 新增扬尘超标记录
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 结果
     */
	public int insertSbExcessiveDust(SbExcessiveDust sbExcessiveDust);
	
	/**
     * 修改扬尘超标记录
     * 
     * @param sbExcessiveDust 扬尘超标记录信息
     * @return 结果
     */
	public int updateSbExcessiveDust(SbExcessiveDust sbExcessiveDust);
	
	/**
     * 删除扬尘超标记录
     * 
     * @param id 扬尘超标记录ID
     * @return 结果
     */
	public int deleteSbExcessiveDustById(Integer id);
	
	/**
     * 批量删除扬尘超标记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbExcessiveDustByIds(String[] ids);
	
}