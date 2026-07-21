# TradingLambda Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 建立 Java 21 Lambda／Stream 精簡教學專案，並完成 workspace CodeNarc 全掃與 Locust 基線壓測。

**Architecture:** 純 Gradle Java 庫；`domain` + 三個 `lab` 參考實作；`practice/` 空殼不進測試；驗證 `gradlew check`。

**Tech Stack:** Java 21 · Gradle 8.5 · JUnit 5 · EngineeringOS eos-minimal @ 0.1.4

**Spec:** `docs/superpowers/specs/2026-07-11-trading-lambda-design.md`

---

### Task 1: Gradle scaffold + EOS 薄文件

**Files:**
- Create: `build.gradle`, `settings.gradle`, `CLAUDE.md`, `README.md`, `TradingLambda-SPEC.md`
- Create: `docs/architecture.md`, `docs/testing.md`, `docs/練習建議.md`
- Copy: `gradlew*`, `gradle/wrapper/`（自 TradingCodeNarc）

- [ ] **Step 1:** 建立 `settings.gradle`（`rootProject.name = 'TradingLambda'`）與 `build.gradle`（java toolchain 21、JUnit 5、`check`=`test`）
- [ ] **Step 2:** 寫 EOS 薄文件（無 DB／Security）
- [ ] **Step 3:** 確認 wrapper 可執行（之後 Task 驗證）

### Task 2: Domain + Labs（TDD）

**Files:**
- Create: `domain/Order.java`, `OrderSide.java`, `OrderStatus.java`
- Create: `lab/lambda/LambdaBasics.java`, `lab/functional/FunctionalInterfacesLab.java`, `lab/stream/OrderStreamLab.java`
- Create: 對應 `*Test.java`
- Create: `practice/` 空殼（同簽名、`UnsupportedOperationException`）

- [ ] **Step 1:** 先寫三個 Lab 的失敗測試
- [ ] **Step 2:** 實作 domain + lab 讓測試綠
- [ ] **Step 3:** 補 practice 空殼
- [ ] **Step 4:** `.\gradlew.bat check` 全綠

### Task 3: CodeNarc 納入 TradingLambda

**Files:**
- Modify: `TradingCodeNarc/scripts/_projects.ps1`

- [ ] **Step 1:** 加入 `TradingLambda`（Scannable=$true, Order=17）
- [ ] **Step 2:** 執行 `.\scripts\scan-all.ps1`

### Task 4: Locust 基線壓測

- [ ] **Step 1:** 確認 `reports/TradingLocustJMeter/` 已產生
- [ ] **Step 2:** `TradingLocustJMeter\scripts\run-baseline.ps1`

---

## Self-review

- Spec coverage: Labs／DoD／scan-all／baseline 皆有 task
- No placeholders
- Types: Order fields 與設計一致
