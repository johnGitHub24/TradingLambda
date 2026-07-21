<#
.SYNOPSIS
    設定 TradingLambda 所需的 JDK 21 環境變數。

.DESCRIPTION
    【職責】把 JAVA_HOME／PATH 指到 JDK 21，讓 gradlew 能編譯與跑測。
    【技巧】PowerShell 工作階段變數（$env:JAVA_HOME）；需用「點來源」載入才會留在目前視窗。
    【概念】本機若預設 JAVA_HOME 是舊版（如 1.7），Gradle Wrapper 會直接失敗。
            教學專案統一用 JDK 21，與 workspace 其他 Trading* 專案一致。

.NOTES
    用途：跑 check／test 前固定 JDK。
    何時跑：開新終端、或 java -version 不是 21 時。
    成功標準：印出 JAVA_HOME=...\jdk-21；之後 .\gradlew.bat check 可啟動。

.EXAMPLE
    . .\scripts\env.ps1
    .\gradlew.bat check
#>
$jdk = "C:\Program Files\Java\jdk-21"
if (Test-Path $jdk) {
    $env:JAVA_HOME = $jdk
    $env:Path = "$jdk\bin;$env:Path"
    Write-Host "JAVA_HOME=$env:JAVA_HOME"
} else {
    Write-Warning "JDK 21 not found at $jdk — set JAVA_HOME manually."
}
