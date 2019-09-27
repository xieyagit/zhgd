package com.hujiang.project.zhgd.hjCompanyLibrary.mapper;

import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 公司库 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjCompanyLibraryMapper 
{
	/**
     * 查询公司库信息
     * 
     * @param id 公司库ID
     * @return 公司库信息
     */
	public HjCompanyLibrary selectHjCompanyLibraryById(Integer id);
	
	/**
     * 查询公司库列表
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 公司库集合
     */
	public List<HjCompanyLibrary> selectHjCompanyLibraryList(HjCompanyLibrary hjCompanyLibrary);
	/**
	 * 查询公司库分页
	 *
	 * @param map
	 * @return 公司库集合
	 */
	List<HjCompanyLibrary> selectHjCompanyLibraryPage(Map<String,Object> map);
	/**
	 * 查询指定公司下公司信息
	 *
	 * @param companyId 父级公司id
	 * @return 公司库集合
	 */
	public List<HjCompanyLibrary> selectHjCompanyLibraryListTwo( Integer companyId);
	/**
     * 新增公司库
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 结果
     */
	public int insertHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary);
	
	/**
     * 修改公司库
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 结果
     */
	public int updateHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary);
	
	/**
     * 删除公司库
     * 
     * @param id 公司库ID
     * @return 结果
     */
	public int deleteHjCompanyLibraryById(Integer id);
	
	/**
     * 批量删除公司库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjCompanyLibraryByIds(String[] ids);
	/**
	 * 批量删除公司库
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteHjCompanyLibraryByIdsTwo(String[] ids);
}