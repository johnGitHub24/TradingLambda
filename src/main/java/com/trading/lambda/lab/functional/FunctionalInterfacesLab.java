package com.trading.lambda.lab.functional;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 【職責】函數式介面 Lab：把「判斷／轉換／副作用」變成可傳遞的物件。
 * 【技巧】{@link Predicate}、{@link Function}、{@link Consumer}；Lambda 與方法參考。
 * 【概念】Java 把常見「單抽象方法」介面叫函數式介面。你不必每次發明新介面：
 *         <ul>
 *           <li>Predicate&lt;T&gt;：T → boolean（過濾條件）</li>
 *           <li>Function&lt;T,R&gt;：T → R（轉換）</li>
 *           <li>Consumer&lt;T&gt;：T → void（吃進去做某事，如加入集合、印 log）</li>
 *         </ul>
 *         Stream 的 filter／map／forEach 其實就是在吃這三種行為。
 */
public final class FunctionalInterfacesLab {

    private FunctionalInterfacesLab() {
    }

    /**
     * 【職責】回傳「是否可成交」的判斷條件：狀態為 PENDING。
     * 【技巧】回傳 {@code Predicate<Order>}；本體是 Lambda {@code order -> ...}。
     * 【概念】條件本身可以當值傳遞——先定義好，稍後在多處 {@code test()}，或交給 Stream.filter。
     *
     * @return 可重複使用的 Predicate
     */
    public static Predicate<Order> fillable() {
        return order -> order.status() == OrderStatus.PENDING;
    }

    /**
     * 【職責】回傳「訂單 → 名目金額」的轉換函式。
     * 【技巧】方法參考 {@code Order::notional}，等價於 {@code o -> o.notional()}。
     * 【概念】Function 描述「怎麼從 A 得到 B」，不立刻執行；呼叫 {@code apply(order)} 才計算。
     *
     * @return 名目金額 Function
     */
    public static Function<Order, BigDecimal> notional() {
        return Order::notional;
    }

    /**
     * 【職責】回傳「把訂單加入指定 List」的 Consumer。
     * 【技巧】{@code collector::add} 實例方法參考（綁定到某個 List 實例）。
     * 【概念】Consumer 有副作用（改集合）。教學上要意識到：函數式風格仍可能碰到副作用，
     *         應集中、明確，不要散落在管線各處。
     *
     * @param collector 要被加入元素的列表（會被修改）
     * @return Consumer
     */
    public static Consumer<Order> collectInto(List<Order> collector) {
        return collector::add;
    }

    /**
     * 【職責】用 {@link #fillable()} 篩出可成交訂單。
     * 【技巧】先取得 Predicate，再 {@code predicate.test(order)}。
     * 【概念】展示「條件物件」如何在一般迴圈裡使用；對照 Stream 版會更短。
     *
     * @param orders 來源
     * @return 僅 PENDING 的新列表；無符合則空
     */
    public static List<Order> selectFillable(List<Order> orders) {
        Predicate<Order> predicate = fillable();
        List<Order> result = new ArrayList<>();
        orders.forEach(order -> {
            if (predicate.test(order)) {
                result.add(order);
            }
        });
        return result;
    }

    /**
     * 【職責】把每筆訂單映射成名目金額列表。
     * 【技巧】{@code Function.apply}；forEach 收集結果。
     * 【概念】map 的本質：每個輸入對應一個輸出，長度通常相同。
     *
     * @param orders 來源
     * @return 與輸入等長的金額列表
     */
    public static List<BigDecimal> mapNotionals(List<Order> orders) {
        Function<Order, BigDecimal> mapper = notional();
        List<BigDecimal> result = new ArrayList<>();
        orders.forEach(order -> result.add(mapper.apply(order)));
        return result;
    }

    /**
     * 【職責】對每個訂單執行同一個 Consumer（例如收集或印出）。
     * 【技巧】{@code List.forEach(Consumer)}——forEach 的參數型別就是 Consumer。
     * 【概念】當你寫 {@code list.forEach(x -> ...)}，編譯器把 Lambda 當成 Consumer。
     *
     * @param orders   來源
     * @param consumer 對每個元素要做的事
     */
    public static void acceptAll(List<Order> orders, Consumer<Order> consumer) {
        orders.forEach(consumer);
    }
}
