package com.hujiang.project.zhgd.lyCompany.service;

import com.hujiang.project.zhgd.lyCompany.domain.LyCompany;
import com.hujiang.project.zhgd.lyCompany.domain.LyCompanyCount;

import java.util.List;
import java.util.Map;

/**
 * 楼宇公司 服务层
 * 
 * @author hujiang
 * @date 2020-03-05
 */
public interface ILyCompanyService 
{
	/**
     * 查询楼宇公司信息
     * 
     * @param id 楼宇公司ID
     * @return 楼宇公司信息
     */
	public LyCompany selectLyCompanyById(Integer id);
	
	/**
     * 查询楼宇公司列表
     * 
     * @param lyCompany 楼宇公司信息
     * @return 楼宇公司集合
     */
	public List<LyCompany> selectLyCompanyList(LyCompany lyCompany);
	
	/**
     * 新增楼宇公司
     * 
     * @param lyCompany 楼宇公司信息
     * @return 结果
     */
	public int insertLyCompany(LyCompany lyCompany);
	
	/**
     * 修改楼宇公司
     * 
     * @param lyCompany 楼宇公司信息
     * @return 结果
     */
	public int updateLyCompany(LyCompany lyCompany);
		
	/**
     * 删除楼宇公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyCompanyByIds(String ids);
	public List<LyCompanyCount> selectLyCompanyCount(Map<String,String> map);
}
