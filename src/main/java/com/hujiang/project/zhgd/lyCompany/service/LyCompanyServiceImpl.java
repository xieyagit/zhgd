package com.hujiang.project.zhgd.lyCompany.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyCompany.mapper.LyCompanyMapper;
import com.hujiang.project.zhgd.lyCompany.domain.LyCompany;
import com.hujiang.project.zhgd.lyCompany.service.ILyCompanyService;
import com.hujiang.common.support.Convert;

/**
 * 楼宇公司 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-05
 */
@Service
public class LyCompanyServiceImpl implements ILyCompanyService 
{
	@Autowired
	private LyCompanyMapper lyCompanyMapper;

	/**
     * 查询楼宇公司信息
     * 
     * @param id 楼宇公司ID
     * @return 楼宇公司信息
     */
    @Override
	public LyCompany selectLyCompanyById(Integer id)
	{
	    return lyCompanyMapper.selectLyCompanyById(id);
	}
	
	/**
     * 查询楼宇公司列表
     * 
     * @param lyCompany 楼宇公司信息
     * @return 楼宇公司集合
     */
	@Override
	public List<LyCompany> selectLyCompanyList(LyCompany lyCompany)
	{
	    return lyCompanyMapper.selectLyCompanyList(lyCompany);
	}
	
    /**
     * 新增楼宇公司
     * 
     * @param lyCompany 楼宇公司信息
     * @return 结果
     */
	@Override
	public int insertLyCompany(LyCompany lyCompany)
	{
	    return lyCompanyMapper.insertLyCompany(lyCompany);
	}
	
	/**
     * 修改楼宇公司
     * 
     * @param lyCompany 楼宇公司信息
     * @return 结果
     */
	@Override
	public int updateLyCompany(LyCompany lyCompany)
	{
	    return lyCompanyMapper.updateLyCompany(lyCompany);
	}

	/**
     * 删除楼宇公司对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyCompanyByIds(String ids)
	{
		return lyCompanyMapper.deleteLyCompanyByIds(Convert.toStrArray(ids));
	}
	
}
