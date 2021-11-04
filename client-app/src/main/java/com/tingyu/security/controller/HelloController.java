package com.tingyu.security.controller;

import com.tingyu.security.util.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HelloController {

    @Resource
    private TokenUtil tokenUtil;

    @RequestMapping("/index.html")
    public String hello(String code, Model model) {
        model.addAttribute("msg", tokenUtil.getData(code));
        return "index";
    }

}
