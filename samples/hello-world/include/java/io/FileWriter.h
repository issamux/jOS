//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/rwl/src/tball/jre_emul/apache_harmony/classlib/modules/luni/src/main/java/java/io/FileWriter.java
//
//  Created by rwl on 2/1/13.
//

@class JavaIoFile;
@class JavaIoFileDescriptor;

#import "JreEmulation.h"
#import "java/io/OutputStreamWriter.h"

@interface JavaIoFileWriter : JavaIoOutputStreamWriter {
}

- (id)initWithJavaIoFile:(JavaIoFile *)file;
- (id)initWithJavaIoFile:(JavaIoFile *)file
                withBOOL:(BOOL)append;
- (id)initWithJavaIoFileDescriptor:(JavaIoFileDescriptor *)fd;
- (id)initWithNSString:(NSString *)filename;
- (id)initWithNSString:(NSString *)filename
              withBOOL:(BOOL)append;
@end
