package com.traffic.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 *
 * @since 2022-01-25
 */
public class ApiResult<T> implements Serializable {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 响应时间
     */
    // @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time = new Date();

    public ApiResult() {}

    public static ApiResult result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail();
    }

    public static ApiResult result(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static ApiResult result(ApiCode apiCode, Object data) {
        return result(apiCode, null, data);
    }

    public static ApiResult result(ApiCode apiCode, String msg, Object data) {
        boolean success = false;
        if (apiCode.getCode() == ApiCode.SUCCESS.getCode()) {
            success = true;
        }
        String message = apiCode.getMsg();
        if (StringUtils.isNotBlank(msg)) {
            message = msg;
        }
        return ApiResult.builder().code(apiCode.getCode()).msg(message).data(data).success(success).time(new Date())
            .build();
    }

    public static ApiResult ok() {
        return ok(null);
    }

    public static ApiResult ok(Object data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static ApiResult ok(Object data, String msg) {
        return result(ApiCode.SUCCESS, msg, data);
    }

    public static ApiResult okMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return ok(map);
    }

    public static ApiResult fail(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static ApiResult fail(String msg) {
        return result(ApiCode.FAIL, msg, null);
    }

    public static ApiResult fail(ApiCode apiCode, Object data) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, data);
    }

    public static ApiResult fail(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return result(ApiCode.FAIL, map);
    }

    public static ApiResult fail() {
        return fail(ApiCode.FAIL);
    }

    // <editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public static class ApiResultBuilder<T> {
        @SuppressWarnings("all")
        private int code;
        @SuppressWarnings("all")
        private String msg;
        @SuppressWarnings("all")
        private boolean success;
        @SuppressWarnings("all")
        private T data;
        @SuppressWarnings("all")
        private Date time;

        @SuppressWarnings("all")
        ApiResultBuilder() {}

        /**
         * 响应码
         * 
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public ApiResultBuilder<T> code(final int code) {
            this.code = code;
            return this;
        }

        /**
         * 响应消息
         * 
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public ApiResultBuilder<T> msg(final String msg) {
            this.msg = msg;
            return this;
        }

        /**
         * 是否成功
         * 
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public ApiResultBuilder<T> success(final boolean success) {
            this.success = success;
            return this;
        }

        /**
         * 响应数据
         * 
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public ApiResultBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        /**
         * 响应时间
         * 
         * @return {@code this}.
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @SuppressWarnings("all")
        public ApiResultBuilder<T> time(final Date time) {
            this.time = time;
            return this;
        }

        @SuppressWarnings("all")
        public ApiResult<T> build() {
            return new ApiResult<T>(this.code, this.msg, this.success, this.data, this.time);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "ApiResult.ApiResultBuilder(code=" + this.code + ", msg=" + this.msg + ", success=" + this.success
                + ", data=" + this.data + ", time=" + this.time + ")";
        }
    }

    @SuppressWarnings("all")
    public static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<T>();
    }

    /**
     * 响应码
     */
    @SuppressWarnings("all")
    public int getCode() {
        return this.code;
    }

    /**
     * 响应消息
     */
    @SuppressWarnings("all")
    public String getMsg() {
        return this.msg;
    }

    /**
     * 是否成功
     */
    @SuppressWarnings("all")
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * 响应数据
     */
    @SuppressWarnings("all")
    public T getData() {
        return this.data;
    }

    /**
     * 响应时间
     */
    @SuppressWarnings("all")
    public Date getTime() {
        return this.time;
    }

    /**
     * 响应码
     * 
     * @return {@code this}.
     */
    @SuppressWarnings("all")
    public ApiResult<T> setCode(final int code) {
        this.code = code;
        return this;
    }

    /**
     * 响应消息
     * 
     * @return {@code this}.
     */
    @SuppressWarnings("all")
    public ApiResult<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 是否成功
     * 
     * @return {@code this}.
     */
    @SuppressWarnings("all")
    public ApiResult<T> setSuccess(final boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 响应数据
     * 
     * @return {@code this}.
     */
    @SuppressWarnings("all")
    public ApiResult<T> setData(final T data) {
        this.data = data;
        return this;
    }

    /**
     * 响应时间
     * 
     * @return {@code this}.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @SuppressWarnings("all")
    public ApiResult<T> setTime(final Date time) {
        this.time = time;
        return this;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ApiResult))
            return false;
        final ApiResult<?> other = (ApiResult<?>)o;
        if (!other.canEqual((Object)this))
            return false;
        if (this.getCode() != other.getCode())
            return false;
        if (this.isSuccess() != other.isSuccess())
            return false;
        final Object this$msg = this.getMsg();
        final Object other$msg = other.getMsg();
        if (this$msg == null ? other$msg != null : !this$msg.equals(other$msg))
            return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data))
            return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time))
            return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof ApiResult;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getCode();
        result = result * PRIME + (this.isSuccess() ? 79 : 97);
        final Object $msg = this.getMsg();
        result = result * PRIME + ($msg == null ? 43 : $msg.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "ApiResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", success=" + this.isSuccess()
            + ", data=" + this.getData() + ", time=" + this.getTime() + ")";
    }

    @SuppressWarnings("all")
    public ApiResult(final int code, final String msg, final boolean success, final T data, final Date time) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
        this.time = time;
    }
    // </editor-fold>
}
