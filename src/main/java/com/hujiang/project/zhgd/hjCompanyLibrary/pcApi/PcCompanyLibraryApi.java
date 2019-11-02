package com.hujiang.project.zhgd.hjCompanyLibrary.pcApi;



import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;

import com.hujiang.framework.web.domain.AjaxResult;

import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * pc端公司库表
 * @author hujiang
 * @date 2019-05-21
 */
@Controller
@RequestMapping(value = "/provider/pcCompanyLibrary" , method = RequestMethod.POST)
public class PcCompanyLibraryApi extends BaseController {
    private Logger logger = Logger.getLogger(PcCompanyLibraryApi.class.getName());
    @Autowired
    private IHjCompanyLibraryService hjCompanyLibraryService;
    @Autowired
    private IHjCompanyHierarchyService hjCompanyHierarchyService;
    @Autowired
    private IHjProjectService projectService;

    /**
     * 公司列表 分页
     * @param hjCompanyLibrary
     * @return
     */
    @RequestMapping("/companyLibraryList")
    @ResponseBody
    public TableDataInfo list(HjCompanyLibrary hjCompanyLibrary,Integer companyId)
    {
        hjCompanyLibrary.setShowState(0);
        System.out.println(companyId);
        startPage();
        List<HjCompanyLibrary> list = hjCompanyLibraryService.selectHjCompanyLibraryPage(hjCompanyLibrary,companyId);
        return getDataTable(list);
    }
    /**
     * 保存公司库信息
     * @param
     * @return
     */
    @RequestMapping("/insertHjCompanyLibrary")
    @ResponseBody
    public int insertHjCompanyLibrary(@RequestBody  HjCompanyLibrary hjCompanyLibrary,  Integer parentaId)
    {
//        System.out.println(parentaId);
        logger.info("保存公司信息库开始");
        hjCompanyLibraryService.insertHjCompanyLibrary(hjCompanyLibrary);
        logger.info("保存公司层级信息开始");

        HjCompanyHierarchy hjh2=new HjCompanyHierarchy();
        hjh2.setCompanyId(parentaId);
        List<HjCompanyHierarchy> list=hjCompanyHierarchyService.selectHjCompanyHierarchyList(hjh2);
        HjCompanyHierarchy hjh3=list.get(0);

        HjCompanyHierarchy hjh=new HjCompanyHierarchy();
        hjh.setCompanyId(hjCompanyLibrary.getId());
        hjh.setParentId(hjh3.getParentId()+","+hjh3.getCompanyId());
//        System.out.println(hjh);
        return hjCompanyHierarchyService.insertHjCompanyHierarchy(hjh);
    }

    /**
     * 根据id查询公司库信息
     * @param
     * @return
     */
    @RequestMapping("/selectHjCompanyLibrary")
    @ResponseBody
    public HjCompanyLibrary selectHjCompanyLibraryIds(Integer id)
    {
        logger.info("根据id查询公司库信息");
        return hjCompanyLibraryService.selectHjCompanyLibraryById(id);
    }
    /**
     * 查询公司库信息列表--根据单位类型查询
     * @param
     * @return
     */
    @RequestMapping("/selectHjCompanyLibraryList")
    @ResponseBody
    public List<HjCompanyLibrary> selectHjCompanyLibraryList(@RequestBody  HjCompanyLibrary hjCompanyLibrary)
    {
        logger.info("查询公司库信息列表");
//        System.out.println(hjCompanyLibrary);
        return hjCompanyLibraryService.selectHjCompanyLibraryList(hjCompanyLibrary);
    }
    /**
     * 修改保存公司库信息
     * @param
     * @return
     */
    @RequestMapping("/updateHjCompanyLibrary")
    @ResponseBody
    public int updateHjCompanyLibrary(@RequestBody HjCompanyLibrary hjCompanyLibrary)
    {
        logger.info("修改保存公司库信息");
        return hjCompanyLibraryService.updateHjCompanyLibrary(hjCompanyLibrary);
    }
    /**
     * 查询指定公司下公司和项目信息
     * @param
     * @return
     */
    @RequestMapping("/selectHjCompanyProjectList")
    @ResponseBody
    public Map<String,Object> selectHjCompanyProjectList( Integer companyId)
    {
        logger.info("查询指定公司下公司信息和项目信息");
        Map<String,Object> map=new HashMap<String,Object>();
        //获取指定公司下的公司信息
        List<HjCompanyLibrary> hjCompanyLibraryList=hjCompanyLibraryService.selectHjCompanyLibraryListTwo(companyId);
        //获取指定公司下的项目信息
        List<HjProject> projectList=projectService.selectHjProjectListTwo(companyId);
        map.put("companyList",hjCompanyLibraryList);
        map.put("projectList",projectList);
        return map;
    }

    /**
     * 查询子公司列表
     * @param companyId 父公司id
     * @return 子公司列表
     * yant
     */
    @RequestMapping("/selectHjCompanyList")
    @ResponseBody
    public List<HjCompanyLibrary> selectHjCompanyList( Integer companyId) {
        logger.info("查询指定子公司信息");
        Map<String, Object> map = new HashMap<String, Object>();
        //获取指定公司的子公司信息
        List<HjCompanyLibrary> hjCompanyLibraryList = hjCompanyLibraryService.selectHjCompanyLibraryListTwo(companyId);
        return hjCompanyLibraryList;
    }

    /**
     * 删除公司库信息
     * @param
     * @return
     */
    @RequestMapping("/deleteHjCompanyLibrary")
    @ResponseBody
    public AjaxResult deleteHjCompanyLibraryIds(String ids)
    {
        logger.info("删除公司库信息");
        String[] id=ids.split(",");
        //
        for(int j=0;j<id.length;j++){
            System.out.println(id[j]);
            Integer companyId=Integer.valueOf(id[j]);
            //获取指定公司下的公司信息
            List<HjCompanyLibrary> hjCompanyLibraryList=hjCompanyLibraryService.selectHjCompanyLibraryListTwo(companyId);
            if(hjCompanyLibraryList.size()>0){
                return AjaxResult.error("项目编号为"+id[j]+"的公司下有子公司，无法删除");
            }
            //获取指定公司下的项目信息
            List<HjProject> projectList=projectService.selectHjProjectListTwo(companyId);
            if(projectList.size()>0){
                return AjaxResult.error("项目编号为"+id[j]+"的公司下有项目，无法删除");
            }
        }
       int i=hjCompanyLibraryService.deleteHjCompanyLibraryByIdsTwo(ids);
        if(i<1){
            AjaxResult.error("删除失败");
        }
        return AjaxResult.success("删除成功");
    }

}
