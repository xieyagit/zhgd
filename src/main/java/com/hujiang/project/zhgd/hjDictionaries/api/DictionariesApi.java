package com.hujiang.project.zhgd.hjDictionaries.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjDictionaries.domain.Dictionaries;
import com.hujiang.project.zhgd.hjDictionaries.domain.DictionariesParam;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.HjDictionariesServiceImpl;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工种表
 *
 * @author hujiang
 * @date 2019-05-19
 */
@Controller
@RequestMapping(value = "/provider/dictionariesApi", method = RequestMethod.POST)
public class DictionariesApi {


    @Autowired
    private IHjDictionariesService hjDictionariesService;

    @RequestMapping("/getHjDictionariesList")
    @ResponseBody
    public JSONObject getHjDictionariesList(@RequestBody String json){
        JSONObject jsonResult = JSONObject.parseObject(json);
        JSONObject jsonObject = new JSONObject();
        String category = jsonResult.getString("category");
        List<Dictionaries> hjDictionariesList = hjDictionariesService.getHjDictionariesList(category);
        jsonObject.put("msg","成功");
        jsonObject.put("code",0);
        jsonObject.put("data",hjDictionariesList);
        return jsonObject;
    }
    /**
     * 类型信息
     *
     * @param
     * @return
     */
    @RequestMapping("/selectDictionaries")
    @ResponseBody
    public Map<String, Object> selectDictionaries(@RequestBody HjDictionaries hjDictionaries) {
        try {
            List<HjDictionaries> hjDictionariesList = hjDictionariesService.selectHjDictionariesList(hjDictionaries);

            if (hjDictionariesList.size() > 0) {
                List<DictionariesParam> dictionariesParamList = HjDictionariesServiceImpl.selectWork(hjDictionariesList);
                return AjaxResult.success(dictionariesParamList);
            }
            return AjaxResult.success(hjDictionariesList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(-1, "查询失败！");
        }
    }


    /**
     * 热门工种
     *
     * @param
     * @return
     */
    @RequestMapping("/selectWorkType")
    @ResponseBody
    public Map<String, Object> selectWorkType(@RequestBody HjDictionaries hjDictionaries) {
        return hjDictionariesService.selectWorkType(hjDictionaries);
    }


    /**
     * 搜索工种
     *
     * @param
     * @return
     */
    @RequestMapping("/queryWorkType")
    @ResponseBody
    public Map<String, Object> queryWorkType(@RequestBody HjDictionaries hjDictionaries) {
        return hjDictionariesService.queryWorkType(hjDictionaries);
    }

}
