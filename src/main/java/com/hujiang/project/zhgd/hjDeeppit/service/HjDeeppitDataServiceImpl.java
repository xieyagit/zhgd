package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.hjDeeppit.domain.SbAvg;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeeppit.mapper.HjDeeppitDataMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.service.IHjDeeppitDataService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑数据记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-04
 */
@Service
public class HjDeeppitDataServiceImpl implements IHjDeeppitDataService 
{
	@Autowired
	private HjDeeppitDataMapper hjDeeppitDataMapper;

	/**
     * 查询深基坑数据记录信息
     * 
     * @param id 深基坑数据记录ID
     * @return 深基坑数据记录信息
     */
    @Override
	public HjDeeppitData selectHjDeeppitDataById(Integer id)
	{
	    return hjDeeppitDataMapper.selectHjDeeppitDataById(id);
	}
	
	/**
     * 查询深基坑数据记录列表
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 深基坑数据记录集合
     */
	@Override
	public List<HjDeeppitData> selectHjDeeppitDataList(HjDeeppitData hjDeeppitData)
	{
	    return hjDeeppitDataMapper.selectHjDeeppitDataList(hjDeeppitData);
	}

	@Override
	public String selectParmeterMax(SbAvg parmeter) {
		return hjDeeppitDataMapper.selectParmeterMax(parmeter);
	}

	@Override
	public String selectParmeterMin(SbAvg parmeter) {
		return hjDeeppitDataMapper.selectParmeterMin(parmeter);
	}


	/**
	 * 查询最大、最小、平均值
	 * @param parmeter
	 * @return
	 */
	@Override
	public String selectParmeterAvg(SbAvg parmeter){

		return hjDeeppitDataMapper.selectParmeterAvg(parmeter);
	}

	/**
	 * 按时间段查询历史数据
	 * @param factorId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
	public List<HjDeeppitData> selectHjDeeppitDataListT(Integer factorId, String startTime, String endTime){
		return hjDeeppitDataMapper.selectHjDeeppitDataListT(factorId,startTime,endTime);
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
	public String selectHjDeeppitDataListV(Integer factorId,String param ,String startTime,String endTime){
		return hjDeeppitDataMapper.selectHjDeeppitDataListV(factorId,param,startTime,endTime);
	}

    /**
     * 新增深基坑数据记录
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 结果
     */
	@Override
	public int insertHjDeeppitData(HjDeeppitData hjDeeppitData)
	{
	    return hjDeeppitDataMapper.insertHjDeeppitData(hjDeeppitData);
	}
	
	/**
     * 修改深基坑数据记录
     * 
     * @param hjDeeppitData 深基坑数据记录信息
     * @return 结果
     */
	@Override
	public int updateHjDeeppitData(HjDeeppitData hjDeeppitData)
	{
	    return hjDeeppitDataMapper.updateHjDeeppitData(hjDeeppitData);
	}

	/**
     * 删除深基坑数据记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjDeeppitDataByIds(String ids)
	{
		return hjDeeppitDataMapper.deleteHjDeeppitDataByIds(Convert.toStrArray(ids));
	}
	
}
