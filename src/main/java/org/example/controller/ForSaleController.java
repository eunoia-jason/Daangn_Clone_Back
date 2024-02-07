package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.domain.User;
import org.example.dto.request.ForSaleRequest;
import org.example.dto.request.UserRequest;
import org.example.service.ForSaleService;
import org.example.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/daangn/forSale")
public class ForSaleController {
    private final ForSaleService forSaleService;
    private final S3Service s3Service;

    // 매물 Create
    @PostMapping("/create")
    public void forSaleCreate(@RequestBody ForSaleRequest forSaleRequest) {
        forSaleRequest.setImageUrl(s3Service.getImageUrl(forSaleRequest.getImage()));
        forSaleService.create(forSaleRequest);
    }

    // 매물 이미지 s3에 업로드
    @PostMapping("/create/image/{email}")
    public String forSaleCreateImage(@RequestParam("image") MultipartFile image, @PathVariable String email) throws IOException {
        return s3Service.upload(image, email);
    }

    // 매물 Read
    @GetMapping("/read")
    public ResponseEntity<List<ForSale>> forSaleRead() {
        List<ForSale> forSales = forSaleService.read();
        return ResponseEntity.ok(forSales);
    }

    // 현재 로그인된 유저의 매물 Read
    @GetMapping("/read/{userId}")
    public ResponseEntity<List<ForSale>> forSaleReadByUserId(@PathVariable Long userId) {
        List<ForSale> forSales = forSaleService.readByUserId(userId);
        return ResponseEntity.ok(forSales);
    }

    // 매물 Update
    @PatchMapping("/update/{id}")
    public ResponseEntity<ForSale> forSaleUpdate(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        forSaleRequest.setImageUrl(s3Service.getImageUrl(forSaleRequest.getImage()));
        ForSale updatedForSale = forSaleService.update(id, forSaleRequest);
        return ResponseEntity.ok(updatedForSale);
    }

    // 매물 관심 수 Update
    @PatchMapping("/update/{id}/interest")
    public ResponseEntity<ForSale> forSaleUpdateInterest(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.updateInterest(id, forSaleRequest.getInterest());
        return ResponseEntity.ok(updatedForSale);
    }

    // 매물 조회 수 Update
    @PatchMapping("/update/{id}/view")
    public ResponseEntity<ForSale> forSaleUpdateView(@PathVariable Long id, @RequestBody ForSaleRequest forSaleRequest) {
        ForSale updatedForSale = forSaleService.updateView(id, forSaleRequest.getView());
        return ResponseEntity.ok(updatedForSale);
    }

    // 매물 Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> forSaleDelete(@PathVariable Long id) {
        if (forSaleService.readById(id).getImage() != null) {
            s3Service.delete(forSaleService.readById(id).getImage());
        }
        forSaleService.delete(id);
        return ResponseEntity.ok().build();
    }
}