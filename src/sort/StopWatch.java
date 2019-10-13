package sort;

/**
 * Stop watch
 * @author Behdad Khamneli, lab2
 *
 */
public class StopWatch {
	private final long start;
	
	/**
	 * Initializes the timer
	 */
	public StopWatch() {
		start = System.currentTimeMillis();
	}
	/**
	 * Elapsed time
	 * @return the elapsed time in seconds
	 */
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;//devide by 1000 to get seconds
	}
}
