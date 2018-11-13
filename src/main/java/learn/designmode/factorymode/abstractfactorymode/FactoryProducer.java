package learn.designmode.factorymode.abstractfactorymode;

/**
 * 创建一个工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂。
 * 
 * @author lenovopc
 *
 */
public class FactoryProducer {

	public static AbstractFactory getFactory(String factoryType) {
		if (factoryType.equalsIgnoreCase("shape")) {
			return new ShapeFactory();
		} else if (factoryType.equalsIgnoreCase("color")) {
			return new ColorFactory();
		}
		return null;
	}

}
