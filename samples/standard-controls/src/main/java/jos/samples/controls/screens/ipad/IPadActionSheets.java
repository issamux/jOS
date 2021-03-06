package jos.samples.controls.screens.ipad;

import jos.api.uikit.UIActionSheet;
import jos.api.uikit.UIActionSheetDelegate;
import jos.api.uikit.UIButton;
import jos.api.uikit.UIControlEvent;
import jos.api.uikit.UIViewController;

import com.google.j2objc.annotations.Outlet;
import com.google.j2objc.annotations.Selector;

public class IPadActionSheets extends UIViewController {

    @Outlet
    UIButton btnActionSheetWithOtherButtons;

    @Outlet
    UIButton btnSimpleActionSheet;

    UIActionSheet actionSheet;

    final UIActionSheetDelegate delegate = new UIActionSheetDelegate() {
        @Override
        public void onClick(UIActionSheet sheet, int buttonIndex) {
            System.out.println("Button " + buttonIndex + " clicked");
        }
    };

    public IPadActionSheets() {
        super("ActionSheets_iPad", null);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        setTitle("Action Sheets");
        btnSimpleActionSheet.addTarget(this, new Selector(
                "handleBtnSimpleActionSheetTouchUpInside"),
                UIControlEvent.TOUCH_UP_INSIDE);
        btnActionSheetWithOtherButtons.addTarget(this, new Selector(
                "handleBtnActionSheetWithOtherButtonsTouchUpInside"),
                UIControlEvent.TOUCH_UP_INSIDE);
    }

    protected void handleBtnSimpleActionSheetTouchUpInside() {
        // create an action sheet using the qualified constructor
        actionSheet = new UIActionSheet("simple action sheet", null, "cancel",
                "delete", null);
        actionSheet.setDelegate(delegate);
        actionSheet.showInView(getView());
    }

    protected void handleBtnActionSheetWithOtherButtonsTouchUpInside() {
        actionSheet = new UIActionSheet("action sheet with other buttons",
                null, null, null, null);
        actionSheet.addButton("delete");
        actionSheet.addButton("a different option!");
        actionSheet.addButton("another option");
        actionSheet.setDestructiveButtonIndex(0);
        actionSheet.setDelegate(delegate);
        actionSheet.showInView(getView());
    }

}
