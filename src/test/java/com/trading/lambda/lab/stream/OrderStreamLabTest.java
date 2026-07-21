package com.trading.lambda.lab.stream;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderSide;
import com.trading.lambda.domain.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 【職責】驗證 Stream filter／map／reduce／groupingBy。
 * 【技巧】斷言 List／Map／BigDecimal；空輸入邊界。
 * 【概念】每個測試對應管線上的一個「終端結果」，方便對照 Lab 原始碼閱讀。
 */
class OrderStreamLabTest {

    private final Order buyPending = new Order("1", "AAPL", OrderSide.BUY, 10, new BigDecimal("100"), OrderStatus.PENDING);
    private final Order sellPending = new Order("2", "AAPL", OrderSide.SELL, 5, new BigDecimal("110"), OrderStatus.PENDING);
    private final Order buyFilled = new Order("3", "TSLA", OrderSide.BUY, 2, new BigDecimal("200"), OrderStatus.FILLED);
    private final Order buyPendingTsla = new Order("4", "TSLA", OrderSide.BUY, 1, new BigDecimal("210"), OrderStatus.PENDING);

    /**
     * CASE-STREAM-001：pendingBuys = BUY AND PENDING。
     * 【技巧驗證】雙 filter。
     */
    @Test
    void pendingBuys_filtersBuyAndPending() {
        List<Order> result = OrderStreamLab.pendingBuys(
                List.of(buyPending, sellPending, buyFilled, buyPendingTsla));

        assertEquals(List.of("1", "4"), result.stream().map(Order::id).toList());
    }

    /**
     * CASE-STREAM-002：空輸入。
     */
    @Test
    void pendingBuys_emptyInput_returnsEmpty() {
        assertTrue(OrderStreamLab.pendingBuys(List.of()).isEmpty());
    }

    /**
     * CASE-STREAM-003：map 名目金額。
     */
    @Test
    void notionals_mapsEachOrder() {
        List<BigDecimal> result = OrderStreamLab.notionals(List.of(buyPending, sellPending));
        assertEquals(0, new BigDecimal("1000").compareTo(result.get(0)));
        assertEquals(0, new BigDecimal("550").compareTo(result.get(1)));
    }

    /**
     * CASE-STREAM-004：reduce 加總。
     * 【技巧驗證】BigDecimal::add。
     */
    @Test
    void totalNotional_sumsAll() {
        BigDecimal total = OrderStreamLab.totalNotional(List.of(buyPending, sellPending));
        assertEquals(0, new BigDecimal("1550").compareTo(total));
    }

    /**
     * CASE-STREAM-005：空列表總和為 0。
     */
    @Test
    void totalNotional_empty_isZero() {
        assertEquals(0, BigDecimal.ZERO.compareTo(OrderStreamLab.totalNotional(List.of())));
    }

    /**
     * CASE-STREAM-006：依 symbol 分組。
     * 【技巧驗證】Collectors.groupingBy。
     */
    @Test
    void groupBySymbol_groupsOrders() {
        Map<String, List<Order>> grouped = OrderStreamLab.groupBySymbol(
                List.of(buyPending, sellPending, buyFilled));

        assertEquals(2, grouped.get("AAPL").size());
        assertEquals(1, grouped.get("TSLA").size());
    }
}
