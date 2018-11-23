package org.daimhim.pagingdemo;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/23 10:52  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/23 10:52  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class JokeBean {
    private String content;
    private String hashId;
    private String unixtime;
    private String updatetime;

    @Override
    public String toString() {
        return "JokeBean{" +
                "content='" + content + '\'' +
                ", hashId='" + hashId + '\'' +
                ", unixtime='" + unixtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String pContent) {
        content = pContent;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String pHashId) {
        hashId = pHashId;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String pUnixtime) {
        unixtime = pUnixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String pUpdatetime) {
        updatetime = pUpdatetime;
    }
}
