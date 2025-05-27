package contracts.check_status

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return product availability with quantity and inventoryStatus only")
    request {
        method GET()
        urlPath("/api/inventory") {
            queryParameters {
                parameter("skuCodes", value("Iphone_13"))
            }
        }
    }
    response {
        status 200
        body([
                [
                        skuCode: "Iphone_13",
                        quantity: 12,
                        inventoryStatus: "IN_STOCK"
                ]
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
