package com.hujiang.common.utils.poi;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: Consumer01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-23 19:33
 **/
public class Util {
    /**
     * 获取图片上传路径
     * @return
     * @throws Exception
     */
    public static String getPath() {
        File upload=null;
        //路径
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
//如果上传目录为/static/images/upload/,则可以如下获取
            upload = new File(path.getAbsolutePath(), "static/upload/");

            if (!upload.exists()) {
                upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
                //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
                //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return upload.getAbsolutePath();
    }
    /**
     * 判断对象属性是否为空
     * @param object
     * @return
     */
    public static JSONObject checkObjAllFieldsIsNull(Object object) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if(!f.getName().equals("id") || !f.getName().equals("color")){
                    if (f.get(object)==null||f.get(object).toString().equals("")) {
                        jsonObject.put("result","false");
                        jsonObject.put("message",f.getName()+"为空");
                        jsonObject.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        return jsonObject;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("result","true");
        jsonObject.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return jsonObject;
    }

    /**
     * 下载pdf
     * @param url
     * @param fileName
     * @param response
     * @throws Exception
     */
    public static void downloadPdf(String url, String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        RestTemplate restTemplate = new RestTemplate();

        String html = restTemplate.getForObject(
                URI.create(url),
                String.class
        );
        html=html.replaceAll("##hh##","123");
        Document document = new Document();
        OutputStream out = response.getOutputStream();

        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();

        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();

        // HTML
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register("fzhei.ttf", "Hei");
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, htmlPipeline);

        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        StringReader in = new StringReader(html);
        p.parse(in);
        response.setContentType("application/pdf");

        fileName =new String(fileName.getBytes("GBK"), "ISO8859_1");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".pdf\"");
        document.close();
    }

}
