package com.hujiang.project.zhgd.hjghformwork.service;

import java.util.List;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbAvg;
import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkDataMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.common.support.Convert;

/**
 * 高支模数据记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class HighformworkDataServiceImpl implements IHighformworkDataService 
{
	@Autowired
	private HighformworkDataMapper highformworkDataMapper;

	/**
     * 查询高支模数据记录信息
     * 
     * @param id 高支模数据记录ID
     * @return 高支模数据记录信息
     */
    @Override
	public HighformworkData selectHighformworkDataById(Integer id)
	{
	    return highformworkDataMapper.selectHighformworkDataById(id);
	}
	
	/**
     * 查询高支模数据记录列表
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 高支模数据记录集合
     */
	@Override
	public List<HighformworkData> selectHighformworkDataList(HighformworkData highformworkData)
	{
	    return highformworkDataMapper.selectHighformworkDataList(highformworkData);
	}

	@Override
	public List<HighformworkData> selectHighformworkDataListTask(HighformworkData highformworkData) {
		return highformworkDataMapper.selectHighformworkDataListTask(highformworkData);
	}

	/**
     * 新增高支模数据记录
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 结果
     */
	@Override
	public int insertHighformworkData(HighformworkData highformworkData)
	{
	    return highformworkDataMapper.insertHighformworkData(highformworkData);
	}
	
	/**
     * 修改高支模数据记录
     * 
     * @param highformworkData 高支模数据记录信息
     * @return 结果
     */
	@Override
	public int updateHighformworkData(HighformworkData highformworkData)
	{
	    return highformworkDataMapper.updateHighformworkData(highformworkData);
	}

	/**
     * 删除高支模数据记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHighformworkDataByIds(String ids)
	{
		return highformworkDataMapper.deleteHighformworkDataByIds(Convert.toStrArray(ids));
	}

	@Override
	public String selectParmeterMax(SbAvg parmeter) {
		return highformworkDataMapper.selectParmeterMax(parmeter);
	}

	@Override
	public String selectParmeterMin(SbAvg parmeter) {
		return highformworkDataMapper.selectParmeterMin(parmeter);
	}

	@Override
	public String selectParmeterAvg(SbAvg parmeter) {
		return highformworkDataMapper.selectParmeterAvg(parmeter);
	}

	@Override
	public List<HighformworkData> selectHighformworkDataListT(Integer factorId, String startTime, String endTime) {
		return highformworkDataMapper.selectHighformworkDataListT(factorId,startTime,endTime);
	}

	/**
	 * 图表数据点
	 * @param factorId
	 * @param param
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
	public String selectHighformworkDataListV(Integer factorId,String param,String startTime,String endTime){
		return highformworkDataMapper.selectHighformworkDataListV(factorId,param,startTime,endTime);
	}



}
