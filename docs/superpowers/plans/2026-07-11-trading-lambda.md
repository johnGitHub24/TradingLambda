# TradingLambda Implementation Plan

> 歷史計畫（2026-07-11）。實作已完成；驗收以 [TradingLambda-SPEC.md](../../../TradingLambda-SPEC.md) 與 [docs/testing.md](../../testing.md) 為準。  
> 勿再依「superpowers:subagent-driven-development」或他倉路徑執行。

**Goal:** Java 21 Lambda／Stream 精簡教學專案（本 repo only）。

**Architecture:** 純 Gradle Java 庫；`domain` + 三個 `lab` 參考實作；`practice/` 空殼不進測試；驗證 `.\scripts\check.ps1`。

**Tech Stack:** Java 21 · Gradle · JUnit 5 · EngineeringOS eos-minimal @ 0.1.10

**Spec:** `docs/superpowers/specs/2026-07-11-trading-lambda-design.md`

---

### Task 1: Gradle scaffold + EOS 薄文件

- [x] **Step 1:** `settings.gradle`／`build.gradle`（toolchain 21、JUnit 5、`check`＝`test`）
- [x] **Step 2:** EOS 薄文件（無 DB／Security／HTTP）
- [x] **Step 3:** wrapper 可執行

### Task 2: Domain + Labs（TDD）

- [x] **Step 1:** 三個 Lab 測試（15 unit-only Case）
- [x] **Step 2:** domain + lab 綠
- [x] **Step 3:** practice 空殼
- [x] **Step 4:** `.\scripts\check.ps1` 全綠

### Task 3–4: 他倉（不做）

CodeNarc `scan-all` 與 Locust baseline **不屬本 repo**。本專案 Gate 僅 `check.ps1`。

---

## Self-review

- Labs／unit-layer-only DoD 已落地
- 無 Spring／假整合測試
- Types: Order fields 與設計一致
