package com.hujiang.project.zhgd.hjSafetyAbarbeitung.mapper;

import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 整改 数据层
 *
 * @author hujiang
 * @date 2019-07-10
 */
public interface HjSafetyAbarbeitungMapper
{
	/**
	 * 根据分包单位查询项目ID
	 * @param constructionId
	 * @return
	 */
	public Project getProject(int constructionId);
	/**
	 * 查询责任分包单位列表
	 * @param projectId
	 * @return
	 */
	public List<Safety> getConstructionList(@Param(value = "projectId")int projectId);


	/**
	 * 查询检查区域列表
	 * @param constructionId
	 * @return
	 */
	public List<Safety> getAreaList(@Param(value = "constructionId")int constructionId);

	/**
	 * 查询整改人和复查人列表
	 * @param areaId
	 * @return
	 */
	public List<Safety> getUserList(@Param(value = "areaId")int areaId);

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
	public int getSafetyCount(@Param("safetyId")int safetyId);
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
	public List<SafetyRectification> getAfterRectificationList(@Param("projectId")Integer projectId,
															   @Param("statusId")Integer statusId,
															   @Param("differentiate")Integer differentiate,
															   @Param("userId")Integer userId);
	public HjSafetyNoPass getAfterRectificationNoPass(@Param("safetyId")int safetyId);

	/**
	 * 待整改详情
	 * @param safetyId
	 * @return
	 */
	public SafetyRectification getAfterRectificationDetails(@Param("safetyId")int safetyId);
	public List<HjSafetyNoPass> getAfterRectificationDetailsList(@Param("safetyId")int safetyId);



	/**
	 * 提交整改后》》增加修改后的信息
	 * @param hjSafetyAbarbeitung
	 * @return
	 */
	public int alterStatus(HjSafetyAbarbeitung hjSafetyAbarbeitung);
	public int alterSafetyNoPass(HjSafetyNoPass hjSafetyNoPass);




	public int alertReviewPassTwo(HjSafetyNoPass hjSafetyNoPass);

	/**
	 * 查询整改标题
	 * @param safetyId
	 * @return
	 */
	public HjSafetyAbarbeitung getDescribe(@Param("safetyId")int safetyId);



	/**
	 * 未通过
	 * @param hjSafetyNoPass
	 * @return
	 */
	public int sponsorReviewNoPass(HjSafetyNoPass hjSafetyNoPass);

	/**
	 * PC统计记录数
	 * @param status	状态
	 * @param differentiate	安全/质量
	 * @return
	 */
	public int statisticsCount(@Param("projectId")Integer projectId,
							   @Param("status") Integer status,
							   @Param("differentiate") Integer differentiate,
							   @Param("startTime")String startTime,
							   @Param("endTime")String endTime);

	public PcYield getQualifiedList(@Param("projectId")Integer projectId,
									@Param("differentiate") Integer differentiate,
									@Param("startTime")String startTime,
									@Param("endTime")String endTime);

	/**
	 * 检查记录
	 * @param projectId
	 * @param status
	 * @param initiatorTime
	 * @param initiatorId
	 * @param rectifyId
	 * @param reviewId
	 * @param constructionId
	 * @param areaId
	 * @param problemGradeId
	 * @param differentiate
	 * @return
	 */
	public List<PcInspectionRecord> getInspectionRecordList(@Param("projectId")Integer projectId,
															@Param("hiddenId")Integer hiddenId,
															@Param("status")Integer status,
															@Param("initiatorTime")String initiatorTime,
															@Param("initiatorId")Integer initiatorId,
															@Param("rectifyId")Integer rectifyId,
															@Param("reviewId")Integer reviewId,
															@Param("constructionId")Integer constructionId,
															@Param("areaId")Integer areaId,
															@Param("problemGradeId")Integer problemGradeId,
															@Param("differentiate")Integer differentiate);

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
	public List<PcManagement> getManagementList(@Param("projectId")Integer projectId,
												@Param("status")Integer status,
												@Param("initiatorId")Integer initiatorId,
												@Param("rectifyId")Integer rectifyId,
												@Param("reviewId")Integer reviewId,
												@Param("constructionId")Integer constructionId,
												@Param("differentiate")Integer differentiate);
	public PcManagement getManagementByTime(@Param("initiatorTime")String initiatorTime,@Param("safetyId") String safetyId);

	/**
	 * 修改整改
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
	 * 删除整改
	 *
	 * @param id 整改ID
	 * @return 结果
	 */
	public int deleteSafety(Integer id);

	public int deleteNoPass(Integer safetyId);

	/**
	 * 批量删除整改
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteHjSafetyAbarbeitungByIds(String[] ids);

}