package cn.com.yusys.tkbb.controller;

import cn.com.yusys.tkbb.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码
        request.setAttribute("javax.servlet.error.status_code",400);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        //将错误信息保存到Request中
        request.setAttribute("ext",map);
        //转发到/error实现自动适配错误
        return "forward:/error";
    }

    //@ResponseBody
    //@ExceptionHandler(UserNotExistException.class)
    //public Map<String,Object> handlerException(Exception e){
    //    Map<String,Object> map = new HashMap<>();
    //    map.put("code","user.notexist");
    //    map.put("message",e.getMessage());
    //    return map;
    //}


}
