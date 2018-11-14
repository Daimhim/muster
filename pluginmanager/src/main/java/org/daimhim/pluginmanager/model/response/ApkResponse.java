package org.daimhim.pluginmanager.model.response;

import org.daimhim.pluginmanager.model.bean.ApkBean;

import java.util.List;

/**
 * 项目名称：org.daimhim.pluginmanager.model.response
 * 项目版本：muster
 * 创建时间：2018/11/14 16:19  星期三
 * 创建人：Administrator
 * 修改时间：2018/11/14 16:19  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApkResponse {
    private List<ApkBean> list;

    public List<ApkBean> getList() {
        return list;
    }

    public void setList(List<ApkBean> pList) {
        list = pList;
    }

    @Override
    public String toString() {
        return "ApkResponse{" +
                "list=" + list +
                '}';
    }
}
