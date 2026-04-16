package api.m2.commerce.services.sale;

import api.m2.commerce.entities.Sale;
import api.m2.commerce.exceptions.EntityNotFoundException;
import api.m2.commerce.mappers.SaleMapper;
import api.m2.commerce.records.sales.SaleRecord;
import api.m2.commerce.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleQueryService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public List<SaleRecord> findByWorkspace(Long workspaceId) {
        return saleRepository.findByWorkspaceIdOrderByDateDesc(workspaceId).stream()
                .map(saleMapper::toRecord)
                .toList();
    }

    public List<SaleRecord> findByWorkspaceAndDateRange(Long workspaceId, LocalDate startDate, LocalDate endDate) {
        return saleRepository.findByWorkspaceIdAndDateBetweenOrderByDateDesc(workspaceId, startDate, endDate).stream()
                .map(saleMapper::toRecord)
                .toList();
    }

    public List<SaleRecord> findByWorkspaceAndDate(Long workspaceId, LocalDate date) {
        return saleRepository.findByWorkspaceIdAndDateOrderByCreatedAtDesc(workspaceId, date).stream()
                .map(saleMapper::toRecord)
                .toList();
    }

    public SaleRecord findById(Long id) {
        Sale sale = this.getById(id);
        return saleMapper.toRecord(sale);
    }

    public Sale getById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta", id));
    }
}
