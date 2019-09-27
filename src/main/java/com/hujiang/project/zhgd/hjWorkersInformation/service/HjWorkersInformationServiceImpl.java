package com.hujiang.project.zhgd.hjWorkersInformation.service;

import java.util.List;

import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformationPc;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjWorkersInformation.mapper.HjWorkersInformationMapper;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformation;
import com.hujiang.project.zhgd.hjWorkersInformation.service.IHjWorkersInformationService;
import com.hujiang.common.support.Convert;

/**
 * 工人 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Service
public class HjWorkersInformationServiceImpl implements IHjWorkersInformationService 
{
	@Autowired
	private HjWorkersInformationMapper hjWorkersInformationMapper;

	/**
     * 查询工人信息
     * 
     * @param id 工人ID
     * @return 工人信息
     */
    @Override
	public HjWorkersInformation selectHjWorkersInformationById(Integer id)
	{
	    return hjWorkersInformationMapper.selectHjWorkersInformationById(id);
	}
	
	/**
     * 查询工人列表
     * 
     * @param hjWorkersInformation 工人信息
     * @return 工人集合
     */
	@Override
	public List<HjWorkersInformation> selectHjWorkersInformationList(HjWorkersInformation hjWorkersInformation)
	{
	    return hjWorkersInformationMapper.selectHjWorkersInformationList(hjWorkersInformation);
	}
	
    /**
     * 新增工人
     * 
     * @param hjWorkersInformation 工人信息
     * @return 结果
     */
	@Override
	public int insertHjWorkersInformation(HjWorkersInformation hjWorkersInformation)
	{
	    return hjWorkersInformationMapper.insertHjWorkersInformation(hjWorkersInformation);
	}
	
	/**
     * 修改工人
     * 
     * @param hjWorkersInformation 工人信息
     * @return 结果
     */
	@Override
	public int updateHjWorkersInformation(HjWorkersInformation hjWorkersInformation)
	{
	    return hjWorkersInformationMapper.updateHjWorkersInformation(hjWorkersInformation);
	}

	/**
     * 删除工人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjWorkersInformationByIds(String ids)
	{
		return hjWorkersInformationMapper.deleteHjWorkersInformationByIds(Convert.toStrArray(ids));
	}


	/**
	 * 上传
	 * */
	public int insteradd(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.insteradd(hjWorkersInformation);
	}

	/**
	 * 查询所有
	 * */
	public List<HjWorkersInformation> selectall(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.selectall(hjWorkersInformation);
	}

	public List<HjWorkersInformationPc> selectalls(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.selectalls(hjWorkersInformation);
	}

	/**
	 * 修改（继续上传其他证件）
	 * */
	public int update(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update(hjWorkersInformation);
	}
	public int update1(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update1(hjWorkersInformation);
	}
	public int update2(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update2(hjWorkersInformation);
	}
	public int update3(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update3(hjWorkersInformation);
	}
	public int update4(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update4(hjWorkersInformation);
	}
	public int update5(HjWorkersInformation hjWorkersInformation){
		return hjWorkersInformationMapper.update5(hjWorkersInformation);
	}

	/**
	 *   修改证件是否齐全
	 * */
	public int updatematerial(Integer id){
		return hjWorkersInformationMapper.updatematerial(id);
	}
	public int updatematerials(Integer userId){
		return hjWorkersInformationMapper.updatematerials(userId);
	}

	/**
	 * 查看详情
	 * */
	public HjWorkersInformation sele(Integer userId){
		return hjWorkersInformationMapper.sele(userId);
	}

	/***
	 * 删除
	 * */
	public int delete(Integer id){
		return hjWorkersInformationMapper.delete(id);
	}

	/**
	 * 查询质料齐全的人数
	 * */
	public Integer selectzhiliaoqiquan(Integer projectId){
		return hjWorkersInformationMapper.selectzhiliaoqiquan(projectId);
	}

	/**
	 * 查询劳务工人总人数
	 * */
	public Integer all(Integer projectId){
		return hjWorkersInformationMapper.all(projectId);
	}

	/**
	 * 查询简易劳动合同已有的人数
	 * */
	public Integer jyht(Integer projectId){
		return hjWorkersInformationMapper.jyht(projectId);
	}

	/**
	 * 查询两制确认书已有的人数
	 * */
	public Integer lzqrs(Integer projectId){
		return hjWorkersInformationMapper.lzqrs(projectId);
	}
	/**
	 * 查询进场确认书已有的人数
	 * */
	public Integer jcqrs(Integer projectId){
		return hjWorkersInformationMapper.jcqrs(projectId);
	}

	/**
	 * 查询身份证正反面复印件书已有的人数
	 * */
	public Integer sfzfyj(Integer projectId){
		return hjWorkersInformationMapper.sfzfyj(projectId);
	}
	/**
	 * 查询退场确认书已有的人数
	 * */
	public Integer come(Integer projectId){
		return hjWorkersInformationMapper.come(projectId);
	}

	/**
	 * 在场人数
	 * */
	public Integer zaichsng(Integer projectId){
		return hjWorkersInformationMapper.zaichsng(projectId);
	}

	/**查询班组*/
	public List<HjWorkersInformation> team(Integer projectId){
		return hjWorkersInformationMapper.team(projectId);
	}

	/** 查询工种*/
	public List<HjWorkersInformation> dictionaries(Integer projectId){
		return hjWorkersInformationMapper.dictionaries(projectId);
	}
	/** 查询单位*/
	public List<HjWorkersInformation> company(Integer projectId){
		return hjWorkersInformationMapper.company(projectId);
	}
}
