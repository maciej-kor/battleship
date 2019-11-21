package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements GameControler {

    Map<Player, GameBoard> playerGameBoardMap = new HashMap<>();
    List<Player> players = new ArrayList<>();
    List<GameBoard> gameBoards = new ArrayList<>();


    @Override
    public void addPlayer(String name) {

        boolean nameExists = false;

        if (players.size() > 0) {
            for (Player player : players) {

                if (player.getName() == name) {
                    nameExists = true;
                }
            }
        }
        if (!nameExists) {

            Player player = new Player();
            player.setName(name);
            Player.setWins(0);
            players.add(player);
            GameBoard gameBoard = new GameBoard(10);
            gameBoard.setPlayer(player);

            gameBoards.add(gameBoard);

            playerGameBoardMap.put(player, gameBoard);

        }
    }

    @Override
    public void shot(int x, int y, Player player) {

        boolean czyZostajeRuchPoTejStronie;

        czyZostajeRuchPoTejStronie = getPlayerGameBoardMap().get(player).getGameBoardControler().shot(x, y);

        if (czyZostajeRuchPoTejStronie){

            if (players.get(0).getName().equals(player.getName())){

                players.get(0).setNextMove(true);
                players.get(1).setNextMove(false);

            } else {

                players.get(0).setNextMove(false);
                players.get(1).setNextMove(true);

            }

        } else {

            if (players.get(0).getName().equals(player.getName())){

                players.get(0).setNextMove(false);
                players.get(1).setNextMove(true);

            } else {

                players.get(0).setNextMove(true);
                players.get(1).setNextMove(false);

            }
        }
    }

    @Override
    public boolean checkIfWin(Player player) {
        if (getPlayerGameBoardMap().get(player).getGameBoardControler().getShipwreckNumber() == getPlayerGameBoardMap().get(player).getGameBoardControler().getShipList().size())
            return true;
        else
            return false;
    }

    @Override
    public GameBoard getGameBoard(Player player) {
        return getPlayerGameBoardMap().get(player);
    }

    public List<GameBoard> getGameBoards() {
        return gameBoards;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Ship> losuj(Player player) {

        GameBoard gameBoard = getPlayerGameBoardMap().get(player);
        gameBoard.getGameBoardControler().randomShipsCoordinates(10);
        return gameBoard.getGameBoardControler().getShipList();

    }

    @Override
    public int[][] getFieldsState(Player player) {

        int[][] fieldStates = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fieldStates[i][j] =  playerGameBoardMap.get(player).getGameBoardControler().getFieldStatus(j, i, player);
            }
        }
        return fieldStates;
    }

    public Map<Player, GameBoard> getPlayerGameBoardMap() {
        return playerGameBoardMap;
    }

    public void setPlayerGameBoardMap(Map<Player, GameBoard> playerGameBoardMap) {
        this.playerGameBoardMap = playerGameBoardMap;
    }


}
