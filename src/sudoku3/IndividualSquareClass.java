/*
 * square.java
 * Laurence Smith
 * Created on 13 November 2004, 15:15
 */

package sudoku3;

/* shows numbers that can be placed in each position
 * there are 81 of these in the puzzle  */
public class IndividualSquareClass {
    public boolean[] numAllowed = new boolean[10];     // is true if number is allowed false if it isn't ignore position [0] so numbers match indices
    public int solved;  // is 0 if square unsolved. o/w is num that box is
    
    /* Creates a new instance of individual_square */
    public IndividualSquareClass() {
        int i;
        for(i = 0; i < 10; i++) {
            numAllowed[i] = true;
        }
        solved = 0;
    }
    
    // Resets everything in the square to allowed
    public void reset() {
        int i;
        for(i = 1; i < 10; i++) {
            numAllowed[i] = true;
        }
        solved = 0;
    }
    
    /* Changes from num allowed to not allowed and back */
    public void change(int num) {
        int i;
        if(countAllowed() > 2) {
            numAllowed[num] = !numAllowed[num];
        }
        else if(countAllowed() == 2) {
            // are we clicking one already allowed?
            if(numAllowed[num]) {  // square currently allowed
                // after change will only be one left so we have solved it
                numAllowed[num] = !numAllowed[num];
                // find which number is left
                for(i = 1; i < 10; i++) {
                    //System.out.println("numAllowed " + i + numAllowed[i]);
                    if(numAllowed[i] == true) {
                        solved = i;
                    }
                    //System.out.println("solved = " + solved);
                }
            }
            else {  // square currently not allowed
                numAllowed[num] = !numAllowed[num];
            }
        }
        else if(countAllowed() == 1) {  // we are putting numbers back - what happens if click in space of last remaining number?
            if(numAllowed[num]) { //clicking in position of the number we currently think it is
                // do nothing
            }
            else {
                numAllowed[num] = !numAllowed[num];
                solved = 0;
            }
        }
        else {
            // shouldn't happen
            System.out.println("Error less than 1 square allowed in box [IndividualSquareClass line 37]");
        }
        
    }
    
    // Counts the number of squares which are still allowed
    private int countAllowed() {
        int i, ret;
        ret = 0;
        for(i = 1; i < 10; i++) {
            if(this.numAllowed[i]) {
                ret += 1;
            }
        }
        return ret;
    }
    
    // changes the square to being solved to be the number sol
    public void setSolved(int sol) {
        int i;
        for(i = 1; i < 10; i++) {
            numAllowed[i] = false;
        }
        numAllowed[sol] = true;
        solved = sol;
    }
    
}
