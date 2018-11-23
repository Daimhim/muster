package org.daimhim.pagingdemo;

import java.util.List;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/23 14:08  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/23 14:08  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class JokeResponse {
    private List<JokeBean> data;

    @Override
    public String toString() {
        return "JokeResponse{" +
                "data=" + data +
                '}';
    }

    public List<JokeBean> getData() {
        return data;
    }

    public void setData(List<JokeBean> pData) {
        data = pData;
    }
}
