package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.dto.request.ForSaleRequest;
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
}