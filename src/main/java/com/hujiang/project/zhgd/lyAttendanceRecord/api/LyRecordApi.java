package com.hujiang.project.zhgd.lyAttendanceRecord.api;

import com.hujiang.common.utils.StringUtils;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecordPersonnel;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyRecordExport;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/lyRecord")
public class LyRecordApi extends BaseController {
    @Autowired
    private ILyAttendanceRecordService lyAttendanceRecordService;


    /**
     * 信息查询人员资料
     */
    @PostMapping("/selectPersonnelRecord")
    public AjaxResult selectPersonnelRecord( LyAttendanceRecord lyAttendanceRecord){
            List<LyAttendanceRecord> lar=lyAttendanceRecordService.selectLyAttendanceRecordListTwo(lyAttendanceRecord);
            return AjaxResult.success(lar);
    }
    /**
     * pc通行记录
     */
    @PostMapping("/selectPersonnelRecordPageList")
    public AjaxResult selectPersonnelRecordPageList( LyAttendanceRecordPersonnel lyAttendanceRecordPersonnel){
        startPage();
        List<LyAttendanceRecordPersonnel> larpList=lyAttendanceRecordService.selectPersonnelRecordPageList(lyAttendanceRecordPersonnel);
        return AjaxResult.success(getDataTable(larpList));
    }
    @GetMapping("/export")
    public void selectPersonnelRecordPageList( LyRecordExport lyRecordExport, HttpServletResponse response)throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        String time="";
        if(StringUtils.isBlank(lyRecordExport.getPassedTime()))
        {
            time=sdf.format(new Date());
            lyRecordExport.setPassedTime(time);
        }else{
            time=lyRecordExport.getPassedTime();
        }
        List<LyRecordExport> list=lyAttendanceRecordService.selectRecordExport(lyRecordExport);
        //excel标题
        String[] title = {"工号", "姓名", "日期", "签到时间", "签退时间","体温","部门"};

        //excel文件名
        String fileName = "记录" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "记录";

        String content[][] = new String[list.size()][title.length];
        for (int i = 0; i < list.size(); i++) {


            LyRecordExport obj = list.get(i);
            content[i][0] = obj.getEmpNumber();
            content[i][1] = obj.getEmpName();
            content[i][2] = time;
            content[i][3] = StringUtils.isBlank(obj.getMinTime())?"":sdf2.format(sdf3.parse(obj.getMinTime()));
            content[i][4] =StringUtils.isBlank(obj.getMaxTime())?"":sdf2.format(sdf3.parse(obj.getMaxTime()));
            content[i][5] =obj.getTemperature();
            content[i][6]=obj.getSubordinate();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbookLyRecord(sheetName, title, content, null);
        //响应到客户端
        try {
            try {
                try {
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                response.setContentType("application/octet-stream;charset=ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.addHeader("Pargam", "no-cache");
                response.addHeader("Cache-Control", "no-cache");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 首页职员和访客统计
     */
    @PostMapping("/getRecordCount")
    public AjaxResult getRecordCount(Integer pid){
        Map<String,String> map=new HashMap<String,String>();
        map.put("pid",pid.toString());
        map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("type","1");
        Map<String,Integer> result=new HashMap<String,Integer>();
        int zzryRecordCount=lyAttendanceRecordService.getRecordCount(map);
        map.put("type","2");
        int fkryRecordCount=lyAttendanceRecordService.getRecordCount(map);
        result.put("zzryRecordCount",zzryRecordCount);
        result.put("fkryRecordCount",fkryRecordCount);
        return AjaxResult.success(result);


    }
}
