package jos.samples.hello;

import jos.api.uikit.UIScreen;
import jos.api.uikit.UIView;
import jos.api.uikit.UIViewController;

public class HelloViewController extends UIViewController {

    public UIView view;

    public HelloViewController() {
        super("HelloWorldViewController", null);
    }

    @Override
    public void loadView() {
        this.view = new HelloView(UIScreen.mainScreen.applicationFrame);
    }

    /*@Override
    public void viewDidUnload() {
        super.viewDidUnload();
        //releaseDesignerOutlets();
    }

    @Override
    public boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation toInterfaceOrientation) {
        // Return true for supported orientations
        return toInterfaceOrientation != UIInterfaceOrientation.PortraitUpsideDown;
    }*/
}
