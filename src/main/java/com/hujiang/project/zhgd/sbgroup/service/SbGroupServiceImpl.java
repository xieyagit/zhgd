package com.hujiang.project.zhgd.sbgroup.service;

import com.hujiang.project.zhgd.sbgroup.domain.SbProject;
import com.hujiang.project.zhgd.sbgroup.mapper.SbGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 集团对讲账号 服务层实现
 * 
 * @author hujiang
 * @date 2019-12-05
 */
@Service
public class SbGroupServiceImpl implements ISbGroupService
{
	@Autowired
	private SbGroupMapper sbGroupMapper;

	@Override
	public Object selectSbGroupById(Integer cid) {
		return sbGroupMapper.selectSbGroupById(cid);
	}

	@Override
	public Object selectSbGroupMU(Integer cid) {
		return sbGroupMapper.selectSbGroupMU(cid);
	}

	@Override
	public List<SbProject> selectProjectList(Integer cid) {
		return sbGroupMapper.selectProjectList(cid);
	}

	@Override
	public int selectProjectCount(Integer cid, String state) {
		return sbGroupMapper.selectProjectCount(cid,state);
	}

	@Override
	public int selectWorkerAttendance(Integer cid, String time) {
		return sbGroupMapper.selectWorkerAttendance(cid,time);
	}

	@Override
	public int selectAdministorAttendance(Integer cid, String time) {
		return sbGroupMapper.selectAdministorAttendance(cid,time);
	}

	@Override
	public int selectOnGuard(Integer cid) {
		return sbGroupMapper.selectOnGuard(cid);
	}

	@Override
	public int selectPlate(Integer cid, Integer inOut, String time) {
		return sbGroupMapper.selectPlate(cid,inOut,time);
	}

	@Override
	public int selectTsp(Integer cid, Integer min, Integer max) {
		return sbGroupMapper.selectTsp(cid,min,max);
	}
}
