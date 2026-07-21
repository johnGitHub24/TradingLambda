package com.trading.lambda.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 【職責】一筆精簡交易訂單（教學用領域模型）。
 * 【技巧】不可變物件（immutable）：欄位皆 {@code final}，建構後不能改；用方法參考時較安全。
 * 【概念】真實系統訂單欄位更多（帳戶、時間、部分成交…）。這裡只留練習 Lambda／Stream 需要的欄位，
 *         避免初學者被業務細節淹沒。金額用 {@link BigDecimal} 而非 {@code double}，避免浮點誤差。
 * 【邊界】不負責持久化、風控、撮合；僅記憶體內資料載體。
 */
public final class Order {

    private final String id;
    private final String symbol;
    private final OrderSide side;
    private final int quantity;
    private final BigDecimal price;
    private final OrderStatus status;

    /**
     * 【職責】建立一筆訂單；必要欄位不可為 null。
     * 【技巧】{@link Objects#requireNonNull} 在建構當下失敗，避免之後 NPE 難追。
     * 【概念】「盡早失敗」：壞資料一進來就擋，不要讓半殘物件流進 Stream 管道。
     *
     * @param id       訂單識別
     * @param symbol   商品代碼，如 AAPL
     * @param side     買／賣
     * @param quantity 數量（整數，教學簡化）
     * @param price    單價
     * @param status   目前狀態
     */
    public Order(String id, String symbol, OrderSide side, int quantity, BigDecimal price, OrderStatus status) {
        this.id = Objects.requireNonNull(id, "id");
        this.symbol = Objects.requireNonNull(symbol, "symbol");
        this.side = Objects.requireNonNull(side, "side");
        this.quantity = quantity;
        this.price = Objects.requireNonNull(price, "price");
        this.status = Objects.requireNonNull(status, "status");
    }

    /** @return 訂單 id */
    public String id() {
        return id;
    }

    /** @return 商品代碼 */
    public String symbol() {
        return symbol;
    }

    /** @return 買／賣方向 */
    public OrderSide side() {
        return side;
    }

    /** @return 數量 */
    public int quantity() {
        return quantity;
    }

    /** @return 單價 */
    public BigDecimal price() {
        return price;
    }

    /** @return 狀態 */
    public OrderStatus status() {
        return status;
    }

    /**
     * 【職責】計算名目金額 = 單價 × 數量。
     * 【技巧】{@link BigDecimal#multiply}；數量先轉成 BigDecimal。
     * 【概念】名目（notional）常出現在風控「這筆單曝險多大」。之後可用方法參考 {@code Order::notional}
     *         當成 {@code Function}，不必每次手寫乘法。
     *
     * @return 名目金額（不為 null）
     */
    public BigDecimal notional() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
