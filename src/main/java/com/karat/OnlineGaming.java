package com.karat;


/*
We are building the back-end for an online gaming platform. The system tracks
players and their match history. Each match result records the outcome from
that player's perspective.

Definitions:
* A "player" has: playerId, username.
* A "match result" has: playerId, opponentId, outcome, score, timestamp.
* Outcome is one of: WIN, LOSS, DRAW.
* "GameManager" manages players and match results and provides player statistics.

To begin with, we present you with two tasks:
1-1) Read through and understand the code below. Feel free to run it.
1-2) The test for GameManager is not passing due to a bug in the code.
     Make the necessary changes to GameManager to fix the bug.
*/
/*
We are extending the platform to support recording match results
and computing per-player score statistics.

Each MatchResult represents one player's experience of a single match:
- playerId      : the player this record belongs to
- opponentId    : the opponent in that match
- outcome        : one of WIN, LOSS, DRAW
- score          : the player's score in that match
- timestamp      : when the match was played

To implement these changes, we need to add two functions to the GameManager class:

2.1) The addMatchResult function should be used to store a match result.
     Only the playerId is validated: if it does not refer to a known
     player in GameManager, the match result should be ignored. A match
     result with a known playerId must still be stored even when the
     opponentId does not refer to a known player.

2.2) The getAverageScoreByOutcome function should return a dictionary
     mapping each outcome (WIN, LOSS, DRAW) to the player's average score
     for that outcome. Only outcomes the player has at least one result
     for should appear in the dictionary. If the player has no match
     results at all, return an empty dictionary.

To assist you in testing these new functions, we have provided the
testAddMatchResult and testGetAverageScoreByOutcome tests.
*/
/*
We want to summarize the match history between two specific players.

For each pair of players, we want to know how each player has performed
against the other and when they last played.

We have added a HeadToHead class to represent this summary:
- winsPlayer1            : number of matches the first player won
- winsPlayer2            : number of matches the second player won
- draws                   : number of matches that ended in a draw
- totalMatches           : total number of matches played between them
- lastResult             : the outcome of the most recent match from
                            player1's perspective (null if they have
                            never played)
- lastMatchTimestamp    : timestamp of the most recent match between
                            them (null if they have never played)

Add one function to the GameManager class:

3) The getHeadToHead function takes two player IDs and returns a
   HeadToHead object summarizing all matches between them.
   If the two players have never faced each other, the function should
   return a HeadToHead with all numeric fields set to 0, lastResult
   set to null, and lastMatchTimestamp set to null.

To assist you in testing this new function, we have provided the
testGetHeadToHead test.
*/

import java.util.*;

enum Outcome {
    WIN, LOSS, DRAW
}

class Player {
    public int playerId;
    public String username;

    public Player(int playerId, String username) {
        this.playerId = playerId;
        this.username = username;
    }
}

class MatchResult {
    public int playerId;
    public int opponentId;
    public Outcome outcome;
    public int score;
    public int timestamp;

    public MatchResult(int playerId, int opponentId, Outcome outcome, int score, int timestamp) {
        this.playerId = playerId;
        this.opponentId = opponentId;
        this.outcome = outcome;
        this.score = score;
        this.timestamp = timestamp;
    }
}

class PlayerStats {
    public int totalMatches;
    public int wins;
    public double winRate;

    public PlayerStats(int totalMatches, int wins, double winRate) {
        this.totalMatches = totalMatches;
        this.wins = wins;
        this.winRate = winRate;
    }
}

class HeadToHead {
    int winsPlayer1;
    int winsPlayer2;
    int draws;
    int totalMatches;
    Outcome lastResult;
    Integer lastMatchTimestamp;

    HeadToHead(int winsPlayer1, int winsPlayer2, int draws,
               int totalMatches, Outcome lastResult, Integer lastMatchTimestamp) {
        this.winsPlayer1 = winsPlayer1;
        this.winsPlayer2 = winsPlayer2;
        this.draws = draws;
        this.totalMatches = totalMatches;
        this.lastResult = lastResult;
        this.lastMatchTimestamp = lastMatchTimestamp;
    }
}

class GameManager {
    public Map<Integer, Player> players;
    public List<MatchResult> matchResults;


    public GameManager() {
        players = new HashMap<>();
        matchResults = new ArrayList<>();
    }

    //TODO::Implement the getHeadToHead function below
    HeadToHead getHeadToHead(Integer player1, Integer player2) {
        //SORT the matchResults by timestamp in descending order to find the last match easily

        matchResults.sort((a, b) -> a.timestamp - b.timestamp);
        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int draws = 0;
        int totalMatches = 0;
        Outcome lastResult = null;
        Integer lastMatchTimestamp = null;

        for (MatchResult matchResult : matchResults) {
            if ((matchResult.playerId == player1 && matchResult.opponentId == player2) ||
                    (matchResult.playerId == player2 && matchResult.opponentId == player1)) {
                totalMatches++;
                if (matchResult.outcome == Outcome.WIN) {
                    if (matchResult.playerId == player1) {
                        winsPlayer1++;
                    } else {
                        winsPlayer2++;
                    }
                } else if (matchResult.outcome == Outcome.LOSS) {
                    if (matchResult.playerId == player1) {
                        winsPlayer2++;
                    } else {
                        winsPlayer1++;
                    }
                } else if (matchResult.outcome == Outcome.DRAW) {
                    draws++;
                }

                if (lastMatchTimestamp == null || matchResult.timestamp > lastMatchTimestamp) {
                    lastMatchTimestamp = matchResult.timestamp;
                    lastResult = matchResult.outcome;
                }
            }
        }

        return new HeadToHead(winsPlayer1, winsPlayer2, draws, totalMatches, lastResult, lastMatchTimestamp);
    }

    //TODO:: Implement the addMatchResult function below
    void addMatchResult(MatchResult matchResult) {
        int id = matchResult.playerId;
        if (players.containsKey(id)) {
            matchResults.add(matchResult);
        }
    }

    //TODO::Implement the getAverageScoreByOutcome function below
    Map<Outcome, Double> getAverageScoreByOutcome(int id) {
        List<Integer> win = new ArrayList<>();
        List<Integer> loss = new ArrayList<>();
        List<Integer> draw = new ArrayList<>();
        for (MatchResult matchResult : matchResults) {
            if (matchResult.outcome == Outcome.WIN && matchResult.playerId == id) {
                win.add(matchResult.score);
            } else if (matchResult.outcome == Outcome.LOSS && matchResult.playerId == id) {
                loss.add(matchResult.score);
            } else if (matchResult.outcome == Outcome.DRAW && matchResult.playerId == id) {
                draw.add(matchResult.score);
            }
        }

        HashMap<Outcome, Double> map = new HashMap<>();
        double sum = 0.0;
        for (double val : win) {
            sum = sum + val;
        }
        double avg = sum / win.size();
        if (win.size() > 0)
            map.put(Outcome.WIN, avg);

        double sum1 = 0.0;
        for (double val : loss) {
            sum1 = sum1 + val;
        }
        double avg1 = sum1 / loss.size();
        if (loss.size() > 0)
            map.put(Outcome.LOSS, avg1);

        double sum2 = 0.0;
        for (double val : draw) {
            sum2 = sum2 + val;
        }
        double avg2 = sum2 / draw.size();

        if (draw.size() > 0)
            map.put(Outcome.DRAW, avg2);

        return map;

    }

    public void addPlayer(Player player) {
        players.put(player.playerId, player);
    }

    public PlayerStats getPlayerStatistics(int playerId) {
        List<MatchResult> playerMatches = new ArrayList<>();
        for (MatchResult m : matchResults) {
            if (m.playerId == playerId) {
                playerMatches.add(m);
            }
        }

        //TODO::FIX this code here to correctly calculate totalMatches, wins, and winRate
        int totalMatches = 0;
        for (MatchResult m : playerMatches) {
            if (m.outcome == Outcome.WIN || m.outcome == Outcome.LOSS || m.outcome == Outcome.DRAW) {
                totalMatches++;
            }
        }

        int wins = 0;
        for (MatchResult m : playerMatches) {
            if (m.outcome == Outcome.WIN) {
                wins++;
            }
        }

        double winRate;
        if (totalMatches > 0) {
            winRate = (double) wins / totalMatches;
        } else {
            winRate = 0.0;
        }

        return new PlayerStats(totalMatches, wins, winRate);
    }
}

public class OnlineGaming {
    public static void main(String[] args) {
        testGetPlayerStatistics();
        testAddMatchResult();
        testGetAverageScoreByOutcome();
        testGetHeadToHead();
        System.out.println("All Tests Passed!");
    }

    public static void testGetPlayerStatistics() {
        System.out.println("Running testGetPlayerStatistics");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        gm.matchResults.add(new MatchResult(1, 2, Outcome.WIN, 80, 1000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.LOSS, 50, 2000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.DRAW, 60, 3000));
        gm.matchResults.add(new MatchResult(1, 2, Outcome.WIN, 90, 4000));

        PlayerStats stats = gm.getPlayerStatistics(1);
        assert stats.totalMatches == 4 :
                "totalMatches should be 4, was " + stats.totalMatches;
        assert stats.wins == 2 :
                "wins should be 2, was " + stats.wins;
        assert Math.abs(stats.winRate - 0.5) < 1e-4 :
                "winRate should be 0.5, was " + stats.winRate;

        gm.matchResults.add(new MatchResult(2, 1, Outcome.DRAW, 60, 1000));
        gm.matchResults.add(new MatchResult(2, 1, Outcome.DRAW, 60, 2000));

        PlayerStats stats2 = gm.getPlayerStatistics(2);
        assert stats2.totalMatches == 2 :
                "totalMatches should be 2, was " + stats2.totalMatches;
        assert stats2.wins == 0 :
                "wins should be 0, was " + stats2.wins;
        assert Math.abs(stats2.winRate - 0.0) < 1e-4 :
                "winRate should be 0.0, was " + stats2.winRate;
    }

    static void testAddMatchResult() {
        System.out.println("Running testAddMatchResult");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 80, 1000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 50, 1000));

        // unknown player ignored
        gm.addMatchResult(new MatchResult(99, 1, Outcome.WIN, 100, 2000));

        // known player, unregistered opponent -> still stored
        gm.addMatchResult(new MatchResult(1, 99, Outcome.WIN, 70, 3000));

        assert gm.matchResults.size() == 3 : "Expected 3 match results";
    }

    static void testGetAverageScoreByOutcome() {
        System.out.println("Running testGetAverageScoreByOutcome");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));

        // match 1 - player1 wins
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 80, 1000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 50, 1000));

        // match 2 - player1 wins again
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 91, 2000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 61, 2000));

        // match 3 - draw
        gm.addMatchResult(new MatchResult(1, 2, Outcome.DRAW, 70, 3000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.DRAW, 70, 3000));

        // match 4 - another draw
        gm.addMatchResult(new MatchResult(1, 2, Outcome.DRAW, 75, 4000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.DRAW, 75, 4000));

        // 1 - o
        // Map<Integer, List<Double>> map = getAverageScoreByOutcome(1);
        //  Map<Integer, Map<Outcome, List<Integer>>> map

        Map<Outcome, Double> avg1 = gm.getAverageScoreByOutcome(1);
        assert Math.abs(85.5 - avg1.get(Outcome.WIN)) < 1e-4 : "Expected 85.5 for WIN";   // (80+91)/2
        assert Math.abs(72.5 - avg1.get(Outcome.DRAW)) < 1e-4 : "Expected 72.5 for DRAW";  // (70+75)/2
        assert !avg1.containsKey(Outcome.LOSS) : "player1 has no losses";                   // player1 has no losses

        Map<Outcome, Double> avg2 = gm.getAverageScoreByOutcome(2);
        assert Math.abs(55.5 - avg2.get(Outcome.LOSS)) < 1e-4 : "Expected 55.5 for LOSS";  // (50+61)/2
        assert Math.abs(72.5 - avg2.get(Outcome.DRAW)) < 1e-4 : "Expected 72.5 for DRAW";  // (70+75)/2
        assert !avg2.containsKey(Outcome.WIN) : "player2 has no wins";                      // player2 has no wins

        // player with no match results
        gm.addPlayer(new Player(3, "player3"));
        assert gm.getAverageScoreByOutcome(3).isEmpty() : "Expected empty map for player3";
    }

    static void testGetHeadToHead() {
        System.out.println("Running testGetHeadToHead");
        GameManager gm = new GameManager();
        gm.addPlayer(new Player(1, "player1"));
        gm.addPlayer(new Player(2, "player2"));
        gm.addPlayer(new Player(3, "player3"));


        // match 1 - player1 wins
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 80, 1000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 50, 1000));

        // match 2 - player2 wins
        gm.addMatchResult(new MatchResult(1, 2, Outcome.LOSS, 60, 2000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.WIN, 90, 2000));

        // match 4 - player1 wins (most recent)
        gm.addMatchResult(new MatchResult(1, 2, Outcome.WIN, 85, 4000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.LOSS, 65, 4000));

        // match 3 - draw
        gm.addMatchResult(new MatchResult(1, 2, Outcome.DRAW, 70, 3000));
        gm.addMatchResult(new MatchResult(2, 1, Outcome.DRAW, 70, 3000));
        //147

        HeadToHead h2h = gm.getHeadToHead(1, 2);
        assert h2h.winsPlayer1 == 2 : "Expected 2";           // match 1 + match 4
        assert h2h.winsPlayer2 == 1 : "Expected 1";           // match 2 only
        assert h2h.draws == 1 : "Expected 1";                  // match 3 only
        assert h2h.totalMatches == 4 : "Expected 4";           // all 4 matches
        assert h2h.lastResult == Outcome.WIN : "Expected WIN"; // match 4 was a WIN for player1
        assert h2h.lastMatchTimestamp == 4000 : "Expected 4000";

        // from player2's perspective
        HeadToHead h2hReverse = gm.getHeadToHead(2, 1);
        assert h2hReverse.winsPlayer1 == 1 : "Expected 1";              // player2 won match 2
        assert h2hReverse.winsPlayer2 == 2 : "Expected 2";              // player1 won match 1 + 4
        assert h2hReverse.draws == 1 : "Expected 1";
        assert h2hReverse.totalMatches == 4 : "Expected 4";
        assert h2hReverse.lastResult == Outcome.LOSS : "Expected LOSS"; // match 4 was a LOSS   for player2
        assert h2hReverse.lastMatchTimestamp == 4000 : "Expected 4000";

        // players who have never faced each other — unchanged
        HeadToHead h2hEmpty = gm.getHeadToHead(1, 3);
        assert h2hEmpty.totalMatches == 0 : "Expected 0";
        assert h2hEmpty.winsPlayer1 == 0 : "Expected 0";
        assert h2hEmpty.winsPlayer2 == 0 : "Expected 0";
        assert h2hEmpty.draws == 0 : "Expected 0";
        assert h2hEmpty.lastResult == null : "Expected null";
        assert h2hEmpty.lastMatchTimestamp == null : "Expected null";
    }
}
