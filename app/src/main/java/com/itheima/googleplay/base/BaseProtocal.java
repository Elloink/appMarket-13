package com.itheima.googleplay.base;

import android.os.SystemClock;
import android.util.LruCache;

import com.google.gson.Gson;
import com.itheima.googleplay.MyApplication;
import com.itheima.googleplay.constant.Constants;
import com.itheima.googleplay.utils.FileUtils;
import com.itheima.googleplay.utils.HttpUtils;
import com.itheima.googleplay.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2016/11/27.
 */

public abstract class BaseProtocal <BEANTYPE> {
    private NetWorkListenter netWorkListenter;

    public void setNetWorkListenter(NetWorkListenter netWorkListenter) {
        this.netWorkListenter = netWorkListenter;
    }

    public BEANTYPE loadData(String tab, int index) throws IOException {
        BEANTYPE data = null;
        data = loadDataFromMem(tab,index);
        if (data == null) {
            data = loadDataFromLocal(tab,index);
        }
        if (data == null) {
            data = loadDataFromNet(tab,index);
        }
        return data;
    }

    /**
     * 从内存中加载数据
     * @return
     */
    private BEANTYPE loadDataFromMem(String tab, int index) {
        BEANTYPE data = null;
        LruCache<String,String> lruCache = MyApplication.getmMemProtocalCacheMap();

        //判断是否有缓存
        String key = generateKey(tab, index);
        String memCacheData = lruCache.get(key);
        data = parseJson(memCacheData);
        return data;
    }

    private String generateKey(String tab, int index) {
        String key = tab+"."+index;
        return key;
    }

    private BEANTYPE loadDataFromLocal(String tab, int index) {

        BufferedReader reader = null;
        //找到缓存文件
        File cacheFile = getCacheFile(tab,index);
        try {
            //判断是否存在
            if (cacheFile.exists()) {
                //可能有效的缓存
                reader = new BufferedReader(new FileReader(cacheFile));
                //读取生成的时间
                String firstLine = reader.readLine();
                long cacheInsertTime = Long.parseLong(firstLine);
                //判断是否过期
                if (SystemClock.currentThreadTimeMillis() - cacheInsertTime < Constants.PROTOCALTIMEOUT) {
                    //没有过期
                    String diskCacheJsonString = reader.readLine();

                    LruCache<String,String> memProtocolCacheMap = MyApplication.getmMemProtocalCacheMap();
                    memProtocolCacheMap.put(generateKey(tab, index), diskCacheJsonString);

                    return parseJson(diskCacheJsonString);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(reader);
        }
        return null;
    }

    private File getCacheFile(String tab, int index) {
        String dir = FileUtils.getDir("json");
        String fileName = generateKey(tab, index);
        return new File(dir, fileName);
    }


    public BEANTYPE loadDataFromNet(String tab,int index) throws IOException {
        //创建客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //输入url
        Map<String, Object> params = new HashMap<>();
        params.put("index", index);
        String url = Constants.URLS.BASEURL+tab+"?"+ HttpUtils.getUrlParamsByMap(params);
        //创建请求
        Request request = new Request.Builder().get().url(url).build();
        //放入浏览器并打开
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            String json = response.body().string();
            Gson gson = new Gson();
            BEANTYPE bean = parseJson(json);

            //写数据到内存
            LruCache<String, String> lruCache = MyApplication.getmMemProtocalCacheMap();
            lruCache.put(generateKey(tab, index), json);

            //写数据到本地
            BufferedWriter writer = null;

            if (netWorkListenter != null) {
                netWorkListenter.success();
            }
            try {
                File cacheFile = getCacheFile(tab, index);
                writer = new BufferedWriter(new FileWriter(cacheFile));
                //写入时间
                writer.write(SystemClock.currentThreadTimeMillis()+"");
                //换行
                writer.newLine();
                //写入第二行
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
                if (netWorkListenter != null) {
                    netWorkListenter.failure();
                }
            } finally {
                IOUtils.close(writer);
            }

            return bean;

        }else{
            //打开链接失败
            return null;
        }
    }

    /**
     * @param resJsonString
     * @return
     * @des 负责解析网络请求回来的jsonString
     * @des 一共有3种解析情况(结点解析, Bean解析, 泛型解析)
     */
    protected abstract BEANTYPE parseJson(String resJsonString);

    public interface NetWorkListenter{
        void success();

        void failure();
    }
}
