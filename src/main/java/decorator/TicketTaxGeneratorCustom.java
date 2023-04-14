package decorator;

import interceptors.MyInterceptor;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import services.TicketTaxInterface;

@Decorator
public abstract class TicketTaxGeneratorCustom implements TicketTaxInterface {
    @Inject
    @Delegate
    @Any
    TicketTaxInterface ticketTaxInterface;

    public Double calculateTax(Double price) {
        Double withTax = ticketTaxInterface.calculateTax(price);
        if(true){
            withTax += 1.23;
        }
        return withTax;
    }
}
