package com.hujiang.project.zhgd.shortCreedNumber.service;

import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
import java.util.List;

/**
 * 项目短信条数 服务层
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public interface IShortCreedNumberService 
{
	/**
     * 查询项目短信条数信息
     * 
     * @param id 项目短信条数ID
     * @return 项目短信条数信息
     */
	public ShortCreedNumber selectShortCreedNumberById(Integer id);
	
	/**
     * 查询项目短信条数列表
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 项目短信条数集合
     */
	public ShortCreedNumber selectShortCreedNumber(ShortCreedNumber shortCreedNumber);

	/**
	 * 查询项目短信条数列表
	 *
	 * @param shortCreedNumber 项目短信条数信息
	 * @return 项目短信条数集合
	 */
	public List<ShortCreedNumber> selectShortCreedNumberList(ShortCreedNumber shortCreedNumber);
	
	/**
     * 新增项目短信条数
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 结果
     */
	public int insertShortCreedNumber(ShortCreedNumber shortCreedNumber);
	
	/**
     * 修改项目短信条数
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 结果
     */
	public int updateShortCreedNumber(ShortCreedNumber shortCreedNumber);
		
	/**
     * 删除项目短信条数信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShortCreedNumberByIds(String ids);
	
}
