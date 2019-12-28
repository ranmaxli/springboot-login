package com.ranmaxli.api.utils;

/**
 * Created by liran on 2017/5/27.
 */
public class PathUtil {
   public static String GetFileNameWithoutExtension(String path){
       return path.substring(path.lastIndexOf('/')+1,path.lastIndexOf('.'));
   }

    public static String GetExtension(String path){
        return path.substring(path.lastIndexOf('.'));
    }
}
