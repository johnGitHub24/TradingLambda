package com.trading.lambda.lab.lambda;

import com.trading.lambda.domain.Order;
import com.trading.lambda.domain.OrderStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 【職責】Lambda 語法入門 Lab：過濾、排序、格式化字串。
 * 【技巧】Lambda 運算式、{@link Comparator#comparing}、方法參考 {@code Order::price}。
 * 【概念】Lambda =「可傳遞的一小段行為」。以前要寫匿名內部類別才能把行為傳進方法；
 *         Java 8 起可用 {@code (參數) -> 表達式}。本類刻意先用 forEach + Lambda，
 *         下一堂 {@code OrderStreamLab} 再換成 Stream 管線。
 * 【邊界】工具類，不可實例化；不修改傳入的原始 List（排序會先複製）。
 */
public final class LambdaBasics {

    private LambdaBasics() {
    }

    /**
     * 【職責】只保留指定 {@link OrderStatus} 的訂單。
     * 【技巧】{@code List.forEach(order -> { ... })}：對每個元素執行 Lambda。
     * 【概念】這等同傳統 for-each + if。差別是「行為」寫在箭頭右邊，之後可抽成變數或方法參數。
     *         enum 比對用 {@code ==} 即可（同一份常數實例）。
     * 【邊界】空列表 → 空列表；不修改 {@code orders} 本身。
     *
     * @param orders 來源訂單
     * @param status 要保留的狀態
     * @return 新的結果列表（永不為 null）
     */
    public static List<Order> filterByStatus(List<Order> orders, OrderStatus status) {
        List<Order> result = new ArrayList<>();
        orders.forEach(order -> {
            if (order.status() == status) {
                result.add(order);
            }
        });
        return result;
    }

    /**
     * 【職責】依單價由低到高排序，回傳新列表。
     * 【技巧】{@link Comparator#comparing}{@code (Order::price)}——方法參考取出排序鍵。
     * 【概念】{@code Order::price} 等於 {@code order -> order.price()}，但更短、意圖更清楚：
     *         「用 price 當比較依據」。先 {@code new ArrayList<>(orders)} 再 sort，避免改到呼叫端的 List。
     *
     * @param orders 來源訂單
     * @return 依 price 升冪的新列表
     */
    public static List<Order> sortByPriceAsc(List<Order> orders) {
        List<Order> copy = new ArrayList<>(orders);
        copy.sort(Comparator.comparing(Order::price));
        return copy;
    }

    /**
     * 【職責】把訂單格式化成可讀字串：{@code SYMBOL side qty@price}。
     * 【技巧】字串串接；此方法本身常被當成 {@code Function<Order,String>} 使用。
     * 【概念】格式化是「資料 → 字串」的映射。學會後可用 {@code orders.stream().map(LambdaBasics::formatOrder)}。
     *
     * @param order 單一訂單
     * @return 例如 {@code AAPL BUY 10@100.00}
     */
    public static String formatOrder(Order order) {
        return order.symbol() + " " + order.side() + " " + order.quantity() + "@" + order.price();
    }
}
