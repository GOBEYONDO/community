package cn.jsy.community.service.impl;

import cn.jsy.community.dao.TagDao;
import cn.jsy.community.model.entity.Tag;
import cn.jsy.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Transactional
    @Override
    public void saveTagAndQuestionRelation(String tagStr,String question_random_id) {
        String[] tags = tagStr.split(",");
        List<Integer> tagIds =new ArrayList<Integer>();
        for (String tag : tags) {
            Integer tag_id = tagDao.findByName(tag);
            if (tag_id==null){
                Tag tag1 =new Tag();
                tag1.setTag_name(tag);
                tagDao.save(tag1);
            }
            tagDao.saveQuestionAndTagsRelation(tagDao.findByName(tag),question_random_id);
        }
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }
}
