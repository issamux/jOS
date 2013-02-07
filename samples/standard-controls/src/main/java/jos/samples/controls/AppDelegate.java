package jos.samples.controls;

import jos.api.foundation.NSDictionary;
import jos.api.uikit.UIApplication;
import jos.api.uikit.UIApplicationDelegate;
import jos.api.uikit.UIDevice;
import jos.api.uikit.UINavigationController;
import jos.api.uikit.UIScreen;
import jos.api.uikit.UIUserInterfaceIdiom;
import jos.api.uikit.UIWindow;
import jos.samples.controls.screens.ipad.IPadHomeNavController;
import jos.samples.controls.screens.iphone.IPhoneHomeNavController;

public class AppDelegate extends UIApplicationDelegate {

    private UINavigationController mainNavController;
    private IPhoneHomeNavController iPhoneHome;
    private IPadHomeNavController iPadHome;

    @Override
    public boolean finishedLaunching(UIApplication app, NSDictionary options) {
        window = new UIWindow(UIScreen.mainScreen.bounds);
        window.makeKeyAndVisible();

        mainNavController = new UINavigationController();

        if (UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.PHONE) {
            iPhoneHome = new IPhoneHomeNavController();
            mainNavController.pushViewController(iPhoneHome, false);
        } else {
            iPadHome = new IPadHomeNavController();
            mainNavController.pushViewController(iPadHome, false);
        }

        window.setRootViewController(mainNavController);
        return true;
    }

}
