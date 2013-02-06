package jos.samples.controls.screens.iphone;

import com.google.j2objc.annotations.EventListener;
import com.google.j2objc.annotations.Export;

import jos.api.foundation.NSCoder;
import jos.api.graphicsimaging.CGGeometry;
import jos.api.system.IntPtr;
import jos.api.uikit.UIControlEvent;
import jos.api.uikit.UISegmentedControl;
import jos.api.uikit.UISegmentedControlStyle;
import jos.api.uikit.UIViewController;

public class SegmentedControls2 extends UIViewController {

    UISegmentedControl segControl1;

    public SegmentedControls2(IntPtr handle) {
        super(handle);
        initialize();
    }

    @Export(selector = "initWithCoder:")
    public SegmentedControls2(NSCoder coder) {
        super(coder);
        initialize();
    }

    public SegmentedControls2() {
        super("SegmentedControls2_iPhone", null);
        initialize();
    }

    void initialize() {
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        this.title = "Programmatic Segmented Controls";

        segControl1 = new UISegmentedControl();
        segControl1.controlStyle = UISegmentedControlStyle.Bordered;
        segControl1.insertSegment("One", 0, false);
        segControl1.insertSegment("Two", 1, false);
        segControl1.setWidth(100f, 1);
        segControl1.selectedSegment = 1;
        segControl1.frame = CGGeometry.CGRectMake(20, 20, 280, 44);
        this.view.addSubview(segControl1);

        segControl1.addTarget(new EventListener() {

            @Override
            public void onEvent(Object sender, int event) {
                System.out.println("Item " + ((UISegmentedControl) sender).selectedSegment + " selected");
            }
        }, UIControlEvent.ValueChanged);;
    }
}
