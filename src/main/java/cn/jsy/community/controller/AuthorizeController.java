package cn.jsy.community.controller;

import cn.jsy.community.model.dto.AccessTokenDTO;
import cn.jsy.community.entity.GitHubUserEntity;
import cn.jsy.community.entity.User;
import cn.jsy.community.provider.GithubProvider;
import cn.jsy.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.Redirect_uri}")
    private String redirect_uri;
    @Autowired
    private UserService userService;
    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code ,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response, HttpServletRequest request){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUserEntity gitUser = githubProvider.getUser(accessToken);

        if (gitUser!=null){
            request.getSession().setAttribute("user",gitUser);
            User user = userService.findByAccountId(String.valueOf(gitUser.getId()));
            if (user==null){
                user = new User();
                user.setName(gitUser.getName());
                user.setAccountId(String.valueOf(gitUser.getId()));
                user.setToken(UUID.randomUUID().toString());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setAvatar_url(gitUser.getAvatar_url());
                userService.save(user);
            }
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";
    }
}
