/*
 * SquarePanelClass.java
 * Laurence Smith
 * Created on 19 November 2004, 23:41
 */

package sudoku3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class SquarePanelClass extends JComponent implements MouseInputListener {
    PuzzleClass Puzzle;
    PositionClass Position;
    
    /** Creates a new instance of SquarePanelClass */
    public SquarePanelClass() {
        Puzzle = new PuzzleClass();
        addMouseListener(this);
        
    }
    
    protected void paintComponent(Graphics g) {
        Position = new PositionClass(getWidth(), getHeight());
        drawposition(g);
    }
    
    private void drawposition(Graphics g) {
        int i, k, indi, indj, smalli, smallj;
        
        // drawing a rectangle to set the background colour to white
        g.setColor(Color.white); //this is done by setBackground in UIClass Yes but also needed for repaint
        g.fillRect(0,0, getWidth(), getHeight());  // should get widths here
       
        // individual square lines horizontal and vertical
        g.setColor(Color.lightGray);
        for(i = 0; i < 28; i++) {   // 28 as fenceposts
            g.drawLine(i * Position.miniSquareSize, 0, i * Position.miniSquareSize, Position.puzzleSize);
            g.drawLine(0, i * Position.miniSquareSize, Position.puzzleSize, i * Position.miniSquareSize);
        }
        
        // small square lines
        g.setColor(Color.gray);
        for(i = 0; i < 10; i++) {
            g.drawLine(i * 3 * Position.miniSquareSize, 0, i * 3 * Position.miniSquareSize, Position.puzzleSize);
            g.drawLine(0, i * 3 * Position.miniSquareSize, Position.puzzleSize, i * 3 * Position.miniSquareSize);
        }
        
        // large square lines
        g.setColor(Color.black);
        for(i = 0; i < 4; i++) {
            g.drawLine(i * 9 * Position.miniSquareSize, 0, i * 9 * Position.miniSquareSize, Position.puzzleSize);
            g.drawLine(0, i * 9 * Position.miniSquareSize, Position.puzzleSize, i * 9 * Position.miniSquareSize);
        }
        
        g.setColor(Color.lightGray);
        for(smalli = 0; smalli < 3; smalli++) {  //for each of the small squares
            for(smallj = 0; smallj < 3; smallj++) {  //for each of the small squares
                for(indi = 0; indi < 3; indi++) {
                    for(indj = 0; indj < 3; indj++) {
                        if(Puzzle.SmallSquare[smalli][smallj].IndividualSquare[indi][indj].solved == 0) {   // square not solved - show possible numbers
                            g.setColor(Color.lightGray);
                            g.setFont(Position.smallFont);
                            for(k = 1; k < 10; k++) { //for each of the numbers
                                
                                if(Puzzle.SmallSquare[smalli][smallj].IndividualSquare[indi][indj].numAllowed[k]) {
                                    // show number x coords = i , y coords = j
                                    
                                    g.drawString(Integer.toString(k), Position.smallNumPos(smalli, smallj, indi, indj, k).x, Position.smallNumPos(smalli, smallj, indi, indj, k).y);
                                }
                            }
                        }
                        else {  // square has been solved - draw large number
                            g.setColor(Color.black);
                            g.setFont(Position.largeFont);
                            g.drawString(Integer.toString(Puzzle.SmallSquare[smalli][smallj].IndividualSquare[indi][indj].solved), Position.largeNumPos(smalli, smallj, indi, indj).x, Position.largeNumPos(smalli, smallj, indi, indj).y);
                        }
                    }
                }
            }
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        int ret = 0, i, smallx, smally, indx, indy, x, y;
        int xdistInSmall, ydistInSmall, xdistInInd, ydistInInd;  // records distance inside each type of square
//        System.out.println("mouseUp event");
        
        x = e.getX();
        y = e.getY();
        
        if(x > 0 & x < Position.puzzleSize & y > 0 & y < Position.puzzleSize) {   //inside box
            //System.out.println("inside box");
                        
            // which small box is it
            // because we dividing integers we get an integer result (from rounding down result)
            smallx = x / Position.smallSquareSize;
            smally = y / Position.smallSquareSize;
            
            xdistInSmall = x - (smallx * Position.smallSquareSize);
            ydistInSmall = y - (smally * Position.smallSquareSize);
            
            // which individual box is it
            indx = xdistInSmall / Position.individualSquareSize;
            indy = ydistInSmall / Position.individualSquareSize;
            
            xdistInInd = xdistInSmall - (indx * Position.individualSquareSize);
            ydistInInd = ydistInSmall - (indy * Position.individualSquareSize);
            
            // which number is it
            ret = 1 + (3 * (ydistInInd / Position.miniSquareSize)) + xdistInInd / Position.miniSquareSize;
            
            if(SwingUtilities.isLeftMouseButton(e)) {
            	Puzzle.SmallSquare[smallx][smally].IndividualSquare[indx][indy].change(ret);
            }
            else if(SwingUtilities.isRightMouseButton(e)) {
            	Puzzle.SmallSquare[smallx][smally].IndividualSquare[indx][indy].setSolved(ret);
            }
            
            // on;y really want to do this if above is solved - perhaps give it a return value
            Puzzle.removeSolved();
        }
        else {
            //System.out.println("outside box");
        }
        
        repaint();
    }
    
    public void mouseDragged(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
}
