package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
	
	import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
	
	import model.*;
	
	public class DrawingPanel extends JPanel implements MouseListener , MouseMotionListener
	{
		private ArrayList<MyShape> shapes = new ArrayList<>(); // memory of shapes
	    private MyShape currentShape = null; // currently being drawn shape
	    private MyShape selectedShape = null; // selected shape
	    private Color currentColor = Color.BLACK; // current color
	    private boolean fill; // checks fill or not
	    private JLabel statusLabel; // label for coordinates
	    
	    // Base constructor for panel
	    public DrawingPanel() 
		{
	    	// Setting proper things
	    	setBackground(Color.WHITE);
	        setSize(800,600);
	        
	        // Adding action listeners
	        addMouseListener(this);
	        addMouseMotionListener(this);
	    }
	    
	    // Get methods
	    public Color getCurrentColor() 
	    {
	        return currentColor;
	    }
	    
	    // Set methods
	    public void setSelectedShape(MyShape selectedShape) 
	    {
	        this.selectedShape = selectedShape;
	    }
	
	    public void setCurrentColor(Color color) 
	    {
	        this.currentColor = color;
	    }
	    
	    public void setFill(boolean fill) 
	    {
	        this.fill = fill;
	    }
	    
	    public void setStatusLabel(JLabel statusLabel) 
	    {
	        this.statusLabel = statusLabel;
	    }
	    
	    @Override
	    public void paintComponent(Graphics g)
	    {
	    	super.paintComponent(g);
	        for (MyShape s : shapes) 
	        {
	            s.draw(g);
	        }
	        if (currentShape != null) 
	        {
	            currentShape.draw(g);
	        }
	    }
	    
	    // Clear all shapes by clearing memory of shapes
	    public void clearShapes() 
	    {
	        shapes.clear();
	        repaint();
	    }
	    
	    // Undo last shape by removing last shape from memory of shapes
	    public void undoLastShape() 
	    {
	        if (!shapes.isEmpty()) 
	        {
	            shapes.remove(shapes.size() - 1);
	            repaint();
	        }
	    }
	    
		@Override
		public void mousePressed(MouseEvent e) 
		{
			// Gets first location of mouse
			int startX = e.getX();
			int startY = e.getY();
			
			// Getting type for control
			String type = selectedShape.getType();
			
			// Getting current shape according to type of selected shape
			if ("line".equals(type)) 
			{
	            currentShape = new MyLine(startX, startX, startY, startY, currentColor);
	        } 
			else if ("rectangle".equals(type)) 
	        {
	            currentShape = new MyRectangle(startX, startX, startY, startY, currentColor,fill);
	        } 
			else if ("oval".equals(type)) 
	        {
	            currentShape = new MyOval(startX, startX, startY, startY, currentColor,fill);
	        }
		}
	
		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// Gets last location and paint shape according to that location
			currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());
            shapes.add(currentShape);
            currentShape = null;
            repaint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			// Updating mouse position continuously
			currentShape.setX2(e.getX());
	        currentShape.setY2(e.getY());
	        repaint();
	        
	        // Updating coordinate continuously
	        statusLabel.setText("X: " + e.getX() + "   Y: " + e.getY());
		}
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseExited(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) 
		{
			// Updating coordinate continuously
	        statusLabel.setText("X: " + e.getX() + "   Y: " + e.getY());
		}
		
	}
