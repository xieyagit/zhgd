package com.hujiang.project.zhgd.utils;

import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FolderUtils {

    /**
     * 文件夹格式化
     * @param list  所有文件夹
     * @param parentLevel  文件夹父级id
     * @return
     */
    public static  List<HjFolder> recursion(List<HjFolder> list,int parentLevel){
        List<HjFolder> returnList = new ArrayList<HjFolder>(); //结果集
        for (Iterator<HjFolder> iterator = list.iterator(); iterator.hasNext();)
        {
            if(iterator.hasNext()){//是否存在元素
                HjFolder t = (HjFolder) iterator.next();
                if(parentLevel==t.getParentLevel()){ //当前对象父级id和指定父级id一至
                    returnList.add(t);//添加进结果集
                    t.setChildren(recursion(list,t.getId()));//查询当前对象是否存在子节点
                }
            }
        }
        return returnList;
    }



}
