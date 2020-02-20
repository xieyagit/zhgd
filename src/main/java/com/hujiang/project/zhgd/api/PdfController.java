package com.hujiang.project.zhgd.api;

import com.hujiang.common.exception.BusinessException;
import com.hujiang.project.zhgd.hjProjectWorkers.api.PC_ProjectWorkersApi;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.PdfWorkers;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Consumer01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-04 09:07
 **/
@Controller
@RequestMapping("pdf")
public class PdfController {
    @Autowired
    private PC_ProjectWorkersApi pc_projectWorkersApi;
    private String prefix = "pdf";


//    @RequestMapping("test")
//    public String test(ModelMap mmap, Model model){
//
//        mmap.put("hh","33333");
//        model.addAttribute("hh","123");
//        return "pdf/jc";
//    }

    /**
     *
     * @param ids 人员id
     * @param response
     * @param tag 合同类型标签 1 进场确认书 2劳动合同 3退场确认书 4两制确认书 5身份证正反面
     * @throws Exception
     */
    @RequestMapping(value = "/downloadIDCardPDF", method = RequestMethod.GET)
    public void downloadIDCardPDF(@RequestParam(value = "ids") String ids, HttpServletResponse response, Integer tag) throws Exception {
        String zipUrl=null;
        String zipName=null;
        switch (tag){
            case 1:
                zipUrl = this.getJCQRS(ids).get("zipUrl").toString();
                zipName = this.getJCQRS(ids).get("zipName").toString();
                break;
            case 2:
                zipUrl = this.getLDHT(ids).get("zipUrl").toString();
                zipName = this.getLDHT(ids).get("zipName").toString();
                break;
            case 3:
                zipUrl = this.getTC(ids).get("zipUrl").toString();
                zipName = this.getTC(ids).get("zipName").toString();
                break;
            case 4:
                zipUrl = this.getLZ(ids).get("zipUrl").toString();
                zipName = this.getLZ(ids).get("zipName").toString();
                break;
            case 5:
                zipUrl = this.getSFZ(ids).get("zipUrl").toString();
                zipName = this.getSFZ(ids).get("zipName").toString();
                break;
        }


        //根据url获取输入流
        URL url = new URL(zipUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        //下载
        try (InputStream inputStream = conn.getInputStream();
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/zip");

            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipName+".zip", "UTF-8"));
            IOUtils.copy(inputStream, outputStream);

            //删除文件
            AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(),"hujiang", "zip"+zipName+".zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 进场
     * @param ids
     * @return
     */
    private Map<String,Object> getJCQRS(String ids){
        Map<String,Object> map = new HashMap<>();
        //工人进场合同模板
        String jc = "jc.html";
        String bzzjc = "bzzjc.html";
        List<PdfWorkers> pdfWorkers = pc_projectWorkersApi.selectPdfWorkers(ids);
        List<String> oldResPath = new ArrayList<String>();
        // 文件路径
        String zipName = "进场确认书" + ZipUtil.dateToString();
        map.put("zipName",zipName);
        String newResPath = Utils.getPath()+"\\" + zipName; // 生成的文件夹名
        //遍历人员信息
        for(PdfWorkers pdf: pdfWorkers){
            if(pdf.getIsTeam()==0){//普通劳务工人
                PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),jc);
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("constructionName",pdf.getConstructionName()==null?"":pdf.getConstructionName());
                paraMap.put("empName",pdf.getEmpName()==null?"":pdf.getEmpName());
                paraMap.put("idCode",pdf.getIdCode()==null?"":pdf.getIdCode());
                paraMap.put("teamName",pdf.getTeamName()==null?"":pdf.getTeamName());
                paraMap.put("projectName",pdf.getProjectName()==null?"":pdf.getProjectName());
                try {
                    String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"进场确认书");
                    //添加pdf
                    oldResPath.add(uploadfile);
                } catch (Exception e) {
                    throw new BusinessException( pdf.getEmpName()+"进场确认书生成失败");
                }
            }else{//班组长
                PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),bzzjc);
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("constructionName",pdf.getConstructionName());
                paraMap.put("empName",pdf.getEmpName());
                paraMap.put("idCode",pdf.getIdCode());
                paraMap.put("teamName",pdf.getTeamName());
                paraMap.put("projectName",pdf.getProjectName());
                try {
                    String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"班组长进场确认书");
                    //添加pdf
                    oldResPath.add(uploadfile);
                } catch (Exception e) {
                    throw new BusinessException( pdf.getEmpName()+"班组长进场确认书生成失败");
                }
            }

        }
        //生成进场合同zip
        String zipPath = newResPath + ".zip";                  // 压缩文件夹名
        ZipUtil.copyResource(oldResPath, newResPath);                  // 把pdf拷贝到同个文件目录下
        ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包
        String zipUrl = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(), new File(zipPath), "hujiang", "zip");
        map.put("zipUrl",zipUrl);
        //删除zip文件
        boolean delete = new File(zipPath).delete();
        //删除pdf文件
        for(String s :oldResPath){
            boolean delete1 = new File(s).delete();
        }
        return map;
    }
    /**
     * 退场
     * @param ids
     * @return
     */
    private Map<String,Object> getTC(String ids){
        Map<String,Object> map = new HashMap<>();
        //工人退场合同模板
        String tc = "tc.html";
        String bzztc = "bzztc.html";
        List<PdfWorkers> pdfWorkers = pc_projectWorkersApi.selectPdfWorkers(ids);
        List<String> oldResPath = new ArrayList<String>();
        // 文件路径
        String zipName = "退场确认书" + ZipUtil.dateToString();
        map.put("zipName",zipName);
        String newResPath = Utils.getPath()+"\\" + zipName; // 生成的文件夹名
        //遍历人员信息
        for(PdfWorkers pdf: pdfWorkers){
            if(pdf.getIsTeam()==0){//普通劳务工人
                PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),tc);
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("pdf",pdf);
                try {
                    String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"退场确认书");
                    //添加pdf
                    oldResPath.add(uploadfile);
                } catch (Exception e) {
                    throw new BusinessException( pdf.getEmpName()+"退场确认书生成失败");
                }
            }else{//班组长
                PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),bzztc);
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("pdf",pdf);
                try {
                    String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"班组长退场确认书");
                    //添加pdf
                    oldResPath.add(uploadfile);
                } catch (Exception e) {
                    throw new BusinessException( pdf.getEmpName()+"班组长退场确认书生成失败");
                }
            }

        }
        //生成进场合同zip
        String zipPath = newResPath + ".zip";                  // 压缩文件夹名
        ZipUtil.copyResource(oldResPath, newResPath);                  // 把pdf拷贝到同个文件目录下
        ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包
        String zipUrl = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(), new File(zipPath), "hujiang", "zip");
        map.put("zipUrl",zipUrl);
        //删除zip文件
        boolean delete = new File(zipPath).delete();
        //删除pdf文件
        for(String s :oldResPath){
            boolean delete1 = new File(s).delete();
        }
        return map;
    }
    /**
     * 劳动合同
     * @param ids
     * @return
     */
    private Map<String,Object> getLDHT(String ids){
        Map<String,Object> map = new HashMap<>();
        //劳动合同模板
        String ldht = "ldht.html";
        List<PdfWorkers> pdfWorkers = pc_projectWorkersApi.selectPdfWorkers(ids);
        List<String> oldResPath = new ArrayList<String>();
        // 文件路径
        String zipName = "劳动合同" + ZipUtil.dateToString();
        map.put("zipName",zipName);
        String newResPath = Utils.getPath()+"\\" + zipName; // 生成的文件夹名
        //遍历人员信息
        for(PdfWorkers pdf: pdfWorkers){
                PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),ldht);
                Map<String, Object> paraMap = new HashMap<String, Object>();
            pdf.setAddress(pdf.getAddress()==null?"":pdf.getAddress());
            pdf.setLegalPerson(pdf.getLegalPerson()==null?"":pdf.getLegalPerson());
                paraMap.put("pdf",pdf);

                try {
                    String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"劳动合同");
                    //添加pdf
                    oldResPath.add(uploadfile);
                } catch (Exception e) {
                    throw new BusinessException( pdf.getEmpName()+"劳动合同生成失败");
                }

        }
        //生成进场合同zip
        String zipPath = newResPath + ".zip";                  // 压缩文件夹名
        ZipUtil.copyResource(oldResPath, newResPath);                  // 把pdf拷贝到同个文件目录下
        ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包
        String zipUrl = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(), new File(zipPath), "hujiang", "zip");
        map.put("zipUrl",zipUrl);
        //删除zip文件
        boolean delete = new File(zipPath).delete();
        //删除pdf文件
        for(String s :oldResPath){
            boolean delete1 = new File(s).delete();
        }
        return map;
    }
    /**
     * 两制确认书
     * @param ids
     * @return
     */
    private Map<String,Object> getLZ(String ids){
        Map<String,Object> map = new HashMap<>();
        //两制确认书模板
        String lz = "lz.html";
        List<PdfWorkers> pdfWorkers = pc_projectWorkersApi.selectPdfWorkers(ids);
        List<String> oldResPath = new ArrayList<String>();
        // 文件路径
        String zipName = "两制确认书" + ZipUtil.dateToString();
        map.put("zipName",zipName);
        String newResPath = Utils.getPath()+"\\" + zipName; // 生成的文件夹名
        //遍历人员信息
        for(PdfWorkers pdf: pdfWorkers){
            PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),lz);
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("pdf",pdf);

            try {
                String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"两制确认书");
                //添加pdf
                oldResPath.add(uploadfile);
            } catch (Exception e) {
                throw new BusinessException( pdf.getEmpName()+"两制确认书生成失败");
            }

        }
        //生成进场合同zip
        String zipPath = newResPath + ".zip";                  // 压缩文件夹名
        ZipUtil.copyResource(oldResPath, newResPath);                  // 把pdf拷贝到同个文件目录下
        ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包
        String zipUrl = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(), new File(zipPath), "hujiang", "zip");
        map.put("zipUrl",zipUrl);
        //删除zip文件
        boolean delete = new File(zipPath).delete();
        //删除pdf文件
        for(String s :oldResPath){
            boolean delete1 = new File(s).delete();
        }
        return map;
    }
    /**
     * 身份证
     * @param ids
     * @return
     */
    private Map<String,Object> getSFZ(String ids){
        Map<String,Object> map = new HashMap<>();
        //两制确认书模板
        String lz = "sfz.html";
        List<PdfWorkers> pdfWorkers = pc_projectWorkersApi.selectPdfWorkers(ids);
        List<String> oldResPath = new ArrayList<String>();
        // 文件路径
        String zipName = "身份证正反面" + ZipUtil.dateToString();
        map.put("zipName",zipName);

        String newResPath = Utils.getPath()+"\\" + zipName; // 生成的文件夹名
        //遍历人员信息
        for(PdfWorkers pdf: pdfWorkers){
            PDFUtil pdfUtil = new PDFUtil(Utils.getPath(),lz);
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("pdf",pdf);

            try {
                String uploadfile = pdfUtil.fillTemplate(paraMap,pdf.getEmpName()+"身份证正反面");
                //添加pdf
                oldResPath.add(uploadfile);
            } catch (Exception e) {
                throw new BusinessException( pdf.getEmpName()+"身份证正反面生成失败");
            }

        }
        //生成进场合同zip
        String zipPath = newResPath + ".zip";                  // 压缩文件夹名
        ZipUtil.copyResource(oldResPath, newResPath);                  // 把pdf拷贝到同个文件目录下
        ZipUtil.createZip(newResPath, zipPath);                        // 打包改目录成.zip包
        String zipUrl = AliyunOSSClientUtil.uploadObject2OSS(AliyunOSSClientUtil.getOSSClient(), new File(zipPath), "hujiang", "zip");
        map.put("zipUrl",zipUrl);
        //删除zip文件
        boolean delete = new File(zipPath).delete();
        //删除pdf文件
        for(String s :oldResPath){
            boolean delete1 = new File(s).delete();
        }
        return map;
    }



}
