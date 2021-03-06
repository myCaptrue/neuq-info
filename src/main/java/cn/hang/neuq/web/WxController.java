package cn.hang.neuq.web;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.entity.vo.DecodeUserInfoVO;
import cn.hang.neuq.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 11:10
 **/
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {
    @Autowired
    private WxService wxService;

    @GetMapping("/login")
    public Response login(@RequestParam(required = true, value = "code") String wxCode) {
        log.info("/wx/login code:{}", wxCode);
        return wxService.getWxSession(wxCode);
    }

    @PostMapping("/decodeUserInfo")
    public Response decodeUserInfo(@RequestBody DecodeUserInfoVO decodeUserInfoVO) {
        return wxService.decodeUserInfo(decodeUserInfoVO);
    }

    @GetMapping("/checkToken")
    public Response checkToken() {
        return Response.success();
    }

}
