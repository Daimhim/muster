package org.daimhim.pluginmanager.model.response;

/**
 * 项目名称：org.daimhim.pluginmanager.model.response
 * 项目版本：muster
 * 创建时间：2018/10/19 10:31  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 10:31  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class JavaResponse<T> {
    private String error_code;
    private String error_msg;
    private T result;

    @Override
    public String toString() {
        return "JavaResponse{" +
                "error_code='" + error_code + '\'' +
                ", error_msg='" + error_msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String pError_code) {
        error_code = pError_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String pError_msg) {
        error_msg = pError_msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T pResult) {
        result = pResult;
    }
}
