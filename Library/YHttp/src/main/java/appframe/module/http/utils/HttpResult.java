package appframe.module.http.utils;

/**
 * Created by ybao on 16/2/1.
 */
public class HttpResult<T> {
    T data;
    Exception exception;
    int tag = 0;

    public HttpResult<T> setData(T data) {
        this.data = data;
        this.exception = null;
        return this;
    }

    public HttpResult<T> setError(Exception exception) {
        this.exception = exception;
        this.data = null;
        return this;
    }

    public HttpResult<T> setCencl() {
        this.data = null;
        this.exception = null;
        return this;
    }

    public T getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }


    public boolean isSucceed() {
        return data != null;
    }


    public boolean isError() {
        return exception != null;
    }

    public boolean isCencl() {
        return exception == null && data == null;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}
