package com.trading.lambda.domain;

/**
 * 【職責】買賣方向：買進或賣出。
 * 【技巧】Java {@code enum}（列舉）——一組固定、具名的常數。
 * 【概念】初學者常把方向寫成字串 {@code "BUY"}，容易打錯且編譯器無法檢查。
 *         用 enum 後，只能是 {@link #BUY} 或 {@link #SELL}，IDE 也會自動補全。
 */
public enum OrderSide {
    /** 買進。 */
    BUY,
    /** 賣出。 */
    SELL
}
