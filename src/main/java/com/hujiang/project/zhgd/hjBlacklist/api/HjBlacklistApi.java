package com.hujiang.project.zhgd.hjBlacklist.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import com.hujiang.project.zhgd.hjBlacklist.service.IHjBlacklistService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 黑名单 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-25
 */
@RestController
@RequestMapping("/provider/hjBlacklist")
public class HjBlacklistApi extends BaseController
{

	@Autowired
	private IHjBlacklistService hjBlacklistService;

	/**
	 * 上传通报文件
	 * @param file
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@PostMapping("uploadBlacklist")
	public JSONObject uploadBlacklist( MultipartFile file,String ids)throws Exception{
		JSONObject result = new JSONObject();
		if(file==null){
			result.put("msg","文件内容为空");
			result.put("code",-1);
			return result;
		}

		if (file != null&&!file.isEmpty()) {
			//上传图片到oss服务器
			String url = AliyunOSSClientUtil.uploadFileImg(file, "blacklist/", System.currentTimeMillis() +file.getOriginalFilename());
			String[] split = ids.split(",");
			int count = 0;
			for(int i = 0; i<split.length;i++){
				HjBlacklist blacklist = new HjBlacklist();
				blacklist.setId(Integer.parseInt(split[i]));
				blacklist.setUrl(url.substring(0,url.indexOf("?")));
				count += hjBlacklistService.updateHjBlacklist(blacklist);
			}
			if(count==split.length && split.length!=0){
				result.put("msg","上传文件成功");
				result.put("code",0);
			}else{
				result.put("msg","上传文件失败");
				result.put("code",-1);
			}
		}
		return  result;
	}
	
	/**
	 * 查询黑名单列表
	 */
	@PostMapping("/getBlacklist")
	public JSONObject list(@RequestBody  HjBlacklist hjBlacklist)
	{

		startPage();
        List<HjBlacklist> list = hjBlacklistService.selectHjBlacklistList(hjBlacklist);
		JSONObject result = new JSONObject();
		result.put("msg","查询成功");
		result.put("code",0);
		result.put("data",getDataTable(list));
		return result;
	}
	

	/**
	 * 新增保存黑名单
	 */
	@PostMapping("/addBlacklist")
	public JSONObject addSave(@RequestBody HjBlacklist hjBlacklist)
	{
		int i = hjBlacklistService.insertHjBlacklist(hjBlacklist);
		JSONObject result = new JSONObject();
		result.put("msg",i>0?"添加成功":"添加失败");
		result.put("code",i>0?0:-1);
		return result;
	}


}
