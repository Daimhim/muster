package org.daimhim.pluginmanager;

import org.daimhim.distance.NetConnectedListener;
import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.utils.NetWorkUtils;
import org.junit.After;
import org.junit.Before;

/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/19 11:07  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 11:07  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class AbsInterfaceTest {
    @Before
    public void init(){
        RetrofitManager.Config lConfig = new RetrofitManager.Config();
        lConfig.setBASE_DOMAIN(BuildConfig.BASE_URL);
        lConfig.setCacheFile(System.getProperty("user.dir"));
        lConfig.setNetConnectedListener(new NetConnectedListener() {
            @Override
            public boolean isNetConnected() {
                return true;
            }
        });
        RetrofitManager.getInstance().init(lConfig);
    }
}
