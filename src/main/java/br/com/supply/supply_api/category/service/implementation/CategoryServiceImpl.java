package br.com.supply.supply_api.category.service.implementation;

import br.com.supply.supply_api.buyer.repository.SubcategoryRepository;
import br.com.supply.supply_api.buyer.repository.entity.CategoryJpaEntity;
import br.com.supply.supply_api.buyer.repository.entity.SubcategoryJpaEntity;
import br.com.supply.supply_api.category.dto.request.CreateCategoryRequest;
import br.com.supply.supply_api.category.dto.response.CategoryResponse;
import br.com.supply.supply_api.category.dto.response.SubcategoryResponse;
import br.com.supply.supply_api.category.exception.CategoryAlreadyExistsException;
import br.com.supply.supply_api.category.repository.CategoryRepository;
import br.com.supply.supply_api.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    @Transactional
    public List<CategoryResponse> create(List<CreateCategoryRequest> requests) {
        List<CategoryJpaEntity> categories = requests.stream().map(request -> {
            String nome = request.nome().trim();
            if (categoryRepository.existsByNomeIgnoreCase(nome)) {
                throw new CategoryAlreadyExistsException(nome);
            }
            CategoryJpaEntity category = new CategoryJpaEntity();
            category.setNome(nome);
            category.setSlug(request.slug().trim());
            category.setDescription(request.description().trim());
            category.setIconKey(request.iconKey() == null ? null : request.iconKey().trim());
            return category;
        }).toList();
        return categoryRepository.saveAll(categories).stream()
                .map(category -> toResponse(category, List.of()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll(String nome, int limite) {
        PageRequest pageRequest = PageRequest.of(0, limite, Sort.by("nome").ascending());
        List<CategoryJpaEntity> categories = nome == null || nome.isBlank()
                ? categoryRepository.findAll(pageRequest).getContent()
                : categoryRepository.findByNomeContainingIgnoreCase(nome.trim(), pageRequest);
        Map<Long, List<SubcategoryResponse>> subcategoriesByCategoryId = categories.isEmpty()
                ? Collections.emptyMap()
                : subcategoryRepository.findByCategoryIdInOrderByCategoryIdAscNomeAsc(
                        categories.stream().map(CategoryJpaEntity::getId).toList()
                ).stream().collect(Collectors.groupingBy(
                        subcategory -> subcategory.getCategory().getId(),
                        Collectors.mapping(this::toSubcategoryResponse, Collectors.toList())
                ));

        return categories.stream()
                .map(category -> toResponse(category, subcategoriesByCategoryId.getOrDefault(category.getId(), List.of())))
                .toList();
    }

    private CategoryResponse toResponse(CategoryJpaEntity category, List<SubcategoryResponse> subcategories) {
        return new CategoryResponse(
                category.getId(), category.getNome(), category.getSlug(), category.getDescription(), category.getIconKey(),
                category.getCreatedAt(), category.getUpdatedAt(), subcategories
        );
    }

    private SubcategoryResponse toSubcategoryResponse(SubcategoryJpaEntity subcategory) {
        return new SubcategoryResponse(subcategory.getId(), subcategory.getNome(), subcategory.getSlug(),
                subcategory.getCreatedAt(), subcategory.getUpdatedAt());
    }
}
