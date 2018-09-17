package org.cz.ppmanager;

/**
 * 项目名称：org.cz.ppmanager
 * 项目版本：VirtualAPKDemo
 * 创建时间：2018/9/17 16:01  星期一
 * 创建人：Administrator
 * 修改时间：2018/9/17 16:01  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class PPManager {
    private static class SingletonHolder {
        private static final PPManager INSTANCE = new PPManager();
    }

    private PPManager() {
    }

    public static final PPManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
