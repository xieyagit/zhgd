package com.hujiang.project.zhgd.hjConstructionCompany.service;

import com.hujiang.project.zhgd.hjConstructionCompany.domain.ConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import java.util.List;
import java.util.Map;

/**
 * 参建单位 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjConstructionCompanyService 
{
	/**
	 * 查询参建单位信息
	 * @param hjConstructionCompany
	 * @return
	 */
	public HjConstructionCompany getConstructionCompany(HjConstructionCompany hjConstructionCompany);
	/**
     * 查询参建单位信息
     * 
     * @param id 参建单位ID
     * @return 参建单位信息
     */
	public HjConstructionCompany selectHjConstructionCompanyById(Integer id);
	
	/**
     * 查询参建单位列表
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 参建单位集合
     */
	public List<HjConstructionCompany> selectHjConstructionCompanyList(HjConstructionCompany hjConstructionCompany);
	
	/**
     * 新增参建单位
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 结果
     */
	public int insertHjConstructionCompany(HjConstructionCompany hjConstructionCompany);
	
	/**
     * 修改参建单位
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 结果
     */
	public int updateHjConstructionCompany(HjConstructionCompany hjConstructionCompany);
		
	/**
     * 删除参建单位信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjConstructionCompanyByIds(String ids);

	/**
	 * 参建单位
	 * @param projectId 项目id
	 * @return
	 */
    Map<String, Object> selectConstructionCompany(Integer projectId);

	/**
	 * 查询参建单位列表
	 * @param map
	 * @return
	 */
	public List<HjConstructionCompany> selectHjConstructionCompanyListTwo(Map<String,Object> map);

	/**
	 * 删除参建单位信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteHjConstructionCompanyByIdsTwo(String ids);

	ConstructionCompany selectConstruction(Integer projectId);

	ConstructionCompany selectSupervisor(Integer projectId);
}
