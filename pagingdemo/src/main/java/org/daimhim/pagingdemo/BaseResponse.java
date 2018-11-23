package org.daimhim.pagingdemo;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/23 14:08  星期五
 * 创建人：Administrator
 * 修改时间：2018/11/23 14:08  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class BaseResponse<T> {
    private String error_code;
    private String reason;
    private T result;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String pError_code) {
        error_code = pError_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String pReason) {
        reason = pReason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T pResult) {
        result = pResult;
    }
}
