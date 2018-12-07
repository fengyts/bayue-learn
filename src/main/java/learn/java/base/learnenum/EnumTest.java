package learn.java.base.learnenum;

public class EnumTest {

	private static interface EGeneric {
		Enum<?>[] values1();
	}

	public static enum SC implements EGeneric {
		TABLE_NAME;

		@Override
		public SC[] values1() {
			return null;
		}

	}

}
