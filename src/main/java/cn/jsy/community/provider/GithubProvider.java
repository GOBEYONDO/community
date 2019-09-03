package cn.jsy.community.provider;

import cn.jsy.community.model.dto.AccessTokenDTO;
import cn.jsy.community.model.dto.GitHubUserDTO;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
  private final ObjectMapper objectMapper =new ObjectMapper();
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String s = response.body().string();
                System.out.println(s);
                s=s.split("&")[0].split("=")[1];
                System.out.println(s);
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    public GitHubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            System.out.println(s);
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(s, GitHubUserDTO.class);
            return gitHubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
