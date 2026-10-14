package io.github.zhouyue.bilifreeformfullscreen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/** A small launcher activity so LSPatch can select this APK as a module. */
public final class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        int padding = (int) (24 * getResources().getDisplayMetrics().density);
        text.setPadding(padding, padding, padding, padding);
        text.setTextSize(16);
        text.setText(
                "Bili Freeform Fullscreen is enabled when embedded by LSPatch.\n\n"
                        + "It applies only to Bilibili International and has no settings to configure.");
        setContentView(text);
    }
}
