package org.daimhim.pluginmanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.DrawableUtils;
import android.text.TextUtils;
import android.util.Log;


import org.daimhim.helpful.util.HLogUtil;
import org.daimhim.pluginmanager.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 项目名称：org.daimhim.pluginmanager.utils
 * 项目版本：muster
 * 创建时间：2018/10/23 16:32  星期二
 * 创建人：Administrator
 * 修改时间：2018/10/23 16:32  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class CacheFileUtils {
    public static final String CACHE_IMAGE_DIR = "/image";
    public static final String CACHE_AUDIO_DIR = "/audio";
    public static final String CACHE_MESSAGE_DIR = "/message";
    public static final String CACHE_FILE_DIR = "/file";

    private final String RESOURCE_DIRECTORY = "";


    //getExternalCacheDir(context).getPath()
    private String GET_EXTERNAL_CACHE_DIR_PATH = "";
    //context.getCacheDir().getPath()
    private String GET_CACHE_DIR_PATH = "";
    //context.getFilesDir().getAbsolutePath()
    private String GET_FILES_DIR_ABSOLUTE_PATH = "";
    //context.getPackageName()
    private String GET_PACKAGE_NAME = "";
    //Environment.getExternalStorageDirectory().getPath()
    private String ENVIRONMENT_GET_EXTERNAL_STORAGE_DIRECTORY_PATH = "";
    //Environment.MEDIA_MOUNTED
    private String ENVIRONMENT_MEDIA_MOUNTED = "";

    private CacheFileUtils() {

    }

    public static CacheFileUtils getInstance() {
        return CacheFileUtils.SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final CacheFileUtils sInstance = new CacheFileUtils();
    }

    /**
     * 获取可以使用的缓存目录(默认目录名/itlanbaoApp/)
     *
     * @return
     */
    public File getDiskCacheDir() {
        return getDiskCacheDir(RESOURCE_DIRECTORY);
    }

    /**
     * 获取可以使用的缓存目录
     *
     * @param uniqueName 目录名称
     * @return
     */
    public File getDiskCacheDir(String uniqueName) {
        final String cachePath = checkSDCard() ? GET_EXTERNAL_CACHE_DIR_PATH : getAppCacheDir();
        File cacheDirFile = null;
        if (TextUtils.isEmpty(uniqueName)) {
            cacheDirFile = new File(cachePath);
            if (!cacheDirFile.exists()) {
                cacheDirFile.mkdirs();
            }
        } else {
            cacheDirFile = new File(cachePath + "/" + uniqueName);
            if (!cacheDirFile.exists()) {
                cacheDirFile.mkdirs();
            }
        }
        return cacheDirFile;
    }

    /**
     * 获取程序外部的缓存目录
     *
     * @return
     */
    public File getExternalCacheDir() {
        // 这个sd卡中文件路径下的内容会随着，程序卸载或者设置中清除缓存后一起清空
        final String cacheDir = "/Android/data/" + GET_PACKAGE_NAME + "/cache/";
        return new File(ENVIRONMENT_GET_EXTERNAL_STORAGE_DIRECTORY_PATH + cacheDir);
    }

    /**
     * 获取文件路径空间大小
     *
     * @param path
     * @return
     */
    public long getUsableSpace(File path) {
        try {
            final StatFs stats = new StatFs(path.getPath());
            return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 空间大小单位格式化
     *
     * @param size
     * @return
     */
    public String formatSize(long size) {
        String suffix = null;
        float fSize;
        if (size >= 1024) {
            suffix = "KB";
            fSize = size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public boolean checkSDCard() {
        final String status = Environment.getExternalStorageState();
        if (ENVIRONMENT_MEDIA_MOUNTED.equals(status)) {
            return true;
        }
        return false;
    }

    /**
     * 获取安装在用户手机上的下的files目录
     *
     * @return files path
     */
    public String getAppFilesDir() {
        return GET_FILES_DIR_ABSOLUTE_PATH;
    }

    /**
     * 获取安装在用户手机上的下的cache目录
     *
     * @return cache path
     */
    public String getAppCacheDir() {
        return GET_CACHE_DIR_PATH;
    }

    /**
     * 创建app主目录
     *
     * @return boolean
     */
    public boolean checkFileDirectory() {
        final File resDir = getDiskCacheDir();
        if (!resDir.exists()) {
            return resDir.mkdirs();
        }
        return true;
    }

    /**
     * 创建缓存文件夹
     */
    public void initCacheFile(Context context) {
        LogUtils.e("initCacheFile");
        GET_PACKAGE_NAME = context.getPackageName();
        ENVIRONMENT_GET_EXTERNAL_STORAGE_DIRECTORY_PATH = Environment.getExternalStorageDirectory().getPath();
        GET_EXTERNAL_CACHE_DIR_PATH = getExternalCacheDir().getPath();
        GET_CACHE_DIR_PATH = context.getCacheDir().getPath();
        GET_FILES_DIR_ABSOLUTE_PATH = context.getFilesDir().getAbsolutePath();
        ENVIRONMENT_MEDIA_MOUNTED = Environment.MEDIA_MOUNTED;
        final String cacheDir = getDiskCacheDir().getAbsolutePath();

        final String imageDirPath = cacheDir + CACHE_IMAGE_DIR;
        final File imageFileDir = new File(imageDirPath);
        if (!imageFileDir.exists()) {
            boolean isOk = imageFileDir.mkdirs();
            LogUtils.e(imageDirPath + " 文件夹创建isOk" + isOk);
        }

        final String audioDirPath = cacheDir + CACHE_AUDIO_DIR;
        final File audioFileDir = new File(audioDirPath);
        if (!audioFileDir.exists()) {
            boolean isOk = audioFileDir.mkdirs();
            LogUtils.e(audioDirPath + " 文件夹创建isOk" + isOk);
        }

        final String messageDirPath = cacheDir + CACHE_MESSAGE_DIR;
        final File messageFileDir = new File(messageDirPath);
        if (!messageFileDir.exists()) {
            boolean isOk = messageFileDir.mkdirs();
            LogUtils.e(messageDirPath + " 文件夹创建isOk" + isOk);
        }
        final String fileDirPath = cacheDir + CACHE_FILE_DIR;
        final File fileFileDir = new File(fileDirPath);
        if (!fileFileDir.exists()) {
            boolean isOk = fileFileDir.mkdirs();
            LogUtils.e(fileFileDir + " 文件夹创建isOk" + isOk);
        }
    }


    /**
     * 读取文件
     *
     * @param sFileName
     * @return
     */
    public String readFile(String sFileName) {
        if (TextUtils.isEmpty(sFileName)) {
            return null;
        }

        final StringBuffer sDest = new StringBuffer();
        final File f = new File(sFileName);
        if (!f.exists()) {
            return null;
        }
        try {
            FileInputStream is = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {
                String data = null;
                while ((data = br.readLine()) != null) {
                    sDest.append(data);
                }
            } catch (IOException ioex) {
                System.out.println(ioex);
            } finally {
                is.close();
                is = null;
                br.close();
                br = null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } catch (OutOfMemoryError ex) {
            System.out.println(ex);
        }
        return sDest.toString().trim();
    }

    /**
     * 从assets 文件夹中获取文件并读取数据
     *
     * @param context
     * @param fileName
     * @return
     */
    public String getFromAssets(Context context, String fileName) {
        String result = "";
        try {
            final InputStream in = context.getResources().getAssets()
                    .open(fileName);
            // 获取文件的字节数
            final int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer, 0, buffer.length, "UTF-8");
            in.close();
            buffer = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 保存文件
     * @param content
     * @param fileName
     * @param isAppend
     * @return
     */
    public Uri writeStringToFile(String content, String fileName, boolean isAppend) {
        return writeStringToFile(content, "", fileName, isAppend);
    }

    public Uri writeStringToFile(String content,
                                     String directoryPath, String fileName, boolean isAppend) {
        if (!TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(directoryPath)) {// 是否需要创建新的目录
                final File threadListFile = new File(directoryPath);
                if (!threadListFile.exists()) {
                    threadListFile.mkdirs();
                }
            }
            boolean bFlag = false;
            final int iLen = content.length();
            final File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                final FileOutputStream fos = new FileOutputStream(file,
                        isAppend);
                byte[] buffer = new byte[iLen];
                try {
                    buffer = content.getBytes();
                    fos.write(buffer);
                    if (isAppend) {
                        fos.write("||".getBytes());
                    }
                    fos.flush();
                    bFlag = true;
                } catch (IOException ioex) {
                    if (LogUtils.DEBUG) {
                        LogUtils.e(ioex);
                    }
                } finally {
                    fos.close();
                    buffer = null;
                }
            } catch (Exception ex) {
                if (LogUtils.DEBUG) {
                    LogUtils.e(ex);
                }
            } catch (OutOfMemoryError o) {
                if (LogUtils.DEBUG) {
                    o.printStackTrace();
                }
            }
            return Uri.fromFile(file);
        }
        return null;
    }

    /**
     * 重命名
     *
     * @param filePath
     * @return
     */
    public boolean rename(String filePath, String newFilePath) {
        if (LogUtils.DEBUG) {
            LogUtils.e("filePath " + filePath);
            LogUtils.e("newFilePath " + newFilePath);
        }

        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);
            final File newFile = new File(newFilePath);
            if (file.exists()) {
                return file.renameTo(newFile);
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath) {
        if (LogUtils.DEBUG) {
            LogUtils.e("deleteFile path " + filePath);
        }

        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);
            if (LogUtils.DEBUG) {
                LogUtils.e("deleteFile path exists " + file.exists());
            }
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 删除文件夹下所有文件
     *
     * @return
     */
    public void deleteDirectoryAllFile(String directoryPath) {
        final File file = new File(directoryPath);
        deleteDirectoryAllFile(file);
    }

    public void deleteDirectoryAllFile(File file) {
        if (!file.exists()) {
            return;
        }

        boolean rslt = true;// 保存中间结果
        if (!(rslt = file.delete())) {// 先尝试直接删除
            // 若文件夹非空。枚举、递归删除里面内容
            final File subs[] = file.listFiles();
            final int size = subs.length - 1;
            for (int i = 0; i <= size; i++) {
                if (subs[i].isDirectory())
                    deleteDirectoryAllFile(subs[i]);// 递归删除子文件夹内容
                rslt = subs[i].delete();// 删除子文件夹本身
            }
            // rslt = file.delete();// 删除此文件夹本身
        }

        if (!rslt) {
            if (LogUtils.DEBUG) {
                LogUtils.w("无法删除:" + file.getName());
            }
            return;
        }
    }

    /**
     * 根据后缀名删除文件
     *
     * @param delEndName end name of file
     * @return boolean the result
     */
    public boolean deleteEndFile(String delPath, String delEndName) {
        // param is null
        if (delPath == null || delEndName == null) {
            return false;
        }
        try {
            // create file
            final File file = new File(delPath);
            if (file != null) {
                if (file.isDirectory()) {
                    // file list
                    String[] fileList = file.list();
                    File delFile = null;

                    // digui
                    final int size = fileList.length;
                    for (int i = 0; i < size; i++) {
                        // create new file
                        delFile = new File(delPath + "/" + fileList[i]);
                        if (delFile != null && delFile.isFile()) {// 删除该文件夹下所有文件以delEndName为后缀的文件（不包含子文件夹里的文件）
                            // if (delFile != null) {//
                            // 删除该文件夹下所有文件以delEndName为后缀的文件（包含子文件夹里的文件）
                            deleteEndFile(delFile.toString(), delEndName);
                        } else {
                            // nothing
                        }
                    }
                } else if (file.isFile()) {

                    // check the end name
                    if (file.toString().contains(".")
                            && file.toString()
                            .substring(
                                    (file.toString().lastIndexOf(".") + 1))
                            .equals(delEndName)) {
                        // file delete
                        file.delete();
                    }
                }
            }
        } catch (Exception ex) {
            if (LogUtils.DEBUG) {
                LogUtils.e(ex);
            }
            return false;
        }
        return true;
    }

    /**
     * 删除文件夹内所有文件
     *
     * @param delpath delpath path of file
     * @return boolean the result
     */
    public boolean deleteAllFile(String delpath) {
        try {
            // create file
            final File file = new File(delpath);

            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {

                final String[] filelist = file.list();
                final int size = filelist.length;
                for (int i = 0; i < size; i++) {

                    // create new file
                    final File delfile = new File(delpath + "/" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        // digui
                        deleteFile(delpath + "/" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception ex) {
            if (LogUtils.DEBUG) {
                LogUtils.e(ex);
            }
            return false;
        }
        return true;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {

        if (TextUtils.isEmpty(sPath)) {
            return false;
        }

        boolean flag;
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        final File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        final File[] files = dirFile.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                } // 删除子目录
                else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                }
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取后缀名
     *
     * @param path 全路径
     * @return
     */
    public String getFileExtName(String path) {
        String ext = "";
        if ((path != null) && (path.length() > 0)) {
            int dot = path.lastIndexOf('.');
            if ((dot > -1) && (dot < (path.length() - 1))) {
                ext = path.substring(dot + 1);
            }
        }
        return ext;
    }

    /**
     * 获取文件名
     *
     * @param path 全路径
     * @return
     */
    public String getFileName(String path) {
        if (!TextUtils.isEmpty(path)) {
            return path.substring(path.lastIndexOf(File.separator) + 1);
        }
        return "";
    }

    /**
     * 获取文件所在的文件路径
     *
     * @param path
     * @return
     */
    public String getFilePath(String path) {
        return path.substring(0, path.lastIndexOf(File.separator) + 1);
    }

    /**
     * 复制文件
     *
     * @param srcPath  : 源文件全路径
     * @param destPath : 目标文件全路径
     * @return
     */
    public long copyFile(String srcPath, String destPath) {
        try {
            int position = destPath.lastIndexOf(File.separator);
            String dir = destPath.substring(0, position);
            String newFileName = destPath.substring(position + 1);
            final File cacheDir = new File(dir);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            return copyFile(new File(srcPath), new File(dir), newFileName);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 复制文件(以超快的速度复制文件)
     *
     * @param srcFile     源文件File
     * @param destDir     目标目录File
     * @param newFileName 新文件名
     * @return 实际复制的字节数，如果文件、目录不存在、文件为null或者发生IO异常，返回-1
     */
    @SuppressWarnings("resource")
    public long copyFile(final File srcFile, final File destDir, String newFileName) {
        long copySizes = 0;
        if (!srcFile.exists()) {
            if (LogUtils.DEBUG) {
                LogUtils.d("源文件不存在");
            }
            copySizes = -1;
        } else if (!destDir.exists()) {
            if (LogUtils.DEBUG) {
                LogUtils.d("目标目录不存在");
            }
            copySizes = -1;
        } else if (newFileName == null) {
            if (LogUtils.DEBUG) {
                LogUtils.d("文件名为null");
            }
            copySizes = -1;
        } else {
            FileChannel fcin = null;
            FileChannel fcout = null;
            try {
                fcin = new FileInputStream(srcFile).getChannel();
                fcout = new FileOutputStream(new File(destDir, newFileName)).getChannel();
                long size = fcin.size();
                fcin.transferTo(0, fcin.size(), fcout);
                copySizes = size;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fcin != null) {
                        fcin.close();
                    }
                    if (fcout != null) {
                        fcout.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return copySizes;
    }


    /**
     * 判断asset下是否存在某个文件
     *
     * @param context
     * @param fileName 如:aa.txt或image/aa.jpg
     * @return
     */
    public boolean existInAsset(Context context, String fileName) {
        boolean exist = false;
        try {
            String[] u = context.getAssets().list(getFilePath(fileName));
            for (String item : u) {
                if (item.equals(getFileName(fileName))) {
                    exist = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 获取目录文件个数
     *
     * @param f
     * @return
     */
    public long getlist(File f) {
        long size = 0;
        try {
            File flist[] = f.listFiles();
            size = flist.length;
            for (int i = 0; i < flist.length; i++) {
                final File file = flist[i];
                if (file == null) {
                    continue;
                }
                if (file.isDirectory()) {
                    size = size + getlist(file);
                    size--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取文件夹下所有文件大小
     *
     * @param f
     * @return
     */
    public long getFileSize(File f) {
        long size = 0;
        try {
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                final File file = flist[i];
                if (file == null) {
                    continue;
                }
                if (file.isDirectory()) {
                    size = size + getFileSize(file);
                } else {
                    size = size + file.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public String getAutoFileOrFilesSize(File file) {
        if (file == null) {
            return "0B";
        }
        final long blockSize = getFileSize(file);
        if (LogUtils.DEBUG) {
            LogUtils.e("getAutoFileOrFilesSize 文件大小：" + blockSize);
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private String FormetFileSize(long fileS) {
        final DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 将字符串写入到文本文件中
     *
     * @param strcontent
     */
    public void writeFileSdcard(String strcontent) {
        DateFormat lDateInstance = SimpleDateFormat.getDateInstance();
        if (lDateInstance instanceof SimpleDateFormat) {
            ((SimpleDateFormat) lDateInstance).applyPattern("yyyy年MM月dd日   HH:mm:ss");
        }
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = lDateInstance.format(curDate);
        // 每次写入时，都换行写
        String strContent = "-------当前时间===" + str + "\r\n" + strcontent + "\r\n";
        try {
            String strFilePath = Environment.getExternalStorageDirectory() + "/lunxun.text";

            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File.");
        }
    }

    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @param destFileDir  目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    public File saveFile(InputStream is, long total, String destFileDir, String destFileName) throws IOException {
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            long sum = 0;
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            if (!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                //这里就是对进度的监听回调
//                onProgress((int) (finalSum * 100 / total),total);
            }
            fos.flush();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param pBitmap
     * @param filePath
     * @param fileName
     * @return
     */
    public Uri saveBitmap(Bitmap pBitmap,String filePath,String fileName){
        byte[] buf = new byte[2048];
        if (!TextUtils.isEmpty(filePath)){
            File folder = new File(filePath);
            if(!folder.exists()){
                folder.mkdir();
            }
            File file = new File(folder,fileName);
            if(file.exists()){
                file.delete();
            }
            FileOutputStream out = null;
            try {
                if(!file.exists()){
                    file.createNewFile();//重点在这里
                }
                out = new FileOutputStream(file);
                pBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                return Uri.fromFile(file);
            } catch (FileNotFoundException pE) {
                pE.printStackTrace();
            } catch (IOException pE) {
                pE.printStackTrace();
            } finally {
                try {
                    if (null!=out) {
                        out.close();
                    }
                } catch (IOException pE) {
                    pE.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 生成随机文件名
     **/
    public String generateRandomFilename(String suffix) {
        String RandomFilename = "";
        Random rand = new Random();//生成随机数  
        int random = rand.nextInt();
        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" + String.valueOf(intDay) + "_";
        LogUtils.d("生成于今日的文件名前缀为：" + now);
        RandomFilename = now + String.valueOf(random > 0 ? random : (-1) * random) + "." + (TextUtils.isEmpty(suffix)?"":suffix);
        return RandomFilename;
    }

    static class LogUtils {
        public static boolean DEBUG = true;

        public static void e(String text) {
            HLogUtil.d("CacheFileUtils",text);
        }

        public static void d(String text) {
            HLogUtil.d("CacheFileUtils",text);
        }

        public static void w(String text) {
            HLogUtil.d("CacheFileUtils",text);
        }

        public static void e(Exception pE) {
            HLogUtil.d("CacheFileUtils",pE.getMessage());
        }
    }
}
