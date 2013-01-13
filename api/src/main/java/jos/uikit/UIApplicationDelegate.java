package jos.uikit;

import jos.api.Export;
import jos.api.Register;
import jos.foundation.NSDictionary;
import jos.foundation.NSObject;

@Register(isWrapper = true)
public class UIApplicationDelegate extends NSObject {

    public UIWindow window;

    @Export(selector = "init")
    public UIApplicationDelegate() {
    }

    @Export(selector = "application:didFinishLaunchingWithOptions:")
    public boolean finishedLaunching(UIApplication app, NSDictionary options) {
        return false;
    }

    /**
     * Sent when the application is about to move from active to inactive state.
     * This can occur for certain types of temporary interruptions (such as an
     * incoming phone call or SMS message) or when the user quits the
     * application and it begins the transition to the background state.
     *
     * Use this method to pause ongoing tasks, disable timers, and throttle down
     * OpenGL ES frame rates. Games should use this method to pause the game.
     */
    public void applicationWillResignActive(UIApplication app) {
    }

    /**
     * Use this method to release shared resources, save user data, invalidate
     * timers, and store enough application state information to restore your
     * application to its current state in case it is terminated later.
     *
     * If your application supports background execution, this method is called
     * instead of applicationWillTerminate when the user quits.
     */
    public void applicationDidEnterBackground(UIApplication app) {
    }

    /**
     * Called as part of the transition from the background to the inactive
     * state; here you can undo many of the changes made on entering the
     * background.
     */
    public void applicationWillEnterForeground(UIApplication app) {
    }

    /**
     * Called when the application is about to terminate. Save data if
     * appropriate.
     *
     * @see applicationDidEnterBackground
     */
    public void applicationWillTerminate(UIApplication app) {
    }
}
