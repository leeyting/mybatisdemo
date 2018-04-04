package com.mybatis.demo.dao.annotation;

import com.mybatis.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liyao
 * @Description: 使用注解方式
 * @Date: Created in 2018/04/03 14:37
 */

@Mapper
public interface UserMapperAnnotation {

    @Select("select * from t_user where user_id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "userName", column = "user_name", javaType = String.class)
    })
    List<User> findUserById(int id);


    @Select("select user_id, user_name, phone, password from t_user")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "userName", column = "user_name", javaType = String.class)
    })
    List<User> selectAllUser();

    @SelectProvider(type = UserMapperProvider.class, method = "findAllUserByCondition")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "userName", column = "user_name", javaType = String.class)
    })
    List<User> findAllUserByCondition(User user);

    @Select("<script>"
            + "select user_id, user_name, phone, password from t_user where user_id IN "
            + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "userName", column = "user_name", javaType = String.class)
    })
    List<User> findUserByIds(@Param("ids")List<Integer> ids);


}
