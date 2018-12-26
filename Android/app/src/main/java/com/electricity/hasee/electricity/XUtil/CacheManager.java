package com.electricity.hasee.electricity.XUtil;





import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.Log;


import com.electricity.hasee.electricity.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**

 * Created by PandaQ on 2017/2/6.

 * email : 767807368@qq.com

 * 磁盘缓存工具类

 */



public class CacheManager  {

    private static CacheManager manager;
    private DiskLruCache mDiskLruCache = null;
    private static final int CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private static final int VALUE_COUNT = 1;
    private static Context context = MyApplication.getContext();
    private static final int BUFFER = 1024;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (manager == null) {
            synchronized (CacheManager.class) {
                if (manager == null) {
                    manager = new CacheManager();
                }
            }
        }
        return manager;
    }

    /**
     * 获取版本号
     */
    public int getAppVersion() {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取缓存目录
     */
    public File getDiskCacheDir(@NonNull String uniqueName) {
        String cachePath = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            File f = context.getExternalCacheDir();
            if (f != null) {
                cachePath = f.getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        Log.e("zhang", "getDiskCacheDir: " + cachePath);
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 一个缓存目录只有一个文件
     */
    public CacheManager open(String dir) {
        open(dir, VALUE_COUNT);
        return manager;
    }

    /**
     * dir:缓存的目录/路径
     * valueCount:缓存文件的个数
     */
    public CacheManager open(String dir, int valueCount) {
        try {
            mDiskLruCache = DiskLruCache.open(getDiskCacheDir(dir), getAppVersion(), valueCount, CACHE_MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manager;
    }

    /**
     * md5生成缓存key
     */
    public String hashKeyForDisk(@NonNull String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 保存字符串
     * key是通过LinkedHashMap获取缓存中的Entry对象
     * index是获取目录中的文件索引
     * value是需要保存的信息值
     */
    public void saveString(String key, int index, @NonNull String value) {
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(hashKeyForDisk(key));
            editor.set(index, value);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveString(String key, @NonNull String value) {
        saveString(key, 0, value);
    }

    /**
     * 保存流信息
     * key是通过LinkedHashMap获取缓存中的Entry对象
     * index是获取目录中的文件索引
     * value是需要保存的信息流
     */
    public void saveStream(String key, int index, @NonNull InputStream value) {
        int len = 0;
        byte[] bytes = new byte[BUFFER];
        OutputStream os = null;
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(hashKeyForDisk(key));
            os = editor.newOutputStream(index);
            while ((len = value.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            editor.commit();
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //快速关流
            Util.closeQuietly(os);
        }
    }

    public void saveStream(@NonNull String key, @NonNull InputStream value) {
        saveStream(key, 0, value);
    }

    /**
     * 获取缓存的String字符串
     * key是通过LinkedHashMap获取缓存中的Entry对象
     * index是获取目录中的文件索引
     */
    public String getString(@NonNull String key, int index) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(hashKeyForDisk(key));
            if (snapshot != null) {
                return snapshot.getString(index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getString(@NonNull String key) {
        return getString(key, 0);
    }

    /**
     * 获取缓存的String字符串
     * key是通过LinkedHashMap获取缓存中的Entry对象
     * index是获取目录中的文件索引
     */
    public InputStream getInputStream(@NonNull String key, int index) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(hashKeyForDisk(key));
            if (snapshot == null) return null;
            return snapshot.getInputStream(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getInputStream(@NonNull String key) {
        return getInputStream(key, 0);
    }

    /**
     * 删除对应的key缓存
     */
    public boolean remove(@NonNull String key) {
        try {
            return mDiskLruCache.remove(hashKeyForDisk(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 清除所有信息
     */
    public void clear() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
