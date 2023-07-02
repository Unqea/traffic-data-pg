
package com.traffic.common;

/**
 * <p>
 * REST API 响应码
 * </p>
 *
 *
 * @since 2022-01-25
 */
public enum ApiCode {

    SUCCESS(200, "操作成功"),

    UNAUTHORIZED(401, "非法访问"),

    NOT_PERMISSION(403, "没有权限"),

    NOT_FOUND(404, "你请求的资源不存在"),

    FAIL(500, "操作失败"),

    LOGIN_EXCEPTION(4000, "登陆失败"),

    SYSTEM_EXCEPTION(5000, "系统异常!"),

    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),

    PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常"),

    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP Media 类型异常"),

    BUSINESS_EXCEPTION(5101, "业务处理异常"),

    VERIFICATION_CODE_EXCEPTION(5103, "验证码校验异常"),

    ROLE_EXCEPTION(5104, "角色异常"),

    UNAUTHENTICATED_EXCEPTION(5105, "没有访问权限"),

    UNAUTHORIZED_EXCEPTION(5106, "没有访问权限"),

    EXISTSNAME_EXCEPTION(5108, "用户名已存在"),

    NO_TOKEN_EXCEPTION(4001, "没有Token")

    ;

    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
