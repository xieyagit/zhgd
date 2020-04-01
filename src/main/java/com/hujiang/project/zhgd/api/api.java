package com.hujiang.project.zhgd.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.api.controller.ApiDustEmissionController;
import com.hujiang.project.api.controller.ApiElectricityBoxController;
import com.hujiang.project.api.controller.ApiLZController;
import com.hujiang.project.common.FileController;
import com.hujiang.project.hq.api.HqFaceApi;
import com.hujiang.project.hs.hsFaceAPi.HsFaceApi;
import com.hujiang.project.hs.hsFaceAPi.HsFaceRecordApi;
import com.hujiang.project.ys.ysRecordApi.YsRecordApi;
import com.hujiang.project.zhgd.deye.PcApi;
import com.hujiang.project.zhgd.deye.PcElevatorApi;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.api.DustEmissionThresholdValueAPI;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.hjAge.controller.HjAgeApi;
import com.hujiang.project.zhgd.hjArea.api.AreaApi;
import com.hujiang.project.zhgd.hjAttendanceDevice.api.AttendanceDeviceApi;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceRecord.api.AttendanceRecordApi;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param;
import com.hujiang.project.zhgd.hjAttendanceRecord.pcApi.AttendanceRecordPcApi;
import com.hujiang.project.zhgd.hjBlacklist.api.HjBlacklistApi;
import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.pcApi.PcCompanyLibraryApi;
import com.hujiang.project.zhgd.hjConstructionCompany.api.ConstructionCompanyApi;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionProject.api.OptionsConstructionApi;
import com.hujiang.project.zhgd.hjDeeppit.api.DeeppitExternalApi;
import com.hujiang.project.zhgd.hjDeeppit.api.HjDeeppitApi;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDictionaries.api.DictionariesApi;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjEpidemicSituation.controller.HjEpidemicSituationApi;
import com.hujiang.project.zhgd.hjFile.api.FileApi;
import com.hujiang.project.zhgd.hjFolder.api.FolderApi;
import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;
import com.hujiang.project.zhgd.hjInformation.api.HjInformationApi;
import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import com.hujiang.project.zhgd.hjLogging.api.HjLoggingApi;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjMenu.api.HjMenuApi;
import com.hujiang.project.zhgd.hjMenu.domain.HjMenu;
import com.hujiang.project.zhgd.hjProject.api.ProjectApi;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProjectImage.api.ProjectImageApi;
import com.hujiang.project.zhgd.hjProjectImage.domain.HjProjectImage;
import com.hujiang.project.zhgd.hjProjectWorkers.api.PC_ProjectWorkersApi;
import com.hujiang.project.zhgd.hjProjectWorkers.api.ProjectWorkersApi;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.*;
import com.hujiang.project.zhgd.hjReport.api.ReportApi;
import com.hujiang.project.zhgd.hjReport.domain.HjReport;
import com.hujiang.project.zhgd.hjReport.domain.HjReportPc;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.api.AppSafety;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.api.OptionsSafetyApi;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.api.PcSafety;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyAbarbeitung;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyNoPass;
import com.hujiang.project.zhgd.hjSafetyCommission.api.AppCommission;
import com.hujiang.project.zhgd.hjStatistics.pcApi.PcIndexApi;
import com.hujiang.project.zhgd.hjSynchronizationInformation.api.PC_HjSynchronizationInformationApi;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSystemPrivileges.api.APP_SystemPrivilegesApi;
import com.hujiang.project.zhgd.hjSystemPrivileges.api.PC_SystemPrivilegesApi;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.SystemRoleParam;
import com.hujiang.project.zhgd.hjSystemRole.api.Pc_SystemRoleApi;
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.domain.RoleParam;
import com.hujiang.project.zhgd.hjSystemUser.api.APP_SystrmUserApi;
import com.hujiang.project.zhgd.hjSystemUser.api.PC_SystrmUserApi;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.domain.PasswordParam;
import com.hujiang.project.zhgd.hjSystemUser.domain.SystemUserParam;
import com.hujiang.project.zhgd.hjSystemUser.domain.UserParam;
import com.hujiang.project.zhgd.hjTeam.api.APP_TeamApi;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.pcApi.PcTeamApi;
import com.hujiang.project.zhgd.hjTemperature.api.HjTemperatureApi;
import com.hujiang.project.zhgd.hjWorkersInformation.api.HjWorkersInformationApi;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformation;
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformationPc;
import com.hujiang.project.zhgd.hjZhgdDriver.api.DriverApi;
import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleAPI;
import com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleAppApi;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.hjghformwork.api.FormWorkExternalApi;
import com.hujiang.project.zhgd.hjghformwork.api.HjGhformworktApi;
import com.hujiang.project.zhgd.inOut.InOutKanBan;
import com.hujiang.project.zhgd.kqbb.KqbbAPI;
import com.hujiang.project.zhgd.kqbb.domain.BG;
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;

import com.hujiang.project.zhgd.options.OptionsPushApi;
import com.hujiang.project.zhgd.sbAccountTalkback.api.SbAccountTalkbackApi;
import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import com.hujiang.project.zhgd.sbApiFaceAttendance.api.FaceAttendanceAPI;
import com.hujiang.project.zhgd.sbApiFaceAttendance.domain.SbApiFaceAttendance;
import com.hujiang.project.zhgd.sbArea.api.OptionsLocationApi;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbCraneAddparams.api.OptionsCraneApi;
import com.hujiang.project.zhgd.sbCraneAddrecord.api.AppCraneAddRecordApi;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCurrentTemperature.api.CurrentTemperatureApi;
import com.hujiang.project.zhgd.sbCurrentTemperature.api.ElectricityBoxExternalApi;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbDustEmission.api.AppDustEmissionApi;
import com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbElevatorAddparams.api.OptionsElevatorApi;
import com.hujiang.project.zhgd.sbElevatorAddrecord.api.AppElevatorAddRecordApi;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbEquipmentRecord.api.EquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentWarning.api.PcEquipmentWarning;
import com.hujiang.project.zhgd.sbExcessiveDust.api.AppDust;
import com.hujiang.project.zhgd.sbGroupTalkback.api.SbGroupTalkbackApi;
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTitle.api.GroupTitleApi;
import com.hujiang.project.zhgd.sbGroupTitle.domain.SbGroupTitle;
import com.hujiang.project.zhgd.sbHire.api.SbHireApi;
import com.hujiang.project.zhgd.sbHire.api.SbHireAppApi;
import com.hujiang.project.zhgd.sbManufacturer.api.ManufacturerPC;
import com.hujiang.project.zhgd.sbManufacturer.domain.SbManufacturer;
import com.hujiang.project.zhgd.sbProjectDustEmission.api.AppProjectDustEmissionApi;
import com.hujiang.project.zhgd.sbProjectDustEmission.api.PcProjectDustEmissionApi;
import com.hujiang.project.zhgd.sbProjectDustEmission.api.ShanDongDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectElectricityBox.api.AppProjectElectricityBoxApi;
import com.hujiang.project.zhgd.sbProjectElectricityBox.api.ProjectElectricityBoxApi;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectVideo.api.ProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideoArea.api.AppProjectVideoAreaApi;
import com.hujiang.project.zhgd.sbProjectVideoArea.api.ProjectVideoAreaApi;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.ProjectVideoJT;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbJTArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.Video;
import com.hujiang.project.zhgd.sbProjectVideoPreset.api.ProjectVideoPresetApi;
import com.hujiang.project.zhgd.sbProjectVideoPreset.domain.SbProjectVideoPreset;
import com.hujiang.project.zhgd.sbUnloaderRegistration.api.AppUnloader;
import com.hujiang.project.zhgd.sbUnloaderRegistration.api.PcUnloader;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderRealtime;
import com.hujiang.project.zhgd.sbVersion.api.VersionApi;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.zhNode.api.NodeApi;
import com.hujiang.project.zhgd.zhNode.domain.*;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hujiang.framework.web.domain.AjaxResult.error;

@RestController
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class api {

    @Autowired
    private PcEquipmentWarning pcEquipmentWarning;
    @Autowired
    private com.hujiang.project.cay.cay cay;
    @Autowired
    private FormWorkExternalApi formWorkExternalApi;
    @Autowired
    private DeeppitExternalApi deeppitExternalApi;
    @Autowired
    private InOutKanBan inOutKanBan;
    @Autowired
    private ElectricityBoxExternalApi electricityBoxExternalApi;
    @Autowired
    private PcUnloader pcUnloader;
    @Autowired
    private AppUnloader appUnloader;
    @Autowired
    private OptionsLocationApi optionsLocationApi;
    @Autowired
    private OptionsConstructionApi optionsConstructionApi;
    @Autowired
    private APP_SystrmUserApi appSystrmUserApi;
    @Autowired
    private OptionsCraneApi optionsCraneApi;
    @Autowired
    private OptionsElevatorApi optionsElevatorApi;
    @Autowired
    private OptionsPushApi optionsPushApi;
    @Autowired
    private OptionsSafetyApi optionsSafetyApi;
    @Autowired
    private DriverApi driverApi;
    @Autowired
    private ManufacturerPC manufacturerPC;
    @Autowired
    private VehicleAPI vehicleAPI;
    @Autowired
    private VersionApi versionApi;
    @Autowired
    private AppSafety appSafety;
    @Autowired
    private AppDust appDust;
    @Autowired
    private AppCommission appCommission;
    @Autowired
    private PcSafety pcSafety;
    @Autowired
    private ProjectWorkersApi projectWorkersApi;
    @Autowired
    private SbHireApi sbHireApi;
    @Autowired
    private EquipmentRecord equipmentRecord;
    @Autowired
    private ConstructionCompanyApi constructionCompanyApi;
    @Autowired
    private PcTeamApi pcTeamApi;
    @Autowired
    private ShanDongDustEmission shanDongDustEmission;
    @Autowired
    private PC_ProjectWorkersApi pcProjectWorkersApi;
    @Autowired
    private DictionariesApi dictionariesApi;
    @Autowired
    private ApiLZController apiLZController;
    @Autowired
    private ApiDustEmissionController apiDustEmissionController;
    @Autowired
    private ApiElectricityBoxController apiElectricityBoxController;
    @Autowired
    private ReportApi reportApi;
    @Autowired
    private HjWorkersInformationApi hjWorkersInformationApi;
    @Autowired
    private HjInformationApi hjInformationApi;
    @Autowired
    private HjMenuApi hjMenuApi;
    @Autowired
    private SbHireAppApi sbHireAppApi;
    @Autowired
    private VehicleAppApi vehicleAppApi;
    @Autowired
    private DustEmissionThresholdValueAPI dustEmissionThresholdValueAPI;
    @Autowired
    private PC_SystrmUserApi pc_systrmUserApi;
    @Autowired
    private PC_SystemPrivilegesApi pc_systemPrivilegesApi;
    @Autowired
    private APP_SystemPrivilegesApi app_systemPrivilegesApi;
    @Autowired
    private AttendanceRecordApi attendanceRecordApi;
    @Autowired
    private AreaApi areaApi;
    @Autowired
    private PcCompanyLibraryApi pcCompanyLibraryApi;
    @Autowired
    private APP_TeamApi app_teamApi;
    @Autowired
    private PC_HjSynchronizationInformationApi pc_hjSynchronizationInformationApi;
    @Autowired
    private Pc_SystemRoleApi pc_systemRoleApi;
    @Autowired
    private AttendanceRecordPcApi attendanceRecordPcApi;
    @Autowired
    private PcIndexApi pcIndexApi;
    @Autowired
    private PcProjectDustEmissionApi pcProjectDustEmissionApi;
    @Autowired
    private AppProjectDustEmissionApi appProjectDustEmissionApi;
    @Autowired
    private DustEmissionApi dustEmissionApi;
    @Autowired
    private ProjectVideoAreaApi projectVideoAreaApi;
    @Autowired
    private AppProjectVideoAreaApi appProjectVideoAreaApi;
    @Autowired
    private ProjectVideo projectVideo;
    @Autowired
    private CurrentTemperatureApi currentTemperatureApi;
    @Autowired
    private ProjectElectricityBoxApi projectElectricityBoxApi;
    @Autowired
    private AppProjectElectricityBoxApi appProjectElectricityBoxApi;
    @Autowired
    private HjBlacklistApi hjBlacklistApi;
    @Autowired
    private HjLoggingApi hjLoggingApi;
    @Autowired
    private AppDustEmissionApi appDustEmissionApi;
    @Autowired
    private com.hujiang.project.zhgd.sbCurrentTemperature.api.appCurrentTemperatureApi appCurrentTemperatureApi;
    @Autowired
    private FolderApi folderApi;
    @Autowired
    private FileApi fileApi;
    @Autowired
    private FileController filec;
    @Autowired
    private FaceAttendanceAPI faceAttendanceAPI;
    @Autowired
    private PcApi pcApi;
    @Autowired
    private PcElevatorApi pcElevatorApi;
    @Autowired
    private KqbbAPI kqbbAPI;
    @Autowired
    private AppElevatorAddRecordApi appElevatorAddRecordApi;
    @Autowired
    private AppCraneAddRecordApi appCraneAddRecordApi;
    @Autowired
    private ProjectApi projectApi;
    @Autowired
    private NodeApi nodeApi;
    @Autowired
    private HqFaceApi hqFaceApi;
    @Autowired
    private HsFaceApi hsFaceApi;
    @Autowired
    private HsFaceRecordApi hsFaceRecordApi;
    @Autowired
    private AttendanceDeviceApi attendanceDeviceApi;
    @Autowired
    private HjGhformworktApi hjGhformworktApi;
    @Autowired
    private HjDeeppitApi hjDeeppitApi;
    @Autowired
    private YsRecordApi ysRecordApi;
    @Autowired
    private ProjectImageApi projectImageApi;
    @Autowired
    private SbGroupTalkbackApi sbGroupTalkbackApi;
    @Autowired
    private SbAccountTalkbackApi sbAccountTalkbackApi;
    @Autowired
    private ProjectVideoPresetApi projectVideoPresetApi;
    @Autowired
    private GroupTitleApi groupTitleApi;
    @Autowired
    private com.hujiang.project.zhgd.sbGroup.api.SbGroupApi sbGroupApi;
    @Autowired
    private HjAgeApi hjAgeApi;
    @Autowired
    private HjEpidemicSituationApi hjEpidemicSituationApi;
    @Autowired
    private HjTemperatureApi hjTemperatureApi;

    @PostMapping("/pcEquipmentWarning/warningCount")
    public JSONObject warningCount(@RequestParam(value = "projectId") Integer projectId){
        return pcEquipmentWarning.warningCount(projectId);
    }
    @PostMapping("/pcEquipmentWarning/getWarningList")
    public JSONObject getWarningList(@RequestParam(value = "userName",required = false)String userName,
                                     @RequestParam(value = "userId",required = false)Integer userId,
                                     @RequestParam(value = "areaName",required = false)String areaName,
                                     @RequestParam(value = "areaId",required =  false)Integer areaId,
                                     @RequestParam(value = "projectId",required = false)Integer projectId,
                                     @RequestParam(value = "warningType",required = false)Integer warningType,
                                     @RequestParam(value = "startTime",required = false)String startTime,
                                     @RequestParam(value = "endTime",required = false)String endTime,
                                     PageDomain pageDomain){

        return pcEquipmentWarning.getWarningList(userName,userId,areaName,areaId,projectId,startTime,endTime,warningType,pageDomain);
    }

    @RequestMapping(value = "/cay",method = RequestMethod.POST)
    public JSONObject cay(Integer projectId)throws IOException, URISyntaxException {
        return cay.cay(projectId);
    }

    @RequestMapping(value = "/caySbDelete")
    public JSONObject delete(String sn,String xmid,String devType,String devCcrq,String subId)throws IOException, URISyntaxException {
        return cay.delete(sn,xmid,devType,devCcrq,subId);
    }

    @PostMapping(value ="/formWorkExternalApi/addFormWorkDisplay")
    public Map<String,Object> addFormWorkDisplay(@RequestBody String json){
        return formWorkExternalApi.addFormWorkDisplay(json);
    }
    @PostMapping(value ="/formWorkExternalApi/addFormWorkFactor")
    public Map<String,Object> addFormWorkFactor(@RequestBody String json){
        return formWorkExternalApi.addFormWorkFactor(json);
    }
    @PostMapping(value = "/formWorkExternalApi/addFormWorkGroup")
    public Map<String,Object> addFormWorkGroup(@RequestBody String json) {
        return formWorkExternalApi.addFormWorkGroup(json);
    }
    @PostMapping(value = "/formWorkExternalApi/addFormWorkData")
    public Map<String,Object> addFormWorkData(@RequestBody String json){
        return formWorkExternalApi.addFormWorkData(json);
    }
    @PostMapping(value = "/formWorkExternalApi/addFormWorkAlarmData")
    public Map<String,Object> addFormWorkAlarmData(@RequestBody String json){
        return formWorkExternalApi.addFormWorkAlarmData(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addDeeppitData")
    public  Map<String,Object> addDeeppitData(@RequestBody String json){
        return deeppitExternalApi.addDeeppitData(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addDeeppitAlarmData")
    public Map<String,Object> addDeeppitAlarmData(@RequestBody String json){
        return deeppitExternalApi.addDeeppitAlarmData(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addStructures")
    public Map<String,Object> addStructures(@RequestBody String json){
        return deeppitExternalApi.addStructures(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addDisplay")
    public Map<String,Object> addDisplay(@RequestBody String json){
        return deeppitExternalApi.addDisplay(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addGroup")
    public Map<String,Object> addGroup(@RequestBody String json){
        return deeppitExternalApi.addGroup(json);
    }
    @PostMapping(value = "/deeppitExternalApi/addFactor")
    public  Map<String,Object> addFactor(@RequestBody String json){
        return deeppitExternalApi.addFactor(json);
    }
    @PostMapping("/electricityBoxExternal/addElectricityBox")
    public Map<String,Object> addElectricityBox(@RequestBody String json){
        return electricityBoxExternalApi.addElectricityBox(json);
    }
    @PostMapping(value = "/unloaderPcApi/getUnloaderList")
    public JSONObject getUnloaderList(@RequestParam("projectId")Integer projectId){
        return pcUnloader.getUnloaderList(projectId);
    }
    @PostMapping(value = "/unloaderPcApi/getSbUnloaderRealtimeList")
    public JSONObject getSbUnloaderRealtimeList(@RequestParam(value = "projectId")Integer projectId,
                                                @RequestParam(value = "deviceId") String deviceId){
        return pcUnloader.getSbUnloaderRealtimeList(projectId, deviceId);
    }
    @PostMapping(value = "/unloaderPcApi/getRealtimeHistory")
    public JSONObject getRealtimeHistory(@RequestParam("projectId")Integer projectId,
                                         @RequestParam(value = "deviceId") String deviceId,
                                         @RequestParam(value = "time",required = false)String time,
                                         @RequestParam(value = "endTime", required = false) String endTime,
                                         @RequestParam(value = "alarmType")Integer alarmType, PageDomain pageDomain) {
        return pcUnloader.getRealtimeHistory(projectId,deviceId,time,endTime,alarmType,pageDomain);
    }
    @PostMapping(value = "/unloaderPcApi/getSbUnloaderHistory")
    public JSONObject getSbUnloaderHistory(@RequestParam(value = "projectId")Integer projectId,
                                           @RequestParam(value = "deviceId") String deviceId,
                                           @RequestParam(value = "time",required = false)String time,
                                           @RequestParam(value = "endTime",required = false)String endTime,
                                           PageDomain pageDomain){
        return pcUnloader.getSbUnloaderHistory(projectId,deviceId,time,endTime,pageDomain);
    }
    @GetMapping("/unloaderPcApi/exportUnloader")
    public void exportUnloader(@RequestParam(value = "ids",required = false)String[] ids,
                               @RequestParam(value = "judge")int judge,
                               @RequestParam(value = "startTime",required = false)String startTime,
                               @RequestParam(value = "endTime",required = false)String endTime,
                               @RequestParam(value = "deviceId",required = false)String deviceId,
                               HttpServletResponse response)throws Exception {
        String title = null;
        if(judge==5){
            ExcelUtil<ExportUnloaderRealtime> util = new ExcelUtil<ExportUnloaderRealtime>(ExportUnloaderRealtime.class);
            List<ExportUnloaderRealtime> exportUnloaderRealtimeList = pcUnloader.exportUnloaderRealtime(ids,startTime,endTime,deviceId);

            title = "卸料实时历史记录";
            //生成Excel
            AjaxResult a = util.exportExcel(exportUnloaderRealtimeList, title);

            //设置下载文件名
            String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
            File file = new File(Utils.getPath(), (String) a.get("msg"));
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream();) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                IOUtils.copy(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(file.exists()){
                file.delete();
            }
        }else {
            ExcelUtil<ExportUnloaderAlarmtime> util = new ExcelUtil<ExportUnloaderAlarmtime>(ExportUnloaderAlarmtime.class);
            List<ExportUnloaderAlarmtime> exportUnloaderAlarmtimeList = pcUnloader.exportUnloaderAlarmtime(ids,startTime,endTime,deviceId,judge);
            if(judge==1){
                title = "卸料载重报警历史记录";
            }else if (judge==2){
                title = "卸料倾角X报警历史记录";
            }else if (judge==3){
                title = "卸料倾角Y报警历史记录";
            }else if (judge==4){
                title = "卸料电池电量报警历史记录";
            }
            //生成Excel
            AjaxResult a = util.exportExcel(exportUnloaderAlarmtimeList, title);
            //设置下载文件名
            String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
            File file = new File(Utils.getPath(), (String) a.get("msg"));
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream();) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                IOUtils.copy(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(file.exists()){
                file.delete();
            }
        }



    }

    @PostMapping(value = "/unloaderAppApi/getSbUnloaderAlarmtimeList")
    public JSONObject getSbUnloaderAlarmtimeList(@RequestParam("projectId")Integer projectId,
                                                 @RequestParam(value = "deviceId") String deviceId,
                                                 @RequestParam(value = "time",required = false)String time,
                                                 @RequestParam(value="endTime",required = false)String endTime,
                                                 PageDomain pageDomain){
        return appUnloader.getSbUnloaderAlarmtimeList(projectId,deviceId,time,endTime,pageDomain);
    }
    @PostMapping(value = "/unloaderAppApi/getSbUnloaderHistory")
    public JSONObject getAppSbUnloaderHistory(@RequestParam(value = "projectId")Integer projectId,
                                           @RequestParam(value = "deviceId",required = false) String deviceId,
                                           @RequestParam(value = "time",required = false)String time,
                                           @RequestParam(value = "endTime",required = false)String endTime,
                                           PageDomain pageDomain){
        return appUnloader.getSbUnloaderHistory(projectId,deviceId,time,endTime,pageDomain);
    }
    @PostMapping(value = "/unloaderAppApi/getSbUnloaderRealtimeList")
    public JSONObject getAppSbUnloaderRealtimeList(@RequestParam(value = "projectId")Integer projectId,
                                                @RequestParam(value = "deviceId") String deviceId){
        return appUnloader.getSbUnloaderRealtimeList(projectId,deviceId);
    }
    @PostMapping(value = "/unloaderAppApi/getUnloader")
    public JSONObject getUnloader(@RequestParam("projectId")Integer projectId){
        return appUnloader.getUnloader(projectId);
    }
    //30
    @PostMapping("/OptionsLocationApi/getAreaList")
    public JSONObject getAreaList(@RequestParam("projectId")Integer projectId){
        return optionsLocationApi.getAreaList(projectId);
    }
    @PostMapping("/OptionsLocationApi/updateArea")
    public JSONObject updateArea(@RequestParam("areaId")Integer areaId,
                                 @RequestParam("areaName")String areaName,
                                 @RequestParam("areaAddress")String areaAddress,
                                 @RequestParam("constructionId")Integer constructionId) {
        return optionsLocationApi.updateArea(areaId, areaName, areaAddress, constructionId);
    }
    @PostMapping("/OptionsLocationApi/addArea")
    public JSONObject addArea(@RequestParam("areaName")String areaName,
                              @RequestParam("areaAddress")String areaAddress,
                              @RequestParam("constructionId")Integer constructionId,
                              @RequestParam("areaXloc")Double areaXloc,
                              @RequestParam("areaYloc")Double areaYloc,
                              @RequestParam("radius")Double radius,
                              @RequestParam("projectId")Integer projectId){
        return optionsLocationApi.addArea(areaName, areaAddress, constructionId, areaXloc, areaYloc, radius, projectId);
    }
    @PostMapping("/OptionsLocationApi/deleteArea")
    public JSONObject deleteArea(@RequestParam("areaId")Integer areaId){
        return optionsLocationApi.deleteArea(areaId);
    }
    @PostMapping("/OptionsLocationApi/getAreaUserList")
    public JSONObject getUserList(@RequestParam(value = "areaId",required = false) Integer areaId,
                                  @RequestParam(value = "filed",required = false)String filed,
                                  @RequestParam(value = "userName",required = false)String userName,
                                  @RequestParam(value = "projectId")Integer projectId, PageDomain pageDomain){
        return optionsLocationApi.getUserList(areaId,filed, projectId,pageDomain);
    }
    @PostMapping("/OptionsLocationApi/getAreaById")
    public JSONObject getAreaById(@RequestParam("areaId")Integer areaId){
        return optionsLocationApi.getAreaById(areaId);
    }
    @PostMapping("/OptionsLocationApi/updateAreaUser")
    public JSONObject updateAreaUser(@RequestParam(value = "areaId") Integer areaId,
                                     @RequestParam(value = "userName")String userName,
                                     @RequestParam(value = "userPhone",required = false)String userPhone,
                                     @RequestParam(value = "imei")String imei,
                                     @RequestParam(value = "userId")Integer userId){
        return optionsLocationApi.updateAreaUser(areaId, userName, userPhone, imei, userId);
    }
    @PostMapping("/OptionsLocationApi/deleteAreaUser")
    public JSONObject deleteLocationAreaUser(@RequestParam(value = "userId") Integer userId,
                                             @RequestParam(value = "areaId")Integer areaId){
        return optionsLocationApi.deleteAreaUser(userId,areaId);
    }
    @PostMapping("/OptionsLocationApi/addAreaUser")
    public JSONObject addAreaUser(@RequestParam(value = "areaId")Integer areaId,
                                  @RequestParam(value = "userName")String userName,
                                  @RequestParam(value = "userPhone",required = false)String userPhone,
                                  @RequestParam(value = "imei")String imei){
        return optionsLocationApi.addAreaUser(areaId, userName, userPhone, imei);
    }
    @PostMapping("/OptionsConstructionApi/getConstructionList")
    public JSONObject getConstructionList(@RequestParam("projectId") Integer projectId, PageDomain pageDomain)
    {
        return optionsConstructionApi.getConstructionList(projectId,pageDomain);
    }
    @PostMapping("/system/application/updateUserById")
    public JSONObject updateUserById(@RequestParam(value = "userName",required = false)String userName,
                                     @RequestParam(value = "userPhone",required = false)String userPhone,
                                     @RequestParam(value = "userId",required = false)Integer userId){
        return appSystrmUserApi.updateUserById(userName,  userPhone, userId);
    }
    @PostMapping("/OptionsCraneApi/getCraneList")
    public JSONObject getCraneList(@RequestParam("projectId")Integer projectId){
        return optionsCraneApi.getCraneList(projectId);
    }
    @PostMapping("/OptionsCraneApi/updateCrane")
    public JSONObject updateCrane(@RequestParam("id")String id,
                                  @RequestParam("craneName")String craneName,
                                  @RequestParam("hxzId")String hxzId,
                                  @RequestParam("serialNum")String serialNum,//广东省统一安装告 知编号（产权备案 编号）
                                  @RequestParam("tcMaxScope")String tcMaxScope,//最大幅度（M）
                                  @RequestParam("tcMaxHeight")String tcMaxHeight,//最大高度（M)
                                  @RequestParam("tcLoadCapacity")String tcLoadCapacity,//最大载重（kg）
                                  @RequestParam("tcLoadMoment")String tcLoadMoment,//额定起重力矩（N·m）
                                  @RequestParam(value = "jdbh" ,required = false)String jdbh,
                                  @RequestParam(value = "xmid" ,required = false)String xmid,
                                  @RequestParam(value = "subId" ,required = false)String subId,
                                  @RequestParam("scznl")String scznl,
                                  @RequestParam("manufacturerId")String manufacturerId,
                                  @RequestParam("installCompany")String installCompany //设备安装单位
    ){
        return optionsCraneApi.updateCrane(Integer.parseInt(id), craneName, hxzId, serialNum, tcMaxScope, tcMaxHeight, tcLoadCapacity, tcLoadMoment, jdbh, xmid, subId, scznl, manufacturerId, installCompany);
    }
    @PostMapping("/OptionsCraneApi/deleteCrane")
    public JSONObject deleteCrane(@RequestParam("id")Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {
        return optionsCraneApi.deleteCrane(id,devCcrq);
    }
    @PostMapping("/OptionsCraneApi/insertCrane")
    public JSONObject insertCrane(@RequestParam(value = "craneName")String craneName,
                                  @RequestParam(value = "hxzId")String hxzId,
                                  @RequestParam(value = "projectId")Integer projectId,
                                  @RequestParam(value = "serialNum")String serialNum,//广东省统一安装告 知编号（产权备案 编号）
                                  @RequestParam(value = "tcMaxScope")String tcMaxScope,//最大幅度（M）
                                  @RequestParam(value = "tcMaxHeight")String tcMaxHeight,//最大高度（M)
                                  @RequestParam(value = "tcLoadCapacity")String tcLoadCapacity,//最大载重（kg）
                                  @RequestParam(value = "tcLoadMoment")String tcLoadMoment,//额定起重力矩（N·m）
                                  @RequestParam(value = "jdbh" ,required = false)String jdbh,
                                  @RequestParam(value = "xmid" ,required = false)String xmid,
                                  @RequestParam(value = "subId" ,required = false)String subId,
                                  @RequestParam(value = "scznl")String scznl,
                                  @RequestParam(value = "manufacturerId")String manufacturerId,
                                  @RequestParam(value = "installCompany")String installCompany //设备安装单位
    )throws IOException, URISyntaxException{
        return optionsCraneApi.insertCrane(craneName, hxzId, projectId, serialNum, tcMaxScope, tcMaxHeight, tcLoadCapacity, tcLoadMoment, jdbh, xmid, subId, scznl, manufacturerId,installCompany);
    }
    @PostMapping("/OptionsCraneApi/getCraneUserList")
    public JSONObject getCraneUserList(@RequestParam("projectId")Integer projectId,
                                       @RequestParam(value = "filed",required = false)String filed){
        return optionsCraneApi.getCraneUserList(projectId,filed);
    }
    @PostMapping("/OptionsElevatorApi/getElevatorList")
    public JSONObject getElevatorList(@RequestParam("projectId")Integer projectId){
        return optionsElevatorApi.getElevatorList(projectId);
    }
    @PostMapping("/OptionsElevatorApi/updateElevator")
    public JSONObject updateElevator(SbElevatorBinding optionsElevator){
        return optionsElevatorApi.updateElevator(optionsElevator);
    }
    @PostMapping("/OptionsElevatorApi/deleteElevator")
    public JSONObject deleteElevator(@RequestParam("id")Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {
        return optionsElevatorApi.deleteElevator(id,devCcrq);
    }
    @PostMapping("/OptionsElevatorApi/insertElevator")
    public JSONObject insertElevator(SbElevatorBinding optionsElevator) throws IOException, URISyntaxException{
        return optionsElevatorApi.insertElevator(optionsElevator);
    }
    @PostMapping("/OptionsElevatorApi/getElevatorUserList")
    public JSONObject getElevatorUserList(@RequestParam("projectId")Integer projectId,
                                          @RequestParam(value = "filed",required = false)String filed){
        return optionsElevatorApi.getElevatorUserList(projectId,filed);
    }
    @PostMapping(value = "/OptionsPushApi/optionsPush")
    public JSONObject optionsPush(@RequestParam(value = "onOff")Integer onOff,
                                  @RequestParam(value = "userId")Integer userId,
                                  @RequestParam(value = "privilegesId")Integer privilegesId) {
        return optionsPushApi.optionsPush(onOff,userId,privilegesId);
    }
    @PostMapping("/optionsSafetyApi/getOptionsList")
    public JSONObject getOptionsList(@RequestParam(value = "projectId")Integer projectId){
        return optionsSafetyApi.getOptionsList(projectId);
    }
    @PostMapping("/optionsSafetyApi/getUserList")
    public JSONObject getUserList(@RequestParam(value = "projectId")Integer projectId){
        return optionsSafetyApi.getUserList(projectId);
    }
    @PostMapping("/optionsSafetyApi/addArea")
    public JSONObject addArea(@RequestParam("areaName")String areaName,
                              @RequestParam("address")String address,
                              @RequestParam("constructionId")Integer constructionId){

        return optionsSafetyApi.addArea(areaName,address,constructionId);
    }
    @PostMapping("/optionsSafetyApi/updateArea")
    public JSONObject updateAreaSafety(@RequestParam("areaId")Integer areaId,
                                 @RequestParam("areaName")String areaName,
                                 @RequestParam("address")String address,
                                 @RequestParam("constructionId")Integer constructionId){
        return optionsSafetyApi.updateArea(areaId,areaName,address,constructionId);
    }
    @PostMapping("/optionsSafetyApi/addAreaUser")
    public JSONObject addAreaUser(@RequestParam("userId")Integer userId,
                                  @RequestParam("areaId")Integer areaId){
        return optionsSafetyApi.addAreaUser(userId,areaId);
    }
    @PostMapping("/optionsSafetyApi/deleteAreaUser")
    public JSONObject deleteAreaUser(@RequestParam("userId")Integer userId,
                                     @RequestParam("areaId")Integer areaId){
        return optionsSafetyApi.deleteAreaUser(userId,areaId);
    }
    @PostMapping("/optionsSafetyApi/deleteArea")
    public JSONObject deleteAreaSafety(@RequestParam("areaId")Integer areaId){
        return optionsSafetyApi.deleteArea(areaId);
    }
    @PostMapping(value = "/driver/insertDriver")
    @ResponseBody
    public JSONObject add(String driver, String vehicle, Integer projectId){
        return driverApi.add(driver, vehicle, projectId.toString());
    }
    @PostMapping(value = "/driver/selectAll")
    @ResponseBody
    public JSONObject all(HjZhgdDriver hjZhgdDriver, PageDomain pageDomain){
        return driverApi.all(hjZhgdDriver,pageDomain);
    }
    @PostMapping(value = "/driver/delDriver")
    @ResponseBody
    public JSONObject del(Integer id){
        return driverApi.del(id);
    }
    @PostMapping(value = "/driver/updateDriver")
    @ResponseBody
    public JSONObject uod(HjZhgdDriver hjZhgdDriver){
        return driverApi.uod(hjZhgdDriver);
    }
    @RequestMapping("/manufacturer/list")
    public JSONObject all(SbManufacturer sbManufacturer){
        return manufacturerPC.all(sbManufacturer);
    }
    @PostMapping(value = "/parkings/vehicleSnAdd")
    public JSONObject vehicleSnAdd(HjZhgdPkcount hjZhgdPkcount){
        return vehicleAPI.add(hjZhgdPkcount);
    }
    @PostMapping(value = "/parkings/vehicleSnDel")
    public JSONObject vehicleSnDel(Integer id){
        return vehicleAPI.del(id);
    }
    @PostMapping(value = "/parkings/vehicleSnUpd")
    public JSONObject vehicleSnUpd(HjZhgdPkcount hjZhgdPkcount){
        return vehicleAPI.upd(hjZhgdPkcount);
    }
    @PostMapping(value = "/parkings/carUpd")
    public JSONObject carUpd(String deptId,Integer pkcount){
        return vehicleAPI.carUpd(deptId,pkcount);
    }
    @PostMapping("/versionApi/getVersion")
    public JSONObject getVersion(){
        return versionApi.getVersion();
    }
    @PostMapping(value = "/safetyAppApi/updateAlias")
    public JSONObject updateAlias(@RequestParam(value = "userId")int userId,
                                  @RequestParam(value = "userAlias")String alias){
        return appSafety.updateAlias(userId,alias);
    }
    @PostMapping("/safetyAppApi/getSafetyList")
    @ResponseBody
    public JSONObject getSafetyList(Integer projectId){
        return appSafety.getSafetyList(projectId);
    }
    @RequestMapping("/safetyAppApi/sponsorSafety")
    public JSONObject sponsorSafety(HjSafetyAbarbeitung hjSafetyAbarbeitung, MultipartFile[] file)throws IOException {
        if (file.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < file.length; i++) {
                String photoName = file[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String safetyPhotos = sb.toString().substring(0, sb.toString().length() - 1);
            hjSafetyAbarbeitung.setSafetyPhotos(safetyPhotos);
        }
        return appSafety.sponsorSafety(hjSafetyAbarbeitung);
    }
    //72
    @RequestMapping("/safetyAppApi/getAfterRectification")
    public JSONObject getAfterRectification(@RequestParam(value = "projectId") Integer projectId,
                                            @RequestParam(value = "status") Integer status,
                                            @RequestParam(value = "differentiate")Integer differentiate,
                                            @RequestParam(value = "userId")Integer userId,
                                            PageDomain pageDomain){
        return appSafety.getAfterRectification(projectId,status,differentiate,userId,pageDomain);
    }
    @RequestMapping("/safetyAppApi/alterSafety")
    public JSONObject alterSafety(HjSafetyNoPass hjSafetyNoPass, MultipartFile[] file)throws IOException{
        if (file.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < file.length; i++) {
                String photoName = file[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String rectifyPhotos = sb.toString().substring(0, sb.toString().length() - 1);
            hjSafetyNoPass.setRectifyPhotos(rectifyPhotos);
        }else{
            hjSafetyNoPass.setRectifyPhotos(null);
        }
        return appSafety.alterSafety(hjSafetyNoPass);
    }
    @RequestMapping("/safetyAppApi/alertReviewPass")
    public JSONObject alertReviewPass(HjSafetyNoPass hjSafetyNoPass, MultipartFile[] file)throws IOException{
        if (file.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < file.length; i++) {
                String photoName = file[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String reviewPath = sb.toString().substring(0, sb.toString().length() - 1);
            hjSafetyNoPass.setReviewPath(reviewPath);
        }
        return appSafety.alertReviewPass(hjSafetyNoPass);
    }
    @RequestMapping("/safetyAppApi/getAfterRectificationDetails")
    public JSONObject getAfterRectificationDetails(@RequestParam(value = "safetyId")Integer safetyId){
        return appSafety.getAfterRectificationDetails(safetyId);
    }
    @RequestMapping("/safetyAppApi/sponsorQuality")
    public JSONObject sponsorQuality(HjSafetyAbarbeitung hjSafetyAbarbeitung, MultipartFile[] file) throws  IOException{
        if (file.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < file.length; i++) {
                String photoName = file[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String safetyPhotos = sb.toString().substring(0, sb.toString().length() - 1);
            hjSafetyAbarbeitung.setSafetyPhotos(safetyPhotos);
        }
        return appSafety.sponsorQuality(hjSafetyAbarbeitung,null);
    }
    @PostMapping(value = "/appDustApi/getExcessive")
    public JSONObject getExcessive(@RequestParam(value = "projectId")Integer projectId,
                                   @RequestParam(value = "userId")Integer userId,
                                   @RequestParam(value = "status")Integer status,
                                   PageDomain pageDomain){
        return appDust.getExcessive(projectId,userId,status,pageDomain);
    }
    @PostMapping(value = "/appDustApi/updateExcessive")
    public JSONObject updateExcessive(@RequestParam(value = "id",required = false)Integer id,
                                      @RequestParam(value = "status",required = false)Integer status){
        return appDust.updateExcessive(id, status);
    }
    @PostMapping(value = "/appDustApi/getCount")
    public JSONObject getCount(@RequestParam(value = "projectId")Integer projectId, @RequestParam(value = "userId")Integer userId){

        return appDust.getCount(projectId, userId);
    }
    //80
    @PostMapping(value = "/appCommissionApi/getCommission")
    @ResponseBody
    public JSONObject getCommission(Integer projectId,
                                    Integer userId,
                                    Integer status,
                                    PageDomain pageDomain){

        return appCommission.getCommission(projectId,userId,status,pageDomain);
    }
    @PostMapping(value = "/appCommissionApi/updateCommission")
    @ResponseBody
    public JSONObject updateCommission(Integer id,
                                       Integer status){
        return appCommission.updateCommission(id, status);
    }
    @PostMapping(value = "/safetyPcApi/statisticsCount")
    public JSONObject statisticsCount(@RequestParam(value = "differentiate") Integer differentiate,
                                      @RequestParam(value = "projectId") Integer projectId,
                                      @RequestParam(value = "startTime",required = false)String startTime,
                                      @RequestParam(value = "endTime",required = false)String endTime){
        return pcSafety.statisticsCount(differentiate, projectId, startTime, endTime);
    }
    @PostMapping(value = "/safetyPcApi/getInformationList")
    public JSONObject getInformationList(@RequestParam(value = "projectId") Integer projectId){
        return pcSafety.getInformationList(projectId);
    }
    @PostMapping(value = "/safetyPcApi/getInspectionRecordList")
    public JSONObject getInspectionRecordList(@RequestParam(value = "projectId") Integer projectId,
                                              @RequestParam(value = "hiddenId",required = false)Integer hiddenId,
                                              @RequestParam(value = "status",required = false)Integer status,
                                              @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                              @RequestParam(value = "initiatorName",required = false)String initiatorName,
                                              @RequestParam(value = "rectifyName",required = false)String rectifyName,
                                              @RequestParam(value = "reviewName",required = false)String reviewName,
                                              @RequestParam(value = "constructionId",required = false)Integer constructionId,
                                              @RequestParam(value = "areaId",required = false)Integer areaId,
                                              @RequestParam(value = "problemGradeId",required = false)Integer problemGradeId,
                                              @RequestParam(value = "differentiate")Integer differentiate,
                                              PageDomain pageDomain){

        return pcSafety.getInspectionRecordList(projectId, hiddenId, status, initiatorTime, initiatorName,
                rectifyName, reviewName, constructionId, areaId, problemGradeId, differentiate,pageDomain);
    }
    @PostMapping(value = "/safetyPcApi/getInspectionRecordDetails")
    public JSONObject getInspectionRecordDetails(@RequestParam(value = "safetyId")Integer safetyId){
        return pcSafety.getInspectionRecordDetails(safetyId);
    }
    @PostMapping(value = "/safetyPcApi/getConstructionList")
    public JSONObject getConstructionList(@RequestParam(value = "projectId") Integer projectId){

        return pcSafety.getConstructionList(projectId);
    }
    @PostMapping(value = "/safetyPcApi/getAreaList")
    public JSONObject getAreaListsafety(@RequestParam(value = "constructionId") Integer constructionId){
        return pcSafety.getAreaList(constructionId);
    }
    @PostMapping(value = "/safetyPcApi/getManagementList")
    public JSONObject getManagementList(@RequestParam(value = "projectId") Integer projectId,
                                        @RequestParam(value = "status",required = false)Integer status,
                                        @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                        @RequestParam(value = "initiatorName",required = false)String initiatorName,
                                        @RequestParam(value = "rectifyName",required = false)String rectifyName,
                                        @RequestParam(value = "reviewName",required = false)String reviewName,
                                        @RequestParam(value = "constructionId",required = false)Integer constructionId,
                                        @RequestParam(value = "differentiate")Integer differentiate,
                                        PageDomain pageDomain){

        return pcSafety.getManagementList(projectId, status, initiatorTime, initiatorName,
                rectifyName, reviewName, constructionId, differentiate,pageDomain);
    }
    @PostMapping(value = "/safetyPcApi/getManagementDetails")
    public JSONObject getManagementDetails(@RequestParam(value = "safetyId")Integer safetyId){
        return pcSafety.getManagementDetails(safetyId);
    }
    //90
    @PostMapping(value = "/safetyPcApi/getHiddenList")
    public JSONObject getHiddenList(){
        return pcSafety.getHiddenList();
    }
    @PostMapping(value = "/safetyPcApi/updateSafety")
    public JSONObject updateSafety(@RequestParam(value = "safetyId") Integer safetyId,
                                   @RequestParam(value = "initiatorId",required = false)Integer initiatorId,
                                   @RequestParam(value = "rectifyId",required = false)Integer rectifyId,
                                   @RequestParam(value = "reviewId",required = false)Integer reviewId,
                                   @RequestParam(value = "make",required = false)Integer[] makes,
                                   @RequestParam(value = "areaId",required = false)Integer areaId,
                                   @RequestParam(value = "safetyDeadline",required = false)String safetyDeadline,
                                   @RequestParam(value = "hiddenId",required = false)Integer hiddenId,
                                   @RequestParam(value = "safetyRequire",required = false)String safetyRequire,
                                   @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                   @RequestParam(value = "rectifyTime",required = false)String rectifyTime,
                                   @RequestParam(value = "reviewTime",required = false)String reviewTime)
    {
        return pcSafety.updateSafety(safetyId, initiatorId, rectifyId, reviewId, makes, areaId, safetyDeadline, hiddenId, safetyRequire, initiatorTime, rectifyTime, reviewTime);
    }
    @PostMapping(value = "/safetyPcApi/deleteSafety")
    public JSONObject deleteSafety(@RequestParam("safetyId")Integer safetyId){

        return pcSafety.deleteSafety(safetyId);
    }
    @PostMapping(value = "/projectWorkersApi/addAttendanceRecord")
    public Map<String, Object> addAttendanceRecord(@RequestBody String json) throws IOException {
        return projectWorkersApi.addAttendanceRecord(json);
    }
    @PostMapping("/hireApi/addHire")
    public JSONObject addHire(@RequestBody String json){
        return sbHireApi.addHire(json);
    }
    @PostMapping("/equipmentRecordApi/addEquipmentRecord")
    public Map<String,Object> addEquipmentRecord(@RequestBody String json){
        return equipmentRecord.addEquipmentRecord(json);
    }
    @RequestMapping("/projectWorkersApi/insertProjectWorkersTwo")
    @ResponseBody
    public Map<String, Object> insertProjectWorkersTwo(@RequestBody String json) throws IOException {
        return projectWorkersApi.insertProjectWorkersTwo(json);
    }
    @RequestMapping("/constructionCompanyApi/insertConstructionCompanyTwo")
    @ResponseBody
    public AjaxResult insertConstructionCompanyTwo(@RequestBody String json)throws Exception
    {
        return constructionCompanyApi.insertConstructionCompanyTwo(json);
    }
    @RequestMapping("/pcCompanyLibrary/insertHjTeamTwo")
    @ResponseBody
    public AjaxResult insertHjTeamTwo(@RequestBody String json)throws Exception {
        return pcTeamApi.insertHjTeamTwo(json);
    }
    @RequestMapping("/pcCompanyLibrary/insertHjTeam")
    @ResponseBody
    public AjaxResult insertHjTeam(HjTeam hjTeam)throws Exception {
        return pcTeamApi.insertHjTeam(hjTeam);
    }
    @RequestMapping("/pcCompanyLibrary/updateHjTeam")
    @ResponseBody
    public AjaxResult updateHjTeam(HjTeam hjTeam) {
        return pcTeamApi.updateHjTeam(hjTeam);
    }
    @RequestMapping("/pcCompanyLibrary/deleteHjTeam")
    @ResponseBody
    public AjaxResult deleteHjTeam(String ids) {
        return pcTeamApi.deleteHjTeam(ids);
    }
    @RequestMapping("/pcCompanyLibrary/selectHjTeamId")
    @ResponseBody
    public AjaxResult selectHjTeamId(Integer id) {
        return pcTeamApi.selectHjTeamId(id);
    }
    @RequestMapping("/dustEmission/addDustEmission")
    @ResponseBody
    public Map<String,Object> addDustEmission(@RequestBody String json){
        return shanDongDustEmission.addDustEmission(json);
    }
    //100
    @PostMapping(value = "/pc/projectWorkersApi/outOrInTwo")
    public  Map<String,Object> outOrInTwo(@RequestBody String json){
        return pcProjectWorkersApi.outOrInTwo(json);
    }

    @RequestMapping("/dictionariesApi/getHjDictionariesList")
    public JSONObject getHjDictionariesList(@RequestBody String json){
        return dictionariesApi.getHjDictionariesList(json);
    }

    @RequestMapping(value = "/lz/get/getWeather",method = RequestMethod.GET)
    public JSONObject getWeather(@RequestParam(value = "pid") Integer pid)throws Exception {
        return apiLZController.getWeather(pid);
    }
    @RequestMapping("/dustEmission/get/getDustEmissionList")
    public JSONObject getDustEmissionList(String pid){
        return apiDustEmissionController.getDustEmissionList(pid);
    }
    @RequestMapping("/dustEmission/get/DustEmissionDatas")
    public JSONObject getDustEmissionData(String sid)throws Exception{
        return apiDustEmissionController.getDustEmissionData(sid);
    }
    @RequestMapping(value = "/dustEmission/get/HomeDustEmissionCentre",method = RequestMethod.GET)
    public JSONObject getHomeDustEmission(String pid)throws Exception{
        return apiDustEmissionController.getHomeDustEmission(pid);
    }
    @RequestMapping("/electricityBox/get/list")
    public Object getProjectElectricityBox(String projectId) {
        return apiElectricityBoxController.getProjectElectricityBox(projectId);
    }
    @RequestMapping("/electricityBox/get/message")
    public Object getElectricityBoxRecord(String electricityBoxId, @RequestParam(required = false) String time) {
        return apiElectricityBoxController.getElectricityBoxRecord(electricityBoxId, time);
    }
    @RequestMapping("/electricityBox/get/getElectricBoxState")
    public JSONObject getElectricBoxState(String projectId) throws Exception {
        return apiElectricityBoxController.getElectricBoxState(projectId);
    }
    @RequestMapping(value = "/baogao/select")
    @ResponseBody
    public JSONObject select(HjReport hjReport, PageDomain pageDomain){
        return reportApi.select(hjReport,pageDomain);
    }
    @PostMapping(value = "/baogao/add")
    @ResponseBody
    public JSONObject add(HjReport hjReport){
        return reportApi.add(hjReport);
    }
    @RequestMapping(value = "/baogao/delete")
    @ResponseBody
    public JSONObject delete(@RequestParam(value = "id") Integer id){
        return reportApi.delete(id);
    }
    @PostMapping(value = "/workersInformationAp/insteradd")
    @ResponseBody
    public JSONObject insteradd(HjWorkersInformation hjWorkersInformation) throws IOException {
        HjWorkersInformation information = new HjWorkersInformation();
        if (hjWorkersInformation.getFile()!=null && !hjWorkersInformation.getFile().equals("")) {
            String url = null;
            url = AliyunOSSClientUtil.uploadFileImg(hjWorkersInformation.getFile(), "zhgd_img", hjWorkersInformation.getFile().getOriginalFilename());
            String name = url.substring(0, url.lastIndexOf("?"));
            information.setPath(name);//路径
            information.setTypeid(hjWorkersInformation.getTypeid());
            information.setUserId(hjWorkersInformation.getUserId());
            information.setProjectId(hjWorkersInformation.getProjectId());
        }else {
            information.setTypeid(hjWorkersInformation.getTypeid());
            information.setUserId(hjWorkersInformation.getUserId());
            information.setProjectId(hjWorkersInformation.getProjectId());
        }
        return hjWorkersInformationApi.insteradd(information);
    }
    @PostMapping(value = "/workersInformationAp/sele")
    public JSONObject sele (Integer userid){
        return hjWorkersInformationApi.sele(userid);
    }
    @PostMapping(value = "/workersInformationAp/selectall")
    @ResponseBody
    public JSONObject selectall(HjWorkersInformation hjWorkersInformation , PageDomain pageDomain){
        return hjWorkersInformationApi.selectall(hjWorkersInformation,pageDomain);
    }
    @PostMapping("/workersInformationAp/delete")
    public JSONObject workersdelete(Integer id){
        return hjWorkersInformationApi.delete(id);
    }
    @PostMapping("/workersInformationAp/lzfwtj")
    public JSONObject selectzhiliaoqiquan(Integer projectId){
        return hjWorkersInformationApi.selectzhiliaoqiquan(projectId);
    }
    @RequestMapping(value = "/lzfw/particulars")
    @ResponseBody
    public JSONObject particulars(HjInformation hjInformation, PageDomain pageDomain){
        return hjInformationApi.particulars(hjInformation,pageDomain);

    }
    @PostMapping(value = "/lzfw/deleteHjInformationById")
    public JSONObject deleteHjInformationById(@RequestParam(value ="id" )Integer id){
        return hjInformationApi.deleteHjInformationById(id);
    }
    @RequestMapping(value = "/lzfw/instadd")
    @ResponseBody
    public JSONObject instadd(HjInformation hjInformation) throws IOException {
        HjInformation information = new HjInformation();
        String url = null;
        String[] q = new String[hjInformation.getFile().length];
        String[] w = new String[hjInformation.getFile().length];
        for (int i = 0;i<hjInformation.getFile().length;i++){
            q[i] = hjInformation.getFile()[i].getOriginalFilename();
            url= AliyunOSSClientUtil.uploadFileImg(hjInformation.getFile()[i],q[i],"");
            String name = url.substring(0,url.lastIndexOf("?"));
            w[i] = name;
        }
        information.setFilePaths(w);
        information.setFileNames(q);
        information.setUploadingName(hjInformation.getUploadingName());
        information.setUploadingDate(new Date());
        information.setRemark(hjInformation.getRemark());
        information.setUnitId(hjInformation.getUnitId());
        information.setProjectId(hjInformation.getProjectId());
        information.setMenuId(hjInformation.getMenuId());
        return hjInformationApi.instadd(information);
    }
    @PostMapping(value = "/lzfw/upda")
    @ResponseBody
    public JSONObject upda(HjInformation hjInformation)  throws IOException {
        return hjInformationApi.upda(hjInformation);
    }
    @GetMapping("/lzfw/testDownload")
    public void downCfg(String imgURL, HttpServletResponse response) throws IOException {
        String fileName = imgURL.substring(imgURL.lastIndexOf("/")+1,imgURL.length());
        // 创建URL
        URL url = new URL(imgURL);
        byte[] by = new byte[1024];
        // 创建链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        try (
                InputStream inputStream = conn.getInputStream();
                OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping(value = "/menu/seleall")
    public JSONObject seleall(HjMenu menu)throws IOException, URISyntaxException{
        return hjMenuApi.seleall(menu);
    }
    @PostMapping("/hireApi/getHireSearch")
    public JSONObject getPeopleAreaSearch(@RequestParam(value = "filed", required = false) String filed,@RequestParam(value = "projectId")int projectId) {
        return sbHireApi.getPeopleAreaSearch(filed,projectId);
    }
    @PostMapping("/hireApi/getHireHistory")
    public JSONObject getHireHistory(@RequestParam(value = "filed", required = false) String filed,
                                     @RequestParam(value = "projectId")int projectId,
                                     @RequestParam(value = "startTime", required = false) String startTime) {
        return sbHireApi.getHireHistory(filed,projectId, startTime);
    }
    @PostMapping("/hireApi/getHirePeople")
    public JSONObject getHirePeople(@RequestParam(value = "projectId")int projectId) throws ParseException {
        return sbHireApi.getHirePeople(projectId);
    }
    @PostMapping("/hireAppApi/getHirePeople")
    public JSONObject getAppHirePeople( @RequestParam(value = "projectId")int projectId) throws ParseException  {
        return sbHireAppApi.getHirePeople(projectId);
    }
    @PostMapping("/hireAppApi/getHireByNameSearch")
    public JSONObject getAppHireByNameSearch(@RequestParam(value = "userName",required = false) String userName,
                                             @RequestParam(value = "projectId")int projectId) throws ParseException  {
        return sbHireAppApi.getHireByNameSearch(userName,projectId);
    }
    @PostMapping("/hireAppApi/getHireByNameHistory")
    public JSONObject getHireByNameHistory(@RequestParam(value = "userName",required = false) String userName,
                                           @RequestParam(value = "projectId")int projectId) {
        return sbHireAppApi.getHireByNameHistory(userName,projectId);
    }
//    @PostMapping("/hireAppApi/getHirePeopleList")
//    public JSONObject getAppHirePeopleList(@RequestParam(value = "userName",required = false)String userName,
//                                           @RequestParam(value = "projectId")int projectId) throws ParseException{
//        return sbHireAppApi.getHirePeopleList(userName,projectId);
//    }
    @PostMapping( value = "/vehicle/app/selectAll")
    public JSONObject selectVehicleAll(Vehicle vehicle , PageDomain pageDomain){
        return vehicleAppApi.selectAll(vehicle, pageDomain);
    }
    @PostMapping(value = "/vehicle/app/carcount")
    public JSONObject countcar(Vehicle vehicle){
        return vehicleAppApi.countcar(vehicle);
    }
    @PostMapping(value = "/vehicle/app/selectpkcounts")
        public JSONObject appparkingspace(HjZhgdPkcount hjZhgdPkcount){
        return vehicleAppApi.parkingspace(hjZhgdPkcount);
    }
//    @PostMapping(value = "/vehicle/app/carpkcount")
//    public JSONObject carpkcount( HjZhgdPkcount hjZhgdPkcount){
//        return vehicleAppApi.carpkcount(hjZhgdPkcount);
//    }
    @PostMapping( value = "/parkings/selectAll")
    public JSONObject selectAll(Vehicle vehicle, PageDomain pageDomain){
        return vehicleAPI.selectAll(vehicle,pageDomain);
    }
    @PostMapping(value = "/parkings/parking/selectscene")
    public JSONObject selectscene(Vehicle vehicle, PageDomain pageDomain) throws ParseException{
        return vehicleAPI.selectscene(vehicle,pageDomain);
    }
    @PostMapping(value = "/parkings/selectpkcount")
    @ResponseBody
    public JSONObject parkingspace(HjZhgdPkcount hjZhgdPkcount){
        return vehicleAPI.parkingspace(hjZhgdPkcount);
    }
    @PostMapping(value = "/parkings/todaycount")
    public JSONObject parkingTodaycount( @RequestParam("projectId") Integer projectId){
        return vehicleAPI.todaycount(projectId);
    }
    @PostMapping("/DustEmissionThresholdValueAPI/getThresholdValue")
    public AjaxResult getThresholdValue(DustEmissionThresholdValue thresholdValue){
        return dustEmissionThresholdValueAPI.getThresholdValue(thresholdValue);
    }
    @PostMapping("/DustEmissionThresholdValueAPI/updateThresholdValue")
    public AjaxResult updateThresholdValue(DustEmissionThresholdValue thresholdValue){
        return dustEmissionThresholdValueAPI.updateThresholdValue(thresholdValue);
    }
    @RequestMapping("/system/computer/test")
    public AjaxResult test(){
        return pc_systrmUserApi.test();
    }
    @RequestMapping(value = "/system/computer/login")
    public AjaxResult pcLogin(HjSystemUser user) {
        if (user.getUserAccount() == null || user.getUserPassword() == null) {
            return error(-1, "账户密码不能为空");
        }
        return pc_systrmUserApi.login(user);
    }
    @RequestMapping(value = "/system/application/login")
    public AjaxResult appLogin(HjSystemUser user) {
        if (user.getUserAccount() == null || user.getUserPassword() == null) {
            return error(-1, "账户密码不能为空");
        }
        return appSystrmUserApi.login(user);
    }
    @RequestMapping(value = "/systemPrivileges/pc/getSystemPrivileges")
    public AjaxResult getSystemPrivileges_pc(Integer uid, Integer appOrPc, Integer parentId) {
        return pc_systemPrivilegesApi.getSystemPrivileges_pc(uid, appOrPc, parentId);
    }
    @RequestMapping(value = "/systemPrivileges/app/getSystemPrivileges")
    public AjaxResult getSystemPrivileges_app(Integer uid, Integer appOrPc, Integer parentId) {
        return app_systemPrivilegesApi.getSystemPrivileges_app(uid, appOrPc,parentId);
    }
    @RequestMapping("/attendanceRecordApi/selectWorkerAttendance")
    @ResponseBody
    public Map<String, Object> selectWorkerAttendance(HjAttendanceRecord hjAttendanceRecord)
    {
        return attendanceRecordApi.selectWorkerAttendance(hjAttendanceRecord);
    }
    @RequestMapping("/attendanceRecordApi/selectAdministration")
    @ResponseBody
    public Map<String, Object> selectAdministration(HjAttendanceRecord hjAttendanceRecord)
    {
        return attendanceRecordApi.selectAdministration(hjAttendanceRecord);
    }
    @RequestMapping("/attendanceRecordApi/getNearlyEightDays")
    @ResponseBody
    public JSONObject attendanceRecordApiGetNearlyEightDays( @RequestParam("projectId") Integer projectId){
        return attendanceRecordApi.attendance(projectId);
    }
    @RequestMapping("/attendanceRecordApi/getTeamCount")
    @ResponseBody
    public JSONObject attendanceRecordApiGetTeamCount( @RequestParam("projectId") Integer projectId){
        return attendanceRecordApi.turnover(projectId);
    }
    @RequestMapping("/attendanceRecordApi/getWorkType")
    @ResponseBody
    public JSONObject attendanceRecordApiGetWorkType( @RequestParam("projectId") Integer projectId){
        return attendanceRecordApi.list(projectId);
    }
    @RequestMapping("/attendanceRecordApi/getBuildcompanyData")
    @ResponseBody
    public JSONObject attendanceRecordApiGetBuildcompanyData( @RequestParam("projectId") Integer projectId){
        return attendanceRecordApi.item(projectId);
    }
    @RequestMapping("/attendanceRecordApi/getXS")
    @ResponseBody
    public JSONObject attendanceRecordApiGetXS( @RequestParam("projectId") Integer projectId) throws ParseException {
        return attendanceRecordApi.jrld(projectId);
    }
    @RequestMapping("/projectWorkersApi/selectProjectWorkers")
    @ResponseBody
    public Map<String, Object> selectProjectWorkers(EmpNameParam empNameParam) {
        return projectWorkersApi.selectProjectWorkers(empNameParam);
    }
    @RequestMapping("/projectWorkersApi/selectProjectWorkersDetails")
    @ResponseBody
    public Map<String, Object> selectProjectWorkersDetails(ProjectWorkersParam projectWorkersParam) {
        return projectWorkersApi.selectProjectWorkersDetails(projectWorkersParam);
    }
    @RequestMapping("/projectWorkersApi/selectConstructionProject")
    @ResponseBody
    public Map<String, Object> selectConstructionProject(ProjectWorkers projectWorkers) {
        System.out.println("-------------------------------------------------------------" + projectWorkers);
        return projectWorkersApi.selectConstructionProject(projectWorkers);
    }
    @RequestMapping("/projectWorkersApi/insertProjectWorkers")
    @ResponseBody
    public Map<String, Object> insertProjectWorkers(HjProjectWorkers hjProjectWorkers) {
        System.out.println("-------------------------------------------------------------" + hjProjectWorkers);
        return projectWorkersApi.insertProjectWorkers(hjProjectWorkers);
    }
    @RequestMapping("/projectWorkersApi/updateProjectWorkers")
    @ResponseBody
    public Map<String, Object> updateProjectWorkers(HjProjectWorkers hj, MultipartFile file) throws Exception {
        System.out.println(hj);
//        System.out.println(file.getOriginalFilename());
        //        return hjProjectWorkersService.updateProjectWorkers(hj);
        hj.setUpdateDate(new SimpleDateFormat().format(new Date()));
        return projectWorkersApi.updateProjectWorkers(hj,file);
    }
    @RequestMapping("/dictionariesApi/selectDictionaries")
    public Map<String, Object> selectDictionaries(HjDictionaries hjDictionaries){
        return  dictionariesApi.selectDictionaries(hjDictionaries);
    }
    @RequestMapping("/dictionariesApi/selectDictionariesWorkType")
    public Map<String, Object> selectDictionariesWorkType(Integer pid)
    {
        return  dictionariesApi.selectDictionariesWorkType(pid);
    }
    @RequestMapping("/area/getArea")
    public AjaxResult getArea(Long parentId){
        if(parentId==null){
            return AjaxResult.error();
        }
        return areaApi.getArea(parentId);
    }
    @RequestMapping("/pcCompanyLibrary/insertHjCompanyLibrary")
    public int insertHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary, Integer pid)
    {
        return pcCompanyLibraryApi.insertHjCompanyLibrary(hjCompanyLibrary, pid);
    }
    @RequestMapping("/pcCompanyLibrary/selectHjCompanyLibrary")
    public AjaxResult selectHjCompanyLibraryIds(Integer id)
    {
        return AjaxResult.success(pcCompanyLibraryApi.selectHjCompanyLibraryIds(id));
    }
    @RequestMapping("/pcCompanyLibrary/updateHjCompanyLibrary")
    public AjaxResult updateHjCompanyLibrary(HjCompanyLibrary hjCompanyLibrary)
    {
        System.out.println(hjCompanyLibrary);
        return AjaxResult.success(pcCompanyLibraryApi.updateHjCompanyLibrary(hjCompanyLibrary));
    }
    @RequestMapping("/pcCompanyLibrary/selectHjCompanyLibraryList")
    public AjaxResult selectHjCompanyLibraryList(HjCompanyLibrary hjCompanyLibrary){
        System.out.println(hjCompanyLibrary);
        return AjaxResult.success(pcCompanyLibraryApi.selectHjCompanyLibraryList(hjCompanyLibrary));
    }
    @RequestMapping("/project/addProject")
    public Map<String,Object> addSave(HjProject hjProject, Integer cid, MultipartFile file, String remark1 , String shortName1  ) throws Exception {
        System.out.println(cid);
        Map<String,Object> map  = new HashMap<>();
        map.put("hjProject",hjProject);
        map.put("cid",cid);
        map.put("remark1",remark1);
        map.put("shortName1",shortName1);
        return projectApi.addSave(hjProject,cid,file,remark1,shortName1);
    }
    @RequestMapping("/projectWorkersApi/selectSignParam")
    @ResponseBody
    public Map<String, Object> selectSignParam(SignParam signParam) {
        return projectWorkersApi.selectSignParam(signParam);
    }
    @RequestMapping("/pcCompanyLibrary/selectHjCompanyProjectList")
    public Map<String,Object> selectHjCompanyProjectList(Integer companyId)throws Exception{
//        Map<String,Object> map = new HashMap<>();
//        map.put("companyId",companyId);
        System.out.println(companyId);
        return AjaxResult.success(pcCompanyLibraryApi.selectHjCompanyProjectList(companyId));
//        return ( Map<String,Object>)restTemplateUtil.Post(map,Constants.SERVICE_NAME+"/provider/pcCompanyLibrary/selectHjCompanyProjectList");

    }
    @RequestMapping(value = "/system/application/updateUserPassword")
    public Map<String, Object> updateUserPassword( PasswordParam passwordParam){
        return appSystrmUserApi.updateUserPassword(passwordParam);
    }
    //166
    @RequestMapping("/system/application/queryProject")
    public Map<String, Object> queryProject(Integer userId)
    {
        return appSystrmUserApi.queryProject(userId);
    }
    @RequestMapping("/pcCompanyLibrary/selectHjTeamList")
    @ResponseBody
    public AjaxResult selectHjTeamList(HjTeam hjTeam, PageDomain pageDomain)throws Exception
    {
        return pcTeamApi.selectHjTeamList(hjTeam,pageDomain);
    }
    @RequestMapping(value = "/teamApi/app/selectTeam")
    public Map<String, Object> selectTeam(HjTeam hjTeam)throws Exception {
//       restTemplateUtil.Post(hjTeam, Constants.SERVICE_NAME+"provider/teamApi/app/selectTeam");
        return app_teamApi.selectTeam(hjTeam);
    }
    @RequestMapping("/constructionCompanyApi/selectConstructionCompany")
    @ResponseBody
    public Map<String, Object> selectConstructionCompany(Integer projectId)throws Exception
    {
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",projectId);
//        return (Map<String, Object>)restTemplateUtil.Post(map, Constants.SERVICE_NAME+"/provider/constructionCompanyApi/selectConstructionCompany");
        return constructionCompanyApi.selectConstructionCompany(projectId);
    }
    @GetMapping("/constructionCompanyApi/export")
    public void export(String param , String suid, Integer projectId,HttpServletResponse response)throws Exception{
        List<HjConstructionCompany> list = constructionCompanyApi.export(param,suid,projectId);
        ExcelUtil<HjConstructionCompany> util = new ExcelUtil<HjConstructionCompany>(HjConstructionCompany.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "参建单位");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @RequestMapping(value ="/dictionar/provider/systemRoleApiiesApi/selectWorkType",method = RequestMethod.POST)
    public  Map<String, Object> selectWorkType(HjDictionaries hjDictionaries){
        return dictionariesApi.selectWorkType(hjDictionaries);
    }
    @RequestMapping(value ="/dictionariesApi/cxdjpt",method = RequestMethod.POST)
    public JSONObject caydjpt(HjDictionaries hjDictionaries){
        return dictionariesApi.caydjpt(hjDictionaries);
    }
    @Log(title = "项目两制同步", businessType = BusinessType.INSERT)
    @PostMapping("/synchronizationInformationApi/add")
    public Map<String,Object> addSave(HjSynchronizationInformation hjSynchronizationInformation)
    {
        return pc_hjSynchronizationInformationApi.addSave(hjSynchronizationInformation);
    }
    @Log(title = "项目两制同步", businessType = BusinessType.UPDATE)
    @PostMapping("/synchronizationInformationApi/edit")
    public Map<String,Object> editSave(HjSynchronizationInformation hjSynchronizationInformation)
    {
        return pc_hjSynchronizationInformationApi.editSave(hjSynchronizationInformation);
    }
    @Log(title = "项目两制同步", businessType = BusinessType.DELETE)
    @PostMapping( "/synchronizationInformationApi/remove")
    public Map<String,Object> remove(String ids)
    {
        return pc_hjSynchronizationInformationApi.remove(ids);
    }
    @RequestMapping(value = "/systemRoleApi/insertSystemRole")
    public Map<String, Object> insertSystemRole(HjSystemRole hjSystemRole, String ids) {
        return pc_systemRoleApi.addSystemRole(hjSystemRole,ids);
    }
    @RequestMapping(value = "/systemRoleApi/selectSystemRoleId")
    public Map<String, Object> selectSystemRoleId( HjSystemRole hjSystemRole) {
        return pc_systemRoleApi.selectSystemRoleId(hjSystemRole);
    }
    @RequestMapping(value = "/systemRoleApi/updateSystemRole")
    public Map<String, Object> updateSystemRole(HjSystemRole hjSystemRole, String ids) {
        return pc_systemRoleApi.updateRole(hjSystemRole, ids);
    }
    @RequestMapping(value = "/systemRoleApi/deleteSystemRole")
    public Map<String, Object> deleteSystemRole( String ids) {
        return pc_systemRoleApi.deleteSystemRole(ids);
    }
    @RequestMapping(value = "/systemRoleApi/selectSystemRole")
    public Map<String, Object> selectSystemRole( RoleParam systemRoleParam) {
        return pc_systemRoleApi.selectSystemRole(systemRoleParam);
    }
    @RequestMapping(value = "/systemRoleApi/querySystemRole")
    public Map<String, Object> querySystemRole( HjSystemRole hjSystemRole) {
        return pc_systemRoleApi.querySystemRole(hjSystemRole);
    }
    @RequestMapping(value = "/system/computer/selectSystemUser")
    public Map<String, Object> selectSystemUser( HjSystemUser user) {
        return pc_systrmUserApi.selectSystemUser(user);
    }
    @RequestMapping(value = "/system/computer/selectUserId")
    public Map<String, Object> selectUserId( HjSystemUser hjSystemUser) {
        return pc_systrmUserApi.selectUserId(hjSystemUser);
    }
    @RequestMapping(value = "/system/computer/insertSystemUser")
    public Map<String, Object> insertSystemUser( SystemUserParam systemUserParam) {
        return pc_systrmUserApi.insertSystemUser(systemUserParam);
    }
    @RequestMapping(value = "/system/computer/updateSystemUser")
    public Map<String, Object> updateSystemUser(HjSystemUser hjSystemUser, String ids) {
        return pc_systrmUserApi.updateUser(hjSystemUser,ids);
    }
    @RequestMapping(value = "/system/computer/deleteSystemUser")
    public Map<String, Object> deleteSystemUser( String ids) {
        return pc_systrmUserApi.deleteSystemUser(ids);
    }
    @RequestMapping("/attendanceRecordPcApi/selectAttendanceRecordList")
    @ResponseBody
    public Map<String,Object> selectAttendanceRecordListTwo(Param param, PageDomain pageDomain)throws Exception
    {
        return attendanceRecordPcApi.selectAttendanceRecordListTwo(param,pageDomain);
    }
    @PostMapping(value = "/pc/projectWorkersApi/outOrIn")
    public  Map<String,Object> out(Integer tag,String ids){
        return pcProjectWorkersApi.outOrIn(tag,ids);
    }
    @PostMapping(value = "/pc/projectWorkersApi/updateQuarantine")
    public AjaxResult updateQuarantine(Integer tag, String ids){
        return pcProjectWorkersApi.updateQuarantine(tag,ids);
    }
    //200
    @RequestMapping(value = "/systemPrivileges/pc/selectSystemPrivileges")
    public Map<String, Object> selectSystemPrivileges( SystemRoleParam systemRoleParam){
        System.out.println(systemRoleParam);
        return pc_systemPrivilegesApi.selectSystemPrivileges(systemRoleParam);
    }
    @PostMapping(value = "/systemPrivileges/pc/getPrivilegesList")
    public JSONObject getPrivilegesList(@RequestParam("userId")Integer userId){
        return pc_systemPrivilegesApi.getPrivilegesList(userId);
    }
    @RequestMapping(value = "/systemPrivileges/pc/querySystemPrivileges")
    public Map<String, Object> querySystemPrivileges(HjSystemPrivileges hjSystemPrivileges) {
        return pc_systemPrivilegesApi.querySystemPrivileges(hjSystemPrivileges);
    }
    @GetMapping("/pc/projectWorkersApi/export")
    public void export(HjProjectWorkers hjProjectWorkers, HttpServletResponse response)throws Exception{
        System.out.println("导出Excel："+hjProjectWorkers);
        List<ProjectWorkerPC> list = pcProjectWorkersApi.export(hjProjectWorkers);
        ExcelUtil<ProjectWorkerPC> util = new ExcelUtil<ProjectWorkerPC>(ProjectWorkerPC.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "项目工人");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @GetMapping("/workersInformationAp/workersExport")
    public void export(HjWorkersInformation hjWorkersInformation, HttpServletResponse response)throws Exception{
        System.out.println("导出Excel："+hjWorkersInformation);
        List<HjWorkersInformationPc> list = hjWorkersInformationApi.export(hjWorkersInformation);
        ExcelUtil<HjWorkersInformationPc> util = new ExcelUtil<HjWorkersInformationPc>(HjWorkersInformationPc.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "工人信息");
        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @RequestMapping("/pc/projectWorkersApi/getDataCount")
    @ResponseBody
    public JSONObject projectWorkersApiGetDataCount(@RequestParam(value = "projectId")Integer projectId){
        return pcProjectWorkersApi.jyht(projectId);
    }
    @RequestMapping("/pc/projectWorkersApi/getKQCount")
    @ResponseBody
    public JSONObject projectWorkersApiGetKQCount(@RequestParam(value = "projectId") Integer projectId){
        return  pcProjectWorkersApi.kanban(projectId);
    }
    @GetMapping("/pcCompanyLibrary/export")
    public void export(HjTeam hjTeam, HttpServletResponse response)throws Exception{
        System.out.println("导出Excel："+hjTeam);
        List<HjTeam> list = pcTeamApi.export(hjTeam);
        ExcelUtil<HjTeam> util = new ExcelUtil<HjTeam>(HjTeam.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "班组");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @PostMapping("/pc/projectWorkersApi/edit")
    public AjaxResult editSaveq(HjProjectWorkers hjProjectWorkers)throws Exception
    {
        System.out.println(hjProjectWorkers);
        hjProjectWorkers.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return pcProjectWorkersApi.editSave(hjProjectWorkers);
    }

    @GetMapping("/attendanceRecordPcApi/export")
    public void export(Param param , HttpServletResponse response)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取数据
        List<Param> list = attendanceRecordPcApi.export(param);

        //excel标题
        String[] title = {"人员编号", "姓名", "项目名称", "参建单位名称", "工种","通行方向","通行时间"};

        //excel文件名
        String fileName = "考勤记录" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "考勤记录";

        String content[][] = new String[list.size()][title.length];
        for (int i = 0; i < list.size(); i++) {


            Param obj = list.get(i);
            content[i][0] = obj.getEmployeeId().toString();
            content[i][1] = obj.getName();
            content[i][2] = obj.getProjectName();
            content[i][3] = obj.getCompanyName();
            content[i][4] = obj.getJobName();
            content[i][5] ="in".equals(obj.getDirection())?"进":"出";
            content[i][6]=obj.getPassedTime();
            ;
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
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
    @GetMapping("/parkings/export")
    public void export(Vehicle vehicle, HttpServletResponse response)throws Exception{
        System.out.println("导出Excel："+vehicle);
        List<Vehicle> list = vehicleAPI.selectAlls(vehicle);
        ExcelUtil<Vehicle> util = new ExcelUtil<Vehicle>(Vehicle.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "车辆进出数据");
        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @GetMapping("/baogao/export")
    public void export(HjReport hjReport, HttpServletResponse response)throws Exception{
        System.out.println("导出Excel："+hjReport);
        List<HjReportPc> list = reportApi.export(hjReport);
        ExcelUtil<HjReportPc> util = new ExcelUtil<HjReportPc>(HjReportPc.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "工作汇报");
        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @RequestMapping("/pcLzIndex/selectIndex")
    @ResponseBody
    public AjaxResult selectIndex(Integer pid)
    {
        return pcIndexApi.selectIndex(pid);
    }

//    @RequestMapping(value = "/moredian/record")
//    public AjaxResult record(@RequestBody String json)throws Exception{
//
//        return moredianAttendanceRecordApi.record(json);
//    }
    @PostMapping("/ProjectDustEmission/getProjectDustEmission")
    public JSONObject getProjectDustEmission(@RequestParam(value = "projectId")Long projectId){
       return pcProjectDustEmissionApi.getProjectDustEmission(projectId);
    }
    @PostMapping("/ProjectDustEmission/projectDustEmissionList")
    public AjaxResult projectDustEmissionList(SbProjectDustEmission sbProjectDustEmission,
                                              PageDomain pageDomain)
    {
        return pcProjectDustEmissionApi.projectDustEmissionList(sbProjectDustEmission,pageDomain);
    }
    @PostMapping("/ProjectDustEmission/projectDustEmissionAddSave")
    public AjaxResult projectDustEmissionAddSave(SbProjectDustEmission sbProjectDustEmission)throws IOException, URISyntaxException
    {
        return pcProjectDustEmissionApi.projectDustEmissionAddSave(sbProjectDustEmission);
    }
    @PostMapping("/ProjectDustEmission/projectDustEmissionById")
    public AjaxResult projectDustEmissionById(@RequestParam(value = "id") Long id)
    {
        return pcProjectDustEmissionApi.projectDustEmissionById(id);
    }
    @PostMapping("/ProjectDustEmission/projectDustEmissionEditSave")
    public AjaxResult projectDustEmissionEditSave(SbProjectDustEmission sbProjectDustEmission)
    {
        return pcProjectDustEmissionApi.projectDustEmissionEditSave(sbProjectDustEmission);
    }
    @PostMapping( "/ProjectDustEmission/remove")
    public AjaxResult projectDustEmissionRemove(@RequestParam(value = "id") Integer id, @RequestParam(value = "devCcrq",required = false)String devCcrq) throws IOException, URISyntaxException
    {
        return pcProjectDustEmissionApi.remove(id,devCcrq);
    }
    @PostMapping("/appProjectDustEmission/getAppProjectDustEmission")
    public JSONObject getAppProjectDustEmission(@RequestParam(value = "projectId",required = false)Long projectId){
        return appProjectDustEmissionApi.getAppProjectDustEmission(projectId);
    }
    @PostMapping("/DustEmission/getDustEmission")
    public JSONObject getDustEmission(@RequestParam(value = "sn")String sn,
                                      @RequestParam(value = "tag",required = false)Integer tag,
                                      @RequestParam(value = "startTime",required = false)String startTime,
                                      @RequestParam(value = "endTime",required = false)String endTime, PageDomain pageDomain) {
        return dustEmissionApi.getDustEmission(sn, tag, startTime, endTime, pageDomain);
    }
    @PostMapping("/DustEmission/getPM25AndPN10")
    public JSONObject getPM25AndPN10(String sn,Integer projectId)throws  Exception{
        return dustEmissionApi.getPM25AndPN10(sn,projectId);
    }
    @GetMapping("/DustEmission/getExcel")
    public void getExcel(@RequestParam(value = "sn")String sn,
                         @RequestParam(value = "startTime",required = false)String startTime,
                         @RequestParam(value = "endTime",required = false)String endTime, HttpServletResponse response)throws Exception{
        List<SbDustEmission> excel = dustEmissionApi.getExcel(sn, startTime, endTime);
        ExcelUtil<SbDustEmission> util = new ExcelUtil<SbDustEmission>(SbDustEmission.class);
        //生成Excel
        AjaxResult a = util.exportExcel(excel, "扬尘监测记录");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @RequestMapping("/DustEmission/Tsp")
    @ResponseBody
    public JSONObject tsp(@RequestParam(value = "projectId") long projectId)
    {
        return dustEmissionApi.raise(projectId);
    }
    @PostMapping("/DustEmission/getTSP")
    @ResponseBody
    public JSONObject getDustUserList(@RequestParam("projectId")Integer projectId,
                                      @RequestParam(value = "filed",required = false)String filed){
        return  dustEmissionApi.getDustUserList(projectId, filed);
    }
    @PostMapping("/ProjectVideoAreaApi/getProjectVideoArea")
    public JSONObject getProjectVideoArea(@RequestParam(value = "projectId") Integer projectId){
        return projectVideoAreaApi.getProjectVideoArea(projectId);
    }
    @PostMapping("/ProjectVideoAreaApi/list")
    public AjaxResult projectVideoAreaList(SbProjectVideoArea sbProjectVideoArea,
                                           @RequestParam(value = "pageNum")Integer pageNum,
                                           @RequestParam(value = "pageSize")Integer pageSize)
    {
        return projectVideoAreaApi.projectVideoAreaList(sbProjectVideoArea, pageNum, pageSize);
    }
    @PostMapping("/ProjectVideoAreaApi/add")
    public AjaxResult projectVideoAreaAddSave(SbProjectVideoArea sbProjectVideoArea)
    {
        return projectVideoAreaApi.projectVideoAreaAddSave(sbProjectVideoArea);
    }
    @PostMapping("/ProjectVideoAreaApi/edit")
    public AjaxResult projectVideoAreaEdit(Integer id)
    {
        return projectVideoAreaApi.projectVideoAreaEdit(id);
    }
    @PostMapping("/ProjectVideoAreaApi/editSave")
    public AjaxResult projectVideoAreaEditSave(SbProjectVideoArea sbProjectVideoArea)
    {
        System.out.println(sbProjectVideoArea);
        return projectVideoAreaApi.projectVideoAreaEditSave(sbProjectVideoArea);
    }
    @PostMapping( "/ProjectVideoAreaApi/remove")
    public AjaxResult projectVideoAreaRemove(String ids)
    {

        return projectVideoAreaApi.projectVideoAreaRemove(ids);
    }
    @PostMapping("/ProjectVideoAreaApi/getVideoListJT")
    public List<SbJTArea> getVideoListJT(Integer cid) throws  Exception{
        return projectVideoAreaApi.getVideoListJT(cid);
    }
    @PostMapping("/ProjectVideoAreaApi/getVideoListImgUrl")
    public List<Video> getVideoListImgUrl(Integer cid) throws  Exception{
        return projectVideoAreaApi.getVideoListImgUrl(cid);
    }
    @PostMapping("/ProjectVideoAreaApi/getVideoProject")
    public ProjectVideoJT getVideoProject(Integer pid) throws  Exception{
        return projectVideoAreaApi.getVideoProject(pid);
    }
    @PostMapping("/ProjectVideoAreaApi/getProjectVideoImgUrl")
    public List<Video> getProjectVideoImgUrl(Integer pid) throws  Exception{
        return projectVideoAreaApi.getProjectVideoImgUrl(pid);
    }
    @PostMapping("/appProjectVideoAreaApi/getAppProjectVideoArea")
    public JSONObject getAppProjectVideoArea(@RequestParam(value = "projectId") Integer projectId){
        return appProjectVideoAreaApi.getAppProjectVideoArea(projectId);
    }
    @PostMapping("/ProjectVideo/getProjectVideo")
    public JSONObject getProjectVideo(@RequestParam(value = "areaId") Integer areaId){
        return projectVideo.getProjectVideo(areaId);
    }
    @PostMapping( "/ProjectVideo/deleteSbProjectVideoByIds")
    public AjaxResult deleteSbProjectVideoByIds(@RequestParam(value = "ids") String ids)
    {
        return projectVideo.deleteSbProjectVideoByIds(ids);
    }
    @PostMapping("/ProjectVideo/projectVideoList")
    public AjaxResult projectVideoList(SbProjectVideo sbProjectVideo,
                                       @RequestParam(value = "pageNum")Integer pageNum,
                                       @RequestParam(value = "pageSize")Integer pageSize)
    {
        return projectVideo.projectVideoList(sbProjectVideo,pageNum,pageSize);
    }
    @PostMapping("/ProjectVideo/selectSbProjectVideoByProjectId")
    public AjaxResult selectSbProjectVideoByProjectId(@RequestParam(value = "projectId") Integer projectId,
                                                      @RequestParam(value = "pageNum")Integer pageNum,
                                                      @RequestParam(value = "pageSize")Integer pageSize)
    {
        return projectVideo.selectSbProjectVideoByProjectId(projectId, pageNum, pageSize);
    }
    @PostMapping("/ProjectVideo/projectVideoAddSave")
    public AjaxResult projectVideoAddSave(SbProjectVideo sbProjectVideo)
    {
        return projectVideo.projectVideoAddSave(sbProjectVideo);
    }
    @PostMapping("/ProjectVideo/selectProjectVideoById")
    public AjaxResult selectProjectVideoById(@RequestParam(value = "id") Integer id)
    {
        return projectVideo.selectProjectVideoById(id);
    }
    @PostMapping("/ProjectVideo/projectVideoEditSave")
    public AjaxResult projectVideoEditSave(SbProjectVideo sbProjectVideo)
    {
        return projectVideo.projectVideoEditSave(sbProjectVideo);
    }
    @PostMapping("/ProjectVideo/ysCloudControldirection")
    public void ysCloudControldirection(Integer pid, String deviceSerial , Integer direction, Video video) throws  Exception{
        projectVideo.ysCloudControldirection(pid,deviceSerial,direction,video);
    }
    @PostMapping("/ProjectVideo/updateVideoCoordinate")
    public AjaxResult updateVideoCoordinate(SbProjectVideo sbProjectVideo){
        return projectVideo.updateVideoCoordinate(sbProjectVideo);
    }
    @PostMapping("/currentTemperatureApi/list")
    public JSONObject list(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                           @RequestParam(value = "startTime",required = false)String startTime,
                           @RequestParam(value = "endTime",required = false)String endTime,
                           @RequestParam(value = "pageNum")Integer pageNum,
                           @RequestParam(value = "pageSize")Integer pageSize)
    {
        return currentTemperatureApi.list(electricityBoxId, startTime, endTime,pageNum,pageSize);
    }
    @PostMapping("/ProjectElectricityBox/getProjectElectricityBox")
    public JSONObject getProjectElectricityBox(@RequestParam(value = "projectId") Integer projectId){
        return projectElectricityBoxApi.getProjectElectricityBox(projectId);
    }
    @PostMapping("/ProjectElectricityBox/list")
    public AjaxResult list(SbProjectElectricityBox sbProjectElectricityBox,
                           @RequestParam(value = "pageNum")Integer pageNum,
                           @RequestParam(value = "pageSize")Integer pageSize)
    {
        return projectElectricityBoxApi.list(sbProjectElectricityBox);
    }
    @PostMapping("/ProjectElectricityBox/addSave")
    public AjaxResult addSave(SbProjectElectricityBox sbProjectElectricityBox)
    {
        return projectElectricityBoxApi.addSave(sbProjectElectricityBox);
    }
    @PostMapping("/ProjectElectricityBox/addElectricityBox")
    public AjaxResult addElectricityBox(@RequestParam(value = "projectId")Integer projectId,
                                        @RequestParam(value = "electricityBoxId")String electricityBoxId,
                                        @RequestParam(value = "electricityBoxName")String electricityBoxName)
    {
        return projectElectricityBoxApi.addElectricityBox(projectId,electricityBoxId,electricityBoxName);
    }
    @PostMapping("/ProjectElectricityBox/edit")
    public AjaxResult edit(@RequestParam("id") Integer id)
    {
        return projectElectricityBoxApi.edit(id);
    }
    @PostMapping("/ProjectElectricityBox/editSave")
    public AjaxResult editSave(SbProjectElectricityBox sbProjectElectricityBox)
    {
        return projectElectricityBoxApi.editSave(sbProjectElectricityBox);
    }
    @PostMapping( "/ProjectElectricityBox/remove")
    public AjaxResult remove(Integer id, String devCcrq) throws IOException, URISyntaxException
    {
        return projectElectricityBoxApi.remove(id,devCcrq);
    }
    @PostMapping("/appProjectElectricityBox/getAppProjectElectricityBox")
    public JSONObject getAppProjectElectricityBox(@RequestParam(value = "projectId") Integer projectId){
        return appProjectElectricityBoxApi.getAppProjectElectricityBox(projectId);
    }
    @PostMapping("/appProjectElectricityBox/getBoxList")
    public AjaxResult getBoxList(@RequestParam(value = "pid") Integer pid){
        return appProjectElectricityBoxApi.getBoxList(pid);
    }
    @GetMapping(value = "/currentTemperatureApi/getExcel")
    public void getSbCurrentTemperatureExcel(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                                             @RequestParam(value = "startTime",required = false)String startTime,
                                             @RequestParam(value = "endTime",required = false)String endTime,
                                             HttpServletResponse response)throws Exception{
        List<SbCurrentTemperature> excel = currentTemperatureApi.getExcel(electricityBoxId, startTime, endTime);
        ExcelUtil<SbCurrentTemperature> util = new ExcelUtil<SbCurrentTemperature>(SbCurrentTemperature.class);
        //生成Excel
        AjaxResult a = util.exportExcel(excel, "电箱监测记录");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @PostMapping(value = "/currentTemperatureApi/getEquipmentInformation")
    public JSONObject getEquipmentInformation(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                                              @RequestParam(value = "projectId")Integer projectId)throws Exception{
        return currentTemperatureApi.getEquipmentInformation(electricityBoxId, projectId);
    }
    @PostMapping(value = "/currentTemperatureApi/kanban")
    public JSONObject currentTemperatureApiKanban( @RequestParam(value = "projectId")Integer projectId)throws Exception{
        return currentTemperatureApi.kanban(projectId);
    }
    @PostMapping("/hjBlacklist/getBlacklist")
    public JSONObject getBlacklist(HjBlacklist hjBlacklist, Integer pageNum, Integer pageSize)
    {
        return hjBlacklistApi.list(hjBlacklist);
    }
    @PostMapping("/hjBlacklist/addBlacklist")
    public JSONObject addBlacklist(HjBlacklist hjBlacklist)
    {
        return hjBlacklistApi.addSave(hjBlacklist);
    }
    //260
    @PostMapping("/HjLogging/getLog")
    public JSONObject getLog(@RequestParam(value = "projectId") Integer projectId,
                             @RequestParam(value = "pageNum")Integer pageNum,
                             @RequestParam(value = "pageSize")Integer pageSize,
                             @RequestParam(value = "startTime",required = false) String startTime,
                             @RequestParam(value = "endTime",required = false) String endTime,
                             @RequestParam(value = "userName",required = false) String userName){
        System.out.println(hjLoggingApi.getLog(projectId, startTime, endTime, userName));
        return hjLoggingApi.getLog(projectId, startTime, endTime, userName);
    }
    @PostMapping( "/HjLogging/remove")
    public JSONObject removeLog(@RequestParam(value = "ids")String ids){
        return hjLoggingApi.remove(ids);
    }
    @PostMapping("/appDustEmission/getDustEmission")
    public JSONObject getDustEmission(@RequestParam(value = "sn")String sn,
                                      @RequestParam(value = "dateTime",required = false)String dateTime,
                                      @RequestParam(value = "enTime",required = false)String enTime,
                                      PageDomain pageDomain
    ){
        return appDustEmissionApi.getDustEmission(sn, dateTime,enTime, pageDomain);
    }
    @PostMapping("/appDustEmission/getPM25AndPN10")
    public JSONObject getPM25AndPN10App(@RequestParam(value = "sn") String sn,@RequestParam(value = "projectId")Integer projectId){
        return appDustEmissionApi.getPM25AndPN10(sn, projectId);
    }
    @PostMapping(value = "/appCurrentTemperature/getEquipmentInformation")
    public JSONObject getEquipmentInformation(@RequestParam(value = "electricityBoxId")String electricityBoxId) throws Exception{
        return appCurrentTemperatureApi.getEquipmentInformation(electricityBoxId);
    }
    @PostMapping("/appCurrentTemperature/list")
    public JSONObject appCurrentTemperaturelist(@RequestParam(value = "sn")String sn,
                           @RequestParam(value = "dateTime",required = false)String dateTime,
                           @RequestParam(value = "endTime",required = false)String endTime,
                           @RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize)throws ParseException{
        return appCurrentTemperatureApi.list(sn, dateTime,endTime);
    }
    @GetMapping("/HjLogging/getLoggingExcel")
    public void getLoggingExcel(@RequestParam(value = "projectId") Integer projectId,
                                @RequestParam(value = "startTime",required = false) String startTime,
                                @RequestParam(value = "endTime",required = false) String endTime,
                                @RequestParam(value = "userName",required = false) String userName, HttpServletResponse response)throws Exception{
        List<HjLogging> list = hjLoggingApi.getLoggingExcel(projectId, startTime, endTime, userName);
        ExcelUtil<HjLogging> util = new ExcelUtil<HjLogging>(HjLogging.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "异常记录");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @PostMapping("/folder/createFolder")
    public Map<String,Object> createFolder( HjFolder folder){
        return folderApi.createFolder(folder);
    }
    @PostMapping("/folder/folderList")
    public Map<String,Object> folderList(HjFolder folder){
        return folderApi.folderList(folder);
    }
    @PostMapping("/folder/removeFolder")
    public Map<String,Object> removeFolder(@RequestParam(value = "ids") String ids ){
        return folderApi.removeFolder(ids);
    }
    @PostMapping(value = "/FileApi/getFileList")
    public Map<String,Object> getFileList(@RequestParam(value = "folderId") Integer folderId,
                                          @RequestParam(value = "pageNum") Integer pageNum,
                                          @RequestParam(value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "fileName",required = false) String fileName){
        return fileApi.getFileListclient(folderId, fileName);
    }
    @PostMapping("/FileApi/deleteFile")
    public  Map<String,Object> deleteFile(@RequestParam(value = "ids") String ids){
        return fileApi.deleteFile(ids);
    }
    @RequestMapping(value = "/projectWorkersApi/faceVerify")
    @ResponseBody
    public Map<String, Object> faceVerify(@RequestParam("imageUrl1") String imageUrl1, @RequestParam("imageUrl2") String imageUrl2) {
        return projectWorkersApi.faceVerify(imageUrl1,imageUrl2);
    }
    @RequestMapping(value ="/file/upload")
    public void upload( @RequestParam(value = "file", required = true) MultipartFile[] file,
                        @RequestParam(value = "folderName", required = true) String folderName,
                        HttpServletRequest request, HttpServletResponse response)throws Exception{
        filec.upload(file,folderName,request,response);
    }
    @PostMapping("/FaceAttendanceAPI/getAttendance")
    public Map<String,Object> getAttendance( @RequestBody String json, HttpServletRequest request){
        SbApiFaceAttendance attendance = JSONObject.parseObject(json, SbApiFaceAttendance.class);
        attendance.setIp(request.getRemoteAddr());
        return faceAttendanceAPI.getAttendance(attendance);
    }
    @RequestMapping("/craneApi/selectIndex")
    @ResponseBody
    public Map<String, Object> selectIndex(Integer pid ,String time,String hxzid){
        return pcApi.selectIndex(pid, hxzid);
    }
    @RequestMapping("/craneApi/switchDevice")
    @ResponseBody
    public AjaxResult switchDevice(SbCraneBinding scb){
        return pcApi.switchDevice(scb);
    }
    @RequestMapping("/craneApi/historyRecord")
    @ResponseBody
    public AjaxResult historyRecord(@RequestParam(value = "time",required = false)String time,
                                    @RequestParam(value = "endTime", required = false) String endTime,
                                    @RequestParam(value = "hxzid")String hxzid,
                                    @RequestParam(value = "pageNum")String pageNum,
                                    @RequestParam(value = "pageSize")String pageSize,
                                    @RequestParam(value = "status",required = false)String status){
        return pcApi.historyRecord(time,endTime, hxzid,status);
    }
    @GetMapping("/historyRecordExcel")
    public  void historyRecordExcel(@RequestParam(value = "time")String time,
                                    @RequestParam(value = "endTime",required = false) String endTime,
                                    @RequestParam(value = "hxzid")String hxzid,
                                    HttpServletResponse response)throws Exception{
        List<SbCraneAddrecord> list = pcApi.historyRecordExcel(time,endTime, hxzid);
        ExcelUtil<SbCraneAddrecord> util = new ExcelUtil<SbCraneAddrecord>(SbCraneAddrecord.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "塔吊历史记录");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }
    }
    @RequestMapping("/elevatorApi/selectIndex")
    @ResponseBody
    public AjaxResult selectIndex(Integer pid , String hxzid) {
        return pcElevatorApi.selectIndex(pid, hxzid);
    }
    @RequestMapping("/elevatorApi/historyRecord")
    @ResponseBody
    public AjaxResult elevatorHistoryRecord(@RequestParam(value = "time")String time,
                                            @RequestParam(value = "endTime",required = false)String endTime,
                                            @RequestParam(value = "hxzid")String hxzid,
                                            @RequestParam(value = "status")String status,
                                            @RequestParam(value = "pageNum")String pageNum,
                                            @RequestParam(value = "pageSize")String pageSize){
        return pcElevatorApi.historyRecord(time,endTime, hxzid, status);
    }
    @GetMapping("/elevatorApi/historyRecordExcel")
    public  void elevatorHistoryRecordExcel(@RequestParam(value = "time")String time,
                                            @RequestParam(value = "endTime")String endTime,
                                            @RequestParam(value = "hxzid")String hxzid,
                                            @RequestParam(value = "status",required = false)String status,
                                            HttpServletResponse response)throws Exception{
        List<SbElevatorAddrecord> list = pcElevatorApi.historyRecordExcel(time,endTime,hxzid,status);
        ExcelUtil<SbElevatorAddrecord> util = new ExcelUtil<SbElevatorAddrecord>(SbElevatorAddrecord.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "升降机历史记录");

        //设置下载文件名
        String fileName = URLEncoder.encode((String)a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()){
            file.delete();
        }

    }
    @RequestMapping("/elevatorApi/switchDevice")
    @ResponseBody
    public AjaxResult switchDevice(Integer pid){
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setPid(pid);
        return pcElevatorApi.switchDevice(sbElevatorBinding);
    }
    @GetMapping("/kqbb/getbb")
    public void getbb(HttpServletResponse response,Integer projectId){
        String fileName = "考勤报表";
        String[] columnNames =new String[33];
        columnNames[0]="ID";
        columnNames[1]="姓名";
        long millis = System.currentTimeMillis();//系统毫秒数
        List<BG> bgs = kqbbAPI.getKqbbListBb(projectId);
        for(int i = 0; i<30;i++) {
            millis=millis - (1000 * 60 * 60 * 24);
            Date d = new Date(millis);
            SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
            String rq=sp.format(d);//获取日期
            int i1 = 32 - (i + 1);
            columnNames[i1]=rq;
            List<Kqbb> kqbbList = kqbbAPI.getKqbbList(projectId,rq);
            for(int s = 0; s < bgs.size();s++){
                BG bg =  bgs.get(s);
                for(Kqbb k:kqbbList) {
                    if(k.getEmpId()==Integer.parseInt(bgs.get(s).getId())){
                        k.setManHour( k.getManHour().equals("null") ? BigDecimal.valueOf(Float.parseFloat("0.00")):k.getManHour());
                        switch (i1) {
                            case 31:
                                bg.setTime_30(k.getManHour().toString());
                                break;
                            case 30:
                                bg.setTime_29(k.getManHour().toString());
                                break;
                            case 29:
                                bg.setTime_28(k.getManHour().toString());
                                break;
                            case 28:
                                bg.setTime_27(k.getManHour().toString());
                                break;
                            case 27:
                                bg.setTime_26(k.getManHour().toString());
                                break;
                            case 26:
                                bg.setTime_25(k.getManHour().toString());
                                break;
                            case 25:
                                bg.setTime_24(k.getManHour().toString());
                                break;
                            case 24:
                                bg.setTime_23(k.getManHour().toString());
                                break;
                            case 23:
                                bg.setTime_22(k.getManHour().toString());
                                break;
                            case 22:
                                bg.setTime_21(k.getManHour().toString());
                                break;
                            case 21:
                                bg.setTime_20(k.getManHour().toString());
                                break;
                            case 20:
                                bg.setTime_19(k.getManHour().toString());
                                break;
                            case 19:
                                bg.setTime_18(k.getManHour().toString());
                                break;
                            case 18:
                                bg.setTime_17(k.getManHour().toString());
                                break;
                            case 17:
                                bg.setTime_16(k.getManHour().toString());
                                break;
                            case 16:
                                bg.setTime_15(k.getManHour().toString());
                                break;
                            case 15:
                                bg.setTime_14(k.getManHour().toString());
                                break;
                            case 14:
                                bg.setTime_13(k.getManHour().toString());
                                break;
                            case 13:
                                bg.setTime_12(k.getManHour().toString());
                                break;
                            case 12:
                                bg.setTime_11(k.getManHour().toString());
                                break;
                            case 11:
                                bg.setTime_10(k.getManHour().toString());
                                break;
                            case 10:
                                bg.setTime_9(k.getManHour().toString());
                                break;
                            case 9:
                                bg.setTime_8(k.getManHour().toString());
                                break;
                            case 8:
                                bg.setTime_7(k.getManHour().toString());
                                break;
                            case 7:
                                bg.setTime_6(k.getManHour().toString());
                                break;
                            case 6:
                                bg.setTime_5(k.getManHour().toString());
                                break;
                            case 5:
                                bg.setTime_4(k.getManHour().toString());
                                break;
                            case 4:
                                bg.setTime_3(k.getManHour().toString());
                                break;
                            case 3:
                                bg.setTime_2(k.getManHour().toString());
                                break;
                            case 2:
                                bg.setTime_1(k.getManHour().toString());
                                break;

                        }

                        bg.setCount(bg.getCount()==null?k.getManHour().toString():k.getManHour().doubleValue()+Float.parseFloat(bg.getCount())+"");
                        bgs.set(s,bg);
                    }
                }
            }


        }
        columnNames[32]="出勤总工时";

        // 列名
        // map中的key
        String keys[] = {  "id", "name", "time_1", "time_2", "time_3" , "time_4" , "time_5" , "time_6" , "time_7" , "time_8" , "time_9"
                , "time_10" , "time_11" , "time_12" , "time_13" , "time_14" , "time_15" , "time_16" , "time_17" , "time_18" , "time_19" , "time_20"
                , "time_21" , "time_22" , "time_23" , "time_24", "time_25" , "time_26" , "time_27" , "time_28" , "time_29" , "time_30", "count"   };
        try {
            ExcelUtils.start_download(response, fileName, bgs,columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }


}
    @PostMapping(value = "/appElevatorAddRecord/getElevatorAddRecord")
    public JSONObject getElevatorAddRecord(@RequestParam(value = "deviceId")String deviceId,@RequestParam(value = "projectId")Integer projectId){
        return appElevatorAddRecordApi.getElevatorAddRecord(deviceId,projectId);
    }
    @PostMapping(value = "/appElevatorAddRecord/getElevatorHxzId")
    public JSONObject getElevatorHxzId(@RequestParam(value = "projectId")int projectId){
        return appElevatorAddRecordApi.getElevatorHxzId(projectId);
    }
    @PostMapping(value = "/appElevatorAddRecord/crane")
    public JSONObject appElevatorAddRecordCrane(@RequestParam(value = "pid")Integer projectId) throws ParseException{
        return appElevatorAddRecordApi.crane(projectId);
    }
    @PostMapping(value = "/appCraneAddRecord/getCraneAddRecord")
    public JSONObject getCraneAddRecord(@RequestParam(value = "deviceId")String deviceId,@RequestParam(value = "projectId")Integer projectId){
        return appCraneAddRecordApi.getCraneAddRecord(deviceId,projectId);
    }
    @PostMapping(value = "/appCraneAddRecord/getCraneAddRecordHistory")
    public JSONObject getCraneAddRecordHistory(@RequestParam(value = "deviceId")String deviceId,
                                               @RequestParam(value = "pageNum")Integer pageNum,
                                               @RequestParam(value = "pageSize")Integer pageSize,
                                               @RequestParam(value = "dateTime",required = false)String dateTime,
                                               @RequestParam(value = "endTime",required = false)String endTime
    ){
        return appCraneAddRecordApi.getCraneAddRecordHistory(deviceId, dateTime,endTime);
    }
    @PostMapping(value = "/appCraneAddRecord/getCraneHxzId")
    public JSONObject getCraneHxzId(@RequestParam(value = "projectId")int projectId){
        return appCraneAddRecordApi.getCraneHxzId(projectId);
    }
    @PostMapping(value = "/appCraneAddRecord/kanban")
    public JSONObject kanban(@RequestParam(value = "pid")Integer pid){
        return appCraneAddRecordApi.kanban(pid);
    }
//    @PostMapping(value = "/provider/project/selectListProjectArea")
//    @RequestMapping("/project/selectListProjectArea")
//    public   Map<String, Object> selectListProjectArea(@RequestParam(value = "companyId") Integer companyId,@RequestParam(value = "region") String region) {
//
//        return projectApi.selectListProjectArea(companyId,region);
//    }
    @RequestMapping("/project/getXmzk")
    @ResponseBody
    public JSONObject pkanban(@RequestParam("id") Integer id){
        return projectApi.kanban(id);
    }
    @RequestMapping("/pcCompanyLibrary/selectHjCompanyList")
    public  List<HjCompanyLibrary> selectHjCompanyList(@RequestParam(value = "companyId") Integer companyId){

        return pcCompanyLibraryApi.selectHjCompanyList(companyId);
    }
    @RequestMapping("/project/day")
    @ResponseBody
    public JSONObject day(@RequestParam("id") Integer id)throws ParseException{
        return projectApi.day(id);
    }
    @RequestMapping(value = "/attendanceRecordApi/TheWorkersWork")
    @ResponseBody
    public JSONObject TheWorkersWork(@RequestParam("projectId") Integer projectId){
        return attendanceRecordApi.TheWorkersWork(projectId);
    }
    @PostMapping(value = "/hireApi/edit")
    @ResponseBody
    public JSONObject updateRadius(SbArea sbArea){
        return sbHireApi.updateRadius(sbArea);
    }
    @RequestMapping(value = "/project/selectProjectArea")
    public Map<String, Object> selectProjectArea(@RequestParam(value = "companyId") Integer companyId, @RequestParam(value = "region") String region)
    {
        return projectApi.selectProjectArea(companyId,region);
    }
    @RequestMapping(value = "/project/selectProjectAreS")
    public Map<String, Object> selectProjectAreaS(@RequestParam(value = "companyId") Integer companyId)
    {
        return  projectApi.selectProjectAreas(companyId);
    }
    @RequestMapping("/project/selectProjects")
    public JSONObject selectProjects(HjProject hjProject){
        return projectApi.selectProject(hjProject);
    }
    @RequestMapping("/project/selectProjectRegion")
    public JSONObject selectProjectRegion(HjProject hjProject){
        return projectApi.selectProjectRegion(hjProject);
    }
    @RequestMapping(value = "/project/selectHjProject")
    public JSONObject selectHjProject(HjProject hjProject){
        return projectApi.projectSelect(hjProject);
    }
    @PostMapping("/Node/selectCruxZhNode")
    @ResponseBody
    public AjaxResult selectCruxZhNode(ZhNode node) {

        return AjaxResult.success(nodeApi.selectCruxZhNode(node));
    }
    @PostMapping("/Node/selectWarningZhNode")
    @ResponseBody
    public AjaxResult selectWarningZhNode(ZhNode node) {
        return AjaxResult.success(nodeApi.selectWarningZhNode(node));
    }
    @PostMapping("/Node/selectBeginZhNode")
    @ResponseBody
    public AjaxResult selectBeginZhNode(ZhNode node) {
        return AjaxResult.success(nodeApi.selectBeginZhNode(node));
    }
    @PostMapping("/Node/selectEndZhNode")
    @ResponseBody
    public AjaxResult selectEndZhNode(ZhNode node) {
        return AjaxResult.success(nodeApi.selectEndZhNode(node));
    }
    @PostMapping("/Node/selectZhProgressPlan")
    @ResponseBody
    public AjaxResult selectZhProgressPlan(ZhProgressPlan zhProgressPlan) {
        return AjaxResult.success(nodeApi.selectZhProgressPlan(zhProgressPlan));
    }
    @PostMapping("/Node/editNodeWithProgress")
    @ResponseBody
    public AjaxResult editNodeWithProgress(ZhProgressNode zhProgressNode) {
        return AjaxResult.success(nodeApi.editNodeWithProgress(zhProgressNode));
    }
    @PostMapping("/Node/selectZhNodeProgressList")
    @ResponseBody
    public AjaxResult selectZhNodeProgressList(ZhProgressNode zhProgressNode) {
        return AjaxResult.success(nodeApi.selectZhNodeProgressList(zhProgressNode));
    }
    @PostMapping("/Node/selectAddZhNodeList")
    public AjaxResult selectAddZhNodeList(@RequestParam(value = "projectId") Integer projectId) {
        return AjaxResult.success(nodeApi.selectAddZhNodeList(projectId));
    }
    @RequestMapping(value = "/project/selectAreaProjectList")
    public AjaxResult selectAreaProjectList(@RequestParam(value = "companyId") Integer companyId, @RequestParam(value = "region") String region){
        return projectApi.selectAreaProjectList(companyId,region);
    }
    @GetMapping("/Node/exportZhProgressPlan")
    public void exportZhProgressPlan(Integer progressId, HttpServletResponse response) throws Exception {
        System.out.println("导出Excel：");
        List<ZhNodePc> list = nodeApi.exportZhProgressPlan(progressId);
        ExcelUtil<ZhNodePc> util = new ExcelUtil<ZhNodePc>(ZhNodePc.class);
        //生成Excel
        AjaxResult a = util.exportExcel(list, "节点计划");
        //设置下载文件名
        String fileName = URLEncoder.encode((String) a.get("msg"), "UTF-8");
        File file = new File(Utils.getPath(), (String) a.get("msg"));
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            file.delete();
        }
    }
    @PostMapping("/Node/selectZhNodeList")
    @ResponseBody
    public AjaxResult selectZhNodeList(ZhNode node) {
        return AjaxResult.success(nodeApi.selectZhNodeList(node));
    }
    @PostMapping("/Node/selectZhProgressNodeList")
    @ResponseBody
    public AjaxResult selectZhProgressNodeList(ZhProgressNode zhProgressNode) {
        return AjaxResult.success(nodeApi.selectZhProgressNodeList(zhProgressNode));
    }
    @PostMapping("/Node/selectZhProgressPlanList")
    @ResponseBody
    public JSONObject selectZhProgressPlanList(ZhProgressPlan zhProgressPlan) {
        return nodeApi.selectZhProgressPlanList(zhProgressPlan);
    }
    @PostMapping("/Node/selectZhPreposeList")
    @ResponseBody
    public AjaxResult selectZhPreposeList(ZhPrepose zhPrepose) {
        return AjaxResult.success(nodeApi.selectZhPreposeList(zhPrepose));
    }
    @PostMapping("/Node/addNode")
    @ResponseBody
    public AjaxResult addNode(ZhNode zhNode) {
        return AjaxResult.success(nodeApi.addNode(zhNode));
    }
    @PostMapping("/Node/addProgressPlan")
    @ResponseBody
    public AjaxResult addProgressPlan(ZhProgressPlan zhProgressPlan) {
        return AjaxResult.success(nodeApi.addProgressPlan(zhProgressPlan));
    }
    @PostMapping("/Node/remoProgressPlan")
    public AjaxResult removeProgressPlan(int id) {
        return AjaxResult.success(nodeApi.removeProgressPlan(id));
    }
    @PostMapping("/Node/editNode")
    @ResponseBody
    public AjaxResult editNode(ZhNode zhNode) {
        return AjaxResult.success(nodeApi.editNode(zhNode));
    }
    @PostMapping("/Node/removeNode")
    @ResponseBody
    public AjaxResult removeNode(int id) {
        return AjaxResult.success(nodeApi.removeNode(id));
    }
    @PostMapping("/Node/editProgressPlan")
    @ResponseBody
    public AjaxResult editProgressPlan(ZhProgressPlan zhProgressPlan) {
        return AjaxResult.success(nodeApi.editProgressPlan(zhProgressPlan));
    }
    @PostMapping("/Node/addPrepose")
    @ResponseBody
    public AjaxResult addPrepose(ZhPrepose zhPrepose) {
        return AjaxResult.success(nodeApi.addPrepose(zhPrepose));
    }
    @PostMapping("/Node/removePreposeById")
    @ResponseBody
    public AjaxResult removePreposeById(int id) {
        return AjaxResult.success(nodeApi.removePreposeById(id));
    }
    @PostMapping("/Node/addProgressNode")
    @ResponseBody
    public AjaxResult addProgressNode(ZhProgressNode zhProgressNode) {
        return AjaxResult.success(nodeApi.addProgressNode(zhProgressNode));
    }
    @PostMapping("/Node/aremoveProgressNode")
    @ResponseBody
    public AjaxResult removeaddProgressNode(int id) {
        return AjaxResult.success(nodeApi.removeaddProgressNode(id));
    }
    @PostMapping(value = "/hs/person")
    public String addPerson(@RequestBody String json)throws Exception{
        System.out.println(json);
        return hsFaceApi.addPerson(json);
    }
    @PostMapping(value = "/hsRecord/setRecord")
    public void  setRecord(@RequestBody String json)throws Exception{
        hsFaceRecordApi.setRecord(json);
    }
    @PostMapping(value = "/attendanceDeviceApi/insertAttendanceDevice")
    public AjaxResult insertAttendanceDevice(HjAttendanceDevice hjAttendanceDevice) throws Exception {
        return attendanceDeviceApi.insertAttendanceDevice(hjAttendanceDevice);
    }
    @PostMapping(value = "/attendanceDeviceApi/updateAttendanceDevice")
    public AjaxResult updateAttendanceDevice(HjAttendanceDevice hjAttendanceDevice){

        return attendanceDeviceApi.updateAttendanceDevice(hjAttendanceDevice);
    }
    @PostMapping(value = "/attendanceDeviceApi/selectAttendanceDevice")
    public TableDataInfo selectAttendanceDevice(HjAttendanceDevice hjAttendanceDevice, Integer pageNum, Integer pageSize) throws Exception {
        return attendanceDeviceApi.selectAttendanceDevice(hjAttendanceDevice);
    }
    @PostMapping(value = "/HjGhformworktApi/selectStructure")
    public AjaxResult selectStructure(@RequestParam("projectId")Integer projectId){
        return hjGhformworktApi.selectStructure(projectId);
    }
    @PostMapping(value = "/HjGhformworktApi/selectDisplay" )
    public AjaxResult selectDisplay(@RequestParam(value = "structureId")Integer structureId){

        return hjGhformworktApi.selectDisplay(structureId);
    }
    @PostMapping(value = "/HjGhformworktApi/getFactorList" )
    public AjaxResult getFactorList(@RequestParam(value = "structureId")Integer structureId, @RequestParam(value = "displayId") Integer displayId){

        return hjGhformworktApi.getFactorList(structureId,displayId);
    }
    @PostMapping(value = "/HjGhformworktApi/getFactorData" )
    public AjaxResult getFactorDatas(@RequestParam(value = "factorId") Integer factorId,
                                     @RequestParam(value = "date") String date,
                                     @RequestParam(value = "endTime") String endTime,
                                     @RequestParam(value = "pageSize")Integer pageSize,
                                     @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);

        return hjGhformworktApi.getFactorData(factorId,date,endTime,pageDomain);
    }
    @PostMapping(value = "/HjGhformworktApi/selectUserAlarms" )
    public AjaxResult selectUserAlarms(@RequestParam(value = "structureId")Integer structureId,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "endTime") String endTime,
                                       @RequestParam(value = "pageSize")Integer pageSize,
                                       @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);

        return hjGhformworktApi.selectUserAlarms(structureId,date,endTime,pageDomain);

    }
    @PostMapping(value = "/HjGhformworktApi/getFactorDataT" )
    public AjaxResult getFactorDataTfor(@RequestParam(value = "factorId") Integer factorId,
                                        @RequestParam(value = "startTime") String startTime,
                                        @RequestParam(value = "endTime")String endTime,
                                        @RequestParam(value = "pageSize")Integer pageSize,
                                        @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        return hjGhformworktApi.getFactorDataT(factorId,startTime,endTime,pageDomain);
    }
    @PostMapping(value = "/HjGhformworktApi/getParmeterAvg" )
    public AjaxResult getParmeterAvg(@RequestParam(value = "displayId") Integer displayId,
                                     @RequestParam(value = "factorId") Integer factorId){

        return hjGhformworktApi.getFactorDataInfo(displayId,factorId);
    }
    @PostMapping(value = "/HjGhformworktApi/selectSpecial" )
    public List selectSpecial(@RequestParam(value = "factorId")Integer factorId,@RequestParam(value = "param")String param,@RequestParam(value = "date") String date){
        return hjGhformworktApi.selectSpecial(factorId,param,date);
    }
    @PostMapping(value = "/HjGhformworktApi/selectSpecialS" )
    public JSONArray selectSpecialS(@RequestParam(value = "displayId") Integer displayId,
                                    @RequestParam(value = "factorId") Integer factorId,
                                    @RequestParam(value = "startTime") String startTime,
                                    @RequestParam(value = "endTime")String endTime) {
        return hjGhformworktApi.getFactorDataT(displayId,factorId,startTime,endTime);
    }
    @PostMapping(value = "/HjGhformworktApi/statisticsAlertor" )
    public AjaxResult statisticsAlertor(@RequestParam(value = "structureId")Integer structureId){
        return hjGhformworktApi.statisticsAlertor(structureId);
    }
    @PostMapping(value = "/HjGhformworktApi/selectUserAlarmsByFactor" )
    public AjaxResult selectUserAlarmsByFactor(@RequestParam(value = "factorName")String factorName, @RequestParam(value = "date") String date){

        return hjGhformworktApi.selectUserAlarmsByFactor(factorName,date);
    }
    @PostMapping(value = "/hjDeeppit/insertDeeppit")
    public AjaxResult insertDeeppit(@RequestBody SbProjectDeeppitStructures sPD){
        return hjDeeppitApi.insertDeeppit(sPD);
    }
    @PostMapping(value = "/hjDeeppit/selectStructure")
    public AjaxResult selectStructures(@RequestParam("projectId")Integer projectId){

        return hjDeeppitApi.selectStructure(projectId);
    }
    @PostMapping(value = "/hjDeeppit/selectDisplay" )
    public AjaxResult selectDisplays(@RequestParam(value = "structureId")Integer structureId){

        return hjDeeppitApi.selectDisplay(structureId);
    }
    @PostMapping(value = "/hjDeeppit/getFactorList" )
    public AjaxResult getFactorLists(@RequestParam(value = "structureId")Integer structureId, @RequestParam(value = "displayId") Integer displayId){

        return hjDeeppitApi.getFactorList(structureId,displayId);
    }
    @PostMapping(value = "/hjDeeppit/getFactorData" )
    public net.sf.json.JSONObject getFactorData(@RequestParam(value = "factorId") Integer factorId,
                                                @RequestParam(value = "date") String date,
                                                @RequestParam(value = "endTime") String endTime,
                                                @RequestParam(value = "pageSize")Integer pageSize,
                                                @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        return hjDeeppitApi.getFactorData(factorId,date,endTime,pageDomain);
    }
    @PostMapping(value = "/hjDeeppit/getParmeterAvg" )
    public AjaxResult getFactorDataInfo(@RequestParam(value = "displayId") Integer displayId, @RequestParam(value = "factorId") Integer factorId){

        return hjDeeppitApi.getFactorDataInfo(displayId,factorId);
    }
    @PostMapping(value = "/hjDeeppit/selectUserAlarms" )
    public AjaxResult selectUserAlarmsdeppit(@RequestParam(value = "structureId")Integer structureId,
                                             @RequestParam(value = "date")String date,
                                             @RequestParam(value = "endTime",required = false) String endTime,
                                             @RequestParam(value = "pageSize")Integer pageSize,
                                             @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        return hjDeeppitApi.selectUserAlarms(structureId,date,endTime,pageDomain);
    }
    @PostMapping(value = "/hjDeeppit/statisticsAlertor" )
    public AjaxResult statisticsAlertordeppit(@RequestParam(value = "structureId")Integer structureId){

        return hjDeeppitApi.statisticsAlertor(structureId);
    }
    @PostMapping(value = "/hjDeeppit/getFactorDataT" )
    public AjaxResult getFactorDataTdeepit(@RequestParam(value = "factorId") Integer factorId, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime")String endTime, @RequestParam(value = "pageSize")Integer pageSize,
                                           @RequestParam(value = "pageNum")Integer pageNum){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        return hjDeeppitApi.getFactorDataT(factorId,startTime,endTime,pageDomain);
    }
    @PostMapping(value = "/hjDeeppit/selectSpecial" )
    public List selectSpecialdeppit(@RequestParam(value = "factorId")Integer factorId, @RequestParam(value = "param")String param, @RequestParam(value = "date") String date){
        return hjDeeppitApi.selectSpecial(factorId,param,date);
    }
    @PostMapping(value = "/hjDeeppit/selectSpecialS" )
    public JSONArray getFactorDataT(@RequestParam(value = "displayId") Integer displayId, @RequestParam(value = "factorId") Integer factorId, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime")String endTime) {
        return hjDeeppitApi.getFactorDataT(displayId,factorId,startTime,endTime);
    }
    @PostMapping(value = "/hjDeeppit/selectUserAlarmsByFactor" )
    public AjaxResult selectUserAlarmsByFactors(@RequestParam(value = "factorName")String factorName, @RequestParam(value = "date") String date){

        return hjDeeppitApi.selectUserAlarmsByFactor(factorName,date);
    }
    @PostMapping(value = "/ys/setRecord")
    public String setRecordYs(@RequestBody String json) throws  Exception{
        return ysRecordApi.setRecordYs(json);
    }
    @RequestMapping("/project/selectProjectMsg")
    public AjaxResult selectProjectMsg(@RequestParam(value = "projectId") Integer projectId){
        return projectApi.selectProjectMsg(projectId);
    }
    @PostMapping(value = "/projectImage/selectProjectImageList")
    @ResponseBody
    public AjaxResult selectProjectImageList(HjProjectImage hjProjectImage) {
        AjaxResult jsonArray = projectImageApi.selectProjectImageList(hjProjectImage);
        return jsonArray;
    }
    @PostMapping(value = "/projectImage/insertProjectImage")
    public  Map<String, Object> insertProjectImage(HjProjectImage hjProjectImage, MultipartFile[] file) {
        Map<String, Object> result = null;
        try {
            result =  projectImageApi.insertProjectImage(hjProjectImage,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @PostMapping(value = "/projectImage/updateProjectImage")
    public AjaxResult updateProjectImage(HjProjectImage hjProjectImage, @RequestParam(value = "file") MultipartFile file) {
        return projectImageApi.updateHjProjectImage(hjProjectImage,file);
    }
    @PostMapping(value = "/projectImage/removeProjectImage")
    public AjaxResult removeProjectImage(@RequestParam(value = "id") Integer id) {
        return projectImageApi.remove(id);
    }
    @GetMapping("/sbGroupTalkback/ftpDownload")
    public void ftpDownload(String ftpPath,String user,String startTime,String name,String endTime, HttpServletResponse response) throws  Exception{

        String s = sbGroupTalkbackApi.ftpDownload(ftpPath,user,startTime,name,endTime);
        if(!"-1".equals(s)){
            URL url = new URL(s);
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

                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name+startTime+"_"+endTime+".zip", "UTF-8"));
                IOUtils.copy(inputStream, outputStream);

                //删除文件
                AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(),"hujiang", name+startTime+"_"+endTime+".zip");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("/sbGroupTalkback/getAccountList")
    public List<SbGroupTalkback> getAccountList(SbGroupTalkback sbGroupTalkback){

        return sbGroupTalkbackApi.getAccountList(sbGroupTalkback);
    }
    @PostMapping("/sbAccountTalkback/getAccountListPage")
    public AjaxResult getAccountListPage(Integer cpid, String isIdType, SbAccountTalkback sbAccountTalkback, PageDomain pageDomain){
//                System.out.println(pageSize+","+pageNum);
        return sbAccountTalkbackApi.getAccountListPage(cpid,isIdType,sbAccountTalkback,pageDomain.getPageSize(),pageDomain.getPageNum());
    }
    @PostMapping("/ProjectVideoPresetApi/insertPreset")
    public AjaxResult insertPreset(Integer pid, String deviceSerial) throws Exception{

        return projectVideoPresetApi.insertPreset(pid,deviceSerial);
    }
    @PostMapping("/ProjectVideoPresetApi/clearPreset")
    public AjaxResult clearPreset(Integer pid, String deviceSerial, Integer  index) throws Exception {

        return  projectVideoPresetApi.clearPreset(pid,deviceSerial,index);
    }
    @PostMapping("/ProjectVideoPresetApi/selectPreset")
    public AjaxResult selectPreset(SbProjectVideoPreset sp)  {

        return projectVideoPresetApi.selectPreset(sp);
    }
    @PostMapping("/ProjectVideoPresetApi/callPreset")
    public void callPreset(Integer pid, String deviceSerial, Integer  index) throws Exception {

        projectVideoPresetApi.callPreset(pid,deviceSerial,index);
    }
    @PostMapping(value = "/groupTitleApi/getTitle")
    public AjaxResult getTitle(SbGroupTitle groupTitle){

        return groupTitleApi.getTitle(groupTitle);
    }
    @PostMapping(value = "/equipment")
    public JSONObject equipment(@RequestParam(value = "cid")int cid)throws ParseException {
        return sbGroupApi.equipment(cid);
    }
    @PostMapping(value = "/marginAlarm")
    public JSONObject marginAlarm(@RequestParam(value = "cid")int cid){
        return sbGroupApi.marginAlarm(cid);
    }

    @PostMapping(value = "/lifterAlarm")
    public JSONObject lifterAlarm(@RequestParam(value = "cid")int cid){
        return sbGroupApi.lifterAlarm(cid);
    }

    @PostMapping(value = "/company")
    public AjaxResult company(@RequestParam(value = "cid")Integer cid){
        return sbGroupApi.company(cid);
    }
    @PostMapping(value = "/totalList")
    public AjaxResult totalList(@RequestParam(value = "cid")Integer cid){
        return sbGroupApi.totalList(cid);
    }

    @PostMapping(value = "/projectList")
    public AjaxResult projectList(@RequestParam(value = "cid")Integer cid){
        return sbGroupApi.projectList(cid);
    }
    @PostMapping(value = "/count")
    public AjaxResult count(@RequestParam(value = "cid")Integer cid){
        return sbGroupApi.count(cid);
    }
    @PostMapping(value = "/clickCard")
    public AjaxResult clickCard(@RequestParam(value = "cid")Integer cid, @RequestParam(value = "start")Long startTime, @RequestParam(value = "end")Long endTime){
        return sbGroupApi.clickCard(cid,startTime,endTime);
    }
    @PostMapping(value = "/plateList")
    public AjaxResult plateList(@RequestParam(value = "cid")Integer cid, @RequestParam(value = "start")Long startTime, @RequestParam(value = "end")Long endTime){
        return sbGroupApi.plateList(cid,startTime,endTime);
    }
    @PostMapping(value = "/environmentList")
    public AjaxResult environmentList(@RequestParam(value = "cid")Integer cid){
        return sbGroupApi.environmentList(cid);
    }


    //    @PostMapping(value = "/provider/instructions/getPWD")
//    @RequestMapping("/instructions/lowerHair")
//    public AjaxResult lowerHair(@RequestParam(value = "deviceNo") String deviceNo,@RequestParam(value = "pid") String pid)throws  Exception{
//
//        //拿到操作设备的句柄
//        WinNT.HANDLE ghandle = handleMap.get(deviceNo);
//        FaceGateApi instance=Client.instance;
//        if(ghandle!=null){
//            HjDeviceProjectworkers hd;
//            //不在设备里的人员信息
//            List<HjProjectWorkers> hpwList=hqFaceApi.getPWD(deviceNo,pid);
//
//
//            for(HjProjectWorkers hpw: hpwList){
//                int nRet=    PersonOperate.addPerson(instance,ghandle,hpw);
//
//                //成功的话需要将该人员和设备保存进设备人员表
//                if(nRet == Constant.RET_TYPE_E.RET_SUCCESS){
//                    hd=new HjDeviceProjectworkers();
//                    hd.setDeviceNo(deviceNo);
//                    hd.setProjectWorkersId(hpw.getId());
//                    int i= hqFaceApi.setPWD(hd);
//                    if(i!=1){
//                        PersonOperate.deletePerson(instance,ghandle,hpw.getId());//保存失败则删除设备里已存的人脸
//                    }
//                    System.out.println("新增人员名单成功");
//                }
//                File file=new File(ResourceUtils.getURL("classpath:").getPath()+"static/upload/img/");
//                delFile(file);
//            }
//
//        }else{
//            return AjaxResult.error("操作失败，该设备不在线");
//        }
//        return AjaxResult.success("设备添加人脸成功");
//    }
//    static void delFile(File file) {
//        File[] files = file.listFiles();
//        for (File f : files) {
//            f.delete();
//        }
//    }
//    @RequestMapping(value = "/instructions/clean")
//    public AjaxResult clean(@RequestParam(value = "deviceNo") String deviceNo){
//        HANDLE ghandle = handleMap.get(deviceNo);
//        FaceGateApi instance=Client.instance;
//        if(ghandle!=null){
//            List<HjDeviceProjectworkers> hdpwList=hqFaceApi.clean(deviceNo);
//            for(HjDeviceProjectworkers hdpw: hdpwList){
//                int nRet=   PersonOperate.deletePerson(instance,ghandle,hdpw.getProjectWorkersId());
//                if(nRet == Constant.RET_TYPE_E.RET_SUCCESS){
//                    hqFaceApi.deleteId(hdpw.getId());
//                }
//            }
//        }
//        return AjaxResult.success("清除人脸成功");
//    }
    @RequestMapping("/constructionCompanyApi/insertConstructionCompany")
    public Map<String,Object> insertConstructionCompany( HjConstructionCompany hjConstructionCompany, Integer projectId)throws Exception
    {
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",projectId);
        for (Field f : hjConstructionCompany.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if(f.get(hjConstructionCompany)!=null){
                map.put(f.getName(), String.valueOf(f.get(hjConstructionCompany)));
            }
        }
        return constructionCompanyApi.insertConstructionCompany(hjConstructionCompany,projectId);
    }
    @RequestMapping("/constructionCompanyApi/updateConstructionCompany")
    public Map<String,Object> updateConstructionCompany( HjConstructionCompany hjConstructionCompany)throws Exception
    {
        return constructionCompanyApi.updateConstructionCompany(hjConstructionCompany);
     }
    /**
     * 查询参建单位列表
     * @param projectId 项目id
     * @param param 项目名或简称
     * @return
     */
    @RequestMapping("/constructionCompanyApi/selectConstructionCompanyList")
    public  Map<String,Object> selectHjConstructionCompanyListTwo(String param ,String suid, Integer projectId, PageDomain pageDomain)throws Exception
    {
        if(projectId==null){
            return AjaxResult.error(-1,"projectId为空");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("param",param);
        map.put("projectId",projectId);
        map.put("suid",suid);
        return constructionCompanyApi.selectHjConstructionCompanyListTwo(param,suid,projectId);
     }
    @RequestMapping("/constructionCompanyApi/selectConstructionCompanyId")
    public Map<String,Object> selectHjConstructionCompanyIds(Integer id)throws Exception
    {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return constructionCompanyApi.selectHjConstructionCompanyIds(id);

    }
    @RequestMapping("/constructionCompanyApi/deleteConstructionCompanyIds")
    @ResponseBody
    public Map<String, Object>  deleteHjConstructionCompanyIds(String ids)throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        return constructionCompanyApi.deleteHjConstructionCompanyIds(ids);
    }
    @PostMapping("/pc/projectWorkersApi/list")
    public Map<String, Object> list(HjProjectWorkers hjProjectWorkers, PageDomain pageDomain) throws Exception {
        return pcProjectWorkersApi.list(hjProjectWorkers);
      }
    /**
     * 查询项目工人列表
     */
    @PostMapping("/pc/projectWorkersApi/quarantineList")
    public Map<String, Object> quarantineList(HjProjectWorkers hjProjectWorkers, PageDomain pageDomain) throws Exception {
        return pcProjectWorkersApi.quarantineList(hjProjectWorkers);
    }
    @PostMapping(value = "/pc/projectWorkersApi/queryProjectWorkers")
    public  Map<String,Object> queryProjectWorkers(Integer id)throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return pcProjectWorkersApi.queryProjectWorkers(id);
    }
    @PostMapping("/edit")
    public Map<String, Object> editSave(HjProjectWorkers hjProjectWorkers)throws Exception
    {
        System.out.println(hjProjectWorkers);
        hjProjectWorkers.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return pcProjectWorkersApi.editSave(hjProjectWorkers);
    }
    @RequestMapping(value = "/projectWorkersApi/getAliOcrIdCard")
    @ResponseBody
    public Map<String, Object> getAliOcrIdcard(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "configStr", required = true) String configStr
    ) throws Exception {
        return projectWorkersApi.getAliOcrIdcard(file,configStr);
    }
    @RequestMapping("/attendanceRecordApi/insertAdministration")
    @ResponseBody
    public Map<String, Object> insertAdministration(HjAttendanceRecord hjAttendanceRecord,
                                                     MultipartFile file)
    {String direction=hjAttendanceRecord.getDirection();
        String deviceType=hjAttendanceRecord.getDeviceType();
        String deviceSn=hjAttendanceRecord.getDeviceSn();
        if(direction!=null) {
            hjAttendanceRecord.setDirection(direction.indexOf(",") >= 0 ? direction.substring(0, direction.indexOf(",")) : direction);
        }
        if(deviceSn!=null){
        hjAttendanceRecord.setDeviceSn(deviceSn.indexOf(",")>=0?deviceSn.substring(0,deviceSn.indexOf(",")):deviceSn);}
        if(deviceType!=null){
        hjAttendanceRecord.setDeviceType(deviceType.indexOf(",")>=0?deviceType.substring(0,deviceType.indexOf(",")):deviceType);}
        return attendanceRecordApi.insertAdministration(hjAttendanceRecord,file);
    }
    @RequestMapping(value = "/projectWorkersApi/queryWitnessComparison")
    @ApiOperation("人证对比")
    @ResponseBody
    public Map<String, Object> queryWitnessComparison(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "url", required = true) String url
    ) throws Exception {
        return projectWorkersApi.queryWitnessComparison(file,url);
    }
    @RequestMapping("/pcCompanyLibrary/companyLibraryList")
    @ResponseBody
    public AjaxResult list( HjCompanyLibrary hjCompanyLibrary,Integer companyId)
    {
        return pcCompanyLibraryApi.list(hjCompanyLibrary,companyId);
    }
    @RequestMapping(value = "/system/computer/querySystemUser")
    public Map<String, Object> querySystemUser( UserParam userParam) {
        return pc_systrmUserApi.querySystemUser(userParam);
    }
    @RequestMapping("/project/selectProjectList")
    public AjaxResult list( HjProject hjProject, Integer cid) {
        return projectApi.list(hjProject,cid);
    }
    @PostMapping(value = "/inOutKanBan/selectWorkerList")
    public AjaxResult selectWorkerList(Integer pid) {
        return inOutKanBan.selectWorkerList(pid);
    }
    @PostMapping(value = "/inOutKanBan/selectManagerList")
    public AjaxResult selectManagerList(Integer pid) {
        return inOutKanBan.selectManagerList(pid);
    }
    @PostMapping(value = "/inOutKanBan/selectPicture")
    public AjaxResult selectPicture(Integer pid) {
        return inOutKanBan.selectPicture(pid);
    }
    @PostMapping("/searchProjectList")
    public AjaxResult searchProjectList( Integer cid,  String name){
        return  sbGroupApi.searchProjectList(cid,name);
    }

    /**
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 查询所有年龄
     * @Date 2020/3/24 11:21
     **/
    @RequestMapping("/age/limitAgeList")
    public AjaxResult limitAgeList(@RequestParam String pid) {
        return hjAgeApi.limitAgeList(pid);
    }

    /**
     * @param pid
     * @param age
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 插入年龄
     * @Date 2020/3/24 12:56
     **/
    @RequestMapping("/age/addForbidAge")
    public AjaxResult addForbidAge(@RequestParam String pid, @RequestParam Integer age) {
        return hjAgeApi.addForbidAge(pid, age);
    }

    /**
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 删除年龄
     * @Date 2020/3/24 13:09
     **/
    @RequestMapping("/age/deleteForbidAge")
    public AjaxResult deleteForbidAge(@RequestParam Integer id) {
        return hjAgeApi.deleteForbidAge(id);
    }

    /**
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 修改年龄状态
     * @Date 2020/3/24 13:13
     **/
    @RequestMapping("/age/forbidAge")
    public AjaxResult forbidAge(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter) {
        return hjAgeApi.forbidAge(id, pid, enter);
    }

    /**
     * @Author xieya
     * @Description 查询所有省和市
     * @Date 2020/3/25 12:10
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/hjarea/selectAllProvinceAndCity")
    public AjaxResult selectAllProvinceAndCity(){
        return hjEpidemicSituationApi.selectAllProvinceAndCity();
    }

    /**
     * @Author xieya
     * @Description 查询所有疫情城市
     * @Date 2020/3/21 16:59
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/hjarea/areaSettingList")
    public AjaxResult areaSettingList(@RequestParam String pid){
        return hjEpidemicSituationApi.areaSettingList(pid);
    }

    /**
     * @Author xieya
     * @Description 查询选中的疫情省和城市 存入数据库
     * @Date 2020/3/21 18:03
     * @param ids
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping(value = "/hjarea/addAreaSetting")
    public AjaxResult addAreaSetting(@RequestParam String ids, @RequestParam String pid){
        return hjEpidemicSituationApi.addAreaSetting(ids, pid);
    }

    /**
     * @Author xieya
     * @Description
     * @Date 删除疫情城市数据 17:30
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/hjarea/deleteAreaSetting")
    public AjaxResult deleteAreaSetting(@RequestParam Integer id, @RequestParam String pid) {
        return hjEpidemicSituationApi.deleteAreaSetting(id, pid);
    }

    @RequestMapping("/hjarea/forbidAreaSetting")
    public AjaxResult forbidAreaSetting(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter) {
        return hjEpidemicSituationApi.forbidAreaSetting(id, pid, enter);
    }

    /**
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 查询温度列表
     * @Date 2020/3/24 13:48
     **/
    @RequestMapping("/temperature/temperatureList")
    public AjaxResult temperatureList(@RequestParam String pid) {
        return hjTemperatureApi.temperatureList(pid);
    }

    /**
     * @param pid
     * @param temperature
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 新增
     * @Date 2020/3/24 13:56
     **/
    @RequestMapping("/temperature/addTemperature")
    public AjaxResult addTemperature(@RequestParam String pid, @RequestParam String temperature) {
        return hjTemperatureApi.addTemperature(pid, temperature);
    }

    /**
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 删除
     * @Date 2020/3/24 13:57
     **/
    @RequestMapping("/temperature/deleteTemperature")
    public AjaxResult deleteTemperature(@RequestParam Integer id) {
        return hjTemperatureApi.deleteTemperature(id);
    }

    /**
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     * @Author xieya
     * @Description 修改状态
     * @Date 2020/3/24 13:57
     **/
    @RequestMapping("/temperature/forbidTemperatures")
    public AjaxResult forbidTemperatures(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter) {
        return hjTemperatureApi.forbidTemperatures(id, pid, enter);
    }
}
