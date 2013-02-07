package jos.samples.controls.screens.iphone;

import com.google.j2objc.annotations.EventListener;
import com.google.j2objc.annotations.Export;
import com.google.j2objc.annotations.Outlet;

import jos.api.foundation.NSCoder;
import jos.api.system.IntPtr;
import jos.api.uikit.UIAlertView;
import jos.api.uikit.UIControlEvent;
import jos.api.uikit.UISwitch;
import jos.api.uikit.UIViewController;

public class Switches extends UIViewController {

    @Outlet UISwitch swchOne;
    @Outlet UISwitch swchTwo;

    public Switches(IntPtr handle) {
        super(handle);
        initialize();
    }

    @Export("initWithCoder:")
    public Switches(NSCoder coder) {
        super(coder);
        initialize();
    }

    public Switches() {
        super("Switches_iPhone", null);
        initialize();
    }

    void initialize() {
    }

    @Override
    public void viewDidLoad ()
    {
        super.viewDidLoad ();

        this.title = "Switches";

        this.swchOne.addTarget(new EventListener() {

            @Override
            public void onEvent(Object object, int event) {
                new UIAlertView("Switch one change!", "is on: " + swchOne.on, null, "OK", null).show();
            }
        }, UIControlEvent.ValueChanged);

        this.swchTwo.addTarget(new EventListener() {

            @Override
            public void onEvent(Object object, int event) {
                new UIAlertView("Switch two change!", "is on: " + swchTwo.on, null, "OK", null).show();
            }
        }, UIControlEvent.ValueChanged);
    }

}
