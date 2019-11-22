package com.hujiang.project.zhgd.hjProjectImage.api;

import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.exception.BusinessException;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.ConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectImage.service.IHjProjectImageService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * @program: Provider01
 * @description: 项目接口
 * @author: Mr.LiuYong
 * @create: 2019-05-14 17:37
 **/
@RestController
@RequestMapping(value = "/provider/projectImage", method = RequestMethod.POST)
public class ProjectImageApi extends BaseController {

    @Autowired
    private IHjProjectImageService hjProjectImageService;

}
