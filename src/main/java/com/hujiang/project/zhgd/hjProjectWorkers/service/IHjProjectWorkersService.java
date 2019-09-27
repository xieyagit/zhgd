package com.hujiang.project.zhgd.hjProjectWorkers.service;

import com.hujiang.project.zhgd.hjProjectWorkers.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目工人 服务层
 * 
 * @author hujiang
 * @date 2019-05-19
 */
public interface IHjProjectWorkersService 
{
	/**
	 * 人员同步进出或退场
	 * @param
	 * @return
	 */
	int updateHjProjectWorkersOutOrInTwo(Integer id,Integer tag);
	/**
	 * 查询项目工人信息
	 *
	 * @param idCode 项目工s身份证号码
	 * @return 项目工人信息
	 */
	public HjProjectWorkers getProjectWorkersById(@Param("idCode") String idCode);
	/**
	 * 查询项目工人信息
	 *
	 * @param idCode 项目工s身份证号码
	 * @return 项目工人信息
	 */
	public HjProjectWorkers selectHjProjectWorkersByIdCode(String idCode,Integer projectId);
	/**
	 * 根据id查询工人信息
	 * @param ids
	 * @return
	 */
	List<PdfWorkers> selectPdfWorkers(String[] ids);
	/**
     * 查询项目工人信息
     * 
     * @param id 项目工人ID
     * @return 项目工人信息
     */
	public HjProjectWorkers selectHjProjectWorkersById(Integer id);
	
	/**
     * 查询项目工人列表
     * 
     * @param hjProjectWorkers 项目工人信息
     * @return 项目工人集合
     */
	public List<HjProjectWorkers> selectHjProjectWorkersList(HjProjectWorkers hjProjectWorkers);


	/**
     * 新增项目工人
     * 
     * @param hjProjectWorkers 项目工人信息
     * @return 结果
     */
	public int insertHjProjectWorkers(HjProjectWorkers hjProjectWorkers);
	
	/**
     * 修改项目工人
     * 
     * @param hjProjectWorkers 项目工人信息
     * @return 结果
     */
	public int updateHjProjectWorkers(HjProjectWorkers hjProjectWorkers);
		
	/**
     * 删除项目工人信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectWorkersByIds(String ids);

	/**
	 * 人员信息列表
	 * @param empNameParam
	 * @return
	 */
    Map<String, Object> selectProjectWorkers(EmpNameParam empNameParam);

	/**
	 * 人员信息 所属单位
	 * @param projectWorkers
	 * @return
	 */
    Map<String, Object> selectConstructionProject(ProjectWorkers projectWorkers);

	/**
	 * 人员信息 详情
	 * @param projectWorkersParam
	 * @return
	 */
    Map<String, Object> selectProjectWorkersDetails(ProjectWorkersParam projectWorkersParam);

	/**
	 * 实名制录入
	 * @param hjProjectWorkers
	 * @return
	 */
	Map<String, Object> insertProjectWorkers(HjProjectWorkers hjProjectWorkers);

	/**
	 * 是否签订
	 * @param signParam
	 * @return
	 */
	Map<String, Object> selectSignParam(SignParam signParam);
	/**
	 * PC人员列表
	 * @param projectWorkers
	 * @return
	 */
	List<ProjectWorkerPC> selectProjectWorkersListPC(HjProjectWorkers projectWorkers);
	/**
	 * 人员同步进出货退场
	 * @param
	 * @return
	 */
	Map<String,Object> updateHjProjectWorkersOutOrIn(String[] ids,Integer tag);

	/**
	 * 修改前查询
	 * @param id 人员id
	 * @param
	 * @return
	 */
	Map<String, Object> queryProjectWorkers(Integer id);
	/**
	 * 查询不再魔点设备的在场项目人员
	 * @param projectId
	 * @return
	 */
	List<HjProjectWorkers> selectHjProjectWorkersNotMoreDianByprojectId(@Param(value = "projectId") Integer projectId);


	/**
	 * 查询指定项目的在场人数
	 */
	public Integer selectOnLineCount(Integer pid);
	/**
	 * 在场关键人员
	 * @param pid
	 * @return
	 */
	public Integer selectOnLineCountGj(Integer pid);
	/**
	 * 出勤工种统计
	 * @param pid
	 * @return
	 */
	public List<Cqgztj> selectCqgztj(Integer pid);

	public InOROut selectInOrOutKB(Integer id);

	/**查找照片*/
	public HjProjectWorkers seleimg(Integer userid);
	public HjProjectWorkers seleimgs(Integer userid);

	/**
	 * 看板数据
	 * */
	public List<HjProjectWorkers> listcount (Integer projectId,String passedTime);
	public List<HjProjectWorkers> listcounts (Integer projectId, String passedTime,Integer ids);

	public HjProjectWorkers jyht(Integer projectId,Integer contract);
	public HjProjectWorkers jc(Integer projectId, Integer contract);
	public HjProjectWorkers tc(Integer projectId, Integer contract);
	public HjProjectWorkers lzqrs(Integer projectId,Integer contract);
}
