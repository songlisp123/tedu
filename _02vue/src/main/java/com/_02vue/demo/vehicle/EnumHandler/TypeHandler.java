package com._02vue.demo.vehicle.EnumHandler;

import com._02vue.demo.vehicle.pojo.entity.Status;
import com._02vue.demo.vehicle.pojo.entity.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class TypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps,
                                    int i, String parameter,
                                    JdbcType jdbcType) throws SQLException
    {
        //空体
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        log.debug("调用此方法:{}",columnName);
        String value = rs.getString(columnName);
        return convert(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        log.debug("进入到此方法……{}",columnIndex);
        String value = rs.getString(columnIndex);
        log.debug("当前值:{}",value);
        return convert(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        log.debug("调用此方法{}",columnIndex);
        String value = cs.getString(columnIndex);
        return convert(value);
    }

    private String convert(String value) {
       if (value == null) return null;
       for (Type s : Type.values()) {
           if (s.getCode().equals(value))
               return s.getMessage();
       }
       throw new IllegalArgumentException("参数不匹配");
    }
}
