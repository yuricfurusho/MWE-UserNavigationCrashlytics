adb shell setprop debug.firebase.analytics.app com.yuricfurusho.temp01firebase

adb shell setprop log.tag.FA VERBOSE
adb shell setprop log.tag.FA-SVC VERBOSE
adb logcat -v time -s FA FA-SVC