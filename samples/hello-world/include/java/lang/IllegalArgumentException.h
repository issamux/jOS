//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/rwl/src/tball/jre_emul/apache_harmony/classlib/modules/luni/src/main/java/java/lang/IllegalArgumentException.java
//
//  Created by rwl on 2/1/13.
//

@class JavaLangThrowable;

#import "JreEmulation.h"
#import "java/lang/RuntimeException.h"

#define JavaLangIllegalArgumentException_serialVersionUID -5365630128856068164

@interface JavaLangIllegalArgumentException : JavaLangRuntimeException {
}

- (id)init;
- (id)initWithNSString:(NSString *)detailMessage;
- (id)initWithNSString:(NSString *)message
 withJavaLangThrowable:(JavaLangThrowable *)cause;
- (id)initWithJavaLangThrowable:(JavaLangThrowable *)cause;
@end
