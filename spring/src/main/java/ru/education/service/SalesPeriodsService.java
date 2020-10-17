package ru.education.service;

import org.springframework.stereotype.Service;
import ru.education.entity.Product;
import ru.education.entity.SalesPeriods;
import ru.education.exceptions.*;
import ru.education.jpa.SalesPeriodsRepository;

import java.util.List;

@Service
public class SalesPeriodsService {
    private final SalesPeriodsRepository salesPeriodsRepository;

    public SalesPeriodsService(SalesPeriodsRepository salesPeriodsRepository) {
        this.salesPeriodsRepository = salesPeriodsRepository;
    }


    public List<SalesPeriods> findAll() {
        return salesPeriodsRepository.findAll();
    }

    public SalesPeriods findById(Object id) {
        SalesPeriods salesPeriods;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Integer parsedId;
        try {
            parsedId = Integer.valueOf(id.toString());
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    " к нужному типу текст ошибки: %s", ex));
        }
        salesPeriods = salesPeriodsRepository.findById(parsedId).orElse(null);
        if (salesPeriods == null)
            throw new EntityNotFoundException(Product.Type_Name, parsedId);
        return salesPeriods;
    }

    public SalesPeriods create(SalesPeriods salesPeriods) {
        if (salesPeriods == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (salesPeriods.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        if (salesPeriods.getProduct() == null) {
            throw new EntityIllegalArgumentException("Поле Product не может быть null");
        }
        Integer productId = salesPeriods.getProduct().getId();
        if (productId == null) {
            throw new EntityIllegalArgumentException("Идентификатор продукта не может быть null");
        }
        if (salesPeriods.getDateFrom() == null) {
            throw new EntityIllegalArgumentException("Дата начала периода не может быть null");
        }
        SalesPeriods existedSalesPeriods = salesPeriodsRepository.findById(salesPeriods.getId()).orElse(null);
        if (existedSalesPeriods != null) {
            throw new EntityAlreadyExistsException(Product.Type_Name, salesPeriods.getId());
        }
        List<SalesPeriods> lastSalesPeriods = salesPeriodsRepository.findByDateToIsNullAndProductId(productId);
        if (lastSalesPeriods.size() > 0) {
            throw new EntityConflictException(String.format("В системе имеются открытые торговые периоды " +
                    "для продукта с id %s", productId));
        }
        return salesPeriodsRepository.save(salesPeriods);
    }

    public void delete(Object id) {
        SalesPeriods salesPeriods = findById(id);
       salesPeriodsRepository.delete(salesPeriods);
    }
}
