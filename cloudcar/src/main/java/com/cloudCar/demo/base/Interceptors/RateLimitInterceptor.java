package com.cloudCar.demo.base.Interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Slf4j
/**
 * 网站限流拦截器
 */
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_MINUTE = 3;
    private static final long WINDOW_SIZE_SECONDS = 60;
    private static final Logger logger = Logger.getLogger("car");

    // {"192.168.1.11": 1, "192.168.1.12": 1, "192.168.1.13": 2}
    private final ConcurrentHashMap<String, Integer> clientCounts = new ConcurrentHashMap<>();
    // {"192.168.1.11": "2024-01-01 14:00:00", "192.168.1.12": "2024-01-01 14:30:30", ...}
    private final ConcurrentHashMap<String, LocalDateTime> lastResetTimes = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {
        logger.info("进入到权限限流拦截器……");
        //获取客户端的IP地址
        String clientId = request.getRemoteAddr();

        // 尝试获取上次重置时间
        LocalDateTime lastResetTime = lastResetTimes.get(clientId);
        // 如果上次重置时间不存在或已过期，则重置计数器和时间
        if (lastResetTime == null || LocalDateTime.now().isAfter(lastResetTime.plusSeconds(WINDOW_SIZE_SECONDS))) {
            clientCounts.put(clientId, 0);
            lastResetTimes.put(clientId, LocalDateTime.now());
        }

        // 增加请求次数
        Integer count = clientCounts.get(clientId);
        count = count + 1;
        clientCounts.put(clientId, count);

        // 检查是否超过限制
        if (count > MAX_REQUESTS_PER_MINUTE) {
            logger.info("访问频繁");
            throw new RuntimeException("访问太频繁了");
        }

        return true;
    }
}
