package com.module.controller;

import com.module.dao.DaoConnection;
import com.module.entry.Question;
import com.module.entry.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    private User user;
    private List<Question> questions;
    private DaoConnection connection = new DaoConnection();
    private int questionInd;

    @RequestMapping(value ="/start", params = {"name"})
    public void startQuestions(@RequestParam(value = "name") String name){
        user = connection.getUser(name);
        questions = connection.getQuestions();
        questionInd = 0;
    }
}
