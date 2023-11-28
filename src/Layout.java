import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JComponent;

public class Layout {

	/* Helper Classes */

	private class Data {
		
		JComponent label;

		Rectangle2D scale;
		Rectangle offset;
		
		private Data(JComponent label, Rectangle2D scale, Rectangle offset) {
			
			this.label = label;
			
			this.scale = scale;
			this.offset = offset;
		}
		
	}
	
	/* Variables */
	
	boolean isBottom = false;
	
	LinkedList<Data> labels;
	
	/* Methods */
	
	public void add(JComponent label, Rectangle2D scale, Rectangle offset) {
		labels.addFirst(new Data(label, scale, offset));
	}
	
	public Rectangle addRectangles(Rectangle... rectangles) {
		
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		
		for (Rectangle rectangle: rectangles) {
			x += rectangle.x;
			y += rectangle.y;
			width += rectangle.width;
			height += rectangle.height;
		}
		
		return new Rectangle(x,y,width,height);
	}
	
	public void remove(JComponent label) {
		for (int i = 0; i < labels.size(); i++) {
			if (labels.get(i).label == label) {
				labels.remove(i);
				break;
			}
		}
	}
	
	public void update(Window window) {
		
		int height = 0;
		Rectangle bounds = window.getContentPane().getBounds();
		
			
		for (Data label: labels) {
			
			if (!label.label.isVisible()) continue;
			
			Rectangle rect = new Rectangle(
					(int)(label.scale.getX() * bounds.width),
					0,
					(int)(label.scale.getWidth() * bounds.width),
					(int)(label.scale.getHeight() * bounds.height)
					);
			
			rect = addRectangles(rect, label.offset);
			
			
			Rectangle position = new Rectangle(0,height,0,0);

			position.setLocation(0, bounds.height - height - rect.height);
			
			height = height + rect.height;
			
			rect = addRectangles(rect, position);
			
			label.label.setBounds(rect);
		}
		
	}
	
	/* Constructors */
	
	public Layout(boolean isBottom) {
		labels = new LinkedList<>();
		this.isBottom = isBottom;
	}
	
}
