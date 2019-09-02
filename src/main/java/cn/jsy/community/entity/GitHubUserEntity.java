package cn.jsy.community.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubUserEntity {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;

}
