package learn.designmode.factorymode.abstractfactorymode1;

public class MailSenderFactory implements MessageSenderFactory {

	@Override
	public MessageSender create() {
		return new MailSender();
	}

}
