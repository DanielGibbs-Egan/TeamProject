import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JComponent;

public class Layout {

	/* Helper Classes */

	/**
	 * <style>tab{margin-left:30px;}</style>
	 * class <b>Data</b> : stores layout information about a component<br><br>
	 *
	 *	variables: <br>
	 *		<tab><b>component</b> : the JComponent to resize<br>
	 *		<tab><b>scale</b> : the size and position of the component relative to the window<br>
	 *		<tab><b>offset</b> : the size and position of the component in pixels<br>
	 *
	 */
	private class Data {
		
		// the JComponent to resize
		JComponent component;
		// the size and position of the component relative to the window
		Rectangle2D scale;
		// the size and position of the component in pixels
		Rectangle offset; 
		
		/**
		 * Data(JComponent component, Rectangle2D scale, Rectangle offset) : 
		 * contructs a new Data Object
		 * 
		 * @param component : the JComponent
		 * @param scale : the size and position of the component relative to the window
		 * @param offset : the size and position of the component in pixels
		 */
		private Data(JComponent component, Rectangle2D scale, Rectangle offset) {
			
			this.component = component; // store the given JComponent
			
			this.scale = scale; // store the given scale for the JComponent
			this.offset = offset; // store the given offset for the JComponent
			
		}
		
	}
	
	/**
	 * <style>tab{margin-left:30px;}</style>
	 * class <b>FontData</b> : stores layout information about a components text<br><br>
	 *
	 *	variables: <br>
	 *		<tab><b>component</b> : the JComponent to resize <br>
	 *		<tab><b>ptsAt500px</b> : the point size of the text font when the window is 500 pixels
	 *
	 */
	private class FontData {
		
		// the JComponent containing the text to resize
		JComponent component; 
		// the point size of the text's font when the window is 500 pixels
		int ptsAt500px; 
		
		/**
		 * FontData(JComponent component, int ptsAt500px) : 
		 * contructs a new FontData Object
		 * 
		 * @param component : the JComponent
		 * @param ptsAt500px : the pt size of the text's font when 
		 * the window is 500 pixels 
		 */
		private FontData(JComponent component, int ptsAt500px) {
			// store the given JComponent
			this.component = component; 
			// store the given points at 500 pixels value
			this.ptsAt500px = ptsAt500px; 
		}
	}
	
	/* Variables */
	
	LinkedList<Data> components = new LinkedList<>(); // the stored resize data of added JComponents
	LinkedList<FontData> fonts = new LinkedList<>(); // the stored font resize data of added JComponents
	
	/* Methods */
	
	/**
	 * add(JComponent component, Rectangle2D scale, Rectangle offset) : adds
	 * a new JComponent to the layout for resizing and positioning
	 * 
	 * @param component : a JComponent to resize
	 * @param scale : the scale of the JComponent relative to the window
	 * @param offset : the offset of the JComponent in pixels
	 * 
	 */
	public void add(JComponent component, Rectangle2D scale, Rectangle offset) {
		// add the new layout information to the components list
		components.addFirst(new Data(component, scale, offset)); 
	}
	
	/**
	 * add(JComponent component, int ptsAt500px) : adds
	 * a new JComponent to the layout for resizing and 
	 * positioning of its text
	 * 
	 * @param component : a JComponent to resize
	 * @param ptsAt500px : the pt size of the JComponents 
	 * text when the window is 500 pixels large
	 * 
	 */
	public void add(JComponent component, int ptsAt500px) {
		// add the new layout information to the fonts list
		fonts.addFirst(new FontData(component, ptsAt500px));
	}
	
	/**
	 * addRectangles(Rectangle... rectangles) : sums the size 
	 * and position of each rectange given and returns it as 
	 * a new rectangle
	 * 
	 * @param rectangles : an arbitrary amount of rectangles to
	 * get the sum of
	 * @return <b>new Rectangle()</b> : a new rectangle the size and position 
	 * of which are the sum of from that of the given rectangles
	 */
	public Rectangle addRectangles(Rectangle... rectangles) {
		
		int xSum = 0; // the sum of the x variables 
		int ySum = 0; // the sum of the y variables 
		int widthSum = 0; // the sum of the width variables 
		int heightSum = 0; // the sum of the height variables 
		
		// loop through the given rectangles
		for (Rectangle rectangle: rectangles) {
			// add the variables of the current 
			// rectangle to the sum
			xSum += rectangle.x;
			ySum += rectangle.y;
			widthSum += rectangle.width;
			heightSum += rectangle.height;
		}
		
		// create a new rectangle from the sum variables
		return new Rectangle(xSum,ySum,widthSum,heightSum);
	}
	
	/**
	 * remove(JComponent component) : removes a JComponent from 
	 * both the components and fonts lists
	 * 
	 * @param component : the component to remove
	 */
	public void remove(JComponent component) {
		// loop through the components list till the component is found
		for (int i = 0; i < components.size(); i++) {
			// if the data's component is the component to remove
			if (components.get(i).component == component) {
				// remove the data from the components list
				components.remove(i);
				// break the loop
				break;
			}
		}
		// loop through the fonts list till the component is found
		for (int i = 0; i < fonts.size(); i++) {
			// if the data's component is the component to remove
			if (fonts.get(i).component == component) {
				// remove the data from the fonts list
				fonts.remove(i);
				// break the loop
				break;
			}
		}
		
	}
	
	/**
	 * update(Window window) : update the layout and recalculate the 
	 * positions, scales and text of its components
	 * 
	 * @param window : the game window
	 */
	public void update(Window window) {
		
		int height = 0; // the sum of the heights of all the JComponents
		// the size of the window
		Rectangle bounds = window.getContentPane().getBounds();
		
		// loop through the components
		for (Data data: components) {
			// if the component is invisible skip it
			if (!data.component.isVisible()) continue;
			// create a new rectangle from the JComponents scales
			Rectangle rectangle = new Rectangle(
					(int)(data.scale.getX() * bounds.width),
					(int)(data.scale.getY() * -bounds.height),
					(int)(data.scale.getWidth() * bounds.width),
					(int)(data.scale.getHeight() * bounds.height)
					);
			// add the offset rectangle to the scaled rectangle
			rectangle = addRectangles(rectangle, data.offset);
			// create a new rectangle a distance of position the component as low as allowed
			Rectangle position = new Rectangle(0, bounds.height - height - rectangle.height, 0, 0);
			// increase the current height by the height of the rectangle
			height = height + rectangle.height;
			// add the position rectangle to the scale & offset rectangles
			rectangle = addRectangles(rectangle, position);
			// resize the component to the new rectangle
			data.component.setBounds(rectangle);
		}
		
		// loop through the font resizing data
		for (FontData data: fonts) {
			// if the component does not exist or is invisible skip the data
			if (data.component == null || !data.component.isVisible()) continue;
			// get the components current font
			Font current = data.component.getFont();
			// calculate the size the font should be at the current window size
			float size = data.ptsAt500px*(Math.max(bounds.height, bounds.width) / 4f + 375)/500f;
			// set the fonts size to the new font size
			data.component.setFont(current.deriveFont(size));
		}
	}
	
}
