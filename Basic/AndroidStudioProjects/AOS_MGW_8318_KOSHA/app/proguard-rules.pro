# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


##--------------- Begin: Debug ProGuard rules  ----------
-ignorewarnings
#-dontobfuscate
#-keepattributes SoureFile
#-keepattributes LineNumberTable
#-keepattributes InnerClasses
##--------------- End: Debug ProGuard rules  ----------

##---------------Begin: proguard configuration for Common  ----------
# Don't note duplicate definition (Legacy Apche Http Client)
-dontnote android.net.http.*
-dontnote org.apache.http.**

# Add when compile with JDK 1.7
-keepattributes EnclosingMethod

# Android components
-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
##---------------End: proguard configuration for Common  ----------

##---------------Begin: For enumeration classes  ----------
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
##---------------End: For enumeration classes  ----------

##---------------Begin: Appcompat and support  ----------
-keep interface android.support.v7.** { *; }
-keep class android.support.v7.** { *; }
#-keep interface android.support.v4.** { *; }
#-keep class android.support.v4.** { *; }
-dontwarn android.app.Notification
##---------------End: Appcompat and support  ----------

##---------------Begin: WebView with JS, uncomment the following and specify the fully qualified class name to the js interface  ----------
-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepclassmembers class **.*AndroidBridge {
    *;
}
-keep public class **.*AndroidBridge
##---------------End: WebView with JS, uncomment the following and specify the fully qualified class name to the js interface  ----------

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.hs.mobile.gw.openapi.vo.** { *; }
-keep class com.hs.mobile.gw.openapi.square.vo.** { *; }
-keep class com.hs.mobile.gw.openapi.squareplus.vo.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
##---------------End: proguard configuration for Gson  ----------

##---------------Begin: proguard configuration for Okhttp  ----------
-dontwarn okhttp.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
##---------------End: proguard configuration for Okhttp  ----------

##---------------Begin: proguard configuration for log4j  ----------
-keep class de.mindpipe.android.logging.log4j.** { *; }
-keep class org.apache.log4j.** { *; }
##---------------End: proguard configuration for log4j  ----------

##---------------Begin: proguard configuration for jackson  ----------
-keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
##---------------End: proguard configuration for jackson  ----------

##---------------Begin: proguard configuration for AQuery  ----------
-keep class com.androidquery.** { *; }
##---------------End: proguard configuration for AQuery  ----------

##---------------Begin: proguard configuration for Cordova  ----------
-keep class org.apache.cordova.** { *; }
-keep class com.hs.mobile.gw.plugin.** { *; }
##---------------End: proguard configuration for Cordova  ----------

##---------------Begin: proguard configuration for ShortcutBadger  ----------
-keep class me.leolin.shortcutbadger.impl.** { <init>(...); }
##---------------End: proguard configuration for ShortcutBadger  ----------
