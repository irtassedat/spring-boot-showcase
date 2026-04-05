package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workintech/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    // Tüm Address kayıtlarını döner
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Belirli bir Address objesini döner
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Yeni bir Address kaydı ekler
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    // Belirli bir Address objesini günceller
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            Address updatedAddress = address.get();
            updatedAddress.setStreet(addressDetails.getStreet());
            updatedAddress.setNo(addressDetails.getNo());
            updatedAddress.setCity(addressDetails.getCity());
            updatedAddress.setCountry(addressDetails.getCountry());
            updatedAddress.setDescription(addressDetails.getDescription());
            return ResponseEntity.ok(addressRepository.save(updatedAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Belirli bir Address objesini siler
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
