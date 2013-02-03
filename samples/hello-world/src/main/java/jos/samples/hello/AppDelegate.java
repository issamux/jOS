package jos.samples.hello;

import jos.api.foundation.NSDictionary;
import jos.api.uikit.UIApplication;
import jos.api.uikit.UIApplicationDelegate;
import jos.api.uikit.UIScreen;
import jos.api.uikit.UIWindow;

public class AppDelegate extends UIApplicationDelegate {

    private HelloViewController viewController;

    @Override
    public boolean finishedLaunching(UIApplication app, NSDictionary options) {
        this.window = new UIWindow(UIScreen.mainScreen.bounds);

        viewController = new HelloViewController();
        this.window.rootViewController = viewController;
        this.window.makeKeyAndVisible();

        return true;
    }

}
