package com.trading.lambda.practice;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 【職責】練習空殼：請自行實作，簽名與 Lab 對齊；不納入 {@code gradlew check}。
 * 【技巧】對照 {@code src/main/.../lab} 參考實作；建議先寫測試或手動 main 驗證。
 * 【概念】學習路徑：先讀 Lab 註解的【概念】→ 在本類重寫 → 再對照答案。
 *         丟 {@link UnsupportedOperationException} 是刻意的「還沒做」訊號。
 */
public final class PracticeLabs {

    private PracticeLabs() {
    }

    /**
     * 【職責】依 status 過濾。
     * 【技巧提示】forEach + Lambda，或 {@code stream().filter(...).toList()}。
     * 【概念】條件是「行為」，可以是 Lambda 也可以是 Predicate 變數。
     */
    public static List<Order> filterByStatus(List<Order> orders, OrderStatus status) {
        throw new UnsupportedOperationException("TODO: Lambda 過濾");
    }

    /**
     * 【職責】依 price 升冪排序（勿改原 List）。
     * 【技巧提示】{@code Comparator.comparing(Order::price)}。
     */
    public static List<Order> sortByPriceAsc(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: Lambda／Comparator 排序");
    }

    /**
     * 【職責】格式化 {@code SYMBOL side qty@price}。
     * 【技巧提示】字串串接；可當 Function 使用。
     */
    public static String formatOrder(Order order) {
        throw new UnsupportedOperationException("TODO: 格式化 SYMBOL side qty@price");
    }

    /**
     * 【職責】PENDING 才可成交的 Predicate。
     * 【技巧提示】{@code return order -> order.status() == OrderStatus.PENDING;}
     */
    public static Predicate<Order> fillable() {
        throw new UnsupportedOperationException("TODO: Predicate");
    }

    /**
     * 【職責】訂單 → 名目金額的 Function。
     * 【技巧提示】{@code Order::notional}。
     */
    public static Function<Order, BigDecimal> notional() {
        throw new UnsupportedOperationException("TODO: Function");
    }

    /**
     * 【職責】把訂單加入 collector 的 Consumer。
     * 【技巧提示】{@code collector::add}。
     */
    public static Consumer<Order> collectInto(List<Order> collector) {
        throw new UnsupportedOperationException("TODO: Consumer");
    }

    /**
     * 【職責】BUY + PENDING。
     * 【技巧提示】雙 filter 或單一 filter 含 {@code &&}。
     */
    public static List<Order> pendingBuys(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: Stream filter");
    }

    /**
     * 【職責】map 出名目金額列表。
     * 【技巧提示】{@code map(Order::notional).toList()}。
     */
    public static List<BigDecimal> notionals(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: Stream map");
    }

    /**
     * 【職責】加總名目金額。
     * 【技巧提示】{@code reduce(BigDecimal.ZERO, BigDecimal::add)}。
     */
    public static BigDecimal totalNotional(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: Stream reduce／sum");
    }

    /**
     * 【職責】依 symbol 分組。
     * 【技巧提示】{@code Collectors.groupingBy(Order::symbol)}。
     */
    public static Map<String, List<Order>> groupBySymbol(List<Order> orders) {
        throw new UnsupportedOperationException("TODO: Collectors.groupingBy");
    }
}
