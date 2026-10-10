package br.com.supply.supply_api.supplier.exception;

public class CnpjAlreadyExistsException extends RuntimeException {

    public CnpjAlreadyExistsException(String cnpj) {
        super("Já existe um fornecedor cadastrado com o CNPJ: " + cnpj);
    }
}
