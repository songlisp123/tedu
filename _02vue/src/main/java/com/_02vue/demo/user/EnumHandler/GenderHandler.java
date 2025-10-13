package com._02vue.demo.user.EnumHandler;

import com._02vue.demo.user.pojo.entity.Gender;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class GenderHandler extends BaseTypeHandler<String> {


    @Override
    public void setNonNullParameter(
            PreparedStatement ps, int i,
            String gender, JdbcType jdbcType) throws SQLException
    {
//        ps.setString(i,gender);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        log.debug("调用此方法1……");
        String value = rs.getString(columnName);
        System.out.println(columnName);
        return convert(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        log.debug("调用此方法2……");
        String value = rs.getNString(columnIndex);
        return convert(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        log.debug("调用此方法3……");
        String string = cs.getString(columnIndex);
        return convert(string);
    }

    private String convert(String value) {
        if(value == null) return null;
        for (Gender g : Gender.values()) {
            if (g.getMessage().equals(value)) {
                return g.getCode();
            }
        }
        throw new IllegalArgumentException("未知性别"+value);
    }
}
