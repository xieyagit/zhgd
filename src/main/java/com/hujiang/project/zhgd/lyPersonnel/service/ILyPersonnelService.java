package com.hujiang.project.zhgd.lyPersonnel.service;

import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import java.util.List;

/**
 * 楼宇人员 服务层
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public interface ILyPersonnelService 
{
	/**
     * 查询楼宇人员信息
     * 
     * @param id 楼宇人员ID
     * @return 楼宇人员信息
     */
	public LyPersonnel selectLyPersonnelById(Integer id);
	
	/**
     * 查询楼宇人员列表
     * 
     * @param lyPersonnel 楼宇人员信息
     * @return 楼宇人员集合
     */
	public List<LyPersonnel> selectLyPersonnelList(LyPersonnel lyPersonnel);
	
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
     * 删除楼宇人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyPersonnelByIds(String ids);

	public void personnelInOUt(LyPersonnel lyPersonnel,String ispresent);
}
