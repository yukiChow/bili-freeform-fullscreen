# Bili Freeform Fullscreen

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[简体中文](README_zh-CN.md)

An Xposed module for using full-screen playback from **Bilibili International**
(`com.bilibili.app.in`) while the app is in a HyperOS freeform window.

## What it does

When Bilibili's full-screen player widgets call
`Activity.isInMultiWindowMode()`, the module returns `false` for that call.
This lets the player follow its normal full-screen path instead of treating the
freeform window as an unsupported multi-window environment.

The hook is deliberately narrow:

- It loads only into `com.bilibili.app.in`.
- It changes results only for call stacks containing
  `GeminiPlayerFullscreenWidget` or `PlayerFullscreenWidget`.
- It has no settings screen, preference storage, network access, or Bilibili
  settings entry.

The last point avoids the obsolete `MODE_WORLD_READABLE` preference bridge used
by MBGA 1.5.0, which crashes on Android 17.

## Install with LSPatch

1. Remove any separately installed MBGA app.
2. In LSPatch, patch the **original** Bilibili International APK or split APK
   set in **Integrated mode**. Do not patch an APK that already embeds MBGA.
3. Add this module APK as the only module, then install the resulting patched
   Bilibili app.
4. Open a video in a freeform window and tap the in-player full-screen button.

If a LSPatch version lists only installed module apps, install this APK first,
then select it as the module. Its launcher activity is informational only;
Bilibili never starts it.

## Build

Requirements: JDK 17 and Android SDK Platform 35.

```sh
./gradlew :app:assembleDebug
```

The signed debug APK is written to:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Download

The build used for the first release is included in this repository:

- [Bili-Freeform-Fullscreen-v1.0.0.apk](release/Bili-Freeform-Fullscreen-v1.0.0.apk)
  (`SHA-256: 306b85b3ab47d78d242e492f5f6ddc76f42b4ab02e016693d107e4c9e6aed0e6`)

## Compatibility

The first release was built and tested for the Bilibili International 3.20.4
player code targeted by the corresponding MBGA hook. A future Bilibili update
can rename its player widgets; in that case the module will safely do nothing
until its class-name match is updated.

This project is unaffiliated with Bilibili, HyperOS, LSPatch, or MBGA.

## License

[MIT](LICENSE)
