package org.daimhim.pluginmanager.model.response;

import org.daimhim.pluginmanager.model.bean.ApplicationBean;

import java.util.List;

/**
 * 项目名称：org.daimhim.pluginmanager.model.response
 * 项目版本：muster
 * 创建时间：2018/10/23 14:11  星期二
 * 创建人：Administrator
 * 修改时间：2018/10/23 14:11  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ApplicationResponse {

    private List<ApplicationBean> list;

    @Override
    public String toString() {
        return "ApplicationResponse{" +
                "list=" + list +
                '}';
    }

    public List<ApplicationBean> getList() {
        return list;
    }

    public void setList(List<ApplicationBean> pList) {
        list = pList;
    }
}
