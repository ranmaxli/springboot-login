package com.ranmaxli.dao;

import com.ranmaxli.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ranli2 on 9/27/2017.
 */

@Mapper
public interface UserMapper {

    User userLogin(@Param("username") String username, @Param("password")String password);

    int insertUser(@Param("username") String username, @Param("password")String password,@Param("displayName")String displayName);

    int updatePassword(@Param("username") String username, @Param("password")String password,@Param("oldPassword")String oldPassword);

    int deleteUser(@Param("username") String username, @Param("password")String password,@Param("displayName")String displayName);

}
