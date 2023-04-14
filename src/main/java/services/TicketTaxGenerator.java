package services;

import interceptors.MyInterceptor;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.math.BigDecimal;

@ApplicationScoped
public class TicketTaxGenerator implements Serializable, TicketTaxInterface {
    @Override
    @MyInterceptor
    public Double calculateTax(Double price) {
        price = price * 1.1;
        BigDecimal bd = BigDecimal.valueOf(price);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
