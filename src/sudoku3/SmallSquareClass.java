/*
 * small_square.java
 * Laurence Smith
 * Created on 13 November 2004, 15:32
 */

package sudoku3;

/* 9 of these in puzzle - topleft = 1, bottomright = 9 */
public class SmallSquareClass {
    
    public IndividualSquareClass[][] IndividualSquare = new IndividualSquareClass[3][3];
    
    /** Creates a new instance of SmallSquareClass */
    public SmallSquareClass() {
        int i, j;
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++) {
                IndividualSquare[i][j] = new IndividualSquareClass();
            }
        }
        
    }
    
}
