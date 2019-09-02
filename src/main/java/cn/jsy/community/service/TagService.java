package cn.jsy.community.service;

import cn.jsy.community.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    public void saveTagAndQuestionRelation(String tagStr,String question_random_id);
    public List<Tag> findAll();

}
