package com.hujiang.project.zhgd.hjSafetyNopass.service;

import com.hujiang.project.zhgd.hjSafetyNopass.domain.SafetyNopass;
import java.util.List;

/**
 * 整改未通过 服务层
 * 
 * @author hujiang
 * @date 2019-07-18
 */
public interface IHjSafetyNopassService 
{

	/**
     * 查询整改未通过信息
     * 
     * @param id 整改未通过ID
     * @return 整改未通过信息
     */
	public SafetyNopass selectHjSafetyNopassById(Integer id);
	
	/**
     * 查询整改未通过列表
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 整改未通过集合
     */
	public List<SafetyNopass> selectHjSafetyNopassList(SafetyNopass hjSafetyNopass);
	
	/**
     * 新增整改未通过
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 结果
     */
	public int insertHjSafetyNopass(SafetyNopass hjSafetyNopass);
	
	/**
     * 修改整改未通过
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 结果
     */
	public int updateHjSafetyNopass(SafetyNopass hjSafetyNopass);
		
	/**
     * 删除整改未通过信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyNopassByIds(String ids);
	
}
