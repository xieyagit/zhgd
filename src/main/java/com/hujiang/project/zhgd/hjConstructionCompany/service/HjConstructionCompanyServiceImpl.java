package com.hujiang.project.zhgd.hjConstructionCompany.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.ConstructionCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjConstructionCompany.mapper.HjConstructionCompanyMapper;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.common.support.Convert;

/**
 * 参建单位 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjConstructionCompanyServiceImpl implements IHjConstructionCompanyService 
{
	@Autowired
	private HjConstructionCompanyMapper hjConstructionCompanyMapper;

	@Override
	public HjConstructionCompany getConstructionCompany(HjConstructionCompany hjConstructionCompany) {
		return hjConstructionCompanyMapper.getConstructionCompany(hjConstructionCompany);
	}

	/**
     * 查询参建单位信息
     * 
     * @param id 参建单位ID
     * @return 参建单位信息
     */
    @Override
	public HjConstructionCompany selectHjConstructionCompanyById(Integer id)
	{
	    return hjConstructionCompanyMapper.selectHjConstructionCompanyById(id);
	}
	
	/**
     * 查询参建单位列表
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 参建单位集合
     */
	@Override
	public List<HjConstructionCompany> selectHjConstructionCompanyList(HjConstructionCompany hjConstructionCompany)
	{
	    return hjConstructionCompanyMapper.selectHjConstructionCompanyList(hjConstructionCompany);
	}
	
    /**
     * 新增参建单位
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 结果
     */
	@Override
	public int insertHjConstructionCompany(HjConstructionCompany hjConstructionCompany)
	{
	    return hjConstructionCompanyMapper.insertHjConstructionCompany(hjConstructionCompany);
	}
	
	/**
     * 修改参建单位
     * 
     * @param hjConstructionCompany 参建单位信息
     * @return 结果
     */
	@Override
	public int updateHjConstructionCompany(HjConstructionCompany hjConstructionCompany)
	{
	    return hjConstructionCompanyMapper.updateHjConstructionCompany(hjConstructionCompany);
	}

	/**
     * 删除参建单位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjConstructionCompanyByIds(String ids)
	{
		return hjConstructionCompanyMapper.deleteHjConstructionCompanyByIds(Convert.toStrArray(ids));
	}

	/**
	 * 参建单位
	 * @param projectId 项目id
	 * @return
	 */
	@Override
	public Map<String, Object> selectConstructionCompany(Integer projectId)
	{

	    try {
            List<ConstructionCompany> constructionCompanyList = hjConstructionCompanyMapper.selectConstructionCompany(projectId);
            return AjaxResult.success(constructionCompanyList);
        }catch (Exception e){
	        e.printStackTrace();
            return AjaxResult.error(-1,"查询失败！");
        }
	}



	/**
	 * 查询参建单位列表
	 * @param map
	 * @return
	 */
	public List<HjConstructionCompany> selectHjConstructionCompanyListTwo(Map<String,Object> map){
		return hjConstructionCompanyMapper.selectHjConstructionCompanyListTwo(map);
	}


	/**
	 * 删除参建单位对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteHjConstructionCompanyByIdsTwo(String ids)
	{
		return hjConstructionCompanyMapper.deleteHjConstructionCompanyByIdsTwo(Convert.toStrArray(ids));
	}
}
