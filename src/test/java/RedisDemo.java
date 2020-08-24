import burgxun.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisDemo
 * @Auther burgxun
 * @Description: 利用Redis做 一个短信超发的验证
 * @Date 2020/08/21 11:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisDemo {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLimitSendSMS() throws InterruptedException {
        Integer[] arr = new Integer[]{1, 8, 9, 10, 11, 12, 13};
        List<Integer> integerList = Arrays.asList(arr);
        for (int i = 0; i < 20; i++) {
            if (integerList.contains(i)) {
                String user = "burgxun";
                System.out.printf("当前用户 %s 开始发送短信。。。,当前发送的时间是：%s ", user, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                boolean isOK = LimitSendSmsLast(user, 5);
                if (isOK) {
                    System.out.printf("用户 %s 发送短信成功\n", user);
                }
            }
            Thread.sleep(1000);
        }
    }


    private boolean LimitSendSmsFirst(String user, long maxSendCountForHour) {
        String redisKey = String.format("LimitSendFirst-%s", user);
        long keyCount = redisTemplate.opsForValue().increment(redisKey, 1);
        if (keyCount == 1) {
            redisTemplate.expire(redisKey, (10), TimeUnit.SECONDS);
        }
        if (keyCount > maxSendCountForHour) {
            System.out.printf("当前用户 %s 发送短信已经超每小时 %d 次上限\n", user, maxSendCountForHour);
            return false;
        }
        return true;
    }


    private boolean LimitSendSmsLast(String user, long maxSendCountForHour) {
        String canSendRedisKey = String.format("CanSendSms-%s", user);
        String isCanSendSms = stringRedisTemplate.opsForValue().get(canSendRedisKey);
        if (isCanSendSms != null && isCanSendSms.equals("1")) {
            System.out.printf("当前用户 %s 发送短信已经超每小时 %d 次上限\n", user, maxSendCountForHour);
            return false;
        }
        String redisKey = String.format("LimitSendLast-%s", user);
        long keyCount = redisTemplate.opsForList().size(redisKey);
        if (keyCount < maxSendCountForHour) {
            long redisTimeValue = System.currentTimeMillis();
            redisTemplate.opsForList().leftPush(redisKey, redisTimeValue);
        } else {
            long value = (long) redisTemplate.opsForList().rightPop(redisKey);
            long timeInterval = (System.currentTimeMillis() - value) / (1000);
            if (timeInterval > 10) {
                System.out.printf("当前用户 %s 发送短信已经超每小时 %d 次上限\n", user, maxSendCountForHour);

                stringRedisTemplate.opsForValue().set(canSendRedisKey, "1", Duration.ofHours(24));
                return false;
            }
        }
        return true;
    }
}
