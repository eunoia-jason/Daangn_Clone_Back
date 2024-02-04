package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.domain.User;
import org.example.dto.request.ForSaleRequest;
import org.example.dto.request.UserRequest;
import org.example.service.ForSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/daangn/forSale")
public class ForSaleController {
    private final ForSaleService forSaleService;

    @PostMapping("/create")
    public void forSaleCreate(@RequestBody ForSaleRequest forSaleRequest) {
        forSaleService.create(forSaleRequest);
    }

    @GetMapping("/read")
    public ResponseEntity<List<ForSale>> forSaleRead() {
        List<ForSale> forSales = forSaleService.read();
        return ResponseEntity.ok(forSales);
    }

    @GetMapping("/read/{userId}")
    public ResponseEntity<List<ForSale>> forSaleReadByUserId(@PathVariable Long userId) {
        List<ForSale> forSales = forSaleService.readById(userId);
        return ResponseEntity.ok(forSales);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ForSale> forSaleUpdate(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.update(id, forSaleRequest);
        return ResponseEntity.ok(updatedForSale);
    }

    @PatchMapping("/update/{id}/interest")
    public ResponseEntity<ForSale> forSaleUpdateInterest(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.updateInterest(id, forSaleRequest.getInterest());
        return ResponseEntity.ok(updatedForSale);
    }

    @PatchMapping("/update/{id}/view")
    public ResponseEntity<ForSale> forSaleUpdateView(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.updateView(id, forSaleRequest.getView());
        return ResponseEntity.ok(updatedForSale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> forSaleDelete(@PathVariable Long id) {
        forSaleService.delete(id);
        return ResponseEntity.ok().build();
    }
}