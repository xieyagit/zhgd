package com.hujiang.project.zhgd.jishijiaoDate.mapper;

import com.hujiang.project.zhgd.jishijiaoDate.domain.JishijiaoDate;
import java.util.List;	

/**
 * 极视角异常数据 数据层
 * 
 * @author hujiang
 * @date 2019-08-15
 */
public interface JishijiaoDateMapper 
{
	/**
     * 查询极视角异常数据信息
     * 
     * @param id 极视角异常数据ID
     * @return 极视角异常数据信息
     */
	public JishijiaoDate selectJishijiaoDateById(Integer id);
	
	/**
     * 查询极视角异常数据列表
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 极视角异常数据集合
     */
	public List<JishijiaoDate> selectJishijiaoDateList(JishijiaoDate jishijiaoDate);
	
	/**
     * 新增极视角异常数据
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 结果
     */
	public int insertJishijiaoDate(JishijiaoDate jishijiaoDate);
	
	/**
     * 修改极视角异常数据
     * 
     * @param jishijiaoDate 极视角异常数据信息
     * @return 结果
     */
	public int updateJishijiaoDate(JishijiaoDate jishijiaoDate);
	
	/**
     * 删除极视角异常数据
     * 
     * @param id 极视角异常数据ID
     * @return 结果
     */
	public int deleteJishijiaoDateById(Integer id);
	
	/**
     * 批量删除极视角异常数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJishijiaoDateByIds(String[] ids);
	
}