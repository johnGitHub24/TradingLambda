package com.trading.lambda.domain;

/**
 * 【職責】訂單生命週期狀態。
 * 【技巧】{@code enum} 表達「有限狀態」。
 * 【概念】交易系統裡狀態通常會轉移（例如 PENDING → FILLED）。
 *         本教學專案不實作狀態機，只把狀態當過濾條件，方便練習 Lambda／Stream。
 */
public enum OrderStatus {
    /** 待成交（尚未完全成交）。 */
    PENDING,
    /** 已成交。 */
    FILLED,
    /** 已取消。 */
    CANCELLED
}
