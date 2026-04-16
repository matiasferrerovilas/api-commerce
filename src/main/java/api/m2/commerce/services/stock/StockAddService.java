package api.m2.commerce.services.stock;

import api.m2.commerce.entities.Stock;
import api.m2.commerce.exceptions.BusinessException;
import api.m2.commerce.mappers.StockMapper;
import api.m2.commerce.records.stock.StockAdjustment;
import api.m2.commerce.records.stock.StockRecord;
import api.m2.commerce.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockAddService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final StockQueryService stockQueryService;

    public StockRecord adjust(Long productId, StockAdjustment dto) {
        Stock stock = stockQueryService.getByProductId(productId);

        int newQuantity = stock.getQuantity() + dto.quantity();
        if (newQuantity < 0) {
            throw new BusinessException("El stock no puede ser negativo. Stock actual: " + stock.getQuantity());
        }

        stock.setQuantity(newQuantity);
        Stock saved = stockRepository.save(stock);
        return stockMapper.toRecord(saved);
    }

    public StockRecord setQuantity(Long productId, Integer quantity) {
        if (quantity < 0) {
            throw new BusinessException("El stock no puede ser negativo");
        }

        Stock stock = stockQueryService.getByProductId(productId);
        stock.setQuantity(quantity);
        Stock saved = stockRepository.save(stock);
        return stockMapper.toRecord(saved);
    }

    public StockRecord setMinStock(Long productId, Integer minStock) {
        if (minStock < 0) {
            throw new BusinessException("El stock mínimo no puede ser negativo");
        }

        Stock stock = stockQueryService.getByProductId(productId);
        stock.setMinStock(minStock);
        Stock saved = stockRepository.save(stock);
        return stockMapper.toRecord(saved);
    }

    public void decrementStock(Long productId, Integer quantity) {
        Stock stock = stockQueryService.getByProductId(productId);

        int newQuantity = stock.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new BusinessException(
                    "Stock insuficiente para el producto. Disponible: " + stock.getQuantity()
                            + ", solicitado: " + quantity
            );
        }

        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
    }
}
