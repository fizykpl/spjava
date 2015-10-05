package p.lodz.pl.spjava.sdudkiewicz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ProcessingTest {

	ExecutorService executor = Executors.newFixedThreadPool(10);

	@Test
	public void test() {

		// Callable<Process> callable = new Processing("ipconfig");
		// Future<Process> future = executor.submit(callable);
		// Process pr;
		// try {
		// pr = future.get();
		// String inputStream = IOUtils.toString(pr.getInputStream(), "UTF-8");
		// String outputStream = IOUtils.toString(pr.getInputStream(), "UTF-8");
		// String errroStream = IOUtils.toString(pr.getInputStream(), "UTF-8");
		// System.out.println(inputStream);
		// System.out.println(outputStream);
		// System.out.println(errroStream);
		// } catch (InterruptedException | ExecutionException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
