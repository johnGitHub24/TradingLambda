package com.trading.lambda.lab.stream;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderSide;
import com.trading.lambda.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 【職責】Stream API Lab：用管線表達 filter／map／彙總／分組。
 * 【技巧】{@link java.util.stream.Stream}、{@code filter}、{@code map}、{@code reduce}、
 *         {@link Collectors#groupingBy}、{@code toList()}。
 * 【概念】Stream 是「資料的流水線」：來源 → 零到多個中間操作 → 一個終端操作才真正執行（惰性）。
 *         中間操作回傳新 Stream，終端操作（toList／collect／reduce）才產出結果。
 *         比起手動 forEach 累加，管線通常更短、意圖（過濾／映射／分組）更一眼可讀。
 */
public final class OrderStreamLab {

    private OrderStreamLab() {
    }

    /**
     * 【職責】篩出「買進且待成交」的訂單。
     * 【技巧】連續兩個 {@code filter}；條件為 Lambda。終端 {@code toList()}（Java 16+ 不可變 List）。
     * 【概念】多個 filter 等價於條件 AND。也可寫成一個 filter 內 {@code &&}，拆開通常較好讀。
     * 【邊界】空輸入或無符合 → 空列表。
     *
     * @param orders 來源
     * @return BUY + PENDING 的列表
     */
    public static List<Order> pendingBuys(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.side() == OrderSide.BUY)
                .filter(o -> o.status() == OrderStatus.PENDING)
                .toList();
    }

    /**
     * 【職責】把每筆訂單轉成名目金額。
     * 【技巧】{@code map(Order::notional)} 方法參考。
     * 【概念】map 一一對應；輸入 N 筆通常輸出 N 個金額。
     *
     * @param orders 來源
     * @return 名目金額列表
     */
    public static List<BigDecimal> notionals(List<Order> orders) {
        return orders.stream()
                .map(Order::notional)
                .toList();
    }

    /**
     * 【職責】加總所有訂單的名目金額。
     * 【技巧】{@code map} 後 {@code reduce(單位元, 累加函式)}；{@code BigDecimal::add} 方法參考。
     * 【概念】reduce＝摺疊：從初始值（這裡是 {@link BigDecimal#ZERO}）開始，逐個把元素「併入」結果。
     *         空 Stream 時直接得到初始值 0，不會 NPE。
     *
     * @param orders 來源
     * @return 總名目金額；空列表為 0
     */
    public static BigDecimal totalNotional(List<Order> orders) {
        return orders.stream()
                .map(Order::notional)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 【職責】依商品代碼分組。
     * 【技巧】{@link Collectors#groupingBy}{@code (Order::symbol)}。
     * 【概念】分組結果是 {@code Map<鍵, List<元素>>}。報表「每個標的有哪些單」常用這招。
     *
     * @param orders 來源
     * @return symbol → 該 symbol 的訂單列表
     */
    public static Map<String, List<Order>> groupBySymbol(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::symbol));
    }
}
