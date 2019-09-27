package com.hujiang.project.zhgd.hjReport.service;

import com.hujiang.project.zhgd.hjReport.domain.HjReport;
import com.hujiang.project.zhgd.hjReport.domain.HjReportPc;

import java.util.List;
import java.util.Map;

/**
 * 工作汇报 服务层
 * 
 * @author hujiang
 * @date 2019-07-03
 */
public interface IHjReportService 
{
	/**
     * 查询工作汇报信息
     * 
     * @param id 工作汇报ID
     * @return 工作汇报信息
     */
	public HjReport selectHjReportById(Integer id);
	
	/**
     * 查询工作汇报列表
     * 
     * @param hjReport 工作汇报信息
     * @return 工作汇报集合
     */
	public List<HjReport> selectHjReportList(HjReport hjReport);
	
	/**
     * 新增工作汇报
     * 
     * @param hjReport 工作汇报信息
     * @return 结果
     */
	public int insertHjReport(HjReport hjReport);
	
	/**
     * 修改工作汇报
     * 
     * @param hjReport 工作汇报信息
     * @return 结果
     */
	public int updateHjReport(HjReport hjReport);
		
	/**
     * 删除工作汇报信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjReportByIds(String ids);

	/**
	 * 添加
	 * */
	public Integer insterall(HjReport report);

	/**
	 * 查询
	 * */
	public List<HjReport> select(HjReport hjReport);

	/**
	 * 删除
	 * */
	public int delete(Integer id);

	/**
	 * 查询报告列表
	 * @param hjReport
	 * @return
	 */
	public List<HjReportPc> selects(HjReport hjReport);
}
