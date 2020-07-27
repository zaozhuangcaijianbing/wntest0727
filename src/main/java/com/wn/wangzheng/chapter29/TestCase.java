package com.wn.wangzheng.chapter29;

public class TestCase {

    public void testExecute() {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;


        TransactionLock mockLock = new TransactionLock() {
            public boolean lock(String id) {
                return true;
            }

            public void unlock() {
            }
        };

        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId);
        // 使用mock对象来替代真正的RPC服务
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        transaction.setTransactionLock(mockLock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }



    public void testExecute_with_TransactionIsExpired() {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId) {
            @Override
            protected boolean isExpired() {
                return true;
            }
        };
        boolean actualResult = transaction.execute();
        assertFalse(actualResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }
}
