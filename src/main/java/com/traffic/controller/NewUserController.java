/*
package com.traffic.controller;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.traffic.common.ApiResult;
import com.traffic.common.LoginErrorEnum;
import com.traffic.common.PgException;
import com.traffic.entity.TbUser;
import com.traffic.mapper.TbUserMapper;
import com.traffic.vo.TablePropertiesQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/newUser")
@Api(tags = "新用户登录 API")
public class NewUserController {

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private RedisTemplate<String,Integer> redisTemplate;

    */
/**
     * 登录
     *//*

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录", response = TablePropertiesQueryVo.class)
    public ApiResult<String> login(@RequestBody TbUser user) throws Exception {
        Integer retryCountNum = redisTemplate.opsForValue().get(user.getUsername());
        if(retryCountNum != null && retryCountNum >= 5){
            Long expire = redisTemplate.getExpire(user.getUsername(), TimeUnit.SECONDS);
            throw new PgException(LoginErrorEnum.TOO_MANY_ERROR.getDetailCode(), "请" + expire + "秒后再试");
        }
        LambdaUpdateWrapper<TbUser> newWrapper = new LambdaUpdateWrapper<>();
        newWrapper.eq(TbUser::getUsername,user.getUsername());
        newWrapper.eq(TbUser::getPassword,user.getPassword());
        TbUser tbUser = tbUserMapper.selectOne(newWrapper);


        return ApiResult.ok("登录成功");
    }



    */
/**
     * 密码失败次数策略校验
     * @param username 用户名
     *//*

    private void checkLogin(String username) {
        int retryCount = getRetryCount(username);
        if (retryCount >= 5) {
            Long expire = redisTemplate.getExpire(username, TimeUnit.SECONDS);
            throw new PgException(LoginErrorEnum.TOO_MANY_ERROR.getDetailCode(), "请" + expire + "秒后再试");
        }
    }

    */
/**
     * 获取密码重试次数
     * @param username 用户名
     * @return 重试次数
     *//*

    private int getRetryCount(String username) {
        Integer retryCountNum = redisTemplate.opsForValue().get(username);
        return retryCountNum != null ? retryCountNum : 0;
    }

    */
/**
     * 记录密码重试次数
     * @param username 用户名
     *//*

    private void recordRetryCount(String username) {
        redisTemplate.opsForValue().increment(username, 1);
        redisTemplate.expire(username, 10, TimeUnit.SECONDS);
    }









}

*/
