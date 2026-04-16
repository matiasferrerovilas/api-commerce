package api.m2.commerce.mappers;

import api.m2.commerce.entities.Sale;
import api.m2.commerce.entities.SaleItem;
import api.m2.commerce.enums.PaymentMethod;
import api.m2.commerce.records.sales.SaleItemRecord;
import api.m2.commerce.records.sales.SaleRecord;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    private SaleItemMapper saleItemMapper;

    @Override
    public SaleRecord toRecord(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        Long id = null;
        LocalDate date = null;
        BigDecimal total = null;
        BigDecimal totalCost = null;
        Long currencyId = null;
        PaymentMethod paymentMethod = null;
        String notes = null;
        Long workspaceId = null;
        List<SaleItemRecord> items = null;
        Instant createdAt = null;

        id = sale.getId();
        date = sale.getDate();
        total = sale.getTotal();
        totalCost = sale.getTotalCost();
        currencyId = sale.getCurrencyId();
        paymentMethod = sale.getPaymentMethod();
        notes = sale.getNotes();
        workspaceId = sale.getWorkspaceId();
        items = saleItemListToSaleItemRecordList( sale.getItems() );
        createdAt = sale.getCreatedAt();

        BigDecimal margin = sale.getMargin();

        SaleRecord saleRecord = new SaleRecord( id, date, total, totalCost, margin, currencyId, paymentMethod, notes, workspaceId, items, createdAt );

        return saleRecord;
    }

    protected List<SaleItemRecord> saleItemListToSaleItemRecordList(List<SaleItem> list) {
        if ( list == null ) {
            return null;
        }

        List<SaleItemRecord> list1 = new ArrayList<SaleItemRecord>( list.size() );
        for ( SaleItem saleItem : list ) {
            list1.add( saleItemMapper.toRecord( saleItem ) );
        }

        return list1;
    }
}
