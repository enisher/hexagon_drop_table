package com.anahoret.anadea_bot;

import com.anahoret.anadea_bot.controller.api.GameController;
import com.anahoret.anadea_bot.dto.Board;
import com.anahoret.anadea_bot.dto.Game;
import com.anahoret.anadea_bot.dto.ServerMove;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AnadeaBotJavaApplicationTests {

	@Autowired
	GameController gameController;

	@Test
	public void smoke() {
		Game game = new Game();
		game.setId("1");
		game.setFirst_turn(true);
		game.setTraining(false);
		final HashMap<String, Integer> jumps = new HashMap<>();
		jumps.put("2", 0);
		jumps.put("1", 1);
		game.setJumps(jumps);

		Board board = new Board();
		board.setCells(new int[][]{
				new int[]{-1, 1, 0, 2, -1},
      			new int[]{0, -1, 0, 0, -1},
				new int[]{2, 0, 0, 0, 1},
				new int[]{0, 0, 0, -1, -1},
				new int[]{-1, 1, 0, 2, -1}
		});
		board.setSize(3);
		game.setBoard(board);

		gameController.startGame(game);
		gameController.makeMove("1", 1);
		ServerMove serverMove = new ServerMove();
		serverMove.setJumps(new HashMap<String, Integer>(){{
			put("1", 2);
			put("2", 3);
		}});
		serverMove.setChanges(new int[][]{
				new int[]{0,5,2,0},
				new int[]{0,3,0,2},
		});
		serverMove.setChanges(new int[1][5]);
		gameController.handleMove("1", serverMove);

		gameController.finishGame("1");
	}

	@Test
	public void testEmptyState() throws Exception {
		gameController.makeMove("1", 1);

		ServerMove serverMove = new ServerMove();
		serverMove.setJumps(new HashMap<String, Integer>(){{
			put("1", 2);
			put("2", 3);
		}});
		serverMove.setChanges(new int[][]{
				new int[]{0,5,2,0},
				new int[]{0,3,0,2},
		});
		serverMove.setChanges(new int[1][5]);
		gameController.handleMove("1", serverMove);
	}
}
