package com.hujiang.project.zhgd.lyPersonnel.mapper;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnelRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 楼宇人员 数据层
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public interface LyPersonnelMapper 
{
	/**
     * 查询楼宇人员信息
     * 
     * @param id 楼宇人员ID
     * @return 楼宇人员信息
     */
	public LyPersonnel selectLyPersonnelById(Integer id);
	public Integer zzryzs(Integer pid);
	public Integer zzryin(LyAttendanceRecord lyAttendanceRecord);
	public Integer zzryout(LyAttendanceRecord lyAttendanceRecord);
	public Integer fkryzs(LyAttendanceRecord lyAttendanceRecord);
	public Integer fkryin(LyAttendanceRecord lyAttendanceRecord);
	public Integer fkryout(LyAttendanceRecord lyAttendanceRecord);
	public Integer zzryinout(LyAttendanceRecord lyAttendanceRecord);
	public Integer fkryinout(LyAttendanceRecord lyAttendanceRecord);
	/**
     * 查询楼宇人员列表
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 楼宇人员集合
     */
	public List<LyPersonnel> selectLyPersonnelList(LyPersonnel lyPersonnel);
	public List<LyCompanyPersonnel> getLyCompanyPersonnel(LyPersonnel lyPersonnel);
	
	/**
     * 新增楼宇人员
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 结果
     */
	public int insertLyPersonnel(LyPersonnel lyPersonnel);
	
	/**
     * 修改楼宇人员
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 结果
     */
	public int updateLyPersonnel(LyPersonnel lyPersonnel);
	
	/**
     * 删除楼宇人员
     * 
     * @param id 楼宇人员ID
     * @return 结果
     */
	public int deleteLyPersonnelById(Integer id);
	
	/**
     * 批量删除楼宇人员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyPersonnelByIds(String[] ids);

	public List<LyPersonnelRecord> getLyPersonnelRecordZZ(LyAttendanceRecord lyAttendanceRecord);
	public List<LyPersonnelRecord> getLyPersonnelRecordFK(LyAttendanceRecord lyAttendanceRecord);
	public int insertBlacklist(@Param("ids") String ids);
	public int deleteBlacklist(@Param("ids") String ids);
}