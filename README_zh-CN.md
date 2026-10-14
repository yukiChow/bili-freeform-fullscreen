# Bili Freeform Fullscreen

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[English](README.md)

这是一个供 **哔哩哔哩国际版**（`com.bilibili.app.in`）在 HyperOS 自由窗口中使用全屏播放的 Xposed 模块。

## 功能

国际版播放器的全屏控件调用 `Activity.isInMultiWindowMode()` 时，模块只对该次调用返回 `false`。播放器因此会走正常的全屏逻辑，而不会把自由窗口视为不支持全屏的多窗口环境。

Hook 的作用范围经过限制：

- 只加载到 `com.bilibili.app.in`；
- 只处理调用栈中包含 `GeminiPlayerFullscreenWidget` 或 `PlayerFullscreenWidget` 的播放器全屏请求；
- 不含设置页面、偏好设置存储、网络请求，也不会在哔哩哔哩设置页添加入口。

没有设置页可以避免 MBGA 1.5.0 使用旧版 `MODE_WORLD_READABLE` 偏好设置桥接时，在 Android 17 上发生的崩溃。

## 通过 LSPatch 安装

1. 卸载单独安装的 MBGA。
2. 在 LSPatch 中选择**原始的**哔哩哔哩国际版 APK 或 split APK 集合，并选择**集成模式**。不要对已经集成 MBGA 的 APK 再次打补丁。
3. 仅添加本模块 APK，完成打包并安装输出的哔哩哔哩国际版。
4. 将视频置于自由窗口后，点击播放器内的全屏按钮进行测试。

如果你的 LSPatch 版本只能从已安装的模块应用中选择，可以先安装本 APK，再在模块列表中选中它。它的启动页只有说明内容，哔哩哔哩不会主动唤起该页面。

## 下载

- [Bili-Freeform-Fullscreen-v1.0.0.apk](release/Bili-Freeform-Fullscreen-v1.0.0.apk)
- SHA-256：`306b85b3ab47d78d242e492f5f6ddc76f42b4ab02e016693d107e4c9e6aed0e6`

## 从源码构建

需要 JDK 17 与 Android SDK Platform 35：

```sh
./gradlew :app:assembleDebug
```

构建结果位于：

```text
app/build/outputs/apk/debug/app-debug.apk
```

## 兼容性

首个版本针对哔哩哔哩国际版 3.20.4 中的播放器代码编写。将来若应用更新并重命名播放器控件，模块会安全地不生效，直到相应类名更新。

本项目与哔哩哔哩、HyperOS、LSPatch 和 MBGA 均无隶属关系。

## 许可证

[MIT](LICENSE)
