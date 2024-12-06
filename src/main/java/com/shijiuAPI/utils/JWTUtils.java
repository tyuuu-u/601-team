package com.shijiuAPI.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String jwtToken = "orderSystem";

    public static String createToken(String openid){
        Map<String,Object> claims = new HashMap<>();
        //存入userId，店主的id
        //存入shopId，店主的店铺id
//        claims.put("userId",userId);
//        claims.put("shopId",shopId);
        //存入用户的openid
        claims.put("openid",openid);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken
                .setClaims(claims) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                //24小时  24 * 60 * 60 * 1000
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        return jwtBuilder.compact();
    }

    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> getUserIdAndShopIdByToken(String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        System.out.println(map.get("userId"));
        System.out.println(map.get("shopId"));
        return map;
    }

    public static void main(String[] args) {
        String token = JWTUtils.createToken("999,111");
//        System.out.println(token);
        Map<String, Object> map = JWTUtils.checkToken(token);

        System.out.println(map.get("openid"));
    }

}