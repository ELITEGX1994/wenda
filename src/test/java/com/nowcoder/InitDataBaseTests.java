package com.nowcoder;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
//@Sql("/init-schema.sql")
public class InitDataBaseTests {
    @Autowired
    UserDAO userDAO;
    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void initDataBase() {
        Random random = new Random();

        for (int i = 0; i < 11; ++i) {
                User user = new User();
                user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
                user.setName(String.format("USER%d", i + 11));
                user.setPassword("1111");
                user.setSalt("1111");
                userDAO.addUser(user);
                user.setPassword("newpassword");

                Question question = new Question();
                question.setCommentCount(i);
                Date date = new Date();
                date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
                question.setCreatedDate(date);
                question.setUserId(i + 11);
                question.setTitle(String.format("TITLE{%d}", i+11));
                question.setContent(String.format("Balaababalalalal Content %d", i+11));
                questionDAO.addQuestion(question);
            }
/*
        Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
        userDAO.deleteById(1);
        Assert.assertNull(userDAO.selectById(1));
        */
        }
}