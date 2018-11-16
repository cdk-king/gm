package com.cdk.service.impl;

import com.cdk.dao.impl.GameDaoImpl;
import com.cdk.entity.Game;
import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class GameServiceImpl {
    public static final String Divider = "############################";
    public static final String Split = "----------------";
    @Autowired
    public GameDaoImpl gameDaoImpl;

    public Result getAllGame(Map map) {
        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");
        String gameTag = (map.get("gameTag") != null ? map.get("gameTag").toString() : "");
        String game_describe = (map.get("game_describe") != null ? map.get("game_describe").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        if (state == "") {
            state = "0";
        }
        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("state：" + state);

        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        System.out.println("pageNo：" + StrPageNo);
        System.out.println("pageSize：" + StrPageSize);
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        game.setGameName(gameName);
        game.setGame_describe(game_describe);
        game.setGameTag(gameTag);
        game.setState(Integer.parseInt(state));

        Result re;

        Map<String, Object> JsonMap = gameDaoImpl.getAllGame(game, isPage, pageNo, pageSize);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "游戏列表获取失败", "");
        } else {
            re = new Result(200, "游戏列表获取成功", JsonMap);
        }
        return re;
    }

    public Result addGame(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");
        String gameTag = (map.get("gameTag") != null ? map.get("gameTag").toString() : "");
        String game_describe = (map.get("game_describe") != null ? map.get("game_describe").toString() : "");
        String gameEncryptSign = (map.get("gameEncryptSign") != null ? map.get("gameEncryptSign").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
        System.out.println("gameTag：" + gameTag);
        System.out.println("sort：" + sort);
        System.out.println("addUser：" + addUser);
        System.out.println("state：" + state);

        Result re;
        Game game = new Game();
        game.setGameName(gameName);
        game.setGame_describe(game_describe);
        game.setGameTag(gameTag);
        game.setAddUser(addUser);
        game.setGameEncryptSign(gameEncryptSign);

        int temp = gameDaoImpl.addGame(game);
        if (temp > 0) {
            System.out.println("游戏添加成功");
            re = new Result(200, "游戏添加成功", null);
        } else {
            System.out.println("游戏添加失败");
            re = new Result(400, "游戏添加失败", null);
        }
        return re;
    }

    public Result editGame(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");
        String game_describe = (map.get("game_describe") != null ? map.get("game_describe").toString() : "");
        String gameTag = (map.get("gameTag") != null ? map.get("gameTag").toString() : "");
        String gameEncryptSign = (map.get("gameEncryptSign") != null ? map.get("gameEncryptSign").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");
        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
        System.out.println("gameTag：" + gameTag);
        System.out.println("sort：" + sort);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result re;
        Game game = new Game();
        game.setId(Integer.parseInt(id));
        game.setGameName(gameName);
        game.setGame_describe(game_describe);
        game.setGameTag(gameTag);
        game.setAddUser(addUser);
        game.setGameEncryptSign(gameEncryptSign);

        int temp = gameDaoImpl.editGame(game);
        if (temp > 0) {
            System.out.println("游戏信息修改成功");
            re = new Result(200, "游戏信息更新成功", null);
        } else {
            System.out.println("游戏信息修改失败");
            re = new Result(400, "游戏信息更新失败", null);
        }
        return re;
    }

    public Result deleteGame(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Game game = new Game();
        game.setId(Integer.parseInt(id));

        Result re;
        int temp = gameDaoImpl.deleteGame(game);
        if (temp > 0) {
            System.out.println("游戏删除成功");
            re = new Result(200, "游戏删除成功", null);
        } else {
            System.out.println("游戏删除失败");
            re = new Result(400, " 游戏删除失败", null);
        }
        return re;
    }

    public Result changeStateToNormal_Game(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Result re;
        Game game = new Game();
        game.setId(Integer.parseInt(id));

        int temp = gameDaoImpl.changeStateToNormal_Game(game);
        if (temp > 0) {
            System.out.println("游戏解冻成功");
            re = new Result(200, "游戏解冻成功", null);
        } else {
            System.out.println("游戏解冻失败");
            re = new Result(400, "游戏解冻失败", null);
        }
        return re;
    }

    public Result changeStateToFrozen_Game(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        Result re;
        Game game = new Game();
        game.setId(Integer.parseInt(id));
        int temp = gameDaoImpl.changeStateToFrozen_Game(game);
        if (temp > 0) {
            System.out.println("游戏冻结成功");
            re = new Result(200, "游戏冻结成功", null);
        } else {
            System.out.println("游戏冻结失败");
            re = new Result(400, "游戏冻结失败", null);

        }
        return re;
    }

    public Result deleteAllGame(Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        if (Objects.equals(id, "")) {
            System.out.println("无任何批量删除操作");
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] ObjectArry = id.split(",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result re;
        int[] temp = gameDaoImpl.deleteAllGame(ObjectArry);

        if (temp.length != 0) {
            System.out.println("游戏批量删除成功");
            re = new Result(200, "游戏批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("游戏批量删除失败");
            re = new Result(400, "游戏批量删除失败", null);
        }
        return re;
    }
}
