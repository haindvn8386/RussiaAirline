package rediscache.russiaairline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SessionService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveSession(String userId, String token)
    {
        redisTemplate.opsForValue().set("session:"+userId, token,30, TimeUnit.MINUTES);
    }

    public String getSession(String userId)
    {
        return (String) redisTemplate.opsForValue().get("session:"+userId);
    }
}
