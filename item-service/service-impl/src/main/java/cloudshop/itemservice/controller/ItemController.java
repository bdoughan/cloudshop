package cloudshop.itemservice.controller;

import cloudshop.itemservice.entity.Item;
import cloudshop.itemservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping(path="/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<?> getItem(@PathVariable long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable long id) {
        Optional<Item> studentOptional = itemRepository.findById(id);
        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        itemRepository.save(item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.delete(itemOptional.get());
        return ResponseEntity.noContent().build();
    }

}