package com.clay.wms.client;

import com.clay.inventory.api.InventoryApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "inventory-service")
public interface InventoryFeignClient extends InventoryApi {

}