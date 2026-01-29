1. Class hierarchy

The project has two packages: model and ui.

model package

MyShape is an abstract superclass that stores coordinates and color and defines draw(Graphics g) and getType().

MyLine, MyRectangle, and MyOval extend MyShape and implement draw for each shape. MyRectangle and MyOval also have a boolean fill field for filled vs outline mode.

ui package

DrawingPanel extends JPanel and implements MouseListener and MouseMotionListener. It keeps an ArrayList<MyShape> for all shapes, a currentShape while drawing, the current color, the current fill setting and a statusLabel.

DrawingFrame extends JFrame and builds the main window: it adds a DrawingPanel in the center and a top control panel with a JComboBox for shape selection, a JCheckBox for filling, JButton for color / clear / undo , and JLabel for status.

2. Event-handling logic

Mouse events are handled in DrawingPanel:

•	mousePressed creates a new shape object (line/rectangle/oval) with the current color and fill setting using the mouse position as the start point.

•	mouseDragged updates the end point of currentShape and calls repaint() for live feedback.

•	mouseReleased finalizes the shape, adds it to the list, and repaints.

•	mouseMoved and mouseDragged also update the status label with the active tool and current coordinates.

Button and combo box events are handled in DrawingFrame by inner ActionListener classes:

•	Shape combo box changes selectedShape in the panel.

•	The fill checkbox updates the panel’s fill flag.

•	The color button opens a JColorChooser and sets the current color.

•	The clear button calls clearShapes() on the panel.

•	The undo button calls undoLastShape().
