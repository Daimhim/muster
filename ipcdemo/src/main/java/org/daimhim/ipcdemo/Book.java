package org.daimhim.ipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：org.daimhim.ipcdemo
 * 项目版本：muster
 * 创建时间：2018.08.08 17:45
 * 修改人：Daimhim
 * 修改时间：2018.08.08 17:45
 * 类描述：
 * 修改备注：
 *
 * @author：Daimhim
 */
public class Book implements Parcelable {
    private String bookName;
    private String author;
    private double price;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookName);
        dest.writeString(this.author);
        dest.writeDouble(this.price);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.bookName = in.readString();
        this.author = in.readString();
        this.price = in.readDouble();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
