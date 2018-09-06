package org.daimhim.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookManagerService extends Service {
    private ArrayList<Book> mBookList = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IBookManager.Stub() {
            @Override
            public List<Book> getBookList() throws RemoteException {
                return null;
            }

            @Override
            public void addBook(Book book) throws RemoteException {

            }
        };
    }
}
