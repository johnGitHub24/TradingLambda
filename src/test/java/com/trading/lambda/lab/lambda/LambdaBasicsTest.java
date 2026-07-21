package com.trading.lambda.lab.lambda;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderSide;
import com.trading.lambda.domain.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 【職責】驗證 {@link LambdaBasics} 的過濾／排序／格式化。
 * 【技巧】JUnit 5 {@code @Test}；用固定 fixture 訂單當 Given。
 * 【概念】教學測試同時當「規格說明」：讀測試名稱就知道 Lab 保證什麼行為。
 */
class LambdaBasicsTest {

    private final Order buyPending = new Order("1", "AAPL", OrderSide.BUY, 10, new BigDecimal("100.00"), OrderStatus.PENDING);
    private final Order sellFilled = new Order("2", "TSLA", OrderSide.SELL, 5, new BigDecimal("200.00"), OrderStatus.FILLED);
    private final Order buyCheap = new Order("3", "MSFT", OrderSide.BUY, 2, new BigDecimal("50.00"), OrderStatus.PENDING);

    /**
     * CASE-LAMBDA-001：依 PENDING 過濾，只留兩筆。
     * Given: 三筆不同狀態；When: filterByStatus(PENDING)；Then: id 1 與 3。
     * 【技巧驗證】forEach + Lambda 條件。
     */
    @Test
    void filterByStatus_keepsMatchingOrders() {
        List<Order> result = LambdaBasics.filterByStatus(
                List.of(buyPending, sellFilled, buyCheap), OrderStatus.PENDING);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).id());
        assertEquals("3", result.get(1).id());
    }

    /**
     * CASE-LAMBDA-002：空列表邊界。
     * Given: 空；When: filter；Then: 空（不拋例外）。
     */
    @Test
    void filterByStatus_emptyInput_returnsEmpty() {
        assertTrue(LambdaBasics.filterByStatus(List.of(), OrderStatus.PENDING).isEmpty());
    }

    /**
     * CASE-LAMBDA-003：依 price 升冪排序。
     * 【技巧驗證】Comparator.comparing(Order::price)。
     */
    @Test
    void sortByPriceAsc_ordersByPrice() {
        List<Order> result = LambdaBasics.sortByPriceAsc(List.of(sellFilled, buyCheap, buyPending));

        assertEquals(List.of("3", "1", "2"), result.stream().map(Order::id).toList());
    }

    /**
     * CASE-LAMBDA-004：格式化字串契約。
     */
    @Test
    void formatOrder_rendersSymbolSideQtyPrice() {
        assertEquals("AAPL BUY 10@100.00", LambdaBasics.formatOrder(buyPending));
    }
}
