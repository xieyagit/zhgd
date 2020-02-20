package com.hujiang.project.zhgd.sbGroup.service;

import com.hujiang.project.zhgd.sbGroup.domain.SbProject;
import com.hujiang.project.zhgd.sbGroup.mapper.SbGroupMapper;
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
	public List<Integer> selectWorkerAttendance(Integer cid, String start, String end) {
		return sbGroupMapper.selectWorkerAttendance(cid,start,end);
	}

	@Override
	public List<Integer> selectAdministorAttendance(Integer cid, String start, String end) {
		return sbGroupMapper.selectAdministorAttendance(cid,start,end);
	}

	@Override
	public int selectOnGuard(Integer cid) {
		return sbGroupMapper.selectOnGuard(cid);
	}

	@Override
	public Integer selectPlate(Integer cid, Integer inOut, String time) {
		return sbGroupMapper.selectPlate(cid,inOut,time);
	}

	@Override
	public Integer selectTsp(Integer cid, Integer min, Integer max) {
		return sbGroupMapper.selectTsp(cid,min,max);
	}
	@Override
	public List<SbProject> searchProjectList(Integer cid,String name){
		return sbGroupMapper.searchProjectList(cid,name);
	}

}
