package introduction.fpij;
/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class DiscountImperative {

  static final List<BigDecimal> prices = Arrays.asList(
          new BigDecimal("10"), new BigDecimal("30"),
          new BigDecimal("17"), new BigDecimal("20"),
          new BigDecimal("15"), new BigDecimal("18"),
          new BigDecimal("45"), new BigDecimal("12"));

  public static void main(final String[] args) {

//    BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
//
//    for (BigDecimal price : prices) {
//      if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
//        totalOfDiscountedPrices =
//                totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
//      }
//    }


    final BigDecimal totalOfDiscountedPrices = prices.stream()
            .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
            .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
            .reduce(BigDecimal.ZERO, BigDecimal::add);


        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
  }
}