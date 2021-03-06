package cn.jsy.community.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubUserDTO {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;
}
