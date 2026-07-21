# TradingLambda

Java 21 **Lambda／Stream** 精簡練習專案（交易訂單情境）。

## 快速開始

```powershell
cd d:\ClaudeCode\TradingLambda
.\gradlew.bat check
```

## 學什麼

| Lab | 內容 |
|-----|------|
| `LambdaBasics` | Lambda 過濾／排序／格式化 |
| `FunctionalInterfacesLab` | Predicate／Function／Consumer |
| `OrderStreamLab` | Stream filter／map／collect |

參考實作：`src/main/java/com/trading/lambda/lab/`  
練習空殼：`practice/com/trading/lambda/practice/`（自行填寫，不影響 `check`）

## 文件

- 權威規格：[TradingLambda-SPEC.md](TradingLambda-SPEC.md)
- 架構：[docs/architecture.md](docs/architecture.md)
- Tab 式架構圖：[docs/codeGraphic.html](docs/codeGraphic.html)（Labs／Stream／Practice／Packages）
- 測試：[docs/testing.md](docs/testing.md)
- 練習路線：[docs/練習建議.md](docs/練習建議.md)
- 註解（detailed）：[docs/註解規範.md](docs/註解規範.md)

繼承 EngineeringOS eos-minimal @ 0.1.5。  
註解深度：`detailed`（見 [docs/註解規範.md](docs/註解規範.md)）。
