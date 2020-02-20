package com.hujiang.project.zhgd.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: Consumer01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-24 19:07
 **/
@Repository("restTemplateUtil")
public class RestTemplateUtil {
    @Resource
    private RestTemplate restTemplate;
    /**
     * 提交表单
     * @param o   参数对象
     * @param url  服务地址
     * @return
     * @throws Exception
     */
    public Object PostDownload(Object o,String url)throws Exception{
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-download");
        headers.setContentType(type);
        //如果对象为map
        if(o.getClass().equals(HashMap.class)){
            Map m = (HashMap)o;
            for(Object obj:m.keySet()){
                Object o1 = m.get(obj);
                if(o1!=null){//value 不为空
                    if(o1 instanceof Integer||o1 instanceof String||o1 instanceof Double||o1 instanceof Float||o1 instanceof Long||o1 instanceof Boolean||o1 instanceof Date ||o1 instanceof Byte||o1 instanceof Short){
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    }else{
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if(f.get(o1)!=null){
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        }else{
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(o)!=null){
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(param, headers);
        Object s = restTemplate.postForObject(url, files, Object.class);
        return s;
    }

    /**
     * 提交表单
     * @param o   参数对象
     * @param url  服务地址
     * @return
     * @throws Exception
     */
    public Object Post(Object o,String url)throws Exception{
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded");
        headers.setContentType(type);
        //如果对象为map
        if(o.getClass().equals(HashMap.class)){
            Map m = (HashMap)o;
            for(Object obj:m.keySet()){
                Object o1 = m.get(obj);
                if(o1!=null){//value 不为空
                    if(o1 instanceof Integer||o1 instanceof String||o1 instanceof Double||o1 instanceof Float||o1 instanceof Long||o1 instanceof Boolean||o1 instanceof Date ||o1 instanceof Byte||o1 instanceof Short){
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    }else{
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if(f.get(o1)!=null){
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        }else{
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(o)!=null){
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(param, headers);
        Object s = restTemplate.postForObject(url, files, Object.class);
        return s;
    }

    /**
     * 分页提交
     * @param o   参数对象
     * @param url  服务地址
     * @param page 分页对象
     * @return
     * @throws Exception
     */
    public Object PostPage(Object o, String url, PageDomain page)throws Exception{
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded");
        headers.setContentType(type);
        //如果对象为map
        if(o.getClass().equals(HashMap.class)){
            Map m = (HashMap)o;
            for(Object obj:m.keySet()){
                Object o1 = m.get(obj);
                if(o1!=null){//value 不为空
                    if(o1 instanceof Integer||o1 instanceof String||o1 instanceof Double||o1 instanceof Float||o1 instanceof Long||o1 instanceof Boolean||o1 instanceof Date ||o1 instanceof Byte||o1 instanceof Short){
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    }else{
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if(f.get(o1)!=null){
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        }else{
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(o)!=null){
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }
        //遍历对象属性，并获取值。保存进MultiValueMap
        for (Field f : page.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if(f.get(page)!=null){
                param.add(f.getName(), String.valueOf(f.get(page)));
            }
        }
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(param, headers);
        TableDataInfo s = restTemplate.postForObject(url, files, TableDataInfo.class);
        return AjaxResult.success(s);
    }

    /**
     *  单文件
     * @param o   参数对象
     * @param url  服务地址
     * @param file 文件对象
     * @return
     * @throws Exception
     */
    public Object PostFile(Object o,String url, MultipartFile file) throws Exception {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        File localFile = null;
        if (file != null && !file.isEmpty()) {//文件不为空
            localFile = new File(Util.getPath(), "/" + file.getOriginalFilename());
            file.transferTo(localFile);
            //加载加载绝对路径资源
            FileSystemResource resource = new FileSystemResource(localFile);
            //添加进MultiValueMap
            param.add("file", resource);
        }

        //如果对象为map
        if(o.getClass().equals(HashMap.class)){
            Map m = (HashMap)o;
            for(Object obj:m.keySet()){
                Object o1 = m.get(obj);
                if(o1!=null){//value 不为空
                    if(o1 instanceof Integer||o1 instanceof String||o1 instanceof Double||o1 instanceof Float||o1 instanceof Long||o1 instanceof Boolean||o1 instanceof Date ||o1 instanceof Byte||o1 instanceof Short){
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    }else{
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if(f.get(o1)!=null){
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        }else{
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(o)!=null){
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(param, headers);
        Object s = restTemplate.postForObject(url, files, Object.class);
        //删除临时文件
        if (localFile != null && localFile.exists()) {
            localFile.delete();
        }
        return s;
    }

    /**
     *  多文件
     * @param o   参数对象
     * @param url  服务地址
     * @param files 文件数组
     * @return
     * @throws Exception
     */
    public Object PostFile(Object o,String url,Map<String, MultipartFile>  files) throws Exception {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        List<File> fileList =  new ArrayList<>();
        for(String s:files.keySet()){
            MultipartFile file = files.get(s);
            File localFile = null;
            if (file != null && !file.isEmpty()) {//文件不为空
                localFile = new File(Util.getPath(), "/" + file.getOriginalFilename());
                file.transferTo(localFile);
                //加载加载绝对路径资源
                FileSystemResource resource = new FileSystemResource(localFile);
                //添加进MultiValueMap
                param.add(s, resource);
                //存放临时文件集合
                fileList.add(localFile);
            }
        }


        //如果对象为map
        if(o.getClass().equals(HashMap.class)){
            Map m = (HashMap)o;
            for(Object obj:m.keySet()){
                Object o1 = m.get(obj);
                if(o1!=null){//value 不为空
                    if(o1 instanceof Integer||o1 instanceof String||o1 instanceof Double||o1 instanceof Float||o1 instanceof Long||o1 instanceof Boolean||o1 instanceof Date ||o1 instanceof Byte||o1 instanceof Short){
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    }else{
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if(f.get(o1)!=null){
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        }else{
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(o)!=null){
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }
        for(String s : param.keySet()){
            System.out.println(s);
            System.out.println(param.get(s));
        }

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        Object s = restTemplate.postForObject(url, httpEntity, Object.class);

        for(File localFile:fileList){
            //删除临时文件
            if (localFile != null && localFile.exists()) {
                localFile.delete();
            }
        }

        return s;
    }
    /**
     * 多文件
     *
     * @param o     参数对象
     * @param url   服务地址
     * @param files 文件数组
     * @return
     * @throws Exception
     */
    public Object PostFile(Object o, String url, MultipartFile[] files) throws Exception {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
        headers.setContentType(type);
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            String tempFilePath = Util.getPath() + "/" + file.getOriginalFilename();
            // create local temp file
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(tempFilePath));
            File localFile = new File(Util.getPath(), "/" + file.getOriginalFilename());
            file.transferTo(localFile);
            fileList.add(localFile);
            // MultipartFile 直接转 FileSystemResource是不行的
            FileSystemResource resource = new FileSystemResource(localFile); // 把临时文件变成FileSystemResource
            param.add("files", resource);
        }

        //如果对象为map
        if (o.getClass().equals(HashMap.class)) {
            Map m = (HashMap) o;
            for (Object obj : m.keySet()) {
                Object o1 = m.get(obj);
                if (o1 != null) {//value 不为空
                    if (o1 instanceof Integer || o1 instanceof String || o1 instanceof Double || o1 instanceof Float || o1 instanceof Long || o1 instanceof Boolean || o1 instanceof Date || o1 instanceof Byte || o1 instanceof Short) {
                        param.add(String.valueOf(obj), String.valueOf(o1));
                    } else {
                        //遍历对象属性，并获取值。保存进MultiValueMap
                        for (Field f : o1.getClass().getDeclaredFields()) {
                            f.setAccessible(true);
                            if (f.get(o1) != null) {
                                param.add(f.getName(), String.valueOf(f.get(o1)));
                            }
                        }
                    }
                }
            }
        } else {
            //遍历对象属性，并获取值。保存进MultiValueMap
            for (Field f : o.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(o) != null) {
                    param.add(f.getName(), String.valueOf(f.get(o)));
                }
            }
        }
        for (String s : param.keySet()) {
            System.out.println(s);
            System.out.println(param.get(s));
        }
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        Object s = restTemplate.postForObject(url, httpEntity, Object.class);

        for (File localFile : fileList) {
            //删除临时文件
            if (localFile != null && localFile.exists()) {
                localFile.delete();
            }
        }
        return s;
    }
}
