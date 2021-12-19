import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MoveTest {

	@Test
	void indexConstructorColTest() {
		Move move;
		boolean pass = true;
		int col = 0;
		for(int i=0; i<16; i++) {
			col = i%4;
			move = new Move(i);
			if(move.getCol() != col) {
				pass = false;
			}
		}
		assertTrue(pass, "Index constructor check row test");
	}

	@Test
	void indexConstructorRowTest() {
		Move move;
		boolean pass = true;
		int row = 0;
		for(int i=0; i<16; i++) {
			if(i == 4 || i==8 || i==12) {
				row++;
			}
			move = new Move(i);
			if(move.getRow() != row) {
				pass = false;
			}
		}
		assertTrue(pass, "Index constructor check row test");
	}

}
