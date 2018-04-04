package com.mybatis.demo.dao.annotation;

import com.mybatis.demo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Author: liyao
 * @Description: sql拼装
 * @Date: Created in 2018/04/03 15:36
 */

public class UserMapperProvider {

    public String findAllUserByCondition(final User user) {
        return new SQL() {{
            SELECT("user_id, user_name, phone, password");
            FROM("t_user");
            if (user.getUserId() != null) {
                WHERE("user_id = #{userId}");
            }
            if (!StringUtils.isEmpty(user.getUserName())) {
                WHERE("user_name = #{userName}");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                WHERE("password = #{password}");
            }
            if (!StringUtils.isEmpty(user.getPhone())) {
                WHERE("phone = #{phone}");
            }
        }}.toString();
    }

}
