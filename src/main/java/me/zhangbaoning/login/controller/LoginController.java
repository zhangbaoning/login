package me.zhangbaoning.login.controller;

import me.zhangbaoning.login.entity.User;
import me.zhangbaoning.login.entity.WechatVO;
import me.zhangbaoning.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.net.HttpURLConnection;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: TODO
 */
@Controller
public class LoginController {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user) {

        ModelAndView view=new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getIdCard(),user.getFullName());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            view.setViewName("index");
        }

        view.setViewName("myappointment");

        return view;
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(WechatVO wechatVO) {
        ModelAndView view=new ModelAndView();

        System.out.println(wechatVO);
        view.addObject("code",wechatVO.getCode());
        view.setViewName("index");
        RestTemplate rest = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc0a643110084e6ba&secret=33b1bf4e00e68e3eae1370d3d2ac9670&code="+wechatVO.getCode()+"&grant_type=authorization_code";
        ResponseEntity<String> entity =  rest.getForEntity(url, String.class);
        JSONObject jsonObject = new JSONObject(entity.getBody());
        String openid = (String) jsonObject.get("openid");
        view.addObject("openid",openid);

        return view;
    }
    @RequestMapping(value = "/wxIndex", method = RequestMethod.GET)
    public String wxIndex() {
        return "wxIndex";
    }
}
