package com.hujiang.project.zhgd.lyDevicePersonnel.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyDevicePersonnel.mapper.LyDevicePersonnelMapper;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.common.support.Convert;

/**
 * 楼宇考勤设备人员 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Service
public class LyDevicePersonnelServiceImpl implements ILyDevicePersonnelService 
{
	@Autowired
	private LyDevicePersonnelMapper lyDevicePersonnelMapper;

	/**
     * 查询楼宇考勤设备人员信息
     * 
     * @param id 楼宇考勤设备人员ID
     * @return 楼宇考勤设备人员信息
     */
    @Override
	public LyDevicePersonnel selectLyDevicePersonnelById(Integer id)
	{
	    return lyDevicePersonnelMapper.selectLyDevicePersonnelById(id);
	}
	
	/**
     * 查询楼宇考勤设备人员列表
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 楼宇考勤设备人员集合
     */
	@Override
	public List<LyDevicePersonnel> selectLyDevicePersonnelList(LyDevicePersonnel lyDevicePersonnel)
	{
	    return lyDevicePersonnelMapper.selectLyDevicePersonnelList(lyDevicePersonnel);
	}
	
    /**
     * 新增楼宇考勤设备人员
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 结果
     */
	@Override
	public int insertLyDevicePersonnel(LyDevicePersonnel lyDevicePersonnel)
	{
	    return lyDevicePersonnelMapper.insertLyDevicePersonnel(lyDevicePersonnel);
	}
	
	/**
     * 修改楼宇考勤设备人员
     * 
     * @param lyDevicePersonnel 楼宇考勤设备人员信息
     * @return 结果
     */
	@Override
	public int updateLyDevicePersonnel(LyDevicePersonnel lyDevicePersonnel)
	{
	    return lyDevicePersonnelMapper.updateLyDevicePersonnel(lyDevicePersonnel);
	}

	/**
     * 删除楼宇考勤设备人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyDevicePersonnelByIds(String ids)
	{
		return lyDevicePersonnelMapper.deleteLyDevicePersonnelByIds(Convert.toStrArray(ids));
	}
	//清除访客人员
	@Override
	public int deleteLyDevicePersonnelTypeTwo(){
		return lyDevicePersonnelMapper.deleteLyDevicePersonnelTypeTwo();
	}
	@Override
	public int updateLyDevicePersonnelTypeTwo(){
		return lyDevicePersonnelMapper.updateLyDevicePersonnelTypeTwo();
	}
	@Override
	public List<HjDeviceProjectworkersParam> selectLyDevicePersonnelListTwo(Map<String,String> param){
		return lyDevicePersonnelMapper.selectLyDevicePersonnelListTwo(param);
	}
	@Override
	public int updateLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel){
		return lyDevicePersonnelMapper.updateLyDevicePersonnelTwo(lyDevicePersonnel);
	}
	@Override
	public int deleteLyDevicePersonnelTwo(LyDevicePersonnel lyDevicePersonnel){
		return lyDevicePersonnelMapper.deleteLyDevicePersonnelTwo(lyDevicePersonnel);
	}
}
