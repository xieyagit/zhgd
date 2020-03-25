package com.hujiang.project.zhgd.hjArea.mapper;

import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import java.util.List;	

/**
 * 城市 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjAreaMapper 
{
	/**
     * 查询城市信息
     * 
     * @param id 城市ID
     * @return 城市信息
     */
	public HjArea selectHjAreaById(Long id);
	
	/**
     * 查询城市列表
     * 
     * @param hjArea 城市信息
     * @return 城市集合
     */
	public List<HjArea> selectHjAreaList(HjArea hjArea);
	
	/**
     * 新增城市
     * 
     * @param hjArea 城市信息
     * @return 结果
     */
	public int insertHjArea(HjArea hjArea);
	
	/**
     * 修改城市
     * 
     * @param hjArea 城市信息
     * @return 结果
     */
	public int updateHjArea(HjArea hjArea);
	
	/**
     * 删除城市
     * 
     * @param id 城市ID
     * @return 结果
     */
	public int deleteHjAreaById(Long id);
	
	/**
     * 批量删除城市
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjAreaByIds(String[] ids);

	/**
	 * @Author xieya
	 * @Description
	 * @Date 2020/3/21 15:37
	 * @return java.util.List<com.hujiang.project.zhgd.hjArea.domain.HjArea>
	 **/
	List<HjArea> selectAllProvinceAndCity();

	/**
	 * @Author xieya
	 * @Description
	 * @Date 2020/3/21 19:05
	 * @param * @param ids
	 * @return java.util.List<com.hujiang.project.zhgd.hjArea.domain.HjArea>
	 **/
	List<HjArea> selectProvinceAndCityByIds(String[] ids);


}