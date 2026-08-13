# Testing and Verification — TradingLambda

> 衝突以 [TradingLambda-SPEC.md](../TradingLambda-SPEC.md) 為準。  
> 規範：EngineeringOS `knowledge/testing.md`（單元 ↔ 整合成對）。  
> 本專案為 **Java Lambda／Stream 教學庫**：**無 HTTP API、無 Spring Boot、無 `bootRun`、無 `*Application.java`**。  
> 全部 Case 為 **unit-layer-only**（教學 labs）；**禁止**捏造 MockMvc／Spring 整合層來「成對」。

## Check command（Gate）

```powershell
.\scripts\check.ps1
```

腳本載入 JDK 21 後跑 `gradlew check`（本專案 `check`＝`test`，僅單元）。  
或已設 `JAVA_HOME` 時：`.\gradlew.bat check`。無 `bootRun`。

掃描（預期 unit-only，非紅燈）：

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File ..\EngineeringOS\eos-minimal\hooks\scan-paired-tests.ps1 -ProjectRoot . -WarnOnly
```

`-WarnOnly` 會列出 15 個 unit-only Case；這是**刻意單層**，不是漏寫整合。

## Test layers

| Layer | Location | Focus |
|-------|----------|-------|
| **Unit**（唯一層） | `src/test/java/com/trading/lambda/lab/**` | 各 Lab Happy Path + 空列表／無符合條件 |
| Integration | **N/A** | 無 HTTP／DB／Security／訊息邊界；**不**新增 `integrationTest` |
| Performance | **N/A** | 教學庫無 SLA；壓測屬其他專案 |

`practice/` 不進測試 classpath，不影響 Gate。

## 單層（刻意僅單元）Case — teaching labs

下列 15 個 Case **只存在單元層**。契約是 Lab 靜態方法對 `List<Order>` fixture 的純邏輯；沒有可對打的 HTTP／DB。  
`scan-paired-tests` 報 `[DRIFT] Case ID only in UNIT layer` 時，以本節為準（T4 降級為「單層即可」）。

| Case ID | Lab／測試類 | 行為 |
|---------|-------------|------|
| CASE-LAMBDA-001 | `LambdaBasicsTest` | `filterByStatus(PENDING)` 只留符合項 |
| CASE-LAMBDA-002 | `LambdaBasicsTest` | 空列表 → 空（不拋例外） |
| CASE-LAMBDA-003 | `LambdaBasicsTest` | `sortByPriceAsc` 依 price 升冪 |
| CASE-LAMBDA-004 | `LambdaBasicsTest` | `formatOrder` → `SYMBOL side qty@price` |
| CASE-FUNC-001 | `FunctionalInterfacesLabTest` | `fillable` Predicate 只接受 PENDING |
| CASE-FUNC-002 | `FunctionalInterfacesLabTest` | `selectFillable` 無符合 → 空列表 |
| CASE-FUNC-003 | `FunctionalInterfacesLabTest` | `notional` Function = qty×price |
| CASE-FUNC-004 | `FunctionalInterfacesLabTest` | `mapNotionals` 長度與數值 |
| CASE-FUNC-005 | `FunctionalInterfacesLabTest` | Consumer 收集到外部 List |
| CASE-STREAM-001 | `OrderStreamLabTest` | `pendingBuys` = BUY AND PENDING |
| CASE-STREAM-002 | `OrderStreamLabTest` | 空輸入 → 空 |
| CASE-STREAM-003 | `OrderStreamLabTest` | `notionals` map 名目金額 |
| CASE-STREAM-004 | `OrderStreamLabTest` | `totalNotional` reduce 加總 |
| CASE-STREAM-005 | `OrderStreamLabTest` | 空列表總和為 0 |
| CASE-STREAM-006 | `OrderStreamLabTest` | `groupBySymbol` groupingBy |

成對規則：**不成對**。勿為通過掃描器新增假 Spring／MockMvc 測試。

## DoD

- [x] `.\scripts\check.ps1` 全綠（僅 unit）
- [x] 每個 Lab ≥ 1 Happy Path + 1 邊界
- [x] 15 Case 已標 **unit-layer-only**（本檔）
- [x] `practice/` 不納入 check
- [x] 無 `bootRun`／`Application.java`

## Key classes

`LambdaBasics`、`FunctionalInterfacesLab`、`OrderStreamLab`、`Order`
