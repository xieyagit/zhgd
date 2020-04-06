package com.hujiang.project.zhgd.sbElevatorAddrecord.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.ElevatorAddRecord;
import com.hujiang.project.zhgd.sbGroup.domain.ElevatorKB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.sbElevatorAddrecord.mapper.SbElevatorAddrecordMapper;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.common.support.Convert;

/**
 * 2.5.3升降机实时数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Service
public class SbElevatorAddrecordServiceImpl implements ISbElevatorAddrecordService 
{
	@Autowired
	private SbElevatorAddrecordMapper sbElevatorAddrecordMapper;

	@Override
	public ElevatorKB selectCount(List list) {
		return sbElevatorAddrecordMapper.selectCount(list);
	}

	@Override
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListKB(Map<String, Object> map) {
		return sbElevatorAddrecordMapper.selectSbElevatorAddrecordListKB(map);
	}

	@Override
	public SbElevatorAddrecord selectSbElevatorAddRecord(String hxzId, String runtime) {
		return sbElevatorAddrecordMapper.selectSbElevatorAddRecord(hxzId, runtime);
	}

	@Override
	public ElevatorAddRecord selectElevatorAddRecordName(String hxzId, String runtime,Integer projectId) {
		return sbElevatorAddrecordMapper.selectElevatorAddRecordName(hxzId, runtime,projectId);
	}


	/**
     * 查询2.5.3升降机实时数据信息
     * 
     * @param id 2.5.3升降机实时数据ID
     * @return 2.5.3升降机实时数据信息
     */
    @Override
	public SbElevatorAddrecord selectSbElevatorAddrecordById(Integer id)
	{
	    return sbElevatorAddrecordMapper.selectSbElevatorAddrecordById(id);
	}
	
	/**
     * 查询2.5.3升降机实时数据列表
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 2.5.3升降机实时数据集合
     */
	@Override
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordList(SbElevatorAddrecord sbElevatorAddrecord)
	{
	    return sbElevatorAddrecordMapper.selectSbElevatorAddrecordList(sbElevatorAddrecord);
	}
	@Override
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListTwo(SbElevatorAddrecord sbElevatorAddrecord){
		return sbElevatorAddrecordMapper.selectSbElevatorAddrecordListTwo(sbElevatorAddrecord);
	}
	@Override
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListThree(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectSbElevatorAddrecordListThree(map);
	}
    /**
     * 新增2.5.3升降机实时数据
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 结果
     */
	@Override
	public int insertSbElevatorAddrecord(SbElevatorAddrecord sbElevatorAddrecord)
	{
	    return sbElevatorAddrecordMapper.insertSbElevatorAddrecord(sbElevatorAddrecord);
	}
	
	/**
     * 修改2.5.3升降机实时数据
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 结果
     */
	@Override
	public int updateSbElevatorAddrecord(SbElevatorAddrecord sbElevatorAddrecord)
	{
	    return sbElevatorAddrecordMapper.updateSbElevatorAddrecord(sbElevatorAddrecord);
	}

	/**
     * 删除2.5.3升降机实时数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbElevatorAddrecordByIds(String ids)
	{
		return sbElevatorAddrecordMapper.deleteSbElevatorAddrecordByIds(Convert.toStrArray(ids));
	}
	/**
	 * 统计在线生相机
	 * @param map
	 * @return
	 */
	@Override
	public List<SbElevatorAddrecord> selectElevatorCount(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCount(map);
	}
	@Override
	public int selectElevatorCountTwo(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountTwo(map);
	}
	@Override
	public int selectElevatorCountThree(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountThree(map);
	}
	@Override
	public int selectElevatorCountFour(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountFour(map);
	}
	@Override
	public int selectElevatorCountFive(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountFive(map);
	}
	@Override
	public int selectElevatorCountSix(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountSix(map);
	}
	@Override
	public int selectElevatorCountSeven(Map<String,Object> map){
		return sbElevatorAddrecordMapper.selectElevatorCountSeven(map);
	}

	/**1.0看板*/
	public  List<SbElevatorAddrecord> crane(Integer pid){
		return sbElevatorAddrecordMapper.crane(pid);
	}
	public  List<SbElevatorAddrecord> cranes(Integer pid,String runtime){
		return sbElevatorAddrecordMapper.cranes(pid, runtime);
	}
}
