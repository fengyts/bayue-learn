package learn.designmode.factorymode.abstractfactorymode1;

public class MailSender implements MessageSender {

	@Override
	public void send() {
		System.out.println("this is a mail sender");
	}

}
