package com.hujiang.project.zhgd.jishijiaoDate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.jishijiaoDate.mapper.JishijiaoDateMapper;
import com.hujiang.project.zhgd.jishijiaoDate.domain.JishijiaoDate;
import com.hujiang.project.zhgd.jishijiaoDate.service.IJishijiaoDateService;
import com.hujiang.common.support.Convert;

/**
 * 极视角异常数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-15
 */
@Service
public class JishijiaoDateServiceImpl implements IJishijiaoDateService 
{
	@Autowired
	private JishijiaoDateMapper jishijiaoDateMapper;

	/**
     * 查询极视角异常数据信息
     * 
     * @param id 极视角异常数据ID
     * @return 极视角异常数据信息
     */
    @Override
	public JishijiaoDate selectJishijiaoDateById(Integer id)
	{
	    return jishijiaoDateMapper.selectJishijiaoDateById(id);
	}
	
	/**
     * 查询极视角异常数据列表
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 极视角异常数据集合
     */
	@Override
	public List<JishijiaoDate> selectJishijiaoDateList(JishijiaoDate jishijiaoDate)
	{
	    return jishijiaoDateMapper.selectJishijiaoDateList(jishijiaoDate);
	}
	
    /**
     * 新增极视角异常数据
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 结果
     */
	@Override
	public int insertJishijiaoDate(JishijiaoDate jishijiaoDate)
	{
	    return jishijiaoDateMapper.insertJishijiaoDate(jishijiaoDate);
	}
	
	/**
     * 修改极视角异常数据
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 结果
     */
	@Override
	public int updateJishijiaoDate(JishijiaoDate jishijiaoDate)
	{
	    return jishijiaoDateMapper.updateJishijiaoDate(jishijiaoDate);
	}

	/**
     * 删除极视角异常数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJishijiaoDateByIds(String ids)
	{
		return jishijiaoDateMapper.deleteJishijiaoDateByIds(Convert.toStrArray(ids));
	}
	
}
