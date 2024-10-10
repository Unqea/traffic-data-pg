package com.traffic.common;

/**
 * 岗位管理业务异常定义
 *
 * @author jiucheng-ccq
 * @version V1.0
 * @date 2021-05-20 10:39:11
 **/
public enum LoginErrorEnum {
    INTERNAL_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INTERNAL_ERROR", "内部错误"),
    PARAM_INVALID_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "PARAM_INVALID_ERROR", "参数 %s 不正确"),
    UNSUPPORT_LOGIN_TYPE(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "UNSUPPORT_LOGIN_TYPE", "不支持的登录方式"),
    USER_OR_PASSWORD_IS_EMPTY_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "USER_OR_PASSWORD_IS_EMPTY_ERROR", "用户名或密码为空"),
    USER_OR_PASSWORD_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "USER_OR_PASSOWRD_ERROR", "用户名或密码错误"),
    TOO_MANY_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "TOO_MANY_ERROR", "输入错误次数太多，请稍后再试"),
    ACCOUNT_DISABLED(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "ACCOUNT_DISABLED", "您的账号已被停用！请联系管理员"),
    ACCOUNT_DORMANT(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "ACCOUNT_DORMANT", "您的账号三个月未登录已被休眠！请联系管理员重新激活"),
    ACCOUNT_NOT_EXIST(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "ACCOUNT_NOT_EXIST", "账号不存在!"),
    DINGTALK_ACCOUNT_NOT_EXIST(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "DINGTALK_ACCOUNT_NOT_EXIST", "钉钉组织用不存在该用户的UID!"),
    GRANT_TYPE_NOT_SUPPORT (ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "GRANT_TYPE_NOT_SUPPORT", "不支持的授权方式"),
    REQUEST_GRANT_TYPE_ERROR (ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "REQUEST_GRANT_TYPE_ERROR", "当前请求的reponseType 与应用配置的授权方式不匹配"),
    ENCRYPTION_METHOD_NOT_SUPPORT (ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "ENCRYPTION_METHOD_NOT_SUPPORT", "不支持的加密方式"),
    TICKET_NOT_FOUND (ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "TICKET_NOT_FOUND", "验证密钥不存在或已过期"),
    USER_NOT_LOGGED_IN(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "USER_NOT_LOGGED_IN", "用户未登录"),
    INVALID_SP_ENTITY_ID(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_SP_ENTITY_ID", "sp_entity_id 未注册"),
    INVALID_CLIENT_ID(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_CLIENT_ID", "ClientId(AppKey) 未注册"),
    INVALID_SAML_CODE(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_SAML_CODE", "无效的SAML code"),
    INVALID_SAML_PARAMS(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_SAML_PARAMS", "无效的SAML 参数"),
    OLD_PASSWORD_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "11001", "用户名或旧密码错误"),
    NEW_PASSWORD_FORMAT_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "11002", "新密码格式错误"),
    PASSWORD_CONTAINS_USER_INFO(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "11003", "新密码不允许包含用户信息（code、手机号、身份证号）"),
    PASSWORD_EQUTAL_TO_OLD_PASSWORD(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "11003", "新密码不允许与历史密码相同"),


    INVALID_AUTH_CODE(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_AUTH_CODE", "无效的授权码"),
    INVALID_APP_KEY_OR_SECRET(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "INVALID_APP_KEY_OR_SECRET", "APPKEY或APPSECRET无效"),

    PRIVATEDINGTALK_ACCESS_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "PRIVATEDINGTALK_ACCESS_ERROR", "未获取到accessToken!"),
    PRIVATEDINGTALK_AUTHCODE_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.ERROR, "PRIVATEDINGTALK_AUTHCODE_ERROR", "授权码已过期或者不正确!"),

    ;

    private static final String SYSTEM = "001";
    private final ErrorTypeEnum type;
    private final ErrorLevelEnum level;
    private final String detailCode;
    private final String description;

    private LoginErrorEnum(ErrorTypeEnum type, ErrorLevelEnum level, String detailCode, String description) {
        this.type = type;
        this.level = level;
        this.detailCode = detailCode;
        this.description = description;
    }

    public ErrorTypeEnum getType() {
        return this.type;
    }

    public ErrorLevelEnum getLevel() {
        return this.level;
    }

    public String getSystem() {
        return "001";
    }

    public String getDetailCode() {
        return this.detailCode;
    }

    public String getDescription() {
        return this.description;
    }

    public String toCode() {
        return String.format("%d%d%s%s", this.getType().getCode(), this.getLevel().getCode(), this.getSystem(), this.getDetailCode());
    }

    public static enum ErrorTypeEnum {
        BUSINESS(1),
        SYSTEM(3),
        THIRD_PARTY(5);

        private final int code;

        private ErrorTypeEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    public static enum ErrorLevelEnum {
        INFO(1),
        WARN(3),
        ERROR(5);

        private final int code;

        private ErrorLevelEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }
}
