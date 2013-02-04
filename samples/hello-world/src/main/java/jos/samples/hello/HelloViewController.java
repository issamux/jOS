package jos.samples.hello;

import jos.api.foundation.NSBundle;
import jos.api.uikit.UIScreen;
import jos.api.uikit.UIViewController;

public class HelloViewController extends UIViewController {

    public HelloViewController(final String nibName, final NSBundle bundle) {
        super(nibName, null);
    }

    public HelloViewController() {
        super("HelloWorldViewController", null);
    }

    @Override
    public void loadView() {
        this.view = new HelloView(UIScreen.mainScreen.applicationFrame);
    }

}
