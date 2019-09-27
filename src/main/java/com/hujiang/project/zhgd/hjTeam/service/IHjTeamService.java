package com.hujiang.project.zhgd.hjTeam.service;

import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import java.util.List;
import java.util.Map;

/**
 * 班组 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjTeamService 
{
	/**
	 * 查询班组信息
	 *
	 * @return 班组信息
	 */
	public HjTeam getHjTeam(HjTeam hjTeam);
	/**
     * 查询班组信息
     * 
     * @param id 班组ID
     * @return 班组信息
     */
	public HjTeam selectHjTeamById(Integer id);
	
	/**
     * 查询班组列表
     * 
     * @param hjTeam 班组信息
     * @return 班组集合
     */
	public List<HjTeam> selectHjTeamList(HjTeam hjTeam);
	
	/**
     * 新增班组
     * 
     * @param hjTeam 班组信息
     * @return 结果
     */
	public int insertHjTeam(HjTeam hjTeam);
	
	/**
     * 修改班组
     * 
     * @param hjTeam 班组信息
     * @return 结果
     */
	public int updateHjTeam(HjTeam hjTeam);
		
	/**
     * 删除班组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjTeamByIds(String ids);

	/**
	 * 查参建单位下的班组
	 * @param hjTeam 参建单位id
	 * @return
	 */
    Map<String, Object> selectTeam(HjTeam hjTeam);
}
