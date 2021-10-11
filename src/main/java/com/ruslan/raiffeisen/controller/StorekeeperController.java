package com.ruslan.raiffeisen.controller;

import com.ruslan.raiffeisen.entity.SocksEntity;
import com.ruslan.raiffeisen.exception.NotEnoghParametersOrIncorrectInput;
import com.ruslan.raiffeisen.exception.QuantityLessZero;
import com.ruslan.raiffeisen.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class StorekeeperController {

    @Autowired
    private SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity addSocks(@RequestBody SocksEntity socks){
        try {
            socksService.add(socks);
            return  ResponseEntity.ok("удалось добавить приход");
        }catch (NotEnoghParametersOrIncorrectInput e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity substractSocks(@RequestBody SocksEntity socks){
        try {
            socksService.substract(socks);
            return  ResponseEntity.ok("удалось отнять");
        }catch (NotEnoghParametersOrIncorrectInput | QuantityLessZero e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity getSocks(@RequestParam String color, String operation, byte cottonPart) {
        try {
            return ResponseEntity.ok(socksService.getEntities(color, operation, cottonPart));
        } catch (NotEnoghParametersOrIncorrectInput e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
