package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ServerServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class ServerTableController {
    private static Logger logger = LoggerFactory.getLogger(ServerTableController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ServerServiceImpl serverServiceImpl;

    @CrossOrigin
    @RequestMapping("/getAllServer")
    public Result getAllServer(@RequestBody Map map) {
        Result re = serverServiceImpl.getAllServer(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping("/addServer")
    public Result addServer(@RequestBody Map map) {
        Result re = serverServiceImpl.addServer(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/editServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editServer(@RequestBody Map map) {
        Result re = serverServiceImpl.editServer(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getAllPlatformList")
    public Result getAllPlatformList(@RequestBody Map map) {
        Result re = serverServiceImpl.getAllPlatformList(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToNormal_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Server(@RequestBody Map map) {
        Result re = serverServiceImpl.changeStateToNormal_Server(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping(value = "/changeStateToFrozen_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Server(@RequestBody Map map) {
        Result re = serverServiceImpl.changeStateToFrozen_Server(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/deleteServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteServer(@RequestBody Map map) {
        Result re = serverServiceImpl.deleteServer(map);
        return re;
    }

    @CrossOrigin
    @Transactional
    @RequestMapping(value = "/deleteAllServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllServer(@RequestBody Map<String, String> map) {
        Result re = serverServiceImpl.deleteAllServer(map);
        return re;
    }


    /**
      * 〈获取某渠道平台上的所有服务器列表〉
      * 〈通过平台的PlatformId查找到该渠道的所以服务器列表〉
      * @param  [map]   [获取一个hashMap对象，从中取得键值为id的值(platformIm)]
      * @return [Result对象，包含响应码code，信息message和数据data，data是包含list的结果集]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @CrossOrigin
    @RequestMapping("/getServerListForPlatform")
    public Result getServerListForPlatform(@RequestBody Map map) {
        Result re = serverServiceImpl.getServerListForPlatform(map);
        return re;
    }

    /**
      * 〈获取所有服务器树状结构〉
      * 〈获取所有服务器树状结构〉
      * @param  [map]   [获取一个hashMap对象，从中取得键值为id的值(platformIm)]
      * @return [Result对象，包含响应码code，信息message和数据data，data是包含list的结果集]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @CrossOrigin
    @RequestMapping("/getServerTree")
    public Result getServerTree(@RequestBody Map map) {
        Result re = serverServiceImpl.getServerTree(map);
        return re;
    }

    /***
     * 同步服务器列表
     * @param map
     * @return
     */
    @CrossOrigin //跨域
    @Transactional //事务
    @RequestMapping("/api/server/SynServerList")
    public Result SynServerList(@RequestBody Map map) {
        Result re = serverServiceImpl.SynServerList(map);
        return re;
    }

    /***
     * 设置默认服务器
     * @param map
     * @return
     */
    @CrossOrigin
    @Transactional
    @RequestMapping("/api/server/setDefaultServer")
    public Result setDefaultServer(@RequestBody Map map) {
        Result re = serverServiceImpl.setDefaultServer(map);
        return re;
    }

    /***
     * 设置服务器状态
     * @param map
     * @return
     */
    @CrossOrigin
    @Transactional
    @RequestMapping("/api/server/ChangeState")
    public Result ChangeState(@RequestBody Map map) {
        Result re = serverServiceImpl.ChangeState(map);
        return re;
    }


    /***
     * 平台获取服务器列表
     * @param
     * @return
     */
    @CrossOrigin
    @Transactional
    @RequestMapping("/api/server/getServerList")
    public String getServerList(HttpServletRequest request) {
        String channel = (request.getParameter("channel") != null ? request.getParameter("channel") : "null");
        String gameId = (request.getParameter("gameId") != null ? request.getParameter("gameId") : "null");
        String platform = (request.getParameter("platform") != null ? request.getParameter("platform") : "null");
        try {
            channel = URLDecoder.decode(channel, "UTF-8");
            platform = URLDecoder.decode(platform, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String re = serverServiceImpl.getServerList(gameId + ";" + platform + ";" + channel);
        return re;
    }
}
