package br.com.supply.supply_api.supplier.service.implementation;

import br.com.supply.supply_api.common.entity.UserJpaEntity;
import br.com.supply.supply_api.common.entity.UserType;
import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.dto.response.CreateSupplierResponse;
import br.com.supply.supply_api.supplier.dto.response.SupplierResponse;
import br.com.supply.supply_api.supplier.entity.SubscriptionJpaEntity;
import br.com.supply.supply_api.supplier.entity.SupplierJpaEntity;
import br.com.supply.supply_api.supplier.exception.CnpjAlreadyExistsException;
import br.com.supply.supply_api.supplier.exception.EmailAlreadyExistsException;
import br.com.supply.supply_api.supplier.exception.SupplierCreationException;
import br.com.supply.supply_api.supplier.exception.SupplierSearchException;
import br.com.supply.supply_api.supplier.mapper.SupplierMapper;
import br.com.supply.supply_api.supplier.model.SupplierModel;
import br.com.supply.supply_api.supplier.repository.SubscriptionRepository;
import br.com.supply.supply_api.supplier.repository.SupplierRepository;
import br.com.supply.supply_api.common.repository.UserRepository;
import br.com.supply.supply_api.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private static final String SUCCESS_MESSAGE = "Fornecedor cadastrado com sucesso.";

    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SupplierMapper supplierMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CreateSupplierResponse createSupplier(CreateSupplierRequest request) {
        validateUniqueness(request);

        try {
            UserJpaEntity user = createUser(request);
            SupplierModel supplierModel = buildSupplierModel(request, user.getId());
            createSupplierEntity(supplierModel);
            createSubscription(supplierModel);

            return new CreateSupplierResponse(user.getId(), SUCCESS_MESSAGE);
        } catch (EmailAlreadyExistsException | CnpjAlreadyExistsException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Erro ao cadastrar fornecedor.", ex);
            throw new SupplierCreationException("Falha ao cadastrar fornecedor.", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findSuppliers(List<String> categorias, String termo) {
        List<String> categoriasNormalizadas = categorias == null ? List.of() : categorias.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(categoria -> !categoria.isEmpty())
                .map(String::toLowerCase)
                .distinct()
                .toList();
        String termoNormalizado = normalizeTerm(termo);

        try {
            List<SupplierJpaEntity> suppliers;
            if (categoriasNormalizadas.isEmpty() && termoNormalizado.isEmpty()) {
                suppliers = supplierRepository.findAllByOrderByRazaoSocialAsc();
            } else if (categoriasNormalizadas.isEmpty()) {
                suppliers = supplierRepository.findByCompanyName(termoNormalizado);
                if (suppliers.isEmpty()) {
                    suppliers = supplierRepository.findBySubcategoryName(termoNormalizado);
                }
            } else if (termoNormalizado.isEmpty()) {
                suppliers = supplierRepository.findByCategoryNames(categoriasNormalizadas);
            } else {
                suppliers = supplierRepository.findByCategoryNamesAndCompanyName(categoriasNormalizadas, termoNormalizado);
                if (suppliers.isEmpty()) {
                    suppliers = supplierRepository.findByCategoryNamesAndSubcategoryName(
                            categoriasNormalizadas, termoNormalizado
                    );
                }
            }

            return suppliers.stream().map(this::toResponse).toList();
        } catch (DataAccessException ex) {
            log.error("Erro ao buscar fornecedores pelas categorias: {} e termo: {}", categoriasNormalizadas, termoNormalizado, ex);
            throw new SupplierSearchException("Falha ao buscar fornecedores.", ex);
        }
    }

    private void validateUniqueness(CreateSupplierRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }
        if (supplierRepository.existsByCnpj(request.cnpj())) {
            throw new CnpjAlreadyExistsException(request.cnpj());
        }
    }

    private String normalizeTerm(String termo) {
        if (termo == null) {
            return "";
        }

        return Normalizer.normalize(termo.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT);
    }

    private UserJpaEntity createUser(CreateSupplierRequest request) {
        UserJpaEntity user = new UserJpaEntity();
        user.setEmail(request.email());
        user.setSenhaHash(passwordEncoder.encode(request.senha()));
        user.setTipo(UserType.FORNECEDOR.name());
        return userRepository.save(user);
    }

    private SupplierModel buildSupplierModel(CreateSupplierRequest request, Long userId) {
        SupplierModel supplierModel = supplierMapper.toModel(request);
        supplierModel.setUserId(userId);
        return supplierModel;
    }

    private void createSupplierEntity(SupplierModel supplierModel) {
        SupplierJpaEntity supplier = supplierMapper.toSupplierEntity(supplierModel);
        supplierRepository.save(supplier);
    }

    private void createSubscription(SupplierModel supplierModel) {
        SubscriptionJpaEntity subscription = supplierMapper.toSubscriptionEntity(supplierModel);
        subscriptionRepository.save(subscription);
    }

    private SupplierResponse toResponse(SupplierJpaEntity supplier) {
        List<String> categorias = supplier.getSubcategories().stream()
                .map(subcategory -> subcategory.getCategory().getNome())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();

        return new SupplierResponse(
                supplier.getUserId(), supplier.getRazaoSocial(), supplier.getNomeFantasia(), supplier.getCnpj(),
                supplier.getDescricao(), supplier.getAtendimento(), supplier.getFaturamentoPrazo(), supplier.getWhatsapp(),
                supplier.getEmailComercial(), supplier.getTelefone(), supplier.getStatus(), supplier.getPlano(),
                supplier.getCidade(), supplier.getUf(), categorias
        );
    }
}
