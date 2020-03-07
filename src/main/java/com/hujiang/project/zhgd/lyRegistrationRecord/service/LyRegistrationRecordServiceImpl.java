package com.hujiang.project.zhgd.lyRegistrationRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyRegistrationRecord.mapper.LyRegistrationRecordMapper;
import com.hujiang.project.zhgd.lyRegistrationRecord.domain.LyRegistrationRecord;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import com.hujiang.common.support.Convert;

/**
 * 登记记录 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Service
public class LyRegistrationRecordServiceImpl implements ILyRegistrationRecordService 
{
	@Autowired
	private LyRegistrationRecordMapper lyRegistrationRecordMapper;

	/**
     * 查询登记记录信息
     * 
     * @param id 登记记录ID
     * @return 登记记录信息
     */
    @Override
	public LyRegistrationRecord selectLyRegistrationRecordById(Integer id)
	{
	    return lyRegistrationRecordMapper.selectLyRegistrationRecordById(id);
	}
	
	/**
     * 查询登记记录列表
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 登记记录集合
     */
	@Override
	public List<LyRegistrationRecord> selectLyRegistrationRecordList(LyRegistrationRecord lyRegistrationRecord)
	{
	    return lyRegistrationRecordMapper.selectLyRegistrationRecordList(lyRegistrationRecord);
	}
	
    /**
     * 新增登记记录
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 结果
     */
	@Override
	public int insertLyRegistrationRecord(LyRegistrationRecord lyRegistrationRecord)
	{
	    return lyRegistrationRecordMapper.insertLyRegistrationRecord(lyRegistrationRecord);
	}
	
	/**
     * 修改登记记录
     * 
     * @param lyRegistrationRecord 登记记录信息
     * @return 结果
     */
	@Override
	public int updateLyRegistrationRecord(LyRegistrationRecord lyRegistrationRecord)
	{
	    return lyRegistrationRecordMapper.updateLyRegistrationRecord(lyRegistrationRecord);
	}

	/**
     * 删除登记记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyRegistrationRecordByIds(String ids)
	{
		return lyRegistrationRecordMapper.deleteLyRegistrationRecordByIds(Convert.toStrArray(ids));
	}
	
}
