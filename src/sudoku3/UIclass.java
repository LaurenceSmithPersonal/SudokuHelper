/*
 * UIclass.java
 * Laurence Smith
 * Created on 19 November 2004, 18:20
 */

package sudoku3;

import javax.swing.*;
import java.awt.*;

public class UIclass extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Drawing Variables declaration - do not modify
	private javax.swing.JCheckBox autoCheckBox;
    private java.awt.Button resetButton;
    private javax.swing.JPanel rightPanel;
    private SquarePanelClass leftPanel;
    private javax.swing.JPanel wholePanel;
    private javax.swing.JLabel instructionLabel;
    /* -- 01/11/2020 removed this way to add numbers as right clicking easier
    private java.awt.Label rowNumLabel;
    private java.awt.Label colNumLabel;
    private java.awt.Label AddNumLabel;
    private java.awt.TextField rowNumTextField;
    private java.awt.TextField colNumTextField;
    private java.awt.TextField AddNumTextField;
    private java.awt.Button AddNumButton;
    -- end 01/11/2020 removed this way to add numbers as right clicking easier */
    
    // Other variables declaration
    //PositionClass Position;
    //PuzzleClass Puzzle;
    
    public int leftPanelHeight;
    public int leftPanelWidth;
    
    /** Creates a new instance of UIclass */
    public UIclass() {
        
        //leftPanelHeight = leftPanel.getSize().height;
        //leftPanelWidth = leftPanel.getSize().width;
        //Position = new PositionClass(leftPanelHeight, leftPanelWidth);
        //Puzzle = new PuzzleClass(); // now in SquarePanelClass
        initComponents();
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        autoCheckBox = new javax.swing.JCheckBox();
        wholePanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        instructionLabel = new javax.swing.JLabel();
        /* -- 01/11/2020 removed this way to add numbers as right clicking easier
        rowNumLabel = new java.awt.Label();
        rowNumTextField = new java.awt.TextField(1);
        colNumLabel = new java.awt.Label();
        colNumTextField = new java.awt.TextField(1);
        AddNumLabel = new java.awt.Label();
        AddNumTextField = new java.awt.TextField(1);
        AddNumButton = new java.awt.Button();
        -- end 01/11/2020 removed this way to add numbers as right clicking easier */
        resetButton = new java.awt.Button(); 
        leftPanel = new SquarePanelClass();
        
        setTitle("Laurence Smith's Sudoku Helper");
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        // making panels opaque to speed drawing
        wholePanel.setOpaque(true);
        leftPanel.setOpaque(true);
        rightPanel.setOpaque(true);
        
        wholePanel.setBackground(Color.white);
        rightPanel.setBackground(Color.white);
        leftPanel.setBackground(Color.white);
        autoCheckBox.setBackground(Color.white);
        
        wholePanel.setLayout(new java.awt.BorderLayout());
        
        wholePanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        rightPanel.setLayout(new java.awt.GridBagLayout());
        
        rightPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 10, 1, 1)));
        
        // Label with instructions
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        instructionLabel.setPreferredSize(new java.awt.Dimension(200, 150));
        instructionLabel.setText("<HTML>Small gray numbers are potential answers. "
        		+ "Left click on them to remove. "
        		+ "Right click to set square to that answer."
        		+ "<P>"
        		+ "<P>Larger black numbers are solved squares."
        		+ "</HTML>");
        rightPanel.add(instructionLabel, gridBagConstraints);
        
        autoCheckBox.setText("automatic removal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        rightPanel.add(autoCheckBox, gridBagConstraints);
        
        // is there a change listener to use instead?
        autoCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                autoRemoveChange(evt);
            }
        });
        /* NOTE: This did not run if box checked with keyboard!
         autoCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autoCheckBoxMouseClicked(evt);
            }
        );
        */
        /* -- 01/11/2020 removed this way to add numbers as right clicking easier
        rowNumLabel.setText("row num");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        rightPanel.add(rowNumLabel, gridBagConstraints);
        
        rowNumTextField.setText("1");
        rowNumTextField.setColumns(1);
        rowNumTextField.setName("rowNumAdd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        rightPanel.add(rowNumTextField, gridBagConstraints);
        
        colNumLabel.setText("col num");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        rightPanel.add(colNumLabel, gridBagConstraints);
        
        colNumTextField.setText("1");
        colNumTextField.setName("colNumAdd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        rightPanel.add(colNumTextField, gridBagConstraints);
        
        AddNumLabel.setText("input num");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        rightPanel.add(AddNumLabel, gridBagConstraints);
        
        AddNumTextField.setText("9");
        AddNumTextField.setName("numAdd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        rightPanel.add(AddNumTextField, gridBagConstraints);
        
        AddNumButton.setLabel("add to puzzle");
        AddNumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNumButtonAction(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        rightPanel.add(AddNumButton, gridBagConstraints);
        -- end 01/11/2020 removed this way to add numbers as right clicking easier */
        
        resetButton.setLabel("reset puzzle");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonAction(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        rightPanel.add(resetButton, gridBagConstraints);
        
        wholePanel.add(rightPanel, java.awt.BorderLayout.EAST);
        
        leftPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        wholePanel.add(leftPanel, java.awt.BorderLayout.CENTER);
        
        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    private void autoRemoveChange(java.awt.event.ItemEvent evt) {
        if(autoCheckBox.isSelected()) {
        	leftPanel.Puzzle.autoRemove = true;
        	leftPanel.Puzzle.removeSolved();
        	repaint();
        }
        else {
            leftPanel.Puzzle.autoRemove = false;
        }
    }
    
    /*
    private void AddNumButtonAction(java.awt.event.ActionEvent evt) {
        int smallX, smallY, indX, indY, row, col, num;
//        System.out.println("row " + rowNumTextField.getText());
//        System.out.println("col " + colNumTextField.getText());
//        System.out.println("num " + AddNumTextField.getText());
        
        // row and columns were initially wrong way round
        //row = (int) Integer.parseInt(rowNumTextField.getText());
        //col = (int) Integer.parseInt(colNumTextField.getText());
        // used quick and dirty change (swapping them) below
        //
        
        col = (int) Integer.parseInt(rowNumTextField.getText());
        row = (int) Integer.parseInt(colNumTextField.getText());
        num = (int) Integer.parseInt(AddNumTextField.getText());
        
        row = row - 1;
        col = col - 1;
        
        if(row < 0 || row > 8) {
            //row out of bounds
        }
        else if(col < 0 || col > 8) {
            //col out of bounds
        }
        else if(num < 1 || num > 9) {
            // num is out
        }
        else {
            
            smallX = row / 3;
            indX = row - 3 * smallX;
            
            smallY = col / 3;
            indY = col - 3 * smallY;
            
            leftPanel.Puzzle.SmallSquare[smallX][smallY].IndividualSquare[indX][indY].setSolved(num);
            leftPanel.Puzzle.removeSolved();
            repaint();
        }
    }
    */
    
    private void resetButtonAction(java.awt.event.ActionEvent evt) {
        leftPanel.Puzzle.reset();
        repaint();
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        UIclass sudoku = new UIclass();
    }
    
    /* main class - is usedto execute program */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        //new UIclass().show();
    }
    
}
