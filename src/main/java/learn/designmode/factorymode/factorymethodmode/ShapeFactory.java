package learn.designmode.factorymode.factorymethodmode;

public class ShapeFactory implements IShapeFactory {
	
	public static final String CIRCLE = "circle";
	public static final String RECTANGLE = "rectangle";
	public static final String ELLIPSE = "ellipse";

	@Override
	public IShape createShape(String shapeType) {
		if(CIRCLE.equals(shapeType)){
			return new CircleShape();
		}
		if(RECTANGLE.equals(shapeType)){
			return new RectangleShape();
		}
		if(ELLIPSE.equals(shapeType)){
			return new EllipseShape();
		}
		return null;
	}

}
