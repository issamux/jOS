package jos.api.foundation;

import com.google.j2objc.annotations.Export;
import com.google.j2objc.annotations.Register;

@Register(isWrapper = true)
public class NSArray extends NSObject {

	@Export("initWithObjects:count:")
	public NSArray(Object[] objects, int count) {
	}

}
