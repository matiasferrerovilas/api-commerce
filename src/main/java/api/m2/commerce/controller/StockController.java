package api.m2.commerce.controller;

import api.m2.commerce.records.stock.StockAdjustment;
import api.m2.commerce.records.stock.StockRecord;
import api.m2.commerce.services.stock.StockAddService;
import api.m2.commerce.services.stock.StockQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockQueryService stockQueryService;
    private final StockAddService stockAddService;

    @GetMapping
    public ResponseEntity<List<StockRecord>> findByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(stockQueryService.findByWorkspace(workspaceId));
    }

    @GetMapping("/low")
    public ResponseEntity<List<StockRecord>> findLowStock(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(stockQueryService.findLowStock(workspaceId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockRecord> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(stockQueryService.findByProductId(productId));
    }

    @PatchMapping("/product/{productId}/adjust")
    public ResponseEntity<StockRecord> adjust(
            @PathVariable Long productId,
            @Valid @RequestBody StockAdjustment dto
    ) {
        return ResponseEntity.ok(stockAddService.adjust(productId, dto));
    }

    @PatchMapping("/product/{productId}/quantity")
    public ResponseEntity<StockRecord> setQuantity(
            @PathVariable Long productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(stockAddService.setQuantity(productId, quantity));
    }

    @PatchMapping("/product/{productId}/min-stock")
    public ResponseEntity<StockRecord> setMinStock(
            @PathVariable Long productId,
            @RequestParam Integer minStock
    ) {
        return ResponseEntity.ok(stockAddService.setMinStock(productId, minStock));
    }
}
