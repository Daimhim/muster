package org.daimhim.ipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.08 15:36
 * 修改人：Daimhim
 * 修改时间：2018.08.08 15:36
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class User implements Parcelable {
    private String userId;
    private String userName;
    private String userSex;
    private String userAge;
    private String userImg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String pUserId) {
        userId = pUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String pUserName) {
        userName = pUserName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String pUserSex) {
        userSex = pUserSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String pUserAge) {
        userAge = pUserAge;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String pUserImg) {
        userImg = pUserImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }
    /**
     * 这里默认返回0即可
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * 把值写入Parcel中
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userSex);
        dest.writeString(this.userAge);
        dest.writeString(this.userImg);
    }

    public User() {
    }
    /**
     * 这里的读的顺序必须与writeToParcel(Parcel dest, int flags)方法中
     * 写的顺序一致，否则数据会有差错，比如你的读取顺序如果是：
     * this.userId = in.readString();
     * this.userName = in.readString();
     * this.userSex = in.readString();
     * 即调换了userId和userName的读取顺序，那么你会发现你拿到的userName是userId的数据，
     * 而你拿到的userId是userName的数据
     * @param in 序列化对象
     */
    protected User(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userSex = in.readString();
        this.userAge = in.readString();
        this.userImg = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        /**
         * 从Parcel中读取数据
         */
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }
        /**
         * 供外部类反序列化本类数组使用
         */
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
