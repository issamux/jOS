//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/rwl/src/tball/jre_emul/apache_harmony/classlib/modules/luni/src/main/java/java/util/Queue.java
//
//  Created by rwl on 2/1/13.
//

#import "JreEmulation.h"
#import "java/util/Collection.h"

@protocol JavaUtilQueue < JavaUtilCollection, NSObject >
- (BOOL)offerWithId:(id)o;
- (id)poll;
- (id)remove;
- (id)peek;
- (id)element;
@end
