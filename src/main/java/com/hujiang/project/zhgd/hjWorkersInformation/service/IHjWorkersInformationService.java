package com.hujiang.project.zhgd.hjWorkersInformation.service;

import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformation;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformationPc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工人 服务层
 * 
 * @author hujiang
 * @date 2019-07-03
 */
public interface IHjWorkersInformationService 
{
	/**
     * 查询工人信息
     * 
     * @param id 工人ID
     * @return 工人信息
     */
	public HjWorkersInformation selectHjWorkersInformationById(Integer id);
	
	/**
     * 查询工人列表
     * 
     * @param hjWorkersInformation 工人信息
     * @return 工人集合
     */
	public List<HjWorkersInformation> selectHjWorkersInformationList(HjWorkersInformation hjWorkersInformation);
	
	/**
     * 新增工人
     * 
     * @param hjWorkersInformation 工人信息
     * @return 结果
     */
	public int insertHjWorkersInformation(HjWorkersInformation hjWorkersInformation);
	
	/**
     * 修改工人
     * 
     * @param hjWorkersInformation 工人信息
     * @return 结果
     */
	public int updateHjWorkersInformation(HjWorkersInformation hjWorkersInformation);
		
	/**
     * 删除工人信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjWorkersInformationByIds(String ids);

	/**
	 * 上传
	 * */
	public int insteradd(HjWorkersInformation hjWorkersInformation);

	/**
	 * 查询所有
	 * */
	public List<HjWorkersInformation> selectall(HjWorkersInformation hjWorkersInformation);
	public List<HjWorkersInformationPc> selectalls(HjWorkersInformation hjWorkersInformation);

	/**
	 * 修改（继续上传其他证件）
	 * */
	public int update(HjWorkersInformation hjWorkersInformation);
	public int update1(HjWorkersInformation hjWorkersInformation);
	public int update2(HjWorkersInformation hjWorkersInformation);
	public int update3(HjWorkersInformation hjWorkersInformation);
	public int update4(HjWorkersInformation hjWorkersInformation);
	public int update5(HjWorkersInformation hjWorkersInformation);

	/**
	 *   修改证件是否齐全
	 * */
	public int updatematerial(Integer id);
	public int updatematerials(Integer userId);

	/**
	 * 查看详情
	 * */
	public HjWorkersInformation sele(Integer userId);

	/***
	 * 删除
	 * */
	public int delete(Integer id);

	/**
	 * 查询质料齐全的人数
	 * */
	public Integer selectzhiliaoqiquan(Integer projectId);

	/**
	 * 查询劳务工人总人数
	 * */
	public Integer all(Integer projectId);

	/**
	 * 查询简易劳动合同已有的人数
	 * */
	public Integer jyht(Integer projectId);

	/**
	 * 查询两制确认书已有的人数
	 * */
	public Integer lzqrs(Integer projectId);
	/**
	 * 查询进场确认书已有的人数
	 * */
	public Integer jcqrs(Integer projectId);

	/**
	 * 查询身份证正反面复印件书已有的人数
	 * */
	public Integer sfzfyj(Integer projectId);
	/**
	 * 查询退场确认书已有的人数
	 * */
	public Integer come(Integer projectId);

	/**
	 * 在场人数
	 * */
	public Integer zaichsng(Integer projectId);

	/**查询班组*/
	public List<HjWorkersInformation> team(Integer projectId);

	/** 查询工种*/
	public List<HjWorkersInformation> dictionaries(Integer projectId);

	/** 查询单位*/
	public List<HjWorkersInformation> company(Integer projectId);
}
