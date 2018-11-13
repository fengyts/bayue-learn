package learn.designmode.factorymode;

import learn.designmode.factorymode.abstractfactorymode1.MailSenderFactory;
import learn.designmode.factorymode.abstractfactorymode1.MessageSender;
import learn.designmode.factorymode.abstractfactorymode1.MessageSenderFactory;
import learn.designmode.factorymode.abstractfactorymode1.SmsSenderFactory;
import learn.designmode.factorymode.factorymethodmode.IShape;
import learn.designmode.factorymode.factorymethodmode.IShapeFactory;
import learn.designmode.factorymode.factorymethodmode.ShapeFactory;
import learn.designmode.factorymode.simplefactorymode.Shape;
import learn.designmode.factorymode.simplefactorymode.SimpleShapeFactory;

public class TestFactoryMode {
	
	public static void testSimpleFactory(){
		Shape shape = SimpleShapeFactory.createShape(SimpleShapeFactory.CIRCLE);
		shape.doSomething();
	}
	
	public static void testFactoryMethod(){
		IShapeFactory factory = new ShapeFactory();
		IShape circle = factory.createShape("circle");
		circle.draw();
		IShape rectangle = factory.createShape("rectangle");
		rectangle.draw();
		IShape ellipse = factory.createShape("ellipse");
		ellipse.draw();
	}
	
	public static void testAbstractFactory(){
		MessageSenderFactory msf = new SmsSenderFactory();
		MessageSender sms = msf.create();
		sms.send();
		
		MessageSenderFactory msf1 = new MailSenderFactory();
		MessageSender mms = msf1.create();
		mms.send();
	}
	
	public static void main(String[] args) {
//		testSimpleFactory();
//		testFactoryMethod();
		testAbstractFactory();
	}

}
