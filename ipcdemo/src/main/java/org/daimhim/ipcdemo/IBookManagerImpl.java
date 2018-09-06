package org.daimhim.ipcdemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class IBookManagerImpl extends Binder implements IBookManager{
    private static final java.lang.String DESCRIPTOR = "org.daimhim.ipcdemo.IBookManagerImpl";

    static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);

    public IBookManagerImpl() {
        this.attachInterface(this,DESCRIPTOR);
    }

    public static IBookManager asInterface(IBinder obj){
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof org.daimhim.ipcdemo.IBookManager))) {
            return ((org.daimhim.ipcdemo.IBookManager) iin);
        }
        return new IBookManagerImpl.Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_basicTypes: {
                data.enforceInterface(DESCRIPTOR);
                int _arg0;
                _arg0 = data.readInt();
                long _arg1;
                _arg1 = data.readLong();
                boolean _arg2;
                _arg2 = (0 != data.readInt());
                float _arg3;
                _arg3 = data.readFloat();
                double _arg4;
                _arg4 = data.readDouble();
                java.lang.String _arg5;
                _arg5 = data.readString();
                this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<org.daimhim.ipcdemo.Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                org.daimhim.ipcdemo.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = org.daimhim.ipcdemo.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        //待实现
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        //待实现
    }

    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        //待实现
    }



    private static class Proxy implements IBookManager {
        private IBinder mRemote;

        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        public String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }


        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            java.util.List<org.daimhim.ipcdemo.Book> _result;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(Stub.TRANSACTION_getBookList, data, reply, 0);
                reply.readException();
                _result = reply.createTypedArrayList(org.daimhim.ipcdemo.Book.CREATOR);
            } finally {
                reply.recycle();
                data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(Stub.TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } finally {
                reply.recycle();
                data.recycle();
            }
        }
    }
}
