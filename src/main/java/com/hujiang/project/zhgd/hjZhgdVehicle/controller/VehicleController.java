package com.hujiang.project.zhgd.hjZhgdVehicle.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.*;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import com.hujiang.project.zhgd.hujiangGroup.service.HujiangGroupService;
import com.hujiang.project.zhgd.secretkey.domain.Secretkey;
import com.hujiang.project.zhgd.secretkey.service.ISecretkeyService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import com.hujiang.project.zhgd.vehicleEquipment.service.IVehicleEquipmentService;
import com.hujiang.project.zhgd.vehicleImg.domain.VehicleImg;
import com.hujiang.project.zhgd.vehicleImg.service.IVehicleImgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 车牌数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-20
 */
@RestController
@RequestMapping("/zhgd/vehicle")
public class VehicleController extends BaseController
{
    private String prefix = "zhgd/vehicle";

	@Autowired
	private IVehicleService vehicleService;

	@Autowired
    private HujiangGroupService hujiangGroupService;

	@Autowired
	private IVehicleEquipmentService vehicleEquipmentService;

	@Autowired
	private IVehicleImgService vehicleImgService;

	@Autowired
	private ISecretkeyService secretkeyService;

	@RequiresPermissions("zhgd:vehicle:view")
	@GetMapping()
	public String vehicle()
	{
	    return prefix + "/vehicle";
	}

	/**
	 * 查询车牌数据列表
	 */
	@RequiresPermissions("zhgd:vehicle:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Vehicle vehicle)
	{
		startPage();
        List<Vehicle> list = vehicleService.selectVehicleList(vehicle);
		return getDataTable(list);
	}


	/**
	 * 导出车牌数据列表
	 */
	@RequiresPermissions("zhgd:vehicle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Vehicle vehicle)
    {
    	List<Vehicle> list = vehicleService.selectVehicleList(vehicle);
        ExcelUtil<Vehicle> util = new ExcelUtil<Vehicle>(Vehicle.class);
        return util.exportExcel(list, "vehicle");
    }

	/**
	 * 新增车牌数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 新增保存车牌数据
	 */
	@RequiresPermissions("zhgd:vehicle:add")
	@Log(title = "车牌数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Vehicle vehicle)
	{
		return toAjax(vehicleService.insertVehicle(vehicle));
	}

	/**
	 * 修改车牌数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Vehicle vehicle = vehicleService.selectVehicleById(id);
		mmap.put("vehicle", vehicle);
	    return prefix + "/edit";
	}

	/**
	 * 修改保存车牌数据
	 */
	@RequiresPermissions("zhgd:vehicle:edit")
	@Log(title = "车牌数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Vehicle vehicle)
	{
		return toAjax(vehicleService.updateVehicle(vehicle));
	}

	/**
	 * 删除车牌数据
	 */
	@RequiresPermissions("zhgd:vehicle:remove")
	@Log(title = "车牌数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(vehicleService.deleteVehicleByIds(ids));
	}

	/**
	 * 智能车牌 上传照片
	 * @return
	 */
	@RequestMapping("/parking/select")
	@ResponseBody
	public List<Vehicle> list (){
		return null;
	}

	/**
	 * 智能车牌 上传照片
	 * @return
	 */
	@RequestMapping("/parking/uploadfile")
	@ResponseBody
	public Map<String,Object> uploadfile(@RequestBody UploadFile uploadFile) {

		// 返回值
		Map<String,Object>  result = new HashMap<>();

		try {
			String filename = StringUtil.getRandomStringByLength(6)+
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(uploadFile.getContent());
			//文件名称
			String url = null;
			try {
				url = AliyunOSSClientUtil.uploadFileImg(file, "lz_img",  filename+".jpeg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String name = url.substring(0,url.lastIndexOf("?"));

			VehicleImg vehicleImg = new VehicleImg();
			vehicleImg.setImgUrl(name);
			vehicleImg.setParkid(uploadFile.getParkid()); // 停车场id
			vehicleImg.setRecordid(uploadFile.getRecordid()); // 记录id 唯一标识
			vehicleImgService.insertVehicleImg(vehicleImg);
			result.put("service",uploadFile.getService());
			result.put("result_code",0);
			result.put("recordid",uploadFile.getRecordid());
			result.put("message","上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("service",uploadFile.getService());
			result.put("result_code",1);
			result.put("recordid",uploadFile.getRecordid());
			result.put("message","上传失败");
		}
		return result;
	}




	/**
	 * 智能车牌 进入车辆信息
	 * @return
	 */
	@RequestMapping("/parking/uploadcarin")
	@ResponseBody
	public Map<String,Object> uploadcarin(@RequestBody UploadCarin uploadCarin) {

		// 返回值
		Map<String,Object>  result = new HashMap<>();

		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setDeptID(Integer.parseInt(uploadCarin.getParkid()));// 车场ID
			vehicle.setRecordId(uploadCarin.getOrder_id()); // 订单记录号
			vehicle.setVehicleNo(uploadCarin.getCar_number()); // 车牌号
			   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   ParsePosition pos = new ParsePosition(0);
			   Date strtodate = formatter.parse(uploadCarin.getIn_time(), pos);
			vehicle.setLiftTime(uploadCarin.getIn_time());//抬杆时间
//			vehicle.setLiftTime(strtodate);//抬杆时间
			vehicle.setInOut(1); // 进出方向 1-进 2-出
			vehicle.setCardType(uploadCarin.getCard_type()); // 车牌类型	0临时车 1月租车 2充值车 3贵宾车 4免费车 8收费月租车
			vehicleService.insertVehicle(vehicle);
			result.put("service",uploadCarin.getService());
			result.put("result_code",0);
			result.put("order_id",uploadCarin.getOrder_id());
			result.put("message","上传成功");

			// 对接中车
			JSONObject objectMap = new JSONObject();
			objectMap.put("type", 5); // 车辆类型
			objectMap.put("plate_number", uploadCarin.getCar_number()); // 车牌号
			objectMap.put("direction", 1); // 0.出 1.进
			objectMap.put("color", null); // 车牌颜色
			objectMap.put("gate_no", uploadCarin.getParkid()); // 闸机编号
			objectMap.put("region_no",2); // 区域编号
			objectMap.put("region_type",1); //区域类型：1-工地，2-受纳场所
			objectMap.put("region_no", uploadCarin.getGateinname()); // 经区域名称
			objectMap.put("lift_type", null); //抬杆方式：1-自动，2-手动

			List list = new ArrayList();
			JSONObject map = new JSONObject();
			map.put("img_type", 1); // 图片类型：1-车头，2-车身
			String filename= StringUtil.getRandomStringByLength(6)+
					new SimpleDateFormat("yyyy-MM-ddHHmmss").format(new Date());
			String img_name = "lz_img"+filename;
			map.put("img_name", img_name); // 图片名称(不允许重复)
			int picSource = 1;
			VehicleImg vehicleImg = vehicleImgService.selectVehicleImgUrl(uploadCarin.getParkid(),uploadCarin.getOrder_id(),picSource);
			map.put("img_data", BASE64DecodedMultipartFile.ImageToBase64ByOnline(vehicleImg.getImgUrl())); // 内容：jpeg格式，base64数据
			list.add(map);
			objectMap.put("images", list); //抬杆方式：1-自动，2-手动
			List<Map<String,Object>>  mapList = new ArrayList<>();
			mapList.add(objectMap);
			Long id = new Long(5);
			Secretkey secretkey = secretkeyService.selectSecretkeyById(id);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("api_key",secretkey.getApiKey());
			jsonObject.put("api_version",secretkey.getApiVersion());
			jsonObject.put("body",mapList);
			jsonObject.put("eng_code",secretkey.getEngCode());
			jsonObject.put("project_code",secretkey.getProjectCode());
			String resultOnt = ZCAPIClient.reportedData("/vehicle/uploadData",jsonObject);
			System.out.println("-------车辆:"+resultOnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}




	/**
	 * 智能车牌 出入车辆信息
	 * @return
	 */
	@RequestMapping("/parking/uploadcarout")
	@ResponseBody
	public Map<String,Object> uploadcarout(@RequestBody UploadCarout uploadCarout) {

		// 返回值
		Map<String,Object>  result = new HashMap<>();

		try {
			Vehicle vehicle = new Vehicle();
			vehicle.setDeptID(Integer.parseInt(uploadCarout.getParkid()));// 车场ID
			vehicle.setRecordId(uploadCarout.getOrder_id()); // 订单记录号
			vehicle.setVehicleNo(uploadCarout.getCar_number()); // 车牌号
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(uploadCarout.getOut_time(), pos);
//			vehicle.setLiftTime(strtodate);//抬杆时间
			vehicle.setLiftTime(uploadCarout.getOut_time());//抬杆时间
			vehicle.setInOut(2); // 进出方向 1-进 2-出
			vehicle.setCardType(uploadCarout.getCard_type()); // 车牌类型	0临时车 1月租车 2充值车 3贵宾车 4免费车 8收费月租车
			vehicleService.insertVehicle(vehicle);
			result.put("service",uploadCarout.getService());
			result.put("result_code",0);
			result.put("order_id",uploadCarout.getOrder_id());
			result.put("message","上传成功");

			// 对接中车
			JSONObject objectMap = new JSONObject();
			objectMap.put("type", 5); // 车辆类型
			objectMap.put("plate_number", uploadCarout.getCar_number()); // 车牌号
			objectMap.put("direction", 0); // 0.出 1.进
			objectMap.put("color", null); // 车牌颜色
			objectMap.put("gate_no", uploadCarout.getParkid()); // 闸机编号
			objectMap.put("region_no",2); // 区域编号
			objectMap.put("region_type",1); //区域类型：1-工地，2-受纳场所
			objectMap.put("region_no", uploadCarout.getGateoutname()); // 经区域名称
			objectMap.put("lift_type", null); //抬杆方式：1-自动，2-手动

			List list = new ArrayList();
			JSONObject map = new JSONObject();
			map.put("img_type", 1); // 图片类型：1-车头，2-车身
			String filename= StringUtil.getRandomStringByLength(6)+
					new SimpleDateFormat("yyyy-MM-ddHHmmss").format(new Date());
			String img_name = "lz_img"+filename;
			map.put("img_name", img_name); // 图片名称(不允许重复)
			int picSource = 2;
			VehicleImg vehicleImg = vehicleImgService.selectVehicleImgUrl(uploadCarout.getParkid(),uploadCarout.getOrder_id(),picSource);
			map.put("img_data", BASE64DecodedMultipartFile.ImageToBase64ByOnline(vehicleImg.getImgUrl())); // 内容：jpeg格式，base64数据
			list.add(map);
			objectMap.put("images", list);
			List<Map<String,Object>>  mapList = new ArrayList<>();
			mapList.add(objectMap);
			Long id = new Long(5);
			Secretkey secretkey = secretkeyService.selectSecretkeyById(id);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("api_key",secretkey.getApiKey());
			jsonObject.put("api_version",secretkey.getApiVersion());
			jsonObject.put("body",mapList);
			jsonObject.put("eng_code",secretkey.getEngCode());
			jsonObject.put("project_code",secretkey.getProjectCode());
			String resultOnt = ZCAPIClient.reportedData("/vehicle/uploadData",jsonObject);
			System.out.println("-------车辆:"+resultOnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}






}
