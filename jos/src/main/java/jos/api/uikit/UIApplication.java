package jos.api.uikit;

import com.google.j2objc.annotations.Export;
import com.google.j2objc.annotations.Register;

@Register(isWrapper = true)
public class UIApplication extends UIResponder {

    @Export("main:principalClassName:delegateClassName:")
    public static void main(String[] args, String principalClassName, String delegateClassName) {
    }

}
