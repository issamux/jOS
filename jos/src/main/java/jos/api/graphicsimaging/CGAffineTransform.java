package jos.api.graphicsimaging;

import com.google.j2objc.annotations.Register;

@Register(isWrapper = true, isPrimitive = true)
public class CGAffineTransform {
    CGFloat a, b, c, d;
    CGFloat tx, ty;
}
