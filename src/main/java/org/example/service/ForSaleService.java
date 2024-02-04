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
                .user(user)
                .build();
        forSaleRepository.save(forSale);
    }

    public List<ForSale> read()  {
        return forSaleRepository.findAll();
    }

    public List<ForSale> readById(Long id) {
        return forSaleRepository.findAllByUser_Id(id);
    }

    public ForSale update(Long id, ForSaleRequest request) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        if (!request.getTitle().equals(forSale.getTitle())) forSale.setTitle(request.getTitle());
        if (!request.getCategory().equals(forSale.getCategory())) forSale.setCategory(request.getCategory());
        if (request.getPrice() != forSale.getPrice()) forSale.setPrice(request.getPrice());
        if (!request.getDescription().equals(forSale.getDescription())) forSale.setDescription(request.getDescription());
        if (request.getImage() != null && !request.getImage().equals(forSale.getImage())) forSale.setImage(request.getImage());
        forSale.setModDate(new Timestamp(System.currentTimeMillis()));

        return forSaleRepository.save(forSale);
    }

    public ForSale updateInterest(Long id, int interest) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        forSale.setInterest(interest);

        return forSaleRepository.save(forSale);
    }

    public ForSale updateView(Long id, int view) {
        ForSale forSale = forSaleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ForSale not Found with ID: " + id));

        forSale.setView(view);

        return forSaleRepository.save(forSale);
    }

    public void delete(Long id) {
        forSaleRepository.deleteById(id);
    }
}