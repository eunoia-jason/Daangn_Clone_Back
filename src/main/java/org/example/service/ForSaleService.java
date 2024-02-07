package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.domain.User;
import org.example.dto.request.ForSaleRequest;
import org.example.repository.ForSaleRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ForSaleService {
    private final ForSaleRepository forSaleRepository;
    private final UserRepository userRepository;

    // 매물 Create
    public void create( ForSaleRequest request ){
        User user = userRepository.findById(request.getUser()).orElseThrow(() -> new EntityNotFoundException("User not Found with ID: " + request.getUser()));

        ForSale forSale = ForSale.builder()
                .id(request.getId())
                .title(request.getTitle())
                .category(request.getCategory())
                .price(request.getPrice())
                .interest(request.getInterest())
                .view(request.getView())
                .description(request.getDescription())
                .image(request.getImage())
                .imageUrl(request.getImageUrl())
                .user(user)
                .build();
        forSaleRepository.save(forSale);
    }

    // 전체 매물 Read
    public List<ForSale> read()  {
        return forSaleRepository.findAll();
    }

    // 매물 Read
    public ForSale readById(Long id)  {
        return forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));
    }

    // 유저 매물 Read
    public List<ForSale> readByUserId(Long id) {
        return forSaleRepository.findAllByUser_Id(id);
    }

    // 매물 정보 Update
    public ForSale update(Long id, ForSaleRequest request) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        if (!request.getTitle().equals(forSale.getTitle())) forSale.setTitle(request.getTitle());
        if (!request.getCategory().equals(forSale.getCategory())) forSale.setCategory(request.getCategory());
        if (request.getPrice() != forSale.getPrice()) forSale.setPrice(request.getPrice());
        if (!request.getDescription().equals(forSale.getDescription())) forSale.setDescription(request.getDescription());
        if (request.getImage() != null && !request.getImage().equals(forSale.getImage())) forSale.setImage(request.getImage());
        if (request.getImageUrl() != null && !request.getImageUrl().equals(forSale.getImageUrl())) forSale.setImageUrl(request.getImageUrl());
        forSale.setModDate(new Timestamp(System.currentTimeMillis()));

        return forSaleRepository.save(forSale);
    }

    // 매물 관심 수 Update
    public ForSale updateInterest(Long id, int interest) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        forSale.setInterest(interest);

        return forSaleRepository.save(forSale);
    }

    // 매물 조회 수 Update
    public ForSale updateView(Long id, int view) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        forSale.setView(view);

        return forSaleRepository.save(forSale);
    }

    // 매물 Delete
    public void delete(Long id) {
        forSaleRepository.deleteById(id);
    }
}