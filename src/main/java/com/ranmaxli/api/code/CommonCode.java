package com.ranmaxli.api.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liran on 2017/2/10.
 */
public class CommonCode {

    //success
    public static final int SUCCESS_CODE = 1;
    public static final String SUCCESS_MSG = "请求成功！";
    public static final String SUCCESS_SAVE = "保存成功！";
    public static final String SUCCESS_NULL_DATA_MSG = "暂无数据！";

    //fail
    public static final int FAIL_CODE = -1;
    public static final String MISSING_PARAM_MSG = "缺少参数！";
    public static final String EMPTY_DATA = "";

    public static final int NO_FOUND_USER_CODE = -2;
    public static final String NO_FOUND_USER_MSG = "查无此用户！";

    public static final int INSERT_USER_FAIL_CODE = -3;
    public static final String INSERT_USER_FAIL_MSG = "新增用户失败！";

    public static final int UPDATE_USER_FAIL_CODE = -4;
    public static final String UPDATE_USER_FAIL_MSG = "更新用户失败！";

    public static final int DELETE_USER_FAIL_CODE = -5;
    public static final String DELETE_USER_FAIL_MSG = "删除用户失败！";
}
