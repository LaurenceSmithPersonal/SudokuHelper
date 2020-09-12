/*
 * PositionClass.java
 * Laurence Smith
 * Version 2 - drawing is donein leftPanel
 *    - so don't need boundary or rightSideSpace
 * Created on 13 November 2004, 18:07
 */

package sudoku3;

import java.awt.*;
import java.lang.Math;

// describes coordinates used
public class PositionClass {
    
    public int miniSquareSize;  // mini square size in pixels
    public int smallNumXadj; //number of pixels to add to get small numbers in centre of square
    public int smallNumYadj; //number of pixels to add to get small numbers in centre of square
    public int largeNumXadj; //number of pixels to add to get large numbers in centre of square
    public int largeNumYadj; //number of pixels to add to get large numbers in centre of square
    public int smallSquareSize;
    public int puzzleSize;
    public int individualSquareSize;
    public Font smallFont;
    public Font largeFont;
    
    /* Creates a new instance of PositionClass where application height and width are specified */
    public PositionClass(int xWindowSize, int yWindowSize) {
        int minSize, smallFontSize, largeFontSize;
        minSize = Math.min(xWindowSize, yWindowSize);
        miniSquareSize = minSize / 27;  // note integer division gives floored result
        
//        System.out.println("miniSquareSize =" + miniSquareSize);
        
        puzzleSize = miniSquareSize * 27;
        smallSquareSize = miniSquareSize * 9;
        individualSquareSize = miniSquareSize * 3;
        smallFontSize = (miniSquareSize * 5) / 6;
        largeFontSize = individualSquareSize;
        
        smallFont = new Font("Serif", Font.PLAIN, smallFontSize);
        largeFont = new Font("Serif", Font.PLAIN, largeFontSize); // Since large font covers 3 mini squares
        
        smallNumXadj = (miniSquareSize * 8) / 24; //number of pixels to add to get small numbers in centre of square
        smallNumYadj = (miniSquareSize * 13) / 16; //number of pixels to add to get small numbers in centre of square
        largeNumXadj = (individualSquareSize * 9) / 32;
        largeNumYadj = (individualSquareSize * 7) / 8;
    }
    
    /** Creates a new instance of PositionClass - ideal for 640x600 applet sizes */
    public PositionClass() {
        // Defaults with no arguments
        miniSquareSize = 20;  // mini square size in pixels
        smallNumXadj = 7; //number of pixels to add to get small numbers in centre of square
        smallNumYadj = 15; //number of pixels to add to get small numbers in centre of square
        largeNumXadj = 12;
        largeNumYadj = 12;
        puzzleSize = miniSquareSize * 27;
        smallSquareSize = miniSquareSize * 9;
        individualSquareSize = miniSquareSize * 3;
        
        smallFont = new Font("Serif", Font.PLAIN, 12);
        largeFont = new Font("Serif", Font.PLAIN, 50);
    }
    
    /* calculates coordinates for drawing numbers */
    public CoordsClass smallNumPos(int smalli, int smallj, int indi, int indj, int num) {
        CoordsClass res = new CoordsClass();
        int xtmp = 0, ytmp = 0;     // records x and y position within square
        if(num == 1) {
            xtmp = 0;
            ytmp = 0;
        }
        else if(num == 2) {
            xtmp = miniSquareSize;
            ytmp = 0;
        }
        else if(num == 3) {
            xtmp = 2 * miniSquareSize;
            ytmp = 0;
        }
        else if(num == 4) {
            xtmp = 0;
            ytmp = miniSquareSize;
        }
        else if(num == 5) {
            xtmp = miniSquareSize;
            ytmp = miniSquareSize;
        }
        else if(num == 6) {
            xtmp = 2 * miniSquareSize;
            ytmp = miniSquareSize;
        }
        else if(num == 7) {
            xtmp = 0;
            ytmp = 2 * miniSquareSize;
        }
        else if(num == 8) {
            xtmp = miniSquareSize;
            ytmp = 2 * miniSquareSize;
        }
        else if(num == 9) {
            xtmp = 2 * miniSquareSize;
            ytmp = 2* miniSquareSize;
        }
        
        res.x = smalli * smallSquareSize + indi * individualSquareSize + smallNumXadj + xtmp;
        res.y = smallj * smallSquareSize + indj * individualSquareSize + smallNumYadj + ytmp;
        
        return res;
    }
    
    public CoordsClass largeNumPos(int smalli, int smallj, int indi, int indj) {
        CoordsClass res = new CoordsClass();
        
        res.x = smalli * smallSquareSize + indi * individualSquareSize + largeNumXadj;
        res.y = smallj * smallSquareSize + indj * individualSquareSize + largeNumYadj;
        
        return res;
    }
}
    
    