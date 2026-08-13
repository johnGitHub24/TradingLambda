# Testing and Verification — TradingLambda

> 衝突以 [TradingLambda-SPEC.md](../TradingLambda-SPEC.md) 為準。

## Check command

```powershell
.\gradlew.bat check
```

## Test layers

| Layer | Location | Focus |
|-------|----------|-------|
| Unit | `src/test/.../lab/**` | 各 Lab Happy Path + 空列表／無符合條件 |

## DoD

- [x] `.\gradlew.bat check` 全綠
- [x] 每個 Lab ≥ 1 Happy Path + 1 邊界
- [x] `practice/` 不納入 check

## Key classes

`LambdaBasics`、`FunctionalInterfacesLab`、`OrderStreamLab`、`Order`
