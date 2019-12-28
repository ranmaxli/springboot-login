package com.ranmaxli.api.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liran on 2017/2/13.
 */
@Configuration
public class CommonUtil {

    public static String localHost;

    public void setLocalHost(String localhost) {
        localHost = localhost;
    }

    public static String alibaHost;

    public void setAlibaHost(String alibahost) {
        alibaHost = alibahost;
    }

    public static String yunDir;

    public void setYunDir(String yundir) {
        yunDir = yundir;
    }

    public static String fileRoot;

    public void setFileRoot(String fileroot) {
        fileRoot = fileroot;
    }

    /***
     * 获取文件完整请求地址
     */
    public static String getFullPath(String path) {
        if (StringUtils.isEmpty(path) || path.indexOf("http://") == 0)
            return path;
        else
            return HostUrlHead(path) + path;
    }

    /**
     * 根据glogin avator获取头像地址
     * @param avatorurl
     * @param size
     * @param usertype
     * @return
     */
    public static String getAvatorUrlByGlogin(String avatorurl, int size, int usertype)
    {
        String result = "";

        if(StringUtils.isEmpty(avatorurl))
        {
            result = "aliba/upload/avator/default/" +  (usertype == 0 ? "teacher":"student");
        }
        else
        {
            if (avatorurl.indexOf("aliba") == 0)
            {
                result = avatorurl;
            }
            else if (avatorurl.indexOf("aliba") == 1)
            {
                result = avatorurl.substring(1);
            }
            else
            {
                result = "aliba/upload/avator/default/" +  (usertype == 0 ? "teacher":"student");
            }
        }

        result = "http://fs.yixuexiao.cn/" + result + "/" + size + ".png";
        return result;
    }
    
    /**
     * 根据字符串获取访问头
     */
    public static String HostUrlHead(String path) {
        if (path != null) 
        {
            if (path.indexOf("http://") == 0 || path.indexOf("https://") == 0)
                return "";
            
            else if (path.indexOf("aliba") == 0)
                return alibaHost + "/";
            
            else if (path.indexOf("aliba") == 1)
                return alibaHost;
            
            else
                return localHost;
        } 
        else 
        {
            return localHost;
        }
    }

    /**
     * 获取头像地址
     * @param userid
     * @param avatorurl
     * @param size
     * @return
     */
    public static String getAvatorUrl(String avatorurl, int size, int usertype)
    {
        String result = "";
        
        if (!StringUtils.isEmpty(avatorurl))
        {
            if (avatorurl.indexOf("aliba") <= 1)
            {
                result = avatorurl;
            }
            else 
            {
                result = "aliba/upload/avator/default/" +  (usertype == 0 ? "teacher":"student");    
            }
        }
        else 
        {
            result = "aliba/upload/avator/default/" +  (usertype == 0 ? "teacher":"student");
        }
        
        return result + "/" + size + ".png";
    }
    
    
    /**
     * @param targetList 要排序的集合
     * @param sortField  要排序的字段
     * @param sortMode   排序的类型
     * @param <T>
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sort(List<T> targetList, final String sortField, final String sortMode) {
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
                    String methodStr = "get" + newStr;

                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
                    if (sortMode != null && "desc".equals(sortMode)) {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序
                    } else {
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }

    /**
     * 设置模型默认值
     *
     * @param model 模型
     * @param <T>   泛型
     */
    public static <T> void setDefault(T model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                String type = field.getGenericType().toString();
                String name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                if (type.equals("class java.lang.String")) {
                    Method m = model.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(model);
                    if (StringUtil.isNullOrEmpty(value)) {
                        m = model.getClass().getMethod("set" + name, String.class);
                        m.invoke(model, "");
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Method m = model.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Integer.class);
                        m.invoke(model, 0);
                    }
                }
                if (type.equals("class java.lang.Short")) {
                    Method m = model.getClass().getMethod("get" + name);
                    Short value = (Short) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Short.class);
                        m.invoke(model, 0);
                    }
                }

                if (type.equals("class java.lang.Double")) {
                    Method m = model.getClass().getMethod("get" + name);
                    Double value = (Double) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Double.class);
                        m.invoke(model, 0.0);
                    }
                }

                if (type.equals("class java.lang.Boolean")) {
                    Method m = model.getClass().getMethod("get" + name);
                    Boolean value = (Boolean) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Boolean.class);
                        m.invoke(model, false);
                    }
                }

                if (type.equals("class java.lang.Long")) {
                    Method m = model.getClass().getMethod("get" + name);

                    Long value = (Long) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Long.class);
                        m.invoke(model, 0l);
                    }
                }

                if (type.equals("class java.lang.Byte")) {
                    Method m = model.getClass().getMethod("get" + name);

                    Byte value = (Byte) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set" + name, Byte.class);
                        m.invoke(model, (byte)0);
                    }
                }

                //if (type.equals("class java.util.Date")) {
                //    Method m = model.getClass().getMethod("get" + name);
                //
                //    Date value = (Date) m.invoke(model);
                //    if (value == null) {
                //        m = model.getClass().getMethod("set" + name, Date.class);
                //        m.invoke(model, new Date());
                //    }
                //}
            } catch (Exception e) {

            }
        }
    }
}