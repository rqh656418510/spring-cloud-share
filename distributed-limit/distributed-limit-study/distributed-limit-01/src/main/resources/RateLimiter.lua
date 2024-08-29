-- 获取KEY，针对特定的接口进行限流
local key = KEYS[1]

-- 获取在限流注解上标注的限流次数
local limit = tonumber(ARGV[1])

-- 获取当前已访问接口的次数
local curentLimit = tonumber(redis.call('get', key) or "0")

-- 超过限流次数直接返回零，否则递增访问次数
if curentLimit + 1 > limit
then return -1
else
    -- 自增长 1
    redis.call('INCRBY', key, 1)
    -- 设置过期时间
    redis.call('EXPIRE', key, ARGV[2])
    return curentLimit + 1
end

-- Use example: @RedisLimitAnnotation(key = "redisLimit", limit = 2, expire = 1, msg = "当前排队人数较多，请稍后再试！")
