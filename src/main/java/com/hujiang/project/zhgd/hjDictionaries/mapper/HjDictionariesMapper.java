package com.hujiang.project.zhgd.hjDictionaries.mapper;

import com.hujiang.project.zhgd.hjDictionaries.domain.Dictionaries;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import java.util.List;	

/**
 * 字典 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjDictionariesMapper 
{
	/**
     * 查询字典信息
     * 
     * @param id 字典ID
     * @return 字典信息
     */
	public HjDictionaries selectHjDictionariesById(Integer id);
	
	/**
     * 查询字典列表
     * 
     * @param hjDictionaries 字典信息
     * @return 字典集合
     */
	public List<HjDictionaries> selectHjDictionariesList(HjDictionaries hjDictionaries);

	/**
	 * 查询字典列表
	 * @return
	 */
	public List<Dictionaries> getHjDictionariesList(String category);
	
	/**
     * 新增字典
     * 
     * @param hjDictionaries 字典信息
     * @return 结果
     */
	public int insertHjDictionaries(HjDictionaries hjDictionaries);
	
	/**
     * 修改字典
     * 
     * @param hjDictionaries 字典信息
     * @return 结果
     */
	public int updateHjDictionaries(HjDictionaries hjDictionaries);
	
	/**
     * 删除字典
     * 
     * @param id 字典ID
     * @return 结果
     */
	public int deleteHjDictionariesById(Integer id);
	
	/**
     * 批量删除字典
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjDictionariesByIds(String[] ids);

	/**
	 * 热门工种
	 * @param
	 * @return
	 */
    List<HjDictionaries> selectWorkType(HjDictionaries hjDictionaries);

	/**
	 * 搜索工种
	 * @param
	 * @return
	 */
	List<HjDictionaries> queryWorkType(HjDictionaries hjDictionaries);

	/**
	 * 删除星河工种
	 */
	void deleteHjDictionariesByTwo();
}