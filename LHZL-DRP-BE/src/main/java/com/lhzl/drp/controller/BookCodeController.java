package com.lhzl.drp.controller;

import com.lhzl.drp.model.BookCode;
import com.lhzl.drp.model.Response;
import com.lhzl.drp.model.Userinfo;
import com.lhzl.drp.service.BookCodeService;
import com.lhzl.drp.util.DataBaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao on 2016/4/2.
 */

@Controller
@RequestMapping("/bookCodeController")
public class BookCodeController {

    private static final Logger logger = LoggerFactory.getLogger(BookCodeController.class);

    @Autowired
    private BookCodeService bookCodeService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/queryBookCode")
    @ResponseBody
    public Response queryBookCode(@RequestBody Map<String, Object> map) {
        List<BookCode> bookCodes = bookCodeService.queryBookCode(map);
        Response res = new Response().success(bookCodes);
        if (map.containsKey("count")) {
            res.setCount((Integer) map.get("count"));
        }
        return res;
    }

    @RequestMapping("/queryBookCodeById")
    @ResponseBody
    public Response queryBookCodeById(long codeid) {
        BookCode bookCode = bookCodeService.queryBookCodeById(codeid);
        return new Response().success(bookCode);
    }

    @RequestMapping("/insertBookCode")
    @ResponseBody
    public Response insertBookCode(@RequestBody @Valid BookCode bookCode) {
        DataBaseUtil.setCreateInfo(bookCode, (String) request.getAttribute("opacct"));
        bookCodeService.insertBookCode(bookCode);
        return new Response().success();
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Response updateBookCode(@RequestBody @Valid BookCode bookCode) {
        DataBaseUtil.setCreateInfo(bookCode, (String) request.getAttribute("opacct"));
        bookCodeService.updateBookCode(bookCode);
        return new Response().success();
    }

    @RequestMapping("/deleteBookCode")
    @ResponseBody
    public Response deleteUser(long codeid) {
        bookCodeService.deleteBookCode(codeid);
        return new Response().success();
    }

}
