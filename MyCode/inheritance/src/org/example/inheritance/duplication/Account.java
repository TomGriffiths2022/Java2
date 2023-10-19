package org.example.inheritance.duplication;

import java.math.BigDecimal;

// One of the options deployed to avoid duplication is to use a differentiator field.
// This is common BUT bad practice
// You can spot this code smell by the presence of a field named type

// Should this class be abstract?
//  - Do you want users of this class to instantiate it?
//  - Do Account objects exist in the real world?
// If the answer to either of the above questions is NO, then the class should be abstract.
public abstract class Account {

    private int number;
    private String name;
    private BigDecimal balance;
//    private String type;   code smell

    // Is there a default implementation that can be inherited by all subclasses, or should each subclass be forced
    // to provide its own implementation?
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

// Conditional logic like this is a code smell
// New types will require changes to existing code
//    public void withdraw(BigDecimal amount) {
//        if (type.equals("savings")) {
//            // TODO
//        }
//    }
}
