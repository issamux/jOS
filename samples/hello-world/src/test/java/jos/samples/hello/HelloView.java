package jos.samples.hello;

import static jos.api.graphicsimaging.CGGeometry.makeRect;
import jos.api.graphicsimaging.CGRect;
import jos.api.uikit.UIColor;
import jos.api.uikit.UILabel;
import jos.api.uikit.UITextAlignment;
import jos.api.uikit.UIView;

public class HelloView extends UIView {

    public HelloView(CGRect frame) {
        super(frame);
        this.setBackgroundColor(UIColor.LIGHT_GRAY);

        UILabel label = new UILabel(makeRect(0.0f, 0.0f, 320.0f, 30.0f));
        label.setText("Hello World");
        label.setCenter(getCenter());
        label.setBackgroundColor(UIColor.CLEAR);
        label.setTextAlignment(UITextAlignment.CENTER);

        addSubview(label);
    }

}