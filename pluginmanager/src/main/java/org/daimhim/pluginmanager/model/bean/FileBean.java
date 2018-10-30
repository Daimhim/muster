package org.daimhim.pluginmanager.model.bean;

/**
 * 项目名称：org.daimhim.pluginmanager.model.bean
 * 项目版本：muster
 * 创建时间：2018/10/30 15:44  星期二
 * 创建人：Administrator
 * 修改时间：2018/10/30 15:44  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class FileBean {
    private String file_id;

    @Override
    public String toString() {
        return "FileBean{" +
                "file_id='" + file_id + '\'' +
                '}';
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String pFile_id) {
        file_id = pFile_id;
    }
}
