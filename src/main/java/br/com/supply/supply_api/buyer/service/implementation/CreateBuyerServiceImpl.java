package br.com.supply.supply_api.buyer.service.implementation;

import br.com.supply.supply_api.buyer.dto.BuyerResponse;
import br.com.supply.supply_api.buyer.dto.CreateBuyerRequest;
import br.com.supply.supply_api.buyer.repository.BuyerRepository;
import br.com.supply.supply_api.buyer.repository.entity.BuyerJpaEntity;
import br.com.supply.supply_api.buyer.service.CreateBuyerService;
import br.com.supply.supply_api.buyer.service.mapper.BuyerMapper;
import br.com.supply.supply_api.buyer.service.model.BuyerModel;
import br.com.supply.supply_api.common.entity.UserJpaEntity;
import br.com.supply.supply_api.common.entity.UserType;
import br.com.supply.supply_api.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateBuyerServiceImpl implements CreateBuyerService {

    private final BuyerRepository buyerRepository;
    private final UserRepository userRepository;
    private final BuyerMapper buyerMapper;

    @Override
    public BuyerResponse create(CreateBuyerRequest createBuyerRequest) {

        UserJpaEntity user = createUser(createBuyerRequest);

        BuyerModel buyerModel = buyerMapper.buyerRequestToBuyerModel(createBuyerRequest);
        buyerModel.setUserId(user.getId());

        BuyerJpaEntity buyerJpaEntity = buyerMapper.buyerModelToBuyerJpaEntity(buyerModel);
        try {
            buyerJpaEntity = buyerRepository.save(buyerJpaEntity);
        } catch (Exception e) {
            log.error("Erro ao cadastrar comprador.", e);
            throw new RuntimeException(e);
        }

        return buyerMapper.buyerJpaEntityToBuyerResponse(buyerJpaEntity);
    }

    private UserJpaEntity createUser(CreateBuyerRequest createBuyerRequest) {
        UserJpaEntity user = new UserJpaEntity();
        user.setTipo(UserType.COMPRADOR.name());
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail(createBuyerRequest.email());
        user.setSenhaHash(createBuyerRequest.senha());

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            log.error("Erro ao cadastrar usuário, no cadastro de comprador.", e);
            throw new RuntimeException(e);
        }

        return user;
    }
}
