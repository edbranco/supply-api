package br.com.supply.supply_api.category.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String nome) { super("A categoria '" + nome + "' já está cadastrada."); }
}
