#!/bin/zsh

export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools:$PATH

set -e

echo ">>> Starting adb"
adb kill-server 2>/dev/null || true
adb start-server

echo ">>> Launching emulator in background"
emulator -avd and80 \
  -no-audio \
  -gpu swiftshader_indirect \
  &

EMU_PID=$!
echo "Emulator PID: $EMU_PID"

echo ">>> Waiting for device"
adb wait-for-device

echo ">>> Waiting for Android boot to complete"
until [[ "$(adb shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')" == "1" ]]; do
  sleep 5
done

echo ">>> Emulator is fully booted"
