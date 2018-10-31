package org.daimhim.pluginmanager.model.bean;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/17 17:44  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/17 17:44  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class PluginBean {
    private String plugin_id;
    private String plugin_name;
    private String plugin_description;
    private String package_name;
    private String last_version_name;
    private String last_version_code;
    private String last_version_upTime;
    private String last_min_sdk_version;
    private String last_target_sdk_version;

    @Override
    public String toString() {
        return "PluginBean{" +
                "plugin_id='" + plugin_id + '\'' +
                ", plugin_name='" + plugin_name + '\'' +
                ", plugin_description='" + plugin_description + '\'' +
                ", package_name='" + package_name + '\'' +
                ", last_version_name='" + last_version_name + '\'' +
                ", last_version_code='" + last_version_code + '\'' +
                ", last_version_upTime='" + last_version_upTime + '\'' +
                ", last_min_sdk_version='" + last_min_sdk_version + '\'' +
                ", last_target_sdk_version='" + last_target_sdk_version + '\'' +
                '}';
    }

    public String getPlugin_id() {
        return plugin_id;
    }

    public void setPlugin_id(String pPlugin_id) {
        plugin_id = pPlugin_id;
    }

    public String getPlugin_name() {
        return plugin_name;
    }

    public void setPlugin_name(String pPlugin_name) {
        plugin_name = pPlugin_name;
    }

    public String getPlugin_description() {
        return plugin_description;
    }

    public void setPlugin_description(String pPlugin_description) {
        plugin_description = pPlugin_description;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String pPackage_name) {
        package_name = pPackage_name;
    }

    public String getLast_version_name() {
        return last_version_name;
    }

    public void setLast_version_name(String pLast_version_name) {
        last_version_name = pLast_version_name;
    }

    public String getLast_version_code() {
        return last_version_code;
    }

    public void setLast_version_code(String pLast_version_code) {
        last_version_code = pLast_version_code;
    }

    public String getLast_version_upTime() {
        return last_version_upTime;
    }

    public void setLast_version_upTime(String pLast_version_upTime) {
        last_version_upTime = pLast_version_upTime;
    }

    public String getLast_min_sdk_version() {
        return last_min_sdk_version;
    }

    public void setLast_min_sdk_version(String pLast_min_sdk_version) {
        last_min_sdk_version = pLast_min_sdk_version;
    }

    public String getLast_target_sdk_version() {
        return last_target_sdk_version;
    }

    public void setLast_target_sdk_version(String pLast_target_sdk_version) {
        last_target_sdk_version = pLast_target_sdk_version;
    }
}
