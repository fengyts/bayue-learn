package learn.designmode.factorymode.abstractfactorymode;

/**
 * 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象。
 * 
 * @author lenovopc
 *
 */
public class ShapeFactory extends AbstractFactory {

	@Override
	public IShape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("circle")) {
			return new CircleShape();
		} else if (shapeType.equalsIgnoreCase("rectangle")) {
			return new RectangleShape();
		} else if (shapeType.equalsIgnoreCase("square")) {
			return new SquareShape();
		}
		return null;
	}

	@Override
	public IColor getColor(String color) {
		return null;
	}

}
