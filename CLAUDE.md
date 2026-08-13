# TradingLambda — 專案規則（薄）

繼承：EngineeringOS eos-minimal @ **0.1.10**  
公版：`EngineeringOS/eos-minimal/`  
權威規格：[TradingLambda-SPEC.md](TradingLambda-SPEC.md)

## 與公版差異

- 類型：Java Lambda／Stream 教學（非 Spring Boot 服務）
- Stack：Java 21 · JUnit 5 · Gradle
- 驗證入口：`.\scripts\check.ps1`（載入 JDK 21 後 `gradlew check`＝**僅 unit**）
- 本機：IntelliJ／Gradle **`check`／`test`**（**無** `bootRun`、**無** `*Application.java`）
- Docs standard：`knowledge/documentation.md`
- 無 DB／Security／HTTP API（N/A）；Case 全為 **unit-layer-only**

## 註解深度

- comment_verbosity: **detailed**
- 權威：`EngineeringOS/eos-minimal/knowledge/comments.md` §0／§3b
- 本專案對照：[docs/註解規範.md](docs/註解規範.md)

## 本專案專屬

- Domain：精簡 Order；Labs：LambdaBasics／FunctionalInterfaces／OrderStream
- 練習空殼：`practice/`（不進主測試 classpath）
- 詳述：`docs/architecture.md`、`docs/testing.md`、`docs/練習建議.md`
- 設計：`docs/superpowers/specs/2026-07-11-trading-lambda-design.md`


## Git Remote
- 帳號：`johnGitHub24`；一專案一 repo
- 規範：`EngineeringOS/eos-minimal/knowledge/專案上船-GitHub.md`

## 回寫

問題與公版改善建議 → `EngineeringOS/eos-minimal/feedback/SYNC_LOG.md`
