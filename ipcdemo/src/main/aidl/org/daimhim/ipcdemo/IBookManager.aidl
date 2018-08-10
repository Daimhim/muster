// IBookManager.aidl
package org.daimhim.ipcdemo;

// Declare any non-default types here with import statements
import org.daimhim.ipcdemo.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
