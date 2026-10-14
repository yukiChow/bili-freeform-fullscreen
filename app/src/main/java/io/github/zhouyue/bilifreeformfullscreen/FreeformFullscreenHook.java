package io.github.zhouyue.bilifreeformfullscreen;

import android.app.Activity;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Makes the Bilibili International player's full-screen widgets treat their
 * containing activity as a normal full-screen activity while it is in a
 * HyperOS freeform window.
 */
public final class FreeformFullscreenHook implements IXposedHookLoadPackage {
    private static final String BILIBILI_INTERNATIONAL = "com.bilibili.app.in";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!BILIBILI_INTERNATIONAL.equals(lpparam.packageName)) {
            return;
        }

        XposedHelpers.findAndHookMethod(
                Activity.class,
                "isInMultiWindowMode",
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        for (StackTraceElement frame : new Throwable().getStackTrace()) {
                            String className = frame.getClassName();
                            if (className.contains("GeminiPlayerFullscreenWidget")
                                    || className.contains("PlayerFullscreenWidget")) {
                                param.setResult(false);
                                return;
                            }
                        }
                    }
                });
    }
}
