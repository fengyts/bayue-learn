package learn.designmode.factorymode.abstractfactorymode;

public class ColorFactory extends AbstractFactory {

	@Override
	public IShape getShape(String shape) {
		return null;
	}

	@Override
	public IColor getColor(String color) {
		if (color == null) {
			return null;
		}
		if (color.equalsIgnoreCase("red")) {
			return new Red();
		} else if (color.equalsIgnoreCase("green")) {
			return new Green();
		} else if (color.equalsIgnoreCase("blue")) {
			return new Blue();
		}
		return null;
	}

}
