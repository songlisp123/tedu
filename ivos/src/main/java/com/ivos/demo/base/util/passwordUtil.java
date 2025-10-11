package com.ivos.demo.base.util;

import java.security.SecureRandom;

public class passwordUtil {
    //定义允许出现在新密码中的字符,包含:大小写字母,数字,特殊字符
    private static final String ALLOWED_CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*.-_=+";
    //创建一个安全的随机数生成器,用于生成随机下标
    private static final SecureRandom random = new SecureRandom();

    /* 创建一个生成指定长度随机密码串的方法 */
    public static String generateRandomPassword(int length) {
        //如果长度小于5,抛出异常
        if (length < 5) {
            throw new RuntimeException("密码长度不能小于5");
        }
        //创建一个字符串拼接工具
        StringBuilder password = new StringBuilder();
        //循环生成随机字符,直到达到指定长度
        for (int i = 0; i < length; i++) {
            //生成一个随机下标,用于从ALLOWED_CHARS中获取字符
            int index = random.nextInt(ALLOWED_CHARS.length());
            //从ALLOWED_CHARS中获取随机字符,并添加到密码中
            password.append(ALLOWED_CHARS.charAt(index));
        }
        //返回生成的随机密码
        return password.toString();
    }
}
