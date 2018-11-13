package learn.designmode.factorymode.simplefactorymode;

public class SimpleShapeFactory {
	
	public static final int CIRCLE = 1;
	public static final int RECTANGLE = 2;
	public static final int ELLIPSE = 3;
	
	public static Shape createShape(int shapeType){
		switch(shapeType){
		case CIRCLE: 
			return new CircleShape();
		case RECTANGLE:
			return new RectangleShape();
		default:
			return new EllipseShape();
		}
		
	}

}
