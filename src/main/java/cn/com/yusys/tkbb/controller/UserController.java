package cn.com.yusys.tkbb.controller;

import cn.com.yusys.tkbb.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello111222";
    }

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public String login(UserVo userVo){
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            return "dashboard";
        }else {
            return "login";
        }

    }
}
