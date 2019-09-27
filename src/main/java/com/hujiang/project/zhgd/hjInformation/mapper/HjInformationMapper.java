package com.hujiang.project.zhgd.hjInformation.mapper;

import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 资料 数据层
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public interface HjInformationMapper 
{
	/**
     * 查询资料信息
     * 
     * @param id 资料ID
     * @return 资料信息
     */
	public HjInformation selectHjInformationById(Integer id);
	
	/**
     * 查询资料列表
     * 
     * @param hjInformation 资料信息
     * @return 资料集合
     */
	public List<HjInformation> selectHjInformationList(HjInformation hjInformation);
	
	/**
     * 新增资料
     * 
     * @param hjInformation 资料信息
     * @return 结果
     */
	public int insertHjInformation(HjInformation hjInformation);
	
	/**
     * 修改资料
     * 
     * @param hjInformation 资料信息
     * @return 结果
     */
	public int updateHjInformation(HjInformation hjInformation);
	
	/**
     * 删除资料
     * 
     * @param id 资料ID
     * @return 结果
     */
	public int deleteHjInformationById(Integer id);
	
	/**
     * 批量删除资料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjInformationByIds(String[] ids);

	/**
	 * 查看详情
	 **/
	public List<HjInformation> particulars(HjInformation hjInformation);


	/**
	 * 上传图片或Excel表格
	 * */
	public int instadd( HjInformation hjInformation);

	/**
	 * 编辑
	 * */
	public int upda(HjInformation hjInformation);

	/**
	 * 查询是否有文件
	 * */
	public List<HjInformation> file(@Param("projectId") Integer projectId,@Param("muenId") Integer muenId);

}