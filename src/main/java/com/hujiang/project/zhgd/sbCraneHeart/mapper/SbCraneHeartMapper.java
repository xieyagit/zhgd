package com.hujiang.project.zhgd.sbCraneHeart.mapper;

import com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart;
import org.springframework.web.bind.annotation.RequestParam;

public interface SbCraneHeartMapper {


    /**
     * @Author xieya
     * @Description 根据id查询数据
     * @Date 2020/3/27 18:32
     * @param hxzId
     * @return com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart
     **/
    SbCraneHeart selectByHxzId(String hxzId);

    /**
     * @Author xieya
     * @Description 新增数据
     * @Date 2020/3/27 18:32
     * @param sbCraneHeart
     * @return int
     **/
    int insertSbCraneHeart(@RequestParam SbCraneHeart sbCraneHeart);

    /**
     * @Author xieya
     * @Description 跟新数据
     * @Date 2020/3/27 18:32
     * @param sbCraneHeart
     * @return int
     **/
    int updateSbCraneHeart(@RequestParam SbCraneHeart sbCraneHeart);
}
