package com.clay.inventory.api;

import org.springframework.web.bind.annotation.PathVariable;

public interface InventoryApi {

    String deductStock(@PathVariable("productId") Long productId, @PathVariable("stock") Long stock);

}
