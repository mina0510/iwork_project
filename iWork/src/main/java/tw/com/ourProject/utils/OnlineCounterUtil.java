package tw.com.ourProject.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class OnlineCounterUtil {
	
    private static Map<String, Object> countMap = new ConcurrentHashMap<String,Object>();


    public void insertToken(String token){
        //獲得當前時間(毫秒)
        long currentTime = System.currentTimeMillis();
        //解析token，獲得簽發時間
        Claims claims = null;
        try {
            claims = JWTUtil.getClaimsFromJwtToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token不存在或已過期");
        }
        Date issuedAt = claims.getIssuedAt();
        //以簽發時間爲key。當前時間+60s爲value存入countMap中
        countMap.put(issuedAt.toString(),currentTime+60*1000);
    }
    public Integer getOnlineCount(){
        int onlineCount = 0;
        //獲取countMap的迭代器
        Iterator iterator = countMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object>  entry = (Map.Entry<String, Object>) iterator.next();
            Long value = (Long) entry.getValue();
            if (value > System.currentTimeMillis()) {
                //過期時間大於當前時間則沒有過期
                onlineCount++;
            }

        }


        return onlineCount;
    }
    
    
}
