# TradingLambda 設計規格

> 日期：2026-07-11  
> 狀態：待使用者審閱後進入實作計畫  
> 繼承：EngineeringOS eos-minimal @ 0.1.4（generic 模板）

## 1. 目標

建立 **Java 21 Lambda／Stream API 精簡教學專案**（純 Gradle、無 Spring／DB），提供可跑的參考實作與練習空殼，並在同一次工作流中完成 workspace CodeNarc 弱點掃描與 TradingLocustJMeter 基線壓測。

## 2. 決策摘要

| 項目 | 選擇 |
|------|------|
| Lambda 含義 | Java Lambda／Stream（非 AWS） |
| 課綱範圍 | 精簡核心：語法、Predicate／Function／Consumer、filter／map／collect＋訂單情境 |
| 專案形態 | 純 Java 21 + Gradle（對齊 TradingCodeNarc） |
| 實作策略 | 方案 1：`lab/` 參考實作 + `practice/` 空殼；測試只覆蓋 lab |

## 3. 範圍

### In scope

- Domain：精簡 `Order`（id、symbol、side、qty、price、status）
- Labs：`LambdaBasics`、`FunctionalInterfacesLab`、`OrderStreamLab`
- EOS 薄文件：`CLAUDE.md`、`TradingLambda-SPEC.md`、`docs/architecture.md`、`docs/testing.md`、`docs/練習建議.md`、`README.md`
- 驗證：`.\gradlew.bat check`
- CodeNarc：`_projects.ps1` 納入 `TradingLambda`；執行 `scan-all.ps1`
- 壓測：掃描產出 `reports/TradingLocustJMeter/` 後執行 `TradingLocustJMeter\scripts\run-baseline.ps1`

### Out of scope

- Optional、並行 Stream、自訂 Collector、方法參考進階、Comparator 鏈綜合題
- Spring Boot、HTTP API、AWS Lambda、業務 DB／Security
- 依 narc 報告內容改寫 Locust 場景（掃描與壓測串行，但場景獨立）

## 4. 架構

```text
TradingLambda/
├── CLAUDE.md
├── README.md
├── TradingLambda-SPEC.md
├── build.gradle / settings.gradle / gradlew*
├── docs/
│   ├── architecture.md
│   ├── testing.md
│   ├── 練習建議.md
│   └── superpowers/specs/   # 本設計文件
├── src/main/java/com/trading/lambda/
│   ├── domain/Order.java · OrderSide.java · OrderStatus.java
│   ├── lab/lambda/LambdaBasics.java
│   ├── lab/functional/FunctionalInterfacesLab.java
│   └── lab/stream/OrderStreamLab.java
├── practice/com/trading/lambda/practice/   # 同簽名空殼，不進主測試 classpath
└── src/test/java/com/trading/lambda/...
```

資料流：測試 fixture 建立 `List<Order>` → 呼叫 lab 方法（Lambda／Stream）→ 斷言結果。無外部 I/O。

## 5. 練習題清單

| Lab | 方法職責 |
|-----|----------|
| LambdaBasics | 依 status 過濾；依 price 排序；格式化 `SYMBOL side qty@price` |
| FunctionalInterfacesLab | Predicate（可成交）；Function（名目金額 qty×price）；Consumer（收集／副作用） |
| OrderStreamLab | filter 買進＋PENDING；map 名目金額；collect 總額／依 symbol 分組 |

錯誤處理：空列表回傳空集合／零；不拋業務例外。非法 null 元素不特別防禦（測試不傳 null）。

## 6. 測試 DoD

- [ ] `.\gradlew.bat check` 全綠
- [ ] 每個 Lab ≥ 1 Happy Path + 1 邊界（空列表或無符合條件）
- [ ] EOS 文件齊全；DB／Security 標 N/A
- [ ] `practice/` 不導致 check 失敗

## 7. 後續工作流（同任務）

1. **實作 TradingLambda**（依本規格 + 實作計畫）
2. **CodeNarc 全掃**
   ```powershell
   cd d:\ClaudeCode\TradingCodeNarc
   .\scripts\scan-all.ps1
   ```
   - 更新 `_projects.ps1`：加入 `TradingLambda`（Scannable）
   - EngineeringOS／無 Java 專案：允許 0 findings，不因單案 findings 中止全批（預設不帶 `-StopOnError`）
3. **基線壓測**（`reports/TradingLocustJMeter/` 產生後）
   ```powershell
   cd d:\ClaudeCode\TradingLocustJMeter
   .\scripts\run-baseline.ps1
   ```

## 8. 成功標準

| 步驟 | 標準 |
|------|------|
| TradingLambda | `gradlew check` 綠；練習範例可讀可跑 |
| CodeNarc | 各 scannable 專案有 `reports/<Id>/narc-report.md`；`_scan-index.md` 更新 |
| 壓測 | 靶場健康 + Locust headless 結束並產出 HTML 報表 |
