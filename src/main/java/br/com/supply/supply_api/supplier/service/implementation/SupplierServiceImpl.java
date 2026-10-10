package br.com.supply.supply_api.supplier.service.implementation;

import br.com.supply.supply_api.common.entity.UserJpaEntity;
import br.com.supply.supply_api.common.entity.UserType;
import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.dto.response.CreateSupplierResponse;
import br.com.supply.supply_api.supplier.entity.SubscriptionJpaEntity;
import br.com.supply.supply_api.supplier.entity.SupplierJpaEntity;
import br.com.supply.supply_api.supplier.exception.CnpjAlreadyExistsException;
import br.com.supply.supply_api.supplier.exception.EmailAlreadyExistsException;
import br.com.supply.supply_api.supplier.exception.SupplierCreationException;
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

    private void validateUniqueness(CreateSupplierRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }
        if (supplierRepository.existsByCnpj(request.cnpj())) {
            throw new CnpjAlreadyExistsException(request.cnpj());
        }
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
}
