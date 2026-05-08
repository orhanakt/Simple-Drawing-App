package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MyLine;
import model.MyRectangle;
import model.MyOval;

public class DrawingFrame extends JFrame {

    private DrawingPanel drawingPanel; // Our main drawing panel
    private JComboBox<String> shapeCombo; // ComboBox for shapes
    private JButton colorButton; // Button for color
    private JButton clearButton; // Button for clear
    private JCheckBox fillCheck; // CheckBox for fill
    private JLabel statusLabel; // Label for status
    private JButton undoButton; // Button for undo
    
    // Base constructor for frame
    public DrawingFrame() 
    {
    	// Making template for frame
        super("Simple Drawing Application");
        setLayout(new BorderLayout());

        // Making panel and adding to frame
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Making control panel to add status label, shapes combo box, fill check box, color button, clear button, undo button
        JPanel controlPanel = new JPanel();
        
        // Making status label and adding to control panel
        statusLabel = new JLabel("Ready");
        controlPanel.add(statusLabel);
        drawingPanel.setStatusLabel(statusLabel);

        // Making shape label and adding to control panel
        controlPanel.add(new JLabel("Shape:"));

        // Making shape combo box and adding to control panel
        shapeCombo = new JComboBox<>(new String[] { "Line", "Rectangle", "Oval" });
        controlPanel.add(shapeCombo);

        // Making fill check box and adding to control panel
        fillCheck = new JCheckBox("Fill the shape");
        controlPanel.add(fillCheck);
        
        // Making color and clear button and adding to control panel
        colorButton = new JButton("Color");
        clearButton = new JButton("Clear");
        controlPanel.add(colorButton);
        controlPanel.add(clearButton);

        //  Making undo button and adding to control panel
        undoButton = new JButton("Undo");
        controlPanel.add(undoButton);
        add(controlPanel, BorderLayout.NORTH);

        drawingPanel.setSelectedShape(new MyLine(0, 0, 0, 0, drawingPanel.getCurrentColor()));

        // Adding Action listener to functional objects
        shapeCombo.addActionListener(new ShapeComboListener());
        colorButton.addActionListener(new ColorButtonListener());
        clearButton.addActionListener(new ClearButtonListener());
        fillCheck.addActionListener(new FillCheckListener());
        undoButton.addActionListener(new UndoButtonListener());
        
        // Making frame ready
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    // Combo box listener for shapes
    private class ShapeComboListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String selected = (String) shapeCombo.getSelectedItem();

            if ("Line".equals(selected)) 
            {
                drawingPanel.setSelectedShape(
                        new MyLine(0, 0, 0, 0, drawingPanel.getCurrentColor()));
            } 
            else if ("Rectangle".equals(selected)) 
            {
            	drawingPanel.setSelectedShape(
            		    new MyRectangle(0, 0, 0, 0, drawingPanel.getCurrentColor(), fillCheck.isSelected()));
            } 
            else if ("Oval".equals(selected)) 
            {
                drawingPanel.setSelectedShape(
                        new MyOval(0, 0, 0, 0, drawingPanel.getCurrentColor(),fillCheck.isSelected()));
            }
        }
    }
    
    // Check box listener for fill
    private class FillCheckListener implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			drawingPanel.setFill(fillCheck.isSelected());
		}
    }

    // Button listener for color
    private class ColorButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Color chosen = JColorChooser.showDialog(
                    DrawingFrame.this,
                    "Choose Color",
                    drawingPanel.getCurrentColor());
            if (chosen != null) 
            {
                drawingPanel.setCurrentColor(chosen);
            }
        }
    }

    // Button listener for clear
    private class ClearButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drawingPanel.clearShapes();
        }
    }

    // Button listener for undo
    private class UndoButtonListener implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			drawingPanel.undoLastShape();
		}
    	
    }
    // Main method
    public static void main(String[] args) 
    {
        DrawingFrame frame = new DrawingFrame();
        frame.setVisible(true);
    }
}
