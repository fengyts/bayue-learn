package learn.designmode.factorymode.abstractfactorymode;

/**
 * 为 Color 和 Shape 两个系列的产品对象 创建抽象类来获取工厂。
 * 
 * @author lenovopc
 *
 */
public abstract class AbstractFactory {

	public abstract IShape getShape(String shape);

	public abstract IColor getColor(String color);

}
