package me.zhangbaoning.login.controller;

import me.zhangbaoning.login.entity.User;
import me.zhangbaoning.login.entity.WechatVO;
import me.zhangbaoning.login.service.OrderService;
import me.zhangbaoning.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.http.HttpClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @Value("${appID}")
    private String appID;
    @Value("${appsecret}")
    private String appsecret;
    /**
     * 登陆
     *
     * @param user 包含登陆信息的用户对象
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user) {
        ModelAndView view = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getIdCard(), user.getFullName());
        try {
            // 登陆成功的话，通过更新保存openid，并跳转到预约页面
            subject.login(token);
            service.updateByIdCard(user);
            view.setViewName("myappointment");
            List  orderList= orderService.getAll();
            view.addObject("orderList",orderList);

        } catch (AuthenticationException e) {
            // 失败的话，返回到登陆页面
            e.printStackTrace();
            view.setViewName("index");
        }
        return view;
    }

    /**
     * 微信回调跳转到主页
     * @param wechatVO 微信登陆参数
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(WechatVO wechatVO) {
        ModelAndView view = new ModelAndView();
        String openid = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if ("openid".equals(cookie.getName())) {
                    openid = cookie.getValue();
                }
            }
        }

        // 当openid不为空的话，通过cookie中的openid去查找用户信息
        if (openid != null) {
            User user = service.getByOpenid(openid);
            // 数据库中存在这个openid所对应的用户时，才能免登陆
            if (user != null) {
                return this.login(user);
            }
        }
        // 无openid或者openid查询不到对应的用户时，重新获取openid
        RestTemplate rest = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret="+appsecret+"&code=" + wechatVO.getCode() + "&grant_type=authorization_code";
        ResponseEntity<String> entity = rest.getForEntity(url, String.class);
        JSONObject jsonObject = new JSONObject(entity.getBody());
        openid = (String) jsonObject.get("openid");
        // 向index页面传入openid
        view.addObject("openid", openid);
        view.setViewName("/WEB-INF/jsp/index.ftl");
        return view;

    }
    @RequestMapping(value = "/wxIndex", method = RequestMethod.GET)
    public void wxIndex() {

        RestTemplate rest = new RestTemplate();
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc0a643110084e6ba&redirect_uri=http://192.168.9.185/login/index&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        ResponseEntity<String> entity = rest.getForEntity(url, String.class);
        System.out.println(entity.getBody());
    }
}
