package com.cdk.controller;

import com.cdk.result.Result;
import com.cdk.service.impl.ServerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.transaction.Transactional;

@RestController
public class ServerTableController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ServerServiceImpl serverServiceImpl;

    @RequestMapping("/getAllServer")
    public Result getAllServer(@RequestBody Map map) {
        Result re = serverServiceImpl.getAllServer(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping("/addServer")
    public Result addServer(@RequestBody Map map) {
        Result re = serverServiceImpl.addServer(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/editServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editServer(@RequestBody Map map) {
        Result re = serverServiceImpl.editServer(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/getAllPlatformList")
    public Result getAllPlatformList(@RequestBody Map map) {
        Result re = serverServiceImpl.getAllPlatformList(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToNormal_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Server(@RequestBody Map map) {
        Result re = serverServiceImpl.changeStateToNormal_Server(map);
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToFrozen_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Server(@RequestBody Map map) {
        Result re = serverServiceImpl.changeStateToFrozen_Server(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deleteServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteServer(@RequestBody Map map) {
        Result re = serverServiceImpl.deleteServer(map);
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deleteAllServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllServer(@RequestBody Map<String, String> map) {
        Result re = serverServiceImpl.deleteAllServer(map);
        System.out.println(Divider);
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
    @RequestMapping("/getServerListForPlatform")
    public Result getServerListForPlatform(@RequestBody Map map) {
        Result re = serverServiceImpl.getServerListForPlatform(map);
        System.out.println(Divider);
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
    @RequestMapping("/getServerTree")
    public Result getServerTree(@RequestBody Map map) {
        Result re = serverServiceImpl.getServerTree(map);
        System.out.println(Divider);
        return re;
    }


}
