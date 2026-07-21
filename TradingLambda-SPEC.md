# TradingLambda Specification

> **Authority contract.** Conflicts resolve to this file.  
> EOS docs standard: EngineeringOS `eos-minimal/knowledge/documentation.md` @ 0.1.4

## 0. Document map

| File | Role |
|------|------|
| This file | Master spec (authority) |
| [README.md](README.md) | Entry |
| [CLAUDE.md](CLAUDE.md) | Thin AI rules |
| [docs/architecture.md](docs/architecture.md) | Components |
| [docs/testing.md](docs/testing.md) | Test / DoD |
| [docs/練習建議.md](docs/練習建議.md) | 學習路線 |

## 1. Scope

- **Purpose:** Java 21 Lambda／Stream 精簡教學：語法、Predicate／Function／Consumer、filter／map／collect，搭配交易訂單情境。
- **Stack:** Java 21 · JUnit 5 · Gradle
- **Non-goals:** Optional、並行 Stream、自訂 Collector、Spring、AWS Lambda、業務 DB／Security

## 2. Architecture

`domain.Order` → `lab.*` 參考實作（Lambda／Stream）→ JUnit 驗證。  
`practice/` 為同簽名空殼，供學員填寫，不納入 `src/main` 編譯與 `check`。

## 3. API / Contract

本專案為 **教學庫**，無 HTTP API。公開入口為各 Lab 的靜態／實例方法（見 `docs/architecture.md`）。

## 4. Test DoD

- [x] `.\gradlew.bat check` 全綠
- [x] 每個 Lab ≥ 1 Happy Path + 1 邊界（空列表／無符合條件）
- [x] `practice/` 不導致 check 失敗

## 5. Changelog

| Date | Note |
|------|------|
| 2026-07-11 | 初版：精簡核心 Labs + EOS 骨架 |
