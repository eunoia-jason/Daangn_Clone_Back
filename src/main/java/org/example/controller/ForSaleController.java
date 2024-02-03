package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.domain.User;
import org.example.dto.request.ForSaleRequest;
import org.example.dto.request.UserRequest;
import org.example.service.ForSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/daangn/forSale")
public class ForSaleController {
    private final ForSaleService forSaleService;

    @PostMapping("/create")
    public void userCreate(@RequestBody ForSaleRequest forSaleRequest) {
        forSaleService.create(forSaleRequest);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ForSale> userUpdate(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.update(id, forSaleRequest);
        return ResponseEntity.ok(updatedForSale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id) {
        forSaleService.delete(id);
        return ResponseEntity.ok().build();
    }
}