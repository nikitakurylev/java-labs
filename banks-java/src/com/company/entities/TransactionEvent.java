package com.company.entities;

import java.util.ArrayList;

public class TransactionEvent{
    ArrayList<TransactionListener> listeners = new ArrayList<TransactionListener>();

    void addListener(TransactionListener listener){
        listeners.add(listener);
    }

    void removeListener(TransactionListener listener){
        listeners.remove(listener);
    }

    public void invoke(Transaction transaction) {
        for (var listener:listeners)
            listener.onTransaction(transaction);
    }
}
