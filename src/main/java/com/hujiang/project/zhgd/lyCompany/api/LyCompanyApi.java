package com.hujiang.project.zhgd.lyCompany.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.lyCompany.domain.LyCompany;
import com.hujiang.project.zhgd.lyCompany.service.ILyCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼宇公司表
 */
@RestController
@RequestMapping(value = "/api/lyCompany",method = RequestMethod.POST)
public class LyCompanyApi extends BaseController {
    @Autowired
    private ILyCompanyService lyCompanyService;
    /**
     * 分页查询
     */
    @PostMapping("/selectPageList")
    public TableDataInfo selectPageList( LyCompany lyCompany){
        startPage();
        List<LyCompany> list = lyCompanyService.selectLyCompanyList(lyCompany);
        return getDataTable(list);
    }
    /**
     * 查询
     */
    @PostMapping("/selectList")
    public AjaxResult selectList( LyCompany lyCompany){

        List<LyCompany> list = lyCompanyService.selectLyCompanyList(lyCompany);
        return AjaxResult.success(list);
    }
    /**
     * 新增
     */
    @PostMapping("/insert")
    public AjaxResult insert( LyCompany lyCompany){
     int i=   lyCompanyService.insertLyCompany(lyCompany);
     if(i>0){
         return AjaxResult.success("成功");
     }
        return AjaxResult.error("失败");
    }
    /**
     * 修改
     */
    @PostMapping("/selectId")
    public AjaxResult selectId(@RequestParam Integer id){
        LyCompany lyCompany=lyCompanyService.selectLyCompanyById(id);

            return AjaxResult.success(lyCompany);

    }
    /**
     * 修改
     */
    @PostMapping("/update")
    public AjaxResult update( LyCompany lyCompany){
        int i=   lyCompanyService.updateLyCompany(lyCompany);
        if(i>0){
            return AjaxResult.success("成功");
        }
        return AjaxResult.error("失败");
    }
    /**
     * 删除
     */
    @PostMapping("/delete")
    public AjaxResult delete(@RequestParam String ids){
        int i=   lyCompanyService.deleteLyCompanyByIds(ids);
        if(i>0){
            return AjaxResult.success("成功");
        }
        return AjaxResult.error("失败");
    }
//    @PostMapping("/companyPerson")
//    public AjaxResult companyPerson(Integer pid){
//        return
//    }









}
