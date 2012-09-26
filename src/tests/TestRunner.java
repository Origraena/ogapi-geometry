import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;

public class TestRunner {

	public static void main(String args[]) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));
		runner.run(SurfaceTest.class);
	}

};

