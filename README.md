# TradingLambda

Java 21 **Lambda／Stream** 精簡練習專案（交易訂單情境）。

## 快速開始

本專案是 **Lambda／Stream LAB**（無 Spring Boot、無 `bootRun`、無 `*Application.java`）。

驗證入口（唯一 Gate）：

```powershell
.\scripts\check.ps1
```

（載入 JDK 21 後 `gradlew check`＝僅單元測試。）

**IntelliJ：** Open 專案根目錄 → Project SDK = 21 → Gradle Sync（Build and run using = Gradle）→ Gradle 視窗跑 **`check`**（或 `test`）。不要找 Application 綠箭頭。

## 學什麼

| Lab | 內容 |
|-----|------|
| `LambdaBasics` | Lambda 過濾／排序／格式化 |
| `FunctionalInterfacesLab` | Predicate／Function／Consumer |
| `OrderStreamLab` | Stream filter／map／collect |

參考實作：`src/main/java/com/trading/lambda/lab/`  
練習空殼：`practice/com/trading/lambda/practice/`（自行填寫，不影響 `check`）

## 文件入口

單一入口：本 README。衝突以主規格為準。

| 文件 | 說明 |
|------|------|
| [TradingLambda-SPEC.md](TradingLambda-SPEC.md) | **主規格（權威）** |
| [docs/architecture.md](docs/architecture.md) | 分層與模組 |
| [docs/codeGraphic.html](docs/codeGraphic.html) | 架構圖（非權威） |
| [docs/testing.md](docs/testing.md) | 測試／Case／check |
| [docs/練習建議.md](docs/練習建議.md) | 練習 |
| [CLAUDE.md](CLAUDE.md) | AI 薄規則 |
| [scripts/README.md](scripts/README.md) | 驗證／啟動腳本 |

