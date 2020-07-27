package com.wn.wangzheng.chapter29;

public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private Long orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;

    private WalletRpcService walletRpcService;

    private TransactionLock lock;

    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    public void setTransactionLock(TransactionLock lock) {
        this.lock = lock;
    }


    // ...get() methods...

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Long orderId) {
        fillTransactionId(preAssignId);
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimestamp();
    }

    protected void fillTransactionId(String preAssignedId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }
        if (!this.id.startWith("t_")) {
            this.id = "t_" + preAssignedId;
        } }


    protected boolean isExpired() {
        long executionInvokedTimestamp = System.currentTimeMillis();
        return executionInvokedTimestamp - createdTimestamp > 14d ays;
    }

    public boolean execute() throws InvalidTransactionException {
        if (buyerId == null || (sellerId == null || amount < 0.0)) {
            throw new InvalidTransactionException(...);
        }
        if (status == STATUS.EXECUTED)
            return true;
        boolean isLocked = false;
        try {
            isLocked = lock.lock("");
            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
            if (status == STATUS.EXECUTED)
                return true; // double check
            long executionInvokedTimestamp = System.currentTimeMillis();
            if (isExpired()){
                this.status = STATUS.EXPIRED;
                return false;
            }

            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);

            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
