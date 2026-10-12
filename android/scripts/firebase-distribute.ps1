# Upload a PxLScan build to Firebase App Distribution (project pxlapps-f816b).
#   powershell -ExecutionPolicy Bypass -File android\scripts\firebase-distribute.ps1 [path\to.apk]
# No google-services.json or Firebase SDK is needed for App Distribution; only the App ID.
# One-time: `npx firebase-tools login` on the uploading machine, and a tester group in the console.
param([string]$Apk = "")
$ErrorActionPreference = "Stop"
# Resolve a given APK path against the CALLER's folder before moving to android\.
if ($Apk) { $Apk = (Resolve-Path $Apk).Path }
Set-Location (Join-Path $PSScriptRoot "..")
function Prop($k) {
    if (Test-Path firebase.properties) {
        $l = Get-Content firebase.properties | Where-Object { $_ -match "^$k=" } | Select-Object -First 1
        if ($l) { return $l.Substring($k.Length + 1).Trim() }
    }
    return ""
}
if (-not $Apk) {
    # Prefer the newest signed release in dist\, else the latest debug build.
    $candidates = @(Get-ChildItem ..\dist\PxLScan-*.apk -ErrorAction SilentlyContinue) +
        @(Get-Item app\build\outputs\apk\debug\app-debug.apk -ErrorAction SilentlyContinue)
    $Apk = ($candidates | Where-Object { $_ } | Sort-Object LastWriteTime -Descending | Select-Object -First 1).FullName
}
$AppId = if ($env:FIREBASE_ANDROID_APP_ID) { $env:FIREBASE_ANDROID_APP_ID } else { Prop "appId" }
$Groups = if ($env:FIREBASE_GROUPS) { $env:FIREBASE_GROUPS } else { Prop "groups" }
if (-not $Groups) { $Groups = "testers" }
if (-not $Apk -or -not (Test-Path $Apk)) { throw "No APK (build with gradlew assembleDebug, or pass -Apk)" }
if (-not $AppId) { throw "Set appId in android\firebase.properties or FIREBASE_ANDROID_APP_ID" }
$Notes = (git log -1 --format="%h %s") 2>$null
if (-not $Notes) { $Notes = "PxLScan Android build" }
Write-Host "Distributing $Apk to '$Groups'..."
npx -y firebase-tools appdistribution:distribute "$Apk" --app "$AppId" --groups "$Groups" --release-notes "$Notes"
