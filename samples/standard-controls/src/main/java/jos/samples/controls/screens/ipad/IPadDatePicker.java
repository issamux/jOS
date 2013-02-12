package jos.samples.controls.screens.ipad;

import java.util.GregorianCalendar;

import jos.api.foundation.NSObject;
import jos.api.uikit.UIButton;
import jos.api.uikit.UIControlEvent;
import jos.api.uikit.UIDatePicker;
import jos.api.uikit.UIDatePickerMode;
import jos.api.uikit.UILabel;
import jos.api.uikit.UIView;
import jos.api.uikit.UIViewController;
import jos.samples.controls.controls.ActionSheetDatePicker;

import com.google.j2objc.annotations.Export;
import com.google.j2objc.annotations.Outlet;
import com.google.j2objc.annotations.Selector;

public class IPadDatePicker extends UIViewController {

    @Outlet
    UIView view;

    @Outlet
    UIButton btnChooseDate;

    @Outlet
    UILabel lblDate;

    ActionSheetDatePicker actionSheetDatePicker;
    ActionSheetDatePicker actionSheetTimerPicker;

    public IPadDatePicker() {
        super("ActionSheetDatePicker_iPad", null);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        setTitle("Date Picker");

        // setup our custom action sheet date picker
        actionSheetDatePicker = new ActionSheetDatePicker(view);
        actionSheetDatePicker.setTitle("Choose Date:");
        actionSheetDatePicker.getDatePicker().addTarget(
                this,
                new Selector(
                        "handleActionSheetDatePickerValueChanged:"),
                UIControlEvent.VALUE_CHANGED);
        actionSheetDatePicker.getDatePicker().setMode(
                UIDatePickerMode.DATE_AND_TIME);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.DAY_OF_MONTH, -7);
        actionSheetDatePicker.getDatePicker()
                .setMinimumDate(calendar.getTime());
        calendar.add(GregorianCalendar.DAY_OF_MONTH, 14);
        actionSheetDatePicker.getDatePicker()
                .setMaximumDate(calendar.getTime());
        /*btnChooseDate.addTarget(new EventListener() {

            @Override
            public void onEvent(NSObject object, UIEvent event) {
                actionSheetDatePicker.show();
            }
        }, UIControlEvent.TOUCH_UP_INSIDE);*/
        btnChooseDate.addTarget(this, new Selector("handle_btnChooseDate"),
                UIControlEvent.TOUCH_UP_INSIDE);

        // setup our countdown timer
        actionSheetTimerPicker = new ActionSheetDatePicker(view);
        actionSheetTimerPicker.setTitle("Choose Time:");
        actionSheetTimerPicker.getDatePicker().setMode(
                UIDatePickerMode.COUNTDOWN_TIMER);
    }

    protected void handle_btnChooseDate() {
        actionSheetDatePicker.show();
    }

    @Export("handleActionSheetDatePickerValueChanged:")
    protected void handleActionSheetDatePickerValueChanged(
            NSObject sender) {
        lblDate.setText(((UIDatePicker) sender).date.toString());
    }

}
