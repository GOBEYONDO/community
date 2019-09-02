package cn.jsy.community.model.dto;

import cn.jsy.community.enums.CustomErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO =new ResultDTO();
        resultDTO.setMessage(message);
        resultDTO.setCode(code);
        return resultDTO;
    }
    public static ResultDTO errorOf(CustomErrorCode customErrorCode){
       return errorOf(customErrorCode.getCode(),customErrorCode.getMessage());
    }
}
