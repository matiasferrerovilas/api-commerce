package api.m2.commerce.services.stock;

import api.m2.commerce.entities.Stock;
import api.m2.commerce.exceptions.EntityNotFoundException;
import api.m2.commerce.mappers.StockMapper;
import api.m2.commerce.records.stock.StockRecord;
import api.m2.commerce.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockQueryService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public List<StockRecord> findByWorkspace(Long workspaceId) {
        return stockRepository.findByWorkspaceId(workspaceId).stream()
                .map(stockMapper::toRecord)
                .toList();
    }

    public List<StockRecord> findLowStock(Long workspaceId) {
        return stockRepository.findLowStockByWorkspaceId(workspaceId).stream()
                .map(stockMapper::toRecord)
                .toList();
    }

    public StockRecord findByProductId(Long productId) {
        Stock stock = this.getByProductId(productId);
        return stockMapper.toRecord(stock);
    }

    public Stock getByProductId(Long productId) {
        return stockRepository.findByProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException("Stock para producto", productId));
    }
}
