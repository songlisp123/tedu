package com.weibo.demo.windowHandle;

import java.time.LocalDateTime;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustonFormatter extends Formatter {

    private static Long messageId;
    static {
        messageId = 0L;
    }
    @Override
    public String format(LogRecord record) {
        messageId++;
        LocalDateTime now = LocalDateTime.now();
        String time = now.toLocalTime()+"  "+now.toLocalTime();
        String method = record.getSourceClassName() + record.getSourceMethodName();
        String level = record.getLevel().toString().toLowerCase();
        String message = record.getMessage();
        return """
                序列号[%d]  时间：[%s]  方法名:[%s]
                信息级别:[%s]  详情:[%s]
                """.formatted(messageId,time,method,level,message);
    }
}
