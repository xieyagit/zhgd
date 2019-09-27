package com.hujiang.project.zhgd.hjZhgdPkcount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjZhgdPkcount.mapper.HjZhgdPkcountMapper;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.common.support.Convert;

/**
 * 车位剩余 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-09
 */
@Service
public class HjZhgdPkcountServiceImpl implements IHjZhgdPkcountService 
{
	@Autowired
	private HjZhgdPkcountMapper hjZhgdPkcountMapper;

	/**
     * 查询车位剩余信息
     * 
     * @param id 车位剩余ID
     * @return 车位剩余信息
     */
    @Override
	public HjZhgdPkcount selectHjZhgdPkcountById(Integer id)
	{
	    return hjZhgdPkcountMapper.selectHjZhgdPkcountById(id);
	}
	
	/**
     * 查询车位剩余列表
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 车位剩余集合
     */
	@Override
	public List<HjZhgdPkcount> selectHjZhgdPkcountList(HjZhgdPkcount hjZhgdPkcount)
	{
	    return hjZhgdPkcountMapper.selectHjZhgdPkcountList(hjZhgdPkcount);
	}
	
    /**
     * 新增车位剩余
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 结果
     */
	@Override
	public int insertHjZhgdPkcount(HjZhgdPkcount hjZhgdPkcount)
	{
	    return hjZhgdPkcountMapper.insertHjZhgdPkcount(hjZhgdPkcount);
	}
	
	/**
     * 修改车位剩余
     * 
     * @param hjZhgdPkcount 车位剩余信息
     * @return 结果
     */
	@Override
	public int updateHjZhgdPkcount(HjZhgdPkcount hjZhgdPkcount)
	{
	    return hjZhgdPkcountMapper.updateHjZhgdPkcount(hjZhgdPkcount);
	}

	/**
     * 删除车位剩余对象
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjZhgdPkcountById(Integer id)
	{
		return hjZhgdPkcountMapper.deleteHjZhgdPkcountById(id);
	}

	/**
	 * 查询某项目剩余车位
	 * */
	public List<HjZhgdPkcount> pkcount(HjZhgdPkcount hjZhgdPkcount){
		return hjZhgdPkcountMapper.pkcount(hjZhgdPkcount);
	}

	/**
	 * 查询剩余车位
	 * */
	public HjZhgdPkcount carpkcount(HjZhgdPkcount hjZhgdPkcount){
		return hjZhgdPkcountMapper.carpkcount(hjZhgdPkcount);
	}

	/**
	 * 添加设备
	 * */
	public int add(HjZhgdPkcount hjZhgdPkcount){
		return hjZhgdPkcountMapper.add(hjZhgdPkcount);
	}

	/**
	 *场内车位设置
	 * */
	public int carUpd(String deptId, Integer pkcount){
		return hjZhgdPkcountMapper.carUpd(deptId,pkcount);
	}

	/**
	 * 查询sn
	 * */
	public HjZhgdPkcount selectAll(String deptId ,String snName){
		return hjZhgdPkcountMapper.selectAll(deptId, snName);
	}

	/**
	 * 查询项目Id
	 * */
	public HjZhgdPkcount selectProjectId(Integer deptID){
		return hjZhgdPkcountMapper.selectProjectId(deptID);
	}
}
