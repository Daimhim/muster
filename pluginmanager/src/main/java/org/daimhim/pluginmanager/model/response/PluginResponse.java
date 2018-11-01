package org.daimhim.pluginmanager.model.response;

import org.daimhim.pluginmanager.model.bean.PluginBean;

import java.util.List;

/**
 * 项目名称：org.daimhim.pluginmanager.model.response
 * 项目版本：muster
 * 创建时间：2018/11/1 17:01  星期四
 * 创建人：Administrator
 * 修改时间：2018/11/1 17:01  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class PluginResponse  {
    private List<PluginBean> list;

    @Override
    public String toString() {
        return "PluginResponse{" +
                "list=" + list +
                '}';
    }

    public List<PluginBean> getList() {
        return list;
    }

    public void setList(List<PluginBean> pList) {
        list = pList;
    }
}
