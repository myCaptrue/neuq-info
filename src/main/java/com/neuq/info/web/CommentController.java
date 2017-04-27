package com.neuq.info.web;

import com.neuq.info.dao.CommentDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.service.CommentService;
import com.neuq.info.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static javafx.scene.input.KeyCode.R;

/**
 * Creaed by lihang on 2017/4/21.
 */
@Controller
@RequestMapping("/comment")
@Api(value = "评论相关API")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value ="/{postId}",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId提交评论", httpMethod = "POST", value = "根据postId提交评论")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId",value = "postId",paramType = "query",dataType ="string"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")

    })
    @ResponseBody
    public ResultModel addComment(@PathVariable("postId") long postId, HttpServletRequest request){
        Long userId= (Long)request.getAttribute("userId");
        String content=request.getParameter("content");
        return commentService.addComment(content,userId,postId);
    }

    @RequestMapping(value ="/{postid}",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "根据postId获取评论", httpMethod = "GET", value = "根据postId获取评论")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId",value = "postId",paramType = "query",dataType ="string"),
            @ApiImplicitParam(name = "session", value = "登陆后返回的3rd_session", required = true,paramType = "header",dataType = "string")

    })
    @ResponseBody
    public ResultModel list(@PathVariable("postid") long postid){
        return commentService.queryComment(postid);
    }
}