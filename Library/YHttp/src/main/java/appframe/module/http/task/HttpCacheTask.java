package appframe.module.http.task;


import appframe.module.http.mode.HttpMode;

public class HttpCacheTask extends BaseHttpCacheTask {
    public HttpCacheTask() {
        super();
    }

    public HttpCacheTask(HttpMode httpMode) {
        super(httpMode);
    }

    @Override
    public <T> String run(Class<T> cla, Object listener, int tag) throws Exception {
        String stringData = null;
        String url = httpMode.getUrl();
        byte[] byteData = httpMode.resultBytes();
        if (byteData == null) {
            byteData = getCache(url);
        } else {
            saveCache(url, byteData);
        }
        if (byteData != null) {
            stringData = new String(byteData, getCharset());
            notice(listener,tag, stringData, cla);
        }
        return stringData;
    }
}