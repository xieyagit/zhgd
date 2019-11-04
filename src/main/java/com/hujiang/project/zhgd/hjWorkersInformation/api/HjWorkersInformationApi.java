package com.hujiang.project.zhgd.hjWorkersInformation.api;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformation;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformationPc;
import com.hujiang.project.zhgd.hjWorkersInformation.service.IHjWorkersInformationService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/workersInformationAp")
public class HjWorkersInformationApi extends BaseController {

    @Autowired
    private IHjWorkersInformationService hjWorkersInformationService;

    @PostMapping(value = "/insteradd")
    public JSONObject add(@RequestBody HjWorkersInformation hjWorkersInformation) throws IOException {
        if (hjWorkersInformation.getPath()!=null && !hjWorkersInformation.getPath().equals("")) {
            HjWorkersInformation hjWorkersInformation1 = new HjWorkersInformation();
            hjWorkersInformation1.setProjectId(hjWorkersInformation.getProjectId());
            hjWorkersInformation1.setUserId(hjWorkersInformation.getUserId());
            List<HjWorkersInformation> hjWorkersInformations = hjWorkersInformationService.selectall(hjWorkersInformation1);
            if (hjWorkersInformations.size() <= 0) {
                return this.insteradd(hjWorkersInformation);
            } else {
                return this.uodate(hjWorkersInformation);
            }
        }else{
            JSONObject jsonObject = new JSONObject();
            HjWorkersInformation workersInformation = new HjWorkersInformation();
            //文件服务器路径
            String url = null;
            if (hjWorkersInformation.getTypeid() == 1){                     //简易劳动合同
                workersInformation.setUserId(hjWorkersInformation.getUserId());
                int i = hjWorkersInformationService.update1(workersInformation);
                int k = hjWorkersInformationService.updatematerials(hjWorkersInformation.getUserId());
                if (i>0) {
                    jsonObject.put("msg", "删除图片成功");
                    jsonObject.put("result_code", 0);
                    jsonObject.put("data", i);
                    return jsonObject;
                }else {
                    jsonObject.put("msg", "删除图片失败");
                    jsonObject.put("result_code", 1);
                    jsonObject.put("data", i);
                    return jsonObject;
                }
            }else if (hjWorkersInformation.getTypeid() == 2){               //两制确认书
                workersInformation.setUserId(hjWorkersInformation.getUserId());
                int i = hjWorkersInformationService.update2(workersInformation);
                int k = hjWorkersInformationService.updatematerials(hjWorkersInformation.getUserId());
                if (i>0) {
                    jsonObject.put("msg", "删除图片成功");
                    jsonObject.put("result_code", 0);
                    jsonObject.put("data", i);
                    return jsonObject;
                }else {
                    jsonObject.put("msg", "删除图片失败");
                    jsonObject.put("result_code", 1);
                    jsonObject.put("data", i);
                    return jsonObject;
                }
            }else if (hjWorkersInformation.getTypeid() == 3){               //进场确认书
                workersInformation.setUserId(hjWorkersInformation.getUserId());
                int i = hjWorkersInformationService.update3(workersInformation);
                int k = hjWorkersInformationService.updatematerials(hjWorkersInformation.getUserId());
                if (i>0) {
                    jsonObject.put("msg", "删除图片成功");
                    jsonObject.put("result_code", 0);
                    jsonObject.put("data", i);
                    return jsonObject;
                }else {
                    jsonObject.put("msg", "删除图片失败");
                    jsonObject.put("result_code", 1);
                    jsonObject.put("data", i);
                    return jsonObject;
                }
            }else if (hjWorkersInformation.getTypeid() == 4){               //退场确认书
                workersInformation.setUserId(hjWorkersInformation.getUserId());
                int i = hjWorkersInformationService.update4(workersInformation);
                int k = hjWorkersInformationService.updatematerials(hjWorkersInformation.getUserId());
                if (i>0) {
                    jsonObject.put("msg", "删除图片成功");
                    jsonObject.put("result_code", 0);
                    jsonObject.put("data", i);
                    return jsonObject;
                }else {
                    jsonObject.put("msg", "删除图片失败");
                    jsonObject.put("result_code", 1);
                    jsonObject.put("data", i);
                    return jsonObject;
                }
            }else if (hjWorkersInformation.getTypeid() == 5){               //身份证真反面复印件
                workersInformation.setUserId(hjWorkersInformation.getUserId());
                int i = hjWorkersInformationService.update5(workersInformation);
                int k = hjWorkersInformationService.updatematerials(hjWorkersInformation.getUserId());
                if (i>0) {
                    jsonObject.put("msg", "删除图片成功");
                    jsonObject.put("result_code", 0);
                    jsonObject.put("data", i);
                    return jsonObject;
                }else {
                    jsonObject.put("msg", "删除图片失败");
                    jsonObject.put("result_code", 1);
                    jsonObject.put("data", i);
                    return jsonObject;
                }
            }
        }
        return null;
    }
    /** 第一次添加*/
    public JSONObject insteradd(HjWorkersInformation hjWorkersInformation) throws IOException {
        JSONObject jsonObject = new JSONObject();
        HjWorkersInformation workersInformation = new HjWorkersInformation();
        if (hjWorkersInformation.getTypeid() == 1){                     //简易劳动合同
            workersInformation.setLaborContract(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 2){               //两制确认书
            workersInformation.setTwoSystems(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 3){               //进场确认书
            workersInformation.setEnter(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 4){               //退场确认书
            workersInformation.setCome(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 5){               //身份证真反面复印件
            workersInformation.setIdentity(hjWorkersInformation.getPath());
        }
        workersInformation.setUserId(hjWorkersInformation.getUserId());
        HjWorkersInformation information = hjWorkersInformationService.sele(hjWorkersInformation.getUserId());
        if (information != null ) {
            if ((information.getLaborContract() != null && information.getLaborContract() != "" && !information.getLaborContract().equals("")) &&
                    (information.getTwoSystems() != null && information.getTwoSystems() != "" && !information.getTwoSystems().equals("")) &&
                    (information.getEnter() != null && information.getEnter() != "" && !information.getEnter().equals("")) &&
                    (information.getIdentity() != null && information.getIdentity() != "" && !information.getIdentity().equals("")) &&
                    (information.getCome() != null && information.getCome() != "" && !information.getCome().equals(""))) {
                int k = hjWorkersInformationService.updatematerial(information.getId());
                if (k > 0) {
                    jsonObject.put("msgs", "状态修改成功：已齐全");
                    jsonObject.put("result_codes", 0);
                    jsonObject.put("datas", k);
                } else {
                    jsonObject.put("msgs", "状态修改失败");
                    jsonObject.put("result_codes", 1);
                    jsonObject.put("datas", k);
                }
            } else {
                int k = hjWorkersInformationService.updatematerials(information.getId());
                if (k > 0) {
                    jsonObject.put("msgs", "状态修改成功：未齐全");
                    jsonObject.put("result_codes", 0);
                    jsonObject.put("datas", k);
                } else {
                    jsonObject.put("msgs", "状态修改失败");
                    jsonObject.put("result_codes", 1);
                    jsonObject.put("datas", k);
                }
            }
        }
        int i = hjWorkersInformationService.insteradd(workersInformation);
        if (i>0) {
            jsonObject.put("msg", "添加图片成功");
            jsonObject.put("result_code", 0);
            jsonObject.put("data", i);
        }else {
            jsonObject.put("msg", "添加图片失败");
            jsonObject.put("result_code", 1);
            jsonObject.put("data", i);
        }
        return jsonObject;
    }

    /**
     * 继续上传（修改）
     * */
    public JSONObject uodate(HjWorkersInformation hjWorkersInformation) throws IOException {
        JSONObject jsonObject = new JSONObject();
        HjWorkersInformation workersInformation = new HjWorkersInformation();
        if (hjWorkersInformation.getTypeid() == 1){                     //简易劳动合同
            workersInformation.setLaborContract(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 2){               //两制确认书
            workersInformation.setTwoSystems(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 3){               //进场确认书
            workersInformation.setEnter(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 4){               //退场确认书
            workersInformation.setCome(hjWorkersInformation.getPath());
        }else if (hjWorkersInformation.getTypeid() == 5){               //身份证真反面复印件
            workersInformation.setIdentity(hjWorkersInformation.getPath());
        }
        workersInformation.setUserId(hjWorkersInformation.getUserId());
        int i = hjWorkersInformationService.update(workersInformation);
        Integer userId = hjWorkersInformation.getUserId();
        HjWorkersInformation information = hjWorkersInformationService.sele(userId);
        if((information.getLaborContract() != null && information.getLaborContract()!="" && !information.getLaborContract().equals(""))&&
                (information.getTwoSystems() != null && information.getTwoSystems()!= "" && !information.getTwoSystems().equals(""))&&
                (information.getEnter() != null && information.getEnter()!="" && !information.getEnter().equals(""))&&
                (information.getIdentity() != null && information.getIdentity() != "" && !information.getIdentity().equals(""))&&
                (information.getCome() != null && information.getCome() != "" && !information.getCome().equals(""))){
            int k = hjWorkersInformationService.updatematerial(information.getId());
            if (k>0) {
                jsonObject.put("msgs", "状态修改成功：已齐全");
                jsonObject.put("result_codes", 0);
                jsonObject.put("datas", k);
            } else {
                jsonObject.put("msgs", "状态修改失败");
                jsonObject.put("result_codes", 1);
                jsonObject.put("datas", k);
            }
        } else {
            int k = hjWorkersInformationService.updatematerials(information.getId());
            if (k>0) {
                jsonObject.put("msgs", "状态修改成功：未齐全");
                jsonObject.put("result_codes", 0);
                jsonObject.put("datas", k);
            }else {
                jsonObject.put("msgs", "状态修改失败");
                jsonObject.put("result_codes", 1);
                jsonObject.put("datas", k);
            }
        }
        if (i>0) {
            jsonObject.put("msg", "添加图片成功");
            jsonObject.put("result_code", 0);
            jsonObject.put("data", i);
            return jsonObject;
        }else {
            jsonObject.put("msg", "添加图片失败");
            jsonObject.put("result_code", 1);
            jsonObject.put("data", i);
            return jsonObject;
        }
    }

    /**
     * 查询所有
     * */
    @RequestMapping(value = "/selectall")
    @ResponseBody
    public JSONObject selectall(@RequestBody HjWorkersInformation hjWorkersInformation){

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        HjWorkersInformation information = new HjWorkersInformation();
        information.setEmpName(hjWorkersInformation.getEmpName());                                      //姓名
        information.setIdCode(hjWorkersInformation.getIdCode());                                        //身份证号码
        information.setJobName(hjWorkersInformation.getJobName());                                      //工种名称
        information.setWorkTypenameId(hjWorkersInformation.getWorkTypenameId());                         //所属班组
        information.setEmpDept(hjWorkersInformation.getEmpDept());                                      //所属工作部门
        information.setEnterAndRetreatCondition(hjWorkersInformation.getEnterAndRetreatCondition());    //进退场状态（0、进场。1、退场,2未同步）
        information.setMaterial(hjWorkersInformation.getMaterial());                                    //状态资料是否齐全（0、不齐全，1、齐全）
        information.setConstructionId(hjWorkersInformation.getConstructionId());                        //所属分包单位
        information.setProjectId(hjWorkersInformation.getProjectId());                                  //项目ID
        List<HjWorkersInformation> result = hjWorkersInformationService.selectall(information);
        jsonObject.put("count",result.size());
        startPage();
        List<HjWorkersInformation> result1 = hjWorkersInformationService.team(hjWorkersInformation.getProjectId());
        List<HjWorkersInformation> result2 = hjWorkersInformationService.dictionaries(hjWorkersInformation.getProjectId());
        List<HjWorkersInformation> result3 = hjWorkersInformationService.company(hjWorkersInformation.getProjectId());
        for (HjWorkersInformation hjWorkersInformation1 : result1){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("workTypenameId",hjWorkersInformation1.getId());
            jsonObject1.put("teamName",hjWorkersInformation1.getEmpName());
            jsonArray1.add(jsonObject1);
        }
        for (HjWorkersInformation hjWorkersInformation2 : result2){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("jobName",hjWorkersInformation2.getJobName());
            jsonObject1.put("title",hjWorkersInformation2.getTitle());
            jsonArray2.add(jsonObject1);
        }
        for (HjWorkersInformation hjWorkersInformation3 : result3){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("constructionId",hjWorkersInformation3.getConstructionId());
            jsonObject1.put("name",hjWorkersInformation3.getEmpName());
            jsonArray3.add(jsonObject1);
        }
        TableDataInfo dataTable = getDataTable(result);
        for (HjWorkersInformation workersInformation : result){
            JSONObject object = new JSONObject();
            object.put("id",workersInformation.getId());
            object.put("empName",workersInformation.getEmpName());
            if (workersInformation.getLaborContract()!=null&& workersInformation.getLaborContract()!=""&& !workersInformation.getLaborContract().equals("")){
                object.put("laborContract",1);
            }else {
                object.put("laborContract", 0);
            }
            if (workersInformation.getTwoSystems()!=null&& workersInformation.getTwoSystems()!=""&& !workersInformation.getTwoSystems().equals("") ){
                object.put("towSystems",1);
            }else {
                object.put("towSystems",0);
            }
            if (workersInformation.getEnter()!=null && workersInformation.getEnter()!=""&& !workersInformation.getEnter().equals("")){
                object.put("enter",1);
            }else {
                object.put("enter",0);
            }
            if (workersInformation.getCome()!=null&& workersInformation.getCome()!=""&& !workersInformation.getCome().equals("") ){
                object.put("come",1);
            }else {
                object.put("come",0);
            }
            if (workersInformation.getIdentity()!=null&& workersInformation.getIdentity()!=""&& !workersInformation.getIdentity().equals("") ){
                object.put("identity",1);
            }else {
                object.put("identity",0);
            }

            if ((workersInformation.getIdentity()!=null&& workersInformation.getIdentity()!=""&& !workersInformation.getIdentity().equals(""))&&
                    (workersInformation.getCome()!=null&& workersInformation.getCome()!=""&& !workersInformation.getCome().equals(""))&&
                    (workersInformation.getEnter()!=null && workersInformation.getEnter()!=""&& !workersInformation.getEnter().equals(""))&&
                    (workersInformation.getTwoSystems()!=null&& workersInformation.getTwoSystems()!=""&& !workersInformation.getTwoSystems().equals(""))&&
                    (workersInformation.getLaborContract()!=null&& workersInformation.getLaborContract()!=""&& !workersInformation.getLaborContract().equals("")))
            {
                object.put("material",1);
            }else {
                object.put("material",0);
            }
            jsonArray.add(object);
        }

        jsonObject.put("msg","查询成功");
        jsonObject.put("result_code",0);
        jsonObject.put("data",jsonArray);
        jsonObject.put("data1",jsonArray1);
        jsonObject.put("data2",jsonArray2);
        jsonObject.put("data3",jsonArray3);
        return jsonObject;
    }



    @PostMapping("/delete")
    public JSONObject delete(@RequestParam(value ="id" )Integer id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","删除成功");
        jsonObject.put("code",0);
        Integer result = hjWorkersInformationService.delete(id);
        jsonObject.put("data",result);
        return jsonObject;
    }


    /** 统计*/
    @PostMapping("/lzfwtj")
    public JSONObject selectzhiliaoqiquan(@RequestParam(value ="projectId" )Integer projectId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        String[] result = new String[14];
        JSONObject object = new JSONObject();
        for (int i = 0 ; i<result.length;i++){
            if (i==0){
                result[i] = String.valueOf(hjWorkersInformationService.all(projectId));                //劳工总人数
                object.put("count",result[i]);
            }else if (i==1){
                result[i] = String.valueOf(hjWorkersInformationService.zaichsng(projectId));             //查询在场人数
                object.put("zaichang",result[i]);
            }else if (i==2){
                result[i] = String.valueOf(hjWorkersInformationService.selectzhiliaoqiquan(projectId)); //质料齐全
                object.put("ziliao",result[i]);
            }else if (i==3){
                double q = Double.valueOf(result[2]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                          //资料齐全百分比
                object.put("ziliaobfb",result[i]);
            } else if (i==4){
                result[i] = String.valueOf(hjWorkersInformationService.jyht(projectId));                //已有简易合同的总人数
                object.put("jyht",result[i]);
            } else if (i==5){
                double q = Double.valueOf(result[4]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                                //已有简易合同百分比
                object.put("jyhtbfb",result[i]);
            } else if (i==6){
                result[i] = String.valueOf(hjWorkersInformationService.lzqrs(projectId));                 //已有两制确认书的总人数
                object.put("lzqrs",result[i]);
            } else if (i==7){
                double q = Double.valueOf(result[6]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                  //已有两制确认书百分比
                object.put("lzqrsbfb",result[i]);
            } else if (i==8){
                result[i] = String.valueOf(hjWorkersInformationService.jcqrs(projectId));                //已有进场确认书的总人数
                object.put("jcqrs",result[i]);
            } else if (i==9){
                double q = Double.valueOf(result[8]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                  //已有进场确认书的总人数
                object.put("jcqrsbfb",result[i]);
            } else if (i==10){
                result[i] = String.valueOf(hjWorkersInformationService.sfzfyj(projectId));                //已有身份证正反面复印件的总人数
                object.put("sfzfyj",result[i]);
            } else if (i==11){
                double q = Double.valueOf(result[10]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                //已有身份证正反面复印件百分比
                object.put("sfzfyjbfb",result[i]);
            } else if (i==12){
                result[i] = String.valueOf(hjWorkersInformationService.come(projectId));                //已有退场确认书的总人数
                object.put("come",result[i]);
            } else if (i==13){
                double q = Double.valueOf(result[12]);
                double l = Double.valueOf(result[0]);
                Object util = Util.accuracy(q,l,2);
                result[i] = String.valueOf(util+"%");                 //已有退场确认书百分比
                object.put("comebfb",result[i]);
            }
        }
        jsonObject.put("data",object);
        return jsonObject;
    }

    /**查看详情*/
    @PostMapping(value = "/sele")
    public JSONObject sele(@RequestParam(value = "userid") Integer userid){
        HjWorkersInformation hjWorkersInformation = hjWorkersInformationService.sele(userid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",hjWorkersInformation);
        return jsonObject;
    }

     /**
     * 导出工作汇报
     * @return
             */
    @PostMapping("/export")
    public List<HjWorkersInformationPc> export(@RequestBody HjWorkersInformation hjWorkersInformation)
    {
        return hjWorkersInformationService.selectalls(hjWorkersInformation);
    }
}
