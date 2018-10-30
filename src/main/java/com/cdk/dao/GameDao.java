package com.cdk.dao;

import com.cdk.entity.Game;

import java.util.Map;

public interface GameDao {

    public Map<String, Object> getAllGame(Game game, String isPage, int pageNo, int pageSize);

    public int addGame(Game game);

    public int editGame(Game game);

    public int deleteGame(Game game);

    public int changeStateToNormal_Game(Game game);

    public int changeStateToFrozen_Game(Game game);

    public int[] deleteAllGame(String[] gameList);
}
