package cn.jsy.community.enums;

public enum CommentErrorCode implements CustomErrorCode {

    COMMENT_NOT_LOGIN(100,"请登录，再回复"),
    COMMENT_NOT_QUESTION(101,"您回复的问题或评论已经被删除了"),
    COMMENT_SUCCESS(200,"回复成功");
    private Integer code;
    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CommentErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
