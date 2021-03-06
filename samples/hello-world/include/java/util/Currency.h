//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/rwl/src/tball/jre_emul/android/libcore/luni/src/main/java/java/util/Currency.java
//
//  Created by rwl on 2/1/13.
//

@class JavaUtilHashMap;
@class JavaUtilLocale;

#import "JreEmulation.h"
#import "java/io/Serializable.h"

#define JavaUtilCurrency_serialVersionUID -158308464356906721

@interface JavaUtilCurrency : NSObject < JavaIoSerializable > {
 @public
  NSString *currencyCode_;
}

@property (nonatomic, copy) NSString *currencyCode;

+ (JavaUtilHashMap *)codesToCurrencies;
+ (JavaUtilHashMap *)localesToCurrencies;
- (id)initWithNSString:(NSString *)currencyCode;
+ (JavaUtilCurrency *)getInstanceWithNSString:(NSString *)currencyCode;
+ (JavaUtilCurrency *)getInstanceWithJavaUtilLocale:(JavaUtilLocale *)locale;
+ (NSString *)getCurrencyCodeForLocaleWithJavaUtilLocale:(JavaUtilLocale *)locale;
+ (NSString *)getCurrencySymbolForLocaleWithJavaUtilLocale:(JavaUtilLocale *)locale;
- (NSString *)getCurrencyCode;
- (NSString *)getSymbol;
- (NSString *)getSymbolWithJavaUtilLocale:(JavaUtilLocale *)locale;
- (NSString *)description;
@end
