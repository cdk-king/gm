package com.cdk.dao.impl;

import com.cdk.dao.GameDao;
import com.cdk.entity.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class GameDaoImpl implements GameDao {
    private static Logger logger = Logger.getLogger(String.valueOf(GameDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getAllGame(Game game, String isPage, int pageNo, int pageSize) {
        String sql = "select * from t_game where isDelete != 1  ";
        if (game.getGameName() != "") {
            sql += " and gameName LIKE '%" + game.getGameName() + "%'";
        }
        if (game.getGame_describe() != "") {
            sql += " and game_describe LIKE '%" + game.getGame_describe() + "%'";
        }
        if (game.getGameTag() != "") {
            sql += " and gameTag LIKE '%" + game.getGameTag() + "%'";
        }
        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(game.getState(), null) && !Objects.equals(game.getState(), 0)) {
            if (Objects.equals(game.getState(), 1)) {
                sql += " and state = 1 ";
            } else if (Objects.equals(game.getState(), 2)) {
                sql += " and state != 1 ";
            }
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        logger.info("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);

        return JsonMap;
    }

    @Override
    public int addGame(Game game) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "insert into t_game (gameName,gameTag,game_describe,gameEncryptSign,sort,addUser,addDatetime,state,isDelete) " + " values ('" +
                game.getGameName() + "','" + game.getGameTag() + "','" + game.getGame_describe() + "','" + game.getGameEncryptSign() + "','0','" +
                game.getAddUser() + "','" + addDatetime + "','0','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int editGame(Game game) {
        String sql = "UPDATE t_game as a SET a.gameName='" + game.getGameName() + "',a.game_describe = '" + game.getGame_describe() + "'," +
                "a.gameTag='" + game.getGameTag() + "' ,a.gameEncryptSign = '" + game.getGameEncryptSign() + "',a.addUser = '" + game.getAddUser() +
                "' where a.id =" + game.getId() + "";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    @Override
    public int deleteGame(Game game) {
        String sql = "UPDATE t_game SET isDelete='1' where id ='" + game.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToNormal_Game(Game game) {
        String sql = "UPDATE t_game SET state='0' where id ='" + game.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Game(Game game) {
        String sql = "UPDATE t_game SET state='1' where id ='" + game.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int[] deleteAllGame(String[] gameList) {
        String sql[] = new String[gameList.length];
        String strSql = "";
        int[] temp = new int[gameList.length];
        for (int i = 0; i < gameList.length; i++) {
            //UPDATE user_roles  set  isDelete='1' where
            sql[i] = "UPDATE  t_game  set isDelete='1' where id = '" + gameList[i] + "';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
