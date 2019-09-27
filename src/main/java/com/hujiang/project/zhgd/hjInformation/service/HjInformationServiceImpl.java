package com.hujiang.project.zhgd.hjInformation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjInformation.mapper.HjInformationMapper;
import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资料 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Service
public class HjInformationServiceImpl implements IHjInformationService 
{
	@Autowired
	private HjInformationMapper hjInformationMapper;

	/**
     * 查询资料信息
     * 
     * @param id 资料ID
     * @return 资料信息
     */
    @Override
	public HjInformation selectHjInformationById(Integer id)
	{
	    return hjInformationMapper.selectHjInformationById(id);
	}
	
	/**
     * 查询资料列表
     * 
     * @param hjInformation 资料信息
     * @return 资料集合
     */
	@Override
	public List<HjInformation> selectHjInformationList(HjInformation hjInformation)
	{
	    return hjInformationMapper.selectHjInformationList(hjInformation);
	}
	
    /**
     * 新增资料
     * 
     * @param hjInformation 资料信息
     * @return 结果
     */
	@Override
	public int insertHjInformation(HjInformation hjInformation)
	{
	    return hjInformationMapper.insertHjInformation(hjInformation);
	}
	
	/**
     * 修改资料
     * 
     * @param hjInformation 资料信息
     * @return 结果
     */
	@Override
	public int updateHjInformation(HjInformation hjInformation)
	{
	    return hjInformationMapper.updateHjInformation(hjInformation);
	}

	/**
	 * 删除资料对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteHjInformationById(Integer id)
	{
		return hjInformationMapper.deleteHjInformationById(id);
	}


	/**
     * 删除资料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjInformationByIds(String[] ids)
	{
		return hjInformationMapper.deleteHjInformationByIds(ids);
	}

	/**
	 * 查看详情
	 **/
	public List<HjInformation> particulars(HjInformation hjInformation){
		return hjInformationMapper.particulars(hjInformation);
	}


	/**
	 * 上传图片或Excel表格
	 * */
	public int instadd(HjInformation hjInformation){
		return hjInformationMapper.instadd(hjInformation);
	}


	/**
	 * 编辑
	 * */
	public int upda(HjInformation hjInformation){
		return hjInformationMapper.upda(hjInformation);
	}

	/**
	 * 查询是否有文件
	 * */
	public List<HjInformation> file(Integer projectId,Integer muenId){
		return hjInformationMapper.file(projectId, muenId);
	}
}
