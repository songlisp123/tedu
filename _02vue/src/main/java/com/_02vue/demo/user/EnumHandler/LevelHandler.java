package com._02vue.demo.user.EnumHandler;

import com._02vue.demo.user.pojo.entity.Level;
import com._02vue.demo.user.pojo.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class LevelHandler extends BaseTypeHandler<String> {


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
        System.out.println(value);
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
        for (Level s : Level.values()) {
            if (s.getMessage().equals(value)) {
                return s.getMessage();
            }
        }
        throw new IllegalArgumentException("非法状态");
    }
}
