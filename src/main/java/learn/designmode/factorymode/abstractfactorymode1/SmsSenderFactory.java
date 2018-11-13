package learn.designmode.factorymode.abstractfactorymode1;

public class SmsSenderFactory implements MessageSenderFactory {

	@Override
	public MessageSender create() {
		return new SmsSender();
	}

}
