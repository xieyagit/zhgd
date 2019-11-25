package com.hujiang.project.zhgd.hjProjectWorkers.mapper;

import com.hujiang.project.zhgd.hjProjectWorkers.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目工人 数据层
 * 
 * @author hujiang
 * @date 2019-05-19
 */
public interface HjProjectWorkersMapper 
{
	/**
     * 查询项目工人信息
     * 
     * @param id 项目工人ID
     * @return 项目工人信息
     */
	public HjProjectWorkers selectHjProjectWorkersById(Integer id);

	/**
	 * 查询项目工人信息
	 *
	 * @param idCode 项目工s身份证号码
	 * @return 项目工人信息
	 */
	public HjProjectWorkers selectHjProjectWorkersByIdCode(@Param("idCode") String idCode,@Param("projectId") Integer projectId);

	/**
	 * 查询项目工人信息
	 *
	 * @param idCode 项目工s身份证号码
	 * @return 项目工人信息
	 */
	public HjProjectWorkers getProjectWorkersById(@Param("idCode") String idCode);
	
	/**
     * 查询项目工人列表
     * 
     * @param hjProjectWorkers 项目工人信息
     * @return 项目工人集合
     */
	public List<HjProjectWorkers> selectHjProjectWorkersList(HjProjectWorkers hjProjectWorkers);



	/**
	 * 查询项目在场工人列表
	 *
	 * @param projectId 项目id
	 * @return 项目在场工人列表
	 * @author yant
	 */
	public List<HjProjectWorkers> selectHjProjectWorkersListN(Integer projectId);

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
     * 删除项目工人
     * 
     * @param id 项目工人ID
     * @return 结果
     */
	public int deleteHjProjectWorkersById(Integer id);
	
	/**
     * 批量删除项目工人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectWorkersByIds(String[] ids);

	/**
	 * 劳务工人在场数
	 *
	 * @param projectId 项目id
	 * @return
	 */
    Integer selectsCeneNumber(@Param(value = "projectId") Integer projectId);

	/**
	 * 管理人员在场数
	 *
	 * @param projectId 项目id
	 * @return 结果
	 */
	Integer selectsAdministrationNumber(@Param(value = "projectId") Integer projectId);

	/**
	 * 查询不再魔点设备的在场项目人员
	 * @param projectId
	 * @return
	 */
	List<HjProjectWorkers> selectHjProjectWorkersNotMoreDianByprojectId(@Param(value = "projectId") Integer projectId);

	/**
	 * 人员信息 总数
	 * @param empNameParam
	 * @return
	 */
    Integer selectProjectWorkersCount(EmpNameParam empNameParam);

	/**
	 * 人员信息
	 * @param empNameParam
	 * @return
	 */
	List<EmpNameParam> selectProjectWorkers(EmpNameParam empNameParam);

	/**
	 * 人员信息 所属单位
	 * @param projectWorkers
	 * @return
	 */
    ProjectWorkers selectConstructionProject(ProjectWorkers projectWorkers);

	/**
	 * 人员信息 详情
	 * @param projectWorkersParam
	 * @return
	 */
	ProjectWorkersParam selectProjectWorkersDetails(ProjectWorkersParam projectWorkersParam);

	/**
	 * 是否签订
	 * @param signParam
	 * @return
	 */
    SignParam selectSignParam(SignParam signParam);

	/**
	 * PC人员列表
	 * @param projectWorkers
	 * @return
	 */
	List<ProjectWorkerPC> selectProjectWorkersListPC(HjProjectWorkers projectWorkers);

	/**
	 * 人员同步进出或退场
	 * @param
	 * @return
	 */
	int updateHjProjectWorkersOutOrIn(@Param("ids")String[] ids,@Param("tag")Integer tag);

	/**
	 * 人员同步进出或退场
	 * @param
	 * @return
	 */
	int updateHjProjectWorkersOutOrInTwo(@Param("id")Integer id,@Param("tag")Integer tag);

	/**
	 * 根据id查询工人信息
	 * @param ids
	 * @return
	 */
	List<PdfWorkers> selectPdfWorkers(@Param("ids")String[] ids);


    List<HjProjectWorkers> selectEmpCategory(Integer pid);

	Integer selectHjProjectWorkersListEmp(HjProjectWorkers hjProjectWorkers);

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
	public HjProjectWorkers seleimg(@Param("userid") Integer userid);
	public HjProjectWorkers seleimgs(@Param("userid") Integer userid);



	public List<HjProjectWorkers> listcount (@Param("projectId") Integer projectId,
										@Param("passedTime") String passedTime);

	public List<HjProjectWorkers> listcounts (@Param("projectId") Integer projectId,
										 @Param("passedTime") String passedTime,
										 @Param("ids") Integer ids);
	public HjProjectWorkers jyht(@Param("projectId") Integer projectId,@Param("contract") Integer contract);
	public HjProjectWorkers jc(@Param("projectId") Integer projectId,@Param("contract") Integer contract);
	public HjProjectWorkers tc(@Param("projectId") Integer projectId,@Param("contract") Integer contract);
	public HjProjectWorkers lzqrs(@Param("projectId") Integer projectId,@Param("contract") Integer contract);
	public HjProjectWorkers easyContract(@Param("projectId") Integer projectId);
}