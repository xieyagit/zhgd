package com.hujiang.project.zhgd.hjSafetyAbarbeitung.service;

import java.util.List;

import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.*;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.mapper.HjSafetyAbarbeitungMapper;
import com.hujiang.common.support.Convert;

/**
 * 整改 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Service
public class HjSafetyAbarbeitungServiceImpl implements IHjSafetyAbarbeitungService
{
	@Autowired
	private HjSafetyAbarbeitungMapper hjSafetyAbarbeitungMapper;

	@Override
	public Project getProject(int constructionId) {
		return hjSafetyAbarbeitungMapper.getProject(constructionId);
	}

	@Override
	public List<Safety> getConstructionList(int projectId) {
		return hjSafetyAbarbeitungMapper.getConstructionList(projectId);
	}

	@Override
	public List<Safety> getAreaList(int constructionId) {
		return hjSafetyAbarbeitungMapper.getAreaList(constructionId);
	}

	@Override
	public List<Safety> getUserList(int areaId) {
		return hjSafetyAbarbeitungMapper.getUserList(areaId);
	}

	@Override
	public List<Safety> getHiddenList() {
		return hjSafetyAbarbeitungMapper.getHiddenList();
	}

	@Override
	public List<Safety> getGradeList() {
		return hjSafetyAbarbeitungMapper.getGradeList();
	}

	@Override
	public int getSafetyCount(int id) {
		return hjSafetyAbarbeitungMapper.getSafetyCount(id);
	}

	@Override
	public int sponsorSafety(HjSafetyAbarbeitung hjSafetyAbarbeitung) {
		return hjSafetyAbarbeitungMapper.sponsorSafety(hjSafetyAbarbeitung);
	}

	@Override
	public int sponsorSafetyTwo(HjSafetyNoPass hjSafetyNoPass) {
		return hjSafetyAbarbeitungMapper.sponsorSafetyTwo(hjSafetyNoPass);
	}

	@Override
	public List<SafetyRectification> getAfterRectificationList(Integer projectId, Integer statusId,Integer differentiate,Integer userId) {
		return hjSafetyAbarbeitungMapper.getAfterRectificationList(projectId, statusId,differentiate,userId);
	}

	@Override
	public HjSafetyNoPass getAfterRectificationNoPass(int safetyId) {
		return hjSafetyAbarbeitungMapper.getAfterRectificationNoPass(safetyId);
	}

	@Override
	public SafetyRectification getAfterRectificationDetails(int safetyId) {
		return hjSafetyAbarbeitungMapper.getAfterRectificationDetails(safetyId);
	}

	@Override
	public List<HjSafetyNoPass> getAfterRectificationDetailsList(int safetyId) {
		return hjSafetyAbarbeitungMapper.getAfterRectificationDetailsList(safetyId);
	}

	@Override
	public int alterStatus(HjSafetyAbarbeitung hjSafetyAbarbeitung) {
		return hjSafetyAbarbeitungMapper.alterStatus(hjSafetyAbarbeitung);
	}

	@Override
	public int alterSafetyNoPass(HjSafetyNoPass hjSafetyNoPass) {
		return hjSafetyAbarbeitungMapper.alterSafetyNoPass(hjSafetyNoPass);
	}



	@Override
	public int alertReviewPassTwo(HjSafetyNoPass hjSafetyNoPass) {
		return hjSafetyAbarbeitungMapper.alertReviewPassTwo(hjSafetyNoPass);
	}

	@Override
	public HjSafetyAbarbeitung getDescribe(int safetyId) {
		return hjSafetyAbarbeitungMapper.getDescribe(safetyId);
	}


	@Override
	public int sponsorReviewNoPass(HjSafetyNoPass hjSafetyNoPass) {
		return hjSafetyAbarbeitungMapper.sponsorReviewNoPass(hjSafetyNoPass);
	}

	@Override
	public int deleteSafety(Integer id) {
		return hjSafetyAbarbeitungMapper.deleteSafety(id);
	}

	@Override
	public int deleteNoPass(Integer safetyId) {
		return hjSafetyAbarbeitungMapper.deleteNoPass(safetyId);
	}

	@Override
	public int statisticsCount(Integer projectId,Integer status, Integer differentiate,String startTime,String endTime) {
		return hjSafetyAbarbeitungMapper.statisticsCount(projectId,status, differentiate,startTime,endTime);
	}

	@Override
	public PcYield getQualifiedList(Integer projectId,Integer differentiate, String startTime, String endTime) {
		return hjSafetyAbarbeitungMapper.getQualifiedList(projectId,differentiate, startTime, endTime);
	}

	@Override
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
															Integer differentiate) {
		return hjSafetyAbarbeitungMapper.getInspectionRecordList(projectId,hiddenId,status, initiatorTime, initiatorId, rectifyId, reviewId, constructionId, areaId, problemGradeId, differentiate);
	}

	@Override
	public List<PcManagement> getManagementList(Integer projectId,
												Integer status,
												Integer initiatorId,
												Integer rectifyId,
												Integer reviewId,
												Integer constructionId,
												Integer differentiate) {
		return hjSafetyAbarbeitungMapper.getManagementList(projectId, status,initiatorId, rectifyId, reviewId, constructionId,differentiate);
	}

	@Override
	public PcManagement getManagementByTime(String initiatorTime,String safetyId) {
		return hjSafetyAbarbeitungMapper.getManagementByTime(initiatorTime,safetyId);
	}

	@Override
	public HjSafetyAbarbeitung selectHjSafetyAbarbeitungById(Integer id){
	    return hjSafetyAbarbeitungMapper.selectHjSafetyAbarbeitungById(id);
	}
	
	@Override
	public List<HjSafetyAbarbeitung> selectHjSafetyAbarbeitungList(HjSafetyAbarbeitung hjSafetyAbarbeitung){
	    return hjSafetyAbarbeitungMapper.selectHjSafetyAbarbeitungList(hjSafetyAbarbeitung);
	}
	
	@Override
	public int insertHjSafetyAbarbeitung(HjSafetyAbarbeitung hjSafetyAbarbeitung){
	    return hjSafetyAbarbeitungMapper.insertHjSafetyAbarbeitung(hjSafetyAbarbeitung);
	}
	
	@Override
	public int updateSafety(UpdateSafety updateSafety){
	    return hjSafetyAbarbeitungMapper.updateSafety(updateSafety);
	}

	@Override
	public int updateNoPass(UpdateSafety updateSafety) {
		return hjSafetyAbarbeitungMapper.updateNoPass(updateSafety);
	}

	@Override
	public int deleteHjSafetyAbarbeitungByIds(String ids){
		return hjSafetyAbarbeitungMapper.deleteHjSafetyAbarbeitungByIds(Convert.toStrArray(ids));
	}
	
}
