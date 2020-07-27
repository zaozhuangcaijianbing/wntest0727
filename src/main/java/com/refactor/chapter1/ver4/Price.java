package com.refactor.chapter1.ver4;

public abstract class Price {
	abstract int getPriceCode(); // 取得价格代号

	abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}
