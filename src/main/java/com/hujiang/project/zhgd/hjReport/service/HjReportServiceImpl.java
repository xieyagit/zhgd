package com.hujiang.project.zhgd.hjReport.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.hjReport.domain.HjReportPc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjReport.mapper.HjReportMapper;
import com.hujiang.project.zhgd.hjReport.domain.HjReport;
import com.hujiang.project.zhgd.hjReport.service.IHjReportService;
import com.hujiang.common.support.Convert;

/**
 * 工作汇报 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Service
public class HjReportServiceImpl implements IHjReportService 
{
	@Autowired
	private HjReportMapper hjReportMapper;

	/**
     * 查询工作汇报信息
     * 
     * @param id 工作汇报ID
     * @return 工作汇报信息
     */
    @Override
	public HjReport selectHjReportById(Integer id)
	{
	    return hjReportMapper.selectHjReportById(id);
	}
	
	/**
     * 查询工作汇报列表
     * 
     * @param hjReport 工作汇报信息
     * @return 工作汇报集合
     */
	@Override
	public List<HjReport> selectHjReportList(HjReport hjReport)
	{
	    return hjReportMapper.selectHjReportList(hjReport);
	}
	
    /**
     * 新增工作汇报
     * 
     * @param hjReport 工作汇报信息
     * @return 结果
     */
	@Override
	public int insertHjReport(HjReport hjReport)
	{
	    return hjReportMapper.insertHjReport(hjReport);
	}
	
	/**
     * 修改工作汇报
     * 
     * @param hjReport 工作汇报信息
     * @return 结果
     */
	@Override
	public int updateHjReport(HjReport hjReport)
	{
	    return hjReportMapper.updateHjReport(hjReport);
	}

	/**
     * 删除工作汇报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjReportByIds(String ids)
	{
		return hjReportMapper.deleteHjReportByIds(Convert.toStrArray(ids));
	}

	/**
	 * 添加
	 * */
	public Integer insterall(HjReport report){
		return hjReportMapper.insterall(report);
	}

	/**
	 * 查询
	 * */
	public List<HjReport> select(HjReport hjReport){
		return hjReportMapper.select(hjReport);
	}

	/**
	 * 删除
	 * */
	public int delete(Integer id){
		return hjReportMapper.delete(id);
	}

	/**
	 * 查询报告列表
	 * @param hjReport
	 * @return
	 */
	public List<HjReportPc> selects(HjReport hjReport){
		return hjReportMapper.selects(hjReport);
	}
}
