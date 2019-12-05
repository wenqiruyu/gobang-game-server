package com.game.gobang.enums;

/**
 * 项目名称：venus-admin-server
 * 类名称：ResultEnum
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
public enum ResultEnum {

    SUCCESS(200L, "成功"),
    FAIL(400L, "失败"),
    INVALID_AUTHCODE(401L, "未授权"),
    SYS_ERROR(402L, "系统错误"),
    PARAMS_ERROR(403L, "参数错误 "),
    PARAMS_ERROR_FORMAT(403001L, "参数传递方式不正确, 请使用application/json传递参数"),
    PARAMS_ERROR_NULL(403002L, "参数为空"),

    UNKNOWN_USER(1000L, "用户名不存在或密码错误"),
    INVALID_ACCESS(1001L, "用户无权访问"),
    USER_NEED_AUTHORITIES(1002L, "用户未进行登录"),
    USER_LOGOUT_SUCCESS(1003L, "用户注销登录成功");

    private Long code;

    private String msg;

    ResultEnum(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 根据code返回枚举值
     *
     * @param code 返回码
     * @return com.server.venus.enums.ResultEnum
     * @author yingx
     * @date 2019/11/29
     */

    public static ResultEnum parse(String code) {
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("该code的枚举值不存在");
    }
}
