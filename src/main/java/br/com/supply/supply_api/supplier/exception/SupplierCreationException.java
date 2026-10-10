package br.com.supply.supply_api.supplier.exception;

public class SupplierCreationException extends RuntimeException {

    public SupplierCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplierCreationException(String message) {
        super(message);
    }
}
