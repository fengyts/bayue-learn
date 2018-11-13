package learn.designmode.factorymode.abstractfactorymode1;

public class SmsSender implements MessageSender {

	@Override
	public void send() {
		System.out.println("this is sms sender");
	}

}
