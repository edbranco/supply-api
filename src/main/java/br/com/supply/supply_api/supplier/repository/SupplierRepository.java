package br.com.supply.supply_api.supplier.repository;

import br.com.supply.supply_api.supplier.entity.SupplierJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierJpaEntity, Long> {

    boolean existsByCnpj(String cnpj);

    @Query("""
            select distinct supplier
            from SupplierJpaEntity supplier
            join supplier.subcategories subcategory
            join subcategory.category category
            where lower(category.nome) in :categorias
            order by supplier.razaoSocial asc
            """)
    List<SupplierJpaEntity> findByCategoryNames(@Param("categorias") Collection<String> categorias);

    @Query("""
            select supplier
            from SupplierJpaEntity supplier
            where cast(function('unaccent', lower(supplier.razaoSocial)) as string) like concat('%', :nome, '%')
               or cast(function('unaccent', lower(supplier.nomeFantasia)) as string) like concat('%', :nome, '%')
            order by supplier.razaoSocial asc
            """)
    List<SupplierJpaEntity> findByCompanyName(@Param("nome") String nome);

    @Query("""
            select distinct supplier
            from SupplierJpaEntity supplier
            join supplier.subcategories subcategory
            join subcategory.category category
            where lower(category.nome) in :categorias
              and (
                    cast(function('unaccent', lower(supplier.razaoSocial)) as string) like concat('%', :nome, '%')
                    or cast(function('unaccent', lower(supplier.nomeFantasia)) as string) like concat('%', :nome, '%')
              )
            order by supplier.razaoSocial asc
            """)
    List<SupplierJpaEntity> findByCategoryNamesAndCompanyName(
            @Param("categorias") Collection<String> categorias,
            @Param("nome") String nome
    );

    @Query("""
            select distinct supplier
            from SupplierJpaEntity supplier
            join supplier.subcategories subcategory
            where cast(function('unaccent', lower(subcategory.nome)) as string) like concat('%', :termo, '%')
            order by supplier.razaoSocial asc
            """)
    List<SupplierJpaEntity> findBySubcategoryName(@Param("termo") String termo);

    @Query("""
            select distinct supplier
            from SupplierJpaEntity supplier
            join supplier.subcategories subcategory
            join subcategory.category category
            where lower(category.nome) in :categorias
              and cast(function('unaccent', lower(subcategory.nome)) as string) like concat('%', :termo, '%')
            order by supplier.razaoSocial asc
            """)
    List<SupplierJpaEntity> findByCategoryNamesAndSubcategoryName(
            @Param("categorias") Collection<String> categorias,
            @Param("termo") String termo
    );

    List<SupplierJpaEntity> findAllByOrderByRazaoSocialAsc();
}
