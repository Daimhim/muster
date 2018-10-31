package org.daimhim.pluginmanager.utils;

import android.media.Image;

import org.daimhim.pluginmanager.BuildConfig;
import org.daimhim.pluginmanager.StartApp;
import org.daimhim.pluginmanager.model.UserHelp;

/**
 * 项目名称：org.daimhim.pluginmanager.utils
 * 项目版本：muster
 * 创建时间：2018/10/31 10:29  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/31 10:29  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class AddressMachining {
    public static String fileIdToImageUrl(String fileId){
        return String.format("%sgetFile/?userId=%s&fileId=%s",BuildConfig.BASE_URL,
                UserHelp.getInstance().getUserId(),fileId);
    }
}
