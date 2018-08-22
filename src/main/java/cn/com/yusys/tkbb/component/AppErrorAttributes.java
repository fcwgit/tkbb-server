package cn.com.yusys.tkbb.component;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//给容器中加入我们自己定义的ErrorAttributes
//@Component
public class AppErrorAttributes extends DefaultErrorAttributes {

    //返回的map就是页面和JSON能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        //webRequest对HTTPServletRequest进行了包装
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","yusys");
        //从请求域中获取ext
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.putAll(ext);
        return map;
    }
}
