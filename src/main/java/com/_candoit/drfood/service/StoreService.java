package com._candoit.drfood.service;

import com._candoit.drfood.domain.Store;
import com._candoit.drfood.enums.Category;
import com._candoit.drfood.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Page<Store> getStores(Category category, Pageable pageable) {
        return storeRepository.findByCategory(category, pageable);
    }
}
