package cn.com.yusys.tkbb.controller;

import cn.com.yusys.tkbb.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello111222";
    }

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public String login(UserVo userVo, Map<String,Object> map, HttpSession session){
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("userVo",userVo);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
