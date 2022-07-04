package com.example.airbnb.controller;

import com.example.airbnb.model.House;
import com.example.airbnb.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private IHouseService houseService;

    @GetMapping
    public ResponseEntity<Page<House>> findAllHouse(@PageableDefault(value = 5) Pageable pageable) {
        Page<House> houses = houseService.findAll(pageable);
        if (houses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(houses, HttpStatus.OK);

    }

//    @PostMapping
//    public ResponseEntity<House> saveHouse(@Valid @RequestBody House house) {
//        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<House> saveHouse(@RequestParam("file") MultipartFile file, House house) {
        String fileName = file.getOriginalFilename();
        house.setImage(fileName);
        try {
            file.transferTo(new File("/Users/tranquanghuy/Desktop/CODEGYM-BaiTapNew/SpringMVC/jwtSpringBoot/src/main/resources/templates/image/" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse (@RequestParam("file") MultipartFile file, @PathVariable Long id , House house){
        String fileName = file.getOriginalFilename();
        if(fileName.equals("")){
            house.setImage(houseService.findById(id).get().getImage());
            try {
                file.transferTo(new File("/Users/tranquanghuy/Desktop/CODEGYM-BaiTapNew/SpringMVC/jwtSpringBoot/src/main/resources/templates/image/" +house.getImage()));
            }catch (IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else {
            house.setImage(fileName);
            try {
                file.transferTo(new File("/Users/tranquanghuy/Desktop/CODEGYM-BaiTapNew/SpringMVC/quanlymuaban/src/main/resources/templates/image/" + fileName));
            } catch (IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        house.setId(id);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
