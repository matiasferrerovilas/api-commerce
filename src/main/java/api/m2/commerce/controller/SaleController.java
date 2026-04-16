package api.m2.commerce.controller;

import api.m2.commerce.records.sales.SaleRecord;
import api.m2.commerce.records.sales.SaleToAdd;
import api.m2.commerce.services.sale.SaleAddService;
import api.m2.commerce.services.sale.SaleQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleQueryService saleQueryService;
    private final SaleAddService saleAddService;

    @GetMapping
    public ResponseEntity<List<SaleRecord>> findByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(saleQueryService.findByWorkspace(workspaceId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<SaleRecord>> findByDateRange(
            @RequestParam Long workspaceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return ResponseEntity.ok(saleQueryService.findByWorkspaceAndDateRange(workspaceId, startDate, endDate));
    }

    @GetMapping("/date")
    public ResponseEntity<List<SaleRecord>> findByDate(
            @RequestParam Long workspaceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(saleQueryService.findByWorkspaceAndDate(workspaceId, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleRecord> findById(@PathVariable Long id) {
        return ResponseEntity.ok(saleQueryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaleRecord> create(@Valid @RequestBody SaleToAdd dto) {
        SaleRecord created = saleAddService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleAddService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
