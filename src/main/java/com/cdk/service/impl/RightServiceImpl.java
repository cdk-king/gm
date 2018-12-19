package com.cdk.service.impl;

import com.cdk.dao.impl.RightDaoImpl;
import com.cdk.entity.Right;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class RightServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(RightServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public RightDaoImpl rightDaoImpl;

    public Result addRight(Map map) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String rightName = (map.get("rightName") != null ? map.get("rightName").toString() : "");
        String rightTag = (map.get("rightTag") != null ? map.get("rightTag").toString() : "");
        String rightParentId = (map.get("rightParentId") != null ? map.get("rightParentId").toString() : "");
        String right_describe = (map.get("right_describe") != null ? map.get("right_describe").toString() : "");
        String rightSort = (map.get("rightSort") != null ? map.get("rightSort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        Right right = new Right();
        right.setRightName(rightName);
        right.setRight_describe(right_describe);
        right.setRightTag(rightTag);
        right.setAddUser(addUser);

        Result re;

        int temp = rightDaoImpl.addRight(right);
        if (temp > 0) {
            logger.info("权限添加成功");
            re = new Result(200, "权限添加成功", null);
        } else {
            logger.info("权限添加失败");
            re = new Result(400, "权限添加失败", null);
        }
        return re;
    }

    public Result getRight(Map map) {
        String rightName = (map.get("rightName") != null ? map.get("rightName").toString() : "");
        String rightTag = (map.get("rightTag") != null ? map.get("rightTag").toString() : "");
        String right_describe = (map.get("right_describe") != null ? map.get("right_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "0";
        }

        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        logger.info("pageNo：" + StrPageNo);
        logger.info("pageSize：" + StrPageSize);
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Right right = new Right();
        right.setRightName(rightName);
        right.setRight_describe(right_describe);
        right.setRightTag(rightTag);
        right.setState(Integer.parseInt(state));

        Result re;

        Map<String, Object> JsonMap = rightDaoImpl.getRight(right, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "权限列表获取失败", "");
        } else {
            re = new Result(200, "权限列表获取成功", JsonMap);
        }
        return re;
    }

    public Result editRight(Map map) {

        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String rightName = (map.get("rightName") != null ? map.get("rightName").toString() : "");
        String right_describe = (map.get("right_describe") != null ? map.get("right_describe").toString() : "");
        String rightTag = (map.get("rightTag") != null ? map.get("rightTag").toString() : "");
        String rightParentId = (map.get("rightParentId") != null ? map.get("rightParentId").toString() : "");
        String rightSort = (map.get("rightSort") != null ? map.get("rightSort").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");

        Right right = new Right();
        right.setId(Integer.parseInt(id));
        right.setRightName(rightName);
        right.setRight_describe(right_describe);
        right.setRightTag(rightTag);
        right.setAddUser(addUser);
        try {
            right.setRightSort(Integer.parseInt(sort));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Result re;
        int temp = rightDaoImpl.editRight(right);
        if (temp > 0) {
            logger.info("权限信息修改成功");
            re = new Result(200, "权限信息更新成功", null);
        } else {
            logger.info("权限信息修改失败");
            re = new Result(400, "权限信息更新失败", null);

        }
        return re;
    }

    public Result changeStateToFrozen_Right(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Right right = new Right();
        right.setId(Integer.parseInt(id));

        Result re;
        int temp = rightDaoImpl.changeStateToFrozen_Right(right);
        if (temp > 0) {
            logger.info("权限冻结成功");
            re = new Result(200, "权限冻结成功", null);
        } else {
            logger.info("权限冻结失败");
            re = new Result(400, "权限冻结失败", null);
        }
        return re;
    }

    public Result changeStateToNormal_Right(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Right right = new Right();
        right.setId(Integer.parseInt(id));

        Result re;
        int temp = rightDaoImpl.changeStateToNormal_Right(right);
        if (temp > 0) {
            logger.info("权限解冻成功");
            re = new Result(200, "权限解冻成功", null);
        } else {
            logger.info("权限解冻失败");
            re = new Result(400, "权限解冻失败", null);
        }
        return re;
    }

    public Result deleteRight(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        Right right = new Right();
        right.setId(Integer.parseInt(id));

        Result re;
        int temp = rightDaoImpl.deleteRight(right);
        if (temp > 0) {
            logger.info("权限删除成功");
            re = new Result(200, "权限删除成功", null);
        } else {
            logger.info("权限删除失败");
            re = new Result(400, "权限删除失败", null);

        }
        return re;
    }

    public Result deleteAllRight(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        logger.info("id：" + id);
        if (Objects.equals(id, "")) {
            logger.info("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] ObjectArry = id.split(",");
        logger.info("ObjectArry：" + ObjectArry);

        Right right = new Right();
        int[] temp = new int[ObjectArry.length];
        Result re;
        temp = rightDaoImpl.deleteAllRight(ObjectArry);

        if (temp.length != 0) {
            logger.info("权限批量删除成功");
            re = new Result(200, "权限批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            logger.info("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            logger.info("权限批量删除失败");
            re = new Result(400, "权限批量删除失败", null);
        }
        return re;
    }
}
