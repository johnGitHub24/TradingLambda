package com.trading.lambda.lab.functional;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderSide;
import com.trading.lambda.domain.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 【職責】驗證 Predicate／Function／Consumer 行為與組合使用。
 * 【技巧】{@code Predicate.test}、{@code Function.apply}、Consumer 副作用收集。
 * 【概念】先測「介面物件本身」，再測「用它們組出來的方法」，對應學習順序。
 */
class FunctionalInterfacesLabTest {

    private final Order pending = new Order("1", "AAPL", OrderSide.BUY, 10, new BigDecimal("100"), OrderStatus.PENDING);
    private final Order filled = new Order("2", "AAPL", OrderSide.SELL, 5, new BigDecimal("110"), OrderStatus.FILLED);

    /**
     * CASE-FUNC-001：fillable 只接受 PENDING。
     * 【技巧驗證】Predicate Lambda。
     */
    @Test
    void fillable_acceptsPendingOnly() {
        assertTrue(FunctionalInterfacesLab.fillable().test(pending));
        assertFalse(FunctionalInterfacesLab.fillable().test(filled));
    }

    /**
     * CASE-FUNC-002：無符合條件時回空列表。
     */
    @Test
    void selectFillable_emptyWhenNoneMatch() {
        assertTrue(FunctionalInterfacesLab.selectFillable(List.of(filled)).isEmpty());
    }

    /**
     * CASE-FUNC-003：notional Function 計算 qty×price。
     * 【技巧驗證】方法參考 Order::notional。
     */
    @Test
    void notional_multipliesQtyAndPrice() {
        assertEquals(0, new BigDecimal("1000").compareTo(FunctionalInterfacesLab.notional().apply(pending)));
    }

    /**
     * CASE-FUNC-004：mapNotionals 長度與數值。
     */
    @Test
    void mapNotionals_mapsAllOrders() {
        List<BigDecimal> notionals = FunctionalInterfacesLab.mapNotionals(List.of(pending, filled));
        assertEquals(2, notionals.size());
        assertEquals(0, new BigDecimal("1000").compareTo(notionals.get(0)));
        assertEquals(0, new BigDecimal("550").compareTo(notionals.get(1)));
    }

    /**
     * CASE-FUNC-005：Consumer 把元素加入外部 List。
     * 【技巧驗證】collector::add 副作用。
     */
    @Test
    void collectInto_appendsViaConsumer() {
        List<Order> bucket = new ArrayList<>();
        FunctionalInterfacesLab.acceptAll(List.of(pending, filled), FunctionalInterfacesLab.collectInto(bucket));
        assertEquals(2, bucket.size());
    }
}
