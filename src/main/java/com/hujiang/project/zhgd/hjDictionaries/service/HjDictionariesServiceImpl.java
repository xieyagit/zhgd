package com.hujiang.project.zhgd.hjDictionaries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjDictionaries.domain.Dictionaries;
import com.hujiang.project.zhgd.hjDictionaries.domain.DictionariesParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDictionaries.mapper.HjDictionariesMapper;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.common.support.Convert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjDictionariesServiceImpl implements IHjDictionariesService 
{
	@Autowired
	private HjDictionariesMapper hjDictionariesMapper;

	@Override
	public List<Dictionaries> getHjDictionariesList(String category) {
		return hjDictionariesMapper.getHjDictionariesList(category);
	}

	/**
     * 查询字典信息
     * 
     * @param id 字典ID
     * @return 字典信息
     */
    @Override
	public HjDictionaries selectHjDictionariesById(Integer id)
	{
	    return hjDictionariesMapper.selectHjDictionariesById(id);
	}
	
	/**
     * 查询字典列表
     * 
     * @param hjDictionaries 字典信息
     * @return 字典集合
     */
	@Override
	public List<HjDictionaries> selectHjDictionariesList(HjDictionaries hjDictionaries)
	{
	    return hjDictionariesMapper.selectHjDictionariesList(hjDictionaries);
	}
	
    /**
     * 新增字典
     * 
     * @param hjDictionaries 字典信息
     * @return 结果
     */
	@Override
	public int insertHjDictionaries(HjDictionaries hjDictionaries)
	{
	    return hjDictionariesMapper.insertHjDictionaries(hjDictionaries);
	}
	
	/**
     * 修改字典
     * 
     * @param hjDictionaries 字典信息
     * @return 结果
     */
	@Override
	public int updateHjDictionaries(HjDictionaries hjDictionaries)
	{
	    return hjDictionariesMapper.updateHjDictionaries(hjDictionaries);
	}

	/**
     * 删除字典对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjDictionariesByIds(String ids)
	{
		return hjDictionariesMapper.deleteHjDictionariesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 热门工种
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> selectWorkType(HjDictionaries hjDictionaries) {
		try {
			List<HjDictionaries> list = hjDictionariesMapper.selectWorkType(hjDictionaries);
			if (list.size() > 0) {
					List<DictionariesParam> dictionariesParamList = HjDictionariesServiceImpl.selectWork(list);
					return AjaxResult.success(dictionariesParamList);
			}
			return AjaxResult.error(-1, "查询失败！");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(-1, "查询失败！");
		}


	}

	/**
	 * 搜索工种
	 * @param
	 * @return
	 */
	@RequestMapping("/queryWorkType")
	@ResponseBody
	@Override
	public Map<String, Object> queryWorkType(HjDictionaries hjDictionaries) {

		try {
			List<HjDictionaries> list = hjDictionariesMapper.queryWorkType(hjDictionaries);
			if (list.size() > 0) {
				List<DictionariesParam> dictionariesParamList = HjDictionariesServiceImpl.selectWork(list);
				return AjaxResult.success(dictionariesParamList);
			}
			return AjaxResult.error(-1, "查询失败！");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(-1, "查询失败！");
		}
	}


	public static List<DictionariesParam> selectWork(List<HjDictionaries> list){
		List<DictionariesParam> dictionariesParamList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			DictionariesParam dictionariesParam = new DictionariesParam();
			dictionariesParam.setId(list.get(i).getId());
			dictionariesParam.setTitle(list.get(i).getTitle());
			dictionariesParam.setTag(list.get(i).getTag());
			dictionariesParam.setCategory(list.get(i).getCategory());
			dictionariesParamList.add(dictionariesParam);
		}
		return dictionariesParamList;
	}


	/**
	 * 删除星河工种
	 */
	public void deleteHjDictionariesByTwo(){
		hjDictionariesMapper.deleteHjDictionariesByTwo();
	}





}
