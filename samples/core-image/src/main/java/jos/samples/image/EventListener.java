package jos.samples.image;

import jos.api.foundation.NSObject;
import jos.api.uikit.UIEvent;

import com.google.j2objc.annotations.Export;
import com.google.j2objc.annotations.Target;

@Target("onEvent:event:")
//@Register(isWrapper = true)
public interface EventListener {

    @Export("onEvent:event:")
    public void onEvent(NSObject sender, UIEvent event);

}
