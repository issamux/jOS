package jos.api.graphicsimaging;

import com.google.j2objc.annotations.Function;
import com.google.j2objc.annotations.Register;

@Register(isWrapper = true)
public abstract class CGGeometry {

    @Function
    public static CGRect makeRect(float x, float y, float width, float height) {
        return null;
    }
}
