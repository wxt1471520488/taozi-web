package code.collector;

import code.pojo.User;
import code.service.LoginService;
import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login/")
public class LoginCollector {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public GlobalResponse login(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "account") String account,
                                @RequestParam(value = "password") String password,@RequestParam(value = "is_remember",required = false,defaultValue = "true") boolean isRemember){
        return loginService.login(account, password,isRemember,request,response);
    }

    @RequestMapping("getLoginUser")
    @ResponseBody
    public GlobalResponse getLoginUser(HttpServletRequest request, HttpServletResponse response){
        User user = userService.getUserBySession(request);
        if(user == null){
            return GlobalResponse.createByError(-1,"user not login");
        }
        return GlobalResponse.createBySuccess(user);
    }

    @RequestMapping("loginOut")
    @ResponseBody
    public Object loginOut(HttpServletRequest request, HttpServletResponse response){
        return userService.loginOut(request,response);
    }
}
