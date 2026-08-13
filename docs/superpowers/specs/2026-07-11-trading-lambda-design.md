# TradingLambda 設計規格

> 日期：2026-07-11  
> 狀態：已實作（Gate＝`.\scripts\check.ps1`）  
> 繼承：EngineeringOS eos-minimal @ 0.1.10（generic 模板）  
> 本檔為歷史設計；驗收衝突以 [TradingLambda-SPEC.md](../../../TradingLambda-SPEC.md) 為準。

## 1. 目標

建立 **Java 21 Lambda／Stream API 精簡教學專案**（純 Gradle、無 Spring／DB），提供可跑的參考實作與練習空殼。

## 2. 決策摘要

| 項目 | 選擇 |
|------|------|
| Lambda 含義 | Java Lambda／Stream（非 AWS） |
| 課綱範圍 | 精簡核心：語法、Predicate／Function／Consumer、filter／map／collect＋訂單情境 |
| 專案形態 | 純 Java 21 + Gradle |
| 實作策略 | 方案 1：`lab/` 參考實作 + `practice/` 空殼；測試只覆蓋 lab（**unit-layer-only**） |

## 3. 範圍

### In scope

- Domain：精簡 `Order`（id、symbol、side、qty、price、status）
- Labs：`LambdaBasics`、`FunctionalInterfacesLab`、`OrderStreamLab`
- EOS 薄文件：`CLAUDE.md`、`TradingLambda-SPEC.md`、`docs/architecture.md`、`docs/testing.md`、`docs/練習建議.md`、`README.md`
- 驗證：`.\scripts\check.ps1`（`gradlew check`＝僅 unit）

### Out of scope

- Optional、並行 Stream、自訂 Collector、方法參考進階、Comparator 鏈綜合題
- Spring Boot、HTTP API、AWS Lambda、業務 DB／Security
- 假 Spring／MockMvc 整合測試（教學庫無邊界可對打）
- 他倉工作（CodeNarc 全掃、Locust 基線）— 不屬本 repo Gate

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

- [x] `.\scripts\check.ps1` 全綠
- [x] 每個 Lab ≥ 1 Happy Path + 1 邊界（空列表或無符合條件）
- [x] 15 Case（CASE-LAMBDA／FUNC／STREAM）標 **unit-layer-only**（見 `docs/testing.md`）
- [x] EOS 文件齊全；DB／Security 標 N/A
- [x] `practice/` 不導致 check 失敗

## 7. 後續（他倉，非本 Gate）

CodeNarc 掃描與 Locust 基線屬 **TradingCodeNarc**／**TradingLocustJMeter**，不在本專案 `check.ps1` 內。

## 8. 成功標準

| 步驟 | 標準 |
|------|------|
| TradingLambda | `.\scripts\check.ps1` 綠；練習範例可讀可跑；無 Spring 整合層 |
