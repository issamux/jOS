package jos.samples.controls.screens.iphone;

import jos.api.uikit.UIAlertView;
import jos.api.uikit.UIButton;
import jos.api.uikit.UIButtonType;
import jos.api.uikit.UIControlEvent;
import jos.api.uikit.UIControlState;
import jos.api.uikit.UIViewController;

import com.google.j2objc.annotations.Outlet;
import com.google.j2objc.annotations.Selector;

public class ButtonsScreen extends UIViewController {

    @Outlet
    UIButton btnOne;

    @Outlet
    UIButton btnTwo;

    public ButtonsScreen() {
        super("ButtonsScreen_iPhone", null);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        setTitle("Buttons");

        btnOne.addTarget(this, new Selector("handleBtnOneTouchUpInside"),
                UIControlEvent.TOUCH_UP_INSIDE);
        /*btnTwo.addTarget(new EventListener() {

            @Override
            public void onEvent(NSObject object, UIEvent event) {
                new UIAlertView("button two click!", "TouchUpInside Handled",
                        null, "OK").show();
            }

        }, UIControlEvent.TOUCH_UP_INSIDE);*/
        btnTwo.addTarget(this, new Selector("handleBtnTwoTouchUpInside"),
                UIControlEvent.TOUCH_UP_INSIDE);

        UIButton button = UIButton.fromType(UIButtonType.ROUNDED_RECT);
        button.setTitle("My Button", UIControlState.NORMAL);
    }

    protected void handleBtnOneTouchUpInside() {
        new UIAlertView("button one click!", "TouchUpInside Handled", null,
                "OK", null).show();
    }

    protected void handleBtnTwoTouchUpInside() {
        new UIAlertView("button two click!", "TouchUpInside Handled",
                null, "OK", null).show();
    }

}
