package com.hujiang.project.zhgd.sbApiFaceAttendance.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbApiFaceAttendance.mapper.SbApiFaceAttendanceMapper;
import com.hujiang.project.zhgd.sbApiFaceAttendance.domain.SbApiFaceAttendance;
import com.hujiang.project.zhgd.sbApiFaceAttendance.service.ISbApiFaceAttendanceService;
import com.hujiang.common.support.Convert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 人脸摄像机考勤记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Service
public class SbApiFaceAttendanceServiceImpl implements ISbApiFaceAttendanceService 
{
	@Autowired
	private SbApiFaceAttendanceMapper sbApiFaceAttendanceMapper;

	/**
     * 查询人脸摄像机考勤记录信息
     * 
     * @param id 人脸摄像机考勤记录ID
     * @return 人脸摄像机考勤记录信息
     */
    @Override
	public SbApiFaceAttendance selectSbApiFaceAttendanceById(Integer id)
	{
	    return sbApiFaceAttendanceMapper.selectSbApiFaceAttendanceById(id);
	}
	
	/**
     * 查询人脸摄像机考勤记录列表
     * 
     * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
     * @return 人脸摄像机考勤记录集合
     */
	@Override
	public List<SbApiFaceAttendance> selectSbApiFaceAttendanceList(SbApiFaceAttendance sbApiFaceAttendance)
	{
	    return sbApiFaceAttendanceMapper.selectSbApiFaceAttendanceList(sbApiFaceAttendance);
	}
	
    /**
     * 新增人脸摄像机考勤记录
     * 
     * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
     * @return 结果
     */
	@Override
	public int insertSbApiFaceAttendance(SbApiFaceAttendance sbApiFaceAttendance)
	{
	    return sbApiFaceAttendanceMapper.insertSbApiFaceAttendance(sbApiFaceAttendance);
	}

	@Override
	@Transactional
	public JSONObject insertSbApiFaceAttendanceResult(SbApiFaceAttendance sbApiFaceAttendance)
	{
		JSONObject result = new JSONObject();
		try{

			JSONObject jsonObject = Util.checkObjAllFieldsIsNull(sbApiFaceAttendance);//判断对象属性是否为空
			if(jsonObject.getString("result").equals("ERROR")){
				result=jsonObject;
			}else{
				String filename = StringUtil.getRandomStringByLength(6)+
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(sbApiFaceAttendance.getFaceUrl());
//			MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline("https://hujiang.oss-cn-shenzhen.aliyuncs.com/in/c99s4w104156.jpg"));
				if(file==null){
					result.put("result","ERROR");
					result.put("message","base64异常");
					result.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					return result;
				}
				//文件名称
				String url = null;
				try {
					url = AliyunOSSClientUtil.uploadFileImg(file, "lz_img",  filename+".jpeg");
				} catch (IOException e) {
					e.printStackTrace();
				}
				String name = url.substring(0,url.lastIndexOf("?"));

				sbApiFaceAttendance.setFaceUrl(name);
				if(Util.isValidDate(sbApiFaceAttendance.getAttendanceTime())){//验证时间格式
					int i =sbApiFaceAttendanceMapper.insertSbApiFaceAttendance(sbApiFaceAttendance);
					if(i>0){
						result.put("result","SUCCEED");
						result.put("message","添加成功");
					}else{
						result.put("result","ERROR");
						result.put("message","添加失败");
					}
				}else{
					result.put("result","ERROR");
					result.put("message","attendanceTime时间格式错误");
				}

			}
		}catch (Exception e){
			e.printStackTrace();
			result.put("result","ERROR");
			result.put("message","添加失败");
		}finally {
			result.put("server_timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			return result;
		}

	}
	
	/**
     * 修改人脸摄像机考勤记录
     * 
     * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
     * @return 结果
     */
	@Override
	public int updateSbApiFaceAttendance(SbApiFaceAttendance sbApiFaceAttendance)
	{
	    return sbApiFaceAttendanceMapper.updateSbApiFaceAttendance(sbApiFaceAttendance);
	}

	/**
     * 删除人脸摄像机考勤记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSbApiFaceAttendanceByIds(String ids)
	{
		return sbApiFaceAttendanceMapper.deleteSbApiFaceAttendanceByIds(Convert.toStrArray(ids));
	}
	
}
