package learn.designmode.factorymode.factorymethodmode;

/**
 * 产品工厂接口
 * 
 * @author lenovopc
 *
 */
public interface IShapeFactory {
	
	IShape createShape(String shapeType);

}
