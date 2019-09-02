package cn.jsy.community.enums;

public enum CustomErrorCodeEnum implements CustomErrorCode {

    QUESTION_NOT_FOUND("该问题找不到了!!"),
    COMMENT_NOT_LOGIN("请登录即可回复问题");
    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    CustomErrorCodeEnum(String message) {
        this.message = message;
    }

}
