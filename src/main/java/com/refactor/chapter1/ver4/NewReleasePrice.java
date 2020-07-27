package com.refactor.chapter1.ver4;

public class NewReleasePrice extends Price {
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

	public double getCharge(int daysRented) {
		return daysRented * 3;
	}

	public int getFrequentRenterPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1;
	}
}