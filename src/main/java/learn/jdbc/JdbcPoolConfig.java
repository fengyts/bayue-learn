package learn.jdbc;

public abstract class JdbcPoolConfig {

	private static final int DEFAULT_POOL_SIZE = 8;
	private static final int DEFAULT_MAX_IDLE = 8;
	private static final int DEFAULT_MIN_IDLE = 0;

	protected int poolSize = DEFAULT_POOL_SIZE;
	protected int maxIdle = DEFAULT_MAX_IDLE;
	protected int minIdle = DEFAULT_MIN_IDLE;

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

}
