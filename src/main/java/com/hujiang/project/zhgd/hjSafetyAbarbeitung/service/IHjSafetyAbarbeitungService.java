package com.hujiang.project.zhgd.hjSafetyAbarbeitung.service;

import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 整改 服务层
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public interface IHjSafetyAbarbeitungService 
{
	public Project getProject(int constructionId);
	/**
	 * 查询责任分包单位列表
	 * @param projectId
	 * @return
	 */
	public List<Safety> getConstructionList(int projectId);

	/**
	 * 查询检查区域列表
	 * @param constructionId
	 * @return
	 */
	public List<Safety> getAreaList(int constructionId);

	/**
	 * 查询整改人和复查人列表
	 * @param areaId
	 * @return
	 */
	public List<Safety> getUserList(int areaId);

	/**
	 * 查询问题隐患列表
	 * @return
	 */
	public List<Safety> getHiddenList();

	/**
	 * 查询问题级别列表
	 * @return
	 */
	public List<Safety> getGradeList();

	/**
	 * 根据ID查询全部信息
	 * @param safetyId
	 * @return
	 */
	public int getSafetyCount(int safetyId);

	/**
	 * 添加安全巡检
	 * @param hjSafetyAbarbeitung
	 * @return
	 */
	public int sponsorSafety(HjSafetyAbarbeitung hjSafetyAbarbeitung);
	public int sponsorSafetyTwo(HjSafetyNoPass hjSafetyNoPass);

	/**
	 * 待整改列表
	 * @param projectId
	 * @param statusId
	 * @return
	 */
	public List<SafetyRectification> getAfterRectificationList(Integer projectId, Integer statusId,Integer differentiate,Integer userId);
	public HjSafetyNoPass getAfterRectificationNoPass(int safetyId);


	/**
	 * 待整改详情
	 * @param safetyId
	 * @return
	 */
	public SafetyRectification getAfterRectificationDetails(int safetyId);
	public List<HjSafetyNoPass> getAfterRectificationDetailsList(int safetyId);

	/**
	 * 修改整改
	 * @param hjSafetyAbarbeitung
	 * @return
	 */
	public int alterStatus(HjSafetyAbarbeitung hjSafetyAbarbeitung);
	public int alterSafetyNoPass(HjSafetyNoPass hjSafetyNoPass);


	/**
	 * 通过：提交复查后》》增加复查后的信息
	 * @param hjSafetyNoPass
	 * @return
	 */
	public int alertReviewPassTwo(HjSafetyNoPass hjSafetyNoPass);

	/**
	 * 查询整改标题
	 * @param safetyId
	 * @return
	 */
	public HjSafetyAbarbeitung getDescribe(int safetyId);

	/**
	 * 未通过
	 * @param hjSafetyNoPass
	 * @return
	 */
	public int sponsorReviewNoPass(HjSafetyNoPass hjSafetyNoPass);

	/**
	 * 删除整改
	 *
	 * @param id 整改ID
	 * @return 结果
	 */
	public int deleteSafety(Integer id);

	public int deleteNoPass(Integer safetyId);

	/**
	 * PC统计记录数
	 * @param status	状态
	 * @param differentiate	1安全/2质量
	 * @return
	 */
	public int statisticsCount(Integer projectId,Integer status, Integer differentiate,String startTime,String endTime);
	public PcYield getQualifiedList(Integer projectId,Integer differentiate,String startTime,String endTime);

	/**
	 * pc检查记录
	 * @param status
	 * @param initiatorTime
	 * @param initiatorId
	 * @param rectifyId
	 * @param reviewId
	 * @param constructionId
	 * @param areaId
	 * @param problemGradeId
	 * @param differentiate 1安全/2质量
	 * @return
	 */
	public List<PcInspectionRecord> getInspectionRecordList(Integer projectId,
															Integer hiddenId,
															Integer status,
															String initiatorTime,
															Integer initiatorId,
															Integer rectifyId,
															Integer reviewId,
													  		Integer constructionId,
															Integer areaId,
															Integer problemGradeId,
													  		Integer differentiate);
	/**
	 * 整改单管理
	 * @param projectId
	 * @param status
	 * @param initiatorId
	 * @param rectifyId
	 * @param reviewId
	 * @param constructionId
	 * @return
	 */
	public List<PcManagement> getManagementList(Integer projectId,
												Integer status,
												Integer initiatorId,
												Integer rectifyId,
												Integer reviewId,
												Integer constructionId,
												Integer differentiate);
	public PcManagement getManagementByTime(String initiatorTime,String safetyId);

	/**
	 * 修改整改
	 *
	 * @param updateSafety 整改信息
	 * @return 结果
	 */
	public int updateSafety(UpdateSafety updateSafety);
	public int updateNoPass(UpdateSafety updateSafety);









	/**
     * 查询整改信息
     * 
     * @param id 整改ID
     * @return 整改信息
     */
	public HjSafetyAbarbeitung selectHjSafetyAbarbeitungById(Integer id);
	
	/**
     * 查询整改列表
     * 
     * @param hjSafetyAbarbeitung 整改信息
     * @return 整改集合
     */
	public List<HjSafetyAbarbeitung> selectHjSafetyAbarbeitungList(HjSafetyAbarbeitung hjSafetyAbarbeitung);
	
	/**
     * 新增整改
     * 
     * @param hjSafetyAbarbeitung 整改信息
     * @return 结果
     */
	public int insertHjSafetyAbarbeitung(HjSafetyAbarbeitung hjSafetyAbarbeitung);
	

		
	/**
     * 删除整改信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyAbarbeitungByIds(String ids);
	
}
