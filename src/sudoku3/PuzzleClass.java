/*
 * PuzzleClass.java
 * Laurence Smith
 * Created on 13 November 2004, 21:15
 */

package sudoku3;

/* The whole puzzle */
public class PuzzleClass {
    public SmallSquareClass[][] SmallSquare = new SmallSquareClass[3][3];
    public boolean autoRemove;
    
    /** Creates a new instance of PuzzleClass */
    public PuzzleClass() {
        int i, j;
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++) {
                SmallSquare[i][j] = new SmallSquareClass();
            }
        }
        autoRemove = false;
    }
    
    // removes the solved squares from the possible choices
    public void removeSolved() {
        if(!autoRemove) {
            // User does not want help removing things so do nothing
        }
        else {  //help removing allowed nums from rows, columns and small squares
            int iX, iY, jX, jY, kX, kY, mX, mY;
            int changed, sol;
            
            changed = 1;
            
            while(changed == 1) {
                changed = 0;
                
                // check individual boxes for solved
                for(iX = 0; iX < 3; iX++) {
                    for(iY = 0; iY < 3; iY++) {
                        for(jX = 0; jX < 3; jX++) {
                            for(jY = 0; jY < 3; jY++) {
                                if(SmallSquare[iX][iY].IndividualSquare[jX][jY].solved != 0) {
                                    sol = SmallSquare[iX][iY].IndividualSquare[jX][jY].solved;
                                    // check all in Individual Square rows
                                    for(kX = 0; kX < 3; kX++) {
                                        for(mX = 0; mX < 3; mX++) {
                                            if(SmallSquare[kX][iY].IndividualSquare[mX][jY].solved == 0) {
                                                if(SmallSquare[kX][iY].IndividualSquare[mX][jY].numAllowed[sol] == true) {
                                                    SmallSquare[kX][iY].IndividualSquare[mX][jY].change(sol);
                                                    changed = 1;
                                                }
                                            }
                                        }
                                    }
                                    // check all in Individual Square columns
                                    for(kY = 0; kY < 3; kY++) {
                                        for(mY = 0; mY < 3; mY++) {
                                            if(SmallSquare[iX][kY].IndividualSquare[jX][mY].solved == 0) {
                                                if(SmallSquare[iX][kY].IndividualSquare[jX][mY].numAllowed[sol] == true) {
                                                    SmallSquare[iX][kY].IndividualSquare[jX][mY].change(sol);
                                                    changed = 1;
                                                }
                                            }
                                        }
                                    }
                                    // check all in Small Square
                                    for(kX = 0; kX < 3; kX++) {
                                        for(mY = 0; mY < 3; mY++) {
                                            if(SmallSquare[iX][iY].IndividualSquare[kX][mY].solved == 0) {
                                                if(SmallSquare[iX][iY].IndividualSquare[kX][mY].numAllowed[sol] == true) {
                                                    SmallSquare[iX][iY].IndividualSquare[kX][mY].change(sol);
                                                    changed = 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Resets the puzzle so that all squares are allowed
    public void reset() {
        int smallX, smallY, indX, indY;
        for(smallX = 0; smallX < 3; smallX++) {
            for(smallY = 0; smallY < 3; smallY++) {
                for(indX = 0; indX < 3; indX++) {
                    for(indY = 0; indY < 3; indY++) {
                        SmallSquare[smallX][smallY].IndividualSquare[indX][indY].reset();
                    }
                }
            }
        }
    }
}

