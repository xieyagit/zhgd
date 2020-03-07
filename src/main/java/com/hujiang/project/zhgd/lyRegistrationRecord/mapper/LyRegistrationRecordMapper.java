package com.hujiang.project.zhgd.lyRegistrationRecord.mapper;

import com.hujiang.project.zhgd.lyRegistrationRecord.domain.LyRegistrationRecord;
import java.util.List;	

/**
 * 登记记录 数据层
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public interface LyRegistrationRecordMapper 
{
	/**
     * 查询登记记录信息
     * 
     * @param id 登记记录ID
     * @return 登记记录信息
     */
	public LyRegistrationRecord selectLyRegistrationRecordById(Integer id);
	
	/**
     * 查询登记记录列表
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 登记记录集合
     */
	public List<LyRegistrationRecord> selectLyRegistrationRecordList(LyRegistrationRecord lyRegistrationRecord);
	
	/**
     * 新增登记记录
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 结果
     */
	public int insertLyRegistrationRecord(LyRegistrationRecord lyRegistrationRecord);
	
	/**
     * 修改登记记录
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 结果
     */
	public int updateLyRegistrationRecord(LyRegistrationRecord lyRegistrationRecord);
	
	/**
     * 删除登记记录
     * 
     * @param id 登记记录ID
     * @return 结果
     */
	public int deleteLyRegistrationRecordById(Integer id);
	
	/**
     * 批量删除登记记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyRegistrationRecordByIds(String[] ids);
	
}