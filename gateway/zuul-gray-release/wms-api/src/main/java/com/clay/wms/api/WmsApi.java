package com.clay.wms.api;

import org.springframework.web.bind.annotation.PathVariable;

public interface WmsApi {

    String delivery(@PathVariable("productId") Long productId);

}