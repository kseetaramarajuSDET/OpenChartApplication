package bridgelabz.UtilityClases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation,
			Class testClass,
			Constructor testConstructor,
			Method testMethod) {

		String[] groups = annotation.getGroups();

		// Retry ONLY UI group
		if (Arrays.asList(groups).contains("functional")) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
	}

}
