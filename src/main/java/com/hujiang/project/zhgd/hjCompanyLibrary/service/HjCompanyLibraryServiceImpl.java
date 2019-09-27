package com.hujiang.project.zhgd.hjCompanyLibrary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjCompanyLibrary.mapper.HjCompanyLibraryMapper;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.common.support.Convert;

/**
 * 公司库 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjCompanyLibraryServiceImpl implements IHjCompanyLibraryService 
{
	@Autowired
	private HjCompanyLibraryMapper hjCompanyLibraryMapper;

	/**
     * 查询公司库信息
     * 
     * @param id 公司库ID
     * @return 公司库信息
     */
    @Override
	public HjCompanyLibrary selectHjCompanyLibraryById(Integer id)
	{
	    return hjCompanyLibraryMapper.selectHjCompanyLibraryById(id);
	}
	
	/**
     * 查询公司库列表
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 公司库集合
     */
	@Override
	public List<HjCompanyLibrary> selectHjCompanyLibraryList(HjCompanyLibrary hjCompanyLibrary)
	{
	    return hjCompanyLibraryMapper.selectHjCompanyLibraryList(hjCompanyLibrary);
	}
	/**
	 * 查询公司库分页
	 *
	 * @param hjCompanyLibrary 公司信息
	 * @param companyId 上级公司id
	 * @return 公司库集合
	 */
	@Override
	public List<HjCompanyLibrary> selectHjCompanyLibraryPage(HjCompanyLibrary hjCompanyLibrary,Integer companyId ){
		Map<String,Object> map= new HashMap<>();
		map.put("hj",hjCompanyLibrary);
		map.put("companyId",companyId);
		return hjCompanyLibraryMapper.selectHjCompanyLibraryPage(map);
	}
	/**
	 * 查询指定公司下公司信息
	 *
	 * @param companyId 父级公司id
	 * @return 公司库集合
	 */
	@Override
	public List<HjCompanyLibrary> selectHjCompanyLibraryListTwo(Integer companyId){
		return hjCompanyLibraryMapper.selectHjCompanyLibraryListTwo(companyId);
	}
    /**
     * 新增公司库
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 结果
     */
	@Override
	public int insertHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary)
	{
	    return hjCompanyLibraryMapper.insertHjCompanyLibrary(hjCompanyLibrary);
	}
	
	/**
     * 修改公司库
     * 
     * @param hjCompanyLibrary 公司库信息
     * @return 结果
     */
	@Override
	public int updateHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary)
	{
	    return hjCompanyLibraryMapper.updateHjCompanyLibrary(hjCompanyLibrary);
	}

	/**
     * 删除公司库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjCompanyLibraryByIds(String ids)
	{
		return hjCompanyLibraryMapper.deleteHjCompanyLibraryByIds(Convert.toStrArray(ids));
	}
	/**
	 * 删除公司库对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteHjCompanyLibraryByIdsTwo(String ids)
	{

		return hjCompanyLibraryMapper.deleteHjCompanyLibraryByIdsTwo(Convert.toStrArray(ids));
	}
}
