package org.daimhim.pluginmanager.model.bean;

/**
 * 项目名称：org.daimhim.pluginmanager.model.bean
 * 项目版本：muster
 * 创建时间：2018/10/25 21:56  星期四
 * 创建人：Administrator
 * 修改时间：2018/10/25 21:56  星期四
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class AddAppMenuBean {

    private String key;
    private String vaue;

    public AddAppMenuBean() {
    }

    public AddAppMenuBean(String pKey, String pVaue) {
        key = pKey;
        vaue = pVaue;
    }

    @Override
    public String toString() {
        return "AddAppMenuBean{" +
                "key='" + key + '\'' +
                ", vaue='" + vaue + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String pKey) {
        key = pKey;
    }

    public String getVaue() {
        return vaue;
    }

    public void setVaue(String pVaue) {
        vaue = pVaue;
    }
}
