package com.hujiang.project.zhgd.hjTeam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjTeam.domain.TeamParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjTeam.mapper.HjTeamMapper;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.common.support.Convert;

/**
 * 班组 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjTeamServiceImpl implements IHjTeamService
{
	@Autowired
	private HjTeamMapper hjTeamMapper;

	@Override
	public HjTeam getHjTeam(HjTeam hjTeam) {
		return hjTeamMapper.getHjTeam(hjTeam);
	}

	/**
     * 查询班组信息
     * 
     * @param id 班组ID
     * @return 班组信息
     */
    @Override
	public HjTeam selectHjTeamById(Integer id)
	{
	    return hjTeamMapper.selectHjTeamById(id);
	}
	
	/**
     * 查询班组列表
     * 
     * @param hjTeam 班组信息
     * @return 班组集合
     */
	@Override
	public List<HjTeam> selectHjTeamList(HjTeam hjTeam)
	{
	    return hjTeamMapper.selectHjTeamList(hjTeam);
	}
	
    /**
     * 新增班组
     * 
     * @param hjTeam 班组信息
     * @return 结果
     */
	@Override
	public int insertHjTeam(HjTeam hjTeam)
	{
	    return hjTeamMapper.insertHjTeam(hjTeam);
	}
	
	/**
     * 修改班组
     * 
     * @param hjTeam 班组信息
     * @return 结果
     */
	@Override
	public int updateHjTeam(HjTeam hjTeam)
	{
	    return hjTeamMapper.updateHjTeam(hjTeam);
	}

	/**
     * 删除班组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjTeamByIds(String ids)
	{
		return hjTeamMapper.deleteHjTeamByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查参建单位下的班组
	 * @param hjTeam 参建单位id
	 * @return
	 */
	@Override
	public Map<String, Object> selectTeam(HjTeam hjTeam) {

		try {

			List<HjTeam> list = hjTeamMapper.selectHjTeamList(hjTeam);
			if(list.size() > 0){
				List<TeamParam> teamParamList = new ArrayList<>();
				for (int i = 0; i < list.size() ; i++) {
					TeamParam teamParam = new TeamParam();
					teamParam.setId(list.get(i).getId());
					teamParam.setTeamName(list.get(i).getTeamName());
					teamParamList.add(teamParam);
				}
               return AjaxResult.success(teamParamList);
			}
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
		return AjaxResult.error(-1,"该参建单位下无班组！");
	}

}
