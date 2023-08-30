package snippets.springboot.springboot_snippets.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    
}
