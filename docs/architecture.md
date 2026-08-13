# Architecture — TradingLambda

> 衝突以 [TradingLambda-SPEC.md](../TradingLambda-SPEC.md) 為準。EOS @ 0.1.4

## Components

| Component | Package / Path | Responsibility |
|-----------|----------------|----------------|
| Domain | `domain` | `Order`、`OrderSide`、`OrderStatus` |
| Lambda lab | `lab.lambda.LambdaBasics` | 過濾、排序、格式化字串 |
| Functional lab | `lab.functional.FunctionalInterfacesLab` | Predicate／Function／Consumer |
| Stream lab | `lab.stream.OrderStreamLab` | filter／map／collect |
| Practice | `practice/`（專案根） | 同簽名空殼，不進 main classpath |
| Tests | `src/test/...` | 驗證 lab 參考實作 |

## Runtime

無常駐行程。學習流程：讀 lab → 在 practice 重寫 → 對照測試／自行驗證。

## Data

記憶體內 `List<Order>` fixture；無 DB／外部 I/O。

## Visual maps

| 文件 | 用途 |
|------|------|
| [codeGraphic.html](codeGraphic.html) | Tab：Labs／Stream／Practice／Packages（圖為主） |
| [練習建議.md](練習建議.md) | 學習路線與分級題 |
