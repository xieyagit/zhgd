package com.hujiang.project.zhgd.sbElevatorAddrecord.service;

import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.ElevatorAddRecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbGroup.domain.ElevatorKB;

import java.util.List;
import java.util.Map;

/**
 * 2.5.3升降机实时数据 服务层
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public interface ISbElevatorAddrecordService 
{
	public ElevatorKB selectCount(Map<String, Object> map);
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListKB(Map<String, Object> map);
	public SbElevatorAddrecord selectSbElevatorAddRecord(String hxzId, String runtime);
	public ElevatorAddRecord selectElevatorAddRecordName(String hxzId, String runtime, Integer projectId);

	/**
     * 查询2.5.3升降机实时数据信息
     * 
     * @param id 2.5.3升降机实时数据ID
     * @return 2.5.3升降机实时数据信息
     */
	public SbElevatorAddrecord selectSbElevatorAddrecordById(Integer id);
	
	/**
     * 查询2.5.3升降机实时数据列表
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 2.5.3升降机实时数据集合
     */
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordList(SbElevatorAddrecord sbElevatorAddrecord);
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListTwo(SbElevatorAddrecord sbElevatorAddrecord);
	public List<SbElevatorAddrecord> selectSbElevatorAddrecordListThree(Map<String, Object> map);
	
	/**
     * 新增2.5.3升降机实时数据
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 结果
     */
	public int insertSbElevatorAddrecord(SbElevatorAddrecord sbElevatorAddrecord);
	
	/**
     * 修改2.5.3升降机实时数据
     * 
     * @param sbElevatorAddrecord 2.5.3升降机实时数据信息
     * @return 结果
     */
	public int updateSbElevatorAddrecord(SbElevatorAddrecord sbElevatorAddrecord);
		
	/**
     * 删除2.5.3升降机实时数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbElevatorAddrecordByIds(String ids);

	/**
	 * 统计在线升降机
	 * @param map
	 * @return
	 */
	public List<SbElevatorAddrecord> selectElevatorCount(Map<String, Object> map);

	public int selectElevatorCountTwo(Map<String, Object> map);
	public int selectElevatorCountThree(Map<String, Object> map);
	public int selectElevatorCountFour(Map<String, Object> map);
	public int selectElevatorCountFive(Map<String, Object> map);
	public int selectElevatorCountSix(Map<String, Object> map);
	public int selectElevatorCountSeven(Map<String, Object> map);

	/**1.0看板*/
	public  List<SbElevatorAddrecord> crane(Integer pid);
	public  List<SbElevatorAddrecord> cranes(Integer pid, String runtime);
}
