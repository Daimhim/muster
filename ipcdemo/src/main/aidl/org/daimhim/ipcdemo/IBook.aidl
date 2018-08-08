// IBook.aidl
package org.daimhim.ipcdemo;

import org.daimhim.ipcdemo.Book;
// Declare any non-default types here with import statements

interface IBook {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    Book getBook();
}
