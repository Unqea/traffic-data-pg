package com.traffic.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.traffic.common.ApiResult;
import com.traffic.common.LoginErrorEnum;
import com.traffic.common.PgException;
import com.traffic.entity.TableProperties;
import com.traffic.entity.TbUser;
import com.traffic.mapper.TbUserMapper;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.service.TablePropertiesService;
import com.traffic.vo.TablePropertiesQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录 API")
public class UserController {

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private RedisTemplate<String,Integer> redisTemplate;

    /**
     * 登录
     */
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

        if (ObjUtil.isEmpty(tbUser)){
            newCheckLoginError(user.getUsername());
        }
        return ApiResult.ok("登录成功");
    }


    /**
     * 密码失败次数策略校验
     * @param username 用户名
     */
    private void newCheckLoginError(String username){
        long isTime = 20;
        Integer retryCountNum = redisTemplate.opsForValue().get(username);
        if(retryCountNum == null){
            redisTemplate.opsForValue().set(username, 1, isTime,TimeUnit.SECONDS);
        }else {
            redisTemplate.opsForValue().increment(username, 1);
            redisTemplate.expire(username, isTime, TimeUnit.SECONDS);
        }
        throw new PgException(LoginErrorEnum.USER_OR_PASSWORD_ERROR.getDetailCode(), LoginErrorEnum.USER_OR_PASSWORD_ERROR.getDescription());
    }



    /**
     * 密码失败次数策略校验
     * @param username 用户名
     */
/*    private void newCheckLoginError(String username){
        long isTime = 10;
        int retryCount = 1;
        Integer retryCountNum = redisTemplate.opsForValue().get(username);
        if(retryCountNum != null){
            retryCount = retryCountNum;
            retryCount ++;
        }
        redisTemplate.opsForValue().set(username, retryCount, isTime,TimeUnit.SECONDS);
        throw new PgException(LoginErrorEnum.USER_OR_PASSWORD_ERROR.getDetailCode(), LoginErrorEnum.USER_OR_PASSWORD_ERROR.getDescription());
    }*/










}

