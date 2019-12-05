package com.game.gobang.controller;

import com.game.gobang.socket.WebSocketHandle;
import com.game.gobang.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 项目名称：gobang-game-server
 * 类名称：ChatController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/5
 * 修改人：yingx
 * 修改时间： 2019/12/5
 * 修改备注：
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    /*//页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
*/
    //推送数据接口
    @ResponseBody
    @RequestMapping("/push")
    public ResultVO pushToWeb(String message) {

        try {
            WebSocketHandle.sendInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultVO();
    }
}
