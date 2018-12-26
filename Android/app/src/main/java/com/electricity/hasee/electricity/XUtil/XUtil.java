package com.electricity.hasee.electricity.XUtil;

import android.os.Environment;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.table.TableEntity;
import org.xutils.http.RequestParams;
import org.xutils.http.app.ResponseParser;
import org.xutils.x;

import java.io.File;
import java.util.Map;

public class XUtil {
/** 
* 发送get请求 
* @param <T> 
*/
public static <T> Callback.Cancelable Get(String url, Map<String,String> map, Callback.CommonCallback<T> callback){
RequestParams params=new RequestParams(url);
if(null!=map){ 
for(Map.Entry<String, String> entry : map.entrySet()){ 
params.addQueryStringParameter(entry.getKey(), entry.getValue()); 
} 
} 
Callback.Cancelable cancelable = x.http().get(params, callback);
return cancelable; 
} 
/** 
* 发送post请求 
* @param <T> 
*/
public static <T> Callback.Cancelable Post(String url, Map<String,Object> map, Callback.CommonCallback<T> callback){
RequestParams params=new RequestParams(url); 
if(null!=map){ 
for(Map.Entry<String, Object> entry : map.entrySet()){ 
params.addParameter(entry.getKey(), entry.getValue()); 
} 
} 
Callback.Cancelable cancelable = x.http().post(params, callback);
return cancelable; 
} 
/** 
* 上传文件 
* @param <T> 
*/
public static <T> Callback.Cancelable UpLoadFile(String url, Map<String,Object> map, Callback.CommonCallback<T> callback){
RequestParams params=new RequestParams(url); 
if(null!=map){ 
for(Map.Entry<String, Object> entry : map.entrySet()){ 
params.addParameter(entry.getKey(), entry.getValue()); 
} 
} 
params.setMultipart(true); 
Callback.Cancelable cancelable = x.http().get(params, callback);
return cancelable; 
} 
/** 
* 下载文件 
* @param <T> 
*/
public static <T> Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback<T> callback){
RequestParams params=new RequestParams(url); 
//设置断点续传 
params.setAutoResume(true); 
params.setSaveFilePath(filepath); 
Callback.Cancelable cancelable = x.http().get(params, callback);
return cancelable; 
}



    public static DbManager.DaoConfig getDaoConfig(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("xutils3_db") //设置数据库名
                .setDbVersion(1) //设置数据库版本,每次启动应用时将会检查该版本号,发现数据库版本低于这里设置的值将进行数据库升级并触发DbUpgradeListener
                .setAllowTransaction(true)//设置是否开启事务,默认为false关闭事务
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {

                    }


                })//设置数据库创建时的Listener
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //balabala...
                    }
                });
        return daoConfig;
    }

}